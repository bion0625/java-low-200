package mySample;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayGround {
    public static void main(String[] args) {
        String s = "<input type=\"hidden\" name=\"BB_MustRead_Opt_In_Date\" id=\"mega_menu_bb_mustread_opt_in_date\" value=\"2024-03-26\" />";
        s = s.substring(0, s.indexOf("\" />"));
        s = s.substring(s.indexOf("value=") + 7);

        System.out.println(s);
    }
}
