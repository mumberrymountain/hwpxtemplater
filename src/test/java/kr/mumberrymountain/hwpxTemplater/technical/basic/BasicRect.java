package kr.mumberrymountain.hwpxTemplater.technical.basic;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxTemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxTemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxTemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicRect {
    @Test
    @DisplayName("1. 글상자 내 단락 템플릿 렌더링")
    public void basicRectTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Rect_01_Text.hwpx"))
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
}
