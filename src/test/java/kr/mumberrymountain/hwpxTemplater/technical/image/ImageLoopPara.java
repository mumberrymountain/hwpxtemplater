package kr.mumberrymountain.hwpxTemplater.technical.image;

import kr.dogfoot.hwpxlib.object.common.ObjectList;
import kr.dogfoot.hwpxlib.object.content.context_hpf.ManifestItem;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.T;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Picture;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxTemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxTemplater.util.ParaTFilter;
import kr.mumberrymountain.hwpxTemplater.util.PicFilter;
import kr.mumberrymountain.hwpxTemplater.util.TestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageLoopPara {
    @Test
    @DisplayName("1. 기본 단락 PNG 로컬 파일 이미지 Loop 템플릿을 이용한 렌더링 - 이미지 렌더링 확인")
    public void basicParaLocalPngImageLoopImageTest() throws Exception {

        // 전체 제품 리스트
        ArrayList<HashMap<String, Object>> countries = new ArrayList<>();

        // 각 제품은 Map 형태로 생성
        HashMap<String, Object> korea = new HashMap<>();
        korea.put("Country", "한국");
        korea.put("Flag", TestUtil.getFilePath(this.getClass(), "images/country/png/korea.png"));

        HashMap<String, Object> spain = new HashMap<>();
        spain.put("Country", "스페인");
        spain.put("Flag",  TestUtil.getFilePath(this.getClass(), "images/country/png/spain.png"));

        HashMap<String, Object> belgium = new HashMap<>();
        belgium.put("Country", "벨기에");
        belgium.put("Flag",  TestUtil.getFilePath(this.getClass(), "images/country/png/belgium.png"));

        countries.add(korea);
        countries.add(spain);
        countries.add(belgium);

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Loop_Para.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Countries", countries);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new PicFilter(), false);

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

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            assertEquals(manifestItem.id(), manifestIds.get(i));
            assertEquals(manifestItem.href(), hrefs.get(i));
        }
    }

    @Test
    @DisplayName("1. 기본 단락 PNG 로컬 파일 이미지 Loop 템플릿을 이용한 렌더링 - 텍스트 렌더링 확인")
    public void basicParaLocalPngImageLoopTextTest() throws Exception {

        // 전체 제품 리스트
        ArrayList<HashMap<String, Object>> countries = new ArrayList<>();

        // 각 제품은 Map 형태로 생성
        HashMap<String, Object> korea = new HashMap<>();
        korea.put("Country", "한국");
        korea.put("Flag", TestUtil.getFilePath(this.getClass(), "images/country/png/korea.png"));

        HashMap<String, Object> spain = new HashMap<>();
        spain.put("Country", "스페인");
        spain.put("Flag",  TestUtil.getFilePath(this.getClass(), "images/country/png/spain.png"));

        HashMap<String, Object> belgium = new HashMap<>();
        belgium.put("Country", "벨기에");
        belgium.put("Flag",  TestUtil.getFilePath(this.getClass(), "images/country/png/belgium.png"));

        countries.add(korea);
        countries.add(spain);
        countries.add(belgium);

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Loop_Para.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Countries", countries);
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new ParaTFilter(), false);

        ArrayList<String> texts = new ArrayList<>();
        List<String> expectedResults = Arrays.asList(
                "한국", ": ", "스페인", ": ",
                "벨기에", ": "
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
}
