package main.billboard;
public class BillboardMain {
    public static void main(String[] args) {
        int rank = 1; // 점수 저장
        String song = "Despacito"; // 문자열 저장
        // Data를 그릇에 담는다.
        int lastweek = 1;
        String imagesrc = "https://www.billboard.com/images/pref_images/q61808osztw.jpg";
        String artist = "luis fonsi";
        String st = String.format("%d, %s, %d, %s, %s", rank, song, lastweek, imagesrc, artist);
        System.out.println(st);
    }
}
