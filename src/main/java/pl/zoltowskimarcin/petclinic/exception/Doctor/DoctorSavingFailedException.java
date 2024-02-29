package pl.zoltowskimarcin.petclinic.exception.Doctor;

public class DoctorSavingFailedException extends DoctorException {
    public DoctorSavingFailedException() {
    }

    public DoctorSavingFailedException(String message) {
        super(message);
    }

    public DoctorSavingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

