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

public class ConditionNestedPara {
    @Test
    @DisplayName("1. 기본 단락 중첩 조건문 템플릿 렌더링")
    public void conditionNestedParaTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath (this.getClass(), "hwpx/condition/Condition_Nested_Para.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("isAsian", true);

                    put("isKorean", false);
                    put("Minjun", "민준");
                    put("Jieun", "지은");
                    put("Jihoon", "지훈");

                    put("isJapan", true);
                    put("Ken", "켄");
                    put("Hanako", "하나코");
                    put("Ichiro", "이치로");

                    put("isWestern", false);

                    put("isAmerican", true);
                    put("Smith", "스미스");
                    put("Johnson", "존슨");
                    put("Williams", "윌리엄스");

                    put("isFrench", false);
                    put("Dupont", "뒤퐁");
                    put("Dubois", "뒤부아");
                    put("Moreau", "모로");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "켄", "하나코", "이치로"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
