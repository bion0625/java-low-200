package mySample.stock;

public class StockModel {
    private String name;
    private String code;
    private Boolean isKospi;

    public StockModel(String name, String code, boolean isKospi) {
        this.name = name;
        this.code = code;
        this.isKospi = isKospi;
    }

    public String getSavingText() {
        return String.format("%s,%s,%s", name, code, isKospi ? "코스피" : "코스닥");
    }
}
