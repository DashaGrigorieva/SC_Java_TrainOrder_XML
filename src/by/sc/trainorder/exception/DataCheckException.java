package by.sc.trainorder.exception;

/**
 * Created by User on 02.06.2016.
 */
public class DataCheckException extends Exception {

    public DataCheckException(String message) {
        super(message);
    }

    public DataCheckException() {
    }

    public DataCheckException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataCheckException(Throwable cause) {
        super(cause);
    }
}
