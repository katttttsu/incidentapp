package in.original.incidentapp;

public class IncidentData {
    private String category1;
    private String category2;
    private int count;

    public IncidentData(String category1, String category2, int count) {
        this.category1 = category1;
        this.category2 = category2;
        this.count = count;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}