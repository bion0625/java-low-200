package main.lotto;

import java.util.Arrays;

public class Lotto6Num {
    private int[] lots;
    private int base; // 45
    private int ballNum; // 6
    public Lotto6Num(int base, int ballNum) {
        this.base = base;
        this.ballNum = ballNum;
        // 배열 생성
        this.lots = new int[ballNum];
    }
    public Lotto6Num() {
        this(45,6);
    }
    public void print() {
        for (int i = 0; i < lots.length; i++) {
            if (i==lots.length-1) {
                System.out.printf("%d", lots[i]);
            } else System.out.printf("%d,", lots[i]);
        }
        System.out.println();
    }
    public int rand() {
        return (int)(Math.random()*base)+1;
    }
    public boolean contain(int n) {
        boolean isC=false;
        for (int i = 0; i < lots.length; i++) {
            if (lots[i]==n) {
                isC=true;
                break;
            }
        }
        return isC;
    }
    public void make() {
        Arrays.fill(lots, 0);
        int count=0;
        while (count!=ballNum) {
            int temp=rand(); // 1~45
            if (!contain(temp)) {
                lots[count++]=temp;
            }
        }
        Arrays.sort(lots);
    }
    public int[] getLots() {
        return lots;
    }
}
