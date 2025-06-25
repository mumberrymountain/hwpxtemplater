package kr.mumberrymountain.hwpxtemplater.exception;

public class InvalidConfigurationException extends RuntimeException {
    public InvalidConfigurationException(String msg) {
        super(msg);
    }

    public InvalidConfigurationException(String msg, Throwable e) {
        super(msg, e);
    }
}
