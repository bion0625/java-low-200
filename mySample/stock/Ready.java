package mySample.stock;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ready {
    public static String FILE_PATH = "mySample/stock/" + DayUtil.getTodayString();
    String path = FILE_PATH;

    public List<StockModel> getStocksFromWeb(){
        List<StockModel> list = getStockCode();
        return getNowPriceAndNewHighPrice(list);
    }

    private List<StockModel> getStockCode() {
        StockRead readStock = new StockRead();
        List<StockModel> list = new ArrayList<>();
        list = readStock.readNameAndCode(true);
        list.addAll(readStock.readNameAndCode(false));
        return list;
    }

    private List<StockModel> getNowPriceAndNewHighPrice(List<StockModel> list){
        int count = 0;
        List<StockModel> priceList = new ArrayList<StockModel>();
        StockRead readStock = new StockRead();
        for (StockModel stockModel : list) {
            StockModel addModel = readStock.readNewHighPrice(stockModel.getCode());
            if (addModel.getNowPrice() != 0 && addModel.getNewHighPrice() != 0) {
                priceList.add(addModel);
            }
            count++;
            if (count%10 == 0 || count == list.size()) {
                System.out.printf("SUCCESS/FALE\t%d/%d\t%d/%d\n", priceList.size(), + (count - priceList.size()), count, list.size());
            }
        }
        return list;
    }

    public void saveStocks(List<StockModel> list){
        TextIo tio = new TextIo();
        File f = new File(path);
        if(!f.exists()) { // 해당 날짜의 파일이 이미 있으면 미생성
            f.mkdirs();
            tio.saveCode(list, path);
        }
    }

    public List<StockModel> getStocksFromText(){
        TextIo tio = new TextIo();
        return tio.getCodeList(path);
    }
}
