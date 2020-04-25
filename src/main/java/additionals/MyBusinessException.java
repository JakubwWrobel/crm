package additionals;

public class MyBusinessException extends Exception {

    private String message;
    private Throwable cause;
    public MyBusinessException() {
        super();
    }

    public MyBusinessException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MyBusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyBusinessException(String message) {
        super(message);
    }

    public MyBusinessException(Throwable cause) {
        super(cause);
    }
}
