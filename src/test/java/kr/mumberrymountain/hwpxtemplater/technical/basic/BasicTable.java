package kr.mumberrymountain.hwpxtemplater.technical.basic;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicTable {

    @Test
    @DisplayName("1. 기본 테이블 셀 템플릿 렌더링")
    public void basicTableTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Table_01_Text.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Test1", "테스트1");
                    put("Test2", "테스트2");
                    put("Test3", "테스트3");
                    put("Test4", "테스트4");
                    put("Test5", "테스트5");
                    put("Test6", "테스트6");
                    put("Test7", "테스트7");
                    put("Test8", "테스트8");
                    put("Test9", "테스트9");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "테스트1", "테스트_", "테스트2", "_테스트",
                "테스트3", "테스트4", "테스트5", "테스트6",
                "테스트7", "테스트8", "테스트9"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("2. 기본 테이블 셀 템플릿 렌더링시 글자 스타일 유지")
    public void basicTableFontStyleTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Table_02_Style.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Test1", "테스트1");
                    put("Test2", "테스트2");
                    put("Test3", "테스트3");
                    put("Test4", "테스트4");
                    put("Test5", "테스트5");
                    put("Test6", "테스트6");
                    put("Test7", "테스트7");
                    put("Test8", "테스트8");
                    put("Test9", "테스트9");
                    put("Test10", "테스트10");
                    put("Test11", "테스트11");
                    put("Test12", "테스트12");
                    put("Test13", "테스트13");
                    put("Test14", "테스트14");
                    put("Test15", "테스트15");
                    put("Test16", "테스트16");
                    put("Test17", "테스트17");
                    put("Test18", "테스트18");
                    put("Test19", "테스트19");
                    put("Test20", "테스트20");
                    put("Test21", "테스트21");
                    put("Test22", "테스트22");
                    put("Test23", "테스트23");
                    put("Test24", "테스트24");
                    put("Test25", "테스트25");
                    put("Test26", "테스트26");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        ArrayList<String> textStyles = new ArrayList<>();
        List<String> expectedTextResults = Arrays.asList(
                "테스트1", "테스트2", "테스트3", "테스트4", "테스트5", "테스트6", "테스트7", "테스트8", "테스트9", "테스트10",
                "테스트11", "테스트12", "테스트13", "테스트14", "테스트14", "테스트14", "테스트15", "테스트15", "테스트15", "테스트16",
                "테스트16", "테스트16", "테스트17", "테스트17", "테스트17", "테스트18", "테스트18", "테스트18", "테스트19", "테스트19",
                "테스트19", "테스트20", "테스트20", "테스트20", "테스트21", "테스트21", "테스트21", "테스트22", "테스트22", "테스트22",
                "테스트23", "테스트23", "테스트23", "테스트24", "테스트24", "테스트24", "테스트25", "테스트25", "테스트25", "테스트26",
                "테스트26", "테스트26"
        );

        List<String> expectedTextStyleResults = Arrays.asList(
                "20", "7", "8", "9", "10", "11", "25", "23", "12", "13",
                "14", "15", "16", "17", "18", "19", "21", "22", "24", "16",
                "8", "16", "16", "9", "16", "16", "10", "16", "16", "11",
                "16", "16", "25", "16", "16", "23", "16", "16", "12", "16",
                "16", "13", "16", "16", "14", "16", "16", "15", "16", "16",
                "16", "16"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            Run run = (Run) results[i].parentsPath().get(9);
            texts.add(text.onlyText());
            textStyles.add(run.charPrIDRef());
        }

        assertEquals(texts, expectedTextResults);
        assertEquals(textStyles, expectedTextStyleResults);
    }
}
