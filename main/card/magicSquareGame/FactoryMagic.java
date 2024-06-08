package main.card.magicSquareGame;

public class FactoryMagic implements AutoCloseable {
    private static FactoryMagic ins;
    private FactoryMagic() {}
    
    public static FactoryMagic getInstance() {
        if (ins == null) {
            ins = new FactoryMagic();
        }
        return ins;
    }

    public IMagicSquare getMagicSquare(int n) throws MagicException {
        IMagicSquare im = null;
        if (n<=2) { // 2보다 작거나 같은 수가 들어오면 throw를 발생시켜서 Exception을 발생시킨다.
            throw new MagicException("2보다 작은 수의 마방진은 ");
        }
        if (n%2 != 0) {
            im = new OddMagicSquare4(n);
        } else if (n%4 == 0) {
            im = new FourMagicSquare3(n);
        } else im = new SixMagicSquare3(n);
        return im;
    }

    // try() 구문을 위한 close()
    @Override
    public void close() throws Exception {
        System.out.println("FactoryMagic End !!");
    }
}
