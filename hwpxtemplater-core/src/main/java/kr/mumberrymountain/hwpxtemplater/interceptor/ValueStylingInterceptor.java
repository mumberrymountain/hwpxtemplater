package kr.mumberrymountain.hwpxtemplater.interceptor;

import kr.mumberrymountain.hwpxtemplater.model.Text;

public interface ValueStylingInterceptor extends Interceptor {
    public Text intercept(Text value, String field);
}
