package kr.mumberrymountain.hwpxtemplater.render;

import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;

public interface SinglePlaceHolderRenderer {
    public void renderReplacement(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, Object value);
}
