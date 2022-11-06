package com.diffblue.interview.exception;

public class NoFileFoundException extends RuntimeException{
    public NoFileFoundException(String message) {
        super(message);
    }

    public NoFileFoundException(Throwable cause) {
        super(cause);
    }
}
