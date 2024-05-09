package mySample.stock1.service;

import java.util.List;
import java.util.stream.Collectors;

import mySample.stock1.Info;
import mySample.stock1.model.Price;
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
            List<Price> prices = info.getPriceInfoByPage(stock.getCode(), 1, 10);
            // 100일 중 신고가
            Price checkPrice = prices.stream().reduce((p, c) -> p.getHigh() > c.getHigh() ? p : c).orElse(null);
            if(checkPrice != null && prices.get(0).getHigh() == checkPrice.getHigh()) {
                stock.setPrices(prices.subList(0, 3));
            }

            // 최근 3일 중 오늘이 최고 거래량이면 제외
            checkPrice = stock.getPrices().stream().reduce((p, c) -> p.getVolume() > c.getVolume() ? p : c).orElse(null);
            if (checkPrice != null && stock.getPrices().get(0).getVolume() == checkPrice.getVolume()) {
                stock.setPrices(null);
            }

            // 로그
            System.out.println(String.format("%d/%d", stocks.indexOf(stock), stocks.size()));
        }

        stocks = stocks.stream()
        .filter(s -> {
            return s.getPrices() != null
            && s.getPrices().get(0).getHigh() > s.getPrices().get(1).getHigh()
            && s.getPrices().get(1).getHigh() > s.getPrices().get(2).getHigh()
            && s.getPrices().get(0).getLow() > s.getPrices().get(1).getLow()
            && s.getPrices().get(1).getLow() > s.getPrices().get(2).getLow();
        })
        .collect(Collectors.toList());

        for (Stock stock : stocks) {
            System.out.println(stock.getName());
            System.out.println("날짜: " + stock.getPrices().get(0).getDate());
            System.out.println("종가: " + stock.getPrices().get(0).getClose());
            System.out.println("고가: " + stock.getPrices().get(0).getHigh());
        }
    }
}
