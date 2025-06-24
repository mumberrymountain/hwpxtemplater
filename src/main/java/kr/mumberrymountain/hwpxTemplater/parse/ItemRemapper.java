package kr.mumberrymountain.hwpxTemplater.parse;

import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.RunItem;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedObj;
import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedRun;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ItemRemapper {
    private final ArrayList<LinkedObj> items;

    protected ItemRemapper(ArrayList<LinkedObj> items){
        this.items = items;
    }

    private Run createRun(String content, LinkedRun run, boolean firstSegment){
        T text = new T();
        text.addText(content);

        Run newRun = run.data().clone();

        if (newRun.countOfRunItem() > 1 && firstSegment == true) {
            Iterator<RunItem> iterator = newRun.runItems().iterator();
            while (iterator.hasNext()) {
                RunItem newRunItem = iterator.next();
                if (newRunItem._objectType() == ObjectType.hp_t) iterator.remove();
            }
        } else  {
            newRun.removeAllRunItems();
        }


        newRun.addRunItem(text);

        return newRun;
    }

    private ArrayList<Run> createRemappedRun(LinkedRun run){
        TextMapper textMapper = new TextMapper();
        List<String> contents = textMapper.map(items);
        ArrayList<Run> remappedRun = new ArrayList<>();
        boolean firstSegment = true;
        for(String content: contents) {
            remappedRun.add(createRun(content, run, firstSegment));
            firstSegment = false;
        }

        return remappedRun;
    }

    private void addRemappedRun(){
        LinkedRun run = (LinkedRun) items.get(0).parent();
        int runIndex = run.parent().data().getRunIndex(run.data());

        // 머지 데이터 생성
        ArrayList<Run> remappedRuns = createRemappedRun(run);

        for (Run remappedRun : remappedRuns) {
            run.parent().data().insertRun(remappedRun, runIndex);
            runIndex++;
        }
    }

    private void disposePreExistingRun(){
        for (LinkedObj item : items) {
            LinkedRun linkedRun = (LinkedRun) item.parent();
            linkedRun.parent().data().removeRun(linkedRun.data());
        }
    }

    protected void reMap(){
        addRemappedRun();
        disposePreExistingRun();
    }
}
