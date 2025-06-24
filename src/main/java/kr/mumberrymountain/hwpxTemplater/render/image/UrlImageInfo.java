package kr.mumberrymountain.hwpxTemplater.render.image;

import kr.mumberrymountain.hwpxTemplater.util.ByteUtil;
import kr.mumberrymountain.hwpxTemplater.util.HWPXUnitUtil;

import java.awt.*;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.logging.Logger;

public class UrlImageInfo implements ImageInfo{
    private String imagePath;
    private int width;
    private int height;
    private String fileName;
    private String id;

    private ImageType type;
    private static final Logger logger = Logger.getLogger(Base64imageInfo.class.getName());
    public UrlImageInfo(String imagePath) {
        try {
            this.imagePath = imagePath;
            this.type = ImageType.getFileType(imagePath);
            this.fileName = new URL(imagePath).getPath().substring(imagePath.lastIndexOf('/') + 1);
            this.id = fileName.substring(0, fileName.lastIndexOf('.'));
            Dimension dimension = ByteUtil.getDimension(new URL(imagePath).openStream());
            this.width = HWPXUnitUtil.pxToHwpxUnit(dimension.width);
            this.height = HWPXUnitUtil.pxToHwpxUnit(dimension.height);
        } catch (FileNotFoundException e) {
            logger.warning("Image file not found: " + imagePath);
        } catch (Exception e) {
            logger.warning("Exception occurred while processing the image: " + e.getMessage());
        }
    }

    @Override
    public String imagePath() {
        return imagePath;
    }

    @Override
    public int width() {
        return width;
    }

    @Override
    public int height() {
        return height;
    }

    @Override
    public String fileName() {
        return fileName;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public ImageType type() {
        return type;
    }

    @Override
    public byte[] imageByte() {
        return ByteUtil.getUrlByteArray(imagePath);
    }

    @Override
    public void setWidth(int width) {
        this.width = HWPXUnitUtil.pxToHwpxUnit(width);
    }

    @Override
    public void setHeight(int height) {
        this.height = HWPXUnitUtil.pxToHwpxUnit(height);
    }
}
