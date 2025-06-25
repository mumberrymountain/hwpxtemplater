package kr.mumberrymountain.hwpxtemplater;

import kr.mumberrymountain.hwpxtemplater.exception.InvalidConfigurationException;

import java.util.HashMap;
import java.util.Map;
public class Config {
    private final Map<String, Object> properties = new HashMap<>();

    public Config() {
        properties.put(ConfigOption.DELIM_PREFIX.getType(), "{{");
        properties.put(ConfigOption.DELIM_SUFFIX.getType(), "}}");
    }

    public void validateDelim() throws Exception{
        if (properties.get(ConfigOption.DELIM_PREFIX.getType()) == null) throw new InvalidConfigurationException("delimPrefix must not be null");
        if (properties.get(ConfigOption.DELIM_SUFFIX.getType()) == null) throw new InvalidConfigurationException("delimSuffix must not be null");
        if (properties.get(ConfigOption.DELIM_PREFIX.getType()) == properties.get(ConfigOption.DELIM_SUFFIX.getType())) throw new InvalidConfigurationException("delimPrefix and delimSuffix must not be the same");
        if (!(properties.get(ConfigOption.DELIM_PREFIX.getType()) instanceof String)) throw new InvalidConfigurationException("delimPrefix must be a string");
        if (!(properties.get(ConfigOption.DELIM_SUFFIX.getType()) instanceof String)) throw new InvalidConfigurationException("delimSuffix must be a string");
        if (((String) properties.get(ConfigOption.DELIM_PREFIX.getType())).length() > 2) throw new InvalidConfigurationException("delimPrefix should not be longer than 2");
        if (((String) properties.get(ConfigOption.DELIM_SUFFIX.getType())).length() > 2) throw new InvalidConfigurationException("delimSuffix should not be longer than 2");
    }

    public Config validate() throws Exception {
        validateDelim();
        return this;
    }

    public void set(String key, Object value) {
        properties.put(key, value);
    }
    public Object get(String key) {
        return properties.get(key);
    }
}
