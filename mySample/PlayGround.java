package mySample;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PlayGround {
   public static void main(String[] args) {
      String str = "<dd>현재가 26,650 전일대비 상승 250 플러스 0.95 퍼센트</dd>";
      str = str.substring("<dd>현재가".length(), str.indexOf("전일대비")).replaceAll(",", "").trim();
      System.out.println(str);
   }
}
