package kr.mumberrymountain.hwpxtemplater.validate;

import java.util.Arrays;
import java.util.List;

public class ValidaterFactory {
    public static List<Validater> validaters() {
        return Arrays.asList(
                DelimValidater.getInstance(),
                CharRoleSetterValidater.getInstance(),
                AutoTrimValidater.getInstance()
        );
    }
}
