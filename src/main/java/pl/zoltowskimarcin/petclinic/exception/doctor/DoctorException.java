package pl.zoltowskimarcin.petclinic.exception.doctor;

public class DoctorException extends Exception {
    public DoctorException() {
    }

    public DoctorException(String message) {
        super(message);
    }

    public DoctorException(String message, Throwable cause) {
        super(message, cause);
    }
}
