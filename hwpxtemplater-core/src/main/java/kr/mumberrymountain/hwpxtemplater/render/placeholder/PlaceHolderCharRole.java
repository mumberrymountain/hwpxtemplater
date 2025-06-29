package kr.mumberrymountain.hwpxtemplater.render.placeholder;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class PlaceHolderCharRole {
    private static final Map<PlaceHolderType, Character> charRoleMap = Collections.synchronizedMap(new EnumMap<>(PlaceHolderType.class));

    static {
        charRoleMap.put(PlaceHolderType.CONDITION, '?');
        charRoleMap.put(PlaceHolderType.LOOP, '#');
        charRoleMap.put(PlaceHolderType.CLOSURE, '/');
        charRoleMap.put(PlaceHolderType.IMAGE_REPLACEMENT, '$');
        charRoleMap.put(PlaceHolderType.TABLE_REPLACEMENT, '@');
    }

    private PlaceHolderCharRole() {}

    public static synchronized void set(PlaceHolderType key, Character value) {
        charRoleMap.put(key, value);
    }

    public static synchronized Character get(PlaceHolderType key) {
        return charRoleMap.get(key);
    }

    public static synchronized void reset(EnumMap<PlaceHolderType, Character> newCharRoleMap) {
        charRoleMap.clear();
        charRoleMap.putAll(newCharRoleMap);
    }
}
