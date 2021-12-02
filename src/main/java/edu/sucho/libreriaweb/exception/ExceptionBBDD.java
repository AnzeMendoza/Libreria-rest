package edu.sucho.libreriaweb.exception;

public class ExceptionBBDD extends Exception {

    public ExceptionBBDD(String message) {
        super(message);
    }

    public ExceptionBBDD(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionBBDD(Throwable cause) {
        super(cause);
    }
}
