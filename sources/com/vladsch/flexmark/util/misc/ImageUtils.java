package com.vladsch.flexmark.util.misc;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageObserver;
import java.awt.image.RGBImageFilter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;

/* loaded from: infinitode-2.jar:com/vladsch/flexmark/util/misc/ImageUtils.class */
public class ImageUtils {
    public static Color TRANSPARENT = new Color(0, 0, 0, 0);
    private static final Pattern BASE64_ENCODING_PATTERN = Pattern.compile("^data:image/[a-z0-9_-]+;base64,", 2);

    public static Image getImageFromClipboard() {
        return getImageFromTransferable(Toolkit.getDefaultToolkit().getSystemClipboard().getContents((Object) null));
    }

    public static Image getImageFromTransferable(Transferable transferable) {
        Transferable transferable2 = transferable;
        if (transferable2 == null) {
            return null;
        }
        try {
            if (transferable.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                transferable2 = (Image) transferable.getTransferData(DataFlavor.imageFlavor);
                return transferable2;
            }
            return null;
        } catch (IOException unused) {
            throw new RuntimeException();
        } catch (UnsupportedFlavorException e) {
            transferable2.printStackTrace();
            return null;
        }
    }

    public static BufferedImage scaleImage(BufferedImage bufferedImage, int i, int i2, int i3) {
        if (bufferedImage == null || i == 0 || i2 == 0) {
            return null;
        }
        return new AffineTransformOp(AffineTransform.getScaleInstance(i / bufferedImage.getWidth((ImageObserver) null), i2 / bufferedImage.getHeight((ImageObserver) null)), i3 != 0 ? i3 : 2).filter(bufferedImage, (BufferedImage) null);
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image == null) {
            return null;
        }
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        int width = image.getWidth((ImageObserver) null);
        int height = image.getHeight((ImageObserver) null);
        if (width < 0 || height < 0) {
            return null;
        }
        BufferedImage bufferedImage = new BufferedImage(width, height, 2);
        Graphics2D createGraphics = bufferedImage.createGraphics();
        createGraphics.drawImage(image, 0, 0, (ImageObserver) null);
        createGraphics.dispose();
        return bufferedImage;
    }

    public static void save(BufferedImage bufferedImage, File file, String str) {
        try {
            ImageIO.write(bufferedImage, str, file);
        } catch (Throwable th) {
            System.out.println("Write error for " + file.getPath() + ": " + th.getMessage());
        }
    }

    public static BufferedImage loadImageFromFile(File file) {
        if (file == null || !file.isFile()) {
            return null;
        }
        for (int i = 0; i < 3; i++) {
            try {
                try {
                    BufferedImage read = ImageIO.read(file);
                    if (i > 0) {
                        System.err.println();
                    }
                    return read;
                } catch (IndexOutOfBoundsException unused) {
                    System.err.print("*");
                    System.err.println("could not read" + file);
                }
            } catch (Throwable unused2) {
                return null;
            }
        }
        return null;
    }

    public static BufferedImage loadImageFromContent(byte[] bArr, String str) {
        if (bArr == null) {
            return null;
        }
        BufferedImage byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            try {
                byteArrayInputStream = ImageIO.read(byteArrayInputStream);
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    byteArrayInputStream.printStackTrace();
                }
                return byteArrayInputStream;
            } catch (IOException | IndexOutOfBoundsException unused) {
                System.err.print("*");
                System.err.println("could not read from image bytes for " + str);
                try {
                    byteArrayInputStream.close();
                    return null;
                } catch (IOException e2) {
                    byteArrayInputStream.printStackTrace();
                    return null;
                }
            }
        } catch (Throwable th) {
            try {
                byteArrayInputStream = byteArrayInputStream;
                byteArrayInputStream.close();
            } catch (IOException e3) {
                byteArrayInputStream.printStackTrace();
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.IOException] */
    public static String base64Encode(BufferedImage bufferedImage) {
        String str = null;
        ?? byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "PNG", (OutputStream) byteArrayOutputStream);
            str = Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray()).replace("\r", "").replace(SequenceUtils.EOL, "");
            byteArrayOutputStream = byteArrayOutputStream;
            byteArrayOutputStream.close();
        } catch (IOException e) {
            byteArrayOutputStream.printStackTrace();
        }
        return "data:image/png;base64," + str;
    }

    public static String base64Encode(File file) {
        if (file == null || !file.isFile()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            if (fileInputStream.read(bArr) != -1) {
                return "data:image/png;base64," + Base64.getEncoder().encodeToString(bArr).replace("\r", "").replace(SequenceUtils.EOL, "");
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static BufferedImage base64Decode(File file) {
        String str;
        int indexOf;
        if (file == null || !file.isFile()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[(int) file.length()];
            if (fileInputStream.read(bArr) != -1 && (indexOf = (str = new String(bArr, StandardCharsets.UTF_8)).indexOf(44)) >= 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(str.substring(indexOf + 1)));
                BufferedImage read = ImageIO.read(byteArrayInputStream);
                byteArrayInputStream.close();
                return read;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean isEncodedImage(String str) {
        return str != null && str.startsWith("data:image/") && BASE64_ENCODING_PATTERN.matcher(str).find();
    }

    public static boolean isPossiblyEncodedImage(String str) {
        return str != null && str.startsWith("data:image/");
    }

    public static BufferedImage base64Decode(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        try {
            int indexOf = str.indexOf(44);
            if (indexOf >= 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Base64.getDecoder().decode(str.substring(indexOf + 1)));
                BufferedImage read = ImageIO.read(byteArrayInputStream);
                byteArrayInputStream.close();
                return read;
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static BufferedImage loadImageFromURL(String str) {
        return loadImageFromURL(str, false);
    }

    public static BufferedImage loadImageFromURL(String str, boolean z) {
        if (str != null) {
            try {
                return toBufferedImage(ImageIO.read(new URL(str)));
            } catch (IOException e) {
                if (z) {
                    e.printStackTrace();
                    return null;
                }
                return null;
            }
        }
        return null;
    }

    public static BufferedImage makeRoundedCorner(BufferedImage bufferedImage, int i, int i2) {
        if (i == 0.0f) {
            return bufferedImage;
        }
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        BufferedImage bufferedImage2 = new BufferedImage(width, height, 2);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        createGraphics.setComposite(AlphaComposite.Src);
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        createGraphics.setColor(Color.WHITE);
        createGraphics.fill(new RoundRectangle2D.Float(0.0f, 0.0f, width, height, i, i));
        createGraphics.setComposite(AlphaComposite.SrcAtop);
        createGraphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        createGraphics.dispose();
        return bufferedImage2;
    }

    public static BufferedImage addBorder(BufferedImage bufferedImage, Color color, int i, int i2) {
        int width = bufferedImage.getWidth() + (i << 1);
        int height = bufferedImage.getHeight() + (i << 1);
        BufferedImage bufferedImage2 = new BufferedImage(width, height, 2);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        createGraphics.setColor(color);
        createGraphics.drawImage(bufferedImage, i, i, bufferedImage.getWidth(), bufferedImage.getHeight(), (ImageObserver) null);
        createGraphics.setStroke(new BasicStroke(i, 2, 0, i));
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int i3 = (width - i) - 1;
        int i4 = (height - i) - 1;
        int i5 = i / 2;
        if (i2 > 0) {
            int i6 = i2 + i;
            createGraphics.drawRoundRect(i5, i5, i3, i4, i6, i6);
        } else {
            createGraphics.drawRect(i5, i5, i3, i4);
        }
        createGraphics.dispose();
        return bufferedImage2;
    }

    public static BufferedImage drawRectangle(BufferedImage bufferedImage, int i, int i2, int i3, int i4, Color color, int i5, int i6) {
        return drawRectangle(bufferedImage, i, i2, i3, i4, color, i5, i6, null, 0.0f);
    }

    public static BufferedImage drawRectangle(BufferedImage bufferedImage, int i, int i2, int i3, int i4, Color color, int i5, int i6, float[] fArr, float f) {
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        if (color == null) {
            color = Color.getColor("", (bufferedImage.getRGB(i + (i3 / 2), i2 + (i4 / 2)) & 16777215) ^ (-1));
        }
        createGraphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        if (fArr != null) {
            createGraphics.setStroke(new BasicStroke(i5, 2, 0, i5, fArr, f));
        } else {
            createGraphics.setStroke(new BasicStroke(i5, 2, 0, i5));
        }
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        createGraphics.setColor(color);
        if (i6 > 0) {
            createGraphics.drawRoundRect(i, i2, i3, i4, i6, i6);
        } else {
            createGraphics.drawRect(i, i2, i3, i4);
        }
        createGraphics.dispose();
        return bufferedImage2;
    }

    public static BufferedImage drawOval(BufferedImage bufferedImage, int i, int i2, int i3, int i4, Color color, int i5, float[] fArr, float f) {
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        if (color == null) {
            color = Color.getColor("", (bufferedImage.getRGB(i + (i3 / 2), i2 + (i4 / 2)) & 16777215) ^ (-1));
        }
        createGraphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        if (fArr != null) {
            createGraphics.setStroke(new BasicStroke(i5, 2, 0, i5, fArr, f));
        } else {
            createGraphics.setStroke(new BasicStroke(i5, 2, 0, i5));
        }
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        createGraphics.setColor(color);
        createGraphics.drawOval(i, i2, i3, i4);
        createGraphics.dispose();
        return bufferedImage2;
    }

    public static BufferedImage drawHighlightRectangle(BufferedImage bufferedImage, int i, int i2, int i3, int i4, Color color, int i5, int i6, Color color2) {
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        boolean z = color2.getAlpha() != 0;
        createGraphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        if (i6 > 0) {
            if (z) {
                createGraphics.setColor(color2);
                createGraphics.fillRoundRect(i, i2, i3, i4, i6, i6);
            }
            if (i5 > 0) {
                createGraphics.setColor(color);
                createGraphics.setStroke(new BasicStroke(i5, 2, 0, i5));
                createGraphics.drawRoundRect(i, i2, i3, i4, i6, i6);
            }
        } else {
            if (z) {
                createGraphics.setColor(color2);
                createGraphics.fillRect(i, i2, i3, i4);
            }
            if (i5 > 0) {
                createGraphics.setColor(color);
                createGraphics.setStroke(new BasicStroke(i5, 2, 0, i5));
                createGraphics.drawRect(i, i2, i3, i4);
            }
        }
        createGraphics.dispose();
        return bufferedImage2;
    }

    public static BufferedImage drawHighlightOval(BufferedImage bufferedImage, int i, int i2, int i3, int i4, Color color, int i5, Color color2) {
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        boolean z = color2.getAlpha() != 0;
        createGraphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        if (z) {
            createGraphics.setColor(color2);
            createGraphics.fillOval(i, i2, i3, i4);
        }
        if (i5 > 0) {
            createGraphics.setColor(color);
            createGraphics.setStroke(new BasicStroke(i5, 2, 0, i5));
            createGraphics.drawOval(i, i2, i3, i4);
        }
        createGraphics.dispose();
        return bufferedImage2;
    }

    public static BufferedImage punchOuterHighlightRectangle(BufferedImage bufferedImage, BufferedImage bufferedImage2, int i, int i2, int i3, int i4, int i5, int i6, Color color, int i7, int i8, boolean z) {
        if (!(color.getAlpha() != 0)) {
            return bufferedImage2;
        }
        BufferedImage bufferedImage3 = bufferedImage2 != null ? bufferedImage2 : new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Graphics2D createGraphics = bufferedImage3.createGraphics();
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (bufferedImage2 == null) {
            createGraphics.setColor(color);
            if (i8 > 0) {
                createGraphics.fillRoundRect(i7, i7, width - (2 * i7), height - (2 * i7), i8, i8);
            } else {
                createGraphics.fillRect(i7, i7, width - (2 * i7), height - (2 * i7));
            }
        }
        if (i6 > 0) {
            createGraphics.setColor(TRANSPARENT);
            createGraphics.setComposite(AlphaComposite.Src);
            createGraphics.fillRoundRect(Utils.minLimit(0, i - (i5 / 2)), Utils.minLimit(0, i2 - (i5 / 2)), i3 + i5, i4 + i5, i6 + i5, i6 + i5);
        } else {
            createGraphics.setColor(TRANSPARENT);
            createGraphics.setComposite(AlphaComposite.Src);
            createGraphics.fillRect(Utils.minLimit(0, i - (i5 / 2)), Utils.minLimit(0, i2 - (i5 / 2)), i3 + i5, i4 + i5);
        }
        if (z) {
            createGraphics.setComposite(AlphaComposite.DstOver);
            createGraphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        }
        createGraphics.dispose();
        return bufferedImage3;
    }

    public static BufferedImage punchOuterHighlightOval(BufferedImage bufferedImage, BufferedImage bufferedImage2, int i, int i2, int i3, int i4, int i5, Color color, int i6, int i7, boolean z) {
        if (!(color.getAlpha() != 0)) {
            return bufferedImage2;
        }
        BufferedImage bufferedImage3 = bufferedImage2 != null ? bufferedImage2 : new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), 2);
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        Graphics2D createGraphics = bufferedImage3.createGraphics();
        createGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (bufferedImage2 == null) {
            createGraphics.setColor(color);
            if (i7 > 0) {
                createGraphics.fillRoundRect(i6, i6, width - (2 * i6), height - (2 * i6), i7, i7);
            } else {
                createGraphics.fillRect(i6, i6, width - (2 * i6), height - (2 * i6));
            }
        }
        createGraphics.setColor(TRANSPARENT);
        createGraphics.setComposite(AlphaComposite.Src);
        createGraphics.fillOval(Utils.minLimit(0, i - (i5 / 2)), Utils.minLimit(0, i2 - (i5 / 2)), i3 + i5, i4 + i5);
        if (z) {
            createGraphics.setComposite(AlphaComposite.DstOver);
            createGraphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        }
        createGraphics.dispose();
        return bufferedImage3;
    }

    public static BufferedImage cropImage(BufferedImage bufferedImage, int i, int i2, int i3, int i4) {
        return bufferedImage.getSubimage(i, i3, (bufferedImage.getWidth() - i) - i2, (bufferedImage.getHeight() - i3) - i4);
    }

    public static BufferedImage removeAlpha(BufferedImage bufferedImage) {
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage.getWidth((ImageObserver) null), bufferedImage.getHeight((ImageObserver) null), 1);
        Graphics2D createGraphics = bufferedImage2.createGraphics();
        createGraphics.drawImage(bufferedImage, 0, 0, bufferedImage2.getWidth(), bufferedImage2.getHeight(), Color.WHITE, (ImageObserver) null);
        createGraphics.dispose();
        return bufferedImage2;
    }

    public static Image toTransparent(BufferedImage bufferedImage, final Color color, final int i) {
        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(bufferedImage.getSource(), new RGBImageFilter() { // from class: com.vladsch.flexmark.util.misc.ImageUtils.1
            public final int markerRGB;
            final int radius;

            {
                this.markerRGB = color.getRGB() | (-16777216);
                this.radius = i * i * 3;
            }

            public final int filterRGB(int i2, int i3, int i4) {
                if (i == 0 && (i4 | (-16777216)) == this.markerRGB) {
                    return 16777215 & i4;
                }
                if ((i4 & (-16777216)) == -16777216) {
                    int i5 = ((i4 >> 16) & 255) - ((this.markerRGB >> 16) & 255);
                    int i6 = ((i4 >> 8) & 255) - ((this.markerRGB >> 8) & 255);
                    int i7 = (i4 & 255) - (this.markerRGB & 255);
                    if ((i5 * i5) + (i6 * i6) + (i7 * i7) <= this.radius) {
                        return 16777215 & i4;
                    }
                }
                return i4;
            }
        }));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.io.OutputStream, java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.IOException] */
    /* JADX WARN: Type inference failed for: r0v8, types: [byte[]] */
    public static byte[] getImageBytes(BufferedImage bufferedImage) {
        ?? byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "PNG", (OutputStream) byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            byteArrayOutputStream = byteArray;
            return byteArrayOutputStream;
        } catch (IOException e) {
            byteArrayOutputStream.printStackTrace();
            return null;
        }
    }
}
