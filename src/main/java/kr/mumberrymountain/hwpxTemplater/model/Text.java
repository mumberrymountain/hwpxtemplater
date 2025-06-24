package kr.mumberrymountain.hwpxTemplater.model;

public class Text {
    private String value;
    private String backgroundColor = "None";
    private String fontColor = "#000000";
    private String fontFamily = "함초롬바탕";
    private int fontSize = 13;

    public Text(String value) {
        this.value = value;
    }

    public Text backgroundColor(String backgroundColor){
        this.backgroundColor = backgroundColor;
        return this;
    }

    public Text fontColor(String fontColor) {
        this.fontColor = fontColor;
        return this;
    }

    public Text fontFamily(String fontFamily) {
        this.fontFamily = fontFamily;
        return this;
    }

    public Text fontSize(int fontSize) {
        this.fontSize = fontSize;
        return this;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public String getFontColor() {
        return fontColor;
    }

    public String getFontFamily() {
        return fontFamily;
    }

    public int getFontSize() {
        return fontSize;
    }

    public String getValue() { return value; }
    public void  setValue(String value) {
        this.value = value;
    }
}
