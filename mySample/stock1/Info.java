package mySample.stock1;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Info {
    public static void main(String[] args) throws IOException {
        Info info = new Info();
        info.getCompanyInfo();;
    }

    public void getCompanyInfo() throws IOException {
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
}
