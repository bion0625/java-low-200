package mySample.stock1.service;

import java.util.List;

import mySample.stock1.Info;
import mySample.stock1.model.Stock;

// 삼일 연달아 상승 가격 종목 구해오기
public class TreeDayPriceService {
    public static void main(String[] args) {
        TreeDayPriceService service = new TreeDayPriceService();
        service.start();
    }

    public void start() {
        Info info = new Info();
        List<Stock> stocks = info.getCompanyInfo();

        for (Stock stock : stocks) {
            stock.setPrices(info.getPriceInfo(stock.getCode(), 1).subList(0, 3));

            // 로그
            System.out.println(String.format("%d/%d", stocks.indexOf(stock), stocks.size()));
        }

        stocks = stocks.stream().filter(s -> {
            return s.getPrices() != null
            && s.getPrices().get(0).getHigh() > s.getPrices().get(1).getHigh()
            && s.getPrices().get(1).getHigh() > s.getPrices().get(2).getHigh()
            && s.getPrices().get(0).getLow() > s.getPrices().get(1).getLow()
            && s.getPrices().get(1).getLow() > s.getPrices().get(2).getLow();
        }).toList();

        System.out.println(stocks);
    }
}
