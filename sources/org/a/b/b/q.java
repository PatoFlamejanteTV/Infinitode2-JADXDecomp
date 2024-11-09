package org.a.b.b;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/b/q.class */
public final class q {
    private a c = null;

    /* renamed from: a, reason: collision with root package name */
    public static final Map<a, String> f4243a;

    /* renamed from: b, reason: collision with root package name */
    public static final Map<a, String> f4244b;

    public q(int i) {
        a(new a(i));
    }

    public q(int i, int i2) {
        a(new a(i, i2));
    }

    public q(int[] iArr) {
        a(new a(iArr));
    }

    public final a a() {
        return this.c;
    }

    private void a(a aVar) {
        this.c = aVar;
    }

    public final String toString() {
        String str = f4244b.get(a());
        String str2 = str;
        if (str == null) {
            str2 = f4243a.get(a());
        }
        if (str2 == null) {
            return a().toString() + '|';
        }
        return str2 + '|';
    }

    public final int hashCode() {
        return a().hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj instanceof q) {
            return a().equals(((q) obj).a());
        }
        return false;
    }

    /* loaded from: infinitode-2.jar:org/a/b/b/q$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private int[] f4245a = null;

        public a(int i) {
            a(new int[]{i});
        }

        public a(int i, int i2) {
            a(new int[]{i, i2});
        }

        public a(int[] iArr) {
            a(iArr);
        }

        public final int[] a() {
            return this.f4245a;
        }

        private void a(int[] iArr) {
            this.f4245a = iArr;
        }

        public final String toString() {
            return Arrays.toString(a());
        }

        public final int hashCode() {
            if (this.f4245a[0] == 12 && this.f4245a.length > 1) {
                return this.f4245a[0] ^ this.f4245a[1];
            }
            return this.f4245a[0];
        }

        public final boolean equals(Object obj) {
            if (obj instanceof a) {
                a aVar = (a) obj;
                return (this.f4245a[0] == 12 && aVar.f4245a[0] == 12) ? (this.f4245a.length <= 1 || aVar.f4245a.length <= 1) ? this.f4245a.length == aVar.f4245a.length : this.f4245a[1] == aVar.f4245a[1] : this.f4245a[0] == aVar.f4245a[0];
            }
            return false;
        }
    }

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap(26);
        linkedHashMap.put(new a(1), "hstem");
        linkedHashMap.put(new a(3), "vstem");
        linkedHashMap.put(new a(4), "vmoveto");
        linkedHashMap.put(new a(5), "rlineto");
        linkedHashMap.put(new a(6), "hlineto");
        linkedHashMap.put(new a(7), "vlineto");
        linkedHashMap.put(new a(8), "rrcurveto");
        linkedHashMap.put(new a(9), "closepath");
        linkedHashMap.put(new a(10), "callsubr");
        linkedHashMap.put(new a(11), "return");
        linkedHashMap.put(new a(12), "escape");
        linkedHashMap.put(new a(12, 0), "dotsection");
        linkedHashMap.put(new a(12, 1), "vstem3");
        linkedHashMap.put(new a(12, 2), "hstem3");
        linkedHashMap.put(new a(12, 6), "seac");
        linkedHashMap.put(new a(12, 7), "sbw");
        linkedHashMap.put(new a(12, 12), FlexmarkHtmlConverter.DIV_NODE);
        linkedHashMap.put(new a(12, 16), "callothersubr");
        linkedHashMap.put(new a(12, 17), "pop");
        linkedHashMap.put(new a(12, 33), "setcurrentpoint");
        linkedHashMap.put(new a(13), "hsbw");
        linkedHashMap.put(new a(14), "endchar");
        linkedHashMap.put(new a(21), "rmoveto");
        linkedHashMap.put(new a(22), "hmoveto");
        linkedHashMap.put(new a(30), "vhcurveto");
        linkedHashMap.put(new a(31), "hvcurveto");
        f4243a = Collections.unmodifiableMap(linkedHashMap);
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(48);
        linkedHashMap2.put(new a(1), "hstem");
        linkedHashMap2.put(new a(3), "vstem");
        linkedHashMap2.put(new a(4), "vmoveto");
        linkedHashMap2.put(new a(5), "rlineto");
        linkedHashMap2.put(new a(6), "hlineto");
        linkedHashMap2.put(new a(7), "vlineto");
        linkedHashMap2.put(new a(8), "rrcurveto");
        linkedHashMap2.put(new a(10), "callsubr");
        linkedHashMap2.put(new a(11), "return");
        linkedHashMap2.put(new a(12), "escape");
        linkedHashMap2.put(new a(12, 3), "and");
        linkedHashMap2.put(new a(12, 4), "or");
        linkedHashMap2.put(new a(12, 5), "not");
        linkedHashMap2.put(new a(12, 9), "abs");
        linkedHashMap2.put(new a(12, 10), "add");
        linkedHashMap2.put(new a(12, 11), FlexmarkHtmlConverter.SUB_NODE);
        linkedHashMap2.put(new a(12, 12), FlexmarkHtmlConverter.DIV_NODE);
        linkedHashMap2.put(new a(12, 14), "neg");
        linkedHashMap2.put(new a(12, 15), "eq");
        linkedHashMap2.put(new a(12, 18), "drop");
        linkedHashMap2.put(new a(12, 20), "put");
        linkedHashMap2.put(new a(12, 21), "get");
        linkedHashMap2.put(new a(12, 22), "ifelse");
        linkedHashMap2.put(new a(12, 23), "random");
        linkedHashMap2.put(new a(12, 24), "mul");
        linkedHashMap2.put(new a(12, 26), "sqrt");
        linkedHashMap2.put(new a(12, 27), "dup");
        linkedHashMap2.put(new a(12, 28), "exch");
        linkedHashMap2.put(new a(12, 29), "index");
        linkedHashMap2.put(new a(12, 30), "roll");
        linkedHashMap2.put(new a(12, 34), "hflex");
        linkedHashMap2.put(new a(12, 35), "flex");
        linkedHashMap2.put(new a(12, 36), "hflex1");
        linkedHashMap2.put(new a(12, 37), "flex1");
        linkedHashMap2.put(new a(14), "endchar");
        linkedHashMap2.put(new a(18), "hstemhm");
        linkedHashMap2.put(new a(19), "hintmask");
        linkedHashMap2.put(new a(20), "cntrmask");
        linkedHashMap2.put(new a(21), "rmoveto");
        linkedHashMap2.put(new a(22), "hmoveto");
        linkedHashMap2.put(new a(23), "vstemhm");
        linkedHashMap2.put(new a(24), "rcurveline");
        linkedHashMap2.put(new a(25), "rlinecurve");
        linkedHashMap2.put(new a(26), "vvcurveto");
        linkedHashMap2.put(new a(27), "hhcurveto");
        linkedHashMap2.put(new a(28), "shortint");
        linkedHashMap2.put(new a(29), "callgsubr");
        linkedHashMap2.put(new a(30), "vhcurveto");
        linkedHashMap2.put(new a(31), "hvcurveto");
        f4244b = Collections.unmodifiableMap(linkedHashMap2);
    }
}
