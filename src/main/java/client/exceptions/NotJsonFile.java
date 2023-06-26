package client.exceptions;

import java.io.IOException;

public class NotJsonFile extends IOException {
    public NotJsonFile(String message) {
        super(message);
    }
}
