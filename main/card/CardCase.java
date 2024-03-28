package main.card;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CardCase {
    // aggregation : CardCase는 Card로 구성된다.
    private List<Card3> cards = new ArrayList<>();
    public CardCase(){
        cards.clear(); // 내용 지우기
    }
    // List 반환
    public List<Card3> getCard3s(){
        return cards;
    }
    // List에 저장된 Card의 개수
    public int count(){
        return cards.size();
    }
    // List의 index번째 Card
    public Card3 getCard(int index){
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
            Card3 c = new Card3(); // 임의의 카드를 만든다.
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
            Card3 c = cards.get(i);
            System.out.printf("%s", c.toString());
            if ((i+1)%valu == 0) {
                System.out.println();
            }
        }
    }

    public void sort(){
        cards.sort(new CardComp());
    }

    public void rsort(){
        cards.sort(new CardRomp());
    }
}
