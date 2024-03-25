package mySample.stock;

import java.util.List;

public class Start {
    public static void main(String[] args) {
        Ready ready = new Ready();
        List<StockModel> list = ready.getStockCode();

        // for (StockModel info : list) {
        //     System.out.println(info.getCode());
        // }
    }
}
