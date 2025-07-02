package kr.mumberrymountain.hwpxtemplater.technical.exception;

import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.exception.TemplateRenderingException;
import kr.mumberrymountain.hwpxtemplater.exception.TemplateSyntaxException;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTemplateSyntax {

    @Test
    @DisplayName("1. 진행중인 If 조건문 태그가 끝나지 않은 상황에서 다른 조건문 태그가 닫힐 경우 TemplateSyntaxException 발생")
    public void templateSyntaxExceptionTest1() throws Exception {

        TemplateRenderingException ex = assertThrows(
                TemplateRenderingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Template_01_Syntax.hwpx"))
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

        assertEquals("Unexpected error occurred while rendering the template", ex.getMessage());
        assertEquals(TemplateSyntaxException.class, ex.getCause().getClass());
        assertEquals("Closing tag mismatch: Unexpected closing tag </IsRender2>, Expected to close </IsRender1> first", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("2. 오프닝 태그 없이 클로징 태그만 있는 경우 TemplateSyntaxException 발생")
    public void templateSyntaxExceptionTest2() throws Exception {

        TemplateRenderingException ex = assertThrows(
                TemplateRenderingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_Template_03_Syntax.hwpx"))
                            .render(new HashMap<String, String>() {{
                                put("Test1", "테스트1");
                            }});
                }
        );

        assertEquals("Unexpected error occurred while rendering the template", ex.getMessage());
        assertEquals(TemplateSyntaxException.class, ex.getCause().getClass());
        assertEquals("Unexpected closing tag 'onlyClosing'. No corresponding opening tag found.", ex.getCause().getMessage());
    }
}
