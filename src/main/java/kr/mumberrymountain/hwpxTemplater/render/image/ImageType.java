package kr.mumberrymountain.hwpxTemplater.render.image;

public enum ImageType {

    JPEG("jpg"),
    PNG("png"),
    GIF("git"),
    BMP("bmp"),
    TIFF("tiff"),
    SVG("svg");

    private String type;

    ImageType(String type) {
        this.type = type;
    }

    public static ImageType getFileType(String imagePath) {
        ImageType type = null;

        imagePath = imagePath.toLowerCase();
        if (imagePath.endsWith(".jpeg") || imagePath.endsWith(".jpg")) type = JPEG;
        else if (imagePath.endsWith(".png")) type = PNG;
        else if (imagePath.endsWith(".gif")) type = GIF;
        else if (imagePath.endsWith(".bmp")) type = BMP;
        else if (imagePath.endsWith(".tiff")) type = TIFF;
        else if (imagePath.endsWith(".svg")) type = SVG;
        else {
            throw new IllegalArgumentException(
                    "Unsupported picture: " + imagePath);
        }
        return type;
    }

    public static ImageType getBase64FileType(String imagePath) {
        ImageType type = null;
        if (imagePath == null || !imagePath.startsWith("data:image/")) {
            return null;
        }

        int slashIndex = imagePath.indexOf('/');
        int semicolonIndex = imagePath.indexOf(';');

        if (slashIndex == -1 || semicolonIndex == -1 || semicolonIndex < slashIndex) {
            return null;
        }

        String base64Type = imagePath.substring(slashIndex + 1, semicolonIndex);

        if (base64Type.equals("jpeg")  || base64Type.equals("jpg")) type = JPEG;
        else if (base64Type.equals("png")) type = PNG;
        else if (base64Type.equals("gif")) type = GIF;
        else if (base64Type.equals("bmp")) type = BMP;
        else if (base64Type.equals("tiff")) type = TIFF;
        else if (base64Type.equals("svg")) type = SVG;
        else {
            throw new IllegalArgumentException(
                    "Unsupported picture: " + imagePath);
        }

        return type;
    }

    /*
    public static ImageType suggestFileType(byte[] bytes) {
        if (startsWith(bytes, "GIF89a".getBytes()) || startsWith(bytes, "GIF87a".getBytes())) {
            return GIF;
        }
        if (startsWith(bytes, new byte[] { (byte) 0xFF, (byte) 0xD8 })
                || endsWith(bytes, new byte[] { (byte) 0xFF, (byte) 0xD9 })) {
            return JPEG;
        }
        if (startsWith(bytes, new byte[] { (byte) 0x89, (byte) 0x50, (byte) 0x4E, (byte) 0x47 })) {
            return PNG;
        }
        if (startsWith(bytes, new byte[] { (byte) 0x49, (byte) 0x49, (byte) 0x2A, (byte) 0x00 })
                || startsWith(bytes, new byte[] { (byte) 0x4D, (byte) 0x4D, (byte) 0x00, (byte) 0x2A })) {
            return TIFF;
        }
        if (startsWith(bytes, "BM".getBytes())) {
            return BMP;
        }
        String str = new String(bytes);
        if (str.substring(0, 100).indexOf("<svg") != -1 || str.substring(str.length() - 10).indexOf("</svg>") != -1) {
            return SVG;
        }
        throw new IllegalArgumentException("Unable to identify the picture type from byte");
    }

    */
}
