package pl.zoltowskimarcin.petclinic.exception.appointment;

public class AppointmentSavingFailedException extends AppointmentException {
    public AppointmentSavingFailedException() {
    }

    public AppointmentSavingFailedException(String message) {
        super(message);
    }

    public AppointmentSavingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

