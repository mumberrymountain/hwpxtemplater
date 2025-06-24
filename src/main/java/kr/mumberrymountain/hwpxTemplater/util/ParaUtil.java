package kr.mumberrymountain.hwpxTemplater.util;

import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.*;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.t.NormalText;

public class ParaUtil {
    public static boolean containsDelim(T text, String delimStart, String delimEnd){
        if (text.items() != null) {
            boolean containsDelim = false;
            for (TItem tItem : text.items()) {
                if (tItem._objectType() == ObjectType.NormalText) {
                    NormalText t = (NormalText) tItem;
                    containsDelim = t.text().indexOf(delimStart) > -1 && t.text().indexOf(delimEnd) > -1;
                }
            }

            return containsDelim;
        }

        return text.isOnlyText() && text.onlyText().indexOf(delimStart) > -1 && text.onlyText().indexOf(delimEnd) > -1;
    }

    public static boolean containsT(Para para) {
        for (Run run : para.runs()) {
            for (RunItem runItem : run.runItems()) {
                if(runItem._objectType() == ObjectType.hp_t) return  true;
            }
        }

        return false;
    }

    public static String getParaText(Para para) {
        StringBuffer sb = new StringBuffer();

        for (Run run : para.runs()) {
            sb.append(getRunText(run));
        }

        return sb.toString();
    }

    public static String getRunText(Run run) {
        StringBuffer sb = new StringBuffer();

        for (RunItem item : run.runItems()) {
            sb.append(getRunItemText(item));
        }

        return sb.toString();
    }

    public static String getRunItemText(RunItem runItem) {
        StringBuffer sb = new StringBuffer();
        if(runItem._objectType() != ObjectType.hp_t) return "";
        T text = (T) runItem;
        sb.append(getTText(text));

        return sb.toString();
    }

    public static String getTText(T t) {
        StringBuffer sb = new StringBuffer();

        if(t.isOnlyText()) sb.append(t.onlyText());
        if(t.items() != null) {
            for (TItem tItem : t.items()) sb.append(getTextItemText(tItem));
        }

        return sb.toString();
    }

    public static String getTextItemText(TItem tItem){
        StringBuffer sb = new StringBuffer();
        if (tItem._objectType() != ObjectType.NormalText) return "";
        NormalText t = (NormalText) tItem;
        sb.append(t.text());

        return sb.toString();
    }
}
