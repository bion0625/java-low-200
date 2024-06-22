package main.base;

public class Java200Math3 {
    public static void printAmicable(int t1, int t2) {
        for (int i = t1; i < t2; i++) {
            int a=i;
            int b=divide(a); // 약수의 합
            int c=divide(b); // 약수의 합
            // System.out.println(i);
            if (a<b && a==c) { // 같은 수 반복 금지
                System.out.printf("(%d,%d)는 친화수: ",a,b);
                printDivide(a); // 약수를 출력한다.
                printDivide(b); // 약수를 출력한다.
            }
        }
    }
    public static void main(String[] args) {
        printAmicable(2, 20000); // 두 수 사이의 친화수
        // printPerfect(2,20000); // 완전수
        // printSociable5(2,20000); // 5개 사이의 친화수
    }
    public static void printDivide(int n) {
        if (n==1) {
            System.out.println("[1]");
        } else {
            System.out.printf("[1,");
            for (int i = 2; i < n; i++) {
                if (n%i==0) {
                    System.out.printf("%d,",i);
                }
            }
            System.out.println(n+ "]");
        }
    }
    public static int divide(int num) {
        int tot=1;
        for (int i = 2; i < num; i++) {
            if (num%i==0) {
                tot+=i;
            }
        }
        return tot;
    }
}
