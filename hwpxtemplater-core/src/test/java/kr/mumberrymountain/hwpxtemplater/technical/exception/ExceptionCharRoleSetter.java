package kr.mumberrymountain.hwpxtemplater.technical.exception;

import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.exception.InvalidConfigurationException;
import kr.mumberrymountain.hwpxtemplater.exception.TemplateParsingException;
import kr.mumberrymountain.hwpxtemplater.model.CharRole;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderType;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionCharRoleSetter {

    @Test
    @DisplayName("1. charRoleSetter 옵션을 CharRole 클래스로 설정하지 않은 경우 예외 발생")
    public void charRoleSetterInvalidTypeExceptionTest() throws Exception {

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("charRoleSetter", "error")
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_CharRoleSetter.hwpx"))
                            .render(new HashMap<String, String>() {{
                                put("Korea", TestUtil.getFilePath(this.getClass(),"images/country/png/korea.png"));
                                put("Spain", TestUtil.getFilePath(this.getClass(),"images/country/png/spain.png"));
                                put("Belgium", TestUtil.getFilePath(this.getClass(),"images/country/png/belgium.png"));
                            }});
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("charRoleSetter must be a CharRole", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("2. charRoleSetter 옵션으로 조건문 char을 null로 설정할 경우 예외 발생")
    public void charRoleSetterConditionCharNullExceptionTest() throws Exception {

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.CONDITION, null);

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("charRoleSetter", charRole)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_CharRoleSetter.hwpx"))
                            .render(new HashMap<String, String>() {{
                                put("Korea", TestUtil.getFilePath(this.getClass(),"images/country/png/korea.png"));
                                put("Spain", TestUtil.getFilePath(this.getClass(),"images/country/png/spain.png"));
                                put("Belgium", TestUtil.getFilePath(this.getClass(),"images/country/png/belgium.png"));
                            }});
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("charRole for placeHolder type condition must not be null", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("3. charRoleSetter 옵션으로 반복문 char을 null로 설정할 경우 예외 발생")
    public void charRoleSetterLoopCharNullExceptionTest() throws Exception {

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.LOOP, null);

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("charRoleSetter", charRole)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_CharRoleSetter.hwpx"))
                            .render(new HashMap<String, String>() {{
                                put("Korea", TestUtil.getFilePath(this.getClass(),"images/country/png/korea.png"));
                                put("Spain", TestUtil.getFilePath(this.getClass(),"images/country/png/spain.png"));
                                put("Belgium", TestUtil.getFilePath(this.getClass(),"images/country/png/belgium.png"));
                            }});
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("charRole for placeHolder type loop must not be null", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("4. charRoleSetter 옵션으로 태그 클로징 char을 null로 설정할 경우 예외 발생")
    public void charRoleSetterClosureCharNullExceptionTest() throws Exception {

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.CLOSURE, null);

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("charRoleSetter", charRole)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_CharRoleSetter.hwpx"))
                            .render(new HashMap<String, String>() {{
                                put("Korea", TestUtil.getFilePath(this.getClass(),"images/country/png/korea.png"));
                                put("Spain", TestUtil.getFilePath(this.getClass(),"images/country/png/spain.png"));
                                put("Belgium", TestUtil.getFilePath(this.getClass(),"images/country/png/belgium.png"));
                            }});
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("charRole for placeHolder type closure must not be null", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("5. charRoleSetter 옵션으로 이미지 char을 null로 설정할 경우 예외 발생")
    public void charRoleSetterImageReplacementCharNullExceptionTest() throws Exception {

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.IMAGE_REPLACEMENT, null);

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("charRoleSetter", charRole)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_CharRoleSetter.hwpx"))
                            .render(new HashMap<String, String>() {{
                                put("Korea", TestUtil.getFilePath(this.getClass(),"images/country/png/korea.png"));
                                put("Spain", TestUtil.getFilePath(this.getClass(),"images/country/png/spain.png"));
                                put("Belgium", TestUtil.getFilePath(this.getClass(),"images/country/png/belgium.png"));
                            }});
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("charRole for placeHolder type image must not be null", ex.getCause().getMessage());
    }

    @Test
    @DisplayName("6. charRoleSetter 옵션으로 테이블 char을 null로 설정할 경우 예외 발생")
    public void charRoleSetterTableReplacementCharNullExceptionTest() throws Exception {

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.TABLE_REPLACEMENT, null);

        TemplateParsingException ex = assertThrows(
                TemplateParsingException.class,
                () -> {
                    HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                            .config("charRoleSetter", charRole)
                            .parse(TestUtil.getFilePath(this.getClass(), "hwpx/exception/Exception_CharRoleSetter.hwpx"))
                            .render(new HashMap<String, String>() {{
                                put("Korea", TestUtil.getFilePath(this.getClass(),"images/country/png/korea.png"));
                                put("Spain", TestUtil.getFilePath(this.getClass(),"images/country/png/spain.png"));
                                put("Belgium", TestUtil.getFilePath(this.getClass(),"images/country/png/belgium.png"));
                            }});
                }
        );

        assertEquals("Unexpected error occurred while parsing the template", ex.getMessage());
        assertEquals(InvalidConfigurationException.class, ex.getCause().getClass());
        assertEquals("charRole for placeHolder type table must not be null", ex.getCause().getMessage());
    }
}
