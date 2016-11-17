package core.jdbc;

/**
 * @author Kj Nam
 * @since 2016-11-16
 */
public class DataAccessException extends RuntimeException {
    public DataAccessException() {
        super();
    }

    protected DataAccessException(String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
