package kr.mumberrymountain.hwpxTemplater.render;

import kr.mumberrymountain.hwpxTemplater.render.placeholder.PlaceHolderRangeStack;
import kr.mumberrymountain.hwpxTemplater.render.placeholder.PlaceHolderType;

public class PairedPlaceHolderRendererFactory<H> {
    public static PairedPlaceHolderRendererFactory pairedPlaceHolderRendererFactory;

    public static PairedPlaceHolderRendererFactory getInstance() {
        if (pairedPlaceHolderRendererFactory == null) pairedPlaceHolderRendererFactory = new PairedPlaceHolderRendererFactory();
        return pairedPlaceHolderRendererFactory;
    }

    public PairedPlaceHolderRenderer create(PlaceHolderType type, PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        switch (type) {
            case CONDITION:
                return new ConditionRenderer<>(rangeStack, rootRenderer);
            case LOOP:
                return new LoopRenderer<>(rangeStack, rootRenderer);
            default:
                throw new IllegalArgumentException("Unsupported renderer type: " + type);
        }
    }
}
