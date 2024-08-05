package in.original.incidentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class IncidentService {

    @Autowired
    private IncidentRepository incidentRepository;

    public List<IncidentData> findAll() {
        return incidentRepository.findAll();
    }
    public Map<String, Integer> getCategoryCounts(List<IncidentEntity> incidents) {
        Map<String, Integer> counts = new HashMap<>();
        // categoriesの初期化と集計処理
        return counts;
    }

    public Map<String, Integer> getLevelCounts(List<IncidentEntity> incidents) {
        Map<String, Integer> counts = new HashMap<>();
        // levelsの初期化と集計処理
        return counts;
    }

    public Map<String, Integer> getDepartmentCounts(List<IncidentEntity> incidents) {
        Map<String, Integer> counts = new HashMap<>();
        // departmentsの初期化と集計処理
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
}