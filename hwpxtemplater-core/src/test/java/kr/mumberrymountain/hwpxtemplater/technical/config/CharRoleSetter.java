package kr.mumberrymountain.hwpxtemplater.technical.config;

import kr.dogfoot.hwpxlib.object.common.ObjectList;
import kr.dogfoot.hwpxlib.object.content.context_hpf.ManifestItem;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Picture;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.table.Tc;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.table.Tr;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.ConfigOption;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.CharRole;
import kr.mumberrymountain.hwpxtemplater.model.table.Col;
import kr.mumberrymountain.hwpxtemplater.model.table.Table;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderType;
import kr.mumberrymountain.hwpxtemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxtemplater.util.PicFilter;
import kr.mumberrymountain.hwpxtemplater.util.TableFilter;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CharRoleSetter {
    @Test
    @DisplayName("1. chartRoleSetter Configuration 옵션 적용 - 조건문 태그")
    public void charRoleSetterConditionTest() throws Exception {

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.CONDITION, '+');

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("charRoleSetter", charRole)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/config/Config_CharRoleSetter_Condition.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Render1", false);
                    put("Render2", true);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "참"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("2. chartRoleSetter Configuration 옵션 적용 - 반복문 태그")
    public void charRoleSetterLoopTest() throws Exception {
        // 전체 제품 리스트
        ArrayList<HashMap<String, Object>> products = new ArrayList<>();

        // 각 제품은 Map 형태로 생성
        HashMap<String, Object> product1 = new HashMap<>();
        product1.put("Name", "Apple");
        product1.put("Price", 1500);

        HashMap<String, Object> product2 = new HashMap<>();
        product2.put("Name", "Banana");
        product2.put("Price", 700);

        HashMap<String, Object> product3 = new HashMap<>();
        product3.put("Name", "Orange");
        product3.put("Price", 1200);

        products.add(product1);
        products.add(product2);
        products.add(product3);

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.LOOP, '+');
        charRole.set(PlaceHolderType.CLOSURE, '-');

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("charRoleSetter", charRole)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/config/Config_CharRoleSetter_Loop.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Products", products);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "Apple", ", ", "1500", " 원", "Banana", ", ", "700", " 원", "Orange", ", ", "1200", " 원"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    @Test
    @DisplayName("3. chartRoleSetter Configuration 옵션 적용 - 이미지 태그")
    public void charRoleSetterImageReplacementTest() throws Exception {
        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.IMAGE_REPLACEMENT, '+');

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("charRoleSetter", charRole)
                .parse(TestUtil.getFilePath(this.getClass(),"hwpx/config/Config_CharRoleSetter_Image.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", TestUtil.getFilePath(this.getClass(),"images/country/png/korea.png"));
                    put("Spain", TestUtil.getFilePath(this.getClass(),"images/country/png/spain.png"));
                    put("Belgium", TestUtil.getFilePath(this.getClass(),"images/country/png/belgium.png"));
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new PicFilter(), false);
        assertTrue(results.length > 0);

        List<String> secIds = Arrays.asList(
                "korea", "spain", "belgium"
        );

        for (int i = 0; i < results.length; i++) {
            Picture picture = (Picture) results[i].thisObject();

            validateImageWidthAndHeight(picture, 1200, 1200);
            assertEquals(picture.img().binaryItemIDRef(), secIds.get(i));
        }

        ObjectList<ManifestItem> manifest = hwpxTemplater.getFile().contentHPFFile().manifest();
        ArrayList<ManifestItem> imageManifestItem = new ArrayList<>();
        for (ManifestItem manifestItem : manifest.items()) {
            if(manifestItem.mediaType().equals("image/PNG")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> manifestIds = Arrays.asList(
                "belgium", "spain",  "korea"
        );

        List<String> hrefs = Arrays.asList(
                "BinData/belgium.png", "BinData/spain.png",  "BinData/korea.png"
        );

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            assertEquals(manifestItem.id(), manifestIds.get(i));
            assertEquals(manifestItem.href(), hrefs.get(i));
        }
    }

    @Test
    @DisplayName("4. chartRoleSetter Configuration 옵션 적용 - 테이블 태그")
    public void charRoleSetterTableReplacementTest() throws Exception {
        Table tableParam = Table.builder()
                .cols(
                        Arrays.asList(
                                new Col("번호").width(60),
                                new Col("이름").width(150),
                                new Col("점수").width(150),
                                new Col("비고").width(150)
                        )
                )
                .row(new HashMap<String, Object>() {{
                    put("번호", "번호");
                    put("이름", "이름");
                    put("점수", "점수");
                    put("비고", "비고");
                }})
                .row(new HashMap<String, Object>() {{
                    put("번호", 1);
                    put("이름", "홍길동");
                    put("점수", 85);
                    put("비고", null);
                }})
                .row(new HashMap<String, Object>() {{
                    put("번호", 2);
                    put("이름", "김철수");
                    put("점수", 	90);
                    put("비고", "우수 성적");
                }})
                .row(new HashMap<String, Object>() {{
                    put("번호", 3);
                    put("이름", "이영희");
                    put("점수", 78);
                }})
                .create();

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.TABLE_REPLACEMENT, '+');

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config("charRoleSetter", charRole)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/config/Config_CharRoleSetter_Table.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Table", tableParam);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new TableFilter(), false);

        List result = Arrays.asList(
                Arrays.asList("번호", "이름", "점수", "비고"),
                Arrays.asList("1", "홍길동", "85", ""),
                Arrays.asList("2", "김철수", "90", "우수 성적"),
                Arrays.asList("3", "이영희", "78", "")
        );

        for (int i = 0; i < results.length; i++) {
            kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Table table
                    = (kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Table) results[i].thisObject();
            assertEquals(getTableText(table), result);
        }
    }

    @Test
    @DisplayName("1. chartRoleSetter Configuration 옵션 적용 - config ConfigOption Enum으로 설정")
    public void charRoleSetterConditionTestConfigOptionEnum() throws Exception {

        CharRole charRole = new CharRole();
        charRole.set(PlaceHolderType.CONDITION, '+');

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .config(ConfigOption.CHAR_ROLE_SETTER, charRole)
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/config/Config_CharRoleSetter_Condition.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Render1", false);
                    put("Render2", true);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);
        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "참"
        );

        for (int i = 0; i < results.length; i++) {
            T text = (T) results[i].thisObject();
            texts.add(text.onlyText());
        }

        assertEquals(texts, expectedResults);
    }

    private void validateImageWidthAndHeight(Picture picture, int width, int height) {
        assertEquals(picture.sz().width(), width);
        assertEquals(picture.sz().height(), height);

        assertEquals(picture.orgSz().width(), width);
        assertEquals(picture.orgSz().height(), height);

        assertEquals(picture.imgRect().pt0().x(), 0);
        assertEquals(picture.imgRect().pt0().y(), 0);

        assertEquals(picture.imgRect().pt1().x(), width);
        assertEquals(picture.imgRect().pt1().y(), 0);

        assertEquals(picture.imgRect().pt2().x(), width);
        assertEquals(picture.imgRect().pt2().y(), height);

        assertEquals(picture.imgRect().pt3().x(), 0);
        assertEquals(picture.imgRect().pt3().y(), height);
    }

    private ArrayList getTableText(kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Table table){
        ArrayList tableText = new ArrayList();

        table.trs().forEach((tr) -> {
            tableText.add(getRowText(tr));
        });

        return tableText;
    }

    private ArrayList getRowText(Tr tr){
        ArrayList rowText = new ArrayList();

        tr.tcs().forEach((tc) -> {
            setCellText(tc, rowText);
        });

        return rowText;
    }

    private void setCellText(Tc tc, ArrayList rowText) {
        tc.subList().paras().forEach((para) -> {
            para.runs().forEach((run -> {
                run.runItems().forEach((runItem -> {
                    rowText.add(((T) runItem).onlyText());
                }));
            }));
        });
    }
}
