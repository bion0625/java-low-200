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
            if(html.contains("class=\"tltle\"")){
                String name = html.substring(html.indexOf("class=\"tltle\">") + 14, html.indexOf("</a></td>"));
                String code = html.substring(html.indexOf("?code=") + 6, html.indexOf("\" class=\"tltle\""));

                list.add(new StockModel(name, code, isKospi));
            }
        }


        return list;
    }

    public StockModel readNewHighPrice(String code){

        int newHighPrice = 0;
        int nowPrice = 0;

        ArrayList<String> htmls = new ArrayList<>();
        String newUrls = "https://finance.naver.com/item/main.naver?code=" + code;

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
        
        StockModel stockModel = new StockModel(code);

        for (int i = 0; i < htmls.size(); i++) {
            if(htmls.get(i).contains("전일대비 상승")){
                for (int j = i; j < htmls.size(); j++) {
                    if (htmls.get(j).contains("현재가")) {
                        try{
                            nowPrice = Integer.valueOf(
                                htmls.get(j)
                                    .substring("<dd>현재가".length(), htmls.get(j).indexOf("전일대비"))
                                    .replaceAll(",", "")
                                    .trim()
                                );
                        }catch(Exception e){
                            System.out.println("nowPrice error !!!, code: " + code);
                            System.out.println("html: " + htmls.get(j));
                            nowPrice = 0;
                        }
                        break;
                    }
                }
            }
            if(htmls.get(i).contains("<th scope=\"row\">52주최고<span class=\"bar\">l</span>최저")){
                for (int j = i; j < htmls.size(); j++) {
                    if(htmls.get(j).contains("<em>")){
                        try{
                            newHighPrice = Integer.valueOf(
                                htmls.get(j)
                                    .replace("<em>", "")
                                    .replace("</em>", "")
                                    .replace(",", "")
                                    );
                        }catch(Exception e){
                            System.out.println("newHighPrice error !!!, code: " + code);
                            System.out.println("html: " + htmls.get(j));
                            newHighPrice = 0;
                        }
                        break;
                    }
                }
            }
        }

        stockModel.setCode(code);
        stockModel.setNowPrice(nowPrice);
        stockModel.setNewHighPrice(newHighPrice);

        return stockModel;
    }

    public static void main(String[] args){
        // TEST CODE
        StockRead read = new StockRead();
        read.readJustNameByCode("003160");
    }

    public String readJustNameByCode(String code){
        String name = "";
        ArrayList<String> htmls = new ArrayList<>();
        String newUrls = "https://finance.naver.com/item/sise.naver?code=" + code;

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
            if(html.contains("class=\"wrap_company\"")){
                for (int i = htmls.indexOf(html); i < htmls.size(); i++) {
                    if (htmls.get(i).contains("<a")) {
                        name = htmls.get(i).substring(htmls.get(i).indexOf("\">") + "\">".length(), htmls.get(i).indexOf("</")).trim();
                        break;
                    }
                }
            }
        }
        return name;
    }
}
