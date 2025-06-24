package kr.mumberrymountain.hwpxTemplater;

public enum ConfigOption {
    DELIM_PREFIX("delimPrefix"),
    DELIM_SUFFIX("delimSuffix");

    private String type;

    ConfigOption(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
