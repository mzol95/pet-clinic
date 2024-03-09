package pl.zoltowskimarcin.petclinic.exception.doctor;

public class DoctorDeletingFailedException extends DoctorException {
    public DoctorDeletingFailedException() {
    }

    public DoctorDeletingFailedException(String message) {
        super(message);
    }

    public DoctorDeletingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
