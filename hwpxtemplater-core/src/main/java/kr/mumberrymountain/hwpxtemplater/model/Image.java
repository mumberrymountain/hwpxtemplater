package kr.mumberrymountain.hwpxtemplater.model;

import kr.mumberrymountain.hwpxtemplater.exception.InvalidModelException;

import java.util.logging.Logger;

public class Image {
    private final String path;
    private int width;
    private int height;
    private static final Logger logger = Logger.getLogger(Image.class.getName());
    public Image (String path) {
        this.path = path;
    }


    public Image width(int width){
        if (width <= 0) {
            logger.warning(String.format("Invalid width value: %d. Width must be greater than 0.", width));
            return this;
        }

        this.width = width;
        return this;
    }
    public Image height(int height) {
        if (height <= 0) {
            logger.warning(String.format("Invalid height value: %d. Height must be greater than 0.", height));
            return this;
        }

        this.height = height;
        return this;
    }

    public String getPath() { return path; }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }
}
