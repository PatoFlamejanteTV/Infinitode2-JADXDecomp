package com.d.c.a;

import com.d.c.d.a.c;
import com.d.c.d.a.g;
import com.d.c.d.a.h;
import com.d.c.d.a.i;
import com.d.c.d.a.j;
import com.d.c.d.a.l;
import com.d.c.d.a.m;
import com.d.c.d.a.n;
import com.d.c.d.a.o;
import com.d.c.f.g;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: infinitode-2.jar:com/d/c/a/a.class */
public final class a implements Comparable {
    private static int bq;
    private final String br;
    private final String bs;
    private final boolean bt;
    private g bu;
    private final boolean bv;
    private final m bw;

    /* renamed from: a, reason: collision with root package name */
    public final int f964a;
    private static final a[] bx;
    public static final a s;
    public static final a t;
    public static final a u;
    public static final a v;
    public static final a w;
    public static final a x;
    public static final a y;
    public static final a z;
    public static final a A;
    public static final a B;
    public static final a C;
    public static final a D;
    public static final a E;
    public static final a F;
    public static final a G;
    public static final a H;
    public static final a I;
    public static final a J;
    public static final a K;
    public static final a L;
    public static final a M;
    public static final a N;
    public static final a O;
    public static final a P;
    public static final a Q;
    public static final a R;
    public static final a S;
    public static final a T;
    public static final a U;
    public static final a V;
    public static final a W;
    public static final a X;
    public static final a Y;
    public static final a Z;
    public static final a aa;
    public static final a ab;
    public static final a ac;
    public static final a ad;
    public static final a ae;
    public static final a af;
    public static final a ag;
    public static final a ah;
    public static final a ai;
    public static final a aj;
    public static final a ak;
    public static final a al;
    public static final a am;
    public static final a an;
    public static final a ao;
    public static final a ap;
    public static final a aq;
    public static final a ar;
    public static final a as;
    public static final a at;
    public static final a au;
    public static final a av;
    public static final a aw;
    public static final a ax;
    public static final a ay;
    public static final a az;
    public static final a aA;
    public static final a aB;
    public static final a aC;
    public static final a aD;
    public static final a aE;
    public static final a aF;
    public static final a aG;
    public static final a aH;
    public static final a aI;
    public static final a aJ;
    public static final a aK;
    public static final a aL;
    public static final a aM;
    public static final a aN;
    public static final a aO;
    public static final a aP;
    public static final a aQ;
    public static final a aR;
    public static final a aS;
    public static final a aT;
    public static final a aU;
    public static final a aV;
    public static final a aW;
    public static final a aX;
    public static final a aY;
    public static final a aZ;
    public static final a ba;
    public static final a bb;
    public static final a bc;
    public static final a bd;
    public static final a be;
    public static final a bf;
    public static final a bg;
    public static final a bh;
    public static final a bi;
    public static final a bj;
    public static final C0018a bk;
    public static final C0018a bl;
    private static final Integer bm = 0;
    private static final Integer bn = 1;
    private static final Integer bo = 2;
    private static final Integer bp = 3;
    private static final Map by = new TreeMap();
    private static final Map bz = new TreeMap();

    /* renamed from: b, reason: collision with root package name */
    public static final a f965b = a("color", bm, "black", bo, new l.ab());
    public static final a c = a("background-color", bm, "transparent", bp, new l.b());
    public static final a d = a("background-image", bm, "none", bp, new l.c());
    public static final a e = a("background-repeat", bm, "repeat", bp, new l.e());
    public static final a f = a("background-attachment", bm, "scroll", bp, new l.a());
    public static final a g = a("background-position", bm, "0% 0%", bp, new l.d());
    public static final a h = a("background-size", bm, "auto auto", bp, new l.f());
    public static final a i = a("border-collapse", bm, "separate", bo, new l.C0021l());
    public static final a j = a("-fs-border-spacing-horizontal", bm, "0", bp, new l.ai());
    public static final a k = a("-fs-border-spacing-vertical", bm, "0", bp, new l.aj());
    public static final a l = a("-fs-dynamic-auto-width", bm, "static", bp, new l.al());
    public static final a m = a("-fs-font-subset", bm, "subset", bp, new l.an());
    public static final a n = a("-fs-checkbox-style", bm, "check", bp, new l.ak());
    public static final a o = a("-fs-keep-with-inline", bm, "auto", bp, new l.ao());
    public static final a p = a("-fs-page-width", bm, "auto", bp, new l.ay());
    public static final a q = a("-fs-page-height", bm, "auto", bp, new l.av());
    public static final a r = a("-fs-page-sequence", bm, "auto", bp, new l.ax());

    static {
        a("-fs-pdf-font-embed", bm, "auto", bp, new l.as());
        s = a("-fs-pdf-font-encoding", bm, "Cp1252", bp, new l.at());
        t = a("-fs-page-orientation", bm, "auto", bp, new l.aw());
        u = a("-fs-table-paginate", bm, "auto", bp, new l.bb());
        v = a("-fs-text-decoration-extent", bm, "line", bp, new l.bc());
        a("-fs-fit-images-to-width", bm, "auto", bp, new l.am());
        a("-fs-named-destination", bm, "none", bp, new l.aq());
        w = a("-fs-page-break-min-height", bm, "0", bo, new l.au());
        x = a("bottom", bm, "auto", bp, new l.w());
        y = a("caption-side", bm, "top", bo, new l.y());
        z = a("clear", bm, "none", bp, new l.z());
        a("clip", bm, "auto", bp, false, null);
        A = a("column-count", bm, "auto", bp, new l.ac());
        B = a("column-gap", bm, "normal", bp, new l.ad());
        C = a("content", bm, "normal", bp, new com.d.c.d.a.e());
        D = a("counter-increment", bm, "none", bp, true, new g.a());
        E = a("counter-reset", bm, "none", bp, true, new g.b());
        a("cursor", bm, "auto", bo, true, new l.ae());
        F = a("direction", bm, "ltr", bo, true, new l.af());
        G = a("display", bm, "inline", bp, new l.ag());
        H = a("empty-cells", bm, "show", bo, new l.ah());
        I = a("float", bm, "none", bp, new l.bd());
        J = a("font-style", bm, "normal", bo, new l.bg());
        K = a("font-variant", bm, "normal", bo, new l.bh());
        L = a("font-weight", bm, "normal", bo, new l.bi());
        M = a("font-size", bm, "medium", bo, new l.bf());
        N = a("line-height", bm, "normal", bo, new l.bz());
        O = a("font-family", bm, "serif", bo, new l.be());
        P = a("-fs-table-cell-colspan", bm, "1", bp, new l.az());
        Q = a("-fs-table-cell-rowspan", bm, "1", bp, new l.ba());
        R = a("height", bm, "auto", bp, new l.bo());
        S = a("left", bm, "auto", bp, new l.bq());
        T = a("letter-spacing", bm, "normal", bo, true, new l.by());
        U = a("list-style-type", bm, "disc", bo, new l.cc());
        V = a("list-style-position", bm, "outside", bo, new l.cb());
        W = a("list-style-image", bm, "none", bo, new l.ca());
        X = a("max-height", bm, "none", bp, new l.ch());
        Y = a("max-width", bm, "none", bp, new l.ci());
        Z = a("min-height", bm, "0", bp, new l.cj());
        aa = a("min-width", bm, "0", bp, new l.ck());
        ab = a("orphans", bm, "2", bo, true, new l.cm());
        a("outline-color", bm, "black", bp, false, null);
        a("outline-style", bm, "none", bp, false, null);
        a("outline-width", bm, "medium", bp, false, null);
        ac = a("overflow", bm, "visible", bp, new l.cn());
        ad = a("page", bm, "auto", bo, new l.cs());
        ae = a("page-break-after", bm, "auto", bp, new l.ct());
        af = a("page-break-before", bm, "auto", bp, new l.cu());
        ag = a("page-break-inside", bm, "auto", bo, new l.cv());
        ah = a("position", bm, "static", bp, new l.cx());
        ai = a("quotes", bm, "none", bo, new n());
        aj = a("right", bm, "auto", bp, new l.cy());
        ak = a("src", bm, "none", bp, new l.da());
        al = a("tab-size", bm, "8", bo, new l.db());
        am = a("table-layout", bm, "auto", bp, new l.dc());
        an = a("text-align", bm, "start", bo, new l.dd());
        ao = a("text-decoration", bm, "none", bp, new l.de());
        ap = a("text-indent", bm, "0", bo, new l.df());
        aq = a("text-transform", bm, "none", bo, new l.dg());
        ar = a("top", bm, "auto", bp, new l.dh());
        a("unicode-bidi", bm, "normal", bp, false, null);
        as = a("vertical-align", bm, "baseline", bp, new l.dm());
        at = a("visibility", bm, "visible", bo, new l.dn());
        au = a("white-space", bm, "normal", bo, new l.Cdo());
        av = a("word-wrap", bm, "normal", bo, new l.ds());
        aw = a("widows", bm, "2", bo, true, new l.dp());
        ax = a("width", bm, "auto", bp, new l.dq());
        ay = a("transform", bm, "none", bp, new l.dl());
        az = a("-fs-transform-origin-x", bm, "50%", bp, new l.dj());
        aA = a("-fs-transform-origin-y", bm, "50%", bp, new l.dk());
        a("word-spacing", bm, "normal", bo, true, new l.dr());
        aB = a("z-index", bm, "auto", bp, new l.dt());
        aC = a("border-top-color", bm, "=color", bp, new l.r());
        aD = a("border-right-color", bm, "=color", bp, new l.m());
        aE = a("border-bottom-color", bm, "=color", bp, new l.g());
        aF = a("border-left-color", bm, "=color", bp, new l.m());
        aG = a("border-top-style", bm, "none", bp, new l.u());
        aH = a("border-right-style", bm, "none", bp, new l.p());
        aI = a("border-bottom-style", bm, "none", bp, new l.j());
        aJ = a("border-left-style", bm, "none", bp, new l.n());
        aK = a("border-top-width", bm, "medium", bp, new l.v());
        aL = a("border-right-width", bm, "medium", bp, new l.q());
        aM = a("border-bottom-width", bm, "medium", bp, new l.k());
        aN = a("border-left-width", bm, "medium", bp, new l.o());
        aO = a("border-top-left-radius", bm, "0 0", bp, true, new l.s());
        aP = a("border-top-right-radius", bm, "0 0", bp, true, new l.t());
        aQ = a("border-bottom-right-radius", bm, "0 0", bp, true, new l.i());
        aR = a("border-bottom-left-radius", bm, "0 0", bp, true, new l.h());
        aS = a("margin-top", bm, "0", bp, new l.cg());
        aT = a("margin-right", bm, "0", bp, new l.cf());
        aU = a("margin-bottom", bm, "0", bp, new l.cd());
        aV = a("margin-left", bm, "0", bp, new l.ce());
        aW = a("padding-top", bm, "0", bp, new l.cr());
        aX = a("padding-right", bm, "0", bp, new l.cq());
        aY = a("padding-bottom", bm, "0", bp, new l.co());
        aZ = a("padding-left", bm, "0", bp, new l.cp());
        ba = a("image-rendering", bm, "auto", bo, new l.bp());
        bb = a("box-sizing", bm, "content-box", bp, new l.x());
        bc = a("-fs-max-overflow-pages", bm, "0", bp, new l.ap());
        bd = a("-fs-overflow-pages-direction", bm, "ltr", bp, new l.ar());
        be = a("background", bn, "transparent none repeat scroll 0% 0%", bp, new com.d.c.d.a.b());
        a("border-radius", bn, "0px", bp, true, new j.b());
        a("border-width", bn, "medium", bp, new j.d());
        a("border-style", bn, "none", bp, new j.c());
        a("border", bn, "medium none black", bp, new c.a());
        a("border-top", bn, "medium none black", bp, new c.f());
        a("border-right", bn, "medium none black", bp, new c.d());
        a("border-bottom", bn, "medium none black", bp, new c.b());
        a("border-left", bn, "medium none black", bp, new c.C0020c());
        a("border-color", bn, "black", bp, new j.a());
        bf = a("border-spacing", bn, "0", bo, new com.d.c.d.a.d());
        bg = a("font", bn, "", bo, new h());
        bh = a("list-style", bn, "disc outside none", bo, new i());
        bi = a("margin", bn, "0", bp, new j.e());
        a("outline", bn, "invert none medium", bp, false, null);
        bj = a("padding", bn, "0", bp, new j.g());
        a("size", bn, "auto", bp, new o());
        a("transform-origin", bn, "50% 50%", bp, new l.di());
        bk = new C0018a(aS, aT, aU, aV);
        bl = new C0018a(aW, aX, aY, aZ);
        new C0018a(aK, aL, aM, aN);
        new C0018a(aG, aH, aI, aJ);
        new C0018a(aC, aD, aE, aF);
        bx = new a[by.size()];
        for (a aVar : by.values()) {
            bx[aVar.f964a] = aVar;
        }
        com.d.c.d.c cVar = new com.d.c.d.c(new b());
        for (a aVar2 : bz.values()) {
            if (aVar2.bs.charAt(0) != '=' && aVar2.bv) {
                com.d.c.d.j a2 = cVar.a(aVar2, 0, aVar2.bs);
                if (a2 == null) {
                    com.d.m.l.c("Unable to derive initial value for " + aVar2);
                } else {
                    aVar2.bu = com.a.a.b.c.a.a((com.d.c.f.c) null, aVar2, a2);
                }
            }
        }
    }

    private a(String str, String str2, boolean z2, boolean z3, m mVar) {
        this.br = str;
        int i2 = bq;
        bq = i2 + 1;
        this.f964a = i2;
        this.bs = str2;
        this.bt = z2;
        this.bv = z3;
        this.bw = mVar;
    }

    public final String toString() {
        return this.br;
    }

    public static int a() {
        return bz.size();
    }

    public static boolean a(a aVar) {
        return aVar.bt;
    }

    public static String b(a aVar) {
        return aVar.bs;
    }

    public static com.d.c.f.g c(a aVar) {
        return aVar.bu;
    }

    public static boolean d(a aVar) {
        return aVar.bv;
    }

    public static m e(a aVar) {
        return aVar.bw;
    }

    public static a a(String str) {
        return (a) by.get(str);
    }

    public static a a(int i2) {
        return bx[i2];
    }

    private static synchronized a a(String str, Object obj, String str2, Object obj2, m mVar) {
        return a(str, obj, str2, obj2, true, mVar);
    }

    private static synchronized a a(String str, Object obj, String str2, Object obj2, boolean z2, m mVar) {
        a aVar = new a(str, str2, obj2 == bo, z2, mVar);
        by.put(str, aVar);
        if (obj == bm) {
            bz.put(str, aVar);
        }
        return aVar;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Object obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return this.f964a - ((a) obj).f964a;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof a) && this.f964a == ((a) obj).f964a;
    }

    public final int hashCode() {
        return this.f964a;
    }

    /* renamed from: com.d.c.a.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:com/d/c/a/a$a.class */
    public static class C0018a {

        /* renamed from: a, reason: collision with root package name */
        public final a f966a;

        /* renamed from: b, reason: collision with root package name */
        public final a f967b;
        public final a c;
        public final a d;

        public C0018a(a aVar, a aVar2, a aVar3, a aVar4) {
            this.f966a = aVar;
            this.f967b = aVar2;
            this.c = aVar3;
            this.d = aVar4;
        }
    }
}
