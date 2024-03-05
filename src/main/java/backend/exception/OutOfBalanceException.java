package backend.exception;

public class OutOfBalanceException extends RuntimeException {
    public OutOfBalanceException(){};

    public OutOfBalanceException(String message){ super(message);

    }
}
