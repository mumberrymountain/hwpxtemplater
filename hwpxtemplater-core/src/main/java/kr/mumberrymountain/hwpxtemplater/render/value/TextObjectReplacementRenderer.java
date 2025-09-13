package kr.mumberrymountain.hwpxtemplater.render.value;

import kr.mumberrymountain.hwpxtemplater.interceptor.InterceptorType;
import kr.mumberrymountain.hwpxtemplater.interceptor.NullValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueStylingInterceptor;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.model.Text;
import kr.mumberrymountain.hwpxtemplater.render.HWPXRenderer;
import kr.mumberrymountain.hwpxtemplater.render.RendererUtil;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;

public class TextObjectReplacementRenderer implements ValueReplacementRenderer {
    private Text value;
    private final LinkedRunItem linkedRunItem;
    private final PlaceHolder placeHolder;
    private final HWPXRenderer rootRenderer;
    private final PlaceHolderRangeStack rangeStack;

    public TextObjectReplacementRenderer(Text value, LinkedRunItem linkedRunItem, PlaceHolder placeHolder, HWPXRenderer rootRenderer, PlaceHolderRangeStack rangeStack) {
        this.value = value;
        this.linkedRunItem = linkedRunItem;
        this.placeHolder = placeHolder;
        this.rootRenderer = rootRenderer;
        this.rangeStack = rangeStack;
    }

    @Override
    public void render() {
        if(value.getValue() == null) executeNullValueInterceptor();
        executeValueInterceptor();
        executeValueStylingInterceptor();
        if (value.getValue() == null) return;
        executeTrim();
        renderReplacement();
    }

    @Override
    public void executeNullValueInterceptor() {
        NullValueInterceptor nullValueInterceptor = (NullValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.NullValueInterceptor);
        if (nullValueInterceptor != null) value.setValue(nullValueInterceptor.intercept(value.getValue(), placeHolder.data()));
    }

    @Override
    public void executeValueInterceptor() {
        ValueInterceptor valueInterceptor = (ValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.ValueInterceptor);
        if (valueInterceptor != null) value.setValue(valueInterceptor.intercept(value.getValue(), placeHolder.data()));
    }

    public void executeValueStylingInterceptor() {
        ValueStylingInterceptor valueStylingInterceptor = (ValueStylingInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.ValueStylingInterceptor);
        if (valueStylingInterceptor != null) value = valueStylingInterceptor.intercept(value, placeHolder.data());
    }

    @Override
    public void executeTrim() {
        if (RendererUtil.isAutoTrim(rootRenderer.config())) value.setValue(value.getValue().trim());
    }

    @Override
    public void renderReplacement() {
        placeHolder.t().clear();
        placeHolder.t().addText(value.getValue());
        linkedRunItem.parent().data().charPrIDRef(rootRenderer.styleRenderer().renderCharStyleAndReturnCharPrId(value));

        if (RendererUtil.isCurrentRangeProcessing(rangeStack.current())) rangeStack.add(linkedRunItem.parent(), placeHolder);
    }
}
