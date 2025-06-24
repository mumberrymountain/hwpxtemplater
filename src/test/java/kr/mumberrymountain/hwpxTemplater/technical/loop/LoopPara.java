package kr.mumberrymountain.hwpxTemplater.technical.loop;

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

public class LoopPara {

    @Test
    @DisplayName("1. 기본 단락 반복문 템플릿 렌더링")
    public void loopParaTextTest() throws Exception {
        // 전체 제품 리스트
        ArrayList<HashMap<String, Object>> products = new ArrayList<>();

        // 각 제품은 Map 형태로 생성
        HashMap<String, Object> product1 = new HashMap<>();
        product1.put("Name", "Apple");
        product1.put("Price", 1500);

        HashMap<String, Object> product2 = new HashMap<>();
        product2.put("Name", "Banana");
        product2.put("Price", 700);

        HashMap<String, Object> product3 = new HashMap<>();
        product3.put("Name", "Orange");
        product3.put("Price", 1200);

        products.add(product1);
        products.add(product2);
        products.add(product3);

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/loop/Loop_Para_Text.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Products", products);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "Apple", ", ", "1500", " 원", "Banana", ", ", "700", " 원", "Orange", ", ", "1200", " 원"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
