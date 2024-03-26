package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class RequestFromBillboardHot {
    ArrayList<String> htmls = new ArrayList<String>();
    boolean isConnection = false;

    public RequestFromBillboardHot(){
        htmls.clear();
    }

    public void getAllHtml(String newUrls) {
        htmls.clear();
        URL url = null;
        try{
            url = new URL(newUrls); // 주소 찾기
            // 주소지에 빨대 꽂기
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream(), "euc-kr"), 8);
                String line = "";
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().equals("")) {
                        htmls.add(line.trim());
                    }
                }
                isConnection = true;
        }catch(Exception e){
            isConnection = false;
            System.out.println("Billboard Parsing error !!!");
        }
    }

    public void printHtml() {
        for (String ss : htmls) {
            System.out.println(ss);
        }
    }
    
    public String getTimeDate(String newUrls) {
        String s = "";
        URL url = null;
        try{
            url = new URL(newUrls); // 주소 찾기
            //주소지에 빨대 꽂기
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream(), "euc-kr"), 8);
                String line = "";
                while ((line = reader.readLine()) != null) {
                    if(!line.trim().equals("")) {
                        // <time datetime="2018-08-13">August 13, 2018</time>
                        if (line.trim().contains("mega_menu_bb_mustread_opt_in_date")) { // 찾기
                            s=line.trim();
                            s = s.substring(0, s.indexOf("\" />")).trim(); // <time datetime="2018-08-13"
                            s = s.substring(s.indexOf("value=") + 7).trim(); // 2018-08-13
                            break;
                        }
                    }
                }
        }catch(Exception e){
            isConnection = false;
            System.out.println("Billboard Parsing error !!!");
        }
        return s;
    }

    public static void main(String[] args) {
        RequestFromBillboardHot rfw = new RequestFromBillboardHot();
        String a = "https://www.billboard.com/charts/hot-100/";
        String rs = rfw.getTimeDate(a);
        System.out.println("이번주 " + rs);
        /**
         * rs.RestDay.toWantedDay(rs,1); // 1주전: s062 RestDay
         * System.out.println("일주전 " + rs);
         * 
         * 이해할 수 없는 함수: 책 257page ????
         */
        rfw.getAllHtml(a + rs);
        rfw.printHtml();
    }
}
