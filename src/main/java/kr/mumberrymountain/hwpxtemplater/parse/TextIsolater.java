package kr.mumberrymountain.hwpxtemplater.parse;

import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.RunItem;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.TItem;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.t.NormalText;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.t.Tab;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedPara;
import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRun;

import java.util.ArrayList;
import java.util.Collection;

public class TextIsolater {

    private final LinkedPara linkedPara;
    protected TextIsolater(LinkedPara linkedPara) {
        this.linkedPara = linkedPara;
    }

    protected void reformatParaToIsolateText(LinkedPara linkedPara) {
        ArrayList<Run> runs = new ArrayList<>((Collection) linkedPara.data().runs());
        boolean firstElement = true;

        for (Run run : new ArrayList<>(runs)) {
            LinkedRun linkedRun = new LinkedRun(run, linkedPara);
            ArrayList<Run> recreatedRuns = new ArrayList<>();

            for (RunItem runItem : linkedRun.data().runItems()) {
                if (runItem._objectType() != ObjectType.hp_t) continue;

                T t = (T) runItem;
                if (t.isOnlyText()) continue;
                if (t.items() == null) continue;
                firstElement = reformatTToIsolateText(t, run, recreatedRuns, firstElement);
            }

            if (!recreatedRuns.isEmpty()) {
                int idx = runs.indexOf(run);
                runs.remove(idx);
                runs.addAll(idx, recreatedRuns);
            }
        }

        linkedPara.data().removeAllRuns();
        runs.forEach(r -> linkedPara.data().addRun(r));
    }

    private Run createOrigBasedNewRun(Run originalRun){
        Run newRun = originalRun.clone();
        newRun.removeAllRunItems();
        return newRun;
    }

    private T createOrigBasedNewT(T t){
        T newT = t.clone();
        newT.removeAllItems();
        return newT;
    }

    private boolean reformatTToIsolateText(T t, Run originalRun, ArrayList<Run> recreatedRuns, boolean firstElement) {
        for (TItem tItem : t.items()) {
            Run newRun = createOrigBasedNewRun(originalRun);
            T newT = createOrigBasedNewT(t);

            switch (tItem._objectType()) {
                case NormalText:
                    newT.addText(((NormalText) tItem).text());
                    break;

                case hp_tab:
                    if (!firstElement) {
                        Tab tab = new Tab();
                        tab.width(20);
                        newT.addItem(tab);
                    }
                    newT.addItem((TItem) tItem.clone());
                    firstElement = false;
                    break;

                default:
                    newT.addItem((TItem) tItem.clone());
                    break;
            }
            newRun.addRunItem(newT);
            recreatedRuns.add(newRun);
        }
        return firstElement;
    }
}
