package pl.zoltowskimarcin.petclinic.exception.pet;

public class PetDeletingFailedException extends PetException {
    public PetDeletingFailedException() {
    }

    public PetDeletingFailedException(String message) {
        super(message);
    }

    public PetDeletingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
