package pl.zoltowskimarcin.petclinic.exception;

public class EntityException extends Exception {
    public EntityException() {
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
