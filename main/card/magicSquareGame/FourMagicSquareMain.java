package main.card.magicSquareGame;

import java.util.Scanner;

public class FourMagicSquareMain {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        System.out.println("4의 배수 정수를 입력하세요.");
        int n = scann.nextInt();
        FourMagicSquare fms = new FourMagicSquare(n);
        fms.make();
        fms.print();
        scann.close();
    }
}
