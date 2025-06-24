package kr.mumberrymountain.hwpxTemplater.render;

import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxTemplater.render.placeholder.PlaceHolder;

public interface SinglePlaceHolderRenderer {
    public void renderReplacement(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, Object value);
}
