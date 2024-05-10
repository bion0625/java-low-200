package mySample.stock1.service;

import java.util.List;
import java.util.stream.Collectors;

import mySample.stock1.Info;
import mySample.stock1.model.Price;
import mySample.stock1.model.Stock;
import mySample.stock1.util.FormatUtil;

/*
 * 1) 3일 연달아 가격 상승이 아니면 제외
 * 2) 오늘이 거래량 최고면 제외
 * 3) 기준 날짜 대비 신고가 아니면 제외
 * 4) 당일 diff가 5% ~ 15% 내에 있지 않으면 제외
*/
public class TreeDayPriceService {
    public static void main(String[] args) {
        TreeDayPriceService service = new TreeDayPriceService();
        service.start();
    }

    public void start() {
        // 신고가 개월 수 (페이지 계산식 항)
        int MONTH = 3;
        /*
         * 13 페이지 6개월
         * 1페이지 10일 (2주)
         * 2페이지 1개월
         * 4페이지 2개월
         * 6페이지 3개월
         * ..
        */
        int SEARCH_PAGE = 2 * MONTH;

        Info info = new Info();
        List<Stock> stocks = info.getCompanyInfo();

        for (Stock stock : stocks) {
            // 로그
            System.out.println(String.format("%d\t/\t%d", stocks.indexOf(stock)+1, stocks.size()));

            // 부하를 방지하기 위해 일단 1페이지만 확인 후 신고가 설정할 때 다시 구하기
            List<Price> prices = info.getPriceInfo(stock.getCode(), 1);

            // 거래량이 0이면 하루 전으로 계산
            int lastdayIndex = prices.get(0).getVolume() == 0 ? 1 : 0;

            // 최근 3일 중 마지막일이 최고 거래량이면 제외            
            Price checkPrice = prices.subList(lastdayIndex, (lastdayIndex + 3)).stream().reduce((p, c) -> p.getVolume() > c.getVolume() ? p : c).orElse(null);
            if (checkPrice != null && prices.get(0).getVolume() == checkPrice.getVolume()) continue;

            // 마지막일 diff가 5% ~ 15% 내에 있지 않으면 제외
            double diffPercent = (double) (prices.get(lastdayIndex).getDiff() * 100) / (double) prices.get(lastdayIndex + 1).getClose();
            if (diffPercent < 5 || diffPercent > 15) continue;

             // 부하를 방지하기 위해 신고가 설정할 때 다시 구하기
            prices = info.getPriceInfoByPage(stock.getCode(), 1, SEARCH_PAGE);

            // 6개월 중 신고가가 아니면 제외
            checkPrice = prices.stream().reduce((p, c) -> p.getHigh() > c.getHigh() ? p : c).orElse(null);
            if(checkPrice == null || prices.get(0).getHigh() != checkPrice.getHigh()) continue;

            // 3일 연달아 가격 상승이 아니면 제외
            if(prices.get(lastdayIndex).getHigh() <= prices.get(lastdayIndex + 1).getHigh() || prices.get(lastdayIndex + 1).getHigh() <= prices.get(lastdayIndex + 2).getHigh()
            || prices.get(lastdayIndex).getLow() <= prices.get(lastdayIndex + 1).getLow() || prices.get(lastdayIndex + 1).getLow() <= prices.get(lastdayIndex + 2).getLow()) {
                continue;
            }

            // 리스트에 저장
            stock.setPrices(prices);

            // 로그
            System.out.println(String.format("\tsuccess:\t%s", stock.getName()));
        }

        // 가격 정보가 있는 객체만 남긴다.
        stocks = stocks.stream().filter(s -> s.getPrices() != null).collect(Collectors.toList());

        for (Stock stock : stocks) {
            FormatUtil formatUtil = new FormatUtil();
            System.out.println(String.format("%s\n\t%s", 
                stock.getName(), formatUtil.dateToString(stock.getPrices().get(0).getDate())));
        }                   
    }
}
