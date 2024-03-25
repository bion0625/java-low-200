package mySample.stock;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayUtil {
    public static String getTodayString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
