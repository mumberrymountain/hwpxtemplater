package kr.mumberrymountain.hwpxtemplater.parse;

import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedObj;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRunItem;
import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.mumberrymountain.hwpxtemplater.util.ParaUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class TextMapper {
    private final StringBuffer sb = new StringBuffer();
    private final List<String> result = new ArrayList<>();
    private final AtomicBoolean collecting = new AtomicBoolean(false);

    private void flush(List<String> result, boolean collect){
        collecting.set(collect);
        if (sb.length() > 0) {
            result.add(sb.toString());
            sb.setLength(0);
        }
    }

    private void mapContent(Integer i, LinkedRunItem runItem, String text){
        if (Objects.equals(i, runItem.delimPos().startPos().start())) flush(result, true);
        sb.append(text.charAt(i));
        if (Objects.equals(i, runItem.delimPos().endPos().end()) && collecting.get()) flush(result, false);
    }

    private void mapItem(LinkedObj item){
        if (!(item instanceof LinkedRunItem)) {} // 캐스팅 에러 처리
        LinkedRunItem runItem = (LinkedRunItem) item;
        if(runItem.data()._objectType() != ObjectType.hp_t) return;

        T text = (T) runItem.data();
        String tText = ParaUtil.getTText(text);
        if(tText == null) return;

        for (Integer i = 0; i < tText.length(); i++) {
            mapContent(i, runItem, tText);
        }
    }

    protected List<String> map(ArrayList<LinkedObj> items){
        for (LinkedObj item : items) mapItem(item);
        flush(result, false);

        return result;
    }

}
