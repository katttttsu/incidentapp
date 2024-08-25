package in.original.incidentapp;

public class IncidentData {
    private String category;
    private String segment;
    private String department;
    private String job;
    private int count;

    public IncidentData(String category, String segment, String department, String job, int count) {
        this.category = category;
        this.segment = segment;
        this.department = department;
        this.job = job;
        this.count = count;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment= segment;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}