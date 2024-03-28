package main.billboard;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class BillboardMain3 {
    public static void main(String[] args) {
        String newUrls = "https://www.billboard.com/charts/hot-100/";
        URL url = null;
        try{
            url = new URL(newUrls); // 주소찾기
            // 주소지에 빨대 꽂기
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream(), "euc-kr"), 8);
                String line = null;
                while ((line = reader.readLine())!=null) { // 한 줄씩 읽어서
                    System.out.println(line.trim()); // 공백이 아니면 출력한다.
                }
        }catch(Exception e){
            System.out.println("Billboard Parsing error !!!");
        }
    }
}
