package org.a.b.c;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:org/a/b/c/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private final byte[] f4256a = new byte[512];

    public final org.a.b.c.a a(String str) {
        InputStream inputStream = null;
        try {
            inputStream = b(str);
            org.a.b.c.a a2 = a(inputStream);
            if (inputStream != null) {
                inputStream.close();
            }
            return a2;
        } catch (Throwable th) {
            if (inputStream != null) {
                inputStream.close();
            }
            throw th;
        }
    }

    public final org.a.b.c.a a(InputStream inputStream) {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
        org.a.b.c.a aVar = new org.a.b.c.a();
        Object obj = null;
        while (true) {
            Object obj2 = obj;
            Object a2 = a(pushbackInputStream);
            if (a2 == null) {
                break;
            }
            if (a2 instanceof C0044b) {
                C0044b c0044b = (C0044b) a2;
                if (c0044b.f4258a.equals("usecmap")) {
                    a((a) obj2, aVar);
                } else {
                    if (c0044b.f4258a.equals("endcmap")) {
                        break;
                    }
                    if (c0044b.f4258a.equals("begincodespacerange")) {
                        a((Number) obj2, pushbackInputStream, aVar);
                    } else if (c0044b.f4258a.equals("beginbfchar")) {
                        b((Number) obj2, pushbackInputStream, aVar);
                    } else if (c0044b.f4258a.equals("beginbfrange")) {
                        d((Number) obj2, pushbackInputStream, aVar);
                    } else if (c0044b.f4258a.equals("begincidchar")) {
                        c((Number) obj2, pushbackInputStream, aVar);
                    } else if (c0044b.f4258a.equals("begincidrange")) {
                        a(((Integer) obj2).intValue(), pushbackInputStream, aVar);
                    }
                }
            } else if (a2 instanceof a) {
                a((a) a2, pushbackInputStream, aVar);
            }
            obj = a2;
        }
        return aVar;
    }

    private void a(a aVar, org.a.b.c.a aVar2) {
        aVar2.a(a(b(aVar.f4257a)));
    }

    private void a(a aVar, PushbackInputStream pushbackInputStream, org.a.b.c.a aVar2) {
        if ("WMode".equals(aVar.f4257a)) {
            Object a2 = a(pushbackInputStream);
            if (a2 instanceof Integer) {
                ((Integer) a2).intValue();
                return;
            }
            return;
        }
        if ("CMapName".equals(aVar.f4257a)) {
            Object a3 = a(pushbackInputStream);
            if (a3 instanceof a) {
                aVar2.a(((a) a3).f4257a);
                return;
            }
            return;
        }
        if ("CMapVersion".equals(aVar.f4257a)) {
            if (a(pushbackInputStream) instanceof Number) {
                return;
            } else {
                return;
            }
        }
        if ("CMapType".equals(aVar.f4257a)) {
            Object a4 = a(pushbackInputStream);
            if (a4 instanceof Integer) {
                ((Integer) a4).intValue();
                return;
            }
            return;
        }
        if ("Registry".equals(aVar.f4257a)) {
            Object a5 = a(pushbackInputStream);
            if (a5 instanceof String) {
                aVar2.b((String) a5);
                return;
            }
            return;
        }
        if ("Ordering".equals(aVar.f4257a)) {
            Object a6 = a(pushbackInputStream);
            if (a6 instanceof String) {
                aVar2.c((String) a6);
                return;
            }
            return;
        }
        if ("Supplement".equals(aVar.f4257a)) {
            Object a7 = a(pushbackInputStream);
            if (a7 instanceof Integer) {
                ((Integer) a7).intValue();
            }
        }
    }

    private void a(Number number, PushbackInputStream pushbackInputStream, org.a.b.c.a aVar) {
        for (int i = 0; i < number.intValue(); i++) {
            Object a2 = a(pushbackInputStream);
            if (!(a2 instanceof C0044b)) {
                byte[] bArr = (byte[]) a2;
                byte[] bArr2 = (byte[]) a(pushbackInputStream);
                d dVar = new d();
                dVar.b(bArr);
                dVar.a(bArr2);
                aVar.a(dVar);
            } else {
                if (!((C0044b) a2).f4258a.equals("endcodespacerange")) {
                    throw new IOException("Error : ~codespacerange contains an unexpected operator : " + ((C0044b) a2).f4258a);
                }
                return;
            }
        }
    }

    private void b(Number number, PushbackInputStream pushbackInputStream, org.a.b.c.a aVar) {
        for (int i = 0; i < number.intValue(); i++) {
            Object a2 = a(pushbackInputStream);
            if (!(a2 instanceof C0044b)) {
                byte[] bArr = (byte[]) a2;
                Object a3 = a(pushbackInputStream);
                if (a3 instanceof byte[]) {
                    aVar.a(bArr, c((byte[]) a3));
                } else if (a3 instanceof a) {
                    aVar.a(bArr, ((a) a3).f4257a);
                } else {
                    throw new IOException("Error parsing CMap beginbfchar, expected{COSString or COSName} and not " + a3);
                }
            } else {
                if (!((C0044b) a2).f4258a.equals("endbfchar")) {
                    throw new IOException("Error : ~bfchar contains an unexpected operator : " + ((C0044b) a2).f4258a);
                }
                return;
            }
        }
    }

    private void a(int i, PushbackInputStream pushbackInputStream, org.a.b.c.a aVar) {
        for (int i2 = 0; i2 < i; i2++) {
            Object a2 = a(pushbackInputStream);
            if (!(a2 instanceof C0044b)) {
                byte[] bArr = (byte[]) a2;
                int b2 = b(bArr);
                byte[] bArr2 = (byte[]) a(pushbackInputStream);
                int b3 = b(bArr2);
                int intValue = ((Integer) a(pushbackInputStream)).intValue();
                if (bArr.length <= 2 && bArr2.length <= 2) {
                    if (b3 == b2) {
                        aVar.a(intValue, b2);
                    } else {
                        aVar.a((char) b2, (char) b3, intValue);
                    }
                } else {
                    int i3 = (intValue + b3) - b2;
                    while (intValue <= i3) {
                        int i4 = intValue;
                        intValue++;
                        aVar.a(i4, b(bArr));
                        a(bArr);
                    }
                }
            } else {
                if (!((C0044b) a2).f4258a.equals("endcidrange")) {
                    throw new IOException("Error : ~cidrange contains an unexpected operator : " + ((C0044b) a2).f4258a);
                }
                return;
            }
        }
    }

    private void c(Number number, PushbackInputStream pushbackInputStream, org.a.b.c.a aVar) {
        for (int i = 0; i < number.intValue(); i++) {
            Object a2 = a(pushbackInputStream);
            if (!(a2 instanceof C0044b)) {
                aVar.a(((Integer) a(pushbackInputStream)).intValue(), b((byte[]) a2));
            } else {
                if (!((C0044b) a2).f4258a.equals("endcidchar")) {
                    throw new IOException("Error : ~cidchar contains an unexpected operator : " + ((C0044b) a2).f4258a);
                }
                return;
            }
        }
    }

    private void d(Number number, PushbackInputStream pushbackInputStream, org.a.b.c.a aVar) {
        for (int i = 0; i < number.intValue(); i++) {
            Object a2 = a(pushbackInputStream);
            if (!(a2 instanceof C0044b)) {
                byte[] bArr = (byte[]) a2;
                byte[] bArr2 = (byte[]) a(pushbackInputStream);
                int a3 = org.a.b.c.a.a(bArr, bArr.length);
                int a4 = org.a.b.c.a.a(bArr2, bArr2.length);
                if (a4 >= a3) {
                    Object a5 = a(pushbackInputStream);
                    if (a5 instanceof List) {
                        List<byte[]> list = (List) a5;
                        if (!list.isEmpty() && list.size() >= a4 - a3) {
                            a(aVar, bArr, list);
                        }
                    } else if (!(a5 instanceof byte[])) {
                        continue;
                    } else if (a4 - a3 <= 255) {
                        byte[] bArr3 = (byte[]) a5;
                        if (bArr3.length > 0) {
                            a(aVar, bArr, (a4 - a3) + 1, bArr3);
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                if (!((C0044b) a2).f4258a.equals("endbfrange")) {
                    throw new IOException("Error : ~bfrange contains an unexpected operator : " + ((C0044b) a2).f4258a);
                }
                return;
            }
        }
    }

    private void a(org.a.b.c.a aVar, byte[] bArr, List<byte[]> list) {
        Iterator<byte[]> it = list.iterator();
        while (it.hasNext()) {
            aVar.a(bArr, c(it.next()));
            a(bArr);
        }
    }

    private void a(org.a.b.c.a aVar, byte[] bArr, int i, byte[] bArr2) {
        for (int i2 = 0; i2 < i; i2++) {
            aVar.a(bArr, c(bArr2));
            a(bArr);
            a(bArr2);
        }
    }

    private InputStream b(String str) {
        InputStream resourceAsStream = getClass().getResourceAsStream(str);
        if (resourceAsStream == null) {
            throw new IOException("Error: Could not find referenced cmap stream " + str);
        }
        return resourceAsStream;
    }

    private Object a(PushbackInputStream pushbackInputStream) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        Object obj = null;
        int read = pushbackInputStream.read();
        while (true) {
            i = read;
            if (i != 9 && i != 32 && i != 13 && i != 10) {
                break;
            }
            read = pushbackInputStream.read();
        }
        switch (i) {
            case -1:
                break;
            case 37:
                StringBuilder sb = new StringBuilder();
                sb.append((char) i);
                a(pushbackInputStream, sb);
                obj = sb.toString();
                break;
            case 40:
                StringBuilder sb2 = new StringBuilder();
                int read2 = pushbackInputStream.read();
                while (true) {
                    int i7 = read2;
                    if (i7 != -1 && i7 != 41) {
                        sb2.append((char) i7);
                        read2 = pushbackInputStream.read();
                    }
                }
                obj = sb2.toString();
                break;
            case 47:
                StringBuilder sb3 = new StringBuilder();
                int read3 = pushbackInputStream.read();
                while (true) {
                    i4 = read3;
                    if (!a(i4) && !b(i4)) {
                        sb3.append((char) i4);
                        read3 = pushbackInputStream.read();
                    }
                }
                if (b(i4)) {
                    pushbackInputStream.unread(i4);
                }
                obj = new a(sb3.toString(), (byte) 0);
                break;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                StringBuilder sb4 = new StringBuilder();
                sb4.append((char) i);
                int read4 = pushbackInputStream.read();
                while (true) {
                    i3 = read4;
                    if (!a(i3) && (Character.isDigit((char) i3) || i3 == 46)) {
                        sb4.append((char) i3);
                        read4 = pushbackInputStream.read();
                    }
                }
                pushbackInputStream.unread(i3);
                String sb5 = sb4.toString();
                if (sb5.indexOf(46) >= 0) {
                    obj = Double.valueOf(sb5);
                    break;
                } else {
                    obj = Integer.valueOf(sb5);
                    break;
                }
                break;
            case 60:
                int read5 = pushbackInputStream.read();
                int i8 = read5;
                if (read5 == 60) {
                    HashMap hashMap = new HashMap();
                    Object a2 = a(pushbackInputStream);
                    while (true) {
                        Object obj2 = a2;
                        if ((obj2 instanceof a) && !">>".equals(obj2)) {
                            hashMap.put(((a) obj2).f4257a, a(pushbackInputStream));
                            a2 = a(pushbackInputStream);
                        }
                    }
                    obj = hashMap;
                    break;
                } else {
                    int i9 = 16;
                    int i10 = -1;
                    while (i8 != -1 && i8 != 62) {
                        if (i8 >= 48 && i8 <= 57) {
                            i5 = i8 - 48;
                        } else if (i8 >= 65 && i8 <= 70) {
                            i5 = (i8 + 10) - 65;
                        } else if (i8 >= 97 && i8 <= 102) {
                            i5 = (i8 + 10) - 97;
                        } else if (a(i8)) {
                            i8 = pushbackInputStream.read();
                        } else {
                            throw new IOException("Error: expected hex character and not " + ((char) i8) + ":" + i8);
                        }
                        int i11 = i5 * i9;
                        if (i9 == 16) {
                            i10++;
                            this.f4256a[i10] = 0;
                            i6 = 1;
                        } else {
                            i6 = 16;
                        }
                        i9 = i6;
                        byte[] bArr = this.f4256a;
                        int i12 = i10;
                        bArr[i12] = (byte) (bArr[i12] + i11);
                        i8 = pushbackInputStream.read();
                    }
                    byte[] bArr2 = new byte[i10 + 1];
                    System.arraycopy(this.f4256a, 0, bArr2, 0, i10 + 1);
                    obj = bArr2;
                    break;
                }
                break;
            case 62:
                if (pushbackInputStream.read() == 62) {
                    obj = ">>";
                    break;
                } else {
                    throw new IOException("Error: expected the end of a dictionary.");
                }
            case 91:
                ArrayList arrayList = new ArrayList();
                Object a3 = a(pushbackInputStream);
                while (true) {
                    Object obj3 = a3;
                    if (obj3 != null && !"]".equals(obj3)) {
                        arrayList.add(obj3);
                        a3 = a(pushbackInputStream);
                    }
                }
                obj = arrayList;
                break;
            case 93:
                obj = "]";
                break;
            default:
                StringBuilder sb6 = new StringBuilder();
                sb6.append((char) i);
                int read6 = pushbackInputStream.read();
                while (true) {
                    i2 = read6;
                    if (!a(i2) && !b(i2) && !Character.isDigit(i2)) {
                        sb6.append((char) i2);
                        read6 = pushbackInputStream.read();
                    }
                }
                if (b(i2) || Character.isDigit(i2)) {
                    pushbackInputStream.unread(i2);
                }
                obj = new C0044b(sb6.toString(), (byte) 0);
                break;
        }
        return obj;
    }

    private static void a(InputStream inputStream, StringBuilder sb) {
        int read = inputStream.read();
        while (true) {
            int i = read;
            if (i != -1 && i != 13 && i != 10) {
                sb.append((char) i);
                read = inputStream.read();
            } else {
                return;
            }
        }
    }

    private static boolean a(int i) {
        return i == -1 || i == 32 || i == 13 || i == 10;
    }

    private static boolean b(int i) {
        switch (i) {
            case 37:
            case 40:
            case 41:
            case 47:
            case 60:
            case 62:
            case 91:
            case 93:
            case 123:
            case 125:
                return true;
            default:
                return false;
        }
    }

    private void a(byte[] bArr) {
        a(bArr, bArr.length - 1);
    }

    private void a(byte[] bArr, int i) {
        if (i > 0 && (bArr[i] & 255) == 255) {
            bArr[i] = 0;
            a(bArr, i - 1);
        } else {
            bArr[i] = (byte) (bArr[i] + 1);
        }
    }

    private static int b(byte[] bArr) {
        int i = bArr[0] & 255;
        if (bArr.length == 2) {
            i = (i << 8) + (bArr[1] & 255);
        }
        return i;
    }

    private static String c(byte[] bArr) {
        return new String(bArr, bArr.length == 1 ? org.a.b.h.b.f4351a : org.a.b.h.b.c);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/c/b$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f4257a;

        /* synthetic */ a(String str, byte b2) {
            this(str);
        }

        private a(String str) {
            this.f4257a = str;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.a.b.c.b$b, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/a/b/c/b$b.class */
    public static final class C0044b {

        /* renamed from: a, reason: collision with root package name */
        private String f4258a;

        /* synthetic */ C0044b(String str, byte b2) {
            this(str);
        }

        private C0044b(String str) {
            this.f4258a = str;
        }
    }
}
