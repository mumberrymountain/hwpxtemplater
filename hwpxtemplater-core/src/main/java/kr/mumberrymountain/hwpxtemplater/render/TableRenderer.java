package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.content.section_xml.enumtype.*;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Table;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.table.Tr;

public class TableRenderer {
    private final HWPXRenderer rootRenderer;
    private final Table renderingTable;

    private final kr.mumberrymountain.hwpxtemplater.model.table.Table table;

    public TableRenderer(HWPXRenderer rootRenderer, kr.mumberrymountain.hwpxtemplater.model.table.Table table) {
        this.rootRenderer = rootRenderer;
        this.table = table;
        this.renderingTable = new Table();
    }

    private void renderRow(int rowIdx){
        Tr tr = renderingTable.addNewTr();

        for (int colIdx = 0; colIdx < table.getColCount(); colIdx++) {
            new TableCellRenderer(rootRenderer, tr.addNewTc(), rowIdx, colIdx,
                    table).render();
        }
    }

    /*
        tbl: 표에 관한 정보를 가진 요소
         - rowCnt: 테이블 행 개수
         - colCnt: 테이블 열 개수
         - pageBreak: 테이블이 페이지 경계에서 나뉘는 방식 - TABLE: 테이블은 나뉘지만 셀은 나뉘지 않음 / CELL: 셀 내의 텍스트도 나뉨 / NONE: 나뉘지 않음
         - repeatHeader: 테이블이 나뉘었을 경우, 제목 행을 나뉜 페이지에서도 반복할지 여부
         - noAdjust: 셀 너비/높이 값의 최소 단위(1 pt) 보정 여부
         - cellSpacing: 셀 간격, 단위는 HWPUNIT
         - borderFillIDRef: 테두리/배경 아이디 참조값
    */
    private void setTable(){
        renderingTable.idAnd(RendererUtil.getRandomId()).rowCntAnd((short) table.getRowCount()).colCntAnd((short) table.getColCount())
                    .numberingTypeAnd(NumberingType.TABLE).textWrapAnd(TextWrapMethod.TOP_AND_BOTTOM)
                    .textFlowAnd(TextFlowSide.BOTH_SIDES).lockAnd(false).dropcapstyleAnd(DropCapStyle.None)
                    .pageBreakAnd(TablePageBreak.CELL).repeatHeaderAnd(true).noAdjustAnd(false)
                    .cellSpacingAnd(0).zOrderAnd(-1).borderFillIDRefAnd("3");
    }

    /*
        inMargin: 바깥쪽 여백 정보
         - left: 왼쪽 여백. 단위는 HWPUNIT.
         - right: 오른쪽 여백. 단위는 HWPUNIT.
         - top: 위쪽 여백. 단위는 HWPUNIT.
         - bottom: 아래쪽 여백. 단위는 HWPUNIT.
    */
    private void setOutMargin(){
        renderingTable.createOutMargin();
        renderingTable.outMargin().leftAnd((long) 283).rightAnd((long) 283).topAnd((long) 283).bottomAnd((long) 283);
    }

    /*
        inMargin: 안쪽 여백 정보
         - left: 왼쪽 여백. 단위는 HWPUNIT.
         - right: 오른쪽 여백. 단위는 HWPUNIT.
         - top: 위쪽 여백. 단위는 HWPUNIT.
         - bottom: 아래쪽 여백. 단위는 HWPUNIT.
    */
    private void setInMargin(){
        renderingTable.createInMargin();
        renderingTable.inMargin().leftAnd((long) 510).rightAnd((long) 510).topAnd((long) 141).bottomAnd((long) 141);
    }

    /*
        pos: 객체들의 위치 정보 및 객체들이 문서에서 차지하는 영역 정보를 가진 요소
         - treatAsChar: 글자처럼 취급 여부
         - affectLSpacing: 줄 간격에 영향을 줄지 여부. treatAsChar 속성이 "true"일 때만 적용
         - flowWithText: 오브젝트의 세로 위치를 본문 영역으로 제한할지 여부. 하위 요소 RelativeTo의 속성 중 "vertical"이 "PARA"일 때만 사용
         - allowOverlap: 다른 오브젝트와 겹치는 것을 허용할지 여부. treatAsChar 속성이 "false"일 때만 사용. flowWithText 속성이 "true"면 무조건 "false"로 간주함
         - holdAnchorAndSO: 객체와 조판부호를 항상 같은 데 놓을지 여부
         - vertRelTo: 세로 위치의 기준. treatAsChar 속성이 "false"일 때만 사용
         - horzRelTo: 가로 위치의 기준. treatAsChar 속성이 "false"일 때만 사용
         - vertAlign: vertRelTo에 관한 상대적인 배열 방식. vertRelTo의 값에 따라 가능한 범위가 제한됨
            * TOP: 위 (vertRelTo="PAPER"|"PAGE"|"PARA")
            * CENTER: 가운데 (vertRelTo="PAPER"|"PAGE")
            * BOTTOM: 아래 (vertRelTo="PAPER"|"PAGE")
            * INSIDE: 안쪽 (vertRelTo="PAPER"|"PAGE")
         - horzAlign: horzRelTo에 대한 상대적인 배열 방식
         - vertOffset: vertRelTo와 verAlign을 기준점으로 한 상대적인 오프셋 값. 단위는 HWPUNIT.
         - horzOffset: horzRelTo와 horzAlign을 기준점으로 한 상대적인 오프셋 값. 단위는 HWPUNIT.
    */
    private void setPos() {
        renderingTable.createPos();
        renderingTable.pos().treatAsCharAnd(false).affectLSpacingAnd(false).flowWithTextAnd(true).allowOverlapAnd(false).holdAnchorAndSOAnd(false)
                            .vertRelToAnd(VertRelTo.PARA).horzRelToAnd(HorzRelTo.COLUMN).vertAlignAnd(VertAlign.TOP).horzAlignAnd(HorzAlign.LEFT)
                            .vertOffsetAnd((long) 0).horzOffsetAnd((long) 0);
    }

    /*
        sz: 객체들의 크기 정보를 가진 요소
         - width: 객체 너비
         - widthRelTo: 객체 너비 기준
         - height: 객체 높이
         - heightRelTo: 객체 높이 기준
    */
    private void setSz() {
        renderingTable.createSZ();
        renderingTable.sz().widthAnd((long) 42522).widthRelToAnd(WidthRelTo.ABSOLUTE)
                            .heightAnd((long) 1284).heightRelToAnd(HeightRelTo.ABSOLUTE).protectAnd(false);
    }

    public Table render(){
        setTable();
        setOutMargin();
        setInMargin();
        setPos();
        setSz();

        // 헤더행 렌더링
        for (int rowIdx = 0; rowIdx < table.getRowCount(); rowIdx++) renderRow(rowIdx);

        return renderingTable;
    }
}
