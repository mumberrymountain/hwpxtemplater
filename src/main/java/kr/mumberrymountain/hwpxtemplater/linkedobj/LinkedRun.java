package kr.mumberrymountain.hwpxtemplater.linkedobj;

import kr.mumberrymountain.hwpxtemplater.delim.DelimPos;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;

public class LinkedRun implements LinkedObj<Run, LinkedPara, LinkedRun, DelimPos> {
    private Run data;
    private int index;
    private LinkedRun next;
    private LinkedPara parent;

    private DelimPos delimPos;

    public LinkedRun(Run data) {
        this.data = data;
    }

    public LinkedRun(Run data, LinkedPara parent) {
        this.data = data;
        this.parent = parent;
        this.index = parent.data().getRunIndex(data);
        this.next = index + 1 < parent.data().countOfRun() ? new LinkedRun(parent.data().getRun(index + 1), parent) : null;
    }

    @Override
    public void setParent(LinkedPara parent) {
        this.parent = parent;
    }

    @Override
    public LinkedRun next(){
        return next;
    }

    @Override
    public LinkedPara parent(){
        return parent;
    }

    @Override
    public Run data() {
        return data;
    }

    @Override
    public void setData(Run data) {
        this.data = data;
    }

    @Override
    public void setDelimPos(DelimPos delimPos) {
        this.delimPos = delimPos;
    }

    @Override
    public DelimPos delimPos() {
        return delimPos;
    }
}
