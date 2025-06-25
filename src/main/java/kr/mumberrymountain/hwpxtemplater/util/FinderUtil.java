package kr.mumberrymountain.hwpxtemplater.util;

import kr.dogfoot.hwpxlib.object.HWPXFile;
import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.render.PlaceHolderFilter;

public class FinderUtil {

    public static ObjectFinder.Result[] findPlaceHolder(HWPXFile hwpxFile, String delimStart, String delimEnd) throws Exception {
        return ObjectFinder.find(hwpxFile, new PlaceHolderFilter(delimStart, delimEnd), false);
    }

    public static ObjectFinder.Result[] findTag(HWPXFile hwpxFile, ObjectType objectType, ObjectType parentsType) throws Exception {
        switch (objectType) {
            case hp_p:
                return ObjectFinder.find(hwpxFile, new ParaFilter(parentsType), false);
        }
        return null;
    }
}
