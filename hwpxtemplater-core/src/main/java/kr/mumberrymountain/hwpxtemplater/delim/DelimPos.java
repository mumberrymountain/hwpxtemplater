package kr.mumberrymountain.hwpxtemplater.delim;

import kr.dogfoot.hwpxlib.object.common.HWPXObject;

public class DelimPos {

    private final DelimStartPos delimStartPos;
    private final DelimEndPos delimEndPos;
    private HWPXObject base;

    public DelimPos(HWPXObject base, String delimStart, String delimEnd,
                    Integer startStartIndex, Integer startEndIndex, Integer endStartIndex, Integer endEndIndex) {
        this.base = base;
        delimStartPos = new DelimStartPos(delimStart, startStartIndex, startEndIndex);
        delimEndPos = new DelimEndPos(delimEnd, endStartIndex, endEndIndex);
    }

    public DelimPos(String delimStart, String delimEnd,
                    Integer startStartIndex, Integer startEndIndex, Integer endStartIndex, Integer endEndIndex) {
        delimStartPos = new DelimStartPos(delimStart, startStartIndex, startEndIndex);
        delimEndPos = new DelimEndPos(delimEnd, endStartIndex, endEndIndex);
    }

    public DelimPos(HWPXObject base, String delimStart, String delimEnd, Integer startIndex, Integer endIndex) {
        this.base = base;
        delimStartPos = new DelimStartPos(delimStart, startIndex);
        delimEndPos = new DelimEndPos(delimEnd, endIndex);
    }

    public DelimPos(String delimStart, String delimEnd, int startIndex, int endIndex) {
        delimStartPos = new DelimStartPos(delimStart, startIndex);
        delimEndPos = new DelimEndPos(delimEnd, endIndex);
    }

    public DelimStartPos startPos() {
        return delimStartPos;
    }

    public DelimEndPos endPos() {
        return delimEndPos;
    }

    public Integer[] getDelimPosAsArray(){
        return new Integer[] {delimStartPos.start(), delimStartPos.end(), delimEndPos.start(), delimEndPos.end()};
    }
}
