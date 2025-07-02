package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Picture;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.model.Image;
import kr.mumberrymountain.hwpxtemplater.render.image.ImageInfo;
import kr.mumberrymountain.hwpxtemplater.render.image.ImageInfoFactory;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;

import java.util.logging.Logger;

import static kr.mumberrymountain.hwpxtemplater.render.RendererUtil.isCurrentRangeLoop;
import static kr.mumberrymountain.hwpxtemplater.render.RendererUtil.isCurrentRangeProcessing;

public class ImageReplacementRenderer<H> implements SinglePlaceHolderRenderer {
    private PlaceHolderRangeStack rangeStack;
    private final HWPXRenderer rootRenderer;
    private static final Logger logger = Logger.getLogger(ImageReplacementRenderer.class.getName());

    public ImageReplacementRenderer(PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        this.rangeStack = rangeStack;
        this.rootRenderer = rootRenderer;
    }

    private ImageInfo createImageInfo(Object value, PlaceHolder placeHolder){
        Image image = null;
        if (value instanceof String) image = new Image(value.toString());
        else if (value instanceof Image) image = (Image) value;
        else {
            logger.warning(String.format("Value for image template field '%s' must be String or Image, not %s '%s'",
                    placeHolder.data(), value.getClass().getName(), value == null ? "null" : value.toString()));
            return null;
        }

        ImageInfo imageInfo = ImageInfoFactory.create(image);
        rootRenderer.imageManager().computeIfAbsent(imageInfo.fileName(), k -> imageInfo);
        return imageInfo;
    }

    private void renderPicture(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, ImageInfo imageInfo){
        int runItemIndex = linkedRunItem.parent().data().getRunItemIndex(linkedRunItem.data());
        linkedRunItem.parent().data().removeAllRunItems();
        Picture pic = new PictureRenderer(imageInfo).render();

        Run pRun = linkedRunItem.parent().data();
        pRun.insertRunItem(pic, runItemIndex);
        linkedRunItem.setData(pic);
        pRun.addNewT();
    }

    @Override
    public void renderReplacement(LinkedRunItem linkedRunItem, PlaceHolder placeHolder, Object value) {
        if(isCurrentRangeLoop(rangeStack.current()) && isCurrentRangeProcessing(rangeStack.current())) {
            rangeStack.add(linkedRunItem.parent(), placeHolder);
            return;
        }

        if (value == null) return;

        ImageInfo imageInfo = createImageInfo(value, placeHolder);
        if (imageInfo == null) return;

        renderPicture(linkedRunItem, placeHolder, imageInfo);

        if (isCurrentRangeProcessing(rangeStack.current())) rangeStack.add(linkedRunItem.parent(), placeHolder);
    }
}
