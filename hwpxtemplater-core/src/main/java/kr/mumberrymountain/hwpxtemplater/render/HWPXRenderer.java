package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.HWPXFile;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.Config;
import kr.mumberrymountain.hwpxtemplater.ConfigOption;
import kr.mumberrymountain.hwpxtemplater.interceptor.InterceptorHandler;
import kr.mumberrymountain.hwpxtemplater.render.image.ImageInfo;
import kr.mumberrymountain.hwpxtemplater.render.style.StyleRenderer;
import kr.mumberrymountain.hwpxtemplater.util.FinderUtil;

import java.util.HashMap;
import java.util.Map;

public class HWPXRenderer <H>{
    private final HWPXFile hwpxFile;
    private final Config config;
    private final InterceptorHandler interceptorHandler;
    private final Map<String, H> data;

    private final HashMap<String, ImageInfo> imageManager = new HashMap<>();
    private final ManifestItemManager manifestItemManager;
    private final StyleRenderer styleRenderer;

    public HWPXRenderer(HWPXFile hwpxFile, Config config, InterceptorHandler interceptorHandler, Map<String, H> data){
        this.hwpxFile = hwpxFile;
        this.config = config;
        this.interceptorHandler = interceptorHandler;
        this.data = data;
        this.manifestItemManager = new ManifestItemManager(hwpxFile.contentHPFFile().manifest());
        this.styleRenderer = new StyleRenderer(hwpxFile.headerXMLFile());
    }


    public void render() throws Exception {
        String delimStart = (String) config.get(ConfigOption.DELIM_PREFIX.getType());
        String delimEnd = (String) config.get(ConfigOption.DELIM_SUFFIX.getType());
        ObjectFinder.Result[] results = FinderUtil.findPlaceHolder(hwpxFile, delimStart, delimEnd);

        PlaceHolderRenderer<H> placeHolderRenderer = new PlaceHolderRenderer<>(results, delimStart, delimEnd, this);
        placeHolderRenderer.render();

        manifestItemManager.addImage(imageManager);
    }

    public Config config(){
        return config;
    }

    public InterceptorHandler interceptorHandler(){
        return interceptorHandler;
    }

    public Map<String, H> data(){
        return data;
    }

    public HashMap<String, ImageInfo> imageManager(){
        return imageManager;
    }

    public ManifestItemManager manifestItemManager(){
        return manifestItemManager;
    }

    public StyleRenderer styleRenderer(){
        return styleRenderer;
    }
}
