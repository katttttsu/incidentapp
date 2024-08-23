package in.original.incidentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class IncidentService {



    @Autowired
    private IncidentRepository incidentRepository;

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

    public List<IncidentData> countIncidentsByCriteria(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, IncidentData> dataMap = new HashMap<>();

        for (String level : levels) {
            for (String category : categories) {
                for (String department : departments) {
                    for (String job : jobs) {
                        String key = level + "-" + category + "-" + department + "-" + job;
                        dataMap.put(key, new IncidentData(level, category, department, job, 0));
                    }
                }
            }
        }

        for (IncidentEntity incident : incidents) {
            String key = incident.getLevel() + "-" + incident.getCategory() + "-" + incident.getDepartment() + "-" + incident.getJob();
            IncidentData data = dataMap.get(key);
            if (data != null) {
                data.setCount(data.getCount() + 1);
            }
        }

        return new ArrayList<>(dataMap.values());
    }

    public Map<String, Map<Integer, Integer>> countIncidentsByLevel(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> levelData = new HashMap<>();

        for (String level : levels) {
            levelData.put(level, new HashMap<>());
            for (int month = 1; month <= 12; month++) {
                levelData.get(level).put(month, 0);
            }
        }

        for (IncidentEntity incident : incidents) {
            String level = incident.getLevel();
            int month = incident.getDate().getMonthValue();
            levelData.get(level).put(month, levelData.get(level).get(month) + 1);
        }

        return levelData;
    }

    public Map<String, Map<Integer, Integer>> countIncidentsByCategory(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> categoryData = new HashMap<>();

        for (String category : categories) {
            categoryData.put(category, new HashMap<>());
            for (int month = 1; month <= 12; month++) {
                categoryData.get(category).put(month, 0);
            }
        }

        for (IncidentEntity incident : incidents) {
            String category = incident.getCategory();
            int month = incident.getDate().getMonthValue();
            categoryData.get(category).put(month, categoryData.get(category).get(month) + 1);
        }

        return categoryData;
    }

    public Map<String, Map<Integer, Integer>> countIncidentsByDepartment(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> departmentData = new HashMap<>();

        for (String department : departments) {
            departmentData.put(department, new HashMap<>());
            for (int month = 1; month <= 12; month++) {
                departmentData.get(department).put(month, 0);
            }
        }

        for (IncidentEntity incident : incidents) {
            String department = incident.getDepartment();
            int month = incident.getDate().getMonthValue();

            if (department == null || !departmentData.containsKey(department)) {
                department = "その他";
            }

            departmentData.get(department).put(month, departmentData.get(department).get(month) + 1);
        }

        return departmentData;
    }

    public Map<String, Map<Integer, Integer>> countIncidentsByJob(int year) {
        List<IncidentEntity> incidents = incidentRepository.findByYear(year);
        Map<String, Map<Integer, Integer>> jobData = new HashMap<>();

        for (String job : jobs) {
            jobData.put(job, new HashMap<>());
            for (int month = 1; month <= 12; month++) {
                jobData.get(job).put(month, 0);
            }
        }

        for (IncidentEntity incident : incidents) {
            String job = incident.getJob();
            int month = incident.getDate().getMonthValue();

            if (job == null || !jobData.containsKey(job)) {
                job = "その他";
            }

            jobData.get(job).put(month, jobData.get(job).get(month) + 1);
        }

        return jobData;
    }

}
