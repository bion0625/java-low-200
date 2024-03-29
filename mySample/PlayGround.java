package mySample;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PlayGround {
   public static void main(String[] args) {
      String str = "<h2><a href=\"#\" onClick=\"clickcr(this, 'sop.title', '', '', event);window.location.reload();\">디아이</a></h2>";
      str = str.substring(str.indexOf("\">") + "\">".length(), str.indexOf("</")).trim();
      System.out.println(str);
   }
}
