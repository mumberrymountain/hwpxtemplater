package kr.mumberrymountain.hwpxtemplater.render;

import kr.mumberrymountain.hwpxtemplater.exception.TemplateSyntaxException;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRange;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;
import kr.mumberrymountain.hwpxtemplater.util.Status;

public class PairedEdgePlaceHolderRenderer<H> {

    private PlaceHolderRange range;

    private final PlaceHolderRangeStack rangeStack;

    private final PairedPlaceHolderRendererFactory pairedPlaceHolderRendererFactory;

    private final HWPXRenderer rootRenderer;

    public PairedEdgePlaceHolderRenderer(PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        this.rangeStack = rangeStack;
        this.rootRenderer = rootRenderer;
        this.pairedPlaceHolderRendererFactory = PairedPlaceHolderRendererFactory.getInstance();
    }

    public void initPlaceHolderRange(LinkedRunItem linkedRunItem, PlaceHolder placeHolder){
        range = new PlaceHolderRange();
        range.add(linkedRunItem.parent(), placeHolder);
        range.setStatus(Status.PROCESSING);
        range.setPlaceHolderText(placeHolder.data());
        range.setType(placeHolder.type());

        rangeStack.push(range);
    }

    public void renderClosure(LinkedRunItem linkedRunItem, PlaceHolder placeHolder) {
        if (!placeHolder.data().equals(rangeStack.current().placeHolderText())) {
            throw new TemplateSyntaxException(String.format(
                    "Closing tag mismatch: Unexpected closing tag </%s>, Expected to close </%s> first",
                    rangeStack.current().placeHolderText(), placeHolder.data()
            ));
        }

        rangeStack.add(linkedRunItem.parent(), placeHolder);

        if(rangeStack.current().status() == Status.PROCESSING && rangeStack.current().placeHolderText() != null
                && rangeStack.current().placeHolderText().equals(placeHolder.data())) {
            rangeStack.current().setStatus(Status.FINISH);
            rangeStack.current().setPlaceHolderText(null);

            PairedPlaceHolderRenderer pairedPlaceHolderRenderer = pairedPlaceHolderRendererFactory.create(rangeStack.current().type(), rangeStack, rootRenderer);
            pairedPlaceHolderRenderer.flushRange(placeHolder);
        }

        rangeStack.pop();
    }
}
