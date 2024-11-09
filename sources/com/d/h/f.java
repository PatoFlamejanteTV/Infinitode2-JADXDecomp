package com.d.h;

import com.d.e.aa;
import com.d.h.x;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import org.a.b.f.ao;
import org.a.b.f.ap;
import org.a.c.h.e.ac;
import org.a.c.h.e.ae;

/* loaded from: infinitode-2.jar:com/d/h/f.class */
public class f implements com.d.d.k {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, com.d.g.a.d<b>> f1222a;
    private final org.a.c.h.b c;
    private final aa d;
    private final com.d.d.a<String, com.d.d.b> f;
    private final x.c g;
    private final boolean h;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, b> f1223b = new HashMap();
    private final List<ao> e = new ArrayList();

    static {
        f.class.desiredAssertionStatus();
    }

    public f(aa aaVar, org.a.c.h.b bVar, com.d.d.a<String, com.d.d.b> aVar, x.c cVar, boolean z) {
        this.d = aaVar;
        this.c = bVar;
        this.f = aVar;
        this.g = cVar;
        this.h = z;
        this.f1222a = (this.g != x.c.NONE || z) ? new HashMap<>() : c();
    }

    @Override // com.d.d.k
    public final com.d.i.k a(aa aaVar, com.d.c.g.a aVar) {
        return a(aaVar, aVar.c, aVar.f1094a, aVar.f1095b, aVar.d, aVar.e);
    }

    public final void a() {
        this.f1223b.clear();
        Iterator<ao> it = this.e.iterator();
        while (it.hasNext()) {
            try {
                it.next().close();
            } catch (IOException unused) {
            }
        }
        this.e.clear();
    }

    @Deprecated
    public final void b() {
        this.f1223b = new HashMap();
        Iterator<com.d.g.a.d<b>> it = this.f1222a.values().iterator();
        while (it.hasNext()) {
            com.d.g.a.d<b> next = it.next();
            Iterator<b> it2 = next.a().iterator();
            while (it2.hasNext()) {
                if (it2.next().d()) {
                    it2.remove();
                }
            }
            if (next.a().size() == 0) {
                it.remove();
            }
        }
    }

    public final void a(List<com.d.c.e.a> list) {
        for (com.d.c.e.a aVar : list) {
            com.d.c.f.c b2 = aVar.b();
            com.d.c.f.g i = b2.i(com.d.c.a.a.ak);
            if (i != com.d.c.a.c.ap) {
                boolean a2 = b2.a(com.d.c.a.a.m, com.d.c.a.c.bQ);
                com.d.c.a.c cVar = null;
                com.d.c.a.c cVar2 = null;
                if (aVar.c()) {
                    String d = b2.i(com.d.c.a.a.O).d();
                    if (aVar.d()) {
                        cVar = b2.e(com.d.c.a.a.L);
                    }
                    if (aVar.e()) {
                        cVar2 = b2.e(com.d.c.a.a.J);
                    }
                    a(d, cVar, cVar2, i.d(), !a2);
                } else {
                    com.d.m.l.a(Level.WARNING, "Must provide at least a font-family and src in @font-face rule");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ap apVar, String str, Integer num, com.d.c.a.c cVar, boolean z) {
        b(new c(ac.a(this.c, apVar, z)), str, num, cVar, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/f$c.class */
    public static class c implements com.d.d.f<org.a.c.h.e.u> {

        /* renamed from: a, reason: collision with root package name */
        private final org.a.c.h.e.u f1228a;

        c(org.a.c.h.e.u uVar) {
            this.f1228a = uVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.d.d.f
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public org.a.c.h.e.u a() {
            return this.f1228a;
        }
    }

    private void b(com.d.d.f<org.a.c.h.e.u> fVar, String str, Integer num, com.d.c.a.c cVar, boolean z) {
        com.d.g.a.d<b> a2 = a(str);
        b bVar = new b(this.c, (com.d.d.f) fVar, b(cVar), a(num), str, false, z, (com.d.d.a) this.f, (byte) 0);
        if (z || bVar.g()) {
            a2.a(bVar);
        }
    }

    private void a(ao aoVar, String str, Integer num, com.d.c.a.c cVar, boolean z) {
        aoVar.a(new g(this, str, num, cVar, z));
        this.e.add(aoVar);
    }

    private void b(File file, String str, Integer num, com.d.c.a.c cVar, boolean z) {
        a(new ao(file), str, num, cVar, z);
    }

    public final void a(File file, String str, Integer num, com.d.c.a.c cVar, boolean z) {
        if (file.getName().toLowerCase(Locale.US).endsWith(".ttc")) {
            b(file, str, num, cVar, z);
        } else {
            b(new a(file, this.c), str, num, cVar, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/d/h/f$a.class */
    public static class a implements com.d.d.f<org.a.c.h.e.u> {

        /* renamed from: a, reason: collision with root package name */
        private final File f1224a;

        /* renamed from: b, reason: collision with root package name */
        private final org.a.c.h.b f1225b;

        a(File file, org.a.c.h.b bVar) {
            this.f1224a = file;
            this.f1225b = bVar;
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // com.d.d.f
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public org.a.c.h.e.u a() {
            try {
                return ac.a(this.f1225b, this.f1224a);
            } catch (IOException e) {
                com.d.m.l.a("Couldn't load font (" + this.f1224a.getAbsolutePath() + "). Please check that it is a valid truetype font.", e);
                return null;
            }
        }
    }

    public final void a(com.d.d.f<InputStream> fVar, String str, Integer num, com.d.c.a.c cVar, boolean z) {
        com.d.g.a.d<b> a2 = a(str);
        b bVar = new b(this.c, (com.d.d.f) fVar, a(num), b(cVar), str, false, z, (com.d.d.a) this.f, (byte) 0);
        if (z || bVar.g()) {
            a2.a(bVar);
        }
    }

    private static int a(com.d.c.a.c cVar) {
        if (cVar != null) {
            return com.a.a.b.c.a.a(cVar);
        }
        return 400;
    }

    private static int a(Integer num) {
        if (num != null) {
            return num.intValue();
        }
        return 400;
    }

    private static com.d.c.a.c b(com.d.c.a.c cVar) {
        return cVar != null ? cVar : com.d.c.a.c.aq;
    }

    private void a(String str, com.d.c.a.c cVar, com.d.c.a.c cVar2, String str2, boolean z) {
        com.d.g.a.c cVar3 = new com.d.g.a.c(this.d, str2);
        com.d.g.a.d<b> a2 = a(str);
        b bVar = new b(this.c, (com.d.d.f) cVar3, a(cVar), b(cVar2), str, true, z, (com.d.d.a) this.f, (byte) 0);
        if (z || bVar.g()) {
            a2.a(bVar);
        }
    }

    private com.d.g.a.d<b> a(String str) {
        com.d.g.a.d<b> dVar = this.f1222a.get(str);
        com.d.g.a.d<b> dVar2 = dVar;
        if (dVar == null) {
            dVar2 = new com.d.g.a.d<>();
            this.f1222a.put(str, dVar2);
        }
        return dVar2;
    }

    private com.d.i.k a(aa aaVar, String[] strArr, float f, com.d.c.a.c cVar, com.d.c.a.c cVar2, com.d.c.a.c cVar3) {
        if (cVar2 != com.d.c.a.c.aq && cVar2 != com.d.c.a.c.at && cVar2 != com.d.c.a.c.W) {
            cVar2 = com.d.c.a.c.aq;
        }
        ArrayList arrayList = new ArrayList(3);
        if (strArr != null) {
            for (String str : strArr) {
                b a2 = a(str, cVar, cVar2);
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
        }
        if (this.g == x.c.NONE && !this.h) {
            arrayList.add(a("Serif", cVar, cVar2));
        }
        return new com.d.h.b(arrayList, f);
    }

    private static String b(String str) {
        String str2 = str;
        if (str.startsWith("\"")) {
            str2 = str2.substring(1);
        }
        if (str2.endsWith("\"")) {
            str2 = str2.substring(0, str2.length() - 1);
        }
        if (str2.equalsIgnoreCase("serif")) {
            str2 = "Serif";
        } else if (str2.equalsIgnoreCase("sans-serif")) {
            str2 = "SansSerif";
        } else if (str2.equalsIgnoreCase("monospace")) {
            str2 = "Monospaced";
        }
        return str2;
    }

    private b a(String str, com.d.c.a.c cVar, com.d.c.a.c cVar2) {
        b a2;
        String b2 = b(str);
        String b3 = b(b2, cVar, cVar2);
        b bVar = this.f1223b.get(b3);
        if (bVar == null) {
            com.d.g.a.d<b> dVar = this.f1222a.get(b2);
            if (dVar != null && (a2 = dVar.a(com.a.a.b.c.a.a(cVar), cVar2)) != null) {
                this.f1223b.put(b3, a2);
                return a2;
            }
            return null;
        }
        return bVar;
    }

    private static String b(String str, com.d.c.a.c cVar, com.d.c.a.c cVar2) {
        return str + "-" + cVar + "-" + cVar2;
    }

    private static Map<String, com.d.g.a.d<b>> c() {
        HashMap hashMap = new HashMap();
        try {
            a((HashMap<String, com.d.g.a.d<b>>) hashMap);
            b((HashMap<String, com.d.g.a.d<b>>) hashMap);
            c(hashMap);
            a((Map<String, com.d.g.a.d<b>>) hashMap);
            b((Map<String, com.d.g.a.d<b>>) hashMap);
            return hashMap;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static org.a.c.h.e.u a(org.a.c.h.e.u uVar) {
        return uVar;
    }

    private static void a(HashMap<String, com.d.g.a.d<b>> hashMap) {
        com.d.g.a.d<b> dVar = new com.d.g.a.d<>();
        dVar.a(new b(a(ae.p), com.d.c.a.c.at, 700, (byte) 0));
        dVar.a(new b(a(ae.o), com.d.c.a.c.at, 400, (byte) 0));
        dVar.a(new b(a(ae.n), com.d.c.a.c.aq, 700, (byte) 0));
        dVar.a(new b(a(ae.m), com.d.c.a.c.aq, 400, (byte) 0));
        hashMap.put("DialogInput", dVar);
        hashMap.put("Monospaced", dVar);
        hashMap.put("Courier", dVar);
    }

    private static void b(HashMap<String, com.d.g.a.d<b>> hashMap) {
        com.d.g.a.d<b> dVar = new com.d.g.a.d<>();
        dVar.a(new b(a(ae.h), com.d.c.a.c.W, 700, (byte) 0));
        dVar.a(new b(a(ae.g), com.d.c.a.c.W, 400, (byte) 0));
        dVar.a(new b(a(ae.f), com.d.c.a.c.aq, 700, (byte) 0));
        dVar.a(new b(a(ae.e), com.d.c.a.c.aq, 400, (byte) 0));
        hashMap.put("Serif", dVar);
        hashMap.put("TimesRoman", dVar);
    }

    private static void c(HashMap<String, com.d.g.a.d<b>> hashMap) {
        com.d.g.a.d<b> dVar = new com.d.g.a.d<>();
        dVar.a(new b(a(ae.l), com.d.c.a.c.at, 700, (byte) 0));
        dVar.a(new b(a(ae.k), com.d.c.a.c.at, 400, (byte) 0));
        dVar.a(new b(a(ae.j), com.d.c.a.c.aq, 700, (byte) 0));
        dVar.a(new b(a(ae.i), com.d.c.a.c.aq, 400, (byte) 0));
        hashMap.put("Dialog", dVar);
        hashMap.put("SansSerif", dVar);
        hashMap.put("Helvetica", dVar);
    }

    private static void a(Map<String, com.d.g.a.d<b>> map) {
        com.d.g.a.d<b> dVar = new com.d.g.a.d<>();
        dVar.a(new b(a(ae.q), com.d.c.a.c.aq, 400, (byte) 0));
        map.put("Symbol", dVar);
    }

    private static void b(Map<String, com.d.g.a.d<b>> map) {
        com.d.g.a.d<b> dVar = new com.d.g.a.d<>();
        dVar.a(new b(a(ae.r), com.d.c.a.c.aq, 400, (byte) 0));
        map.put("ZapfDingbats", dVar);
    }

    /* loaded from: infinitode-2.jar:com/d/h/f$b.class */
    public static class b implements com.d.g.a.f {

        /* renamed from: a, reason: collision with root package name */
        private final com.d.c.a.c f1226a;

        /* renamed from: b, reason: collision with root package name */
        private final int f1227b;
        private final String c;
        private final org.a.c.h.b d;
        private com.d.d.f<InputStream> e;
        private com.d.d.f<org.a.c.h.e.u> f;
        private org.a.c.h.e.u g;
        private final boolean h;
        private final boolean i;
        private p j;
        private final com.d.d.a<String, com.d.d.b> k;

        /* synthetic */ b(org.a.c.h.b bVar, com.d.d.f fVar, com.d.c.a.c cVar, int i, String str, boolean z, boolean z2, com.d.d.a aVar, byte b2) {
            this(bVar, (com.d.d.f<org.a.c.h.e.u>) fVar, cVar, i, str, false, z2, (com.d.d.a<String, com.d.d.b>) aVar);
        }

        /* synthetic */ b(org.a.c.h.b bVar, com.d.d.f fVar, int i, com.d.c.a.c cVar, String str, boolean z, boolean z2, com.d.d.a aVar, byte b2) {
            this(bVar, (com.d.d.f<InputStream>) fVar, i, cVar, str, z, z2, (com.d.d.a<String, com.d.d.b>) aVar);
        }

        /* synthetic */ b(org.a.c.h.e.u uVar, com.d.c.a.c cVar, int i, byte b2) {
            this(uVar, cVar, i);
        }

        private b(org.a.c.h.e.u uVar, com.d.c.a.c cVar, int i) {
            this((org.a.c.h.b) null, uVar, cVar, i);
        }

        private b(org.a.c.h.b bVar, com.d.d.f<InputStream> fVar, int i, com.d.c.a.c cVar, String str, boolean z, boolean z2, com.d.d.a<String, com.d.d.b> aVar) {
            this.e = fVar;
            this.f1227b = i;
            this.f1226a = cVar;
            this.d = bVar;
            this.c = str;
            this.h = z;
            this.i = z2;
            this.k = aVar;
            this.j = b(str, i, cVar);
        }

        private b(org.a.c.h.b bVar, org.a.c.h.e.u uVar, com.d.c.a.c cVar, int i) {
            this.g = uVar;
            this.f1226a = cVar;
            this.f1227b = i;
            this.e = null;
            this.d = bVar;
            this.k = null;
            this.c = null;
            this.h = false;
            this.i = false;
            try {
                this.j = p.a(uVar, uVar.b());
            } catch (IOException e) {
                com.d.m.l.a("Couldn't load font metrics.", e);
            }
        }

        private b(org.a.c.h.b bVar, com.d.d.f<org.a.c.h.e.u> fVar, com.d.c.a.c cVar, int i, String str, boolean z, boolean z2, com.d.d.a<String, com.d.d.b> aVar) {
            this.f = fVar;
            this.f1226a = cVar;
            this.f1227b = i;
            this.e = null;
            this.d = bVar;
            this.c = str;
            this.h = z;
            this.i = z2;
            this.k = aVar;
            this.j = b(str, i, cVar);
        }

        private static String a(String str, int i, com.d.c.a.c cVar) {
            return "font-metrics:" + str + ":" + i + ":" + cVar.toString();
        }

        private p b(String str, int i, com.d.c.a.c cVar) {
            return (p) this.k.a(a(str, i, cVar));
        }

        private void a(String str, int i, com.d.c.a.c cVar, p pVar) {
            a(str, i, cVar);
        }

        private boolean f() {
            try {
                this.j = p.a(this.g, this.g.b());
                a(this.c, this.f1227b, this.f1226a, this.j);
                return true;
            } catch (IOException unused) {
                com.d.m.l.c("Couldn't load font. Please check that it is a valid truetype font.");
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean g() {
            if (this.g == null && this.f != null) {
                com.d.m.l.e(Level.INFO, "Loading font(" + this.c + ") from PDFont supplier now.");
                this.g = this.f.a();
                this.f = null;
                if (!h()) {
                    return f();
                }
            }
            if (this.g == null && this.e != null) {
                com.d.m.l.e(Level.INFO, "Loading font(" + this.c + ") from InputStream supplier now.");
                InputStream a2 = this.e.a();
                this.e = null;
                try {
                    if (a2 == null) {
                        return false;
                    }
                    try {
                        this.g = ac.a(this.d, a2, this.i);
                        if (!h()) {
                            return f();
                        }
                        try {
                            a2.close();
                        } catch (IOException unused) {
                        }
                    } catch (IOException unused2) {
                        com.d.m.l.c("Couldn't load font. Please check that it is a valid truetype font.");
                        try {
                            a2.close();
                            return false;
                        } catch (IOException unused3) {
                            return false;
                        }
                    }
                } finally {
                    try {
                        a2.close();
                    } catch (IOException unused4) {
                    }
                }
            }
            return this.g != null;
        }

        public final org.a.c.h.e.u c() {
            g();
            return this.g;
        }

        @Override // com.d.g.a.f
        public final int a() {
            return this.f1227b;
        }

        @Override // com.d.g.a.f
        public final com.d.c.a.c b() {
            return this.f1226a;
        }

        public final boolean d() {
            return this.h;
        }

        private boolean h() {
            return this.j != null;
        }

        public final p e() {
            if (!h()) {
                g();
            }
            return this.j;
        }
    }
}
