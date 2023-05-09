package bstorm.be.demoservletjava23.exceptions;

public class InvalidPasswordUserException extends RuntimeException{
    public InvalidPasswordUserException() {
        super("Wrong password");
    }

    public InvalidPasswordUserException(String message) {
        super(message);
    }
}
