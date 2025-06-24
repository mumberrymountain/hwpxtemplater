package kr.mumberrymountain.hwpxTemplater.technical.table;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.table.Tc;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.table.Tr;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxTemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxTemplater.model.table.Col;
import kr.mumberrymountain.hwpxTemplater.model.table.Table;
import kr.mumberrymountain.hwpxTemplater.util.TableFilter;
import kr.mumberrymountain.hwpxTemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TablePara {
    @Test
    @DisplayName("1. 기본 단락 테이블 기본 렌더링")
    private void tableParaBasicTableTest() throws Exception {
        Table tableParam = Table.builder()
                .cols(
                        Arrays.asList(
                                new Col("번호").width(60),
                                new Col("이름").width(150),
                                new Col("점수").width(150),
                                new Col("비고").width(150)
                        )
                )
                .row(new HashMap<String, Object>() {{
                    put("번호", "번호");
                    put("이름", "이름");
                    put("점수", "점수");
                    put("비고", "비고");
                }})
                .row(new HashMap<String, Object>() {{
                    put("번호", 1);
                    put("이름", "홍길동");
                    put("점수", 85);
                    put("비고", null);
                }})
                .row(new HashMap<String, Object>() {{
                    put("번호", 2);
                    put("이름", "김철수");
                    put("점수", 	90);
                    put("비고", "우수 성적");
                }})
                .row(new HashMap<String, Object>() {{
                    put("번호", 3);
                    put("이름", "이영희");
                    put("점수", 78);
                }})
                .create();

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/table/Table_Basic_Para.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Table", tableParam);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new TableFilter(), false);

        List result = Arrays.asList(
            Arrays.asList("번호", "이름", "점수", "비고"),
            Arrays.asList("1", "홍길동", "85", ""),
            Arrays.asList("2", "김철수", "90", "우수 성적"),
            Arrays.asList("3", "이영희", "78", "")
        );

        for (int i = 0; i < results.length; i++) {
            kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Table table
                    = (kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Table) results[i].thisObject();
            assertEquals(getTableText(table), result);
        }
    }

    private ArrayList getTableText(kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Table table){
        ArrayList tableText = new ArrayList();

        table.trs().forEach((tr) -> {
            tableText.add(getRowText(tr));
        });

        return tableText;
    }

    private ArrayList getRowText(Tr tr){
        ArrayList rowText = new ArrayList();

        tr.tcs().forEach((tc) -> {
            setCellText(tc, rowText);
        });

        return rowText;
    }

    private void setCellText(Tc tc, ArrayList rowText) {
        tc.subList().paras().forEach((para) -> {
            para.runs().forEach((run -> {
                run.runItems().forEach((runItem -> {
                    rowText.add(((T) runItem).onlyText());
                }));
            }));
        });
    }
}
