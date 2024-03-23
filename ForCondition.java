public class ForCondition {
    public static void main(String[] args) {
        int s = 0;
        for (int i = 1; i <= 100; i++) {
            s += i;
        }
        System.out.println("1~100까지의 합: " + s);

        s = 0;
        for (int i = 1; i <= 100; i++) {
            s += i%2 == 1 ? i : 0;            
        }
        System.out.println("1~100 사이의 홀수의 합: " + s);
    }
}
