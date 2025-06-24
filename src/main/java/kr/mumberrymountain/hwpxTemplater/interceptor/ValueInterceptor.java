package kr.mumberrymountain.hwpxTemplater.interceptor;

@FunctionalInterface
public interface ValueInterceptor extends Interceptor {
    public String intercept(String value, String field);
}
