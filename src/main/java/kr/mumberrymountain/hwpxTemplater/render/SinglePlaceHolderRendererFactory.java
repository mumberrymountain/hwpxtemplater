package kr.mumberrymountain.hwpxTemplater.render;

import kr.mumberrymountain.hwpxTemplater.render.placeholder.PlaceHolderRangeStack;
import kr.mumberrymountain.hwpxTemplater.render.placeholder.PlaceHolderType;

public class SinglePlaceHolderRendererFactory<H>  {
    public static SinglePlaceHolderRendererFactory singlePlaceHolderRendererFactory;

    public static SinglePlaceHolderRendererFactory getInstance() {
        if (singlePlaceHolderRendererFactory == null) singlePlaceHolderRendererFactory = new SinglePlaceHolderRendererFactory();
        return singlePlaceHolderRendererFactory;
    }

    public SinglePlaceHolderRenderer create(PlaceHolderType type, PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        switch (type) {
            case REPLACEMENT:
                return new ReplacementRenderer(rangeStack, rootRenderer);
            case IMAGE_REPLACEMENT:
                return new ImageReplacementRenderer(rangeStack, rootRenderer);
            case TABLE_REPLACEMENT:
                return new TableReplacementRenderer(rangeStack, rootRenderer);
            default:
                throw new IllegalArgumentException("Unsupported renderer type: " + type);
        }
    }
}
