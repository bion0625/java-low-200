package mySample;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Stock {
    public static void main(String[] args) {
        System.out.println("This is my stock project by java");
        
        Document KOSPI;
        Document KOSDAK;
        try {
            KOSPI = Jsoup.connect("https://finance.naver.com/sise/sise_rise.naver").get();
            KOSDAK = Jsoup.connect("https://finance.naver.com/sise/sise_rise.naver?sosok=1").get();
        
            System.out.println(KOSPI.title());
            System.out.println(KOSDAK.title());

            Elements newsHeadlines = KOSPI.select("#mp-itn b a");
            for (Element headline : newsHeadlines) {
                System.out.println(String.format("%s\n\t%s", headline.attr("title"), headline.absUrl("href")));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
