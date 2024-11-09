package com.b.a.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/b/a/a/f.class */
public final class f {

    /* renamed from: a, reason: collision with root package name */
    private static final List<c> f798a;

    /* renamed from: b, reason: collision with root package name */
    private static /* synthetic */ boolean f799b;

    /* loaded from: infinitode-2.jar:com/b/a/a/f$a.class */
    public interface a {
        boolean a(byte[] bArr);
    }

    static {
        f799b = !f.class.desiredAssertionStatus();
        f798a = new ArrayList();
        String a2 = g.a(f.class.getName() + ".dataPath");
        if (a2 != null) {
            c(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/f$b.class */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private static final a f800a;

        /* renamed from: b, reason: collision with root package name */
        private static /* synthetic */ boolean f801b;

        static {
            f801b = !f.class.desiredAssertionStatus();
            f800a = new a((byte) 0);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:com/b/a/a/f$b$a.class */
        public static final class a implements a {
            private a() {
            }

            /* synthetic */ a(byte b2) {
                this();
            }

            @Override // com.b.a.a.f.a
            public final boolean a(byte[] bArr) {
                return bArr[0] == 1;
            }
        }

        static boolean a(ByteBuffer byteBuffer) {
            try {
                f.b(byteBuffer, 1131245124, f800a);
                int i = byteBuffer.getInt(byteBuffer.position());
                if (i <= 0 || byteBuffer.position() + 4 + (i * 24) > byteBuffer.capacity() || !a(byteBuffer, b(byteBuffer, 0)) || !a(byteBuffer, b(byteBuffer, i - 1))) {
                    return false;
                }
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        private static boolean a(ByteBuffer byteBuffer, int i) {
            int i2 = 8 - 1;
            for (int i3 = 0; i3 < i2; i3++) {
                if (byteBuffer.get(i + i3) != "icudt59b".charAt(i3)) {
                    return false;
                }
            }
            int i4 = i2 + 1;
            byte b2 = byteBuffer.get(i + i2);
            if ((b2 != 98 && b2 != 108) || byteBuffer.get(i + i4) != 47) {
                return false;
            }
            return true;
        }

        static ByteBuffer a(ByteBuffer byteBuffer, CharSequence charSequence) {
            int b2 = b(byteBuffer, charSequence);
            if (b2 >= 0) {
                ByteBuffer duplicate = byteBuffer.duplicate();
                duplicate.position(c(byteBuffer, b2));
                duplicate.limit(c(byteBuffer, b2 + 1));
                return f.a(duplicate);
            }
            return null;
        }

        private static int b(ByteBuffer byteBuffer, CharSequence charSequence) {
            int i = 0;
            int i2 = byteBuffer.getInt(byteBuffer.position());
            while (i < i2) {
                int i3 = (i + i2) >>> 1;
                int a2 = f.a(charSequence, byteBuffer, b(byteBuffer, i3) + 8 + 1);
                if (a2 < 0) {
                    i2 = i3;
                } else if (a2 > 0) {
                    i = i3 + 1;
                } else {
                    return i3;
                }
            }
            return i ^ (-1);
        }

        private static int b(ByteBuffer byteBuffer, int i) {
            int position = byteBuffer.position();
            if (f801b || (i >= 0 && i < byteBuffer.getInt(position))) {
                return position + byteBuffer.getInt(position + 4 + (i << 3));
            }
            throw new AssertionError();
        }

        private static int c(ByteBuffer byteBuffer, int i) {
            int position = byteBuffer.position();
            int i2 = byteBuffer.getInt(position);
            if (i == i2) {
                return byteBuffer.capacity();
            }
            if (f801b || (i >= 0 && i < i2)) {
                return position + byteBuffer.getInt(position + 4 + 4 + (i << 3));
            }
            throw new AssertionError();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/f$c.class */
    public static abstract class c {

        /* renamed from: a, reason: collision with root package name */
        protected final String f802a;

        abstract ByteBuffer a(String str);

        c(String str) {
            this.f802a = str;
        }

        public String toString() {
            return this.f802a;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/f$e.class */
    public static final class e extends c {

        /* renamed from: b, reason: collision with root package name */
        private final File f804b;

        e(String str, File file) {
            super(str);
            this.f804b = file;
        }

        @Override // com.b.a.a.f.c
        public final String toString() {
            return this.f804b.toString();
        }

        @Override // com.b.a.a.f.c
        final ByteBuffer a(String str) {
            if (str.equals(this.f802a)) {
                return f.b(this.f804b);
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/b/a/a/f$d.class */
    public static final class d extends c {

        /* renamed from: b, reason: collision with root package name */
        private final ByteBuffer f803b;

        d(String str, ByteBuffer byteBuffer) {
            super(str);
            this.f803b = byteBuffer;
        }

        @Override // com.b.a.a.f.c
        final ByteBuffer a(String str) {
            return b.a(this.f803b, str);
        }
    }

    private static void c(String str) {
        int length;
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < str.length()) {
                int indexOf = str.indexOf(File.pathSeparatorChar, i2);
                if (indexOf >= 0) {
                    length = indexOf;
                } else {
                    length = str.length();
                }
                String trim = str.substring(i2, length).trim();
                String str2 = trim;
                if (trim.endsWith(File.separator)) {
                    str2 = str2.substring(0, str2.length() - 1);
                }
                if (str2.length() != 0) {
                    a(new File(str2), new StringBuilder(), f798a);
                }
                if (indexOf >= 0) {
                    i = indexOf + 1;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private static void a(File file, StringBuilder sb, List<c> list) {
        File[] listFiles = file.listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        int length = sb.length();
        int i = length;
        if (length > 0) {
            sb.append('/');
            i++;
        }
        for (File file2 : listFiles) {
            String name = file2.getName();
            if (!name.endsWith(".txt")) {
                sb.append(name);
                if (file2.isDirectory()) {
                    a(file2, sb, list);
                } else if (name.endsWith(".dat")) {
                    ByteBuffer b2 = b(file2);
                    if (b2 != null && b.a(b2)) {
                        list.add(new d(sb.toString(), b2));
                    }
                } else {
                    list.add(new e(sb.toString(), file2));
                }
                sb.setLength(i);
            }
        }
    }

    static int a(CharSequence charSequence, ByteBuffer byteBuffer, int i) {
        int i2 = 0;
        while (true) {
            byte b2 = byteBuffer.get(i);
            if (b2 == 0) {
                if (i2 == charSequence.length()) {
                    return 0;
                }
                return 1;
            }
            if (i2 != charSequence.length()) {
                int charAt = charSequence.charAt(i2) - b2;
                if (charAt == 0) {
                    i2++;
                    i++;
                } else {
                    return charAt;
                }
            } else {
                return -1;
            }
        }
    }

    public static ByteBuffer a(String str) {
        return a(null, null, str, false);
    }

    public static ByteBuffer b(String str) {
        return a(null, null, str, true);
    }

    private static ByteBuffer a(ClassLoader classLoader, String str, String str2, boolean z) {
        ByteBuffer d2 = d(str2);
        if (d2 != null) {
            return d2;
        }
        if (classLoader == null) {
            classLoader = com.b.a.a.d.a(i.class);
        }
        if (str == null) {
            str = "com/ibm/icu/impl/data/icudt59b/" + str2;
        }
        try {
            InputStream a2 = i.a(classLoader, str, z);
            if (a2 == null) {
                return null;
            }
            return a(a2);
        } catch (IOException e2) {
            throw new com.b.a.d.c(e2);
        }
    }

    private static ByteBuffer d(String str) {
        Iterator<c> it = f798a.iterator();
        while (it.hasNext()) {
            ByteBuffer a2 = it.next().a(str);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBuffer b(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            try {
                MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                fileInputStream.close();
                return map;
            } catch (Throwable th) {
                fileInputStream.close();
                throw th;
            }
        } catch (FileNotFoundException e2) {
            System.err.println(e2);
            return null;
        } catch (IOException e3) {
            System.err.println(e3);
            return null;
        }
    }

    public static com.b.a.d.d a(ByteBuffer byteBuffer, int i, a aVar) {
        return a(b(byteBuffer, i, aVar));
    }

    public static int b(ByteBuffer byteBuffer, int i, a aVar) {
        if (!f799b && (byteBuffer == null || byteBuffer.position() != 0)) {
            throw new AssertionError();
        }
        byte b2 = byteBuffer.get(2);
        byte b3 = byteBuffer.get(3);
        if (b2 != -38 || b3 != 39) {
            throw new IOException("ICU data file error: Not an ICU data file");
        }
        byte b4 = byteBuffer.get(8);
        byte b5 = byteBuffer.get(9);
        byte b6 = byteBuffer.get(10);
        if (b4 < 0 || 1 < b4 || b5 != 0 || b6 != 2) {
            throw new IOException("ICU data file error: Header authentication failed, please check if you have a valid ICU data file");
        }
        byteBuffer.order(b4 != 0 ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        char c2 = byteBuffer.getChar(0);
        char c3 = byteBuffer.getChar(4);
        if (c3 < 20 || c2 < c3 + 4) {
            throw new IOException("Internal Error: Header size error");
        }
        byte[] bArr = {byteBuffer.get(16), byteBuffer.get(17), byteBuffer.get(18), byteBuffer.get(19)};
        if (byteBuffer.get(12) != ((byte) (i >> 24)) || byteBuffer.get(13) != ((byte) (i >> 16)) || byteBuffer.get(14) != ((byte) (i >> 8)) || byteBuffer.get(15) != ((byte) i) || (aVar != null && !aVar.a(bArr))) {
            throw new IOException("ICU data file error: Header authentication failed, please check if you have a valid ICU data file" + String.format("; data format %02x%02x%02x%02x, format version %d.%d.%d.%d", Byte.valueOf(byteBuffer.get(12)), Byte.valueOf(byteBuffer.get(13)), Byte.valueOf(byteBuffer.get(14)), Byte.valueOf(byteBuffer.get(15)), Integer.valueOf(bArr[0] & 255), Integer.valueOf(bArr[1] & 255), Integer.valueOf(bArr[2] & 255), Integer.valueOf(bArr[3] & 255)));
        }
        byteBuffer.position(c2);
        return (byteBuffer.get(20) << 24) | ((byteBuffer.get(21) & 255) << 16) | ((byteBuffer.get(22) & 255) << 8) | (byteBuffer.get(23) & 255);
    }

    public static void a(ByteBuffer byteBuffer, int i) {
        if (i > 0) {
            byteBuffer.position(byteBuffer.position() + i);
        }
    }

    public static String a(ByteBuffer byteBuffer, int i, int i2) {
        String charSequence = byteBuffer.asCharBuffer().subSequence(0, i).toString();
        a(byteBuffer, i << 1);
        return charSequence;
    }

    public static char[] b(ByteBuffer byteBuffer, int i, int i2) {
        char[] cArr = new char[i];
        byteBuffer.asCharBuffer().get(cArr);
        a(byteBuffer, i << 1);
        return cArr;
    }

    public static int[] c(ByteBuffer byteBuffer, int i, int i2) {
        int[] iArr = new int[i];
        byteBuffer.asIntBuffer().get(iArr);
        a(byteBuffer, i << 2);
        return iArr;
    }

    public static ByteBuffer a(ByteBuffer byteBuffer) {
        return byteBuffer.slice().order(byteBuffer.order());
    }

    private static ByteBuffer a(InputStream inputStream) {
        byte[] bArr;
        int i;
        try {
            int available = inputStream.available();
            if (available > 32) {
                bArr = new byte[available];
            } else {
                bArr = new byte[128];
            }
            i = 0;
        } finally {
            inputStream.close();
        }
        while (true) {
            if (i < bArr.length) {
                int read = inputStream.read(bArr, i, bArr.length - i);
                if (read < 0) {
                    break;
                }
                i += read;
            } else {
                int read2 = inputStream.read();
                if (read2 < 0) {
                    break;
                }
                int length = 2 * bArr.length;
                int i2 = length;
                if (length < 128) {
                    i2 = 128;
                } else if (i2 < 16384) {
                    i2 <<= 1;
                }
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, 0, bArr2, 0, i);
                bArr = bArr2;
                int i3 = i;
                i++;
                bArr2[i3] = (byte) read2;
            }
            inputStream.close();
        }
        return ByteBuffer.wrap(bArr, 0, i);
    }

    private static com.b.a.d.d a(int i) {
        return com.b.a.d.d.a(i >>> 24, (i >> 16) & 255, (i >> 8) & 255, i & 255);
    }
}
