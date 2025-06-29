package kr.mumberrymountain.hwpxtemplater.validate;
import kr.mumberrymountain.hwpxtemplater.ConfigOption;
import kr.mumberrymountain.hwpxtemplater.exception.InvalidConfigurationException;

import java.util.Map;

public class DelimValidater implements Validater {
    private static DelimValidater delimValidater;

    public static DelimValidater getInstance(){
        if (delimValidater == null) delimValidater = new DelimValidater();
        return delimValidater;
    }

    @Override
    public void validate(Map<String, Object> properties) throws InvalidConfigurationException {
        if (properties.get(ConfigOption.DELIM_PREFIX.getType()) == null) throw new InvalidConfigurationException("delimPrefix must not be null");
        if (properties.get(ConfigOption.DELIM_SUFFIX.getType()) == null) throw new InvalidConfigurationException("delimSuffix must not be null");
        if (properties.get(ConfigOption.DELIM_PREFIX.getType()) == properties.get(ConfigOption.DELIM_SUFFIX.getType())) throw new InvalidConfigurationException("delimPrefix and delimSuffix must not be the same");
        if (!(properties.get(ConfigOption.DELIM_PREFIX.getType()) instanceof String)) throw new InvalidConfigurationException("delimPrefix must be a string");
        if (!(properties.get(ConfigOption.DELIM_SUFFIX.getType()) instanceof String)) throw new InvalidConfigurationException("delimSuffix must be a string");
        if (((String) properties.get(ConfigOption.DELIM_PREFIX.getType())).length() > 2) throw new InvalidConfigurationException("delimPrefix should not be longer than 2");
        if (((String) properties.get(ConfigOption.DELIM_SUFFIX.getType())).length() > 2) throw new InvalidConfigurationException("delimSuffix should not be longer than 2");
    }
}
