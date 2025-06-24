package kr.mumberrymountain.hwpxTemplater.util;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Iterator;

public final class ByteUtil {

    public static byte[] getUrlByteArray(String urlPath) {
        try {
            return toByteArray(getUrlStream(urlPath));
        } catch (IOException e) {
            //
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getLocalByteArray(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] getBase64ByteArray(String base64) {
        String encodingPrefix = "base64,";
        if (base64.contains(encodingPrefix)) {
            int contentStartIndex = base64.indexOf(encodingPrefix) + encodingPrefix.length();
            base64 = base64.substring(contentStartIndex);
            return Base64.getDecoder().decode(base64);
        }
        return null;
    }

    public static byte[] toByteArray(InputStream is) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        byte[] data = new byte[4096];
        int bytesRead;

        while ((bytesRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }

        return buffer.toByteArray();
    }

    public static InputStream getUrlStream(String urlPath) throws IOException {
        URL url = new URL(urlPath);
        URLConnection connection = url.openConnection();
        connection.setConnectTimeout(15000);
        connection.setReadTimeout(15000);
        InputStream inputStream = connection.getInputStream();
        if (connection instanceof HttpURLConnection) {
            if (200 != ((HttpURLConnection) connection).getResponseCode()) {
                throw new IOException("get url " + urlPath + " content error, response status: "
                        + ((HttpURLConnection) connection).getResponseCode());
            }
        }
        return inputStream;
    }

    public static BufferedImage bytesToBufferedImage(byte[] bytes) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
            BufferedImage bufferedImage = ImageIO.read(bais);
            if (bufferedImage == null) {
                throw new IOException("Failed to decode image from byte array.");
            }
            return bufferedImage;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Dimension getDimension(InputStream input) throws Exception {
        try (ImageInputStream iis = ImageIO.createImageInputStream(input)) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(iis);
            if (!readers.hasNext()) {
                throw new IllegalArgumentException("No ImageReader found for input.");
            }

            ImageReader reader = readers.next();
            reader.setInput(iis, true, true);

            int width = reader.getWidth(0);
            int height = reader.getHeight(0);

            return new Dimension(width, height);
        }
    }
}

