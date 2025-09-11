package kr.mumberrymountain.hwpxtemplater.issue.issue16;

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

public class Issue16 {
    @Test
    @DisplayName("1. 여러 문단에 하나 이상의 필드를 나눠 배치한 상황에서 Loop 태그를 이용하여 데이터를 렌더링할 때의 동작")
    public void issue16Test() throws Exception {
        ArrayList<HashMap<String, Object>> tests = new ArrayList<>();
        HashMap<String, Object> test1 = new HashMap<>();
        test1.put("test1", "테스트1-1");
        test1.put("test2", "테스트1-2");
        test1.put("test3", "테스트1-3");
        test1.put("test4", "테스트1-4");
        test1.put("test5", "테스트1-5");

        HashMap<String, Object> test2 = new HashMap<>();
        test2.put("test1", "테스트2-1");
        test2.put("test2", "테스트2-2");
        test2.put("test3", "테스트2-3");
        test2.put("test4", "테스트2-4");
        test2.put("test5", "테스트2-5");

        HashMap<String, Object> test3 = new HashMap<>();
        test3.put("test1", "테스트3-1");
        test3.put("test2", "테스트3-2");
        test3.put("test3", "테스트3-3");
        test3.put("test4", "테스트3-4");
        test3.put("test5", "테스트3-5");

        tests.add(test1);
        tests.add(test2);
        tests.add(test3);

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/issue/16/#16.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("tests", tests);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "테스트1: ", "테스트1-1",
                "테스트2: ", "테스트1-2",
                "테스트3: ", "테스트1-3",
                "테스트4: ", "테스트1-4",
                "테스트5: ", "테스트1-5",

                "테스트1: ", "테스트2-1",
                "테스트2: ", "테스트2-2",
                "테스트3: ", "테스트2-3",
                "테스트4: ", "테스트2-4",
                "테스트5: ", "테스트2-5",

                "테스트1: ", "테스트3-1",
                "테스트2: ", "테스트3-2",
                "테스트3: ", "테스트3-3",
                "테스트4: ", "테스트3-4",
                "테스트5: ", "테스트3-5"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
