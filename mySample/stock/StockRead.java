package mySample.stock;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class StockRead {
    public ArrayList<StockModel> readNameAndCode(boolean isKospi) {
        ArrayList<String> htmls = new ArrayList<>();
        String newUrls = "https://finance.naver.com/sise/sise_rise.naver?" + (isKospi ? "" : "sosok=1");

        URL url = null;
        try{
            url = new URL(newUrls);

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(url.openStream(), "euc-kr"),
                8
            );
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().equals("")) {
                    htmls.add(line.trim());
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        ArrayList<StockModel> list = new ArrayList<>();
        for (String html : htmls) {
            // System.out.println(html);
            if(html.contains("class=\"tltle\"")){
                String name = html.substring(html.indexOf("class=\"tltle\">") + 14, html.indexOf("</a></td>"));
                String code = html.substring(html.indexOf("?code=") + 6, html.indexOf("\" class=\"tltle\""));

                list.add(new StockModel(name, code, isKospi));
            }
        }

        return list;
    }
}
