package kr.mumberrymountain.hwpxTemplater.util;

public class HWPXUnitUtil {
    public static int pxToHwpxUnit(int px) {
        return (int) Math.round((px / 96.0) * 7200.0);
    }
}
