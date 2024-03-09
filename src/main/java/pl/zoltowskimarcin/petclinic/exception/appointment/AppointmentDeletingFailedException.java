package pl.zoltowskimarcin.petclinic.exception.appointment;

public class AppointmentDeletingFailedException extends AppointmentException {
    public AppointmentDeletingFailedException() {
    }

    public AppointmentDeletingFailedException(String message) {
        super(message);
    }

    public AppointmentDeletingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
