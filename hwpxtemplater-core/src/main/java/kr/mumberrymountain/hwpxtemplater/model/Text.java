package kr.mumberrymountain.hwpxtemplater.model;

public class Text {
    private String value;
    private String backgroundColor = "None";
    private String fontColor = "#000000";
    private String fontFamily = "함초롬바탕";
    private int fontSize = 13;
    private boolean bold = false;
    private boolean italic = false;
    private boolean underLine = false;
    private boolean strikeOut = false;
    private boolean outline = false;
    private boolean shadow = false;
    private boolean emboss = false;
    private boolean engrave = false;

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

    public Text bold(boolean bold){
        this.bold = bold;
        return this;
    }

    public Text italic(boolean italic){
        this.italic = italic;
        return this;
    }

    public Text underLine(boolean underLine){
        this.underLine = underLine;
        return this;
    }

    public Text strikeOut(boolean strikeOut){
        this.strikeOut = strikeOut;
        return this;
    }

    public Text outline(boolean outline){
        this.outline = outline;
        return this;
    }

    public Text shadow(boolean shadow){
        this.shadow = shadow;
        return this;
    }

    public Text emboss(boolean emboss){
        this.emboss = emboss;
        return this;
    }

    public Text engrave(boolean engrave){
        this.engrave = engrave;
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

    public boolean isBold(){
        return bold;
    }

    public boolean isItalic(){
        return italic;
    }

    public boolean isUnderLine(){
        return underLine;
    }

    public boolean isStrikeOut(){
        return strikeOut;
    }

    public boolean isOutline(){
        return outline;
    }

    public boolean isShadow(){
        return shadow;
    }

    public boolean isEmboss(){
        return emboss;
    }

    public boolean isEngrave(){
        return engrave;
    }

    public String getValue() { return value; }
    public void  setValue(String value) {
        this.value = value;
    }
}
