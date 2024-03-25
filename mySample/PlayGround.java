package mySample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayGround {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dayString = sdf.format(new Date());
        System.out.println(dayString);
    }
}
