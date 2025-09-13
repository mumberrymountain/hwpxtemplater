package kr.mumberrymountain.hwpxtemplater.render;

import kr.mumberrymountain.hwpxtemplater.interceptor.InterceptorType;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueStylingInterceptor;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.model.Text;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;
import kr.mumberrymountain.hwpxtemplater.render.value.ValueReplacementRendererFactory;

public class ReplacementRenderer<H> implements SinglePlaceHolderRenderer {

    private final PlaceHolderRangeStack rangeStack;
    private final HWPXRenderer rootRenderer;

    public ReplacementRenderer(PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        this.rangeStack = rangeStack;
        this.rootRenderer = rootRenderer;
    }

    @Override
    public void renderReplacement(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, Object value){
        if(RendererUtil.isCurrentRangeLoop(rangeStack.current()) && RendererUtil.isCurrentRangeProcessing(rangeStack.current())) {
            rangeStack.add(linkedRunItem.parent(), placeHolder);
            return;
        }

        // ValueStylingInterceptor를 사용하는 경우에는 value 값을 Text 객체로 변환 처리한다.
        ValueStylingInterceptor valueStylingInterceptor = (ValueStylingInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.ValueStylingInterceptor);
        if (valueStylingInterceptor != null && value != null && !(value instanceof Text)) value = new Text(String.valueOf(value));

        ValueReplacementRendererFactory.create(value, linkedRunItem, placeHolder, rootRenderer, rangeStack).render();
    }
}
