package pl.zoltowskimarcin.petclinic.exception.doctor;

public class DoctorReadingFailedException extends DoctorException {
    public DoctorReadingFailedException() {
    }

    public DoctorReadingFailedException(String message) {
        super(message);
    }

    public DoctorReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
