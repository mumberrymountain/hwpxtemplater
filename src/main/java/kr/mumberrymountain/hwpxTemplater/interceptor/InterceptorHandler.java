package kr.mumberrymountain.hwpxTemplater.interceptor;

import java.util.HashMap;

public class InterceptorHandler {
    private final HashMap<InterceptorType, Interceptor> interceptorMap = new HashMap<>();

    public InterceptorHandler register(Interceptor interceptor){
        switch (getType(interceptor)) {
            case ValueInterceptor:
                interceptorMap.put(InterceptorType.ValueInterceptor, interceptor);
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
        } else {
            throw new IllegalArgumentException("Unknown interceptor type: " + interceptor.getClass());
        }
    }
}
