package kr.mumberrymountain.hwpxtemplater.render.style;

import kr.dogfoot.hwpxlib.object.content.header_xml.HeaderXMLFile;
import kr.dogfoot.hwpxlib.object.content.header_xml.enumtype.FontFamilyType;
import kr.dogfoot.hwpxlib.object.content.header_xml.enumtype.FontType;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.Fontface;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.fontface.Font;

public class FontRenderer {
    private final HeaderXMLFile headerXMLFile;
    private final String fontFamily;
    private final Fontface fontFace;

    public FontRenderer (HeaderXMLFile headerXMLFile, String fontFamily) {
        this.headerXMLFile = headerXMLFile;
        this.fontFamily = fontFamily;
        this.fontFace = headerXMLFile.refList().fontfaces().hangulFontface();
    }

    private String checkExistingFonts(Fontface fontface) {
        String fontId = null;
        for (Font font : fontface.fonts()) {
            if (font.face().equals(fontFamily)) fontId = font.id();
        }
        return fontId;
    }

    private String addNewFont() {
        String fontId = Integer.toString(fontFace.countOfFont());
        Font font = fontFace.addNewFont();
        font.idAnd(fontId).typeAnd(FontType.TTF).isEmbeddedAnd(false).face(fontFamily);
        font.createTypeInfo();
        font.typeInfo().familyTypeAnd(FontFamilyType.FCAT_UNKNOWN).weightAnd(0).proportionAnd(0).contrastAnd(0)
                .strokeVariationAnd(0).armStyleAnd(false).letterformAnd(false).midlineAnd(252).xHeightAnd(255);
        return fontId;
    }

    public String render(){
        String fontId = checkExistingFonts(fontFace);
        if (fontId == null) fontId = addNewFont();
        return fontId;
    }
}
