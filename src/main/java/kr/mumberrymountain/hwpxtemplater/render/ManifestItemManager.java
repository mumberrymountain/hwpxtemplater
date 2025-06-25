package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.common.ObjectList;
import kr.dogfoot.hwpxlib.object.content.context_hpf.ManifestItem;
import kr.mumberrymountain.hwpxtemplater.render.image.ImageInfo;

import java.util.HashMap;

public class ManifestItemManager {

    private final ObjectList<ManifestItem> manifest;

    public ManifestItemManager (ObjectList<ManifestItem> manifest){
        this.manifest = manifest;
    }

    private void addImageEach(ImageInfo imageInfo){
        ManifestItem mItem = (ManifestItem) manifest.addNew();
        mItem.href("BinData/" + imageInfo.fileName());
        mItem.mediaType("image/" + imageInfo.type());
        mItem.id(imageInfo.id());
        mItem.embedded(true);
        mItem.createAttachedFile();
        mItem.attachedFile().data(imageInfo.imageByte());
    }

    public void addImage(HashMap<String, ImageInfo> imageManager){
        for (ImageInfo imageInfo : imageManager.values()) {
            addImageEach(imageInfo);
        }
    }
}
