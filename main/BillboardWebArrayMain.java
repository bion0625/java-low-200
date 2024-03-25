package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class BillboardWebArrayMain {
    public static void main(String[] args) {
        ArrayList<String> htmls = new ArrayList<>();
        String newUrls = "https://www.billboard.com/charts/hot-100/";
        URL url = null;
        try{
            url = new URL(newUrls); // 주소찾기
            // 주소지에 빨대 꽂기
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream(), "euc-kr"), 8);
                String line = null;
                while ((line = reader.readLine())!=null) { // 한 줄씩 읽어서
                    if(!line.trim().equals("")){ // 공백이 아니면 출력한다.
                        htmls.add(line.trim());
                    }
                }
        }catch(Exception e){
            System.out.println("Billboard Parsing error !!!");
        }
        // ArrayList에 저장된 문자열을 출력
        for (String str : htmls) {
            System.out.println(str);
        }
    }
}
