package mySample.stock1.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import mySample.stock1.service.GetStockAndSendService;
import mySample.stock1.service.SendMailService;

public class TimerController {
    String LOG_THIS_TEXT = "This time is %d";
    String LOG_NEXT_TEXT = "Next time is %d";

    GetStockAndSendService getStockAndSendService = new GetStockAndSendService();
    SendMailService sendMailService = new SendMailService();

    public void stockTimer() {
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                List<Integer> actTimes = new ArrayList<>(Arrays.asList(8,11,14,17));
                LocalDateTime now = LocalDateTime.now();
                if (actTimes.contains(now.getHour())) {
                    System.out.println(String.format(LOG_THIS_TEXT, now.getHour()));
                    // sendMailService.mainInfoSetting(); // 메일 세팅 정보 (처음에만 한 번)
                    getStockAndSendService.start();
                    if (actTimes.indexOf(now.getHour()) == actTimes.size() - 1) {
                        System.out.println(String.format(LOG_NEXT_TEXT, actTimes.get(0)));
                    } else System.out.println(String.format(LOG_NEXT_TEXT, actTimes.get(actTimes.indexOf(now.getHour()) + 1)));
                } else {
                    int nextTime = actTimes.get(0);
                    for (int i = actTimes.size() - 1; i >= 0; i--) {
                        nextTime = now.getHour() < actTimes.get(i) ? actTimes.get(i) : nextTime;
                    }
                    System.out.println(String.format(LOG_NEXT_TEXT, nextTime));
                }
            }
        };
        t.schedule(timerTask, 0, 3600000); // 1시간 마다 배치
    }

    public static void main(String[] args) {
        TimerController timerController = new TimerController();
        timerController.stockTimer();
    }
}
