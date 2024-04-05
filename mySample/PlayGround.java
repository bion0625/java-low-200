package mySample;

public class PlayGround {
   public static void main(String[] args) {
      String code = "5930";
      String plusZero = "";

      if (code.length() < 6) {
         for (int i = 0; i < 6 - code.length(); i++) {
            plusZero = "0" + plusZero;
         }
      }
      System.out.println(plusZero + code);
   }
}
