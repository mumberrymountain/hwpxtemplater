package kr.mumberrymountain.hwpxTemplater.technical.basic;

import kr.dogfoot.hwpxlib.object.content.header_xml.references.CharPr;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Run;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxTemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxTemplater.model.Text;
import kr.mumberrymountain.hwpxTemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxTemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasicPara {
    @Test
    @DisplayName("1. 기본 단락 템플릿 렌더링")
    public void basicParaTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_01_Text.hwpx"))
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

    @Test
    @DisplayName("2. 기본 단락 템플릿 렌더링시 글자 스타일 유지")
    public void basicParaFontStyleTest() throws Exception {
        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_02_Style.hwpx"))
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
                    put("Test10", "테스트10");
                    put("Test11", "테스트11");
                    put("Test12", "테스트12");
                    put("Test13", "테스트13");
                    put("Test14", "테스트14");
                    put("Test15", "테스트15");
                    put("Test16", "테스트16");
                    put("Test17", "테스트17");
                    put("Test18", "테스트18");
                    put("Test19", "테스트19");
                    put("Test20", "테스트20");
                    put("Test21", "테스트21");
                    put("Test22", "테스트22");
                    put("Test23", "테스트23");
                    put("Test24", "테스트24");
                    put("Test25", "테스트25");
                    put("Test26", "테스트26");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        ArrayList<String> textStyles = new ArrayList<>();
        List<String> expectedTextResults = Arrays.asList(
                "테스트1", "테스트2", "테스트3", "테스트4", "테스트5", "테스트6", "테스트7", "테스트8", "테스트9", "테스트10",
                "테스트11", "테스트12", "테스트13", "테스트14", "테스트14", "테스트14", "테스트15", "테스트15", "테스트15", "테스트16",
                "테스트16", "테스트16", "테스트17", "테스트17", "테스트17", "테스트18", "테스트18", "테스트18", "테스트19", "테스트19",
                "테스트19", "테스트20", "테스트20", "테스트20", "테스트21", "테스트21", "테스트21", "테스트22", "테스트22", "테스트22",
                "테스트23", "테스트23", "테스트23", "테스트24", "테스트24", "테스트24", "테스트25", "테스트25", "테스트25", "테스트26",
                "테스트26", "테스트26"
        );

        List<String> expectedTextStyleResults = Arrays.asList(
                "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
                "18", "19", "7", "22", "20", "21", "25", "23", "24", "7",
                "10", "7", "7", "11", "7", "7", "12", "7", "7", "13",
                "7", "7", "14", "7", "7", "15", "7", "7", "16", "7",
                "7", "17", "7", "7", "18", "7", "7", "19", "7", "7",
                "7", "7"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            Run run = (Run) results[i].parentsPath().get(3);
            texts.add(text.onlyText());
            textStyles.add(run.charPrIDRef());
        }

        assertEquals(texts, expectedTextResults);
        assertEquals(textStyles, expectedTextStyleResults);
    }

    @Test
    @DisplayName("3. Tab 키 스페이스가 기입된 기본 단락 템플릿 렌더링")
    public void basicParaTextWithTabTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_03_Text_With_Tab.hwpx"))
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
                null, "테스트1", null, "테스트_", "테스트2", "_테스트",
                null, "테스트3", null, "테스트4", null, "테스트5", null, "테스트6",
                null, "테스트7", null, "테스트8", null, "테스트9"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("4. 특수문자가 포함된 Value 값 템플릿 렌더링")
    public void basicParaTextSpecialCharTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_04_Special_Char.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Test1", "~테스트1~");
                    put("Test2", "`테스트2`");
                    put("Test3", "!테스트3!");
                    put("Test4", "@테스트4@");
                    put("Test5", "#테스트5#");
                    put("Test6", "$테스트6$");
                    put("Test7", "%테스트7%");
                    put("Test8", "^테스트8^");
                    put("Test9", "&테스트9&");
                    put("Test10", "*테스트10*");
                    put("Test11", "(테스트11(");
                    put("Test12", ")테스트12)");
                    put("Test13", "-테스트13-");
                    put("Test14", "_테스트14_");
                    put("Test15", "+테스트15+");
                    put("Test16", "=테스트16=");
                    put("Test17", "{테스트17{");
                    put("Test18", "}테스트18}");
                    put("Test19", "[테스트19[");
                    put("Test20", "]테스트20]");
                    put("Test21", ";테스트21;");
                    put("Test22", ":테스트22:");
                    put("Test23", "'테스트23'");
                    put("Test24", "\"테스트24\"");
                    put("Test25", "<테스트25<");
                    put("Test26", ">테스트26>");
                    put("Test27", ",테스트27,");
                    put("Test28", ".테스트28.");
                    put("Test29", "?테스트29?");
                    put("Test30", "/테스트30/");
                    put("Test31", "\\테스트31\\");
                    put("Test32", "|테스트32|");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "~테스트1~", "`테스트2`", "!테스트3!", "@테스트4@", "#테스트5#", "$테스트6$", "%테스트7%", "^테스트8^","&테스트9&", "*테스트10*",
                "(테스트11(", ")테스트12)", "-테스트13-", "_테스트14_", "+테스트15+", "=테스트16=", "{테스트17{", "}테스트18}","[테스트19[", "]테스트20]",
                ";테스트21;", ":테스트22:", "'테스트23'", "\"테스트24\"", "<테스트25<", ">테스트26>", ",테스트27,", ".테스트28.","?테스트29?", "/테스트30/",
                "\\테스트31\\", "|테스트32|"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("5. 머릿말에 템플릿 설정된 경우 템플릿 렌더링")
    public void basicParaPrefaceTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_05_Text_With_Preface.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Preface", "머리말");
                    put("Body", "본문");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "머리말", "본문"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("6. 꼬리말에 템플릿 설정된 경우 템플릿 렌더링")
    public void basicParaFooterTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_06_Text_With_Footer.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Footer", "꼬리말");
                    put("Body", "본문");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "꼬리말", "본문"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("7. 각주에 템플릿 설정된 경우 템플릿 렌더링")
    public void basicParaFootNoteTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_07_Text_With_Footnote.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Footnote", "각주");
                    put("Body", "본문");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
               " ", "각주", " ", "본문"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("8. 미주에 템플릿 설정된 경우 템플릿 렌더링")
    public void basicParaEndNoteTextTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_08_Text_With_Endnote.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Endnote", "미주");
                    put("Body", "본문");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                " ", "미주", " ", "본문"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("9. 기본 단락 Text 모델을 활용한 템플릿 렌더링")
    public void basicParaTextObjTest() throws Exception {
        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/basic/Basic_Para_09_Text_Obj.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Test1", new Text("테스트1"));
                    put("Test2", new Text("테스트2").fontColor("#9C3B00"));
                    put("Test3", new Text("테스트3").backgroundColor("#FFEF99"));
                    put("Test4", new Text("테스트4").fontFamily("Noto Sans KR"));
                    put("Test5", new Text("테스트5").fontSize(20));
                    put("Test6", new Text("테스트6").fontColor("#9C3B00").backgroundColor("#FFEF99"));
                    put("Test7", new Text("테스트7").fontColor("#9C3B00").fontFamily("Noto Sans KR"));
                    put("Test8", new Text("테스트8").fontColor("#9C3B00").fontSize(20));
                    put("Test9", new Text("테스트9").backgroundColor("#FFEF99").fontFamily("Noto Sans KR"));
                    put("Test10", new Text("테스트10").backgroundColor("#FFEF99").fontSize(20));
                    put("Test11", new Text("테스트11").fontFamily("Noto Sans KR").fontSize(20));
                    put("Test12", new Text("테스트12").fontColor("#9C3B00").backgroundColor("#FFEF99").fontFamily("Noto Sans KR"));
                    put("Test13", new Text("테스트13").fontColor("#9C3B00").backgroundColor("#FFEF99").fontSize(20));
                    put("Test14", new Text("테스트14").fontColor("#9C3B00").fontFamily("Noto Sans KR").fontSize(20));
                    put("Test15", new Text("테스트15").backgroundColor("#FFEF99").fontFamily("Noto Sans KR").fontSize(20));
                    put("Test16", new Text("테스트16").fontColor("#9C3B00").backgroundColor("#FFEF99").fontFamily("Noto Sans KR").fontSize(20));
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        ArrayList<Integer> charPrId = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "테스트1", "테스트2", "테스트3", "테스트4", "테스트5", "테스트6", "테스트7", "테스트8",
                "테스트9", "테스트10", "테스트11", "테스트12", "테스트13", "테스트14", "테스트15", "테스트16"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());

            Run run = (Run) results[i].parentsPath().get(results[i].parentsPath().size() - 1);
            charPrId.add(Integer.parseInt(run.charPrIDRef()));
        }
        assertEquals(texts, expectedResults);

        List<Integer> heightResults = Arrays.asList(
                975, 975, 975, 975, 1500, 975, 975, 1500,
                975, 1500, 1500, 975, 1500, 1500, 1500, 1500
        );

        List<String> textColorResults = Arrays.asList(
                "#000000", "#9C3B00", "#000000", "#000000", "#000000", "#9C3B00", "#9C3B00", "#9C3B00",
                "#000000", "#000000", "#000000", "#9C3B00", "#9C3B00", "#9C3B00", "#000000", "#9C3B00"
        );

        List<String> shadeColorResults = Arrays.asList(
                "None", "None", "#FFEF99", "None", "None", "#FFEF99", "None", "None",
                "#FFEF99", "#FFEF99", "None", "#FFEF99", "#FFEF99", "None", "#FFEF99", "#FFEF99"
        );

        for (int i = 0; i < charPrId.size(); i++) {
            CharPr charPr = hwpxTemplater.getFile().headerXMLFile().refList().charProperties().get(charPrId.get(i));
            assertEquals(charPr.height(), heightResults.get(i));
            assertEquals(charPr.textColor(), textColorResults.get(i));
            assertEquals(charPr.shadeColor(), shadeColorResults.get(i));
        }
    }
}
