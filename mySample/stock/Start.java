package mySample.stock;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class Start {
    public static void main(String[] args) {
        Ready ready = new Ready();
        List<StockModel> list = null;
        File f = new File(ready.FILE_PATH);

        System.out.println("get high price list start !");

        if(!f.exists()){
            System.out.println("getStocksFromWeb start !!!");
            list = ready.getStocksFromWeb();
            System.out.println("getStocksFromWeb end !!!");

            ready.saveStocks(list); // 저장
        }else{
            list = ready.getStocksFromText(); // 이미 저장되어 있으면 불러오기
        }

        System.out.println("get high price list end !");

        System.out.println("get high price in 52 list start !");

        list = list.stream().filter(m -> m.getNowPrice()>=m.getNewHighPrice()).collect(Collectors.toList()); // 52주 신고가

        System.out.println("get high price in 52 list end !");

        System.out.println("get name start !");

        for (StockModel stockModel : list) {
            stockModel.setName(ready.getJustNameByCode(stockModel.getCode()));
            System.out.printf("get name count :%s\t%d\t/\t%d\n", stockModel.getCode(), list.indexOf(stockModel), list.size());
        }

        System.out.println("get name end !");

        ThreeDayCheck check = new ThreeDayCheck();

        System.out.println("check three day price & ten day volum start !");
        
        // 목록 보여주기
        final List<StockModel> countList = list;
        System.out.println();
        List<StockModel> kospiList = list.stream()
            .filter(
                l -> {
                    System.out.printf("KOSPI: %d/%d\n", countList.indexOf(l), countList.size());
                    return 
                    l.getIsKospi() // 코스피
                    && check.threeDayPriceAndVolumCheck(l.getSixCode()) // 오늘 미포함 최근 3일 연달아 상승(고가 저가 둘 다)
                    && check.tenDayVolumCheck(l.getSixCode()); // 거래량이 최근 10일 중 오늘이 최대가 아니어야 함
                }
            )
            .collect(Collectors.toList());        

        System.out.println();
        List<StockModel> kosdaqList = list.stream()
            .filter(
                l -> {
                    System.out.printf("KOSDAQ: %d/%d\n", countList.indexOf(l), countList.size());
                    return 
                    !l.getIsKospi() // 코스닥
                    && check.threeDayPriceAndVolumCheck(l.getSixCode()) // 오늘 미포함 최근 3일 연달아 상승(고가 저가 둘 다)
                    && check.tenDayVolumCheck(l.getSixCode()); // 거래량이 최근 10일 중 오늘이 최대가 아니어야 함
                }
            )
            .collect(Collectors.toList());

        System.out.println("check three day price & ten day volum end !");

        System.out.printf("코스피\t%d개\n", kospiList.size());
        for (StockModel stockModel : kospiList) {
            System.out.printf("%s\t%s\n", stockModel.getCode(), stockModel.getName());
        }
        System.out.printf("코스닥\t%d개\n", kosdaqList.size());
        for (StockModel stockModel : kosdaqList) {
            System.out.printf("%s\t%s\n", stockModel.getCode(), stockModel.getName());
        }
    }
}
