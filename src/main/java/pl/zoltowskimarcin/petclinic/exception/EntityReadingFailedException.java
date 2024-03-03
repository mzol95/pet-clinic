package pl.zoltowskimarcin.petclinic.exception;

public class EntityReadingFailedException extends EntityException {
    public EntityReadingFailedException() {
    }

    public EntityReadingFailedException(String message) {
        super(message);
    }

    public EntityReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
