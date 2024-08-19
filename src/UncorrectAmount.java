public class UncorrectAmount extends Exception{
    public UncorrectAmount() {
        super();
    }

    public UncorrectAmount(String message) {
        super(message);
    }

    public UncorrectAmount(String message, Throwable cause) {
        super(message, cause);
    }

    public UncorrectAmount(Throwable cause) {
        super(cause);
    }
}
