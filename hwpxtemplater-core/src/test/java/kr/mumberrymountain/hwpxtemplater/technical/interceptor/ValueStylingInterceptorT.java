package kr.mumberrymountain.hwpxtemplater.technical.interceptor;

import kr.dogfoot.hwpxlib.object.content.header_xml.references.CharPr;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.interceptor.Interceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueInterceptor;
import kr.mumberrymountain.hwpxtemplater.interceptor.ValueStylingInterceptor;
import kr.mumberrymountain.hwpxtemplater.model.Text;
import kr.mumberrymountain.hwpxtemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.NumberFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ValueStylingInterceptorT {
    @Test
    @DisplayName("1. ValueStylingInterceptor를 이용한 포맷 및 스타일 적용")
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

        Interceptor valueStylingInterceptor = new ValueStylingInterceptor() {
            @Override
            public Text intercept(Text value, String field) {
                if (field.equals("Salary")) {
                    int salary = Integer.parseInt(value.getValue());
                    String newValue = NumberFormat.getNumberInstance(Locale.KOREA).format(salary) + "만원";
                    value.setValue(newValue);

                    if (salary >= 5000) {
                        value.bold(true);
                        value.fontColor("#DC3545");
                    } else {
                        value.fontColor("#007BFF");
                    }
                }
                return value;
            }
        };

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .interceptor(valueStylingInterceptor)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/interceptor/Interceptor_Value.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Employees", employees);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        ArrayList<Integer> charPrId = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "김하윤", " : ", "4,500만원", "이준호", " : ", "5,600만원", "박서연", " : ", "7,200만원"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());

            Run run = (Run) results[i].parentsPath().get(results[i].parentsPath().size() - 1);
            charPrId.add(Integer.parseInt(run.charPrIDRef()));
        }

        assertEquals(texts, expectedResults);

        for (int i = 0; i < charPrId.size(); i++) {
            CharPr charPr = hwpxTemplater.getFile().headerXMLFile().refList().charProperties().get(charPrId.get(i));
            System.out.println();
            if (i == 2) {
                assertEquals("#007BFF", charPr.textColor());
            } else if (i == 5 || i == 8) {
                assertEquals("#DC3545", charPr.textColor());
                assertNotNull(charPr.bold());
            }
        }
    }
}
