package mySample.stock;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TextIo {
    public void saveCode(List<StockModel> list, String path) {
        try(PrintWriter pw = new PrintWriter(new FileWriter(path + "/stockCode.txt", false), true)){
            for (int i = 0; i < list.size(); i++) {
                pw.print(list.get(i).getCode());
                if (i != list.size() - 1) {
                    pw.print(",");
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
