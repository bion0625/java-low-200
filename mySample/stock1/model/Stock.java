package mySample.stock1.model;

import java.util.List;

public class Stock {
    private String name; // 종목명
    private String code; // 종목코드
    private int totalPage; // 네이버에서 가져올 전체 페이지
    private List<Price> prices; // 가격정보 리스트

    @Override
    public String toString() {
        return String.format("(name: %s, code: %s, totalPage: %d, prices: %s)", name, code, totalPage, prices == null ? "" : prices.toString());
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return this.totalPage;
    }
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }
    public List<Price> getPrices() {
        return this.prices;
    }
}
