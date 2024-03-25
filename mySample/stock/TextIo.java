package mySample.stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TextIo {
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
            String code = codeStr.split("/")[0];
            String isKospi = codeStr.split("/")[1];
            codeList.add(new StockModel(null, code, isKospi.equals("1")));
        }

        return codeList;
    }
}
