package main.card.magicSquareGame;

import java.util.Scanner;

public class OddMagicSquareMain2 {
    public static void main(String[] args) {
        Scanner scann = new Scanner(System.in);
        System.out.println("홀수 정수를 입력하세요.");
        int n = scann.nextInt();
        OddMagicSquare2 odd = new OddMagicSquare2(n);
        odd.make();
        odd.print();
        scann.close();
    }
}
