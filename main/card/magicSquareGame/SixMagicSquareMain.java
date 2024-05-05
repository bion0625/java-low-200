package main.card.magicSquareGame;

import java.util.Scanner;

public class SixMagicSquareMain {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        System.out.println("6,10,14 정수(4n+2)를 입력하세요.");
        int n = scann.nextInt();
        SixMagicSquare sms = new SixMagicSquare(n);
        sms.make();
        sms.print();
        scann.close();
    }
}
