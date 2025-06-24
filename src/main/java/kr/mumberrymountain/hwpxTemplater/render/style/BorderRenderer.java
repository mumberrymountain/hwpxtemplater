package kr.mumberrymountain.hwpxTemplater.render.style;

import kr.dogfoot.hwpxlib.object.content.header_xml.HeaderXMLFile;
import kr.dogfoot.hwpxlib.object.content.header_xml.enumtype.LineType2;
import kr.dogfoot.hwpxlib.object.content.header_xml.enumtype.LineWidth;
import kr.dogfoot.hwpxlib.object.content.header_xml.enumtype.SlashType;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.BorderFill;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.borderfill.FillBrush;
import kr.mumberrymountain.hwpxTemplater.model.table.Cell;

public class BorderRenderer {
    private final HeaderXMLFile headerXMLFile;
    private final Cell cell;
    private final BorderFill borderFill;
    public BorderRenderer (HeaderXMLFile headerXMLFile, Cell cell){
        this.headerXMLFile = headerXMLFile;
        this.cell = cell;
        this.borderFill = new BorderFill();
    }

    private void setId(){
        String bfId = Integer.toString(headerXMLFile.refList().borderFills().count() + 1);
        borderFill.id(bfId);
    }

    private void setLeftBorder(LineType2 lineType, LineWidth lineWidth, String color){
        borderFill.createLeftBorder();
        borderFill.leftBorder().type(lineType);
        borderFill.leftBorder().width(lineWidth);
        borderFill.leftBorder().color(color);
    }

    private void setRightBorder(LineType2 lineType, LineWidth lineWidth, String color){
        borderFill.createRightBorder();
        borderFill.rightBorder().type(lineType);
        borderFill.rightBorder().width(lineWidth);
        borderFill.rightBorder().color(color);
    }

    private void setTopBorder(LineType2 lineType, LineWidth lineWidth, String color){
        borderFill.createTopBorder();
        borderFill.topBorder().type(lineType);
        borderFill.topBorder().width(lineWidth);
        borderFill.topBorder().color(color);
    }

    private void setBottomBorder(LineType2 lineType, LineWidth lineWidth, String color){
        borderFill.createBottomBorder();
        borderFill.bottomBorder().type(lineType);
        borderFill.bottomBorder().width(lineWidth);
        borderFill.bottomBorder().color(color);
    }

    private void setSlash(){
        borderFill.createSlash();
        borderFill.slash().Crooked(false);
        borderFill.slash().type(SlashType.NONE);
        borderFill.slash().isCounter(false);
    }

    private void setBorderAll(LineType2 lineType, LineWidth lineWidth, String color){
        setLeftBorder(lineType, lineWidth, color);
        setRightBorder(lineType, lineWidth, color);
        setTopBorder(lineType, lineWidth, color);
        setBottomBorder(lineType, lineWidth, color);
    }

    private void setBackSlash(){
        borderFill.createBackSlash();
        borderFill.backSlash().Crooked(false);
        borderFill.backSlash().type(SlashType.NONE);
        borderFill.backSlash().isCounter(false);
    }

    private void setFillBrush(String faceColor, String hatchColor){
        borderFill.createFillBrush();

        FillBrush fillBrush = borderFill.fillBrush();
        fillBrush.createWinBrush();
        fillBrush.winBrush().faceColor(faceColor); // 배경색상
        fillBrush.winBrush().hatchColor(hatchColor);
        fillBrush.winBrush().alpha((float) 0);
    }

    public String render(){
        setId();

        if (cell.isBorder()) {
            setBorderAll(LineType2.SOLID, LineWidth.MM_0_1, cell.getBorderColor());
        } else {
            setBorderAll(LineType2.NONE, LineWidth.MM_0_12, cell.getBorderColor());
            setSlash();
            setBackSlash();
        }

        setFillBrush(cell.getBackgroundColor(), "#000000");

        headerXMLFile.refList().borderFills().add(borderFill);

        return borderFill.id();
    }
}
