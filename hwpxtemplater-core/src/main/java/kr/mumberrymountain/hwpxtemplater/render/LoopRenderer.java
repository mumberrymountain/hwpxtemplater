package kr.mumberrymountain.hwpxtemplater.render;

import kr.dogfoot.hwpxlib.object.content.section_xml.SectionXMLFile;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.Para;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolder;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderRangeStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LoopRenderer<H> implements PairedPlaceHolderRenderer{

    private final PlaceHolderRangeStack rangeStack;
    private final SinglePlaceHolderRendererFactory singlePlaceHolderRendererFactory;
    private final HWPXRenderer rootRenderer;
    public LoopRenderer(PlaceHolderRangeStack rangeStack, HWPXRenderer rootRenderer) {
        this.rangeStack = rangeStack;
        this.rootRenderer = rootRenderer;
        this.singlePlaceHolderRendererFactory = SinglePlaceHolderRendererFactory.getInstance();
    }

    private void clearParaInCurrentStack(){
        SectionXMLFile section = (SectionXMLFile) rangeStack.current().start().parent().parent();

        int startParaIndex = section.getParaIndex(rangeStack.current().start().parent().data());
        int endParaIndex = section.getParaIndex(rangeStack.current().end().parent().data());
        int idx = endParaIndex;

        while (idx >= startParaIndex) {
            section.removePara(section.getPara(idx));
            idx--;
        }
    }

    private void replaceInsideLoop(PlaceHolder currentHolder, PlaceHolder nextHolder, ArrayList<Para> paras, Map listData){
        if(currentHolder.linkedRunItem().parent().equals(rangeStack.current().start()) ||
                currentHolder.linkedRunItem().parent().equals(rangeStack.current().end())) {
            return;
        }

        SinglePlaceHolderRenderer singlePlaceHolderRenderer = singlePlaceHolderRendererFactory.create(currentHolder.type(), rangeStack, rootRenderer);
        singlePlaceHolderRenderer.renderReplacement(currentHolder.linkedRunItem(), currentHolder, listData.get(currentHolder.data()));

        if (nextHolder != null && !currentHolder.linkedRunItem().parent().parent().data().equals(nextHolder.linkedRunItem().parent().parent().data())) {
            paras.add(currentHolder.linkedRunItem().parent().parent().data().clone());
        }
    }

    private ArrayList<Para> loopReplacement(Map listData){
        ArrayList<Para> paras = new ArrayList<>();

        for (int i = 0; i < rangeStack.current().placeHolders().size(); i++) {
            PlaceHolder currentHolder = rangeStack.current().placeHolders().get(i);
            PlaceHolder nextHolder = (i + 1 < rangeStack.current().placeHolders().size()) ? rangeStack.current().placeHolders().get(i + 1) : null;

            replaceInsideLoop(currentHolder, nextHolder, paras, listData);
        }

        return paras;
    }

    private void renderLoopPara(SectionXMLFile section, ArrayList loopedParas, int startParaIndex){
        for (int i = 0; i < loopedParas.size(); i++) {
            ArrayList<Para> innerList = (ArrayList<Para>) loopedParas.get(i);

            for (int j = 0; j < innerList.size(); j++) {
                Para para = innerList.get(j);
                section.insertPara(para, startParaIndex + i + j);
            }
        }
    }

    @Override
    public void flushRange(PlaceHolder placeHolder) {
        Object dataObj = rootRenderer.data().get(placeHolder.data());
        if (dataObj instanceof List<?>) {
            List dataList = (List) dataObj;

            ArrayList loopedParas = new ArrayList();
            for (Object obj: dataList) {
                if (!(obj instanceof Map<?,?>)) continue;
                loopedParas.add(loopReplacement((Map) obj));
            }

            if (RendererUtil.isRangeVertical(rangeStack.current())) {
                SectionXMLFile section = (SectionXMLFile) rangeStack.current().start().parent().parent();
                int startParaIndex = section.getParaIndex(rangeStack.current().start().parent().data());
                clearParaInCurrentStack();
                renderLoopPara(section, loopedParas, startParaIndex);
            }
        }
    }
}
