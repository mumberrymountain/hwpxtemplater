package kr.mumberrymountain.hwpxtemplater.technical.loop;

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

public class LoopNestedPara {
    @Test
    @DisplayName("1. 기본 단락 조건문 내 중첩 조건문 템플릿 렌더링")
    public void loopNestedParaTextTest() throws Exception {
        ArrayList<HashMap<String, Object>> koreans = new ArrayList<>();
        HashMap<String, Object> minJun = new HashMap<>();
        minJun.put("Korean", "민준");

        HashMap<String, Object> jieun = new HashMap<>();
        jieun.put("Korean", "지은");

        HashMap<String, Object> jihoon = new HashMap<>();
        jihoon.put("Korean", "지훈");
        koreans.add(minJun);
        koreans.add(jieun);
        koreans.add(jihoon);

        ArrayList<HashMap<String, Object>> japaneses = new ArrayList<>();
        HashMap<String, Object> ken = new HashMap<>();
        ken.put("Japanese", "켄");

        HashMap<String, Object> hanako = new HashMap<>();
        hanako.put("Japanese", "하나코");

        HashMap<String, Object> ichiro = new HashMap<>();
        ichiro.put("Japanese", "이치로");

        japaneses.add(ken);
        japaneses.add(hanako);
        japaneses.add(ichiro);

        ArrayList<HashMap<String, Object>> americans = new ArrayList<>();
        HashMap<String, Object> smith = new HashMap<>();
        smith.put("American", "스미스");

        HashMap<String, Object> johnson = new HashMap<>();
        johnson.put("American", "존슨");

        HashMap<String, Object> williams = new HashMap<>();
        williams.put("American", "윌리엄스");

        americans.add(smith);
        americans.add(johnson);
        americans.add(williams);

        ArrayList<HashMap<String, Object>> frenches = new ArrayList<>();
        HashMap<String, Object> dupont = new HashMap<>();
        dupont.put("French", "뒤퐁");

        HashMap<String, Object> dubois = new HashMap<>();
        dubois.put("French", "뒤부아");

        HashMap<String, Object> moreau = new HashMap<>();
        moreau.put("French", "모로");

        frenches.add(dupont);
        frenches.add(dubois);
        frenches.add(moreau);

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath (this.getClass(), "hwpx/loop/Loop_Nested_Para.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("IsAsian", true);
                    put("Koreans", koreans);
                    put("Japaneses", japaneses);

                    put("IsWestern", false);
                    put("Americans", americans);
                    put("Frenches", frenches);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "----------", "한국인:", "민준", "지은", "지훈", "일본인:", "켄", "하나코", "이치로", "----------"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
