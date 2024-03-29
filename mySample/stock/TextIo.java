package mySample.stock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TextIo {
    public static void main(String[] args){
        // TEST CODE
        List<StockModel> list = new ArrayList<>();
        StockModel model = new StockModel("test", "005930", true, 80800, 81000);
        list.add(model);
        String path = Ready.FILE_PATH;

        TextIo tio = new TextIo();
        File f = new File(path);
        if(!f.exists()) { // 해당 날짜의 파일이 이미 있으면 미생성
            f.mkdirs();
            tio.saveCode(list, path);
        }
    }

    public void saveCode(List<StockModel> list, String path) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(path + "/stockCode.txt", false), true)){
            for (int i = 0; i < list.size(); i++) {
                pw.print(list.get(i).getSavingText());
                if (i != list.size() - 1) {
                    pw.print(",");
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public List<StockModel> getCodeList(String path) {
        String codeString = "";
        try(BufferedReader br = new BufferedReader(new FileReader(path + "/stockCode.txt"))){
            codeString=br.readLine();
        }catch(Exception e){
            e.printStackTrace();
        }

        List<StockModel> codeList = new ArrayList<>();

        String[] codeStringList = codeString.split(",");
        for (String codeStr : codeStringList) {
            String[] str = codeStr.split("/");
            String code = str[0];
            String isKospi = str[1];
            int nowPrice = 0; 
            try{
                nowPrice = Integer.valueOf(str[2].replace("nowPrice:", ""));
            }catch(Exception e){
                nowPrice = 0;
                e.printStackTrace();
            }
            int newHighPrice = 0;
            try{
                newHighPrice = Integer.valueOf(str[3].replace("newHighPrice:", ""));
            }catch(Exception e){
                newHighPrice = 0;
                e.printStackTrace();
            }
            codeList.add(new StockModel(null, code, isKospi.equals("1"), nowPrice, newHighPrice));
        }

        return codeList;
    }
}
