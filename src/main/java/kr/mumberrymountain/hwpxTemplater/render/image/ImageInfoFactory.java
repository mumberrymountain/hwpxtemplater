package kr.mumberrymountain.hwpxTemplater.render.image;

import kr.mumberrymountain.hwpxTemplater.model.Image;

public final class ImageInfoFactory {
    public static ImageInfo create(Image image) {
        String imagePath = image.getPath();
        ImageInfo imageInfo;
        if (imagePath.contains("base64,")) imageInfo = new Base64imageInfo(imagePath);
        else if (imagePath.startsWith("http")) imageInfo = new UrlImageInfo(imagePath);
        else imageInfo = new LocalImageInfo(imagePath);

        if (image.getWidth() > 0) imageInfo.setWidth(image.getWidth());
        if (image.getHeight() > 0) imageInfo.setHeight(image.getHeight());

        return imageInfo;
    }
}
