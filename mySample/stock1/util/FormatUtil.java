package mySample.stock1.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
    public static void main(String[] args) {
        FormatUtil formatUtil = new FormatUtil();
        System.out.println(formatUtil.dateToString(new Date()));
        System.out.println(formatUtil.stringToDate("2024.05.09"));
        System.out.println(formatUtil.stringToLong("30"));
    }

    public String dateToString(Date date) {
        String str = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            str = date == null ? null : sdf.format(date);
        } catch (Exception e) {
            System.out.println(String.format("dateToString : date >>>> %s", date));
        }
        return str;
    }

    public Date stringToDate(String str) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            date = str == "" ? null : sdf.parse(str);
        } catch (Exception e) {
            System.out.println(String.format("stringToDate : str >>>> %s", str));
        }
        return date;
    }

    public long stringToLong(String str) {
        long result = 0L;
        str = str == "" ? "0" : str;
        try {
            str = str.replaceAll(",", "");
            result = Long.parseLong(str);
        } catch (Exception e) {
            System.out.println(String.format("stringToLong : str >>>> %s", str));
        }
        return result;
    }
}
