package pl.zoltowskimarcin.petclinic.exception.pet;

public class PetReadingFailedException extends PetException {
    public PetReadingFailedException() {
    }

    public PetReadingFailedException(String message) {
        super(message);
    }

    public PetReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
