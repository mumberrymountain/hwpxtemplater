package kr.mumberrymountain.hwpxtemplater.render.placeholder;

import kr.mumberrymountain.hwpxtemplater.linkedobj.LinkedRun;
import kr.mumberrymountain.hwpxtemplater.util.Status;

import java.util.ArrayList;

public class PlaceHolderRange {
    private final ArrayList<LinkedRun> linkedRuns = new ArrayList<>();
    private final ArrayList<PlaceHolder> placeHolders = new ArrayList<>();

    private Status status = Status.WAITING;

    private String placeHolderText = null;

    private PlaceHolderType type;


    public void add(LinkedRun linkedRun, PlaceHolder placeHolder){
        linkedRuns.add(linkedRun);
        placeHolders.add(placeHolder);
    }

    public void add(LinkedRun linkedRun){
        linkedRuns.add(linkedRun);
    }

    public ArrayList<LinkedRun> linkedRuns() {
        return linkedRuns;
    }

    public LinkedRun start(){
        return linkedRuns.get(0);
    }

    public LinkedRun end(){
        return linkedRuns.get(linkedRuns.size() - 1);
    }

    public ArrayList<PlaceHolder> placeHolders(){
        return placeHolders;
    }

    public PlaceHolder startPlaceholder(){
        return placeHolders.get(0);
    }

    public PlaceHolder endPlaceholder(){
        return placeHolders.get(placeHolders.size() - 1);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status status(){
        return status;
    }

    public void setPlaceHolderText(String placeHolderText) {
        this.placeHolderText = placeHolderText;
    }

    public String placeHolderText() {
        return placeHolderText;
    }

    public void setType(PlaceHolderType type) {
        this.type = type;
    }

    public PlaceHolderType type(){
        return type;
    }

    public Pos pairAlignment(){
        if (start().parent().data().equals(end().parent().data())) {
            return Pos.HORIZONTAL;
        }
        return Pos.VERTICAL;
    }
}
