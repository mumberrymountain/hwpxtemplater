package kr.mumberrymountain.hwpxtemplater;

import kr.mumberrymountain.hwpxtemplater.exception.InvalidConfigurationException;
import kr.mumberrymountain.hwpxtemplater.model.CharRole;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderCharRole;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderType;

import java.util.HashMap;
import java.util.Map;
public class Config {
    private final Map<String, Object> properties = new HashMap<>();

    public Config() {
        properties.put(ConfigOption.DELIM_PREFIX.getType(), "{{");
        properties.put(ConfigOption.DELIM_SUFFIX.getType(), "}}");
        properties.put(ConfigOption.CHAR_ROLE_SETTER.getType(), new CharRole());
    }

    private void validateDelim() throws Exception {
        if (properties.get(ConfigOption.DELIM_PREFIX.getType()) == null) throw new InvalidConfigurationException("delimPrefix must not be null");
        if (properties.get(ConfigOption.DELIM_SUFFIX.getType()) == null) throw new InvalidConfigurationException("delimSuffix must not be null");
        if (properties.get(ConfigOption.DELIM_PREFIX.getType()) == properties.get(ConfigOption.DELIM_SUFFIX.getType())) throw new InvalidConfigurationException("delimPrefix and delimSuffix must not be the same");
        if (!(properties.get(ConfigOption.DELIM_PREFIX.getType()) instanceof String)) throw new InvalidConfigurationException("delimPrefix must be a string");
        if (!(properties.get(ConfigOption.DELIM_SUFFIX.getType()) instanceof String)) throw new InvalidConfigurationException("delimSuffix must be a string");
        if (((String) properties.get(ConfigOption.DELIM_PREFIX.getType())).length() > 2) throw new InvalidConfigurationException("delimPrefix should not be longer than 2");
        if (((String) properties.get(ConfigOption.DELIM_SUFFIX.getType())).length() > 2) throw new InvalidConfigurationException("delimSuffix should not be longer than 2");
    }

    private void validateCharRoleSetter() throws Exception{
        if (!(properties.get(ConfigOption.CHAR_ROLE_SETTER.getType()) instanceof CharRole)) throw new InvalidConfigurationException("charRoleSetter must be a CharRole");
        CharRole charRole = (CharRole) properties.get(ConfigOption.CHAR_ROLE_SETTER.getType());
        validateCharRoleIndividual(charRole);
        resetCharRoleMap(charRole);
    }

    private void validateCharRoleIndividual(CharRole charRole){
        if (charRole.get(PlaceHolderType.CONDITION) == null) throw new InvalidConfigurationException("charRole for placeHolder type conditition must not be null");
        if (charRole.get(PlaceHolderType.LOOP) == null) throw new InvalidConfigurationException("charRole for placeHolder type loop must not be null");
        if (charRole.get(PlaceHolderType.CLOSURE) == null) throw new InvalidConfigurationException("charRole for placeHolder type closure must not be null");
        if (charRole.get(PlaceHolderType.IMAGE_REPLACEMENT) == null) throw new InvalidConfigurationException("charRole for placeHolder type image must not be null");
        if (charRole.get(PlaceHolderType.TABLE_REPLACEMENT) == null) throw new InvalidConfigurationException("charRole for placeHolder type table must not be null");
    }

    private void resetCharRoleMap(CharRole charRole){
        PlaceHolderCharRole.reset(charRole.getCharRoleMap());
    }

    public Config validate() throws Exception {
        validateDelim();
        validateCharRoleSetter();
        return this;
    }

    public void set(String key, Object value) {
        properties.put(key, value);
    }
    public Object get(String key) {
        return properties.get(key);
    }
}
