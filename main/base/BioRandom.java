package main.base;
public class BioRandom {
    public static void main(String[] args) {
        int a = 0, b = 0, c = 0;
        while (true) {
            a = (int) (Math.random() * 1000);
            b = (int) (Math.random() * 1000);
            c = (int) (Math.random() * 1000);
            if(a != b && b != c && c != a 
            && a >= 100 && a < 1000 && b >= 100 && b < 1000 && c >= 100 && c < 1000)break;
        }
        System.out.printf("a: %s\nb: %s\nc: %s", a, b, c);
    }
}