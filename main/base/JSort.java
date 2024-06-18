package main.base;

public class JSort {
    public static void BubbleSort(int[] n) {
        for (int i = 0; i < n.length-1; i++) {
            for (int j = 0; j < n.length-1-i; j++) {
                if (n[j]>n[j+1]) {
                    int temp=n[j];
                    n[j]=n[j+1];
                    n[j+1]=temp;
                }
            }
        }
    } // 버블 정렬
    public static void print(int[] n) {
        for (int i = 0; i < n.length; i++) {
            System.out.print(n[i]+" ");
        }
        System.out.println();
    }
}
