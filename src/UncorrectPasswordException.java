public class UncorrectPasswordException extends Exception{
    public UncorrectPasswordException() {
        super();
    }

    public UncorrectPasswordException(String message) {
        super(message);
    }

    public UncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public UncorrectPasswordException(Throwable cause) {
        super(cause);
    }
}
