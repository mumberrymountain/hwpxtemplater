package kr.mumberrymountain.hwpxTemplater.technical.condition;

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

public class ConditionPara {
    @Test
    @DisplayName("1. 기본 단락 조건문 템플릿 렌더링")
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

    @Test
    @DisplayName("2. 기본 단락 조건문 및 조건문 내 필드 템플릿 렌더링")
    public void conditionParaInnerPlaceHolderTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/condition/Condition_Para_InnerPlaceHolder.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("IsReptiles", true);
                    put("Lizard", "도마뱀");
                    put("Snake", "뱀");
                    put("Turtle", "거북이");

                    put("IsMammals", false);
                    put("Lion", "사자");
                    put("Whale", "고래");
                    put("Human", "인간");

                    put("IsTrees", true);
                    put("Pine", "소나무");
                    put("AppleTree", "사과나무");
                    put("CherryTree", "벚나무");

                    put("IsFlowers", false);
                    put("Rose", "장미");
                    put("Tulip", "튤립");
                    put("Sunflower", "해바라기");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                " 파충류: ", "도마뱀", " ", "뱀", " ", "거북이", " ", "나무:", "소나무", "사과나무", "벚나무"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
