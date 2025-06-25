package kr.mumberrymountain.hwpxtemplater.model.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Table {
    private final HashMap<String, Object> config = new HashMap<>();

    private final ArrayList<Row> rows;
    private final ArrayList<Col> cols;

    public Table (ArrayList<Row> rows, ArrayList<Col> cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getColCount() {
        return cols.size();
    }

    public int getRowCount() {
        return rows.size();
    }

    public Cell getCell(int rowIdx, int colIdx) {
        return getRow(rowIdx).getCell(getCol(colIdx));
    }

    public Row getRow(int rowIdx) {
        return this.rows.get(rowIdx);
    }

    public Col getCol(int colIdx) {
        return this.cols.get(colIdx);
    }

    public static builder builder() {
        return new builder();
    }

    public static class builder {
        HashMap<String, Object> config;
        ArrayList<Col> cols;
        ArrayList<Row> rows = new ArrayList<>();
        ArrayList<HashMap<String, ?>> rowsParam = new ArrayList<>();

        public void config(String option, Object value){
            config.put(option, value);
        }

        public builder cols(List<Col> cols){
            this.cols = new ArrayList<>(cols);
            return this;
        }

        private ArrayList getColNames(){
            ArrayList colNames = new ArrayList();
            for (Col col : cols) colNames.add(col.getName());
            return colNames;
        }

        private void addCellIntoRow(HashMap<Col, Cell> row, ArrayList colNames, String key, Object value){
            if (colNames.indexOf(key) == -1) return;
            Col col = cols.get(colNames.indexOf(key));

            if (value instanceof Cell) row.put(col, (Cell) value);
            else if (value == null) row.put(col, new Cell(""));
            else row.put(col, new Cell(String.valueOf(value)));
        }

        private Row convertRow(HashMap<String, ?> row, RowType rowType) {
            HashMap<Col, Cell> r = new HashMap<>();
            ArrayList colNames = getColNames();

            for (Map.Entry<String, ?> entry : row.entrySet()) {
                addCellIntoRow(r, colNames, entry.getKey(), entry.getValue());
            }

            cols.forEach((col) -> {
                if(r.get(col) == null) r.put(col, new Cell(""));
            });

            return new Row(r, rowType);
        }

        public builder row(HashMap<String, ?> row){
            new rowBuilder(this, row).apply();
            return this;
        }

        public rowBuilder rowWithStyle(HashMap<String, ?> row) {
            return new rowBuilder(this, row);
        }

        public class rowBuilder {
            private final builder parent;
            private final Row row;

            public rowBuilder(builder parent, HashMap<String, ?> rowParam) {
                this.parent = parent;
                this.row = convertRow(rowParam, RowType.Body);
            }

            public rowBuilder height(int height) {
                this.row.height(height);
                return this;
            }

            public rowBuilder backgroundColor(String backgroundColor) {
                this.row.backgroundColor(backgroundColor);
                return this;
            }

            public rowBuilder fontColor(String fontColor) {
                this.row.fontColor(fontColor);
                return this;
            }

            public rowBuilder fontFamily(String fontFamily) {
                this.row.fontFamily(fontFamily);
                return this;
            }

            public rowBuilder fontSize(int fontSize) {
                this.row.fontSize(fontSize);
                return this;
            }

            public builder apply(){
                rows.add(this.row);
                return parent;
            }
        }


        public Table create(){
            return new Table(rows, cols);
        }
    }
}
