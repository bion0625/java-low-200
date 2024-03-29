package main.card.섯다;

import main.card.CardCase3;

public class CardRuleMain {
    public static void main(String[] args){
        CardCase3 cc = new CardCase3(); // 카드 저장
        cc.make(); // 카드 만들고
        CardRule rule = new CardRule(); // 족보

        for (int i = 0; i < 5; i++) {
            System.out.println("----------");
            cc.shuffle();  // 카드 섞기
            cc.print();
            int a = rule.rule(cc.getCard(0), cc.getCard(2));
            int b = rule.rule(cc.getCard(1), cc.getCard(3));
            System.out.println(cc.getCard(0) + " " + cc.getCard(2) + " " + a);
            System.out.println(cc.getCard(1) + " " + cc.getCard(3) + " " + b);
        }
        System.out.println("----------");
    }
}
