package pl.zoltowskimarcin.petclinic.exception.client;

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
