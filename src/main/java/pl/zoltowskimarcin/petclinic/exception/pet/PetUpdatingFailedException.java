package pl.zoltowskimarcin.petclinic.exception.pet;

public class PetUpdatingFailedException extends PetException {
    public PetUpdatingFailedException() {
    }

    public PetUpdatingFailedException(String message) {
        super(message);
    }

    public PetUpdatingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
