package kr.mumberrymountain.hwpxtemplater;

public enum ConfigOption {
    DELIM_PREFIX("delimPrefix"),
    DELIM_SUFFIX("delimSuffix"),
    CHAR_ROLE_SETTER("charRoleSetter");

    private String type;

    ConfigOption(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
