package kr.mumberrymountain.hwpxtemplater.render;

import kr.mumberrymountain.hwpxtemplater.interceptor.InterceptorType;
import kr.mumberrymountain.hwpxtemplater.interceptor.NullValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.model.Text;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;
import java.util.Objects;

public class ReplacementRenderer<H> implements SinglePlaceHolderRenderer {

    private final PlaceHolderRangeStack rangeStack;
    private final HWPXRenderer rootRenderer;

    public ReplacementRenderer(PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        this.rangeStack = rangeStack;
        this.rootRenderer = rootRenderer;
    }

    private void renderReplacementString(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, Object value) {
        String val = Objects.toString(value, null);

        if(val == null) {
            NullValueInterceptor nullValueInterceptor = (NullValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.NullValueInterceptor);
            if (nullValueInterceptor != null) val = nullValueInterceptor.intercept(val, placeHolder.data());
        }

        ValueInterceptor valueInterceptor = (ValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.ValueInterceptor);
        if (valueInterceptor != null) val = valueInterceptor.intercept(val, placeHolder.data());

        if (val == null) return;

        placeHolder.t().clear();
        placeHolder.t().addText(val);

        if (RendererUtil.isCurrentRangeProcessing(rangeStack.current())) rangeStack.add(linkedRunItem.parent(), placeHolder);
    }

    private void renderReplacementTextObj(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, Object value) {
        Text text = (Text) value;

        if(text.getValue() == null) {
            NullValueInterceptor nullValueInterceptor = (NullValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.NullValueInterceptor);
            if (nullValueInterceptor != null) text.setValue(nullValueInterceptor.intercept(text.getValue(), placeHolder.data()));
        }

        ValueInterceptor valueInterceptor = (ValueInterceptor) rootRenderer.interceptorHandler().get(InterceptorType.ValueInterceptor);
        if (valueInterceptor != null) text.setValue(valueInterceptor.intercept(text.getValue(), placeHolder.data()));

        if (text.getValue() == null) return;

        placeHolder.t().clear();
        placeHolder.t().addText(text.getValue());
        linkedRunItem.parent().data().charPrIDRef(rootRenderer.styleRenderer().renderTextStyleAndReturnCharPrId(text));

        if (RendererUtil.isCurrentRangeProcessing(rangeStack.current())) rangeStack.add(linkedRunItem.parent(), placeHolder);
    }

    @Override
    public void renderReplacement(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, Object value){
        if(RendererUtil.isCurrentRangeLoop(rangeStack.current()) && RendererUtil.isCurrentRangeProcessing(rangeStack.current())) {
            rangeStack.add(linkedRunItem.parent(), placeHolder);
            return;
        }

        if (value instanceof Text) renderReplacementTextObj(linkedRunItem, placeHolder, value);
        else renderReplacementString(linkedRunItem, placeHolder, value);
    }
}
