package pl.zoltowskimarcin.petclinic.exception.appointment;

public class AppointmentUpdatingFailedException extends AppointmentException {
    public AppointmentUpdatingFailedException() {
    }

    public AppointmentUpdatingFailedException(String message) {
        super(message);
    }

    public AppointmentUpdatingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
