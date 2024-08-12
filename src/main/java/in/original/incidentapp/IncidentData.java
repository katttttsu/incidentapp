package in.original.incidentapp;

public class IncidentData {
    private String largeCategory;
    private String smallCategory;
    private int count;

    public IncidentData(String largeCategory, String smallCategory, int count) {
        this.largeCategory = largeCategory;
        this.smallCategory = smallCategory;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}