package pl.zoltowskimarcin.petclinic.exception.pet;

public class PetSavingFailedException extends PetException {
    public PetSavingFailedException() {
    }

    public PetSavingFailedException(String message) {
        super(message);
    }

    public PetSavingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

