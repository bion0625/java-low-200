package main.card.sutdaGame;

import java.util.Date;
import java.util.Scanner;

import main.card.Card4;
import main.card.CardCase3;
import main.card.CardUtil;

public class Sutda {
    CardCase3 cc = new CardCase3(); // 카드를 저장
    CardRule rule = new CardRule(); // 2장의 카드를 받아서 계산
    Card4[] cc1 = new Card4[CardUtil.SUTDA];
    Card4[] cc2 = new Card4[CardUtil.SUTDA];

    public Sutda() {
        cc.make(); // 카드 만들기
    }
    // 너 둘 나 둘
    private void divide() {
        for (int i = 0; i < CardUtil.SUTDA; i++) {
            cc1[i] = cc.getCard(i); // 0, 1
            cc2[i] = cc.getCard(i + CardUtil.SUTDA); //  2, 3
        }
    }
    // 너 하나 나 하나
    public void divide2() {
        for (int i = 0, j = 0; i < cc1.length; i++, j+=2) {
            cc1[i] = cc.getCard(j); // 0, 2
            cc2[i] = cc.getCard(j + 1); // 1, 3
        }
    }
    public void play() {
        Scanner scan = new Scanner(System.in);
        System.out.println("!!WELCOME TO CARDGAME" + new Date() + "!!");
        System.out.println("Start !");
        String ss = "N";
        do {
            cc.shuffle(); // 카드 섞기
            // divide; // 2장씩 주기 너 둘 나 둘
            divide2(); // 2장씩 주기 너 하나 나 하나
            int v1 = rule.rule(cc1[0], cc1[1]); // 족보를 이용하여 값 구하기
            int v2 = rule.rule(cc2[0], cc2[1]); // 족보를 이용하여 값 구하기
            if (v1 > v2) {
                System.out.println("YOU WIN!");
            } else if (v1 < v2) {
                System.out.println("YOU LOSE!");
            } else {
                System.out.println("YOU SAME!");
            }
            // 카드 종류와 값을 출력
            cc.print(); // 테스트용-훔쳐보기
            System.out.println("YOU ");
            System.out.printf(cc1[0] + " " + cc1[1]);
            System.out.println(rule.rule(cc1[0], cc1[1]));
            System.out.println("COM ");
            System.out.printf(cc2[0] + " " + cc2[1]);
            System.out.println(rule.rule(cc2[0], cc2[1]));

            System.out.println("!!WELCOME TO CARDGAME " + new Date() + "!!");
            System.out.println("Do you want to replay again(Y/N)!!");
            ss = scan.next();
        } while((ss.toUpperCase()).equalsIgnoreCase("Y")); // 적어도 한 번 경기를 한다.
        System.out.println("Good Bye~~~~~");
        scan.close();
    }
}//
