package pl.zoltowskimarcin.petclinic.exception;

public class EntityDeletingFailedException extends EntityException {
    public EntityDeletingFailedException() {
    }

    public EntityDeletingFailedException(String message) {
        super(message);
    }

    public EntityDeletingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
