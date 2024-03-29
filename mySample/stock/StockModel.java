package mySample.stock;

public class StockModel {
    private String name;
    private String code;
    private Boolean isKospi;
    private int nowPrice;
    private int newHighPrice;

    public StockModel(String code){
        this.code = code;
    }

    public StockModel(String name, String code, boolean isKospi) {
        this.name = name;
        this.code = code;
        this.isKospi = isKospi;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String getSavingText() {
        return String.format("%s/%s", code, isKospi ? "1" : "0");
    }

    public void setNewHighPrice(int newHighPrice){
        this.newHighPrice = newHighPrice;
    }

    public int getNewHighPrice(){
        return this.newHighPrice;
    }

    public void setNowPrice(int nowPrice){
        this.nowPrice = nowPrice;
    }

    public int getNowPrice(){
        return this.nowPrice;
    }
}
