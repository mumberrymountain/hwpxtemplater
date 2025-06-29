package kr.mumberrymountain.hwpxtemplater.validate;

import kr.mumberrymountain.hwpxtemplater.ConfigOption;
import kr.mumberrymountain.hwpxtemplater.exception.InvalidConfigurationException;
import kr.mumberrymountain.hwpxtemplater.model.CharRole;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderCharRole;
import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderType;

import java.util.Map;

public class CharRoleSetterValidater implements Validater{
    private static CharRoleSetterValidater charRoleSetterValidater;

    public static CharRoleSetterValidater getInstance(){
        if (charRoleSetterValidater == null) charRoleSetterValidater = new CharRoleSetterValidater();
        return charRoleSetterValidater;
    }

    @Override
    public void validate(Map<String, Object> properties) throws InvalidConfigurationException {
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
}
