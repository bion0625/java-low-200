package main.card;

public class CardMain2 {
    public static void main(String[] args){
        Card2 c1 = new Card2("H4"); // 생성자 (String아큐먼트)
        Card2 c2 = new Card2("H4"); // 생성자 (String아큐먼트)
        Card2 c3 = new Card2(c1); // 복사 생성자-값 동일
        System.out.println(c1.hashCode()); // 값은 동일, 다른 해시
        System.out.println(c2.hashCode()); // 값은 동일, 다른 해시
        System.out.println(c1.getCardVal()); // 값은 동일
        System.out.println(c2.getCardVal()); // 값은 동일
        System.out.println(c1.equals(c2)); // hashCode 비교-중요-false
        System.out.println(c1.equals(c3)); // hashCode 비교-중요-false
        for (int i = 0; i < CardUtil.SUIT.length; i++) { // SUIT 2
            for (int j = 0; j < CardUtil.VALU.length; j++) { // VALU 10
                // 값은 같을 수 있지만, 해시코드는 고유하다(같을 수 없다).
                Card2 c = new Card2(); // 임의로 카드 생성-기본 생성자()
                System.out.printf("%s\t", c); // c.toString()
            }
            System.out.println(); // 10개 출력 후 한 칸 아래
        }
    }
}
