package pl.zoltowskimarcin.petclinic.exception.Doctor;

public class DoctorUpdatingFailedException extends DoctorException {
    public DoctorUpdatingFailedException() {
    }

    public DoctorUpdatingFailedException(String message) {
        super(message);
    }

    public DoctorUpdatingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
