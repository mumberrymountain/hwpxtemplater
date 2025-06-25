package kr.mumberrymountain.hwpxtemplater.exception;

public class TemplateSyntaxException extends RuntimeException{
    public TemplateSyntaxException(String msg) {
        super(msg);
    }

    public TemplateSyntaxException(String msg, Throwable e) {
        super(msg, e);
    }
}
