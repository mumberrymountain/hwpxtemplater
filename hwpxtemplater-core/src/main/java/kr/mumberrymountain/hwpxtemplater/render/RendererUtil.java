package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.content.section_xml.SectionXMLFile;
import kr.mumberrymountain.hwpxtemplater.Config;
import kr.mumberrymountain.hwpxtemplater.ConfigOption;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRange;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.Pos;
import kr.mumberrymountain.hwpxtemplater.util.Status;

import java.util.UUID;

import static kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderType.LOOP;

public class RendererUtil {

    public static boolean isRangeVertical(PlaceHolderRange currentRange){
        return currentRange != null && currentRange.pairAlignment() == Pos.VERTICAL
                && currentRange.start().parent().parent() instanceof SectionXMLFile;
    }

    public static boolean isCurrentRangeProcessing(PlaceHolderRange currentRange){
        return currentRange != null && currentRange.status() == Status.PROCESSING;
    }

    public static boolean isCurrentRangeLoop(PlaceHolderRange currentRange){
        return currentRange != null && currentRange.type() == LOOP;
    }

    public static String getRandomId() {
        return UUID.randomUUID().toString();
    }

    public static Boolean isAutoTrim(Config config){
        return (Boolean) config.get(ConfigOption.AUTO_TRIM.getType());
    }
}
