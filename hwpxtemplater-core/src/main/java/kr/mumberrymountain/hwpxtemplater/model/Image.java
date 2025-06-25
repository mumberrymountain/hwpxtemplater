package kr.mumberrymountain.hwpxtemplater.model;

import kr.mumberrymountain.hwpxtemplater.exception.InvalidModelException;

public class Image {
    private final String path;
    private int width;
    private int height;
    public Image (String path) {
        this.path = path;
    }

    public Image width(int width){
        if (width <= 0) throw new InvalidModelException("width must be greater than 0");

        this.width = width;
        return this;
    }
    public Image height(int height) {
        if (height <= 0) throw new InvalidModelException("height must be greater than 0");

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
