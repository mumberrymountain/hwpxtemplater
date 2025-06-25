package kr.mumberrymountain.hwpxtemplater.delim;

public class DelimEndPos implements DelimEdgePos {
    private final String delimEnd;
    private final Integer start;
    private final Integer end;

    public DelimEndPos(String delimEnd, Integer startIndex) {
        this.delimEnd = delimEnd;
        this.start = startIndex;
        this.end = startIndex + (delimEnd.length() - 1);
    }

    public DelimEndPos(String delimEnd, Integer startIndex, Integer endIndex) {
        this.delimEnd = delimEnd;
        this.start = startIndex;
        this.end = endIndex;
    }

    @Override
    public String delim() {
        return delimEnd;
    }

    @Override
    public Integer start() {
        return start;
    }

    @Override
    public Integer end() {
        return end;
    }
}
