package mySample.stock;

import java.io.File;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        Ready ready = new Ready();
        List<StockModel> list = null;

        
        File f = new File(ready.FILE_PATH);
        if(!f.exists()){
            list = ready.getStocksFromWeb();

            ready.saveStocks(list); // 저장
        }else{
            list = ready.getStocksFromText(); // 이미 저장되어 있으면 불러오기
        }

        System.out.println(list);
    }
}
