package kr.mumberrymountain.hwpxtemplater.render.placeholder;

import java.util.EnumMap;

public class PlaceHolderCharRole {
    private static final EnumMap<PlaceHolderType, Character> charRoleMap = new EnumMap<>(PlaceHolderType.class);

    static {
        charRoleMap.put(PlaceHolderType.CONDITION, '?');
        charRoleMap.put(PlaceHolderType.LOOP, '#');
        charRoleMap.put(PlaceHolderType.CLOSURE, '/');
        charRoleMap.put(PlaceHolderType.IMAGE_REPLACEMENT, '$');
        charRoleMap.put(PlaceHolderType.TABLE_REPLACEMENT, '@');
    }

    private PlaceHolderCharRole() {}

    public static void set(PlaceHolderType key, Character value) {
        charRoleMap.put(key, value);
    }

    public static Character get(PlaceHolderType key) {
        return charRoleMap.get(key);
    }

    public static void reset(EnumMap<PlaceHolderType, Character> newCharRoleMap) {
        charRoleMap.clear();
        charRoleMap.putAll(newCharRoleMap);
    }
}
