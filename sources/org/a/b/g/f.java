package org.a.b.g;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.a.b.g.b;

/* loaded from: infinitode-2.jar:org/a/b/g/f.class */
final class f {

    /* renamed from: a, reason: collision with root package name */
    private e f4345a;

    /* renamed from: b, reason: collision with root package name */
    private d f4346b;

    public final d a(byte[] bArr, byte[] bArr2) {
        this.f4346b = new d(bArr, bArr2);
        a(bArr);
        if (bArr2.length > 0) {
            b(bArr2);
        }
        return this.f4346b;
    }

    private void a(byte[] bArr) {
        b b2;
        if (bArr.length == 0) {
            throw new IllegalArgumentException("byte[] is empty");
        }
        if (bArr.length < 2 || (bArr[0] != 37 && bArr[1] != 33)) {
            throw new IOException("Invalid start of ASCII segment");
        }
        this.f4345a = new e(bArr);
        if (this.f4345a.b().a().equals("FontDirectory")) {
            a(b.f4338b, "FontDirectory");
            a(b.c);
            a(b.f4338b, "known");
            a(b.h);
            e();
            a(b.h);
            e();
            a(b.f4338b, "ifelse");
        }
        int c = a(b.e).c();
        a(b.f4338b, "dict");
        b(b.f4338b, "dup");
        a(b.f4338b, "begin");
        for (int i = 0; i < c && (b2 = this.f4345a.b()) != null && (b2.b() != b.f4338b || (!b2.a().equals("currentdict") && !b2.a().equals("end"))); i++) {
            String a2 = a(b.c).a();
            if (a2.equals("FontInfo") || a2.equals("Fontinfo")) {
                a(b());
            } else if (a2.equals("Metrics")) {
                b();
            } else if (a2.equals("Encoding")) {
                a();
            } else {
                a(a2);
            }
        }
        b(b.f4338b, "currentdict");
        a(b.f4338b, "end");
        a(b.f4338b, "currentfile");
        a(b.f4338b, "eexec");
    }

    private void a(String str) {
        List<b> c = c();
        if (str.equals("FontName")) {
            this.f4346b.f4341a = c.get(0).a();
            return;
        }
        if (str.equals("PaintType")) {
            c.get(0).c();
            return;
        }
        if (str.equals("FontType")) {
            c.get(0).c();
            return;
        }
        if (str.equals("FontMatrix")) {
            this.f4346b.c = a(c);
            return;
        }
        if (str.equals("FontBBox")) {
            this.f4346b.d = a(c);
        } else if (str.equals("UniqueID")) {
            c.get(0).c();
        } else if (str.equals("StrokeWidth")) {
            c.get(0).d();
        } else if (str.equals("FID")) {
            c.get(0).a();
        }
    }

    private void a() {
        if (this.f4345a.b().b() == b.f4338b) {
            String a2 = this.f4345a.a().a();
            if (a2.equals("StandardEncoding")) {
                this.f4346b.f4342b = org.a.b.d.c.f4266a;
                b(b.f4338b, "readonly");
                a(b.f4338b, "def");
                return;
            }
            throw new IOException("Unknown encoding: " + a2);
        }
        a(b.e).c();
        b(b.f4338b, "array");
        while (true) {
            if (this.f4345a.b().b() != b.f4338b || (!this.f4345a.b().a().equals("dup") && !this.f4345a.b().a().equals("readonly") && !this.f4345a.b().a().equals("def"))) {
                this.f4345a.a();
            }
        }
        HashMap hashMap = new HashMap();
        while (this.f4345a.b().b() == b.f4338b && this.f4345a.b().a().equals("dup")) {
            a(b.f4338b, "dup");
            int c = a(b.e).c();
            String a3 = a(b.c).a();
            a(b.f4338b, "put");
            hashMap.put(Integer.valueOf(c), a3);
        }
        this.f4346b.f4342b = new org.a.b.d.a(hashMap);
        b(b.f4338b, "readonly");
        a(b.f4338b, "def");
    }

    private static List<Number> a(List<b> list) {
        ArrayList arrayList = new ArrayList();
        int size = list.size() - 1;
        for (int i = 1; i < size; i++) {
            b bVar = list.get(i);
            if (bVar.b() == b.d) {
                arrayList.add(Float.valueOf(bVar.d()));
            } else if (bVar.b() == b.e) {
                arrayList.add(Integer.valueOf(bVar.c()));
            } else {
                throw new IOException("Expected INTEGER or REAL but got " + bVar.b());
            }
        }
        return arrayList;
    }

    private void a(Map<String, List<b>> map) {
        for (Map.Entry<String, List<b>> entry : map.entrySet()) {
            String key = entry.getKey();
            List<b> value = entry.getValue();
            if (key.equals("version")) {
                value.get(0).a();
            } else if (key.equals("Notice")) {
                value.get(0).a();
            } else if (key.equals("FullName")) {
                this.f4346b.e = value.get(0).a();
            } else if (key.equals("FamilyName")) {
                this.f4346b.f = value.get(0).a();
            } else if (key.equals("Weight")) {
                this.f4346b.g = value.get(0).a();
            } else if (key.equals("ItalicAngle")) {
                value.get(0).d();
            } else if (key.equals("isFixedPitch")) {
                value.get(0).e();
            } else if (key.equals("UnderlinePosition")) {
                value.get(0).d();
            } else if (key.equals("UnderlineThickness")) {
                value.get(0).d();
            }
        }
    }

    private Map<String, List<b>> b() {
        HashMap hashMap = new HashMap();
        int c = a(b.e).c();
        a(b.f4338b, "dict");
        b(b.f4338b, "dup");
        a(b.f4338b, "begin");
        for (int i = 0; i < c && this.f4345a.b() != null; i++) {
            if (this.f4345a.b().b() == b.f4338b && !this.f4345a.b().a().equals("end")) {
                a(b.f4338b);
            }
            if (this.f4345a.b() == null || (this.f4345a.b().b() == b.f4338b && this.f4345a.b().a().equals("end"))) {
                break;
            }
            hashMap.put(a(b.c).a(), c());
        }
        a(b.f4338b, "end");
        b(b.f4338b, "readonly");
        a(b.f4338b, "def");
        return hashMap;
    }

    private List<b> c() {
        List<b> d = d();
        g();
        return d;
    }

    private List<b> d() {
        ArrayList arrayList = new ArrayList();
        b a2 = this.f4345a.a();
        if (this.f4345a.b() == null) {
            return arrayList;
        }
        arrayList.add(a2);
        if (a2.b() == b.f) {
            int i = 1;
            while (this.f4345a.b() != null) {
                if (this.f4345a.b().b() == b.f) {
                    i++;
                }
                b a3 = this.f4345a.a();
                arrayList.add(a3);
                if (a3.b() == b.g) {
                    i--;
                    if (i == 0) {
                    }
                }
            }
            return arrayList;
        }
        if (a2.b() == b.h) {
            arrayList.addAll(e());
        } else if (a2.b() == b.k) {
            a(b.l);
            return arrayList;
        }
        b(arrayList);
        return arrayList;
    }

    private void b(List<b> list) {
        if (this.f4345a.b().a().equals("systemdict")) {
            a(b.f4338b, "systemdict");
            a(b.c, "internaldict");
            a(b.f4338b, "known");
            a(b.h);
            e();
            a(b.h);
            e();
            a(b.f4338b, "ifelse");
            a(b.h);
            a(b.f4338b, "pop");
            list.clear();
            list.addAll(d());
            a(b.i);
            a(b.f4338b, "if");
        }
    }

    private List<b> e() {
        ArrayList arrayList = new ArrayList();
        int i = 1;
        while (true) {
            if (this.f4345a.b().b() == b.h) {
                i++;
            }
            b a2 = this.f4345a.a();
            arrayList.add(a2);
            if (a2.b() == b.i) {
                i--;
                if (i == 0) {
                    break;
                }
            }
        }
        b b2 = b(b.f4338b, "executeonly");
        if (b2 != null) {
            arrayList.add(b2);
        }
        return arrayList;
    }

    private void b(byte[] bArr) {
        byte[] a2;
        b bVar;
        if (c(bArr)) {
            a2 = a(bArr, 55665, 4);
        } else {
            a2 = a(d(bArr), 55665, 4);
        }
        this.f4345a = new e(a2);
        b b2 = this.f4345a.b();
        while (true) {
            bVar = b2;
            if (bVar == null || bVar.a().equals("Private")) {
                break;
            }
            this.f4345a.a();
            b2 = this.f4345a.b();
        }
        if (bVar == null) {
            throw new IOException("/Private token not found");
        }
        a(b.c, "Private");
        int c = a(b.e).c();
        a(b.f4338b, "dict");
        b(b.f4338b, "dup");
        a(b.f4338b, "begin");
        int i = 4;
        for (int i2 = 0; i2 < c && this.f4345a.b() != null && this.f4345a.b().b() == b.c; i2++) {
            String a3 = a(b.c).a();
            if ("Subrs".equals(a3)) {
                a(i);
            } else if ("OtherSubrs".equals(a3)) {
                f();
            } else if ("lenIV".equals(a3)) {
                i = c().get(0).c();
            } else if ("ND".equals(a3)) {
                a(b.h);
                b(b.f4338b, "noaccess");
                a(b.f4338b, "def");
                a(b.i);
                b(b.f4338b, "executeonly");
                a(b.f4338b, "def");
            } else if ("NP".equals(a3)) {
                a(b.h);
                b(b.f4338b, "noaccess");
                a(b.f4338b);
                a(b.i);
                b(b.f4338b, "executeonly");
                a(b.f4338b, "def");
            } else if ("RD".equals(a3)) {
                a(b.h);
                e();
                b(b.f4338b, "bind");
                b(b.f4338b, "executeonly");
                a(b.f4338b, "def");
            } else {
                a(a3, c());
            }
        }
        while (true) {
            if (this.f4345a.b().b() != b.c || !this.f4345a.b().a().equals("CharStrings")) {
                this.f4345a.a();
            } else {
                a(b.c, "CharStrings");
                b(i);
                return;
            }
        }
    }

    private void a(String str, List<b> list) {
        if (str.equals("BlueValues")) {
            a(list);
            return;
        }
        if (str.equals("OtherBlues")) {
            a(list);
            return;
        }
        if (str.equals("FamilyBlues")) {
            a(list);
            return;
        }
        if (str.equals("FamilyOtherBlues")) {
            a(list);
            return;
        }
        if (str.equals("BlueScale")) {
            list.get(0).d();
            return;
        }
        if (str.equals("BlueShift")) {
            list.get(0).c();
            return;
        }
        if (str.equals("BlueFuzz")) {
            list.get(0).c();
            return;
        }
        if (str.equals("StdHW")) {
            a(list);
            return;
        }
        if (str.equals("StdVW")) {
            a(list);
            return;
        }
        if (str.equals("StemSnapH")) {
            a(list);
            return;
        }
        if (str.equals("StemSnapV")) {
            a(list);
        } else if (str.equals("ForceBold")) {
            list.get(0).e();
        } else if (str.equals("LanguageGroup")) {
            list.get(0).c();
        }
    }

    private void a(int i) {
        int c = a(b.e).c();
        for (int i2 = 0; i2 < c; i2++) {
            this.f4346b.h.add(null);
        }
        a(b.f4338b, "array");
        for (int i3 = 0; i3 < c && this.f4345a.b() != null && this.f4345a.b().b() == b.f4338b && this.f4345a.b().a().equals("dup"); i3++) {
            a(b.f4338b, "dup");
            b a2 = a(b.e);
            a(b.e);
            this.f4346b.h.set(a2.c(), a(a(b.j).f(), 4330, i));
            h();
        }
        g();
    }

    private void f() {
        if (this.f4345a.b().b() == b.f) {
            d();
        } else {
            int c = a(b.e).c();
            a(b.f4338b, "array");
            for (int i = 0; i < c; i++) {
                a(b.f4338b, "dup");
                a(b.e);
                d();
                h();
            }
        }
        g();
    }

    private void b(int i) {
        int c = a(b.e).c();
        a(b.f4338b, "dict");
        a(b.f4338b, "dup");
        a(b.f4338b, "begin");
        for (int i2 = 0; i2 < c && this.f4345a.b() != null && (this.f4345a.b().b() != b.f4338b || !this.f4345a.b().a().equals("end")); i2++) {
            String a2 = a(b.c).a();
            a(b.e);
            this.f4346b.i.put(a2, a(a(b.j).f(), 4330, i));
            g();
        }
        a(b.f4338b, "end");
    }

    private void g() {
        b(b.f4338b, "readonly");
        b(b.f4338b, "noaccess");
        b a2 = a(b.f4338b);
        b bVar = a2;
        if (a2.a().equals("ND") || bVar.a().equals("|-")) {
            return;
        }
        if (bVar.a().equals("noaccess")) {
            bVar = a(b.f4338b);
        }
        if (bVar.a().equals("def")) {
        } else {
            throw new IOException("Found " + bVar + " but expected ND");
        }
    }

    private void h() {
        b(b.f4338b, "readonly");
        b a2 = a(b.f4338b);
        b bVar = a2;
        if (a2.a().equals("NP") || bVar.a().equals("|")) {
            return;
        }
        if (bVar.a().equals("noaccess")) {
            bVar = a(b.f4338b);
        }
        if (bVar.a().equals("put")) {
        } else {
            throw new IOException("Found " + bVar + " but expected NP");
        }
    }

    private b a(b.a aVar) {
        b a2 = this.f4345a.a();
        if (a2 == null || a2.b() != aVar) {
            throw new IOException("Found " + a2 + " but expected " + aVar);
        }
        return a2;
    }

    private void a(b.a aVar, String str) {
        b a2 = a(aVar);
        if (!a2.a().equals(str)) {
            throw new IOException("Found " + a2 + " but expected " + str);
        }
    }

    private b b(b.a aVar, String str) {
        b b2 = this.f4345a.b();
        if (b2 != null && b2.b() == aVar && b2.a().equals(str)) {
            return this.f4345a.a();
        }
        return null;
    }

    private static byte[] a(byte[] bArr, int i, int i2) {
        if (i2 == -1) {
            return bArr;
        }
        if (bArr.length == 0 || i2 > bArr.length) {
            return new byte[0];
        }
        byte[] bArr2 = new byte[bArr.length - i2];
        for (int i3 = 0; i3 < bArr.length; i3++) {
            int i4 = bArr[i3] & 255;
            int i5 = i4 ^ (i >> 8);
            if (i3 >= i2) {
                bArr2[i3 - i2] = (byte) i5;
            }
            i = (((i4 + i) * 52845) + 22719) & 65535;
        }
        return bArr2;
    }

    private static boolean c(byte[] bArr) {
        if (bArr.length < 4) {
            return true;
        }
        for (int i = 0; i < 4; i++) {
            byte b2 = bArr[i];
            if (b2 != 10 && b2 != 13 && b2 != 32 && b2 != 9 && Character.digit((char) b2, 16) == -1) {
                return true;
            }
        }
        return false;
    }

    private static byte[] d(byte[] bArr) {
        int i = 0;
        for (byte b2 : bArr) {
            if (Character.digit((char) b2, 16) != -1) {
                i++;
            }
        }
        byte[] bArr2 = new byte[i / 2];
        int i2 = 0;
        int i3 = -1;
        for (byte b3 : bArr) {
            int digit = Character.digit((char) b3, 16);
            if (digit != -1) {
                if (i3 == -1) {
                    i3 = digit;
                } else {
                    int i4 = i2;
                    i2++;
                    bArr2[i4] = (byte) ((i3 << 4) + digit);
                    i3 = -1;
                }
            }
        }
        return bArr2;
    }
}
