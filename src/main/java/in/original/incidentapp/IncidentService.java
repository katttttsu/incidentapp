package in.original.incidentapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public void updateIncident(IncidentEntity incident) {
        incidentRepository.update(incident);
    }

    public void addIncident(IncidentEntity incident) {
        incidentRepository.insert(
                incident.getLevel(),
                incident.getDate(),
                incident.getTime(),
                incident.getPlace(),
                incident.getPatientId(),
                incident.getPatientName(),
                incident.getPatientAge(),
                incident.getDepartment(),
                incident.getJob(),
                incident.getContinuousService(),
                incident.getCategory1(),
                incident.getCategory2(),
                incident.getSituation(),
                incident.getCause(),
                incident.getSuggestion(),
                incident.getCountermeasure()
        );
    }

    public void deleteIncident(long id) {
        incidentRepository.delete(id);
    }

    public Map<String, Long> getMonthlyIncidentCounts(int year) {
        Map<String, Long> monthlyCounts = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            LocalDate startDate = LocalDate.of(year, month, 1);
            LocalDate endDate = startDate.plusMonths(1).minusDays(1); // End of the month
            long count = incidentRepository.countByDateBetween(startDate, endDate);
            monthlyCounts.put(String.format("%d-%02d", year, month), count);
        }
        return monthlyCounts;
    }

    public List<IncidentEntity> findIncidentsByCriteria(LocalDate startDate, LocalDate endDate, String department, String job) {
        return incidentRepository.findByCriteria(startDate, endDate, department, job);
    }

    public List<String> getDistinctDepartments() {
        return incidentRepository.findDistinctDepartments();
    }

    public List<String> getDistinctJobs() {
        return incidentRepository.findDistinctJobs();
    }
}