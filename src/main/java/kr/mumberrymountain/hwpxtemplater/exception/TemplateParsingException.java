package kr.mumberrymountain.hwpxtemplater.exception;

public class TemplateParsingException extends RuntimeException{
    public TemplateParsingException(String msg) {
        super(msg);
    }

    public TemplateParsingException(String msg, Throwable e) {
        super(msg, e);
    }
}
