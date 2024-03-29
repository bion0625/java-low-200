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
            System.out.println("getStocksFromWeb start !!!");
            list = ready.getStocksFromWeb();
            System.out.println("getStocksFromWeb end !!!");

            ready.saveStocks(list); // 저장
        }else{
            list = ready.getStocksFromText(); // 이미 저장되어 있으면 불러오기
        }

        list = list.stream().filter(m -> m.getNowPrice()>=m.getNewHighPrice()).collect(Collectors.toList()); // 52주 신고가

        for (StockModel stockModel : list) {
            stockModel.setName(ready.getJustNameByCode(stockModel.getCode()));
        }

        // 52주 신고가 목록 보여주기
        List<StockModel> kospiList = list.stream().filter(l -> l.getIsKospi()).collect(Collectors.toList());
        System.out.printf("코스피\t%d개\n", kospiList.size());
        for (StockModel stockModel : kospiList) {
            System.out.printf("%s\t%s\n", stockModel.getCode(), stockModel.getName());
        }

        System.out.println();

        List<StockModel> kosdaqList = list.stream().filter(l -> !l.getIsKospi()).collect(Collectors.toList());
        System.out.printf("코스닥\t%d개\n", kosdaqList.size());
        for (StockModel stockModel : kosdaqList) { // 52주 신고가 목록 보여주기
            System.out.printf("%s\t%s\n", stockModel.getCode(), stockModel.getName());
        }
    }
}
