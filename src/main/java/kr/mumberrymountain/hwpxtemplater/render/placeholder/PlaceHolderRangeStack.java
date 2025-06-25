package kr.mumberrymountain.hwpxtemplater.render.placeholder;

import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRun;

import java.util.ArrayDeque;
import java.util.Deque;

public class PlaceHolderRangeStack {
    private Deque<PlaceHolderRange> rangeStack = new ArrayDeque<>();

    public void push(PlaceHolderRange range){
        rangeStack.push(range);
    }

    public void pop() {
        rangeStack.pop();
    }

    public PlaceHolderRange current(){
        return rangeStack.peek();
    }

    public void add(LinkedRun linkedRun) {
        for (PlaceHolderRange range : rangeStack) {
            range.add(linkedRun);
        }
    }

    public void add(LinkedRun linkedRun, PlaceHolder placeHolder) {
        for (PlaceHolderRange range : rangeStack) {
            range.add(linkedRun, placeHolder);
        }
    }
}
