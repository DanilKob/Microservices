package home.project.authservice.exceptions;

public class UserRepositoryException extends Exception{
    private static final long serialVersionUID = -6714581485436265313L;

    public UserRepositoryException() {
    }

    public UserRepositoryException(String message) {
        super(message);
    }

    public UserRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRepositoryException(Throwable cause) {
        super(cause);
    }

    public UserRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
