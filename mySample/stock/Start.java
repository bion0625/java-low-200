package mySample.stock;

import java.io.File;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        StockRead readStock = new StockRead();
        List<StockModel> list = readStock.readNameAndCode(true);
        list.addAll(readStock.readNameAndCode(false));

        for (StockModel info : list) {
            System.out.println(info.getSavingText());
        }
    }
}
