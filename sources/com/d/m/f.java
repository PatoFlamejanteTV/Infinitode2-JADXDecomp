package com.d.m;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.imageio.ImageIO;

/* loaded from: infinitode-2.jar:com/d/m/f.class */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final Map f1421a;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.d.m.f$f, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/m/f$f.class */
    public interface InterfaceC0030f {
        BufferedImage a(BufferedImage bufferedImage, h hVar);
    }

    static {
        HashMap hashMap = new HashMap();
        f1421a = hashMap;
        hashMap.put(com.d.m.d.c, new e());
        f1421a.put(com.d.m.d.f1419a, new d());
        f1421a.put(com.d.m.d.f1420b, new c());
        f1421a.put(com.d.m.d.d, new b());
    }

    public static BufferedImage a(BufferedImage bufferedImage) {
        BufferedImage createCompatibleImage;
        if (GraphicsEnvironment.isHeadless()) {
            createCompatibleImage = a(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getTransparency());
        } else {
            GraphicsConfiguration a2 = a();
            if (bufferedImage.getColorModel().equals(a2.getColorModel())) {
                return bufferedImage;
            }
            createCompatibleImage = a2.createCompatibleImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getTransparency());
        }
        Graphics graphics = createCompatibleImage.getGraphics();
        graphics.drawImage(bufferedImage, 0, 0, (ImageObserver) null);
        graphics.dispose();
        return createCompatibleImage;
    }

    public static BufferedImage a(int i, int i2, int i3) {
        BufferedImage createCompatibleImage;
        if (GraphicsEnvironment.getLocalGraphicsEnvironment().isHeadlessInstance()) {
            createCompatibleImage = new BufferedImage(i, i2, i3);
        } else {
            createCompatibleImage = a().createCompatibleImage(i, i2, (i3 == 2 || i3 == 3) ? 3 : 1);
        }
        return createCompatibleImage;
    }

    private static GraphicsConfiguration a() {
        return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
    }

    private static BufferedImage a(h hVar, BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth((ImageObserver) null);
        int height = bufferedImage.getHeight((ImageObserver) null);
        if (hVar.a(width, height)) {
            return bufferedImage;
        }
        int b2 = hVar.b() <= 0 ? width : hVar.b();
        int c2 = hVar.c() <= 0 ? height : hVar.c();
        InterfaceC0030f interfaceC0030f = (InterfaceC0030f) f1421a.get(hVar.a());
        hVar.a(b2);
        hVar.b(c2);
        return interfaceC0030f.a(bufferedImage, hVar);
    }

    public static BufferedImage a(BufferedImage bufferedImage, int i, int i2) {
        return a(new h(i, i2, com.d.m.d.a(com.d.m.b.a("xr.image.scale", com.d.m.d.f1419a.a()), com.d.m.d.f1419a), com.d.m.b.a("xr.image.render-quality", RenderingHints.VALUE_INTERPOLATION_BICUBIC)), bufferedImage);
    }

    public static BufferedImage a(Image image, int i) {
        BufferedImage bufferedImage;
        if (image instanceof BufferedImage) {
            bufferedImage = (BufferedImage) image;
        } else {
            BufferedImage a2 = a(image.getWidth((ImageObserver) null), image.getHeight((ImageObserver) null), i);
            bufferedImage = a2;
            Graphics2D createGraphics = a2.createGraphics();
            createGraphics.drawImage(image, 0, 0, (Color) null, (ImageObserver) null);
            createGraphics.dispose();
        }
        return bufferedImage;
    }

    public static BufferedImage a(int i, int i2) {
        BufferedImage a2 = a(1, 1, 2);
        Graphics2D createGraphics = a2.createGraphics();
        createGraphics.setColor(new Color(0, 0, 0, 0));
        createGraphics.setComposite(AlphaComposite.Src);
        createGraphics.fillRect(0, 0, 1, 1);
        createGraphics.dispose();
        return a2;
    }

    public static boolean a(String str) {
        return str != null && str.startsWith("data:image/");
    }

    public static boolean b(String str) {
        return str != null && str.startsWith("data:");
    }

    public static byte[] c(String str) {
        int indexOf = str.indexOf("base64,");
        if (indexOf != -1) {
            return Base64.getMimeDecoder().decode(str.substring(indexOf + 7));
        }
        l.e(Level.SEVERE, "Embedded data uris must be encoded in base 64.");
        return null;
    }

    public static byte[] d(String str) {
        return c(str);
    }

    public static BufferedImage e(String str) {
        try {
            byte[] c2 = c(str);
            if (c2 != null) {
                return ImageIO.read(new ByteArrayInputStream(c2));
            }
            return null;
        } catch (IOException e2) {
            l.a("Can't read XHTML embedded image", e2);
            return null;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/m/f$a.class */
    static abstract class a implements InterfaceC0030f {
        protected abstract int a();

        a() {
        }

        @Override // com.d.m.f.InterfaceC0030f
        public final BufferedImage a(BufferedImage bufferedImage, h hVar) {
            return f.a(bufferedImage.getScaledInstance(hVar.b(), hVar.c(), a()), bufferedImage.getType());
        }
    }

    /* loaded from: infinitode-2.jar:com/d/m/f$e.class */
    static class e extends a {
        e() {
        }

        @Override // com.d.m.f.a
        protected final int a() {
            return 2;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/m/f$b.class */
    static class b extends a {
        b() {
        }

        @Override // com.d.m.f.a
        protected final int a() {
            return 16;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/m/f$c.class */
    static class c implements InterfaceC0030f {
        c() {
        }

        @Override // com.d.m.f.InterfaceC0030f
        public final BufferedImage a(BufferedImage bufferedImage, h hVar) {
            int b2 = hVar.b();
            int c = hVar.c();
            BufferedImage a2 = f.a(b2, c, bufferedImage.getType());
            Graphics2D createGraphics = a2.createGraphics();
            hVar.a(createGraphics);
            createGraphics.drawImage(bufferedImage, 0, 0, b2, c, (ImageObserver) null);
            createGraphics.dispose();
            return a2;
        }
    }

    /* loaded from: infinitode-2.jar:com/d/m/f$d.class */
    static class d implements InterfaceC0030f {
        d() {
        }

        @Override // com.d.m.f.InterfaceC0030f
        public final BufferedImage a(BufferedImage bufferedImage, h hVar) {
            int b2;
            int c;
            int width = bufferedImage.getWidth((ImageObserver) null);
            int height = bufferedImage.getHeight((ImageObserver) null);
            if (hVar.b() < width && hVar.c() < height) {
                b2 = width;
                c = height;
            } else {
                b2 = hVar.b();
                c = hVar.c();
            }
            BufferedImage bufferedImage2 = bufferedImage;
            while (true) {
                if (b2 > hVar.b()) {
                    int i = b2 / 2;
                    b2 = i;
                    if (i < hVar.b()) {
                        b2 = hVar.b();
                    }
                }
                if (c > hVar.c()) {
                    int i2 = c / 2;
                    c = i2;
                    if (i2 < hVar.c()) {
                        c = hVar.c();
                    }
                }
                BufferedImage a2 = f.a(b2, c, bufferedImage.getType());
                Graphics2D createGraphics = a2.createGraphics();
                hVar.a(createGraphics);
                createGraphics.drawImage(bufferedImage2, 0, 0, b2, c, (ImageObserver) null);
                createGraphics.dispose();
                bufferedImage2 = a2;
                if (b2 == hVar.b() && c == hVar.c()) {
                    return bufferedImage2;
                }
            }
        }
    }
}
