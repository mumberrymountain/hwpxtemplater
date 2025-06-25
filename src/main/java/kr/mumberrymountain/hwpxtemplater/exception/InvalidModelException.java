package kr.mumberrymountain.hwpxtemplater.exception;

public class InvalidModelException extends RuntimeException {
    public InvalidModelException(String msg) {
        super(msg);
    }

    public InvalidModelException(String msg, Throwable e) {
        super(msg, e);
    }
}
