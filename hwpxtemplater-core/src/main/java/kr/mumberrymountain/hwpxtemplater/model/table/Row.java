package kr.mumberrymountain.hwpxtemplater.model.table;

import java.util.HashMap;
import java.util.logging.Logger;

public class Row {
    private RowType rowType;
    private int height = 10;
    private HashMap <Col, Cell> cells;
    private static final Logger logger = Logger.getLogger(Row.class.getName());

    public Row (HashMap <Col, Cell> cells, RowType rowType) {
        this.cells = cells;
        this.rowType = rowType;
    }

    public void type (RowType rowType) {
        this.rowType = rowType;
    }

    public Cell getCell(Col col){
        return cells.get(col);
    }

    public int getHeight() {
        return height;
    }

    public Row height(int height) {
        if (height <= 0) {
            logger.warning(String.format("Invalid height value: %d. Height must be greater than 0.", height));
            return this;
        }

        this.height = height;
        return this;
    }

    public Row backgroundColor(String backgroundColor){
        cells.forEach((col, cell) -> {
            cell.backgroundColor(backgroundColor);
        });
        return this;
    }

    public Row fontColor(String fontColor) {
        cells.forEach((col, cell) -> {
            cell.fontColor(fontColor);
        });
        return this;
    }

    public Row fontFamily(String fontFamily) {
        cells.forEach((col, cell) -> {
            cell.fontFamily(fontFamily);
        });
        return this;
    }

    public Row fontSize(int fontSize) {
        if (fontSize <= 0) {
            logger.warning(String.format("Invalid fontSize value: %d. FontSize must be greater than 0.", fontSize));
            return this;
        }

        cells.forEach((col, cell) -> {
            cell.fontSize(fontSize);
        });
        return this;
    }
}
