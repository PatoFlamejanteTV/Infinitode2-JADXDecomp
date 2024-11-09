package org.a.b.f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/f/n.class */
public class n extends an {
    private static final org.a.a.a.a c = org.a.a.a.c.a(n.class);
    private LinkedHashMap<String, C0045n> d;
    private d[] e;
    private i[] f;
    private final Map<Integer, Integer> g;
    private final Map<Integer, Integer> h;
    private String i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(ap apVar) {
        super(apVar);
        this.g = new HashMap();
        this.h = new HashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        long e2 = akVar.e();
        akVar.c();
        int c2 = akVar.c();
        int c3 = akVar.c();
        int c4 = akVar.c();
        int c5 = akVar.c();
        if (c2 == 1) {
            akVar.k();
        }
        this.d = a(akVar, e2 + c3);
        this.e = d(akVar, e2 + c4);
        this.f = f(akVar, e2 + c5);
    }

    private LinkedHashMap<String, C0045n> a(ak akVar, long j2) {
        akVar.a(j2);
        int c2 = akVar.c();
        m[] mVarArr = new m[c2];
        int[] iArr = new int[c2];
        for (int i2 = 0; i2 < c2; i2++) {
            m mVar = new m();
            mVar.f4324a = akVar.a(4);
            iArr[i2] = akVar.c();
            mVarArr[i2] = mVar;
        }
        for (int i3 = 0; i3 < c2; i3++) {
            mVarArr[i3].f4325b = b(akVar, j2 + iArr[i3]);
        }
        LinkedHashMap<String, C0045n> linkedHashMap = new LinkedHashMap<>(c2);
        for (m mVar2 : mVarArr) {
            linkedHashMap.put(mVar2.f4324a, mVar2.f4325b);
        }
        return linkedHashMap;
    }

    private C0045n b(ak akVar, long j2) {
        akVar.a(j2);
        C0045n c0045n = new C0045n();
        int c2 = akVar.c();
        int c3 = akVar.c();
        f[] fVarArr = new f[c3];
        int[] iArr = new int[c3];
        String str = "";
        for (int i2 = 0; i2 < c3; i2++) {
            f fVar = new f();
            fVar.f4314a = akVar.a(4);
            if (i2 > 0 && fVar.f4314a.compareTo(str) <= 0) {
                throw new IOException("LangSysRecords not alphabetically sorted by LangSys tag: " + fVar.f4314a + " <= " + str);
            }
            iArr[i2] = akVar.c();
            fVarArr[i2] = fVar;
            str = fVar.f4314a;
        }
        if (c2 != 0) {
            c0045n.f4326a = c(akVar, j2 + c2);
        }
        for (int i3 = 0; i3 < c3; i3++) {
            fVarArr[i3].f4315b = c(akVar, j2 + iArr[i3]);
        }
        c0045n.f4327b = new LinkedHashMap<>(c3);
        for (f fVar2 : fVarArr) {
            c0045n.f4327b.put(fVar2.f4314a, fVar2.f4315b);
        }
        return c0045n;
    }

    private static g c(ak akVar, long j2) {
        akVar.a(j2);
        g gVar = new g();
        akVar.c();
        gVar.f4316a = akVar.c();
        int c2 = akVar.c();
        gVar.f4317b = new int[c2];
        for (int i2 = 0; i2 < c2; i2++) {
            gVar.f4317b[i2] = akVar.c();
        }
        return gVar;
    }

    private d[] d(ak akVar, long j2) {
        akVar.a(j2);
        int c2 = akVar.c();
        d[] dVarArr = new d[c2];
        int[] iArr = new int[c2];
        for (int i2 = 0; i2 < c2; i2++) {
            d dVar = new d();
            dVar.f4311a = akVar.a(4);
            iArr[i2] = akVar.c();
            dVarArr[i2] = dVar;
        }
        for (int i3 = 0; i3 < c2; i3++) {
            dVarArr[i3].f4312b = e(akVar, j2 + iArr[i3]);
        }
        return dVarArr;
    }

    private static e e(ak akVar, long j2) {
        akVar.a(j2);
        e eVar = new e();
        akVar.c();
        int c2 = akVar.c();
        eVar.f4313a = new int[c2];
        for (int i2 = 0; i2 < c2; i2++) {
            eVar.f4313a[i2] = akVar.c();
        }
        return eVar;
    }

    private i[] f(ak akVar, long j2) {
        akVar.a(j2);
        int c2 = akVar.c();
        int[] iArr = new int[c2];
        for (int i2 = 0; i2 < c2; i2++) {
            iArr[i2] = akVar.c();
        }
        i[] iVarArr = new i[c2];
        for (int i3 = 0; i3 < c2; i3++) {
            iVarArr[i3] = g(akVar, j2 + iArr[i3]);
        }
        return iVarArr;
    }

    private i g(ak akVar, long j2) {
        akVar.a(j2);
        i iVar = new i();
        iVar.f4320a = akVar.c();
        iVar.f4321b = akVar.c();
        int c2 = akVar.c();
        int[] iArr = new int[c2];
        for (int i2 = 0; i2 < c2; i2++) {
            iArr[i2] = akVar.c();
        }
        if ((iVar.f4321b & 16) != 0) {
            iVar.c = akVar.c();
        }
        iVar.d = new h[c2];
        switch (iVar.f4320a) {
            case 1:
                for (int i3 = 0; i3 < c2; i3++) {
                    iVar.d[i3] = h(akVar, j2 + iArr[i3]);
                }
                break;
            default:
                new StringBuilder("Type ").append(iVar.f4320a).append(" GSUB lookup table is not supported and will be ignored");
                break;
        }
        return iVar;
    }

    private h h(ak akVar, long j2) {
        akVar.a(j2);
        int c2 = akVar.c();
        switch (c2) {
            case 1:
                j jVar = new j();
                jVar.f4318a = c2;
                int c3 = akVar.c();
                jVar.c = akVar.d();
                jVar.f4319b = i(akVar, j2 + c3);
                return jVar;
            case 2:
                k kVar = new k();
                kVar.f4318a = c2;
                int c4 = akVar.c();
                int c5 = akVar.c();
                kVar.c = new int[c5];
                for (int i2 = 0; i2 < c5; i2++) {
                    kVar.c[i2] = akVar.c();
                }
                kVar.f4319b = i(akVar, j2 + c4);
                return kVar;
            default:
                throw new IllegalArgumentException("Unknown substFormat: " + c2);
        }
    }

    private a i(ak akVar, long j2) {
        akVar.a(j2);
        int c2 = akVar.c();
        switch (c2) {
            case 1:
                b bVar = new b();
                bVar.f4308a = c2;
                int c3 = akVar.c();
                bVar.f4309b = new int[c3];
                for (int i2 = 0; i2 < c3; i2++) {
                    bVar.f4309b[i2] = akVar.c();
                }
                return bVar;
            case 2:
                c cVar = new c();
                cVar.f4308a = c2;
                int c4 = akVar.c();
                cVar.f4310b = new l[c4];
                for (int i3 = 0; i3 < c4; i3++) {
                    cVar.f4310b[i3] = a(akVar);
                }
                return cVar;
            default:
                throw new IllegalArgumentException("Unknown coverage format: " + c2);
        }
    }

    private String a(String[] strArr) {
        if (strArr.length == 1) {
            String str = strArr[0];
            if ("Inherited".equals(str) || ("DFLT".equals(str) && !this.d.containsKey(str))) {
                if (this.i == null) {
                    this.i = this.d.keySet().iterator().next();
                }
                return this.i;
            }
        }
        for (String str2 : strArr) {
            if (this.d.containsKey(str2)) {
                this.i = str2;
                return this.i;
            }
        }
        return strArr[0];
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Collection<g> b(String str) {
        Collection emptyList = Collections.emptyList();
        C0045n c0045n = this.d.get(str);
        if (c0045n != null) {
            if (c0045n.f4326a == null) {
                emptyList = c0045n.f4327b.values();
            } else {
                Collection arrayList = new ArrayList(c0045n.f4327b.values());
                emptyList = arrayList;
                arrayList.add(c0045n.f4326a);
            }
        }
        return emptyList;
    }

    private List<d> a(Collection<g> collection, List<String> list) {
        if (collection.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (g gVar : collection) {
            int i2 = gVar.f4316a;
            if (i2 != 65535) {
                arrayList.add(this.e[i2]);
            }
            for (int i3 : gVar.f4317b) {
                if (list == null || list.contains(this.e[i3].f4311a)) {
                    arrayList.add(this.e[i3]);
                }
            }
        }
        if (a(arrayList, "vrt2")) {
            b(arrayList, "vert");
        }
        if (list != null && arrayList.size() > 1) {
            Collections.sort(arrayList, new o(this, list));
        }
        return arrayList;
    }

    private static boolean a(List<d> list, String str) {
        Iterator<d> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().f4311a.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private static void b(List<d> list, String str) {
        Iterator<d> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().f4311a.equals(str)) {
                it.remove();
            }
        }
    }

    private int a(d dVar, int i2) {
        for (int i3 : dVar.f4312b.f4313a) {
            i iVar = this.f[i3];
            if (iVar.f4320a != 1) {
                new StringBuilder("Skipping GSUB feature '").append(dVar.f4311a).append("' because it requires unsupported lookup table type ").append(iVar.f4320a);
            } else {
                i2 = a(iVar, i2);
            }
        }
        return i2;
    }

    private static int a(i iVar, int i2) {
        for (h hVar : iVar.d) {
            int a2 = hVar.f4319b.a(i2);
            if (a2 >= 0) {
                return hVar.a(i2, a2);
            }
        }
        return i2;
    }

    public final int a(int i2, String[] strArr, List<String> list) {
        if (i2 == -1) {
            return -1;
        }
        Integer num = this.g.get(Integer.valueOf(i2));
        if (num != null) {
            return num.intValue();
        }
        List<d> a2 = a(b(a(strArr)), list);
        int i3 = i2;
        Iterator<d> it = a2.iterator();
        while (it.hasNext()) {
            i3 = a(it.next(), i3);
        }
        this.g.put(Integer.valueOf(i2), Integer.valueOf(i3));
        this.h.put(Integer.valueOf(i3), Integer.valueOf(i2));
        return i3;
    }

    public final int a(int i2) {
        Integer num = this.h.get(Integer.valueOf(i2));
        if (num == null) {
            new StringBuilder("Trying to un-substitute a never-before-seen gid: ").append(i2);
            return i2;
        }
        return num.intValue();
    }

    private static l a(ak akVar) {
        l lVar = new l();
        lVar.f4322a = akVar.c();
        lVar.f4323b = akVar.c();
        lVar.c = akVar.c();
        return lVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$m.class */
    public static class m {

        /* renamed from: a, reason: collision with root package name */
        String f4324a;

        /* renamed from: b, reason: collision with root package name */
        C0045n f4325b;

        m() {
        }

        public final String toString() {
            return String.format("ScriptRecord[scriptTag=%s]", this.f4324a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.a.b.f.n$n, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$n.class */
    public static class C0045n {

        /* renamed from: a, reason: collision with root package name */
        g f4326a;

        /* renamed from: b, reason: collision with root package name */
        LinkedHashMap<String, g> f4327b;

        C0045n() {
        }

        public final String toString() {
            Object[] objArr = new Object[2];
            objArr[0] = Boolean.valueOf(this.f4326a != null);
            objArr[1] = Integer.valueOf(this.f4327b.size());
            return String.format("ScriptTable[hasDefault=%s,langSysRecordsCount=%d]", objArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$f.class */
    public static class f {

        /* renamed from: a, reason: collision with root package name */
        String f4314a;

        /* renamed from: b, reason: collision with root package name */
        g f4315b;

        f() {
        }

        public final String toString() {
            return String.format("LangSysRecord[langSysTag=%s]", this.f4314a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$g.class */
    public static class g {

        /* renamed from: a, reason: collision with root package name */
        int f4316a;

        /* renamed from: b, reason: collision with root package name */
        int[] f4317b;

        g() {
        }

        public final String toString() {
            return String.format("LangSysTable[requiredFeatureIndex=%d]", Integer.valueOf(this.f4316a));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$d.class */
    public static class d {

        /* renamed from: a, reason: collision with root package name */
        String f4311a;

        /* renamed from: b, reason: collision with root package name */
        e f4312b;

        d() {
        }

        public final String toString() {
            return String.format("FeatureRecord[featureTag=%s]", this.f4311a);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$e.class */
    public static class e {

        /* renamed from: a, reason: collision with root package name */
        int[] f4313a;

        e() {
        }

        public final String toString() {
            return String.format("FeatureTable[lookupListIndiciesCount=%d]", Integer.valueOf(this.f4313a.length));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$i.class */
    public static class i {

        /* renamed from: a, reason: collision with root package name */
        int f4320a;

        /* renamed from: b, reason: collision with root package name */
        int f4321b;
        int c;
        h[] d;

        i() {
        }

        public final String toString() {
            return String.format("LookupTable[lookupType=%d,lookupFlag=%d,markFilteringSet=%d]", Integer.valueOf(this.f4320a), Integer.valueOf(this.f4321b), Integer.valueOf(this.c));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$h.class */
    public static abstract class h {

        /* renamed from: a, reason: collision with root package name */
        int f4318a;

        /* renamed from: b, reason: collision with root package name */
        a f4319b;

        abstract int a(int i, int i2);

        h() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$j.class */
    public static class j extends h {
        short c;

        j() {
        }

        @Override // org.a.b.f.n.h
        final int a(int i, int i2) {
            return i2 < 0 ? i : i + this.c;
        }

        public final String toString() {
            return String.format("LookupTypeSingleSubstFormat1[substFormat=%d,deltaGlyphID=%d]", Integer.valueOf(this.f4318a), Short.valueOf(this.c));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$k.class */
    public static class k extends h {
        int[] c;

        k() {
        }

        @Override // org.a.b.f.n.h
        final int a(int i, int i2) {
            return i2 < 0 ? i : this.c[i2];
        }

        public final String toString() {
            return String.format("LookupTypeSingleSubstFormat2[substFormat=%d,substituteGlyphIDs=%s]", Integer.valueOf(this.f4318a), Arrays.toString(this.c));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$a.class */
    public static abstract class a {

        /* renamed from: a, reason: collision with root package name */
        int f4308a;

        abstract int a(int i);

        a() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$b.class */
    public static class b extends a {

        /* renamed from: b, reason: collision with root package name */
        int[] f4309b;

        b() {
        }

        @Override // org.a.b.f.n.a
        final int a(int i) {
            return Arrays.binarySearch(this.f4309b, i);
        }

        public final String toString() {
            return String.format("CoverageTableFormat1[coverageFormat=%d,glyphArray=%s]", Integer.valueOf(this.f4308a), Arrays.toString(this.f4309b));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$c.class */
    public static class c extends a {

        /* renamed from: b, reason: collision with root package name */
        l[] f4310b;

        c() {
        }

        @Override // org.a.b.f.n.a
        final int a(int i) {
            for (l lVar : this.f4310b) {
                if (lVar.f4322a <= i && i <= lVar.f4323b) {
                    return (lVar.c + i) - lVar.f4322a;
                }
            }
            return -1;
        }

        public final String toString() {
            return String.format("CoverageTableFormat2[coverageFormat=%d]", Integer.valueOf(this.f4308a));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/f/n$l.class */
    public static class l {

        /* renamed from: a, reason: collision with root package name */
        int f4322a;

        /* renamed from: b, reason: collision with root package name */
        int f4323b;
        int c;

        l() {
        }

        public final String toString() {
            return String.format("RangeRecord[startGlyphID=%d,endGlyphID=%d,startCoverageIndex=%d]", Integer.valueOf(this.f4322a), Integer.valueOf(this.f4323b), Integer.valueOf(this.c));
        }
    }
}
