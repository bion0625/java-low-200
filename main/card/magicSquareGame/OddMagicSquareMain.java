package main.card.magicSquareGame;

import java.util.Scanner;

public class OddMagicSquareMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("홀수 정수를 입력하세요.");
        int n = in.nextInt();
        OddMagicSquare odd = new OddMagicSquare(n);
        odd.make();
        odd.print();
    }
}
