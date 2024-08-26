理解を深める為、分解して意味を考える\\
@Controller\
public class IncidentController {\
@Controller アノテーションを使用しており、Spring MVCのコントローラーとして機能。\\
@Autowired\
private IncidentService incidentService;\
IncidentService クラスを自動的に注入し、コントローラー内でサービスを利用できる。\
@Autowiredを使用する事でSpringのコンテナが自動的に適切な型のBeanを探してフィールドやコンストラクタ、またはセッターに注入する。\\
@Value("${admin.password}")\
private String adminPassword;\\
@Value("${openai.api.key}")\
private String openaiApiKey;\\

private final IncidentRepository incidentRepository;\
private final IncidentMapper incidentMapper;\
@Value アノテーションを使用して、アプリケーションのプロパティファイル (application.properties) から値を取得する。これには管理者パスワードとOpenAI APIキーが含まれます。\
IncidentRepository と IncidentMapper はコンストラクターインジェクションを通じて注入される。\
@Valueはを使用する事でアプリケーションの設定値や外部の値を簡単に取得し、フィールド、コンストラクタ、メソッドに注入する事が出来る。\\\
## 年次サマリーの表示 (/annualSummary)\

@GetMapping("/annualSummary")\
public String showAnnualSummary(Model model) {\
年次サマリーを表示するためのメソッドです。Model オブジェクトを使用してテンプレートにデータを渡す。\\

int year = LocalDate.now().getYear();\
現在の年を取得する。\\

Map<String, Map<Integer, Integer>> levelData = incidentService.countIncidentsByLevel(year);\
IncidentService クラスのメソッドを呼び出して、年ごとのレベル別インシデント件数を取得する。ここでは、インシデントを月ごとに集計してマップ形式で返す。\\

model.addAttribute("levelData", levelData);\
取得したデータをテンプレートに渡す。levelData はテンプレート内でアクセスできる名前の事。\\

int totalAnnualSum = levelTotals.values().stream().mapToInt(Integer::intValue).sum();\
年間のインシデント件数を合計する。\
values().stream().mapToInt(Integer::intValue).sum()はjava Stream APIを使用してコレクションやマップの値の合計を求める際によく使われるコード。\
map.values()はmapのすべての値を取得する。\
stream()は取得した値のコレクションをストリームに変換する。stream APIを使用してデータの処理を行う事が出来る。\
.mapToInt(Integer::intValue)はstream<Integer>をInstreamに変換する。Integer::intValueはメソッド参照で各Integerオブジェクトをプリミティブ型のintの変換する。\
sum()は変換されたintstreamの全てに値を合計する。\\


## 年次サマリーの検索 (/SearchAnnualSummaries)\

@GetMapping("/SearchAnnualSummaries")\
public String searchAnnualSummary(@RequestParam(value = "year", required = false) Integer year, Model model) {\
年次サマリーを指定された年で検索するためのメソッド。\
@RequestParam アノテーションを使用して、リクエストパラメータとして年を受け取る。\\

if (year == null || year == 0) {\
    year = LocalDate.now().getYear();\
}\
年が指定されていない場合、現在の年を使用する。\\


## インシデントフォームの表示 (/incidentForm)\

@GetMapping("/incidentForm")\
public String showIncidentForm(Model model) {\
インシデントを新たに登録するためのフォームを表示する。\\

Map<String, List<String>> subCategoryMap = new HashMap<>();\
subCategoryMap.put("転倒・転落", Arrays.asList("転倒", "転落", "滑落", "その他"));\
大カテゴリごとにサブカテゴリのマッピングを設定する。subCategoryMap は大カテゴリとそれに対応するサブカテゴリのリストを保持する。\\

model.addAttribute("subCategoryMap", subCategoryMap);\
このマッピングをテンプレートに渡し、フォーム内で使用する。\\


## インシデントの作成 (/incidents)\

@PostMapping("/incidents")\
public String createIncident(@ModelAttribute IncidentForm incidentForm) {\
インシデントを新規作成または更新するためのメソッド。\
@ModelAttribute アノテーションを使用して、フォームデータを IncidentForm オブジェクトにバインドする。\\

if (incidentForm.getId() == null) {\
    incidentMapper.insert(...);\
} else {\
    incidentMapper.update(...);\
}
新しいインシデントか既存のインシデントかを確認し、それに応じて挿入または更新を行う。\\


##OpenAIを使用した提案生成 (/generateAISuggestion)\

@PostMapping("/generateAISuggestion")\
@ResponseBody\
public Map<String, String> generateAISuggestion(@RequestBody Map<String, String> request) {\
ユーザーが入力した原因に基づいて、AI（OpenAI）から提案を生成するメソッド。\
@ResponseBodyはSpring Freamworkのアノテーションでコントローラーのメソッドの戻り値をHTTPレスポンスのボディとして直接出力する為に使用される。\\

String suggestion = getAIPrediction(prompt);\
getAIPrediction メソッドを使用して、OpenAIから提案を取得する。\\

private String getAIPrediction(String prompt) {\
    OkHttpClient client = new OkHttpClient();\
    ...\
    try (Response response = client.newCall(request).execute()) {\
        if (response.isSuccessful() && response.body() != null) {\
            ...\
        } else {\
            return handleAPIError(errorResponse);\
        }\
    } catch (IOException e) {\
        e.printStackTrace();\
        return "提案が取得できませんでした: " + e.getMessage();\
    }\
}\
OkHttpClient を使用して、OpenAIのAPIにリクエストを送信し、提案を取得する。\
okHttpClientはHttpリクエストを簡単に送信し、レスポンスを受け取る事でできるライブラリ。シンプルなAPIで非同期リクエストもサポートしている。また接続の再利用や接続プールの管理が自動的に行われる。\\


## グラフデータのテンプレートへの追加 (addGraphDataToModel)\

private void addGraphDataToModel(Model model, List<IncidentEntity> incidents) {\
    model.addAttribute("categoryCounts", getCategoryCounts(incidents));\
    model.addAttribute("levelCounts", getLevelCounts(incidents));
    model.addAttribute("departmentCounts", getDepartmentCounts(incidents));\
    model.addAttribute("jobCounts", getJobCounts(incidents));\
}\
グラフ表示用のデータを計算し、テンプレートに渡する。各メソッド（getCategoryCounts など）で、インシデントリストを集計し、カテゴリやレベルごとの件数を算出する。
