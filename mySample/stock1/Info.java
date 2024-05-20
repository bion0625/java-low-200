package mySample.stock1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import mySample.stock1.model.Price;
import mySample.stock1.model.Stock;
import mySample.stock1.util.FormatUtil;

public class Info {
    public static void main(String[] args) throws IOException {
        Info info = new Info();
        System.out.println(info.getCompanyInfo());
        // TODO 마지막 페이지 오류: 추후 수정
        List<Price> prices = info.getPriceInfoByPage("439250", 1, 3);
        for (Price price : prices) {
            FormatUtil formatUtil = new FormatUtil();
            System.out.println(formatUtil.dateToString(price.getDate()));
        }
        info.getPriceTotalPage("439250");
    }

    public List<Stock> getCompanyInfo() { // 기본정보 가져오기
        List<Stock> stocks = new ArrayList<>();
        Document doc = null;
        try {
            doc = Jsoup.connect("http://kind.krx.co.kr/corpgeneral/corpList.do?method=download&searchType=13")
                                .userAgent("Mozilla/5.0")
                                .ignoreContentType(true)
                                .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Elements infoList = doc.select("tr");
        for (int i = 1; i < infoList.size(); i++) {
            Elements info = infoList.get(i).select("td");
            Stock stock = new Stock();
            stock.setName(info.get(0).text());
            stock.setCode(info.get(1).text());
            stocks.add(stock);
        }

        return stocks;
    }

    public List<Price> getPriceInfoByPage(String code, int from, int to) {
        List<Price> prices = new ArrayList<>();
        for (int page = from; page <= to; page++) {
            prices.addAll(getPriceInfo(code, page));
        }
        return prices;
    } 

    public List<Price> getPriceInfo(String code, int page) { // 종목 및 페이지로 가격 정보 가져오기
        Document doc = null;
        try {
            doc = Jsoup.connect(String.format("http://finance.naver.com/item/sise_day.nhn?code=%s&page=%d", code, page))
                                .userAgent("Mozilla/5.0")
                                .ignoreContentType(true)
                                .get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Elements infoList = doc.select("tr");

        List<Price> prices = new ArrayList<>();

        for (int i = 2; i < infoList.size() - 2; i++) {
            if (i >= 7 && i <= 9) {
                continue;
            }
            Price price = new Price();

            Elements info = infoList.get(i).select("td");
            FormatUtil formatUtil = new FormatUtil();
            price.setDate(formatUtil.stringToDate(info.get(0).text()));
            price.setClose(formatUtil.stringToLong(info.get(1).text()));
            price.setOpen(formatUtil.stringToLong(info.get(3).text()));
            price.setHigh(formatUtil.stringToLong(info.get(4).text()));
            price.setLow(formatUtil.stringToLong(info.get(5).text()));
            price.setVolume(formatUtil.stringToLong(info.get(6).text()));

            Boolean minusDiffFlag = info.get(2).text().contains("하한가") || info.get(2).text().contains("하락");
            price.setDiff(formatUtil.stringToLong(info.get(2).text()
                                    .replaceAll("상한가","")
                                    .replaceAll("하한가","")
                                    .replaceAll("상승","")
                                    .replaceAll("하락","")
                                    .replaceAll("보합","")
                                    .trim()));
            if (minusDiffFlag) {
                price.setDiff(price.getDiff() * -1);
            }

            prices.add(price);
        }

        return prices;
    }

    public int getPriceTotalPage(String code) throws IOException { // 가격 정보 가져올 때, 전체 페이지 가져오기
        Document doc = Jsoup.connect(String.format("http://finance.naver.com/item/sise_day.nhn?code=%s", code))
                            .userAgent("Mozilla/5.0")
                            .ignoreContentType(true)
                            .get();

        Element pgRR = doc.select(".pgRR a").get(0);
        String href = pgRR.attr("href");
        int totalPage = Integer.valueOf(href.substring(href.indexOf("&page=") + 6).trim());

        System.out.println(); // todo del
        System.out.println(totalPage); //  todo del
        System.out.println(); // todo del

        return totalPage;
    }
}
