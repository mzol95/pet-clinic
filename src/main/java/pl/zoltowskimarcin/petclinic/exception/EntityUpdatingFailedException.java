package pl.zoltowskimarcin.petclinic.exception;

public class EntityUpdatingFailedException extends EntityException {
    public EntityUpdatingFailedException() {
    }

    public EntityUpdatingFailedException(String message) {
        super(message);
    }

    public EntityUpdatingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}