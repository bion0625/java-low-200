package main.base;

public class StarPoint {
    public static void printStarDecrease(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k < 2 * (n - i); k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printStarDecrease(5);
    }
}
