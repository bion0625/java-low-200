package main.card.bakaraGame;

public class CardLaw {
    public boolean thirdCardPlayer(int x) {
        boolean isC = false;
        if(x < 6) { // 플레이어의 카드 두 장의 합이 0~5까지일 때
            isC = true;
        }
        return isC;
    }
    public boolean thirdCardDealer(int x, int y) { // 뱅커의 세 번째 카드를 받는 조건
        boolean isC = false;
        if(x < 3) { // 뱅커 카드 두 장의 합이 0, 1, 2일 때
            isC = true;
        } else if (x == 3 && y != 8){
            // 뱅커 카드 두 장의 합이 3이고 플레이어의 세 번째 카드가 8이 아닐 때
            isC = true;
        } else if (x == 4 && (y > 1 && y < 8)) {
            // 뱅커 카드 두 장의 합이 4이고 플레이어의 세 번째 카드가 2~7일 때
            isC = true;
        } else if (x == 5 && (y > 3 && y < 8)) {
            // 뱅커 카드 두 장의 합이 5이고 플레이어의 세 번째 카드가 4~7일 때
            isC = true;
        } else if (x == 6 && (y > 5 && y < 8)) {
            // 뱅커 카드 두 장의 합이 6이고 플레이어의 세 번째 카드가 6, 7일 때
            isC = true;
        }
        return isC;
    }
    public boolean stand(int x, int y) { // 스탠드가 되는 조건
        boolean isC = false;

        if (x > 5 && x < 8) { // 플레이어의 두 장의 카드합이 6, 7일 때
            if (x == y) { // 뱅커의 두 장의 카드합이 플레이어의 두 장의 카드합과 같을 때
                isC = true;
            }
            isC = true;
        } else if (y == 7) { // 뱅커의 두 장의 카드합이 7일 때
            isC = true;
        }
        return isC;
    }
    public boolean natural(int x, int y) { // 예외 처리가 되는 조건
        boolean isE = false;
        if (x > 7 && x < 10) {
            isE = true;
        } else if (y > 7 && y < 10) {
            isE = true;
        }
        return isE;
    }
}
