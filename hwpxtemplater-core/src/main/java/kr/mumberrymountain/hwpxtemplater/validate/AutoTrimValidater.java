package kr.mumberrymountain.hwpxtemplater.validate;

import kr.mumberrymountain.hwpxtemplater.ConfigOption;
import kr.mumberrymountain.hwpxtemplater.exception.InvalidConfigurationException;

import java.util.Map;

public class AutoTrimValidater implements Validater {
    private static AutoTrimValidater autoTrimValidater;

    public static synchronized AutoTrimValidater getInstance(){
        if (autoTrimValidater == null) autoTrimValidater = new AutoTrimValidater();
        return autoTrimValidater;
    }

    @Override
    public void validate(Map<String, Object> properties) {
        if (!(properties.get(ConfigOption.AUTO_TRIM.getType()) instanceof Boolean)) throw new InvalidConfigurationException("autoTrm must be a boolean");
    }
}
