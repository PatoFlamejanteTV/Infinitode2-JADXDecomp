package org.a.b.b;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/b/k.class */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    private a f4209a = null;

    /* renamed from: b, reason: collision with root package name */
    private String f4210b = null;
    private static Map<a, k> c = new LinkedHashMap(52);
    private static Map<String, k> d = new LinkedHashMap(52);

    private k(a aVar, String str) {
        b(aVar);
        a(str);
    }

    private a b() {
        return this.f4209a;
    }

    private void b(a aVar) {
        this.f4209a = aVar;
    }

    public final String a() {
        return this.f4210b;
    }

    private void a(String str) {
        this.f4210b = str;
    }

    public final String toString() {
        return a();
    }

    public final int hashCode() {
        return b().hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof k) {
            return b().equals(((k) obj).b());
        }
        return false;
    }

    private static void a(a aVar, String str) {
        k kVar = new k(aVar, str);
        c.put(aVar, kVar);
        d.put(str, kVar);
    }

    public static k a(a aVar) {
        return c.get(aVar);
    }

    /* loaded from: infinitode-2.jar:org/a/b/b/k$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int[] f4211a;

        public a(int i) {
            this(new int[]{i});
        }

        public a(int i, int i2) {
            this(new int[]{i, i2});
        }

        private a(int[] iArr) {
            this.f4211a = null;
            a(iArr);
        }

        private int[] a() {
            return this.f4211a;
        }

        private void a(int[] iArr) {
            this.f4211a = iArr;
        }

        public final String toString() {
            return Arrays.toString(a());
        }

        public final int hashCode() {
            return Arrays.hashCode(a());
        }

        public final boolean equals(Object obj) {
            if (obj instanceof a) {
                return Arrays.equals(a(), ((a) obj).a());
            }
            return false;
        }
    }

    static {
        a(new a(0), "version");
        a(new a(1), "Notice");
        a(new a(12, 0), "Copyright");
        a(new a(2), "FullName");
        a(new a(3), "FamilyName");
        a(new a(4), "Weight");
        a(new a(12, 1), "isFixedPitch");
        a(new a(12, 2), "ItalicAngle");
        a(new a(12, 3), "UnderlinePosition");
        a(new a(12, 4), "UnderlineThickness");
        a(new a(12, 5), "PaintType");
        a(new a(12, 6), "CharstringType");
        a(new a(12, 7), "FontMatrix");
        a(new a(13), "UniqueID");
        a(new a(5), "FontBBox");
        a(new a(12, 8), "StrokeWidth");
        a(new a(14), "XUID");
        a(new a(15), "charset");
        a(new a(16), "Encoding");
        a(new a(17), "CharStrings");
        a(new a(18), "Private");
        a(new a(12, 20), "SyntheticBase");
        a(new a(12, 21), "PostScript");
        a(new a(12, 22), "BaseFontName");
        a(new a(12, 23), "BaseFontBlend");
        a(new a(12, 30), "ROS");
        a(new a(12, 31), "CIDFontVersion");
        a(new a(12, 32), "CIDFontRevision");
        a(new a(12, 33), "CIDFontType");
        a(new a(12, 34), "CIDCount");
        a(new a(12, 35), "UIDBase");
        a(new a(12, 36), "FDArray");
        a(new a(12, 37), "FDSelect");
        a(new a(12, 38), "FontName");
        a(new a(6), "BlueValues");
        a(new a(7), "OtherBlues");
        a(new a(8), "FamilyBlues");
        a(new a(9), "FamilyOtherBlues");
        a(new a(12, 9), "BlueScale");
        a(new a(12, 10), "BlueShift");
        a(new a(12, 11), "BlueFuzz");
        a(new a(10), "StdHW");
        a(new a(11), "StdVW");
        a(new a(12, 12), "StemSnapH");
        a(new a(12, 13), "StemSnapV");
        a(new a(12, 14), "ForceBold");
        a(new a(12, 15), "LanguageGroup");
        a(new a(12, 16), "ExpansionFactor");
        a(new a(12, 17), "initialRandomSeed");
        a(new a(19), "Subrs");
        a(new a(20), "defaultWidthX");
        a(new a(21), "nominalWidthX");
    }
}
