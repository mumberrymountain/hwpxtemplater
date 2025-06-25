package kr.mumberrymountain.hwpxtemplater.linkedobj;

import kr.dogfoot.hwpxlib.object.common.HWPXObject;
import kr.dogfoot.hwpxlib.object.content.section_xml.SectionXMLFile;
import kr.dogfoot.hwpxlib.object.content.section_xml.SubList;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Para;
import kr.mumberrymountain.hwpxtemplater.delim.DelimPos;

public class LinkedPara implements LinkedObj<Para, HWPXObject, LinkedPara, DelimPos> {

    private Para data;
    private int index;
    private LinkedPara next;
    private HWPXObject parent;

    private DelimPos delimPos;

    public LinkedPara(Para data) {
        this.data = data;
    }

    public LinkedPara(Para data, HWPXObject parent) {
        this.data = data;
        this.parent = parent;

        checkParentType();
    }

    private void checkParentType(){
        switch (parent._objectType()) {
            case hs_sec:
                SectionXMLFile section = (SectionXMLFile) parent;
                this.index = section.getParaIndex(data);
                this.next = index + 1 < section.countOfPara() ? new LinkedPara(section.getPara(index + 1), parent) : null;
                break;
            case hp_subList:
                SubList subList = (SubList) parent;
                this.index = subList.getParaIndex(data);
                this.next = index + 1 < subList.countOfPara() ? new LinkedPara(subList.getPara(index + 1), parent) : null;
                break;
        }
    }

    @Override
    public LinkedPara next() {
        return next;
    }

    @Override
    public void setParent(HWPXObject parent) {
        this.parent = parent;
    }

    @Override
    public HWPXObject parent() {
        return parent;
    }

    @Override
    public Para data() {
        return data;
    }

    @Override
    public void setData(Para data) {
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
