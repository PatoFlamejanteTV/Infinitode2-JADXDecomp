package com.a.a.a;

import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

@Deprecated
/* loaded from: infinitode-2.jar:com/a/a/a/am.class */
public class am {

    /* renamed from: a, reason: collision with root package name */
    @Deprecated
    public int f47a;

    /* renamed from: b, reason: collision with root package name */
    private X509Certificate f48b;
    private org.a.c.h.c.j c;

    /* loaded from: infinitode-2.jar:com/a/a/a/am$b.class */
    public static abstract class b extends al<Object> {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/a/am$a.class */
    public static abstract class a<T> extends al<T> {

        /* renamed from: a, reason: collision with root package name */
        protected final Class<?> f49a;

        protected a(Class<?> cls) {
            this.f49a = cls;
        }

        @Override // com.a.a.a.al
        public final Class<?> a() {
            return this.f49a;
        }

        @Override // com.a.a.a.al
        public boolean a(al<?> alVar) {
            return alVar.getClass() == getClass() && alVar.a() == this.f49a;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/a/am$c.class */
    public static abstract class c extends a<Object> {
        @Override // com.a.a.a.am.a, com.a.a.a.al
        public /* bridge */ /* synthetic */ boolean a(al alVar) {
            return super.a((al<?>) alVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public c(Class<?> cls) {
            super(cls);
        }
    }

    @Deprecated
    public static int a(CharSequence charSequence, int i) {
        if (i < 0 || i > 1114111) {
            throw new IllegalArgumentException();
        }
        int length = charSequence.length();
        if (length == 0) {
            return -1;
        }
        char charAt = charSequence.charAt(0);
        int i2 = i - 65536;
        if (i2 < 0) {
            int i3 = charAt - i;
            if (i3 != 0) {
                return i3;
            }
            return length - 1;
        }
        int i4 = charAt - ((char) ((i2 >>> 10) + 55296));
        if (i4 != 0) {
            return i4;
        }
        if (length > 1) {
            int charAt2 = charSequence.charAt(1) - ((char) ((i2 & 1023) + 56320));
            if (charAt2 != 0) {
                return charAt2;
            }
        }
        return length - 2;
    }

    public static int a(String str, int i) {
        char charAt = str.charAt(i);
        if (charAt < 55296) {
            return charAt;
        }
        return a(str, i, charAt);
    }

    private static int a(String str, int i, char c2) {
        char charAt;
        char charAt2;
        if (c2 > 57343) {
            return c2;
        }
        if (c2 <= 56319) {
            int i2 = i + 1;
            if (str.length() != i2 && (charAt2 = str.charAt(i2)) >= 56320 && charAt2 <= 57343) {
                return Character.toCodePoint(c2, charAt2);
            }
        } else {
            int i3 = i - 1;
            if (i3 >= 0 && (charAt = str.charAt(i3)) >= 55296 && charAt <= 56319) {
                return Character.toCodePoint(charAt, c2);
            }
        }
        return c2;
    }

    public static int a(char[] cArr, int i, int i2, int i3) {
        if (i3 < 0 || i3 >= i2) {
            throw new ArrayIndexOutOfBoundsException(i3);
        }
        char c2 = cArr[i3];
        if (!a(c2)) {
            return c2;
        }
        if (c2 <= 56319) {
            int i4 = i3 + 1;
            if (i4 >= i2) {
                return c2;
            }
            char c3 = cArr[i4];
            if (b(c3)) {
                return Character.toCodePoint(c2, c3);
            }
        } else {
            if (i3 == 0) {
                return c2;
            }
            char c4 = cArr[i3 - 1];
            if (c(c4)) {
                return Character.toCodePoint(c4, c2);
            }
        }
        return c2;
    }

    public static int a(int i) {
        if (i < 65536) {
            return 1;
        }
        return 2;
    }

    public static boolean a(char c2) {
        return (c2 & 63488) == 55296;
    }

    public static boolean b(char c2) {
        return (c2 & 64512) == 56320;
    }

    public static boolean c(char c2) {
        return (c2 & 64512) == 55296;
    }

    public static char b(int i) {
        if (i >= 65536) {
            return (char) (55232 + (i >> 10));
        }
        return (char) 0;
    }

    public static char c(int i) {
        if (i >= 65536) {
            return (char) (56320 + (i & 1023));
        }
        return (char) i;
    }

    public static String d(int i) {
        if (i < 0 || i > 1114111) {
            throw new IllegalArgumentException("Illegal codepoint");
        }
        return e(i);
    }

    public static StringBuffer a(StringBuffer stringBuffer, int i) {
        if (i < 0 || i > 1114111) {
            throw new IllegalArgumentException("Illegal codepoint: " + Integer.toHexString(i));
        }
        if (i >= 65536) {
            stringBuffer.append(b(i));
            stringBuffer.append(c(i));
        } else {
            stringBuffer.append((char) i);
        }
        return stringBuffer;
    }

    private static String e(int i) {
        if (i < 65536) {
            return String.valueOf((char) i);
        }
        StringBuilder sb = new StringBuilder();
        sb.append(b(i));
        sb.append(c(i));
        return sb.toString();
    }

    @Deprecated
    public String toString() {
        return Integer.toString(this.f47a);
    }

    public static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static long a(InputStream inputStream, OutputStream outputStream) {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            long j2 = j;
            int read = inputStream.read(bArr);
            if (-1 != read) {
                outputStream.write(bArr, 0, read);
                j = j2 + read;
            } else {
                return j2;
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static IOException a(Closeable closeable, org.a.a.a.a aVar, String str, IOException iOException) {
        try {
            closeable.close();
        } catch (IOException e) {
            new StringBuilder("Error closing ").append(str);
            if (iOException == null) {
                return e;
            }
        }
        return iOException;
    }

    public X509Certificate a() {
        return this.f48b;
    }

    public org.a.c.h.c.j b() {
        return this.c;
    }

    public static org.a.c.h.f.c.b a(org.a.c.h.b bVar, InputStream inputStream) {
        return a(bVar, a(inputStream));
    }

    private static org.a.c.h.f.c.b a(org.a.c.h.b bVar, byte[] bArr) {
        org.a.c.h.f.a.h hVar;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        Raster b2 = b(byteArrayInputStream);
        byteArrayInputStream.reset();
        switch (b2.getNumDataElements()) {
            case 1:
                hVar = org.a.c.h.f.a.i.f4574a;
                break;
            case 2:
            default:
                throw new UnsupportedOperationException("number of data elements not supported: " + b2.getNumDataElements());
            case 3:
                hVar = org.a.c.h.f.a.m.f4580a;
                break;
            case 4:
                hVar = org.a.c.h.f.a.g.f4572a;
                break;
        }
        org.a.c.h.f.c.b bVar2 = new org.a.c.h.f.c.b(bVar, byteArrayInputStream, org.a.c.b.j.ao, b2.getWidth(), b2.getHeight(), 8, hVar);
        if (hVar instanceof org.a.c.h.f.a.g) {
            org.a.c.b.a aVar = new org.a.c.b.a();
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4367b);
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4366a);
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4367b);
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4366a);
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4367b);
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4366a);
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4367b);
            aVar.a((org.a.c.b.b) org.a.c.b.i.f4366a);
            bVar2.a(aVar);
        }
        return bVar2;
    }

    private static Raster b(InputStream inputStream) {
        Iterator imageReadersByFormatName = ImageIO.getImageReadersByFormatName("JPEG");
        ImageReader imageReader = null;
        while (imageReadersByFormatName.hasNext()) {
            ImageReader imageReader2 = (ImageReader) imageReadersByFormatName.next();
            imageReader = imageReader2;
            if (imageReader2.canReadRaster()) {
                break;
            }
        }
        if (imageReader == null) {
            throw new org.a.c.c.s("Cannot read JPEG image: a suitable JAI I/O image filter is not installed");
        }
        ImageInputStream imageInputStream = null;
        try {
            imageInputStream = ImageIO.createImageInputStream(inputStream);
            imageReader.setInput(imageInputStream);
            ImageIO.setUseCache(false);
            Raster readRaster = imageReader.readRaster(0, (ImageReadParam) null);
            if (imageInputStream != null) {
                imageInputStream.close();
            }
            imageReader.dispose();
            return readRaster;
        } catch (Throwable th) {
            if (imageInputStream != null) {
                imageInputStream.close();
            }
            imageReader.dispose();
            throw th;
        }
    }

    public static org.a.c.h.g.a.a a(org.a.c.b.d dVar) {
        org.a.c.h.g.a.a aVar = null;
        if (dVar != null) {
            String g = dVar.g(org.a.c.b.j.dn);
            if ("JavaScript".equals(g)) {
                aVar = new org.a.c.h.g.a.f(dVar);
            } else if ("GoTo".equals(g)) {
                aVar = new org.a.c.h.g.a.c(dVar);
            } else if ("Launch".equals(g)) {
                aVar = new org.a.c.h.g.a.g(dVar);
            } else if ("GoToR".equals(g)) {
                aVar = new org.a.c.h.g.a.j(dVar);
            } else if ("URI".equals(g)) {
                aVar = new org.a.c.h.g.a.o(dVar);
            } else if ("Named".equals(g)) {
                aVar = new org.a.c.h.g.a.i(dVar);
            } else if ("Sound".equals(g)) {
                aVar = new org.a.c.h.g.a.l(dVar);
            } else if ("Movie".equals(g)) {
                aVar = new org.a.c.h.g.a.h(dVar);
            } else if ("ImportData".equals(g)) {
                aVar = new org.a.c.h.g.a.e(dVar);
            } else if ("ResetForm".equals(g)) {
                aVar = new org.a.c.h.g.a.k(dVar);
            } else if ("Hide".equals(g)) {
                aVar = new org.a.c.h.g.a.d(dVar);
            } else if ("SubmitForm".equals(g)) {
                aVar = new org.a.c.h.g.a.m(dVar);
            } else if ("Thread".equals(g)) {
                aVar = new org.a.c.h.g.a.n(dVar);
            } else if ("GoToE".equals(g)) {
                aVar = new org.a.c.h.g.a.b(dVar);
            }
        }
        return aVar;
    }
}
