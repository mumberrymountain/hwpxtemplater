package kr.mumberrymountain.hwpxtemplater.technical.config;

import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.Config;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.Text;
import kr.mumberrymountain.hwpxtemplater.model.table.Col;
import kr.mumberrymountain.hwpxtemplater.model.table.Table;
import kr.mumberrymountain.hwpxtemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutoTrim {
    @Test
    @DisplayName("1. autoTrim Configuration 옵션 적용")
    public void configAutoTrimTest() throws Exception {

        ArrayList<HashMap<String, Object>> trimLoops = new ArrayList<>();

        HashMap<String, Object> trimLoop1 = new HashMap<>();
        trimLoop1.put("TrimLoop", "   반복문 공백1     ");
        HashMap<String, Object> trimLoop2 = new HashMap<>();
        trimLoop2.put("TrimLoop", "   반복문 공백2     ");
        HashMap<String, Object> trimLoop3 = new HashMap<>();
        trimLoop3.put("TrimLoop", "   반복문 공백3     ");

        trimLoops.add(trimLoop1);
        trimLoops.add(trimLoop2);
        trimLoops.add(trimLoop3);

        Table tableParam = Table.builder()
                .cols(
                        Arrays.asList(
                                new Col("공백제거").width(150)
                        )
                )
                .row(new HashMap<String, Object>() {{
                    put("공백제거", "   테이블 공백 제거1    ");
                }})
                .row(new HashMap<String, Object>() {{
                    put("공백제거", "   테이블 공백 제거 2  ");
                }})
                .create();

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("autoTrim", true)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/config/Config_AutoTrim.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("AutoTrim1", "    공백 제거1     ");
                    put("AutoTrim2", new Text("     공백 제거2     "));
                    put("AutoTrimLoop", trimLoops);
                    put("AutoTrimTable", tableParam);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "공백 제거1", "공백 제거2", "반복문 공백1", "반복문 공백2",
                "반복문 공백3", "테이블 공백 제거1", "테이블 공백 제거 2"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }
}
