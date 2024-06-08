package main.card.magicSquareGame;

public abstract class MagicSquare2 implements IMagicSquare {
    protected int[][] magic; // 자식이 public처럼 사용
    protected int n; // 자식이 public처럼 사용

    public int[][] getMagic() {
        return magic;
    }

    // 반드시 int를 받아야 하는 생성자
    public MagicSquare2(int n) {
        magic = new int[n][n];
        this.n = n;
    }

}
