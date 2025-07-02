package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.common.HWPXObject;
import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.comm.ObjectFilter;
import kr.mumberrymountain.hwpxtemplater.exception.TemplateSyntaxException;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.util.Status;
import kr.mumberrymountain.hwpxtemplater.util.ParaUtil;

import java.util.ArrayList;

public class PlaceHolderFilter implements ObjectFilter {

    private final  String delimStart;
    private final  String delimEnd;

    private Status status = Status.WAITING;

    public PlaceHolderFilter(String delimStart, String delimEnd) {
        this.delimStart = delimStart;
        this.delimEnd = delimEnd;
    }

    private String placeHolderText;

    private void processingCondition(Status status, String data) {
        this.status = status;
        this.placeHolderText = data;
    }

    public boolean filterT(T text){
        if (status == Status.PROCESSING) return true;
        if (!ParaUtil.containsDelim(text, delimStart, delimEnd)) return false;

        PlaceHolder placeHolder = new PlaceHolder(text, delimStart, delimEnd);
        switch (placeHolder.type()) {
            case CONDITION:
            case LOOP:
                processingCondition(Status.PROCESSING, placeHolder.data());
                return true;
            case REPLACEMENT:
            case IMAGE_REPLACEMENT:
            case TABLE_REPLACEMENT:
                return true;
            case CLOSURE:
                if(!(status == Status.PROCESSING && placeHolderText == placeHolder.data())) {
                    throw new TemplateSyntaxException("Unexpected closing tag '" + placeHolder.data() + "'. No corresponding opening tag found.");
                }
                processingCondition(Status.WAITING, null);
                return true;
        }

        return false;
    }

    @Override
    public boolean isMatched(HWPXObject thisObject, ArrayList<HWPXObject> parentsPath) {
        if (thisObject._objectType() == ObjectType.hp_t) return filterT((T) thisObject);
        return false;
    }
}
