package main.card.bakaraGame;

import java.util.Scanner;

public class BakaraMain {
    public static void main(String[] args) {
        System.out.println("자산을 입력하시오");
        Scanner in = new Scanner(System.in);
        int sum = in.nextInt();
        int i = 0;
        Bakara bakara = new Bakara();
        while (true) {
            bakara.start();
            i++;
            System.out.println(i + "번째 대결입니다.");
            System.out.println("현재 당신의 자산은 총" + sum + "원입니다.");
            System.out.println("당신이 배팅할 금액을 설정하세요.");

            int n = in.nextInt();
            if (n > sum) {
                System.out.println("자신을 넘어설 수 없습니다. 다시 입력하세요.");
                i--;
            } else {
                sum = sum - n;
                System.out.println("당신은 " + n + "만큼의 금액을 배팅했습니다.");
                System.out.println("당신의 자산은 " + sum + "원입니다.");
                System.out.println("이 금액을 어디에 거시겠습니까?");
                System.out.println("1:Player");
                System.out.println("2:Dealer");
                System.out.println("3:Tie(무승부)");

                int nn = in.nextInt(); // 승부를 걸 쪽
                switch (nn) {
                    case 1:System.out.println("당신은 Player에 걸었습니다.")
                        ;break;
                    case 2:System.out.println("당신은 Dealer에 걸었습니다.")
                        ;break;
                    case 3:System.out.println("당신은 Tie(무승부)에 걸었습니다.")
                        ;break;
                }
                System.out.println();
                System.out.println("----------");
                System.out.println("-----<Game Start!>-----");
                System.out.println("----------");

                bakara.result();

                System.out.println("----------");
                System.out.println("-----<배팅 결과!>-----");
                System.out.println("----------");

                if (nn == bakara.getWhoWin()) {
                    System.out.println("당신이 이겼습니다!");
                    System.out.println("축하합니다!");
                    sum = sum + (n * 2);
                } else {
                    System.out.println("안됐군요.. 유감입니다.");
                }

                System.out.println("당신의 남은 자산은 " + sum + "원입니다!");
                System.out.println();
                System.out.println("----------");

                if (sum <= 0) {
                    System.out.println("자산이 부족합니다. 수고하셨습니다.");
                    in.close();
                    break;
                }
            }
        }
    }
}
