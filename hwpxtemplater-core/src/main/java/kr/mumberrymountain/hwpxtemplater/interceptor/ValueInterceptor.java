package kr.mumberrymountain.hwpxtemplater.interceptor;

@FunctionalInterface
public interface ValueInterceptor extends Interceptor {
    public String intercept(String value, String field);
}
