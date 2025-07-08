package kr.mumberrymountain.hwpxtemplater.render;

import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
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

        ValueReplacementRendererFactory.create(value, linkedRunItem, placeHolder, rootRenderer, rangeStack).render();
    }
}
