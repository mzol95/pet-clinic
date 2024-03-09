package pl.zoltowskimarcin.petclinic.exception.appointment;

public class AppointmentException extends Exception {
    public AppointmentException() {
    }

    public AppointmentException(String message) {
        super(message);
    }

    public AppointmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
