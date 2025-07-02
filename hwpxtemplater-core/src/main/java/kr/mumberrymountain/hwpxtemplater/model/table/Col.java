package kr.mumberrymountain.hwpxtemplater.model.table;

import java.util.logging.Logger;

public class Col {
    private int width = 100;

    private String name;

    private static final Logger logger = Logger.getLogger(Col.class.getName());

    public Col(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Col width(int width){
        if (width <= 0) {
            logger.warning(String.format("Invalid width value: %d. Width must be greater than 0.", width));
            return this;
        }

        this.width = width;
        return this;
    }

    public int getWidth() {
        return width;
    }
}
