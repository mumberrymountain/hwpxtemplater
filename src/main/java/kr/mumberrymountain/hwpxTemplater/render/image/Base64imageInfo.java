package kr.mumberrymountain.hwpxTemplater.render.image;

import kr.mumberrymountain.hwpxTemplater.util.ByteUtil;
import kr.mumberrymountain.hwpxTemplater.util.HWPXUnitUtil;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;
import java.util.logging.Logger;

public class Base64imageInfo implements ImageInfo {

    private String imagePath;

    private int width;
    private int height;
    private String fileName;
    private String id;
    private ImageType type;
    private static final Logger logger = Logger.getLogger(Base64imageInfo.class.getName());
    public Base64imageInfo(String imagePath) {
        try{
            this.imagePath = imagePath;
            this.type = ImageType.getBase64FileType(imagePath);
            String randomId = generateRandomId();
            this.fileName = randomId + "." + type;
            this.id = randomId;
            Dimension dimension = ByteUtil.getDimension(new ByteArrayInputStream(ByteUtil.getBase64ByteArray(imagePath)));
            this.width = HWPXUnitUtil.pxToHwpxUnit(dimension.width);
            this.height = HWPXUnitUtil.pxToHwpxUnit(dimension.height);
        } catch (FileNotFoundException e) {
            logger.warning("Image file not found: " + imagePath);
        } catch (Exception e) {
            logger.warning("Exception occurred while processing the image: " + e.getMessage());
        }
    }

    private String generateRandomId() {
        String randomId = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
        return "image_" + randomId;
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
        return ByteUtil.getBase64ByteArray(imagePath);
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
