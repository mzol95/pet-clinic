package pl.zoltowskimarcin.petclinic.exception.client;

public class EntitySavingFailedException extends EntityException {
    public EntitySavingFailedException() {
    }

    public EntitySavingFailedException(String message) {
        super(message);
    }

    public EntitySavingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

