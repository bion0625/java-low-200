package main.card;

public class CardCaseMain2 {
    public static void main(String[] args){
        CardCase2 cc = new CardCase2();
        cc.make(); // 서로 다른 카드 20장 만들기
        cc.print();
        System.out.println("----------");
        cc.shuffle(); // 카드 섞기
        cc.print();
        System.out.println("----------");
        cc.shuffle(); // 카드 섞기
        cc.print();
        System.out.println("----------");
        cc.sort();
        cc.print();
        System.out.println("----------");
        cc.rsort();
        cc.print();
        System.out.println("----------");
        cc.lambdasort();
        cc.print();
        System.out.println("----------");
        cc.lambdarsort();
        cc.print();
    }
}
