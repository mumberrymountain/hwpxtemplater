package kr.mumberrymountain.hwpxTemplater.render.image;

import java.io.FileNotFoundException;

public interface ImageInfo {

    public String imagePath();
    public int width();
    public int height();
    public String fileName();
    public String id();
    public ImageType type();

    public byte[] imageByte();

    public void setWidth(int width);

    public void setHeight(int height);
}
