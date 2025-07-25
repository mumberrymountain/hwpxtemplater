package kr.mumberrymountain.hwpxtemplater.render.placeholder;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.mumberrymountain.hwpxtemplater.delim.DelimPos;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxtemplater.util.ParaUtil;

public class PlaceHolder {

    private final T t;
    private final LinkedRunItem runItem;
    private PlaceHolderType type;
    private final String data;

    private final DelimPos delimPos;
    private char typeChar;

    public PlaceHolder(T t, String delimStart, String delimEnd) {
        this(null, t, delimStart, delimEnd);
    }

    public PlaceHolder(LinkedRunItem runItem, T t, String delimStart, String delimEnd){
        this.runItem = runItem;
        this.t = t;
        String tText = ParaUtil.getTText(t);
        this.delimPos = new DelimPos(delimStart, delimEnd, tText.indexOf(delimStart), tText.indexOf(delimEnd));
        checkType(tText.charAt(delimStart.length()));
        this.data = this.type == PlaceHolderType.REPLACEMENT ? tText.substring(delimStart.length(), tText.length() - delimEnd.length()) :
                                                                tText.substring(delimStart.length() + 1, tText.length() - delimEnd.length());
    }

    private void checkType(char typeChar){
        this.typeChar = typeChar;
        for (PlaceHolderType placeholderType : PlaceHolderType.values()) {
            Character mappedChar = PlaceHolderCharRole.get(placeholderType);
            if (mappedChar != null && mappedChar == typeChar) {
                this.type = placeholderType;
                return;
            }
        }

        this.type = PlaceHolderType.REPLACEMENT;
    }

    public T t(){
        return t;
    }

    public DelimPos delimPos(){
        return delimPos;
    }

    public PlaceHolderType type(){
        return type;
    }

    public String data(){
        return data;
    }

    public LinkedRunItem linkedRunItem() {
        return runItem;
    }
}
