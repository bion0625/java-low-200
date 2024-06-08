package main.base;
// && 논리 연산자
public class Smith {
    public static void printSmith(int t1, int t2) {
        for (int i = t1; i < t2; i++) {
            if (!Java200Math2.isPrime(i) && 
                    Java200Math2.sumEach(i)==Java200Math2.sumSmith(i)) { // 스미스
                System.out.printf("%d는 스미스 수",i);
                Java200Math2.printPrimeDevide(i);
            }
        }
    }

    public static void main(String[] args) {
        // 10000~20000 사이의 스미스 수
        printSmith(10000, 20000);
    }
}
