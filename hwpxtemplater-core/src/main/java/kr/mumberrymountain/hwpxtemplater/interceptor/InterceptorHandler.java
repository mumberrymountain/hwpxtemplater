package kr.mumberrymountain.hwpxtemplater.interceptor;

import java.util.HashMap;

public class InterceptorHandler {
    private final HashMap<InterceptorType, Interceptor> interceptorMap = new HashMap<>();

    public InterceptorHandler register(Interceptor interceptor){
        switch (getType(interceptor)) {
            case ValueInterceptor:
                interceptorMap.put(InterceptorType.ValueInterceptor, interceptor);
                break;
            case NullValueInterceptor:
                interceptorMap.put(InterceptorType.NullValueInterceptor, interceptor);
                break;
        }

        return this;
    }

    public Interceptor get(InterceptorType interceptorType) {
        return interceptorMap.get(interceptorType);
    }

    private InterceptorType getType(Interceptor interceptor) {
        if (interceptor instanceof ValueInterceptor) {
            return InterceptorType.ValueInterceptor;
        } else if (interceptor instanceof  NullValueInterceptor) {
            return InterceptorType.NullValueInterceptor;
        } else {
            throw new IllegalArgumentException("Unknown interceptor type: " + interceptor.getClass());
        }
    }
}
