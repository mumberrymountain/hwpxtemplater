package kr.mumberrymountain.hwpxtemplater.render.value;

import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.model.Text;
import kr.mumberrymountain.hwpxtemplater.render.HWPXRenderer;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;

import java.util.Objects;

public class ValueReplacementRendererFactory {

    public static ValueReplacementRenderer create(Object value, LinkedRunItem linkedRunItem, PlaceHolder placeHolder, HWPXRenderer rootRenderer, PlaceHolderRangeStack rangeStack) {
        if (value instanceof Text) return new TextObjectReplacementRenderer((Text) value, linkedRunItem, placeHolder, rootRenderer, rangeStack);
        else return new StringValueReplacementRenderer(Objects.toString(value, null), linkedRunItem, placeHolder, rootRenderer, rangeStack);
    }
}
