package kr.mumberrymountain.hwpxTemplater.model.table;

public class Col {
    private int width = 100;

    private String name;

    public Col(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Col width(int width){
        this.width = width;
        return this;
    }

    public int getWidth() {
        return width;
    }
}
