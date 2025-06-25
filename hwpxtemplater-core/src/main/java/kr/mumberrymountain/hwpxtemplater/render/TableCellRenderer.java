package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.content.section_xml.SubList;
import kr.dogfoot.hwpxlib.object.content.section_xml.enumtype.LineWrapMethod;
import kr.dogfoot.hwpxlib.object.content.section_xml.enumtype.TextDirection;
import kr.dogfoot.hwpxlib.object.content.section_xml.enumtype.VerticalAlign2;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Para;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.table.Tc;
import kr.mumberrymountain.hwpxtemplater.model.table.Cell;
import kr.mumberrymountain.hwpxtemplater.model.table.Col;
import kr.mumberrymountain.hwpxtemplater.model.table.Row;
import kr.mumberrymountain.hwpxtemplater.model.table.Table;
import kr.mumberrymountain.hwpxtemplater.render.style.StyleRenderer;
import kr.mumberrymountain.hwpxtemplater.util.HWPXUnitUtil;

public class TableCellRenderer {

    private final StyleRenderer styleRenderer;
    private final Tc renderingCell;
    private final int rowIdx;
    private final int colIdx;
    private final Table tableParam;
    private final Cell cell;
    private final Col col;
    private final Row row;
    public TableCellRenderer(StyleRenderer styleRenderer, Tc renderingCell, int rowIdx, int colIdx, Table tableParam) {
        this.styleRenderer = styleRenderer;
        this.renderingCell = renderingCell;
        this.rowIdx = rowIdx;
        this.colIdx = colIdx;
        this.tableParam = tableParam;
        this.cell = tableParam.getCell(rowIdx, colIdx);
        this.row = tableParam.getRow(rowIdx);
        this.col = tableParam.getCol(colIdx);
    }

    /*
        tc: 테이블 셀 요소. 하위 요소로 글 내용을 담은 subList 요소를 가짐.
         - name: 셀 필드 이름
         - header: 제목 셀인지 여부
         - hasMargin: 테이블의 기본 셀 여백이 아닌 독자적인 여백을 사용하는지 여부
         - protect: 사용자 편집을 막을지 여부
         - editable: 읽기 전용 상태에서도 수정 가능한지 여부
         - dirty: 마지막 업데이트된 이후 사용자가 내용을 변경했는지 여부
         - borderFillIDRef: 테두리/배경 아이디 참조값
    */
    private void setTc(){
        renderingCell.nameAnd("").headerAnd(false).hasMarginAnd(false).protectAnd(false)
                    .editableAnd(false).dirtyAnd(false).borderFillIDRefAnd(styleRenderer.renderBorderStyle(cell));
    }

    /*
        cellAddr: 표에서 하나의 열이 차지하는 영역을 지정하기 위한 요소
         - colAddr: 셀의 열 주소. 주소는 0부터 시작. 표에서 제일 왼쪽 셀이 0부터 시작하여 1씩 증가
         - rowAddr: 셀의 행 주소. 주소는 0부터 시작. 표에서 제일 위쪽 셀이 0부터 시작하여 1씩 증가
    */
    private void setCellAddr(){
        renderingCell.createCellAddr();
        renderingCell.cellAddr().colAddrAnd((short) colIdx).rowAddrAnd((short) rowIdx);
    }

    /*
        cellSpan: 표에서 하나의 열이 하나의 셀 대신 여러 개의 셀로 구성되어 있다면 병합된 셀 정보를 표현하기 위해 사용되는 요소
         - colSpan: 열 병합 개수
         - rowSpan: 행 병합 개수
    */
    private void setCellSpan() {
        renderingCell.createCellSpan();
        renderingCell.cellSpan().colSpanAnd((short) 1).rowSpanAnd((short) 1);
    }

    /*
        cellSz: 개별 셀의 크기 정보를 가진 요소
         - width: 셀의 너비. 단위는 HWPUNIT.
         - height: 셀의 높이. 단위는 HWPUNIT.
    */
    private void setCellSz(){
        renderingCell.createCellSz();
        renderingCell.cellSz().widthAnd((long) HWPXUnitUtil.pxToHwpxUnit(col.getWidth()))
                              .heightAnd((long) HWPXUnitUtil.pxToHwpxUnit(row.getHeight()));
    }

    /*
        cellMargin: 셀 여백 정보
         - left: 왼쪽 여백. 단위는 HWPUNIT.
         - right: 오른쪽 여백. 단위는 HWPUNIT.
         - top: 위쪽 여백. 단위는 HWPUNIT.
         - bottom: 아래쪽 여백. 단위는 HWPUNIT.
    */
    private void setCellMargin(){
        renderingCell.createCellMargin();
        renderingCell.cellMargin().leftAnd((long) 510).rightAnd((long) 510).topAnd((long) 141).bottomAnd((long) 141);
    }

    private void setSubList(){
        renderingCell.createSubList();
        SubList sl = renderingCell.subList();
        renderingCell.subList().idAnd("").textDirectionAnd(TextDirection.HORIZONTAL).lineWrapAnd(LineWrapMethod.BREAK)
                .vertAlignAnd(VerticalAlign2.CENTER).linkListIDRefAnd("0").linkListNextIDRefAnd("0")
                .textWidthAnd(0).textHeightAnd(0).hasTextRefAnd(false).hasNumRefAnd(false);
        setParagraph(sl);
    }

    private void setParagraph(SubList sl){
        Para cellPara = sl.addNewPara();
        cellPara.id("0");
        cellPara.paraPrIDRef("0");
        cellPara.styleIDRef("0");
        cellPara.pageBreak(false);
        cellPara.columnBreak(false);
        cellPara.merged(false);
        setRun(cellPara);
    }

    private void setRun(Para cellPara) {
        Run cellRun = cellPara.addNewRun();
        T cellT = cellRun.addNewT();
        cellT.addText(cell.getText().getValue());
        cellRun.charPrIDRef(styleRenderer.renderTextStyleAndReturnCharPrId(cell.getText()));
    }

    public void render(){
        setTc();
        setSubList();
        setCellAddr();
        setCellSpan();
        setCellSz();
        setCellMargin();
    }
}
