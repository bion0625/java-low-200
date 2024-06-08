package main.card.magicSquareGame;

import java.util.Scanner;

public class SquareMain3 {
    public static void main(String[] args) {
        System.out.println("3 이상의 정수 마방진을 입력하세요.");
        Scanner scann = new Scanner(System.in);
        int n = scann.nextInt();
        scann.close();
        try(FactoryMagic magicFactory = FactoryMagic.getInstance()) {
            IMagicSquare magic = magicFactory.getMagicSquare(n);
            // 3, 4, 6을 팩토리가 처리한다.
            MagicPrint2.print(magic); // 모든 마방진진을 출력한다.
        }catch(MagicException e) { // 사용자 정의 예외
            System.out.println(e);
        }catch(Exception e1){ // 범위를 벗어나면 자동 close()
        }
    }
}
