package kr.mumberrymountain.hwpxTemplater.technical.interceptor;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxTemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxTemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxTemplater.interceptor.ValueInterceptor;
import kr.mumberrymountain.hwpxTemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxTemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValueInterceptorT {
    @Test
    @DisplayName("1. ValueInterceptor를 이용한 포맷 일괄 적용")
    public void basicValueInterceptorTest() throws Exception {

        ArrayList<HashMap<String, Object>> employees = new ArrayList<>();

        HashMap<String, Object> employee1 = new HashMap<>();
        employee1.put("Name", "김하윤");
        employee1.put("Salary", 4500);

        HashMap<String, Object> employee2 = new HashMap<>();
        employee2.put("Name", "이준호");
        employee2.put("Salary", 5600);

        HashMap<String, Object> employee3 = new HashMap<>();
        employee3.put("Name", "박서연");
        employee3.put("Salary", 7200);

        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Interceptor valueInterceptor = new ValueInterceptor() {
            @Override
            public String intercept(String value, String field) {
                if (field.equals("Salary")) return NumberFormat.getNumberInstance(Locale.KOREA).format(Integer.parseInt(value)) + "만원";
                return value;
            }
        };

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .interceptor(valueInterceptor)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/interceptor/Interceptor_Value.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Employees", employees);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "김하윤", " : ", "4,500만원", "이준호", " : ", "5,600만원", "박서연", " : ", "7,200만원"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
