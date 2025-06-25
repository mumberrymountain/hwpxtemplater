package kr.mumberrymountain.hwpxtemplater.exception;

public class TemplateRenderingException extends RuntimeException{
    public TemplateRenderingException(String msg) {
        super(msg);
    }

    public TemplateRenderingException(String msg, Throwable e) {
        super(msg, e);
    }
}
