package main.card.magicSquareGame;

public class MagicException extends Exception {
    public MagicException() {
        this("이런 마방진 생성 불가");
    }

    public MagicException(String message, Throwable cause) {
        super(message, cause);
    }

    public MagicException(String message) {
        super(message + "형태의 마방진 생성불가");
    }

    public MagicException(Throwable cause) {
        super(cause);
    }
}
