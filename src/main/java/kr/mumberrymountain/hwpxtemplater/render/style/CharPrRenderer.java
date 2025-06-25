package kr.mumberrymountain.hwpxtemplater.render.style;

import kr.dogfoot.hwpxlib.object.content.header_xml.HeaderXMLFile;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.CharPr;
import kr.mumberrymountain.hwpxtemplater.util.HWPXUnitUtil;

public class CharPrRenderer {
    private final HeaderXMLFile headerXMLFile;
    private final int fontSize;
    private final String fontColor;
    private final String backgroundColor;
    private final String fontId;
    private final CharPr charPr;

    public CharPrRenderer(HeaderXMLFile headerXMLFile, int fontSize, String fontColor, String backgroundColor, String fontId) {
        this.headerXMLFile = headerXMLFile;
        this.fontSize = HWPXUnitUtil.pxToHwpxUnit(fontSize);
        this.fontColor = fontColor;
        this.backgroundColor = backgroundColor;
        this.fontId = fontId;
        this.charPr = new CharPr();
    }

    private void setId(){
        String charPrId = Integer.toString(headerXMLFile.refList().charProperties().count());
        charPr.id(charPrId);
    }

    private void setCharPr(){
        charPr.height(fontSize);
        charPr.textColor(fontColor);
        charPr.shadeColor(backgroundColor);
        charPr.useFontSpace(false);
        charPr.useKerning(false);
    }

    private void setFontRef(){
        charPr.createFontRef();
        charPr.fontRef().hangul(fontId);
        charPr.fontRef().latin(fontId);
        charPr.fontRef().hanja(fontId);
        charPr.fontRef().japanese(fontId);
        charPr.fontRef().other(fontId);
        charPr.fontRef().symbol(fontId);
        charPr.fontRef().user(fontId);
    }

    private void setRatio(){
        charPr.createRatio();
        charPr.ratio().hangul((short) 100);
        charPr.ratio().latin((short) 100);
        charPr.ratio().hanja((short) 100);
        charPr.ratio().japanese((short) 100);
        charPr.ratio().other((short) 100);
        charPr.ratio().symbol((short) 100);
        charPr.ratio().user((short) 100);
    }

    private void setSpacing(){
        charPr.createSpacing();
        charPr.spacing().hangul((short) 0);
        charPr.spacing().latin((short) 0);
        charPr.spacing().hanja((short) 0);
        charPr.spacing().japanese((short) 0);
        charPr.spacing().other((short) 0);
        charPr.spacing().symbol((short) 0);
        charPr.spacing().user((short) 0);
    }

    private void setRelSz(){
        charPr.createRelSz();
        charPr.relSz().hangul((short) 100);
        charPr.relSz().latin((short) 100);
        charPr.relSz().hanja((short) 100);
        charPr.relSz().japanese((short) 100);
        charPr.relSz().other((short) 100);
        charPr.relSz().symbol((short) 100);
        charPr.relSz().user((short) 100);
    }

    private void setOffset(){
        charPr.createOffset();
        charPr.offset().hangul((short) 0);
        charPr.offset().latin((short) 0);
        charPr.offset().hanja((short) 0);
        charPr.offset().japanese((short) 0);
        charPr.offset().other((short) 0);
        charPr.offset().symbol((short) 0);
        charPr.offset().user((short) 0);
    }

    public CharPr render(){
        setId();
        setCharPr();
        setFontRef();
        setRatio();
        setSpacing();
        setRelSz();
        setOffset();

        return charPr;
    }
}
