package mySample.stock;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class PriceAndVolumCheck {
    public static void main(String[] args) {
        
        PriceAndVolumCheck check = new PriceAndVolumCheck();

        System.out.println("nowHighSize:" + check.nowHighSize("440640"));
    }

    public boolean threeDayPriceAndVolumCheck(String stockCode) {
        boolean threeDayHighChain = true;

        String url = "http://finance.naver.com/item/sise_day.nhn?code=" + stockCode;

        ArrayList<Double> highPrices = new ArrayList<>();
        ArrayList<Double> lowPrices = new ArrayList<>();
        ArrayList<Double> volums = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows= doc.select("table.type2 tr");

            for (int i = 3; i < 6; i++) { // 오늘 미포함 이전 3일의 데이터를 가져옴
                Element row = rows.get(i);
                Elements cols = row.select("td");

                String date = cols.get(0).text();
                double highPrice = Double.parseDouble(cols.get(4).text().replace(",", ""));
                double lowPrice = Double.parseDouble(cols.get(5).text().replace(",", ""));
                double volum = Double.parseDouble(cols.get(6).text().replace(",", ""));

                highPrices.add(highPrice);
                lowPrices.add(lowPrice);
                volums.add(volum);
            }

        } catch (Exception e) {
            threeDayHighChain = false;
        }

        if (highPrices.get(0) <= highPrices.get(1) || highPrices.get(1) <= highPrices.get(2) 
            || lowPrices.get(0) <= lowPrices.get(1) || lowPrices.get(1) <= lowPrices.get(2)) {
                threeDayHighChain = false;
        }

        // if (volums.get(0) >= volums.get(1) || volums.get(1) >= volums.get(2)) {
        //     threeDayHighChain = false;
        // }
        return threeDayHighChain;
    }

    public boolean tenDayVolumCheck(String stockCode) {
        boolean tenDayVolumCheck = true;

        String url = "http://finance.naver.com/item/sise_day.nhn?code=" + stockCode;

        ArrayList<Double> volums = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows= doc.select("table.type2 tr");

            for (int i = 2; i < 15; i++) { // 오늘 포함 이전 10일의 데이터(거래량)를 가져옴
                if (i == 7) { // html이 중간에 데코되어 있어서 뛰어넘기
                    i+=3;
                }
                Element row = rows.get(i);
                Elements cols = row.select("td");

                String date = cols.get(0).text();
                double volum = Double.parseDouble(cols.get(6).text().replace(",", ""));

                volums.add(volum);
            }

        } catch (Exception e) {
            e.printStackTrace();
            tenDayVolumCheck = false;
        }

        Double max = volums.stream().max((v1, v2) -> v1.compareTo(v2)).get();

        if (volums.get(0) == max) { // 오늘 포함 거래랴이 최근 10일 이내 최대인 날이 오늘이면 false
            tenDayVolumCheck = false;
        }

        return tenDayVolumCheck;
    }

    public boolean nowHighSize(String stockCode) {
        boolean isBetween5and15 = false;

        String url = "http://finance.naver.com/item/sise_day.nhn?code=" + stockCode;

        ArrayList<Double> closePrices = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements rows= doc.select("table.type2 tr");

            for (int i = 2; i < 4; i++) { // 오늘과 어제 종가 데이터 가져옴
                Element row = rows.get(i);
                Elements cols = row.select("td");

                String date = cols.get(0).text();
                double closePrice = Double.parseDouble(cols.get(1).text().replace(",", ""));

                closePrices.add(closePrice);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (closePrices.get(0) <= closePrices.get(1)) { // 오늘 종가(or 현재가)가 어제 종가보다 낮거나 같으면 false 리턴
        } else {
            double highSize = ((closePrices.get(0) - closePrices.get(1)) / closePrices.get(0)) * 100; // 백분율로 계산
            if (highSize >= 5 && highSize <= 15) { // 오늘 종가(or 현재가)가 어제보다 오른 정도가 5%이상 15% 이하일 때 true 리턴
                isBetween5and15 = true;
            }
        }

        return isBetween5and15;
    }
}
