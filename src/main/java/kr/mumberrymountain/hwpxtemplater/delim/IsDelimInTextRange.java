package kr.mumberrymountain.hwpxtemplater.delim;
public class IsDelimInTextRange {
    private final String text;
    private final int offset;
    private final DelimPos delimPos;
    private boolean startStart;
    private boolean startEnd;
    private boolean endStart;
    private boolean endEnd;

    public IsDelimInTextRange(String text, int offset, DelimPos delimPos) {
        this.text = text;
        this.offset = offset;
        this.delimPos = delimPos;

        checkDelimInTextRange(delimPos);
    }

    private void checkDelimInTextRange(DelimPos delimPos){
        startStart = this.delimInTextRange(delimPos.startPos().start());
        startEnd = this.delimInTextRange(delimPos.startPos().end());
        endStart = this.delimInTextRange(delimPos.endPos().start());
        endEnd = this.delimInTextRange(delimPos.endPos().end());
    }

    private boolean delimInTextRange(int idx) {
        return idx >= offset && idx < text.length() + offset;
    }

    public DelimPos relativeDelimPos(){
        Integer relativeStartStartIdx = null;
        Integer relativeStartEndIdx = null;
        Integer relativeEndStartIdx = null;
        Integer relativeEndEndIdx = null;

        if (startStart) relativeStartStartIdx = delimPos.startPos().start() - offset;
        if (startEnd) relativeStartEndIdx = delimPos.startPos().end() - offset;

        if(endStart) relativeEndStartIdx = delimPos.endPos().start() - offset;
        if(endEnd) relativeEndEndIdx = delimPos.endPos().end() - offset;

        DelimPos pos = new DelimPos(delimPos.startPos().delim(), delimPos.endPos().delim(),
                                        relativeStartStartIdx, relativeStartEndIdx, relativeEndStartIdx, relativeEndEndIdx);
        return pos;
    }

    public DelimInTextRange check() {
        if (startStart && startEnd && endStart && endEnd) {
            return DelimInTextRange.ALL;
        } else if (startStart && startEnd && endStart && !endEnd) {
            return DelimInTextRange.START_All_END_START_ONLY;
        } else if (startStart && startEnd && !endStart && !endEnd) {
            return DelimInTextRange.START_ALL_END_NONE;
        } else if (startStart && !startEnd && !endStart && !endEnd) {
            return DelimInTextRange.START_START_ONLY;
        } else if (!startStart && startEnd && !endStart && !endEnd) {
            return DelimInTextRange.START_END_ONLY_END_NONE;
        } else if (!startStart && startEnd && endStart && endEnd) {
            return DelimInTextRange.START_END_ONLY_END_ALL;
        } else if (!startStart && startEnd && endStart && !endEnd) {
            return DelimInTextRange.START_END_ONLY_END_START_ONLY;
        }else if (!startStart && !startEnd && endStart && endEnd) {
            return DelimInTextRange.START_NONE_END_ALL;
        } else if (!startStart && !startEnd && endStart && !endEnd) {
            return DelimInTextRange.START_NONE_END_START_ONLY;
        } else if (!startStart && !startEnd && !endStart && endEnd) {
            return DelimInTextRange.START_NONE_END_END_ONLY;
        } else {
            return DelimInTextRange.NONE;
        }
    }
}
