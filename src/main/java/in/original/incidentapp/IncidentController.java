package in.original.incidentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class IncidentController {

    @Value("${admin.password}")
    private String adminPassword;

    private final IncidentRepository incidentRepository;
    private final IncidentMapper incidentMapper;
    private final IncidentService incidentService;

    @Autowired
    public IncidentController(IncidentRepository incidentRepository, IncidentMapper incidentMapper, IncidentService incidentService) {
        this.incidentRepository = incidentRepository;
        this.incidentMapper = incidentMapper;
        this.incidentService = incidentService;
    }

    @GetMapping("/incidentForm")
    public String showIncidentForm(Model model) {
        model.addAttribute("incidentForm", new IncidentForm());

        Map<String, List<String>> subCategoryMap = new HashMap<>();
        subCategoryMap.put("転倒・転落", Arrays.asList("転倒", "転落", "滑落", "その他"));
        subCategoryMap.put("外傷", Arrays.asList("熱傷", "擦過傷", "表皮剥離", "打撲", "骨折", "自傷行為", "その他"));
        subCategoryMap.put("薬剤", Arrays.asList("薬剤の過不足", "薬剤の入れ間違い", "薬袋の記入間違い", "配薬ミス", "患者間違い", "患者による与薬トラブル", "その他"));
        subCategoryMap.put("食事", Arrays.asList("患者間違い", "誤嚥・誤飲", "異物混入", "指示と食事内容の違い", "その他"));
        subCategoryMap.put("受付", Arrays.asList("患者間違い", "カルテ作成ミス", "受診科登録ミス", "受付対応の不備", "電話対応", "その他"));
        subCategoryMap.put("検査・処置", Arrays.asList("患者間違い", "検体の採り間違い", "未採集", "不適切な前処置", "その他"));
        subCategoryMap.put("放射線", Arrays.asList("患者間違い", "部位間違い", "撮影条件の間違い", "撮影端末への入力ミス", "その他"));
        subCategoryMap.put("リハビリ", Arrays.asList("患者間違い", "部位間違い", "設定条件の間違い", "評価ミス", "その他"));
        subCategoryMap.put("機器操作", Arrays.asList("操作ミス", "故障・不具合", "電源の入れ忘れ", "設定条件の間違い", "その他"));
        subCategoryMap.put("チューブ・カテーテル", Arrays.asList("自己抜去", "自然抜去", "漏れ", "留置固定部障害", "その他"));
        subCategoryMap.put("輸液ルート", Arrays.asList("自己抜去", "接続の緩み", "三方活栓の方向間違い", "クランプの開閉", "その他"));
        subCategoryMap.put("手術", Arrays.asList("患者間違い", "部位間違い", "連絡ミス", "機具の不足・調整ミス"));
        subCategoryMap.put("その他", Arrays.asList("離院", "患者間トラブル", "その他"));

        model.addAttribute("mainCategories", subCategoryMap.keySet());
        model.addAttribute("subCategoryMap", subCategoryMap);
        return "incidentForm";
    }

    @PostMapping("/incidents")
    public String createIncident(@ModelAttribute IncidentForm incidentForm) {
        incidentMapper.insert(
                incidentForm.getLevel(),
                incidentForm.getDate(), incidentForm.getTime(),
                incidentForm.getPlace(),
                incidentForm.getPatientId(), incidentForm.getPatientName(),
                incidentForm.getPatientAge(),
                incidentForm.getDepartment(), incidentForm.getJob(),
                incidentForm.getContinuousService(),
                incidentForm.getCategory1(), incidentForm.getCategory2(),
                incidentForm.getSituation(), incidentForm.getCause(),
                incidentForm.getSuggestion(), incidentForm.getCountermeasure()
        );
        return "redirect:/";
    }

    @GetMapping("/")
    public String showIncidents(Model model) {
        LocalDate now = LocalDate.now();
        return searchIncidents(now.getYear(), now.getMonthValue(), "", "", model);
    }

    @GetMapping("/searchIncidents")
    public String searchIncidents(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "department", required = false) String department,
            @RequestParam(value = "job", required = false) String job,
            Model model) {

        if (year == null) {
            year = LocalDate.now().getYear();
        }
        if (month == null) {
            month = LocalDate.now().getMonthValue();
        }

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        List<IncidentEntity> incidents = incidentRepository.findByCriteria(startDate, endDate, department, job);
        model.addAttribute("incidents", incidents);

        return "index";
    }

    @GetMapping("/incidents/{id}")
    public String incidentDetail(@PathVariable long id, Model model) {
        var incidentOptional = incidentRepository.findById(id);
        var incident = incidentOptional.orElseThrow(() -> new IllegalArgumentException("Invalid incident Id:" + id));
        model.addAttribute("incident", incident);

        Map<String, List<String>> subCategoryMap = new HashMap<>();
        subCategoryMap.put("転倒・転落", Arrays.asList("転倒", "転落", "滑落", "その他"));
        subCategoryMap.put("外傷", Arrays.asList("熱傷", "擦過傷", "表皮剥離", "打撲", "骨折", "自傷行為", "その他"));
        subCategoryMap.put("薬剤", Arrays.asList("薬剤の過不足", "薬剤の入れ間違い", "薬袋の記入間違い", "持参薬鑑別関連", "配薬ミス", "副作用", "患者間違い", "患者による与薬トラブル", "その他"));
        subCategoryMap.put("食事", Arrays.asList("患者間違い", "誤嚥・誤飲", "異物混入", "指示と食事内容の違い", "その他"));
        subCategoryMap.put("受付", Arrays.asList("患者間違い", "カルテ作成ミス", "受診科登録ミス", "受付対応の不備", "電話対応", "その他"));
        subCategoryMap.put("検査・処置", Arrays.asList("患者間違い", "検体の採り間違い", "未採集", "不適切な前処置", "その他"));
        subCategoryMap.put("放射線", Arrays.asList("患者間違い", "部位間違い", "撮影条件の間違い", "撮影端末への入力ミス", "その他"));
        subCategoryMap.put("リハビリ", Arrays.asList("患者間違い", "部位間違い", "設定条件の間違い", "評価ミス", "理学・作業療法時のトラブル", "その他"));
        subCategoryMap.put("機器操作", Arrays.asList("操作ミス", "故障・不具合", "電源の入れ忘れ", "設定条件の間違い", "その他"));
        subCategoryMap.put("チューブ・カテーテル", Arrays.asList("自己抜去", "自然抜去", "漏れ", "留置固定部障害", "その他"));
        subCategoryMap.put("輸液ルート", Arrays.asList("自己抜去", "接続の緩み", "三方活栓の方向間違い", "クランプの開閉", "その他"));
        subCategoryMap.put("手術", Arrays.asList("患者間違い", "部位間違い", "連絡ミス", "機具の不足・調整ミス"));
        subCategoryMap.put("その他", Arrays.asList("離院", "患者間トラブル", "その他"));

        model.addAttribute("subCategoryMap", subCategoryMap);
        return "detail";
    }

    @PostMapping("/incidents/{id}/update")
    public String updateIncident(@PathVariable long id, @ModelAttribute("incidentForm") IncidentForm incidentForm) {
        incidentMapper.update(
                id, incidentForm.getLevel(),
                incidentForm.getDate(), incidentForm.getTime(),
                incidentForm.getPlace(),
                incidentForm.getPatientId(), incidentForm.getPatientName(),
                incidentForm.getPatientAge(),
                incidentForm.getDepartment(), incidentForm.getJob(),
                incidentForm.getContinuousService(),
                incidentForm.getCategory1(), incidentForm.getCategory2(),
                incidentForm.getSituation(), incidentForm.getCause(),
                incidentForm.getSuggestion(), incidentForm.getCountermeasure()
        );
        return "redirect:/";
    }

    @PostMapping("/incidents/{id}/delete")
    public String deleteIncident(@PathVariable long id, @RequestParam("adminPassword") String inputPassword, Model model) {
        if (!adminPassword.equals(inputPassword)) {
            model.addAttribute("errorMessage", "管理者パスワードが間違っています");
            return "error";
        }
        incidentMapper.delete(id);
        return "redirect:/";
    }
}
