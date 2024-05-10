package mySample.stock1.service;

import java.util.List;

import mySample.stock1.model.Stock;

public class GetStockAndSendService {
    TreeDayPriceService treeDayPriceService = new TreeDayPriceService();
    IOService ioService = new IOService();
    SendMailService sendMailService = new SendMailService();

    public void start() {
        String sendText = "";

        List<Stock> stocks = treeDayPriceService.start();
        for (Stock stock : stocks) {
            sendText += String.format("%s\n\t%s\n", stock.getName(), stock.getPrices().get(0).getDateString());
        }

        System.out.println(sendText); // todo del?

        String from = ioService.getByText("from");
        String to = ioService.getByText("to");
        String password = ioService.getByText("password");

        sendMailService.sendMailByGoogle(
            from, 
            to, 
            password, 
            stocks.get(0).getPrices().get(0).getDateString(), 
            sendText
        );
    }

    public static void main(String[] args) {
        GetStockAndSendService service = new GetStockAndSendService();
        service.start();
    }
}
