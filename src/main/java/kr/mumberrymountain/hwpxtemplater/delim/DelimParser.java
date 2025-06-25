package kr.mumberrymountain.hwpxtemplater.delim;

import java.util.ArrayList;
import java.util.List;

public class DelimParser {

    String delimStart;
    String delimEnd;

    int index = 0;
    public DelimParser (String delimStart, String delimEnd) {
        this.delimStart = delimStart;
        this.delimEnd = delimEnd;
    }

    public List<DelimPos> parse(String input) {
        List<DelimPos> positions = new ArrayList<>();

        while (index < input.length()) {
            int startIdx = input.indexOf(delimStart, index);
            if (startIdx == -1) break;

            int endIdx = input.indexOf(delimEnd, startIdx);
            if (endIdx == -1) break;

            positions.add(new DelimPos(delimStart, delimEnd, startIdx, endIdx));
            index = endIdx + 2;
        }

        return positions;
    }
}
