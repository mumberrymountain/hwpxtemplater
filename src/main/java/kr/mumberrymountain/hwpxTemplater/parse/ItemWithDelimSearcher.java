package kr.mumberrymountain.hwpxTemplater.parse;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.RunItem;
import kr.mumberrymountain.hwpxTemplater.delim.DelimInTextRange;
import kr.mumberrymountain.hwpxTemplater.delim.DelimPos;
import kr.mumberrymountain.hwpxTemplater.delim.IsDelimInTextRange;
import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedObj;
import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedPara;
import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedRun;
import kr.mumberrymountain.hwpxTemplater.linkedobj.LinkedRunItem;
import kr.mumberrymountain.hwpxTemplater.util.Status;
import kr.mumberrymountain.hwpxTemplater.util.ParaUtil;

import java.util.ArrayList;

public class ItemWithDelimSearcher {
    private int idxCumulated = 0;
    private Status collecting = Status.WAITING;
    private final ArrayList<LinkedObj> result = new ArrayList<>();
    private final EndPatterns endPatterns = new EndPatterns();
    private final DelimPos delim;

    protected ItemWithDelimSearcher(DelimPos delim) {
        this.delim = delim;
    }

    private void processAll(LinkedObj linkedT){
        result.add(linkedT);
    }

    private void processStartAllEndStartOnly(LinkedObj linkedT) {
        collecting = Status.PROCESSING;
        endPatterns.append(DelimInTextRange.START_NONE_END_END_ONLY);
        result.add(linkedT);
    }

    private void processStartAllEndNone(LinkedObj linkedT) {
        collecting = Status.PROCESSING;
        endPatterns.append(DelimInTextRange.START_NONE_END_ALL);
        endPatterns.append(
                DelimInTextRange.START_NONE_END_START_ONLY,
                DelimInTextRange.START_NONE_END_END_ONLY
        );
        result.add(linkedT);
    }

    private void processStartStartOnly(LinkedObj linkedT) {
        collecting = Status.PROCESSING;
        endPatterns.append(DelimInTextRange.START_END_ONLY_END_ALL);
        endPatterns.append(
                DelimInTextRange.START_END_ONLY_END_NONE,
                DelimInTextRange.START_NONE_END_ALL
        );
        endPatterns.append(
                DelimInTextRange.START_END_ONLY_END_NONE,
                DelimInTextRange.START_NONE_END_START_ONLY,
                DelimInTextRange.START_NONE_END_END_ONLY
        );
        endPatterns.append(
                DelimInTextRange.START_END_ONLY_END_START_ONLY,
                DelimInTextRange.START_NONE_END_END_ONLY
        );
        result.add(linkedT);
    }

    private void processStartStartNone(LinkedObj linkedT, DelimInTextRange check){
        if (endPatterns.isEndPattern(check)) {
            result.add(linkedT);
            collecting = endPatterns.processEndPattern(check) == 1 ? Status.FINISH : collecting;
        }
    }

    private DelimInTextRange checkDelimInTextRange(LinkedObj linkedT, String text){
        IsDelimInTextRange delimInTextRange = new IsDelimInTextRange(text, idxCumulated, delim);
        DelimInTextRange check = delimInTextRange.check();
        DelimPos relativeDelimPos = delimInTextRange.relativeDelimPos();
        linkedT.setDelimPos(relativeDelimPos);
        return check;
    }

    private void processT(RunItem item, LinkedObj linkedRun){
        LinkedObj linkedT = new LinkedRunItem(item, (LinkedRun) linkedRun);
        String runItemText = ParaUtil.getRunItemText((RunItem) linkedT.data());

        DelimInTextRange check = checkDelimInTextRange(linkedT, runItemText);
        switch (check) {
            case ALL:
                processAll(linkedT);
                break;
            case START_All_END_START_ONLY:
                processStartAllEndStartOnly(linkedT);
                break;
            case START_ALL_END_NONE:
                processStartAllEndNone(linkedT);
                break;
            case START_START_ONLY:
                processStartStartOnly(linkedT);
                break;

            case START_END_ONLY_END_ALL:
                processStartStartNone(linkedT, check);
                break;
            case START_END_ONLY_END_START_ONLY:
                processStartStartNone(linkedT, check);
                break;
            case START_END_ONLY_END_NONE:
                processStartStartNone(linkedT, check);
                break;
            case START_NONE_END_ALL:
                processStartStartNone(linkedT, check);
                break;
            case START_NONE_END_START_ONLY:
                processStartStartNone(linkedT, check);
                break;
            case START_NONE_END_END_ONLY:
                processStartStartNone(linkedT, check);
                break;
            case NONE:
                if (collecting == Status.PROCESSING) result.add(linkedT);
                break;
        }

        idxCumulated += runItemText.length();
    }

    protected ArrayList<LinkedObj> find(LinkedPara linkedPara) {

        for (Run run : linkedPara.data().runs()) {
            LinkedObj linkedRun = new LinkedRun(run, linkedPara);

            for (RunItem item : run.runItems()) {

                switch (item._objectType()) {
                    case hp_t:
                        processT(item, linkedRun);
                        break;
                    case hp_ctrl:
                    case hp_tbl:
                    case hp_container:
                    case hp_line:
                    case hp_rect:
                    case hp_ellipse:
                    case hp_arc:
                    case hp_polygon:
                    case hp_curve:
                    case hp_connectLine:
                    case hp_textart:
                }

                if (collecting == Status.FINISH) break;
            }
        }

        return result;
    }
}
