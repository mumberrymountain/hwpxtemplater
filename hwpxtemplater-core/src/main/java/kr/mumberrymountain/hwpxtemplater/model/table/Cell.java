package kr.mumberrymountain.hwpxtemplater.model.table;

import kr.mumberrymountain.hwpxtemplater.model.Text;

public class Cell {
    private final Text text;
    private String backgroundColor = "None";
    private boolean border = true;
    private String borderColor = "#000000";
    private Align align;

    public Cell(String value) {
        text = new Text(value);
    }

    public Text getText(){
        return text;
    }

    public Cell backgroundColor(String backgroundColor){
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Cell fontColor(String fontColor) {
        text.fontColor(fontColor);
        return this;
    }

    public Cell fontFamily(String fontFamily) {
        text.fontFamily(fontFamily);
        return this;
    }

    public Cell fontSize(int fontSize) {
        text.fontSize(fontSize);
        return this;
    }

    public Cell align(Align align){
        this.align = align;
        return this;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getFontColor() {
        return text.getFontColor();
    }

    public String getFontFamily() {
        return text.getFontFamily();
    }

    public int getFontSize() {
        return text.getFontSize();
    }

    public boolean isBorder(){
        return border;
    }

    public String getBorderColor(){
        return borderColor;
    }

    public Align getAlign(){
        return align;
    }
}
