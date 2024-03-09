package pl.zoltowskimarcin.petclinic.exception.client;

public class ClientDeletingFailedException extends ClientException {
    public ClientDeletingFailedException() {
    }

    public ClientDeletingFailedException(String message) {
        super(message);
    }

    public ClientDeletingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
