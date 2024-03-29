package mySample.stock;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

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

        list = list.stream().filter(m -> m.getNowPrice()>=m.getNewHighPrice()).collect(Collectors.toList()); // 52주 신고가

        for (StockModel stockModel : list) { // 52주 신고가 목록 보여주기
            String name = ready.getJustNameByCode(stockModel.getCode());
            stockModel.setName(name);
            System.out.printf("%s\t%s\n", stockModel.getCode(), stockModel.getName());
        }
    }
}
