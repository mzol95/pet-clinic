package pl.zoltowskimarcin.petclinic.exception.appointment;

public class AppointmentReadingFailedException extends AppointmentException {
    public AppointmentReadingFailedException() {
    }

    public AppointmentReadingFailedException(String message) {
        super(message);
    }

    public AppointmentReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
