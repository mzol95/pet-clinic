package pl.zoltowskimarcin.petclinic.exception.pet;

public class PetException extends Exception {
    public PetException() {
    }

    public PetException(String message) {
        super(message);
    }

    public PetException(String message, Throwable cause) {
        super(message, cause);
    }
}
