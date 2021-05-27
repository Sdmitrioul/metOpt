package skroba.lab1.methods.exceptions;

import java.io.IOException;

public class TimeOutException extends IOException {
    public TimeOutException(String message) {
        super(message);
    }
}
