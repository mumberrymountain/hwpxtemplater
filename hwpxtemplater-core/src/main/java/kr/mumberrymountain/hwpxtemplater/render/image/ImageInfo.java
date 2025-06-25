package kr.mumberrymountain.hwpxtemplater.render.image;

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
