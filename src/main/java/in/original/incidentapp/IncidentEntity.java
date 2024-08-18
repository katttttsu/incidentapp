package in.original.incidentapp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "incidents")
public class IncidentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String level;
    private LocalDate date;
    private LocalTime time;
    private String place;
    private Long patientId;
    private String patientName;
    private Integer patientAge;
    private String department;
    private String job;
    private String continuousService;
    private String largeCategory;
    private String smallCategory;
    private String customSubCategory;

    private String situation;
    private String cause;
    private String suggestion;
    private String countermeasure;

    public IncidentEntity() {}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    public void setPatientAge(Integer patientAge) {
        this.patientAge = patientAge;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getContinuousService() {
        return continuousService;
    }

    public void setContinuousService(String continuousService) {
        this.continuousService = continuousService;
    }

    public String getLargeCategory() {
        return largeCategory;
    }

    public void setLargeCategory(String largeCategory) {
        this.largeCategory = largeCategory;
    }

    public String getSmallCategory() {
        return smallCategory;
    }

    public void setSmallCategory(String smallCategory) {
        this.smallCategory = smallCategory;
    }

    public String getCustomSubCategory() {
        return customSubCategory;
    }

    public void setCustomSubCategory(String customSubCategory) {
        this.customSubCategory = customSubCategory;
    }

    public String getSituation() {
        return situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getCountermeasure() {
        return countermeasure;
    }

    public void setCountermeasure(String countermeasure) {
        this.countermeasure = countermeasure;
    }
}
