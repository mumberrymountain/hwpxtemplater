package kr.mumberrymountain.hwpxTemplater.render;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Picture;
import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxTemplater.model.Image;
import kr.mumberrymountain.hwpxTemplater.render.image.ImageInfo;
import kr.mumberrymountain.hwpxTemplater.render.image.ImageInfoFactory;
import kr.mumberrymountain.hwpxTemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxTemplater.render.placeholder.PlaceHolderRangeStack;

import static kr.mumberrymountain.hwpxTemplater.render.RendererUtil.isCurrentRangeLoop;
import static kr.mumberrymountain.hwpxTemplater.render.RendererUtil.isCurrentRangeProcessing;

public class ImageReplacementRenderer<H> implements SinglePlaceHolderRenderer {
    private PlaceHolderRangeStack rangeStack;
    private final HWPXRenderer rootRenderer;

    public ImageReplacementRenderer(PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        this.rangeStack = rangeStack;
        this.rootRenderer = rootRenderer;
    }

    private ImageInfo createImageInfo(Object value){
        Image image = null;
        if (value instanceof String) image = new Image(value.toString());
        else if (value instanceof Image) image = (Image) value;
        else return null;

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

        ImageInfo imageInfo = createImageInfo(value);
        if (imageInfo == null) return;

        renderPicture(linkedRunItem, placeHolder, imageInfo);

        if (isCurrentRangeProcessing(rangeStack.current())) rangeStack.add(linkedRunItem.parent(), placeHolder);
    }
}
