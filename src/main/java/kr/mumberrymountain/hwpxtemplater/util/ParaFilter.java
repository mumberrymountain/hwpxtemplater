package kr.mumberrymountain.hwpxtemplater.util;

import kr.dogfoot.hwpxlib.object.common.HWPXObject;
import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.tool.finder.comm.ObjectFilter;

import java.util.ArrayList;

import static kr.dogfoot.hwpxlib.object.common.ObjectType.hp_p;

public class ParaFilter implements ObjectFilter {

    private final ObjectType parentType;
    public ParaFilter(ObjectType parentType) {
        this.parentType = parentType;
    }
    @Override
    public boolean isMatched(HWPXObject thisObject, ArrayList<HWPXObject> parentsPath) {
        if (thisObject._objectType() == hp_p &&
            parentsPath.get(parentsPath.size() - 1)._objectType().equals(parentType)) {
                return true;
        }

        return false;
    }
}
