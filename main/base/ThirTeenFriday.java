package main.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThirTeenFriday {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        int currentyear = cal.get(Calendar.YEAR); // 현재 연도
        int count = 0;
        for (int year = 2000; year <= currentyear; year++) { // 서기 2000년부터 현재까지
            for (int month = 0; month < 12; month++) {
                cal.set(year, month, 13); // 연도와 달변경
                if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) { // 금요일
                    System.out.println((++count)+"\t"+sdf.format(cal.getTime()));
                }
            }
        }
    }
}
