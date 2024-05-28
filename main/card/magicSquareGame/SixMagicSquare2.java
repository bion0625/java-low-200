package main.card.magicSquareGame;

public class SixMagicSquare2 extends MagicSquare {
    
    public SixMagicSquare2(int n) {
        super(n);
    }

    public SixMagicSquare2() {
        this(6);
    }

    @Override
    public void make() {
        makeA();
        makeB();
        makeCD();
        makeMulti();
        makeOdd();
    }

    @Override
    public void print() {
        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(magic[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void makeA() {
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/4; j++) {
                if(i==n/4) {
                    magic[i][j+1] = 3;
                } else {
                    magic[i][j] = 3;
                }
            }
        }
    }
    private void makeB() {
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                magic[i][j+n/2] = 1;
            }
        }
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2-(n/4-1); j++) {
                magic[i][j+n/2] = 2;
            }
        }
    }
    private void makeCD() {
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                if(magic[i][j]==0) {
                    magic[i+n/2][j]=3;
                } else {
                    magic[i+n/2][j]=0;
                }

                if(magic[i][j+n/2]==1){
                    magic[i+n/2][j+n/2]=2;
                } else {
                    magic[i+n/2][j+n/2]=1;
                }
            }
        }
    }
    private void makeMulti() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                magic[i][j] *= (n/2*n/2);
            }
        }
    }
    private void makeOdd() {
        // Create dependency 생성 의존
        // 6 마방진은 3마방진이 필요하다.
        OddMagicSquare2 odd = new OddMagicSquare2(n/2);
        odd.make(); // 3 마방진을 만든다.
        int[][] mm = odd.getMagic();
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n/2; j++) {
                magic[i][j] = mm[i][j];
                magic[i][j+n/2] = mm[i][j];
                magic[i+n/2][j] = mm[i][j];
                magic[i+n/2][j+n/2] = mm[i][j];
            }
        }
    }
    
}
