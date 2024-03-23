public class PrimMath {
    // public static boolean isPrim(int number) {
        // boolean result = true;
        // for (int i = 2; i < number; i++) {
        //     if(number%i == 0) {
        //         System.out.printf("%s는 %s로 나누어떨어짐 (%s)\n", number, i, number/i);
        //         result = false;
        //     };
        // }
        // return result;

    public static boolean isPrim(int n) {
        boolean isS = true;
        for (int i = 2; i < (int) Math.sqrt(n); i++) {
            if(n%i == 0){
                isS = false;
                break;
            }
        }
        return isS;
    }

    public static void main(String[] args) {
        int number = 1234567;
        Boolean ifPrime = isPrim(number);
        System.out.println(String.format("%s는 ", number) + (ifPrime ? "소수입니다." : "소수가 아닙니다."));
    }
}
