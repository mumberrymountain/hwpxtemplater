package kr.mumberrymountain.hwpxtemplater.render.value;

import kr.mumberrymountain.hwpxtemplater.interceptor.InterceptorType;
import kr.mumberrymountain.hwpxtemplater.interceptor.NullValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.render.HWPXRenderer;
import kr.mumberrymountain.hwpxtemplater.render.RendererUtil;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;

import java.util.Objects;

public class StringValueReplacementRenderer implements ValueReplacementRenderer {
    private String value;
    private final LinkedRunItem linkedRunItem;
    private final PlaceHolder placeHolder;
    private final HWPXRenderer rootRenderer;
    private final PlaceHolderRangeStack rangeStack;

    public StringValueReplacementRenderer(String value, LinkedRunItem linkedRunItem, PlaceHolder placeHolder, HWPXRenderer rootRenderer, PlaceHolderRangeStack rangeStack) {
        this.value = Objects.toString(value, null);
        this.linkedRunItem = linkedRunItem;
        this.placeHolder = placeHolder;
        this.rootRenderer = rootRenderer;
        this.rangeStack = rangeStack;
    }

    @Override
    public void render() {
        if(value == null) executeNullValueInterceptor();
        executeValueInterceptor();
        if (value == null) return;
        executeTrim();
        renderReplacement();
    }

    @Override
    public void executeNullValueInterceptor() {
        NullValueInterceptor nullValueInterceptor = (NullValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.NullValueInterceptor);
        if (nullValueInterceptor != null) value = nullValueInterceptor.intercept(value, placeHolder.data());
    }

    @Override
    public void executeValueInterceptor() {
        ValueInterceptor valueInterceptor = (ValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.ValueInterceptor);
        if (valueInterceptor != null) value = valueInterceptor.intercept(value, placeHolder.data());
    }

    @Override
    public void executeTrim(){
        if (RendererUtil.isAutoTrim(rootRenderer.config())) value = value.trim();
    }

    @Override
    public void renderReplacement(){
        placeHolder.t().clear();
        placeHolder.t().addText(value);

        if (RendererUtil.isCurrentRangeProcessing(rangeStack.current())) rangeStack.add(linkedRunItem.parent(), placeHolder);
    }
}
