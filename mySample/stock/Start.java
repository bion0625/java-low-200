package mySample.stock;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        Ready ready = new Ready();
        List<StockModel> list = null;
        list = ready.getStockCode();
        list = ready.getNowPriceAndNewHighPrice(list);

        System.out.println(list);
    }
}
