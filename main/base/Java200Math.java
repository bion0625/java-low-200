package main.base;

public class Java200Math {
    public static void printDivide(int n) {
        if (n == 1) System.out.println("[1]");
        else {
            System.out.printf("[1,");
            for (int i = 2; i < n; i++) {
                if (n%i==0) System.out.printf("%d,", i);
            }
            System.out.println(n + "]");
        }
    }

    public static void printPrimeDevide(int n) {
        int a = 2;
        while (n!=1) {
            if (n%a == 0) {
                if (n/a == 1) System.out.println(a);
                else System.out.print(a + "x");
                n/=a;
            } else a++;
        }
    }

    public static void main(String[] args) {
        printDivide(194560);
        printPrimeDevide(194560);
    }
}
