package main.card;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CardCase3 {
    // aggregation : CardCase는 Card로 구성된다.
    private List<Card4> cards = new ArrayList<>();
    public CardCase3(){
        cards.clear(); // 내용 지우기
    }
    // List 반환
    public List<Card4> getCards(){
        return cards;
    }
    // List에 저장된 Card의 개수
    public int count(){
        return cards.size();
    }
    // List의 index번째 Card
    public Card4 getCard(int index){
        return cards.get(index); // index번째 Card
    }
    // 서로 다른 카드 20장 만들기
    public void make(){
        cards.clear(); // 내용 지우기
        int suit = CardUtil.SUIT.length; // 2
        int valu = CardUtil.VALU.length; // 10
        int count = 0;
        // 서로 다른 20개의 카드를 만든다
        while (count != suit * valu) { // 20장이 될 때까지
            Card4 c = new Card4(); // 임의의 카드를 만든다.
            if(!cards.contains(c)){ // contains -> equals()를 이용해 비교
                cards.add(c); // 같은 객체가 아니라면 저장
                count++;
            }
        }
    }

    public void shuffle(){
        Collections.shuffle(cards); // 카드 섞기
    }

    public void print(){
        int valu = CardUtil.VALU.length;
        for (int i = 0; i < cards.size(); i++) {
            Card4 c = cards.get(i);
            System.out.printf("%s", c.toString());
            if ((i+1)%valu == 0) {
                System.out.println();
            }
        }
    }
    // CardComp 클래스가 없다
    public void sort(){
        Comparator<Card4> cmp = new Comparator<Card4>() {
            @Override
            public int compare(Card4 c1, Card4 c2) {
                return c1.getCardVal().compareTo(c2.getCardVal());
            }
        };
        cards.sort(cmp);
    }
    // CardComp 클래스가 없다
    public void rsort(){
        // 익명 -anonymous
        cards.sort(new Comparator<Card4>() {
            public int compare(Card4 c1, Card4 c2) {
                return -c1.getCardVal().compareTo(c2.getCardVal());
            };
        });
    }
    // sort를 Lambda로 구현
    public void lambdasort(){
        cards.sort((c1, c2) -> {return c1.getCardVal().compareTo(c2.getCardVal());});
    }
    public void lambdasort2(){
        cards.sort(Card4::compareCard);
    }
    // rsort를 Lambda로 구현
    public void lambdarsort(){
        cards.sort((c1, c2) -> -c1.getCardVal().compareTo(c2.getCardVal()));
    }
    public void lambdarsort2(){
        cards.sort(Card4::compareRCard);
    }
}
