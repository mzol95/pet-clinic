package pl.zoltowskimarcin.petclinic.exception.client;

public class ClientUpdatingFailedException extends ClientException {
    public ClientUpdatingFailedException() {
    }

    public ClientUpdatingFailedException(String message) {
        super(message);
    }

    public ClientUpdatingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
