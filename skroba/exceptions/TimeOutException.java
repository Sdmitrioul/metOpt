package skroba.exceptions;

import java.io.IOException;

public class TimeOutException extends IOException {
    public TimeOutException(String message) {
        super(message);
    }
}
