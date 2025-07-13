package kr.mumberrymountain.hwpxtemplater.render.style;

import kr.dogfoot.hwpxlib.object.content.header_xml.HeaderXMLFile;
import kr.dogfoot.hwpxlib.object.content.header_xml.enumtype.*;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.ParaPr;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.parapr.ParaMargin;
import kr.mumberrymountain.hwpxtemplater.model.table.Align;

public class ParaPrRenderer {
    private final HeaderXMLFile headerXMLFile;
    private final ParaPr paraPr;
    private HorizontalAlign2 horizontalAlign;

    public ParaPrRenderer(HeaderXMLFile headerXMLFile, Align align) {
        this.headerXMLFile = headerXMLFile;
        setHorizontalAlign(align);
        this.paraPr = new ParaPr();
    }

    private void setHorizontalAlign(Align align){
        switch (align) {
            case Left:
                horizontalAlign = HorizontalAlign2.LEFT;
                break;
            case Center:
                horizontalAlign = HorizontalAlign2.CENTER;
                break;
            case Right:
                horizontalAlign = HorizontalAlign2.RIGHT;
                break;
            default:
                horizontalAlign = HorizontalAlign2.CENTER;
        }
    }

    private void setId(){
        String paraPrId = Integer.toString(headerXMLFile.refList().paraProperties().count());
        paraPr.id(paraPrId);
    }

    private void setParaPr(){
        paraPr.tabPrIDRef("0");
        paraPr.condense((byte) 0);
        paraPr.fontLineHeight(false);
        paraPr.snapToGrid(true);
        paraPr.suppressLineNumbers(false);
        paraPr.checked(false);
    }

    private void setAlign(){
        paraPr.createAlign();
        paraPr.align().horizontal(horizontalAlign);
        paraPr.align().vertical(VerticalAlign1.BASELINE);
    }

    private void setHeading(){
        paraPr.createHeading();
        paraPr.heading().type(ParaHeadingType.NONE);
        paraPr.heading().idRef("0");
        paraPr.heading().level((byte) 0);
    }

    private void setBreakSetting(){
        paraPr.createBreakSetting();
        paraPr.breakSetting().breakLatinWord(LineBreakForLatin.KEEP_WORD);
        paraPr.breakSetting().breakNonLatinWord(LineBreakForNonLatin.BREAK_WORD);
        paraPr.breakSetting().widowOrphan(false);
        paraPr.breakSetting().keepWithNext(false);
        paraPr.breakSetting().keepLines(false);
        paraPr.breakSetting().pageBreakBefore(false);
        paraPr.breakSetting().lineWrap(LineWrap.BREAK);
    }

    private void setAutoSpacing(){
        paraPr.createAutoSpacing();
        paraPr.autoSpacing().eAsianEng(false);
        paraPr.autoSpacing().eAsianNum(false);
    }

    private void setMarginIntent(ParaMargin margin) {
        margin.createIntent();
        margin.intent().value(0);
        margin.intent().unit(ValueUnit2.HWPUNIT);
    }

    private void setMarginLeft(ParaMargin margin) {
        margin.createLeft();
        margin.left().value(0);
        margin.left().unit(ValueUnit2.HWPUNIT);
    }

    private void setMarginRight(ParaMargin margin) {
        margin.createRight();
        margin.right().value(0);
        margin.right().unit(ValueUnit2.HWPUNIT);
    }

    private void setMarginPrev(ParaMargin margin) {
        margin.createPrev();
        margin.prev().value(0);
        margin.prev().unit(ValueUnit2.HWPUNIT);
    }

    private void setMarginNext(ParaMargin margin) {
        margin.createNext();
        margin.next().value(0);
        margin.next().unit(ValueUnit2.HWPUNIT);
    }

    private void setMargin(){
        paraPr.createMargin();
        setMarginIntent(paraPr.margin());
        setMarginLeft(paraPr.margin());
        setMarginRight(paraPr.margin());
        setMarginPrev(paraPr.margin());
        setMarginNext(paraPr.margin());
    }

    private void setBorder(){
        paraPr.createBorder();
        paraPr.border().borderFillIDRef("2");
        paraPr.border().offsetLeft(0);
        paraPr.border().offsetRight(0);
        paraPr.border().offsetTop(0);
        paraPr.border().offsetBottom(0);
        paraPr.border().connect(false);
        paraPr.border().ignoreMargin(false);
    }

    public ParaPr render() {
        setId();
        setParaPr();
        setAlign();
        setHeading();
        setBreakSetting();
        setAutoSpacing();
        setMargin();
        setBorder();

        return paraPr;
    }
}
