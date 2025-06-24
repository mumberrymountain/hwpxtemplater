package kr.mumberrymountain.hwpxTemplater.linkedobj;

import kr.mumberrymountain.hwpxTemplater.delim.DelimPos;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.RunItem;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;

public class LinkedRunItem implements LinkedObj<RunItem, LinkedRun, LinkedRunItem, DelimPos> {
    private RunItem data;

    private int index;
    private LinkedRunItem next;
    private LinkedRun parent;
    private DelimPos delimPos;

    public LinkedRunItem(T data) {
        this.data = data;
    }

    public LinkedRunItem(RunItem data, LinkedRun parent) {
        this.data = data;
        this.parent = parent;
        this.index = parent.data().getRunItemIndex(data);
        this.next = index + 1 < parent.data().countOfRunItem() ? new LinkedRunItem(parent.data().getRunItem(index + 1), parent): null;
    }

    @Override
    public void setParent(LinkedRun parent) {
        this.parent = parent;
    }

    @Override
    public LinkedRunItem next(){
        return next;
    }

    @Override
    public LinkedRun parent(){
        return parent;
    }

    @Override
    public RunItem data() {
        return data;
    }

    @Override
    public void setData(RunItem data) {
        this.data = data;
    }

    @Override
    public void setDelimPos(DelimPos delimPos){
        this.delimPos = delimPos;
    }

    @Override
    public DelimPos delimPos() {
        return delimPos;
    }
}
