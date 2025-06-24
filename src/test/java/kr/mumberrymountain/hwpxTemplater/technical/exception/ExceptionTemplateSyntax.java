package kr.mumberrymountain.hwpxTemplater.technical.exception;

import kr.mumberrymountain.hwpxTemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxTemplater.exception.TemplateRenderingException;
import kr.mumberrymountain.hwpxTemplater.exception.TemplateSyntaxException;
import kr.mumberrymountain.hwpxTemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTemplateSyntax {

    @Test
    @DisplayName("1. 진행중인 If 조건문 태그가 끝나지 않은 상황에서 다른 조건문 태그가 닫힐 경우 예외 발생")
    public void templateSyntaxExceptionTest() throws Exception {

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
}
