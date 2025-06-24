package kr.mumberrymountain.hwpxTemplater.util;

import java.net.URISyntaxException;
import java.nio.file.Paths;

public class TestUtil {
    public static String getFilePath(Class<?> clazz, String path) throws URISyntaxException {
        return Paths.get(clazz.getClassLoader().getResource(path).toURI()).toString();
    }
}
