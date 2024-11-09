package org.a.b.f;

import com.vladsch.flexmark.util.html.Attribute;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/f/am.class */
public final class am {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4281a = org.a.a.a.c.a(am.class);

    /* renamed from: b, reason: collision with root package name */
    private static final byte[] f4282b = {0, 0, 0};
    private final ap c;
    private final c d;
    private final List<String> f;
    private String h;
    private boolean i;
    private final SortedMap<Integer, Integer> e = new TreeMap();
    private final SortedSet<Integer> g = new TreeSet();

    public am(ap apVar, List<String> list) {
        this.c = apVar;
        this.f = list;
        this.d = apVar.z();
        this.g.add(0);
    }

    public final void a(String str) {
        this.h = str;
    }

    private void a(int i) {
        int a2 = this.d.a(i);
        if (a2 != 0) {
            this.e.put(Integer.valueOf(i), Integer.valueOf(a2));
            this.g.add(Integer.valueOf(a2));
        }
    }

    public final void a(Set<Integer> set) {
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()) {
            a(it.next().intValue());
        }
    }

    public final Map<Integer, Integer> a() {
        g();
        HashMap hashMap = new HashMap();
        int i = 0;
        Iterator<Integer> it = this.g.iterator();
        while (it.hasNext()) {
            hashMap.put(Integer.valueOf(i), Integer.valueOf(it.next().intValue()));
            i++;
        }
        return hashMap;
    }

    private long a(DataOutputStream dataOutputStream, int i) {
        dataOutputStream.writeInt(65536);
        dataOutputStream.writeShort(i);
        int highestOneBit = Integer.highestOneBit(i);
        int i2 = highestOneBit << 4;
        dataOutputStream.writeShort(i2);
        int b2 = b(highestOneBit);
        dataOutputStream.writeShort(b2);
        int i3 = (i * 16) - i2;
        dataOutputStream.writeShort(i3);
        return 65536 + a(i, i2) + a(b2, i3);
    }

    private long a(DataOutputStream dataOutputStream, String str, long j, byte[] bArr) {
        long j2 = 0;
        for (int i = 0; i < bArr.length; i++) {
            j2 += (bArr[i] & 255) << (24 - ((i % 4) << 3));
        }
        long j3 = j2 & 4294967295L;
        byte[] bytes = str.getBytes("US-ASCII");
        dataOutputStream.write(bytes, 0, 4);
        dataOutputStream.writeInt((int) j3);
        dataOutputStream.writeInt((int) j);
        dataOutputStream.writeInt(bArr.length);
        return a(bytes) + j3 + j3 + j + bArr.length;
    }

    private static void a(OutputStream outputStream, byte[] bArr) {
        int length = bArr.length;
        outputStream.write(bArr);
        if (length % 4 != 0) {
            outputStream.write(f4282b, 0, 4 - (length % 4));
        }
    }

    private byte[] b() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        q n = this.c.n();
        a(dataOutputStream, n.l());
        a(dataOutputStream, n.d());
        a(dataOutputStream, 0L);
        a(dataOutputStream, n.i());
        b(dataOutputStream, n.b());
        b(dataOutputStream, n.k());
        a(dataOutputStream, n.a());
        a(dataOutputStream, n.j());
        a(dataOutputStream, n.n());
        a(dataOutputStream, n.p());
        a(dataOutputStream, n.m());
        a(dataOutputStream, n.o());
        b(dataOutputStream, n.h());
        b(dataOutputStream, n.g());
        a(dataOutputStream, n.c());
        a(dataOutputStream, (short) 1);
        a(dataOutputStream, n.e());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] c() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        r o = this.c.o();
        a(dataOutputStream, o.p());
        a(dataOutputStream, o.b());
        a(dataOutputStream, o.e());
        a(dataOutputStream, o.f());
        b(dataOutputStream, o.a());
        a(dataOutputStream, o.h());
        a(dataOutputStream, o.i());
        a(dataOutputStream, o.q());
        a(dataOutputStream, o.c());
        a(dataOutputStream, o.d());
        a(dataOutputStream, o.k());
        a(dataOutputStream, o.l());
        a(dataOutputStream, o.m());
        a(dataOutputStream, o.n());
        a(dataOutputStream, o.o());
        a(dataOutputStream, o.g());
        int size = this.g.subSet(0, Integer.valueOf(o.j())).size();
        if (this.g.last().intValue() >= o.j() && !this.g.contains(Integer.valueOf(o.j() - 1))) {
            size++;
        }
        b(dataOutputStream, size);
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private static boolean a(y yVar) {
        return yVar.f() == 3 && yVar.e() == 1 && yVar.c() == 1033 && yVar.d() >= 0 && yVar.d() < 7;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private byte[] d() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        z j = this.c.j();
        if (j == null) {
            return null;
        }
        if (this.f != null && !this.f.contains(Attribute.NAME_ATTR)) {
            return null;
        }
        List<y> a2 = j.a();
        int i = 0;
        Iterator<y> it = a2.iterator();
        while (it.hasNext()) {
            if (a(it.next())) {
                i++;
            }
        }
        b(dataOutputStream, 0);
        b(dataOutputStream, i);
        b(dataOutputStream, 6 + (i * 12));
        if (i == 0) {
            return null;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (y yVar : a2) {
            if (a(yVar)) {
                int f = yVar.f();
                int e = yVar.e();
                String str = "ISO-8859-1";
                if (f == 3 && e == 1) {
                    str = "UTF-16BE";
                } else if (f == 2) {
                    if (e == 0) {
                        str = "US-ASCII";
                    } else if (e == 1) {
                        str = "UTF16-BE";
                    } else if (e == 2) {
                        str = "ISO-8859-1";
                    }
                }
                String g = yVar.g();
                if (yVar.d() == 6 && this.h != null) {
                    g = this.h + g;
                }
                bArr[i2] = g.getBytes(str);
                i2++;
            }
        }
        int i3 = 0;
        int i4 = 0;
        for (y yVar2 : a2) {
            if (a(yVar2)) {
                b(dataOutputStream, yVar2.f());
                b(dataOutputStream, yVar2.e());
                b(dataOutputStream, yVar2.c());
                b(dataOutputStream, yVar2.d());
                b(dataOutputStream, bArr[i4].length);
                b(dataOutputStream, i3);
                i3 += bArr[i4].length;
                i4++;
            }
        }
        for (int i5 = 0; i5 < i; i5++) {
            dataOutputStream.write(bArr[i5]);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] e() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        w m = this.c.m();
        a(dataOutputStream, 1.0d);
        b(dataOutputStream, this.g.size());
        b(dataOutputStream, m.h());
        b(dataOutputStream, m.e());
        b(dataOutputStream, m.d());
        b(dataOutputStream, m.c());
        b(dataOutputStream, m.m());
        b(dataOutputStream, m.l());
        b(dataOutputStream, m.k());
        b(dataOutputStream, m.f());
        b(dataOutputStream, m.g());
        b(dataOutputStream, m.j());
        b(dataOutputStream, m.i());
        b(dataOutputStream, m.b());
        b(dataOutputStream, m.a());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] f() {
        aa l = this.c.l();
        if (l == null || this.e.isEmpty()) {
            return null;
        }
        if (this.f != null && !this.f.contains("OS/2")) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        b(dataOutputStream, l.v());
        a(dataOutputStream, l.b());
        b(dataOutputStream, l.w());
        b(dataOutputStream, l.x());
        a(dataOutputStream, l.g());
        a(dataOutputStream, l.l());
        a(dataOutputStream, l.n());
        a(dataOutputStream, l.k());
        a(dataOutputStream, l.m());
        a(dataOutputStream, l.p());
        a(dataOutputStream, l.r());
        a(dataOutputStream, l.o());
        a(dataOutputStream, l.q());
        a(dataOutputStream, l.j());
        a(dataOutputStream, l.i());
        a(dataOutputStream, (short) l.e());
        dataOutputStream.write(l.h());
        a(dataOutputStream, 0L);
        a(dataOutputStream, 0L);
        a(dataOutputStream, 0L);
        a(dataOutputStream, 0L);
        dataOutputStream.write(l.a().getBytes("US-ASCII"));
        b(dataOutputStream, l.f());
        b(dataOutputStream, this.e.firstKey().intValue());
        b(dataOutputStream, this.e.lastKey().intValue());
        b(dataOutputStream, l.t());
        b(dataOutputStream, l.u());
        b(dataOutputStream, l.s());
        b(dataOutputStream, l.y());
        b(dataOutputStream, l.z());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] a(long[] jArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (long j : jArr) {
            a(dataOutputStream, j);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private void g() {
        TreeSet treeSet;
        int i;
        if (this.i) {
            return;
        }
        this.i = true;
        do {
            p e = this.c.e();
            long[] a2 = this.c.q().a();
            InputStream u = this.c.u();
            treeSet = null;
            try {
                u.skip(e.D());
                long j = 0;
                for (Integer num : this.g) {
                    long j2 = a2[num.intValue()];
                    long j3 = a2[num.intValue() + 1] - j2;
                    u.skip(j2 - j);
                    byte[] bArr = new byte[(int) j3];
                    u.read(bArr);
                    if (bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -1) {
                        int i2 = 10;
                        do {
                            i = ((bArr[i2] & 255) << 8) | (bArr[i2 + 1] & 255);
                            int i3 = i2 + 2;
                            int i4 = ((bArr[i3] & 255) << 8) | (bArr[i3 + 1] & 255);
                            if (!this.g.contains(Integer.valueOf(i4))) {
                                if (treeSet == null) {
                                    treeSet = new TreeSet();
                                }
                                treeSet.add(Integer.valueOf(i4));
                            }
                            int i5 = i3 + 2;
                            if ((i & 1) != 0) {
                                i2 = i5 + 4;
                            } else {
                                i2 = i5 + 2;
                            }
                            if ((i & 128) != 0) {
                                i2 += 8;
                            } else if ((i & 64) != 0) {
                                i2 += 4;
                            } else if ((i & 8) != 0) {
                                i2 += 2;
                            }
                        } while ((i & 32) != 0);
                    }
                    j = a2[num.intValue() + 1];
                }
                if (treeSet != null) {
                    this.g.addAll(treeSet);
                }
            } finally {
                u.close();
            }
        } while (treeSet != null);
    }

    private byte[] b(long[] jArr) {
        int i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        p e = this.c.e();
        long[] a2 = this.c.q().a();
        InputStream u = this.c.u();
        try {
            u.skip(e.D());
            long j = 0;
            long j2 = 0;
            int i2 = 0;
            for (Integer num : this.g) {
                long j3 = a2[num.intValue()];
                long j4 = a2[num.intValue() + 1] - j3;
                int i3 = i2;
                i2++;
                jArr[i3] = j2;
                u.skip(j3 - j);
                byte[] bArr = new byte[(int) j4];
                u.read(bArr);
                if (bArr.length >= 2 && bArr[0] == -1 && bArr[1] == -1) {
                    int i4 = 10;
                    do {
                        i = ((bArr[i4] & 255) << 8) | (bArr[i4 + 1] & 255);
                        int i5 = i4 + 2;
                        int i6 = ((bArr[i5] & 255) << 8) | (bArr[i5 + 1] & 255);
                        if (!this.g.contains(Integer.valueOf(i6))) {
                            this.g.add(Integer.valueOf(i6));
                        }
                        int a3 = a(Integer.valueOf(i6));
                        bArr[i5] = (byte) (a3 >>> 8);
                        bArr[i5 + 1] = (byte) a3;
                        int i7 = i5 + 2;
                        if ((i & 1) != 0) {
                            i4 = i7 + 4;
                        } else {
                            i4 = i7 + 2;
                        }
                        if ((i & 128) != 0) {
                            i4 += 8;
                        } else if ((i & 64) != 0) {
                            i4 += 4;
                        } else if ((i & 8) != 0) {
                            i4 += 2;
                        }
                    } while ((i & 32) != 0);
                    if ((i & 256) == 256) {
                        i4 = i4 + 2 + (((bArr[i4] & 255) << 8) | (bArr[i4 + 1] & 255));
                    }
                    byteArrayOutputStream.write(bArr, 0, i4);
                    j2 += i4;
                } else if (bArr.length > 0) {
                    byteArrayOutputStream.write(bArr, 0, bArr.length);
                    j2 += bArr.length;
                }
                if (j2 % 4 != 0) {
                    int i8 = 4 - ((int) (j2 % 4));
                    byteArrayOutputStream.write(f4282b, 0, i8);
                    j2 += i8;
                }
                j = j3 + j4;
            }
            jArr[i2] = j2;
            return byteArrayOutputStream.toByteArray();
        } finally {
            u.close();
        }
    }

    private int a(Integer num) {
        return this.g.headSet(num).size();
    }

    private byte[] h() {
        if (this.c.r() == null || this.e.isEmpty()) {
            return null;
        }
        if (this.f != null && !this.f.contains("cmap")) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        b(dataOutputStream, 0);
        b(dataOutputStream, 1);
        b(dataOutputStream, 3);
        b(dataOutputStream, 1);
        a(dataOutputStream, 12L);
        Iterator<Map.Entry<Integer, Integer>> it = this.e.entrySet().iterator();
        Map.Entry<Integer, Integer> next = it.next();
        Map.Entry<Integer, Integer> entry = next;
        Map.Entry<Integer, Integer> entry2 = next;
        int a2 = a(entry.getValue());
        int[] iArr = new int[this.e.size() + 1];
        int[] iArr2 = new int[this.e.size() + 1];
        int[] iArr3 = new int[this.e.size() + 1];
        int i = 0;
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> next2 = it.next();
            int a3 = a(next2.getValue());
            if (next2.getKey().intValue() > 65535) {
                throw new UnsupportedOperationException("non-BMP Unicode character");
            }
            if (next2.getKey().intValue() != entry2.getKey().intValue() + 1 || a3 - a2 != next2.getKey().intValue() - entry.getKey().intValue()) {
                if (a2 != 0) {
                    iArr[i] = entry.getKey().intValue();
                    iArr2[i] = entry2.getKey().intValue();
                    iArr3[i] = a2 - entry.getKey().intValue();
                    i++;
                } else if (!entry.getKey().equals(entry2.getKey())) {
                    iArr[i] = entry.getKey().intValue() + 1;
                    iArr2[i] = entry2.getKey().intValue();
                    iArr3[i] = a2 - entry.getKey().intValue();
                    i++;
                }
                a2 = a3;
                entry = next2;
            }
            entry2 = next2;
        }
        iArr[i] = entry.getKey().intValue();
        iArr2[i] = entry2.getKey().intValue();
        iArr3[i] = a2 - entry.getKey().intValue();
        int i2 = i + 1;
        iArr[i2] = 65535;
        iArr2[i2] = 65535;
        iArr3[i2] = 1;
        int i3 = i2 + 1;
        int pow = 2 * ((int) Math.pow(2.0d, b(i3)));
        b(dataOutputStream, 4);
        b(dataOutputStream, 16 + ((i3 << 2) << 1));
        b(dataOutputStream, 0);
        b(dataOutputStream, i3 << 1);
        b(dataOutputStream, pow);
        b(dataOutputStream, b(pow / 2));
        b(dataOutputStream, (2 * i3) - pow);
        for (int i4 = 0; i4 < i3; i4++) {
            b(dataOutputStream, iArr2[i4]);
        }
        b(dataOutputStream, 0);
        for (int i5 = 0; i5 < i3; i5++) {
            b(dataOutputStream, iArr[i5]);
        }
        for (int i6 = 0; i6 < i3; i6++) {
            b(dataOutputStream, iArr3[i6]);
        }
        for (int i7 = 0; i7 < i3; i7++) {
            b(dataOutputStream, 0);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] i() {
        ag k = this.c.k();
        if (k == null) {
            return null;
        }
        if (this.f != null && !this.f.contains("post")) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        a(dataOutputStream, 2.0d);
        a(dataOutputStream, k.b());
        a(dataOutputStream, k.g());
        a(dataOutputStream, k.h());
        a(dataOutputStream, k.a());
        a(dataOutputStream, k.f());
        a(dataOutputStream, k.d());
        a(dataOutputStream, k.e());
        a(dataOutputStream, k.c());
        b(dataOutputStream, this.g.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<Integer> it = this.g.iterator();
        while (it.hasNext()) {
            String a2 = k.a(it.next().intValue());
            Integer num = at.f4290b.get(a2);
            if (num != null) {
                b(dataOutputStream, num.intValue());
            } else {
                Integer num2 = (Integer) linkedHashMap.get(a2);
                Integer num3 = num2;
                if (num2 == null) {
                    num3 = Integer.valueOf(linkedHashMap.size());
                    linkedHashMap.put(a2, num3);
                }
                b(dataOutputStream, 258 + num3.intValue());
            }
        }
        Iterator it2 = linkedHashMap.keySet().iterator();
        while (it2.hasNext()) {
            byte[] bytes = ((String) it2.next()).getBytes(Charset.forName("US-ASCII"));
            c(dataOutputStream, bytes.length);
            dataOutputStream.write(bytes);
        }
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private byte[] j() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        r o = this.c.o();
        s p = this.c.p();
        InputStream u = this.c.u();
        int j = o.j() - 1;
        boolean z = false;
        if (this.g.last().intValue() > j && !this.g.contains(Integer.valueOf(j))) {
            z = true;
        }
        try {
            u.skip(p.D());
            long j2 = 0;
            Iterator<Integer> it = this.g.iterator();
            while (it.hasNext()) {
                if (it.next().intValue() <= j) {
                    j2 = a(u, byteArrayOutputStream, r0.intValue() << 2, j2, 4);
                } else {
                    if (z) {
                        z = false;
                        j2 = a(u, byteArrayOutputStream, j << 2, j2, 2);
                    }
                    j2 = a(u, byteArrayOutputStream, (o.j() << 2) + ((r0.intValue() - o.j()) << 1), j2, 2);
                }
            }
            return byteArrayOutputStream.toByteArray();
        } finally {
            u.close();
        }
    }

    private static long a(InputStream inputStream, OutputStream outputStream, long j, long j2, int i) {
        long j3 = j - j2;
        if (j3 != inputStream.skip(j3)) {
            throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
        }
        byte[] bArr = new byte[i];
        if (i != inputStream.read(bArr, 0, i)) {
            throw new EOFException("Unexpected EOF exception parsing glyphId of hmtx table.");
        }
        outputStream.write(bArr, 0, i);
        return j + i;
    }

    public final void a(OutputStream outputStream) {
        if (!this.g.isEmpty()) {
            this.e.isEmpty();
        }
        g();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        try {
            long[] jArr = new long[this.g.size() + 1];
            byte[] b2 = b();
            byte[] c = c();
            byte[] e = e();
            byte[] d = d();
            byte[] f = f();
            byte[] b3 = b(jArr);
            byte[] a2 = a(jArr);
            byte[] h = h();
            byte[] j = j();
            byte[] i = i();
            TreeMap treeMap = new TreeMap();
            if (f != null) {
                treeMap.put("OS/2", f);
            }
            if (h != null) {
                treeMap.put("cmap", h);
            }
            treeMap.put("glyf", b3);
            treeMap.put("head", b2);
            treeMap.put("hhea", c);
            treeMap.put("hmtx", j);
            treeMap.put("loca", a2);
            treeMap.put("maxp", e);
            if (d != null) {
                treeMap.put(Attribute.NAME_ATTR, d);
            }
            if (i != null) {
                treeMap.put("post", i);
            }
            for (Map.Entry<String, an> entry : this.c.i().entrySet()) {
                String key = entry.getKey();
                an value = entry.getValue();
                if (!treeMap.containsKey(key) && (this.f == null || this.f.contains(key))) {
                    treeMap.put(key, this.c.b(value));
                }
            }
            long a3 = a(dataOutputStream, treeMap.size());
            long size = 12 + (16 * treeMap.size());
            for (Map.Entry entry2 : treeMap.entrySet()) {
                a3 += a(dataOutputStream, (String) entry2.getKey(), size, (byte[]) entry2.getValue());
                size += ((((byte[]) entry2.getValue()).length + 3) / 4) << 2;
            }
            b2[8] = (byte) (r0 >>> 24);
            b2[9] = (byte) (r0 >>> 16);
            b2[10] = (byte) (r0 >>> 8);
            b2[11] = (byte) (2981146554L - (a3 & 4294967295L));
            Iterator it = treeMap.values().iterator();
            while (it.hasNext()) {
                a(dataOutputStream, (byte[]) it.next());
            }
        } finally {
            dataOutputStream.close();
        }
    }

    private static void a(DataOutputStream dataOutputStream, double d) {
        double floor = Math.floor(d);
        dataOutputStream.writeShort((int) floor);
        dataOutputStream.writeShort((int) ((d - floor) * 65536.0d));
    }

    private static void a(DataOutputStream dataOutputStream, long j) {
        dataOutputStream.writeInt((int) j);
    }

    private static void b(DataOutputStream dataOutputStream, int i) {
        dataOutputStream.writeShort(i);
    }

    private static void a(DataOutputStream dataOutputStream, short s) {
        dataOutputStream.writeShort(s);
    }

    private static void c(DataOutputStream dataOutputStream, int i) {
        dataOutputStream.writeByte(i);
    }

    private static void a(DataOutputStream dataOutputStream, Calendar calendar) {
        Calendar gregorianCalendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("UTC"));
        gregorianCalendar.set(1904, 0, 1, 0, 0, 0);
        gregorianCalendar.set(14, 0);
        dataOutputStream.writeLong((calendar.getTimeInMillis() - gregorianCalendar.getTimeInMillis()) / 1000);
    }

    private static long a(int i, int i2) {
        return ((i & User32.HWND_BROADCAST) << 16) | (i2 & User32.HWND_BROADCAST);
    }

    private static long a(byte[] bArr) {
        return ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
    }

    private static int b(int i) {
        return (int) Math.round(Math.log(i) / Math.log(2.0d));
    }
}
