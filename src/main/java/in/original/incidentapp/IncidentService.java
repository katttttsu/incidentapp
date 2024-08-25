package in.original.incidentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public int getAnnualTotal(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        return incidents.size();
    }

    public List<IncidentData> findAll() {
        return incidentRepository.findAll();
    }

    public Map<String, Integer> getCategoryCounts(List<IncidentEntity> incidents) {
        Map<String, Integer> counts = new HashMap<>();
        for (IncidentEntity incident : incidents) {
            String category = incident.getCategory() + " ／ " + incident.getSegment();
            counts.put(category, counts.getOrDefault(category, 0) + 1);
        }
        return counts;
    }

    public Map<String, Integer> getLevelCounts(List<IncidentEntity> incidents) {
        Map<String, Integer> counts = new HashMap<>();
        for (IncidentEntity incident : incidents) {
            String level = incident.getLevel();
            counts.put(level, counts.getOrDefault(level, 0) + 1);
        }
        return counts;
    }

    public Map<String, Integer> getDepartmentCounts(List<IncidentEntity> incidents) {
        Map<String, Integer> counts = new HashMap<>();
        for (IncidentEntity incident : incidents) {
            String department = incident.getDepartment();
            counts.put(department, counts.getOrDefault(department, 0) + 1);
        }
        return counts;
    }

    public Map<String, Integer> getJobCounts(List<IncidentEntity> incidents) {
        Map<String, Integer> counts = new HashMap<>();
        for (IncidentEntity incident : incidents) {
            String job = incident.getJob();
            counts.put(job, counts.getOrDefault(job, 0) + 1);
        }
        return counts;
    }

    private List<String> levels = Arrays.asList("0", "1", "2", "3a", "3b", "4", "5");
    private List<String> categories = Arrays.asList("転倒・転落", "外傷", "薬剤", "食事", "受付", "診察", "検査・処置", "放射線", "リハビリ", "機器操作", "チューブ・カテーテル", "輸液ルート", "手術", "その他");
    private List<String> departments = Arrays.asList("整形外科", "形成外科", "外科", "皮膚科", "循環器内科", "呼吸器内科", "呼吸器外科", "消火器内科", "脳神経外科", "泌尿器科", "眼科", "麻酔科", "放射線科", "リハビリテーション科", "薬剤部", "医療技術部", "看護部", "その他");
    private List<String> jobs = Arrays.asList("医師", "看護師", "薬剤師", "理学療法士", "作業療法士", "視能訓練士", "臨床検査技師", "臨床工学技士", "診療放射線技師", "看護補助者", "事務員", "その他");

    public Map<String, Map<Integer, Integer>> countIncidentsByLevel(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> levelData = new HashMap<>();

        for (String level : levels) {
            Map<Integer, Integer> monthData = new HashMap<>();
            for (int month = 1; month <= 12; month++) {
                monthData.put(month, 0);
            }
            levelData.put(level, monthData);
        }

        for (IncidentEntity incident : incidents) {
            String level = incident.getLevel();
            int month = incident.getDate().getMonthValue();
            if (levelData.containsKey(level)) {
                levelData.get(level).put(month, levelData.get(level).get(month) + 1);
            }
        }

        return levelData;
    }

    public Map<String, Map<Integer, Integer>> countIncidentsByCategory(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> categoryData = new HashMap<>();

        for (String category : categories) {
            Map<Integer, Integer> monthData = new HashMap<>();
            for (int month = 1; month <= 12; month++) {
                monthData.put(month, 0);
            }
            categoryData.put(category, monthData);
        }

        for (IncidentEntity incident : incidents) {
            String category = incident.getCategory();
            int month = incident.getDate().getMonthValue();
            if (categoryData.containsKey(category)) {
                categoryData.get(category).put(month, categoryData.get(category).get(month) + 1);
            }
        }

        return categoryData;
    }


    public Map<String, Map<Integer, Integer>> countIncidentsByDepartment(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> departmentData = new HashMap<>();

        for (String department : departments) {
            Map<Integer, Integer> monthData = new HashMap<>();
            for (int month = 1; month <= 12; month++) {
                monthData.put(month, 0);
            }
            departmentData.put(department, monthData);
        }

        for (IncidentEntity incident : incidents) {
            String department = incident.getDepartment();
            int month = incident.getDate().getMonthValue();
            if (departmentData.containsKey(department)) {
                departmentData.get(department).put(month, departmentData.get(department).get(month) + 1);
            }
        }

        return departmentData;
    }

    public Map<String, Map<Integer, Integer>> countIncidentsByJob(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> jobData = new HashMap<>();

        for (String job : jobs) {
            Map<Integer, Integer> monthData = new HashMap<>();
            for (int month = 1; month <= 12; month++) {
                monthData.put(month, 0);
            }
            jobData.put(job, monthData);
        }

        for (IncidentEntity incident : incidents) {
            String job = incident.getJob();
            int month = incident.getDate().getMonthValue();
            if (jobData.containsKey(job)) {
                jobData.get(job).put(month, jobData.get(job).get(month) + 1);
            }
        }

        return jobData;
    }

    public Map<String, Integer> getLevelTotals(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Integer> levelTotals = new HashMap<>();

        List<String> levels = Arrays.asList("0", "1", "2", "3a", "3b", "4", "5");
        for (String level : levels) {
            levelTotals.put(level, 0);
        }

        for (IncidentEntity incident : incidents) {
            String level = incident.getLevel();
            levelTotals.put(level, levelTotals.getOrDefault(level, 0) + 1);
        }

        return levelTotals;
    }

    public Map<String, Integer> getCategoryTotals(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Integer> categoryTotals = new HashMap<>();

        List<String> categories = Arrays.asList("転倒・転落", "外傷", "薬剤", "食事", "受付", "診察", "検査・処置", "放射線", "リハビリ", "機器操作", "チューブ・カテーテル", "輸液ルート", "手術", "その他");
        for (String category : categories) {
            categoryTotals.put(category, 0);
        }

        for (IncidentEntity incident : incidents) {
            String category = incident.getCategory();
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0) + 1);
        }

        return categoryTotals;
    }

    public Map<String, Integer> getDepartmentTotals(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Integer> departmentTotals = new HashMap<>();

        List<String> departments = Arrays.asList("整形外科", "形成外科", "外科", "皮膚科", "循環器内科", "呼吸器内科", "呼吸器外科", "消火器内科", "脳神経外科", "泌尿器科", "眼科", "麻酔科", "放射線科", "リハビリテーション科", "薬剤部", "医療技術部", "看護部", "その他");
        for (String department : departments) {
            departmentTotals.put(department, 0);
        }

        for (IncidentEntity incident : incidents) {
            String department = incident.getDepartment();
            departmentTotals.put(department, departmentTotals.getOrDefault(department, 0) + 1);
        }

        return departmentTotals;
    }

    public Map<String, Integer> getJobTotals(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Integer> jobTotals = new HashMap<>();

        List<String> jobs = Arrays.asList("医師", "看護師", "薬剤師", "理学療法士", "作業療法士", "視能訓練士", "臨床検査技師", "臨床工学技士", "診療放射線技師", "看護補助者", "事務員", "その他");
        for (String job : jobs) {
            jobTotals.put(job, 0);
        }

        for (IncidentEntity incident : incidents) {
            String job = incident.getJob();
            jobTotals.put(job, jobTotals.getOrDefault(job, 0) + 1);
        }

        return jobTotals;
    }
}