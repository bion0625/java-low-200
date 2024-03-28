package main.card;

public class CardMain3 {
    public static void main(String[] args){
        Card3 c1 = new Card3("H4"); // 생성자(String 아규먼트)
        Card3 c2 = new Card3("H4"); // 생성자(String 아규먼트)
        System.out.println(c1.hashCode()); // 값은 동일 같은 해시
        System.out.println(c2.hashCode()); // 값은 동일 같은 해시
        System.out.println(c1.getCardVal()); // 값은 동일
        System.out.println(c2.getCardVal()); // 값은 동일
        System.out.println(c1.equals(c2)); // hashCode 비교 - 오버라이딩 - 중요
        // 같은 값을 가질 때 같은 객체로 정의하고 싶다면
        // hashCode().equals()를 오버라이딩하자.
    }
}
