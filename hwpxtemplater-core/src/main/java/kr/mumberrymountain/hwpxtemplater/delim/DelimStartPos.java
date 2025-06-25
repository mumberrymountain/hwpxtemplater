package kr.mumberrymountain.hwpxtemplater.delim;

public class DelimStartPos implements DelimEdgePos {
    private final String delimStart;
    private final Integer start;
    private final Integer end;

    public DelimStartPos(String delimStart, Integer startIndex) {
        this.delimStart = delimStart;
        this.start = startIndex;
        this.end = startIndex + (delimStart.length() - 1);
    }

    public DelimStartPos(String delimStart, Integer startIndex, Integer endIndex) {
        this.delimStart = delimStart;
        this.start = startIndex;
        this.end = endIndex;
    }

    @Override
    public String delim() {
        return delimStart;
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
