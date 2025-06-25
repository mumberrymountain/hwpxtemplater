package kr.mumberrymountain.hwpxtemplater.parse;

import kr.mumberrymountain.hwpxtemplater.delim.DelimInTextRange;

import java.util.ArrayList;

public class EndPatterns {

    private final ArrayList<ArrayList<DelimInTextRange>> endPatterns = new ArrayList<>();

    private ArrayList<DelimInTextRange> createEndPattern(DelimInTextRange... ranges) {
        ArrayList<DelimInTextRange> pattern = new ArrayList<>();
        for (DelimInTextRange range : ranges) {
            pattern.add(range);
        }
        return pattern;
    }

    protected void append(DelimInTextRange... delimInTextRange){
        endPatterns.add(createEndPattern(delimInTextRange));
    }

    protected boolean isEndPattern(DelimInTextRange pattern){
        endPatterns.removeIf( pt -> pt.isEmpty() || pt.get(0) != pattern );
        for (ArrayList<DelimInTextRange> endPattern : endPatterns) {
            if (endPattern.get(0) == pattern) return true;
        }

        return false;
    }

    protected int processEndPattern(DelimInTextRange pattern){
        int result = 0;
        for (ArrayList<DelimInTextRange> endPattern : endPatterns) {
            if (!endPattern.isEmpty() && endPattern.get(0) == pattern) {
                endPattern.remove(0);
                if(endPattern.size() == 0) result = 1;
            }
        }

        return result;
    }
}
