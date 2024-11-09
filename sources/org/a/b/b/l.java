package org.a.b.b;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.a.b.b.k;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/b/b/l.class */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4212a = org.a.a.a.c.a(l.class);

    /* renamed from: b, reason: collision with root package name */
    private String[] f4213b = null;
    private a c;
    private String d;

    /* loaded from: infinitode-2.jar:org/a/b/b/l$a.class */
    public interface a {
    }

    public final List<org.a.b.b.i> a(byte[] bArr, a aVar) {
        this.c = aVar;
        return a(bArr);
    }

    private List<org.a.b.b.i> a(byte[] bArr) {
        org.a.b.b.d dVar = new org.a.b.b.d(bArr);
        org.a.b.b.d dVar2 = dVar;
        String a2 = a(dVar);
        if ("OTTO".equals(a2)) {
            dVar2 = a(dVar2, bArr);
        } else {
            if ("ttcf".equals(a2)) {
                throw new IOException("True Type Collection fonts are not supported.");
            }
            if ("��\u0001����".equals(a2)) {
                throw new IOException("OpenType fonts containing a true type font are not supported.");
            }
            dVar2.a(0);
        }
        c(dVar2);
        String[] f2 = f(dVar2);
        if (f2 == null) {
            throw new IOException("Name index missing in CFF font");
        }
        byte[][] e2 = e(dVar2);
        this.f4213b = f(dVar2);
        byte[][] e3 = e(dVar2);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < f2.length; i2++) {
            org.a.b.b.i a3 = a(dVar2, f2[i2], e2[i2]);
            a3.a(e3);
            arrayList.add(a3);
        }
        return arrayList;
    }

    private static org.a.b.b.d a(org.a.b.b.d dVar, byte[] bArr) {
        int e2 = dVar.e();
        dVar.e();
        dVar.e();
        dVar.e();
        for (int i2 = 0; i2 < e2; i2++) {
            String a2 = a(dVar);
            b(dVar);
            long b2 = b(dVar);
            long b3 = b(dVar);
            if ("CFF ".equals(a2)) {
                return new org.a.b.b.d(Arrays.copyOfRange(bArr, (int) b2, (int) (b2 + b3)));
            }
        }
        throw new IOException("CFF tag not found in this OpenType font.");
    }

    private static String a(org.a.b.b.d dVar) {
        return new String(dVar.c(4), org.a.b.h.b.f4351a);
    }

    private static long b(org.a.b.b.d dVar) {
        return (dVar.j() << 16) | dVar.j();
    }

    private static m c(org.a.b.b.d dVar) {
        m mVar = new m((byte) 0);
        mVar.f4233a = dVar.i();
        mVar.f4234b = dVar.i();
        mVar.c = dVar.i();
        mVar.d = dVar.k();
        return mVar;
    }

    private static int[] d(org.a.b.b.d dVar) {
        int j2 = dVar.j();
        if (j2 == 0) {
            return null;
        }
        int k2 = dVar.k();
        int[] iArr = new int[j2 + 1];
        for (int i2 = 0; i2 <= j2; i2++) {
            int d2 = dVar.d(k2);
            if (d2 > dVar.h()) {
                throw new IOException("illegal offset value " + d2 + " in CFF font");
            }
            iArr[i2] = d2;
        }
        return iArr;
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [byte[], byte[][]] */
    private static byte[][] e(org.a.b.b.d dVar) {
        int[] d2 = d(dVar);
        if (d2 == null) {
            return (byte[][]) null;
        }
        int length = d2.length - 1;
        ?? r0 = new byte[length];
        for (int i2 = 0; i2 < length; i2++) {
            r0[i2] = dVar.c(d2[i2 + 1] - d2[i2]);
        }
        return r0;
    }

    private static String[] f(org.a.b.b.d dVar) {
        int[] d2 = d(dVar);
        if (d2 == null) {
            return null;
        }
        int length = d2.length - 1;
        String[] strArr = new String[length];
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = d2[i2 + 1] - d2[i2];
            if (i3 < 0) {
                throw new IOException("Negative index data length + " + i3 + " at " + i2 + ": offsets[" + (i2 + 1) + "]=" + d2[i2 + 1] + ", offsets[" + i2 + "]=" + d2[i2]);
            }
            strArr[i2] = new String(dVar.c(i3), org.a.b.h.b.f4351a);
        }
        return strArr;
    }

    private static c g(org.a.b.b.d dVar) {
        c cVar = new c((byte) 0);
        while (dVar.a()) {
            cVar.a(h(dVar));
        }
        return cVar;
    }

    private static c a(org.a.b.b.d dVar, int i2) {
        c cVar = new c((byte) 0);
        int b2 = dVar.b() + i2;
        while (dVar.b() < b2) {
            cVar.a(h(dVar));
        }
        return cVar;
    }

    private static c.a h(org.a.b.b.d dVar) {
        int d2;
        c.a aVar = new c.a((byte) 0);
        while (true) {
            d2 = dVar.d();
            if (d2 < 0 || d2 > 21) {
                if (d2 == 28 || d2 == 29) {
                    aVar.f4219a.add(d(dVar, d2));
                } else if (d2 != 30) {
                    if (d2 < 32 || d2 > 254) {
                        break;
                    }
                    aVar.f4219a.add(d(dVar, d2));
                } else {
                    aVar.f4219a.add(i(dVar));
                }
            } else {
                aVar.f4220b = b(dVar, d2);
                return aVar;
            }
        }
        throw new IOException("invalid DICT data b0 byte: " + d2);
    }

    private static org.a.b.b.k b(org.a.b.b.d dVar, int i2) {
        return org.a.b.b.k.a(c(dVar, i2));
    }

    private static k.a c(org.a.b.b.d dVar, int i2) {
        if (i2 == 12) {
            return new k.a(i2, dVar.d());
        }
        return new k.a(i2);
    }

    private static Integer d(org.a.b.b.d dVar, int i2) {
        if (i2 == 28) {
            return Integer.valueOf(dVar.e());
        }
        if (i2 == 29) {
            return Integer.valueOf(dVar.g());
        }
        if (i2 >= 32 && i2 <= 246) {
            return Integer.valueOf(i2 - 139);
        }
        if (i2 >= 247 && i2 <= 250) {
            return Integer.valueOf(((i2 - User32.VK_CRSEL) << 8) + dVar.d() + 108);
        }
        if (i2 >= 251 && i2 <= 254) {
            return Integer.valueOf((((-(i2 - User32.VK_ZOOM)) << 8) - dVar.d()) - 108);
        }
        throw new IllegalArgumentException();
    }

    private static Double i(org.a.b.b.d dVar) {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        while (!z) {
            int d2 = dVar.d();
            int[] iArr = {d2 / 16, d2 % 16};
            for (int i2 = 0; i2 < 2; i2++) {
                int i3 = iArr[i2];
                switch (i3) {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        sb.append(i3);
                        z2 = false;
                        break;
                    case 10:
                        sb.append(".");
                        break;
                    case 11:
                        if (z3) {
                            new StringBuilder("duplicate 'E' ignored after ").append((Object) sb);
                            break;
                        } else {
                            sb.append("E");
                            z2 = true;
                            z3 = true;
                            break;
                        }
                    case 12:
                        if (z3) {
                            new StringBuilder("duplicate 'E-' ignored after ").append((Object) sb);
                            break;
                        } else {
                            sb.append("E-");
                            z2 = true;
                            z3 = true;
                            break;
                        }
                    case 13:
                        break;
                    case 14:
                        sb.append("-");
                        break;
                    case 15:
                        z = true;
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            }
        }
        if (z2) {
            sb.append("0");
        }
        if (sb.length() == 0) {
            return Double.valueOf(0.0d);
        }
        return Double.valueOf(sb.toString());
    }

    private org.a.b.b.i a(org.a.b.b.d dVar, String str, byte[] bArr) {
        org.a.b.b.i oVar;
        org.a.b.b.c b2;
        c g2 = g(new org.a.b.b.d(bArr));
        if (g2.a("SyntheticBase") != null) {
            throw new IOException("Synthetic Fonts are not supported");
        }
        boolean z = g2.a("ROS") != null;
        boolean z2 = z;
        if (z) {
            oVar = new org.a.b.b.a();
            c.a a2 = g2.a("ROS");
            ((org.a.b.b.a) oVar).c(a(a2.a(0).intValue()));
            ((org.a.b.b.a) oVar).d(a(a2.a(1).intValue()));
            ((org.a.b.b.a) oVar).a(a2.a(2).intValue());
        } else {
            oVar = new org.a.b.b.o();
        }
        this.d = str;
        oVar.e(str);
        oVar.a("version", a(g2, "version"));
        oVar.a("Notice", a(g2, "Notice"));
        oVar.a("Copyright", a(g2, "Copyright"));
        oVar.a("FullName", a(g2, "FullName"));
        oVar.a("FamilyName", a(g2, "FamilyName"));
        oVar.a("Weight", a(g2, "Weight"));
        oVar.a("isFixedPitch", g2.a("isFixedPitch", false));
        oVar.a("ItalicAngle", g2.a("ItalicAngle", (Number) 0));
        oVar.a("UnderlinePosition", g2.a("UnderlinePosition", (Number) (-100)));
        oVar.a("UnderlineThickness", g2.a("UnderlineThickness", (Number) 50));
        oVar.a("PaintType", g2.a("PaintType", (Number) 0));
        oVar.a("CharstringType", g2.a("CharstringType", (Number) 2));
        oVar.a("FontMatrix", g2.a("FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
        oVar.a("UniqueID", g2.a("UniqueID", (Number) null));
        oVar.a("FontBBox", g2.a("FontBBox", Arrays.asList(0, 0, 0, 0)));
        oVar.a("StrokeWidth", g2.a("StrokeWidth", (Number) 0));
        oVar.a("XUID", g2.a("XUID", (List<Number>) null));
        dVar.a(g2.a("CharStrings").a(0).intValue());
        byte[][] e2 = e(dVar);
        c.a a3 = g2.a("charset");
        if (a3 != null) {
            int intValue = a3.a(0).intValue();
            if (!z2 && intValue == 0) {
                b2 = org.a.b.b.j.b();
            } else if (!z2 && intValue == 1) {
                b2 = org.a.b.b.f.b();
            } else if (!z2 && intValue == 2) {
                b2 = org.a.b.b.h.b();
            } else {
                dVar.a(intValue);
                b2 = a(dVar, e2.length, z2);
            }
        } else if (z2) {
            b2 = new e(e2.length);
        } else {
            b2 = org.a.b.b.j.b();
        }
        oVar.a(b2);
        oVar.d = e2;
        if (z2) {
            a(dVar, g2, (org.a.b.b.a) oVar, e2.length);
            List list = null;
            List<Map<String, Object>> g3 = ((org.a.b.b.a) oVar).g();
            if (!g3.isEmpty() && g3.get(0).containsKey("FontMatrix")) {
                list = (List) g3.get(0).get("FontMatrix");
            }
            List<Number> a4 = g2.a("FontMatrix", (List<Number>) null);
            if (a4 == null) {
                if (list != null) {
                    oVar.a("FontMatrix", list);
                } else {
                    oVar.a("FontMatrix", g2.a("FontMatrix", Arrays.asList(Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d), Double.valueOf(0.001d), Double.valueOf(0.0d), Double.valueOf(0.0d))));
                }
            } else if (list != null) {
                a(a4, (List<Number>) list);
            }
        } else {
            a(dVar, g2, (org.a.b.b.o) oVar, b2);
        }
        return oVar;
    }

    private static void a(List<Number> list, List<Number> list2) {
        double doubleValue = list.get(0).doubleValue();
        double doubleValue2 = list.get(1).doubleValue();
        double doubleValue3 = list.get(2).doubleValue();
        double doubleValue4 = list.get(3).doubleValue();
        double doubleValue5 = list.get(4).doubleValue();
        double doubleValue6 = list.get(5).doubleValue();
        double doubleValue7 = list2.get(0).doubleValue();
        double doubleValue8 = list2.get(1).doubleValue();
        double doubleValue9 = list2.get(2).doubleValue();
        double doubleValue10 = list2.get(3).doubleValue();
        double doubleValue11 = list2.get(4).doubleValue();
        double doubleValue12 = list2.get(5).doubleValue();
        list.set(0, Double.valueOf((doubleValue * doubleValue7) + (doubleValue2 * doubleValue9)));
        list.set(1, Double.valueOf((doubleValue * doubleValue8) + (doubleValue2 * doubleValue4)));
        list.set(2, Double.valueOf((doubleValue3 * doubleValue7) + (doubleValue4 * doubleValue9)));
        list.set(3, Double.valueOf((doubleValue3 * doubleValue8) + (doubleValue4 * doubleValue10)));
        list.set(4, Double.valueOf((doubleValue5 * doubleValue7) + (doubleValue6 * doubleValue9) + doubleValue11));
        list.set(5, Double.valueOf((doubleValue5 * doubleValue8) + (doubleValue6 * doubleValue10) + doubleValue12));
    }

    private void a(org.a.b.b.d dVar, c cVar, org.a.b.b.a aVar, int i2) {
        c.a a2 = cVar.a("FDArray");
        if (a2 == null) {
            throw new IOException("FDArray is missing for a CIDKeyed Font.");
        }
        dVar.a(a2.a(0).intValue());
        byte[][] e2 = e(dVar);
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        for (byte[] bArr : e2) {
            c g2 = g(new org.a.b.b.d(bArr));
            c.a a3 = g2.a("Private");
            if (a3 == null) {
                throw new IOException("Font DICT invalid without \"Private\" entry");
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put("FontName", a(g2, "FontName"));
            linkedHashMap.put("FontType", g2.a("FontType", (Number) 0));
            linkedHashMap.put("FontBBox", g2.a("FontBBox", (List<Number>) null));
            linkedHashMap.put("FontMatrix", g2.a("FontMatrix", (List<Number>) null));
            linkedList2.add(linkedHashMap);
            int intValue = a3.a(1).intValue();
            dVar.a(intValue);
            c a4 = a(dVar, a3.a(0).intValue());
            Map<String, Object> a5 = a(a4);
            linkedList.add(a5);
            int intValue2 = ((Integer) a4.a("Subrs", (Number) 0)).intValue();
            if (intValue2 > 0) {
                dVar.a(intValue + intValue2);
                a5.put("Subrs", e(dVar));
            }
        }
        dVar.a(cVar.a("FDSelect").a(0).intValue());
        s a6 = a(dVar, i2, aVar);
        aVar.a(linkedList2);
        aVar.b(linkedList);
        aVar.a(a6);
    }

    private static Map<String, Object> a(c cVar) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(17);
        linkedHashMap.put("BlueValues", cVar.b("BlueValues", null));
        linkedHashMap.put("OtherBlues", cVar.b("OtherBlues", null));
        linkedHashMap.put("FamilyBlues", cVar.b("FamilyBlues", null));
        linkedHashMap.put("FamilyOtherBlues", cVar.b("FamilyOtherBlues", null));
        linkedHashMap.put("BlueScale", cVar.a("BlueScale", Double.valueOf(0.039625d)));
        linkedHashMap.put("BlueShift", cVar.a("BlueShift", (Number) 7));
        linkedHashMap.put("BlueFuzz", cVar.a("BlueFuzz", (Number) 1));
        linkedHashMap.put("StdHW", cVar.a("StdHW", (Number) null));
        linkedHashMap.put("StdVW", cVar.a("StdVW", (Number) null));
        linkedHashMap.put("StemSnapH", cVar.b("StemSnapH", null));
        linkedHashMap.put("StemSnapV", cVar.b("StemSnapV", null));
        linkedHashMap.put("ForceBold", cVar.a("ForceBold", false));
        linkedHashMap.put("LanguageGroup", cVar.a("LanguageGroup", (Number) 0));
        linkedHashMap.put("ExpansionFactor", cVar.a("ExpansionFactor", Double.valueOf(0.06d)));
        linkedHashMap.put("initialRandomSeed", cVar.a("initialRandomSeed", (Number) 0));
        linkedHashMap.put("defaultWidthX", cVar.a("defaultWidthX", (Number) 0));
        linkedHashMap.put("nominalWidthX", cVar.a("nominalWidthX", (Number) 0));
        return linkedHashMap;
    }

    private void a(org.a.b.b.d dVar, c cVar, org.a.b.b.o oVar, org.a.b.b.c cVar2) {
        org.a.b.b.e a2;
        c.a a3 = cVar.a("Encoding");
        int intValue = a3 != null ? a3.a(0).intValue() : 0;
        int i2 = intValue;
        switch (intValue) {
            case 0:
                a2 = org.a.b.b.m.a();
                break;
            case 1:
                a2 = org.a.b.b.g.a();
                break;
            default:
                dVar.a(i2);
                a2 = a(dVar, cVar2);
                break;
        }
        oVar.a(a2);
        c.a a4 = cVar.a("Private");
        if (a4 == null) {
            throw new IOException("Private dictionary entry missing for font " + oVar.f4205a);
        }
        int intValue2 = a4.a(1).intValue();
        dVar.a(intValue2);
        c a5 = a(dVar, a4.a(0).intValue());
        for (Map.Entry<String, Object> entry : a(a5).entrySet()) {
            oVar.b(entry.getKey(), entry.getValue());
        }
        int intValue3 = ((Integer) a5.a("Subrs", (Number) 0)).intValue();
        if (intValue3 > 0) {
            dVar.a(intValue2 + intValue3);
            oVar.b("Subrs", e(dVar));
        }
    }

    private String a(int i2) {
        if (i2 >= 0 && i2 <= 390) {
            return org.a.b.b.n.a(i2);
        }
        if (i2 - 391 < this.f4213b.length) {
            return this.f4213b[i2 - 391];
        }
        return "SID" + i2;
    }

    private String a(c cVar, String str) {
        c.a a2 = cVar.a(str);
        if (a2 != null) {
            return a(a2.a(0).intValue());
        }
        return null;
    }

    private org.a.b.b.e a(org.a.b.b.d dVar, org.a.b.b.c cVar) {
        int i2 = dVar.i();
        switch (i2 & 127) {
            case 0:
                return a(dVar, cVar, i2);
            case 1:
                return b(dVar, cVar, i2);
            default:
                throw new IllegalArgumentException();
        }
    }

    private g a(org.a.b.b.d dVar, org.a.b.b.c cVar, int i2) {
        g gVar = new g((byte) 0);
        gVar.f4222a = i2;
        gVar.f4223b = dVar.i();
        gVar.a(0, ".notdef");
        for (int i3 = 1; i3 <= gVar.f4223b; i3++) {
            gVar.a(dVar.i(), a(cVar.a(i3)));
        }
        if ((i2 & 128) != 0) {
            a(dVar, gVar);
        }
        return gVar;
    }

    private j b(org.a.b.b.d dVar, org.a.b.b.c cVar, int i2) {
        j jVar = new j((byte) 0);
        jVar.f4227a = i2;
        jVar.f4228b = dVar.i();
        jVar.a(0, ".notdef");
        int i3 = 1;
        for (int i4 = 0; i4 < jVar.f4228b; i4++) {
            int i5 = dVar.i();
            int i6 = dVar.i();
            for (int i7 = 0; i7 < i6 + 1; i7++) {
                jVar.a(i5 + i7, a(cVar.a(i3)));
                i3++;
            }
        }
        if ((i2 & 128) != 0) {
            a(dVar, jVar);
        }
        return jVar;
    }

    private void a(org.a.b.b.d dVar, b bVar) {
        bVar.f4214a = dVar.i();
        bVar.f4215b = new b.a[bVar.f4214a];
        for (int i2 = 0; i2 < bVar.f4215b.length; i2++) {
            b.a aVar = new b.a();
            aVar.f4216a = dVar.i();
            aVar.f4217b = dVar.l();
            a(aVar.f4217b);
            bVar.f4215b[i2] = aVar;
            int i3 = aVar.f4216a;
            int unused = aVar.f4217b;
            bVar.a(i3, a(aVar.f4217b));
        }
    }

    private static s a(org.a.b.b.d dVar, int i2, org.a.b.b.a aVar) {
        int i3 = dVar.i();
        switch (i3) {
            case 0:
                return a(dVar, i3, i2, aVar);
            case 3:
                return b(dVar, i3, aVar);
            default:
                throw new IllegalArgumentException();
        }
    }

    private static h a(org.a.b.b.d dVar, int i2, int i3, org.a.b.b.a aVar) {
        h hVar = new h(aVar, (byte) 0);
        hVar.f4224a = new int[i3];
        for (int i4 = 0; i4 < hVar.f4224a.length; i4++) {
            hVar.f4224a[i4] = dVar.i();
        }
        return hVar;
    }

    private static C0043l b(org.a.b.b.d dVar, int i2, org.a.b.b.a aVar) {
        C0043l c0043l = new C0043l(aVar, (byte) 0);
        c0043l.f4231a = i2;
        c0043l.f4232b = dVar.j();
        c0043l.c = new n[c0043l.f4232b];
        for (int i3 = 0; i3 < c0043l.f4232b; i3++) {
            n nVar = new n((byte) 0);
            nVar.f4235a = dVar.j();
            nVar.f4236b = dVar.i();
            c0043l.c[i3] = nVar;
        }
        c0043l.d = dVar.j();
        return c0043l;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.a.b.b.l$l, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$l.class */
    public static final class C0043l extends s {

        /* renamed from: a, reason: collision with root package name */
        private int f4231a;

        /* renamed from: b, reason: collision with root package name */
        private int f4232b;
        private n[] c;
        private int d;

        /* synthetic */ C0043l(org.a.b.b.a aVar, byte b2) {
            this(aVar);
        }

        private C0043l(org.a.b.b.a aVar) {
            super(aVar);
        }

        @Override // org.a.b.b.s
        public final int a(int i) {
            for (int i2 = 0; i2 < this.f4232b; i2++) {
                if (this.c[i2].f4235a <= i) {
                    if (i2 + 1 >= this.f4232b) {
                        if (this.d <= i) {
                            return -1;
                        }
                        return this.c[i2].f4236b;
                    }
                    if (this.c[i2 + 1].f4235a > i) {
                        return this.c[i2].f4236b;
                    }
                }
            }
            return 0;
        }

        public final String toString() {
            return getClass().getName() + "[format=" + this.f4231a + " nbRanges=" + this.f4232b + ", range3=" + Arrays.toString(this.c) + " sentinel=" + this.d + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$n.class */
    public static final class n {

        /* renamed from: a, reason: collision with root package name */
        private int f4235a;

        /* renamed from: b, reason: collision with root package name */
        private int f4236b;

        private n() {
        }

        /* synthetic */ n(byte b2) {
            this();
        }

        public final String toString() {
            return getClass().getName() + "[first=" + this.f4235a + ", fd=" + this.f4236b + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$h.class */
    public static class h extends s {

        /* renamed from: a, reason: collision with root package name */
        private int[] f4224a;

        /* synthetic */ h(org.a.b.b.a aVar, byte b2) {
            this(aVar);
        }

        private h(org.a.b.b.a aVar) {
            super(aVar);
        }

        @Override // org.a.b.b.s
        public final int a(int i) {
            if (i < this.f4224a.length) {
                return this.f4224a[i];
            }
            return 0;
        }

        public final String toString() {
            return getClass().getName() + "[fds=" + Arrays.toString(this.f4224a) + "]";
        }
    }

    private org.a.b.b.c a(org.a.b.b.d dVar, int i2, boolean z) {
        int i3 = dVar.i();
        switch (i3) {
            case 0:
                return a(dVar, i3, i2, z);
            case 1:
                return b(dVar, i3, i2, z);
            case 2:
                return c(dVar, i3, i2, z);
            default:
                throw new IllegalArgumentException();
        }
    }

    private f a(org.a.b.b.d dVar, int i2, int i3, boolean z) {
        f fVar = new f(z);
        fVar.f4221a = i2;
        if (z) {
            fVar.a(0, 0);
        } else {
            fVar.a(0, 0, ".notdef");
        }
        for (int i4 = 1; i4 < i3; i4++) {
            int l = dVar.l();
            if (z) {
                fVar.a(i4, l);
            } else {
                fVar.a(i4, l, a(l));
            }
        }
        return fVar;
    }

    private i b(org.a.b.b.d dVar, int i2, int i3, boolean z) {
        i iVar = new i(z);
        iVar.f4225a = i2;
        if (z) {
            iVar.a(0, 0);
            iVar.f4226b = new ArrayList();
        } else {
            iVar.a(0, 0, ".notdef");
        }
        int i4 = 1;
        while (i4 < i3) {
            int l = dVar.l();
            int i5 = dVar.i();
            if (!z) {
                for (int i6 = 0; i6 < i5 + 1; i6++) {
                    int i7 = l + i6;
                    iVar.a(i4 + i6, i7, a(i7));
                }
            } else {
                iVar.f4226b.add(new o(i4, l, i5, (byte) 0));
            }
            i4 = i4 + i5 + 1;
        }
        return iVar;
    }

    private k c(org.a.b.b.d dVar, int i2, int i3, boolean z) {
        k kVar = new k(z);
        kVar.f4229a = i2;
        if (z) {
            kVar.a(0, 0);
            kVar.f4230b = new ArrayList();
        } else {
            kVar.a(0, 0, ".notdef");
        }
        int i4 = 1;
        while (i4 < i3) {
            int l = dVar.l();
            int j2 = dVar.j();
            if (!z) {
                for (int i5 = 0; i5 < j2 + 1; i5++) {
                    int i6 = l + i5;
                    kVar.a(i4 + i5, i6, a(i6));
                }
            } else {
                kVar.f4230b.add(new o(i4, l, j2, (byte) 0));
            }
            i4 = i4 + j2 + 1;
        }
        return kVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$m.class */
    public static class m {

        /* renamed from: a, reason: collision with root package name */
        private int f4233a;

        /* renamed from: b, reason: collision with root package name */
        private int f4234b;
        private int c;
        private int d;

        private m() {
        }

        /* synthetic */ m(byte b2) {
            this();
        }

        public final String toString() {
            return getClass().getName() + "[major=" + this.f4233a + ", minor=" + this.f4234b + ", hdrSize=" + this.c + ", offSize=" + this.d + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$c.class */
    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private final Map<String, a> f4218a;

        private c() {
            this.f4218a = new HashMap();
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        public final void a(a aVar) {
            if (aVar.f4220b == null) {
                return;
            }
            this.f4218a.put(aVar.f4220b.a(), aVar);
        }

        public final a a(String str) {
            return this.f4218a.get(str);
        }

        public final Boolean a(String str, boolean z) {
            a a2 = a(str);
            return Boolean.valueOf((a2 == null || a2.a().isEmpty()) ? false : a2.b(0).booleanValue());
        }

        public final List<Number> a(String str, List<Number> list) {
            a a2 = a(str);
            return (a2 == null || a2.a().isEmpty()) ? list : a2.a();
        }

        public final Number a(String str, Number number) {
            a a2 = a(str);
            return (a2 == null || a2.a().isEmpty()) ? number : a2.a(0);
        }

        public final List<Number> b(String str, List<Number> list) {
            a a2 = a(str);
            return (a2 == null || a2.a().isEmpty()) ? list : a2.b();
        }

        public final String toString() {
            return getClass().getName() + "[entries=" + this.f4218a + "]";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:org/a/b/b/l$c$a.class */
        public static class a {

            /* renamed from: a, reason: collision with root package name */
            private List<Number> f4219a;

            /* renamed from: b, reason: collision with root package name */
            private org.a.b.b.k f4220b;

            private a() {
                this.f4219a = new ArrayList();
                this.f4220b = null;
            }

            /* synthetic */ a(byte b2) {
                this();
            }

            public final Number a(int i) {
                return this.f4219a.get(i);
            }

            public final Boolean b(int i) {
                Number number = this.f4219a.get(0);
                if (number instanceof Integer) {
                    switch (number.intValue()) {
                        case 0:
                            return Boolean.FALSE;
                        case 1:
                            return Boolean.TRUE;
                    }
                }
                throw new IllegalArgumentException();
            }

            public final List<Number> a() {
                return this.f4219a;
            }

            public final List<Number> b() {
                ArrayList arrayList = new ArrayList(this.f4219a);
                for (int i = 1; i < arrayList.size(); i++) {
                    arrayList.set(i, Integer.valueOf(((Number) arrayList.get(i - 1)).intValue() + ((Number) arrayList.get(i)).intValue()));
                }
                return arrayList;
            }

            public final String toString() {
                return getClass().getName() + "[operands=" + this.f4219a + ", operator=" + this.f4220b + "]";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$b.class */
    public static abstract class b extends org.a.b.b.e {

        /* renamed from: a, reason: collision with root package name */
        private int f4214a;

        /* renamed from: b, reason: collision with root package name */
        private a[] f4215b;

        b() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: infinitode-2.jar:org/a/b/b/l$b$a.class */
        public static class a {

            /* renamed from: a, reason: collision with root package name */
            private int f4216a;

            /* renamed from: b, reason: collision with root package name */
            private int f4217b;

            a() {
            }

            public final String toString() {
                return getClass().getName() + "[code=" + this.f4216a + ", sid=" + this.f4217b + "]";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$g.class */
    public static class g extends b {

        /* renamed from: a, reason: collision with root package name */
        private int f4222a;

        /* renamed from: b, reason: collision with root package name */
        private int f4223b;

        private g() {
        }

        /* synthetic */ g(byte b2) {
            this();
        }

        public final String toString() {
            return getClass().getName() + "[format=" + this.f4222a + ", nCodes=" + this.f4223b + ", supplement=" + Arrays.toString(((b) this).f4215b) + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$j.class */
    public static class j extends b {

        /* renamed from: a, reason: collision with root package name */
        private int f4227a;

        /* renamed from: b, reason: collision with root package name */
        private int f4228b;

        private j() {
        }

        /* synthetic */ j(byte b2) {
            this();
        }

        public final String toString() {
            return getClass().getName() + "[format=" + this.f4227a + ", nRanges=" + this.f4228b + ", supplement=" + Arrays.toString(((b) this).f4215b) + "]";
        }
    }

    /* loaded from: infinitode-2.jar:org/a/b/b/l$d.class */
    static abstract class d extends org.a.b.b.c {
        protected d(boolean z) {
            super(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$e.class */
    public static class e extends d {
        protected e(int i) {
            super(true);
            a(0, 0);
            for (int i2 = 1; i2 <= i; i2++) {
                int i3 = i2;
                a(i3, i3);
            }
        }

        public final String toString() {
            return getClass().getName();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$f.class */
    public static class f extends d {

        /* renamed from: a, reason: collision with root package name */
        private int f4221a;

        protected f(boolean z) {
            super(z);
        }

        public final String toString() {
            return getClass().getName() + "[format=" + this.f4221a + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$i.class */
    public static class i extends d {

        /* renamed from: a, reason: collision with root package name */
        private int f4225a;

        /* renamed from: b, reason: collision with root package name */
        private List<o> f4226b;

        protected i(boolean z) {
            super(z);
        }

        @Override // org.a.b.b.c
        public final int c(int i) {
            if (a()) {
                for (o oVar : this.f4226b) {
                    if (oVar.a(i)) {
                        return oVar.b(i);
                    }
                }
            }
            return super.c(i);
        }

        public final String toString() {
            return getClass().getName() + "[format=" + this.f4225a + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$k.class */
    public static class k extends d {

        /* renamed from: a, reason: collision with root package name */
        private int f4229a;

        /* renamed from: b, reason: collision with root package name */
        private List<o> f4230b;

        protected k(boolean z) {
            super(z);
        }

        @Override // org.a.b.b.c
        public final int c(int i) {
            for (o oVar : this.f4230b) {
                if (oVar.a(i)) {
                    return oVar.b(i);
                }
            }
            return super.c(i);
        }

        public final String toString() {
            return getClass().getName() + "[format=" + this.f4229a + "]";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/l$o.class */
    public static final class o {

        /* renamed from: a, reason: collision with root package name */
        private final int f4237a;

        /* renamed from: b, reason: collision with root package name */
        private final int f4238b;
        private final int c;
        private final int d;

        /* synthetic */ o(int i, int i2, int i3, byte b2) {
            this(i, i2, i3);
        }

        private o(int i, int i2, int i3) {
            this.f4237a = i;
            this.f4238b = this.f4237a + i3;
            this.c = i2;
            this.d = this.c + i3;
        }

        final boolean a(int i) {
            return i >= this.c && i <= this.d;
        }

        final int b(int i) {
            if (a(i)) {
                return this.f4237a + (i - this.c);
            }
            return 0;
        }

        public final String toString() {
            return getClass().getName() + "[start value=" + this.f4237a + ", end value=" + this.f4238b + ", start mapped-value=" + this.c + ", end mapped-value=" + this.d + "]";
        }
    }

    public String toString() {
        return getClass().getSimpleName() + "[" + this.d + "]";
    }
}
