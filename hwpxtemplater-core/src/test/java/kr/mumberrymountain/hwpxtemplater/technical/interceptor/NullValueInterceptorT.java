package kr.mumberrymountain.hwpxtemplater.technical.interceptor;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.NullValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NullValueInterceptorT {
    @Test
    @DisplayName("1. NullValueInterceptor 기본 적용")
    public void basicNullValueInterceptorTest() throws Exception {

        Interceptor nullValueInterceptor = (NullValueInterceptor) (value, field) -> {
            if (field.equals("Null1")) return "빈 값1";
            else if (field.equals("Null2")) return "빈 값2";
            else if (field.equals("Null3")) return "빈 값3";
            return value;
        };

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .interceptor(nullValueInterceptor)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/interceptor/Interceptor_Null_Value.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Null1", null);
                    put("Null3", ""); // ""은 null이 아니며 따라서 NullValueInterceptor에 진입하지 않고 ""으로 렌더링됨
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "빈 값1", "빈 값2", ""
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("1. NullValueInterceptor LOOP 태그 내 적용")
    public void loopNullValueInterceptorTest() throws Exception {

        ArrayList<HashMap<String, Object>> names = new ArrayList<>();

        HashMap<String, Object> name1 = new HashMap<>();
        name1.put("Name", "Michael");

        HashMap<String, Object> name2 = new HashMap<>();
        name2.put("Name", null);

        HashMap<String, Object> name3 = new HashMap<>();
        name3.put("Name", "James");

        HashMap<String, Object> name4 = new HashMap<>();
        name4.put("Name", "");

        HashMap<String, Object> name5 = new HashMap<>();
        name5.put("Name", "Jessica");

        names.add(name1);
        names.add(name2);
        names.add(name3);
        names.add(name4);
        names.add(name5);

        Interceptor nullValueInterceptor = (NullValueInterceptor) (value, field) -> "Blank";

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .interceptor(nullValueInterceptor)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/interceptor/Interceptor_Null_Value_Loop.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Names", names);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "Michael", "Blank", "James", "", "Jessica"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
