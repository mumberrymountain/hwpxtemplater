package kr.mumberrymountain.hwpxtemplater.technical.image;

import kr.dogfoot.hwpxlib.object.common.ObjectList;
import kr.dogfoot.hwpxlib.object.content.context_hpf.ManifestItem;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.object.Picture;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.HWPXTemplater;
import kr.mumberrymountain.hwpxtemplater.model.Image;
import kr.mumberrymountain.hwpxtemplater.util.PicFilter;
import kr.mumberrymountain.hwpxtemplater.util.TestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ImagePara {
    @Test
    @DisplayName("1. 기본 단락 PNG 로컬 파일 이미지 렌더링")
    public void imageParaLocalPngImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", TestUtil.getFilePath(this.getClass(), "images/country/png/korea.png"));
                    put("Spain", TestUtil.getFilePath(this.getClass(), "images/country/png/spain.png"));
                    put("Belgium", TestUtil.getFilePath(this.getClass(), "images/country/png/belgium.png"));
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
    @DisplayName("2. 기본 단락 JPG 로컬 파일 이미지 렌더링")
    public void imageParaLocalJpgImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", TestUtil.getFilePath(this.getClass(), "images/country/jpg/korea.jpg"));
                    put("Spain", TestUtil.getFilePath(this.getClass(), "images/country/jpg/spain.jpg"));
                    put("Belgium", TestUtil.getFilePath(this.getClass(), "images/country/jpg/belgium.jpg"));
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
            if(manifestItem.mediaType().equals("image/JPEG")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> manifestIds = Arrays.asList(
                "spain",  "korea", "belgium"
        );

        List<String> hrefs = Arrays.asList(
                "BinData/spain.jpg", "BinData/korea.jpg", "BinData/belgium.jpg"
        );

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            assertEquals(manifestItem.id(), manifestIds.get(i));
            assertEquals(manifestItem.href(), hrefs.get(i));
        }
    }

    @Test
    @DisplayName("3. 기본 단락 GIF 로컬 파일 이미지 렌더링")
    public void imageParaLocalGifImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", TestUtil.getFilePath(this.getClass(), "images/country/gif/korea.gif"));
                    put("Spain", TestUtil.getFilePath(this.getClass(), "images/country/gif/spain.gif"));
                    put("Belgium", TestUtil.getFilePath(this.getClass(), "images/country/gif/belgium.gif"));
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
            if(manifestItem.mediaType().equals("image/GIF")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> manifestIds = Arrays.asList(
                "belgium", "spain",  "korea"
        );

        List<String> hrefs = Arrays.asList(
                "BinData/belgium.gif", "BinData/spain.gif",  "BinData/korea.gif"
        );

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            assertEquals(manifestItem.id(), manifestIds.get(i));
            assertEquals(manifestItem.href(), hrefs.get(i));
        }
    }

    @Test
    @DisplayName("4. 기본 단락 BMP 로컬 파일 이미지 렌더링")
    public void imageParaLocalBmpImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", TestUtil.getFilePath(this.getClass(), "images/country/bmp/korea.bmp"));
                    put("Spain", TestUtil.getFilePath(this.getClass(), "images/country/bmp/spain.bmp"));
                    put("Belgium", TestUtil.getFilePath(this.getClass(), "images/country/bmp/belgium.bmp"));
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
            if(manifestItem.mediaType().equals("image/BMP")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> manifestIds = Arrays.asList(
                "belgium", "korea", "spain"
        );

        List<String> hrefs = Arrays.asList(
                "BinData/belgium.bmp", "BinData/korea.bmp", "BinData/spain.bmp"
        );

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            assertEquals(manifestItem.id(), manifestIds.get(i));
            assertEquals(manifestItem.href(), hrefs.get(i));
        }
    }

    @Test
    @DisplayName("5. 기본 단락 PNG Base64 이미지 렌더링")
    public void imageParaBase64PngImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAsTAAALEwEAmpwYAAABoElEQVQ4jX1Ty0pCURS1oEbVh6SDiuo/pEkE5jTwAZHeNGgQaJikqSk6chTUoEHjVGiSYcM0H9RMBZ308C0Yq7OP14sdvS1YcB57rbv3PvtqNAIA6BgDjDnGlsycfKYT48eF84wRxh+oY8B4SbHTxKl/hCKSf0zkL6NQKKDVaqmq2u028vn8aBsar3nQ6/VgtVpht9tRLpcnxNVqFTabDV6vF/1+f1SOlgyCtItEIjCbzZAkiQs+E48o7hygZDzE10OGn8XjcZhMJh4rw08Gr7TqdruIxWKo1+v4YOL00hrSCytDLq5yE7qjmE6nMzLIkkFTTPfOFVfEPr0D1t0zvOydTGtLgwwa4qnx+Ab7Bg/0Bj806w7OU8/tNINvpQRKKxqNolarIfX8jpkNpyKe3XTi/qnE7yhGLOGCVuFwGC6XS2kimWwfXWNLukIi86Y00e1281gZ52SgpSehp7FYLKrPWKlU+J3wjMujWaDxRLFY5MOiBkqdhk1GQBzlpKpyEgnGuWn/Q0hOTQ10F5wQC0bUEz9jFsMZacprn1LzGH4B/DgM50F4T3YAAAAASUVORK5CYII=");
                    put("Spain", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAABbUlEQVR42mNAB8f4ZFSO8Mj1H+WRO3OUW+4dCIPYQNwHkmPABfYzMLCAFAE1/wXS/7FhqFwvSC2K5jMMxqxAyd1wxYTxLhRDjvDI9hDShOka2W6w5v/HRFX+HhL9+++w6H9SMFDPv/+HRNQY/h4W7UOW+LBT/P+t5ZL/by6T+P95tzheQ4B6e0EGnIUJfNsp+v9On9j/jQVC/9fnC/+/OUH0/9edIvhccRpkwHuYwPnlWv/PFIr+Xxko/P/ksun/r25N+X9+mRY+F7yDG/DngOj/Qw12/3dESf5v8jT4v6Ft+v8L6+qAYvb//x7Eb8BZqHP+H+v2+n99muj/1Wka/+e6af0/lSH2/1iXFyEvIAJx52Ln/w/mS/w/1a3yf3+Jzv+b7RL/dwHF8Abi/yOiypREIywhdZOakI5yy3ahJGVQ8iTBgJ2QpIyZmXoJZSZQsgdrxgWO8Mkqgw3ilr0G1PANhIHOvQrSCJJDVw8Aeub+zHVtrmAAAAAASUVORK5CYII=");
                    put("Belgium", "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAACXBIWXMAAAsTAAALEwEAmpwYAAAA40lEQVQ4jWNgwAS6Z7dbN/6853b1xz23ryAMYv/c79r4VsNSB4t6OGAH4mlA/O/KPptjP++7/0fG34+6HHutafH3tab55FsqHuzYNO8D4v8gjMuAN5oW/8FYw3wPuiHTYJqJMgCMzafANOsD8V9SDQB5BxYmU5E1E+8CoCEa5pNABlwn3wCLqyADvpFrwBsNi68gA75SYMAXkAHXKPDCFZABUygNRD2yo1HTVBuWFqaSboD5ZPSkvIdYA4C27/qvpcWGLT+ATP2LNzNpWEzEphkZaJ/ZYdWEnp2/7nNpQvYzDAAArcHYaYwD20EAAAAASUVORK5CYII=");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new PicFilter(), false);
        assertTrue(results.length > 0);

        List<String> secIds = new ArrayList<>();

        for (int i = 0; i < results.length; i++) {
            Picture picture = (Picture) results[i].thisObject();

            validateImageWidthAndHeight(picture, 1200, 1200);
            secIds.add(picture.img().binaryItemIDRef());
        }

        ObjectList<ManifestItem> manifest = hwpxTemplater.getFile().contentHPFFile().manifest();
        ArrayList<ManifestItem> imageManifestItem = new ArrayList<>();
        for (ManifestItem manifestItem : manifest.items()) {
            if(manifestItem.mediaType().equals("image/PNG")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> hrefs = new ArrayList<>();
        for (String secId : secIds) {
            hrefs.add("BinData/" + secId + ".PNG");
        }

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            Assertions.assertTrue(secIds.contains(manifestItem.id()));
            Assertions.assertTrue(hrefs.contains(manifestItem.href()));
        }
    }

    @Test
    @DisplayName("5. 기본 단락 Jpg Base64 이미지 렌더링")
    public void imageParaBase64JpgImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD//gAfQ29tcHJlc3NlZCBieSBqcGVnLXJlY29tcHJlc3P/2wCEAAQEBAQEBAQEBAQGBgUGBggHBwcHCAwJCQkJCQwTDA4MDA4MExEUEA8QFBEeFxUVFx4iHRsdIiolJSo0MjRERFwBBAQEBAQEBAQEBAYGBQYGCAcHBwcIDAkJCQkJDBMMDgwMDgwTERQQDxAUER4XFRUXHiIdGx0iKiUlKjQyNEREXP/CABEIABAAEAMBIgACEQEDEQH/xAAXAAADAQAAAAAAAAAAAAAAAAAAAQMI/9oACAEBAAAAANh1F//EABQBAQAAAAAAAAAAAAAAAAAAAAL/2gAIAQIQAAAAf//EABQBAQAAAAAAAAAAAAAAAAAAAAP/2gAIAQMQAAAAT//EACIQAAEEAgEEAwAAAAAAAAAAAAMBAgQRBQYABxIhMiIjof/aAAgBAQABPwDZt6Zp8nIHzmKKHBxognpPtHqWSV9IAQ0u15rG7j2iTAkYjGGLhjxTPWavwcCQJ1KEo3V2rS+Ku+bZoY9vkzgZzLHfg5EUbGQR/W8MkbrQ4SNpW+Oah0+jaUuOha/kSgwMWMVFhezjSCvtxivd7flc/8QAGxEAAgEFAAAAAAAAAAAAAAAAERIAAQIhMnH/2gAIAQIBAT8AFiF6udRgdn//xAAcEQACAgIDAAAAAAAAAAAAAAAREwESAAIDIVH/2gAIAQMBAT8AvxKjRMsJZfoeVGf/2Q==");
                    put("Spain", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD//gAfQ29tcHJlc3NlZCBieSBqcGVnLXJlY29tcHJlc3P/2wCEAAQEBAQEBAQEBAQGBgUGBggHBwcHCAwJCQkJCQwTDA4MDA4MExEUEA8QFBEeFxUVFx4iHRsdIiolJSo0MjRERFwBBAQEBAQEBAQEBAYGBQYGCAcHBwcIDAkJCQkJDBMMDgwMDgwTERQQDxAUER4XFRUXHiIdGx0iKiUlKjQyNEREXP/CABEIABAAEAMBIgACEQEDEQH/xAAVAAEBAAAAAAAAAAAAAAAAAAAFBv/aAAgBAQAAAAC/QFW//8QAFAEBAAAAAAAAAAAAAAAAAAAAB//aAAgBAhAAAAAj/8QAFAEBAAAAAAAAAAAAAAAAAAAAB//aAAgBAxAAAABa/8QAIxAAAQQBAwQDAAAAAAAAAAAAAQIDBAUGAAcSERNBUSFCYf/aAAgBAQABPwDc6BbZXchM3M340B1wtw65iKpSAkfZYS4OZ8knW3ePZBg9nFfj5i7KqlkB+C4we0tB8o6uHgr9Gt1XLPGbruzsQlPQWlBcOyjSyhPH0oho8FfPQjW299fZpPhRYuHuxqhoJD092Qe2lCPCerY5rPoa/8QAHhEAAQMEAwAAAAAAAAAAAAAAAQIDEwAEERIGIWH/2gAIAQIBAT8AdHOpWCytZupDKFBvTXPWPK//xAAdEQAABQUAAAAAAAAAAAAAAAAAEhMVFiNEY6Hx/9oACAEDAQE/AKkft2lLF0+x/9k=");
                    put("Belgium", "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD//gAfQ29tcHJlc3NlZCBieSBqcGVnLXJlY29tcHJlc3P/2wCEAAQEBAQEBAQEBAQGBgUGBggHBwcHCAwJCQkJCQwTDA4MDA4MExEUEA8QFBEeFxUVFx4iHRsdIiolJSo0MjRERFwBBAQEBAQEBAQEBAYGBQYGCAcHBwcIDAkJCQkJDBMMDgwMDgwTERQQDxAUER4XFRUXHiIdGx0iKiUlKjQyNEREXP/CABEIABAAEAMBIgACEQEDEQH/xAAVAAEBAAAAAAAAAAAAAAAAAAAHBP/aAAgBAQAAAAChSLlH/8QAFAEBAAAAAAAAAAAAAAAAAAAABv/aAAgBAhAAAAAH/8QAFAEBAAAAAAAAAAAAAAAAAAAABv/aAAgBAxAAAABt/8QAHhAAAQQCAwEAAAAAAAAAAAAAAgEDBAUGBwARQUL/2gAIAQEAAT8A13uG/wBpbtyUnZ7oY5FqZg1kAC6ZFsJLQC6Y/Tp8vc6tMPz2rVuSZVjsRhJUYl7bUScNFNE8NOa807kGrN3ZMD0B08dk1MwqyeA9sk2choxaMvl0eX2DWmYZ7VoEYxrGojCypJJ0CCLhqoIvprz/xAAeEQAABAcAAAAAAAAAAAAAAAAAAgMTBBESFBYxcf/aAAgBAgEBPwCMezNNidViXXR//8QAHxEAAAQHAQAAAAAAAAAAAAAAAxESFQAGExQhUWFy/9oACAEDAQE/AJUbSHdkWlEdavOC7qP/2Q==");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new PicFilter(), false);
        assertTrue(results.length > 0);

        List<String> secIds = new ArrayList<>();

        for (int i = 0; i < results.length; i++) {
            Picture picture = (Picture) results[i].thisObject();

            validateImageWidthAndHeight(picture, 1200, 1200);
            secIds.add(picture.img().binaryItemIDRef());
        }

        ObjectList<ManifestItem> manifest = hwpxTemplater.getFile().contentHPFFile().manifest();
        ArrayList<ManifestItem> imageManifestItem = new ArrayList<>();
        for (ManifestItem manifestItem : manifest.items()) {
            if(manifestItem.mediaType().equals("image/JPEG")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> hrefs = new ArrayList<>();
        for (String secId : secIds) {
            hrefs.add("BinData/" + secId + ".JPEG");
        }

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            Assertions.assertTrue(secIds.contains(manifestItem.id()));
            Assertions.assertTrue(hrefs.contains(manifestItem.href()));
        }
    }

    @Test
    @DisplayName("6. 기본 단락 Gif Base64 이미지 렌더링")
    public void imageParaBase64GifImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", "data:image/gif;base64,R0lGODlhEAAQAPU2AAAAAAA0eAE1eQI2eWtra2xsbG1tbW5ubnR0dHV1dXZ2dsYMMMYNMcYOMo1ReNhac9lfd1Fdjm5eiXFdiFN2pFh6p199qNJogYSEhIWFhYqKipSUlJWVlZeXl5iYmJ6enrCEnoaIrO67xe+7xbrH2bvJ273K3NbW1tfX19jY2PDAyePj4+Tk5OXl5ejo6Onp6fT09PX19fb29vf39/j4+Pn5+f///wAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAAAAIf8LWE1QIERhdGFYTVA8P3hwYWNrZXQgYmVnaW49J++7vycgaWQ9J1c1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCc/Pgo8eDp4bXBtZXRhIHhtbG5zOng9J2Fkb2JlOm5zOm1ldGEvJyB4OnhtcHRrPSdJbWFnZTo6RXhpZlRvb2wgMTIuNDAnPgo8cmRmOlJERiB4bWxuczpyZGY9J2h0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMnPgoKIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PScnCiAgeG1sbnM6dGlmZj0naHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8nPgogIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6T3JpZW50YXRpb24+CiA8L3JkZjpEZXNjcmlwdGlvbj4KPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKPD94cGFja2V0IGVuZD0ndyc/PgH//v38+/r5+Pf29fTz8vHw7+7t7Ovq6ejn5uXk4+Lh4N/e3dzb2tnY19bV1NPS0dDPzs3My8rJyMfGxcTDwsHAv769vLu6ubi3trW0s7KxsK+urayrqqmop6alpKOioaCfnp2cm5qZmJeWlZSTkpGQj46NjIuKiYiHhoWEg4KBgH9+fXx7enl4d3Z1dHNycXBvbm1sa2ppaGdmZWRjYmFgX15dXFtaWVhXVlVUU1JRUE9OTUxLSklIR0ZFRENCQUA/Pj08Ozo5ODc2NTQzMjEwLy4tLCsqKSgnJiUkIyIhIB8eHRwbGhkYFxYVFBMSERAPDg0MCwoJCAcGBQQDAgEAACwAAAAAEAAQAAAGb0CAUGgrGodIgHF5RDKftmERBXvCUEUirZBYLVkJDS1a5HBtowdEZfsQOEuY5zVq2Buql6f6BNkdEhdQRhYTEQICIVAwHC8miIgkLxx8ZRgJNiYVFCVtGHBkW11fYWNENimVRldZp4OtUq9JsUtJQQA7");
                    put("Spain", "data:image/gif;base64,R0lGODlhEAAQAPUAAAAAAMQLHcULHcULHsQMHcQMHsUMHqlRE7haGa9vE7FwEp1GKoJJMKtmKL90LMpoFsxxFdyOFtmHGNeWFdmQFeCfGNmmGNqnGfC5F/O7F/W5FPa5FcqLJMKAPsKAP8+mKs+nKvzAFf3BFf3CFf3DFf7DFbmjQ7qjQ8aKSsaLStW1ZNCufrCGl8mmlwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAAAAIf8LWE1QIERhdGFYTVA8P3hwYWNrZXQgYmVnaW49J++7vycgaWQ9J1c1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCc/Pgo8eDp4bXBtZXRhIHhtbG5zOng9J2Fkb2JlOm5zOm1ldGEvJyB4OnhtcHRrPSdJbWFnZTo6RXhpZlRvb2wgMTIuNDAnPgo8cmRmOlJERiB4bWxuczpyZGY9J2h0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMnPgoKIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PScnCiAgeG1sbnM6dGlmZj0naHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8nPgogIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6T3JpZW50YXRpb24+CiA8L3JkZjpEZXNjcmlwdGlvbj4KPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKPD94cGFja2V0IGVuZD0ndyc/PgH//v38+/r5+Pf29fTz8vHw7+7t7Ovq6ejn5uXk4+Lh4N/e3dzb2tnY19bV1NPS0dDPzs3My8rJyMfGxcTDwsHAv769vLu6ubi3trW0s7KxsK+urayrqqmop6alpKOioaCfnp2cm5qZmJeWlZSTkpGQj46NjIuKiYiHhoWEg4KBgH9+fXx7enl4d3Z1dHNycXBvbm1sa2ppaGdmZWRjYmFgX15dXFtaWVhXVlVUU1JRUE9OTUxLSklIR0ZFRENCQUA/Pj08Ozo5ODc2NTQzMjEwLy4tLCsqKSgnJiUkIyIhIB8eHRwbGhkYFxYVFBMSERAPDg0MCwoJCAcGBQQDAgEAACwAAAAAEAAQAAAGV0CAUEggDAbFoRJQbDoJy6cUSpxKhaWsdrsFbDGXC4ZL3kQUCcqGvAVBDi0ViK3tIBisVYeeTU0aCw8pfCUmFRwOEiZsXoRaVVZNA0ORTVGRS5RIRlRDQQA7");
                    put("Belgium", "data:image/gif;base64,R0lGODlhEAAQAPQAAAAAAAAAAOsoN+spN+woN+wpN+soOOspOOwoOOwpOM23O8y4OtS+PPW+RPm/RffFRPjeRvneRvnfRwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACH5BAEAAAAAIf8LWE1QIERhdGFYTVA8P3hwYWNrZXQgYmVnaW49J++7vycgaWQ9J1c1TTBNcENlaGlIenJlU3pOVGN6a2M5ZCc/Pgo8eDp4bXBtZXRhIHhtbG5zOng9J2Fkb2JlOm5zOm1ldGEvJyB4OnhtcHRrPSdJbWFnZTo6RXhpZlRvb2wgMTIuNDAnPgo8cmRmOlJERiB4bWxuczpyZGY9J2h0dHA6Ly93d3cudzMub3JnLzE5OTkvMDIvMjItcmRmLXN5bnRheC1ucyMnPgoKIDxyZGY6RGVzY3JpcHRpb24gcmRmOmFib3V0PScnCiAgeG1sbnM6dGlmZj0naHR0cDovL25zLmFkb2JlLmNvbS90aWZmLzEuMC8nPgogIDx0aWZmOk9yaWVudGF0aW9uPjE8L3RpZmY6T3JpZW50YXRpb24+CiA8L3JkZjpEZXNjcmlwdGlvbj4KPC9yZGY6UkRGPgo8L3g6eG1wbWV0YT4KICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgIAogICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgCiAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAgICAKPD94cGFja2V0IGVuZD0ndyc/PgH//v38+/r5+Pf29fTz8vHw7+7t7Ovq6ejn5uXk4+Lh4N/e3dzb2tnY19bV1NPS0dDPzs3My8rJyMfGxcTDwsHAv769vLu6ubi3trW0s7KxsK+urayrqqmop6alpKOioaCfnp2cm5qZmJeWlZSTkpGQj46NjIuKiYiHhoWEg4KBgH9+fXx7enl4d3Z1dHNycXBvbm1sa2ppaGdmZWRjYmFgX15dXFtaWVhXVlVUU1JRUE9OTUxLSklIR0ZFRENCQUA/Pj08Ozo5ODc2NTQzMjEwLy4tLCsqKSgnJiUkIyIhIB8eHRwbGhkYFxYVFBMSERAPDg0MCwoJCAcGBQQDAgEAACwAAAAAEAAQAAAFUCAgikpkRs6oAkHAnNFzDGvrwk+SEKN9nzkdj2V7AXWJA7GIQyYGvp8pqDtEjVNn4tp0co9Iqw8bc9LG3aQIDX72WuSgUgXHHeYrwALWWIUAADs=");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new PicFilter(), false);
        assertTrue(results.length > 0);

        List<String> secIds = new ArrayList<>();

        for (int i = 0; i < results.length; i++) {
            Picture picture = (Picture) results[i].thisObject();

            validateImageWidthAndHeight(picture, 1200, 1200);
            secIds.add(picture.img().binaryItemIDRef());
        }

        ObjectList<ManifestItem> manifest = hwpxTemplater.getFile().contentHPFFile().manifest();
        ArrayList<ManifestItem> imageManifestItem = new ArrayList<>();
        for (ManifestItem manifestItem : manifest.items()) {
            if(manifestItem.mediaType().equals("image/GIF")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> hrefs = new ArrayList<>();
        for (String secId : secIds) {
            hrefs.add("BinData/" + secId + ".GIF");
        }

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            Assertions.assertTrue(secIds.contains(manifestItem.id()));
            Assertions.assertTrue(hrefs.contains(manifestItem.href()));
        }
    }

    @Test
    @DisplayName("6. 기본 단락 Bmp Base64 이미지 렌더링")
    public void imageParaBase64BmpImageTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, String>() {{
                    put("Korea", "data:image/bmp;base64,Qk2KBAAAAAAAAIoAAAB8AAAAEAAAABAAAAABACAAAwAAAAAEAAATCwAAEwsAAAAAAAAAAAAAAAD/AAD/AAD/AAAAAAAA/0JHUnOPwvUoUbgeFR6F6wEzMzMTZmZmJmZmZgaZmZkJPQrXAyhcjzIAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP///yv///+O////0/////P////z////0////43///8qAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA////Bv///5L////8/////////////////////////////////////P///5H///8FAAAAAAAAAAAAAAAA////Bv///7z/////////////////////////////////////////////////////////u////wUAAAAAAAAAAP///5P/////2NjY//X19f/////////////////////////////////29vb/19fX//////////+QAAAAAP///yv////8+fn5/21tbf91dXX/4+Pj///////////////////////k5OT/dXV1/4qKiv/5+fn//////P///yr///+P/////5SUlP+EhIT/dnZ2///////cyr3/p3pY/6R2U//bybv//////56env+FhYX/lJSU//////////+M////1f/////29vb/l5eX/+jo6P/cyr3/eTUB/3g0AP94NAD/eTYC/9nHuv/o6Oj/l5eX//b29v//////////0/////L/////////////////////qH1f/4hdcf+OXVH/eDQA/3g0AP+siIb///////////////////////////H////z/////////////////////56EsP8wDMb/MAzG/3hRjf+JXm7/gWjS///////////////////////////y////1f/////39/f/mJiY/+np6f/Fu+7/Mg7G/zAMxv8wDMb/MQ3G/8nA8P/p6en/mJiY//b29v//////////0////5H/////lZWV/2xsbP92dnb//////8W77/9zWtj/d1/Z/8nA8P//////np6e/2tra/+VlZX//////////47///8s/////Pj4+P9ubm7/dXV1/+Pj4///////////////////////5eXl/3R0dP+Kior/+fn5//////z///8rAAAAAP///5X/////19fX//T09P/////////////////////////////////19fX/1tbW//////////+SAAAAAAAAAAD///8G////vf////////////////////////////////////////////////////////+8////BgAAAAAAAAAAAAAAAP///wb///+V/////f////////////////////////////////////z///+T////BgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAP///yz///+Q////1P////T////0////1P///5D///8sAAAAAAAAAAAAAAAAAAAAAA==");
                    put("Spain", "data:image/bmp;base64,Qk2KBAAAAAAAAIoAAAB8AAAAEAAAABAAAAABACAAAwAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAD/AAD/AAD/AAAAAAAA/0JHUnOPwvUoUbgeFR6F6wEzMzMTZmZmJmZmZgaZmZkJPQrXAyhcjzIAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAB0OxCMeDMWNHQvE1h4MxPYeDMT2HQvF1R0MxIwdDsQjAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAC/BB4MxY0eDMT9HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzE/R0MxIwAAL8EAAAAAAAAAAAAAAAAMwDMBR4MxboeDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzFuQAAvwQAAAAAAAAAAB0MxIseDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8dC8WKAAAAABXE/yMVwv39FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcL9/hTC/yYVw/2OFcP+/xXD/v9Do7n/GJ/g/ySLyv8sdL//GIfZ/0Ojuv8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/2NFcP9zRXD/v8Vwv3/SovG/xWW1/8oZqv/Kkad/xZoyv9Kisb/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcL9yxXD/e8Vw/7/FcD8/z6Awv8ZWrj/MEmC/5eGsP9+rtD/P4DC/xXB/f8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/e4Vw/3vFcP+/xXD/v8qp8//FXHM/xNRqf+Xpsn/ZLXV/yqmz/8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/3uFcP9zRXD/v8Vw/7/Fbn2/xaO3P8ScLH/E2+v/xWQ2f8UufX/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcL9yxXD/Y4Vw/7/FcP+/xXD/v8XufD/Gafa/xim2f8Xu/P/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/Y0Vxv8kFcL9/RXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXD/v8Vw/7/FcP+/xXC/f4Uwv8mAAAAAB0MxIweDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8dDMSLAAAAAAAAAAAzAMwFHgzEux4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4Mxf8eDMW6AAC/BAAAAAAAAAAAAAAAAAAAvwQeDMWOHgzE/R4Mxf8eDMX/HgzF/x4Mxf8eDMX/HgzF/x4MxP0eDMWNAAC/BAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABwOxiQeDMSPHgzFzB4Lxe4eC8XuHgzFzB4MxY4cDsYkAAAAAAAAAAAAAAAAAAAAAA==");
                    put("Belgium", "data:image/bmp;base64,Qk2KBAAAAAAAAIoAAAB8AAAAEAAAABAAAAABACAAAwAAAAAEAAATCwAAEwsAAAAAAAAAAAAAAAD/AAD/AAD/AAAAAAAA/0JHUnOPwvUoUbgeFR6F6wEzMzMTZmZmJmZmZgaZmZkJPQrXAyhcjzIAAAAAAAAAAAAAAAAEAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACs6uMyCRt751Ube+PVG3vj1Rt751US+9YI1Ke0rAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABwAAAJMAAAD9PL7Uxkff+f9H3/n/R9/5/0ff+f9ExffGOCnr/Tgo65EqKv8GAAAAAAAAAAAAAAAAAAAABwAAALwAAAD/AAAA/zy+1MZH3/n/R9/5/0ff+f9H3/n/RMX3xjgp7P84Kez/OCnruioq/wYAAAAAAAAAAAAAAJUAAAD/AAAA/wAAAP88vtTGR9/5/0ff+f9H3/n/R9/5/0TF98Y4Kez/OCns/zgp7P83KeuTAAAAAAAAAC4AAAD9AAAA/wAAAP8AAAD/PL7Uxkff+f9H3/n/R9/5/0ff+f9ExffGOCns/zgp7P84Kez/OCnr/TUp7SsAAACUAAAA/wAAAP8AAAD/AAAA/zy+1MZH3/n/R9/5/0ff+f9H3/n/RMX3xjgp7P84Kez/OCns/zgp7P83KOuSAAAA1gAAAP8AAAD/AAAA/wAAAP88vtTGR9/5/0ff+f9H3/n/R9/5/0TF98Y4Kez/OCns/zgp7P84Kez/OCjr1AAAAPUAAAD/AAAA/wAAAP8AAAD/PL7Uxkff+f9H3/n/R9/5/0ff+f9ExffGOCns/zgp7P84Kez/OCns/zgo7PQAAAD2AAAA/wAAAP8AAAD/AAAA/zy+1MZH3/n/R9/5/0ff+f9H3/n/RMX3xjgp7P84Kez/OCns/zgp7P84KOz1AAAA1wAAAP8AAAD/AAAA/wAAAP88vtTGR9/5/0ff+f9H3/n/R9/5/0TF98Y4Kez/OCns/zgp7P84Kez/OCjr1QAAAJUAAAD/AAAA/wAAAP8AAAD/PL7Uxkff+f9H3/n/R9/5/0ff+f9ExffGOCns/zgp7P84Kez/OCns/zco65IAAAAvAAAA/QAAAP8AAAD/AAAA/zy+1MZH3/n/R9/5/0ff+f9H3/n/RMX3xjgp7P84Kez/OCns/zgp6/05KO0sAAAAAAAAAJYAAAD/AAAA/wAAAP88vtTGR9/5/0ff+f9H3/n/R9/5/0TF98Y4Kez/OCns/zgp7P83KeyUAAAAAAAAAAAAAAAHAAAAvgAAAP8AAAD/PL7Uxkff+f9H3/n/R9/5/0ff+f9ExffGOCns/zgp7P83KOy8SCTaBwAAAAAAAAAAAAAAAAAAAAcAAACWAAAA/jy+1MZH3/n/R9/5/0ff+f9H3/n/RMX3xjgp6/03KeuTSCTaBwAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAC07t82BRt751Ube+PVG3vj1Rt751UW/+YE5KO0sAAAAAAAAAAAAAAAAAAAAAA==");
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new PicFilter(), false);
        assertTrue(results.length > 0);

        List<String> secIds = new ArrayList<>();

        for (int i = 0; i < results.length; i++) {
            Picture picture = (Picture) results[i].thisObject();

            validateImageWidthAndHeight(picture, 1200, 1200);
            secIds.add(picture.img().binaryItemIDRef());
        }

        ObjectList<ManifestItem> manifest = hwpxTemplater.getFile().contentHPFFile().manifest();
        ArrayList<ManifestItem> imageManifestItem = new ArrayList<>();
        for (ManifestItem manifestItem : manifest.items()) {
            if(manifestItem.mediaType().equals("image/BMP")) {
                imageManifestItem.add(manifestItem);
            }
        }

        List<String> hrefs = new ArrayList<>();
        for (String secId : secIds) {
            hrefs.add("BinData/" + secId + ".BMP");
        }

        assertTrue(imageManifestItem.size() > 0);

        for (int i = 0; i < imageManifestItem.size(); i++) {
            ManifestItem manifestItem = imageManifestItem.get(i);
            Assertions.assertTrue(secIds.contains(manifestItem.id()));
            Assertions.assertTrue(hrefs.contains(manifestItem.href()));
        }
    }

    @Test
    @DisplayName("7. 기본 단락 이미지 모델을 활용한 PNG 로컬 파일 이미지 렌더링")
    public void imageParaLocalPngImageUsingImageModelTest() throws Exception {

        HWPXTemplater hwpxTemplater = HWPXTemplater.builder()
                .parse(TestUtil.getFilePath(this.getClass(), "hwpx/image/Image_Basic_Para.hwpx"))
                .render(new HashMap<String, Object>() {{
                    put("Korea", new Image(TestUtil.getFilePath(this.getClass(), "images/country/png/korea.png")).width(50).height(50));
                    put("Spain", new Image(TestUtil.getFilePath(this.getClass(), "images/country/png/spain.png")).width(50).height(50));
                    put("Belgium", new Image(TestUtil.getFilePath(this.getClass(), "images/country/png/belgium.png")).width(50).height(50));
                }});

        ObjectFinder.Result[] results = ObjectFinder.find(hwpxTemplater.getFile(), new PicFilter(), false);
        assertTrue(results.length > 0);

        List<String> secIds = Arrays.asList(
                "korea", "spain", "belgium"
        );

        for (int i = 0; i < results.length; i++) {
            Picture picture = (Picture) results[i].thisObject();

            validateImageWidthAndHeight(picture, 3750, 3750);
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
