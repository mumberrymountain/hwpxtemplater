package kr.mumberrymountain.hwpxtemplater.technical.condition;

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

public class ConditionRect {
    @Test
    @DisplayName("1. 글상자 내 단락 조건문 템플릿 렌더링")
    public void conditionParaTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/condition/Condition_Para_Text.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Render1", true);
                    put("Render2", false);
                    put("Render3", true);
                    put("Render4", false);
                    put("Render5", true);
                    put("Render6", false);
                    put("Render7", true);
                    put("Render8", false);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "참1", "    ", "테스트3_", "참2", "_테스트3", "테스트4_", "_테스트4", "참3", "참4"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
