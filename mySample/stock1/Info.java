package mySample.stock1;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Info {
    public static void main(String[] args) throws IOException {
        Info info = new Info();
        info.getCompanyInfo();
        info.getPriceInfo("439250", 3);
        info.getPriceTotalPage("439250");
    }

    public void getCompanyInfo() throws IOException { // 기본정보 가져오기
        Document doc = Jsoup.connect("http://kind.krx.co.kr/corpgeneral/corpList.do?method=download&searchType=13")
                            .userAgent("Mozilla/5.0")
                            .ignoreContentType(true)
                            .get();
        Elements infoList = doc.select("tr");
        for (int i = 1; i < infoList.size(); i++) {
            Elements info = infoList.get(i).select("td");
            System.out.println(info.get(0).text()); // 종목명
            System.out.println(info.get(1).text()); // 종목 코드
        }
    }

    public void getPriceInfo(String code, int page) throws IOException { // 종목 및 페이지로 가격 정보 가져오기
        Document doc = Jsoup.connect(String.format("http://finance.naver.com/item/sise_day.nhn?code=%s&page=%d", code, page))
                            .userAgent("Mozilla/5.0")
                            .ignoreContentType(true)
                            .get();

        Elements infoList = doc.select("tr");

        for (int i = 2; i < infoList.size() - 2; i++) {
            if (i >= 7 && i <= 9) {
                continue;
            }
            Elements info = infoList.get(i).select("td");
            System.out.println(info.get(0).text()); // 날짜
            System.out.println(info.get(1).text()); // 종가
            System.out.println(info.get(2).text()
                .replaceAll("상승","")
                .replaceAll("하락","")
                .replaceAll("보합","")
                .trim()); // 전일비
            System.out.println(info.get(3).text()); // 시가
            System.out.println(info.get(4).text()); // 고가
            System.out.println(info.get(5).text()); // 저가
            System.out.println(info.get(6).text()); // 거래량
            System.out.println();
        }
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
