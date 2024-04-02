package main.card.bakaraGame;

public class Bakara {
    CardPrint cp = new CardPrint();
    int whoWin = -1;
    public void start() {
        cp.make(); // 카드 만들기
        cp.shuffle(); // 카드 초기화
    }
    public void result() { // 누가 이겼는가?
        whoWin = -1;
        cp.print();
        System.out.println("결과: ");
        System.out.println("----------");
        System.out.println("플레이어: " + cp.returnPlayer());
        System.out.println("뱅커: " + cp.returnDealer());
        if (cp.returnPlayer() < cp.returnDealer()) {
            System.out.println("뱅커 승!");
            whoWin = 2;
        } else if (cp.returnPlayer() > cp.returnDealer()) {
            System.out.println("플레이어 승!");
            whoWin = 1;
        } else {
            System.out.println("타이!");
            whoWin = 3;
        }
    }
    public int getWhoWin() {
        return whoWin;
    }
}
