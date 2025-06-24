package kr.mumberrymountain.hwpxTemplater.render.style;

import kr.dogfoot.hwpxlib.object.content.header_xml.HeaderXMLFile;
import kr.dogfoot.hwpxlib.object.content.header_xml.references.CharPr;
import kr.mumberrymountain.hwpxTemplater.model.Text;
import kr.mumberrymountain.hwpxTemplater.model.table.Cell;

import java.util.HashMap;
import java.util.Map;

public class StyleRenderer {
    private final HeaderXMLFile headerXMLFile;
    private final Map<String, CharPr> charPrs = new HashMap<String, CharPr>();
    public StyleRenderer (HeaderXMLFile headerXMLFile){
        this.headerXMLFile = headerXMLFile;
    }

    public String renderTextStyleAndReturnCharPrId(Text text) {
        String key = text.getFontSize() + ";" + text.getFontColor() + ";" + text.getFontFamily() + ";" + text.getBackgroundColor();

        if (charPrs.containsKey(key)) return charPrs.get(key).id();

        String fontId = new FontRenderer(headerXMLFile, text.getFontFamily()).render();
        CharPr charPr = new CharPrRenderer(headerXMLFile, text.getFontSize(),
                            text.getFontColor(), text.getBackgroundColor(), fontId).render();

        headerXMLFile.refList().charProperties().add(charPr);

        charPrs.put(key, charPr);

        return charPr.id();
    }

    public String renderBorderStyle(Cell cell) {
        return new BorderRenderer(headerXMLFile, cell).render();
    }
}
