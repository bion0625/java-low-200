package mySample.stock;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Start {
    public static void main(String[] args) {

        Scanner scann = new Scanner(System.in);

        System.out.println("\nwrite app password");

        String password = scann.nextLine();

        if (password.trim() == "" || password == null) {
            System.out.println("need app password");
        }

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
            System.out.printf("get name count :\t%s\t%d\t/\t%d\n", stockModel.getCode(), list.indexOf(stockModel) + 1, list.size());
        }

        System.out.println("get name end !");

        System.out.println("check stock by name start !");

        list = list.stream().filter( // 증권 아닌 종목 필터
            l -> 
            !l.getName().contains("선물") 
            && !l.getName().contains("금리") 
            && !l.getName().contains("채권") 
            && !l.getName().contains("KODEX")
            && !l.getName().contains("레버리지")
            && !l.getName().contains("인버스")
            && !l.getName().contains("TIGER")
            && !l.getName().contains("회사채")
            && !l.getName().contains("금융채")
            && !l.getName().contains("KBSTAR")
        )
        .collect(Collectors.toList());

        System.out.println("check stock by name end !");

        PriceAndVolumCheck check = new PriceAndVolumCheck();

        System.out.println("check three day price & ten day volum & nowHighSize start !");
        

        final List<StockModel> countList = list;

        list = list.stream().filter(l -> {

            boolean isOk = 
            check.threeDayPriceAndVolumCheck(l.getSixCode()) // 오늘 미포함 최근 3일 연달아 상승(고가 저가 둘 다)
            && check.tenDayVolumCheck(l.getSixCode()) // 거래량이 최근 10일 중 오늘이 최대가 아니어야 함
            && check.nowHighSize(l.getSixCode()); // 어제 종가대비 오늘 종가(or 현재가) 상승률이 5%이상 15%미만이어야 함

            System.out.printf("check complete: %d/%d\n", countList.indexOf(l) + 1, countList.size());
            return isOk;
        })
        .collect(Collectors.toList()); 

        System.out.println("check three day price & ten day volum & nowHighSize end !");

        // 목록 구분
        System.out.println();
        List<StockModel> kospiList = list.stream()
            .filter(l -> l.getIsKospi())
            .collect(Collectors.toList());        

        System.out.println();
        List<StockModel> kosdaqList = list.stream()
            .filter(l -> !l.getIsKospi())
            .collect(Collectors.toList());

        String msg = "";

        // 목록 보여주기
        String kospiTitle = String.format("코스피\t%d개\n", kospiList.size());
        System.out.print(kospiTitle);
        msg += kospiTitle;
        for (StockModel stockModel : kospiList) {
            String content = String.format("%s\t%s\n", stockModel.getCode(), stockModel.getName());
            System.out.print(content);
            msg += content;
        }
        String kosdaqTitle = String.format("코스닥\t%d개\n", kosdaqList.size());
        System.out.print(kosdaqTitle);
        msg += kospiTitle;
        for (StockModel stockModel : kosdaqList) {
            String content = String.format("%s\t%s\n", stockModel.getCode(), stockModel.getName());
            System.out.print(content);
            msg += content;
        }

        SendMail sendMail = new SendMail();
        try {
            sendMail.sendMailByGoogle(
                "tempbion@gmail.com", 
                password, 
                "stxtory@gmail.com", 
                new Date() + " - 종목", 
                msg
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        scann.close();
    }
}
