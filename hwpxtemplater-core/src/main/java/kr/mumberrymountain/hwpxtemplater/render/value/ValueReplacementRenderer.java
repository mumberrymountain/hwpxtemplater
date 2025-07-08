package kr.mumberrymountain.hwpxtemplater.render.value;

public interface ValueReplacementRenderer {
    void render();
    void executeNullValueInterceptor();
    void executeValueInterceptor();
    void executeTrim();
    void renderReplacement();
}
