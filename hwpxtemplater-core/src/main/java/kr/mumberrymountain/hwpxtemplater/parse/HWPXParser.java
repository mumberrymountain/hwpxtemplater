package kr.mumberrymountain.hwpxtemplater.parse;

import kr.dogfoot.hwpxlib.object.HWPXFile;
import kr.dogfoot.hwpxlib.object.common.ObjectType;
import kr.dogfoot.hwpxlib.object.content.section_xml.paragraph.*;
import kr.dogfoot.hwpxlib.tool.finder.ObjectFinder;
import kr.mumberrymountain.hwpxtemplater.Config;
import kr.mumberrymountain.hwpxtemplater.ConfigOption;
import kr.mumberrymountain.hwpxtemplater.delim.DelimParser;
import kr.mumberrymountain.hwpxtemplater.delim.DelimPos;
import kr.mumberrymountain.hwpxtemplater.linkedobj.*;
import kr.mumberrymountain.hwpxtemplater.util.ParaUtil;
import kr.mumberrymountain.hwpxtemplater.util.FinderUtil;

import java.util.ArrayList;
import java.util.List;

public class HWPXParser {
    private final HWPXFile hwpxFile;
    private final Config config;

    public HWPXParser(HWPXFile hwpxFile, Config config){
        this.hwpxFile = hwpxFile;
        this.config = config;
    }

    public void parse() throws Exception {
        parsePara(ObjectType.hs_sec);
        parsePara(ObjectType.hp_subList);
    }

    private void parsePara(ObjectType parentsType) throws Exception {
        ObjectFinder.Result[] results = FinderUtil.findTag(hwpxFile, ObjectType.hp_p, parentsType);
        for (ObjectFinder.Result result: results) {
            parseParaEach(new LinkedPara((Para) result.thisObject(), result.parentsPath().get(result.parentsPath().size() - 1)));
        }
    }

    private void parseParaEach(LinkedPara linkedPara){
        linkedPara.data().removeLineSegArray();
        String str = ParaUtil.getParaText(linkedPara.data());
        String delimStart = (String) config.get(ConfigOption.DELIM_PREFIX.getType());
        String delimEnd = (String) config.get(ConfigOption.DELIM_SUFFIX.getType());

        TextIsolater textIsolater = new TextIsolater(linkedPara);
        textIsolater.reformatParaToIsolateText(linkedPara);

        DelimParser delimParser = new DelimParser(delimStart, delimEnd);
        List<DelimPos> delims = delimParser.parse(str);

        for (DelimPos delim : delims) {
            ArrayList<LinkedObj> items = new ItemWithDelimSearcher(delim).find(linkedPara);
            new ItemRemapper(items).reMap();
        }
    }
}
