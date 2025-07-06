package kr.mumberrymountain.hwpxtemplater.render.style;

import kr.dogfoot.hwpxlib.object.content.header_xml.HeaderXMLFile;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.CharPr;
import kr.mumberrymountain.hwpxtemplater.model.Text;
import kr.mumberrymountain.hwpxtemplater.model.table.Cell;

import java.util.HashMap;
import java.util.Map;

public class StyleRenderer {
    private final HeaderXMLFile headerXMLFile;
    private final Map<String, CharPr> charPrs = new HashMap<String, CharPr>();
    public StyleRenderer (HeaderXMLFile headerXMLFile){
        this.headerXMLFile = headerXMLFile;
    }

    private String createStyleKey(Text text) {
        return String.join(";",
                    String.valueOf(text.getFontSize()),
                    text.getFontColor(),
                    text.getFontFamily(),
                    text.getBackgroundColor(),
                    String.valueOf(text.isBold()),
                    String.valueOf(text.isItalic()),
                    String.valueOf(text.isUnderLine()),
                    String.valueOf(text.isStrikeOut()),
                    String.valueOf(text.isOutline()),
                    String.valueOf(text.isShadow()),
                    String.valueOf(text.isEmboss()),
                    String.valueOf(text.isEngrave())
              );
    }

    public String renderTextStyleAndReturnCharPrId(Text text) {
        String key = createStyleKey(text);

        if (charPrs.containsKey(key)) return charPrs.get(key).id();

        String fontId = new FontRenderer(headerXMLFile, text.getFontFamily()).render();
        CharPr charPr = new CharPrRenderer(headerXMLFile, fontId, text).render();

        headerXMLFile.refList().charProperties().add(charPr);

        charPrs.put(key, charPr);

        return charPr.id();
    }

    public String renderBorderStyle(Cell cell) {
        return new BorderRenderer(headerXMLFile, cell).render();
    }
}
