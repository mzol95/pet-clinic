package pl.zoltowskimarcin.petclinic.exception.client;

public class ClientReadingFailedException extends ClientException {
    public ClientReadingFailedException() {
    }

    public ClientReadingFailedException(String message) {
        super(message);
    }

    public ClientReadingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
