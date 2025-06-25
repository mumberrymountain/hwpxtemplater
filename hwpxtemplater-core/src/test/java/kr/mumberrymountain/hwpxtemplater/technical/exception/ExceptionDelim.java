package kr.mumberrymountain.hwpxtemplater.technical.exception;

import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.exception.InvalidConfigurationException;
import kr.mumberrymountain.hwpxtemplater.exception.TemplateParsingException;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionDelim {

    @Test
    @DisplayName("1. delimPrefix를 null로 설정할 경우 예외 발생")
    public void delimPrefixNullExceptionTest() throws Exception {

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("delimPrefix", null)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Delim.hwpx"))
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
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("delimPrefix must not be null", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("2. delimSuffix를 null로 설정할 경우 예외 발생")
    public void delimSuffixNullExceptionTest() throws Exception {

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("delimSuffix", null)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Delim.hwpx"))
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
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("delimSuffix must not be null", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("3. delimPrefix와 delimSuffix를 동일하게 설정할 경우 예외 발생")
    public void delimPrefixDelimSuffixSameExceptionTest() throws Exception {


        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("delimPrefix", "{{")
                            .config("delimSuffix", "{{")
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Delim.hwpx"))
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
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("delimPrefix and delimSuffix must not be the same", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("3. delimPrefix를 문자열이 아닌 값으로 설정할 경우 예외 발생")
    public void delimStartNoStringExceptionTest() throws Exception {

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("delimPrefix", 11)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Delim.hwpx"))
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
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("delimPrefix must be a string", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("3. delimSuffix를 문자열이 아닌 값으로 설정할 경우 예외 발생")
    public void delimEndNoStringExceptionTest() throws Exception {

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("delimSuffix", 11)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Delim.hwpx"))
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
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("delimSuffix must be a string", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("3. delimPrefix 문자열 길이가 2보다 긴 경우 예외 발생")
    public void delimPrefixLongerThan2ExceptionText() throws Exception {

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("delimPrefix", "{{{")
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Delim.hwpx"))
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
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("delimPrefix should not be longer than 2", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("3. delimSuffix 문자열 길이가 2보다 긴 경우 예외 발생")
    public void delimEndLongerThan2ExceptionText() throws Exception {

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("delimSuffix", "{{{")
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Delim.hwpx"))
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
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("delimSuffix should not be longer than 2", ex.getCause().getMessage());
    }
}
