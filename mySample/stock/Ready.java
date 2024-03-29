package mySample.stock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ready {
    public List<StockModel> getStockCode() {

        List<StockModel> list = new ArrayList<>();

        TextIo tio = new TextIo();
        String path = "mySample/stock/" + DayUtil.getTodayString();
        File f = new File(path);
        if(!f.exists()) { // 해당 날짜의 파일이 이미 있으면 미생성
            f.mkdirs();
            StockRead readStock = new StockRead();
            list = readStock.readNameAndCode(true);
            list.addAll(readStock.readNameAndCode(false));

            tio.saveCode(list, path);
        }else{
            list = tio.getCodeList(path);
        }
        return list;
    }

    public List<StockModel> getNowPriceAndNewHighPrice(List<StockModel> list){
        int count = 0;
        List<StockModel> priceList = new ArrayList<StockModel>();
        StockRead readStock = new StockRead();
        for (StockModel stockModel : list) {
            StockModel addModel = readStock.readNewHighPrice(stockModel.getCode());
            if (addModel.getNowPrice() != 0 && addModel.getNewHighPrice() != 0) {
                priceList.add(addModel);
            }
            count++;
            System.out.println("성공:" + priceList.size() + " 실패" + (count - priceList.size()) + "/" + list.size());
        }

        return list;
    }
}
