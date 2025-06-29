package kr.mumberrymountain.hwpxtemplater.model;

import kr.mumberrymountain.hwpxtemplater.render.placeholder.PlaceHolderType;

import java.util.EnumMap;

public class CharRole {
    private final EnumMap<PlaceHolderType, Character> charRoleMap = new EnumMap<>(PlaceHolderType.class);

    public CharRole() {
        charRoleMap.put(PlaceHolderType.CONDITION, '?');
        charRoleMap.put(PlaceHolderType.LOOP, '#');
        charRoleMap.put(PlaceHolderType.CLOSURE, '/');
        charRoleMap.put(PlaceHolderType.IMAGE_REPLACEMENT, '$');
        charRoleMap.put(PlaceHolderType.TABLE_REPLACEMENT, '@');
    }

    public void set(PlaceHolderType key, Character value) {
        charRoleMap.put(key, value);
    }

    public Character get(PlaceHolderType key) {
        return charRoleMap.get(key);
    }

    public EnumMap<PlaceHolderType, Character> getCharRoleMap() {
        return charRoleMap;
    }
}
