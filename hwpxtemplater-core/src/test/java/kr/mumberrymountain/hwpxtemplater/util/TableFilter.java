package kr.mumberrymountain.hwpxtemplater.util;

import kr.dogfoot.hwpxlib.object.common.HWPXObject;
import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.tool.finder.comm.ObjectFilter;

import java.util.ArrayList;

public class TableFilter implements ObjectFilter {
    @Override
    public boolean isMatched(HWPXObject thisObject, ArrayList<HWPXObject> parentsPath) {
        if (thisObject._objectType() == ObjectType.hp_tbl) return true;
        return false;
    }
}
