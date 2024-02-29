package pl.zoltowskimarcin.petclinic.exception.client;

public class ClientSavingFailedException extends ClientException {
    public ClientSavingFailedException() {
    }

    public ClientSavingFailedException(String message) {
        super(message);
    }

    public ClientSavingFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

