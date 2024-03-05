package backend.exception;

public class IncorrectInputException extends  RuntimeException{
    public IncorrectInputException(){};

    public IncorrectInputException(String message){ super(message);
    }
}
