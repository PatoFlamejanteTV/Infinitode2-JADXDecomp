package org.a.c.b;

import com.a.a.a.am;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: infinitode-2.jar:org/a/c/b/d.class */
public class d extends b implements t {

    /* renamed from: a, reason: collision with root package name */
    protected Map<j, b> f4359a = new LinkedHashMap();

    public final boolean a(Object obj) {
        boolean containsValue = this.f4359a.containsValue(obj);
        boolean z = containsValue;
        if (!containsValue && (obj instanceof m)) {
            z = this.f4359a.containsValue(((m) obj).a());
        }
        return z;
    }

    public final j b(Object obj) {
        for (Map.Entry<j, b> entry : this.f4359a.entrySet()) {
            b value = entry.getValue();
            if (value.equals(obj) || ((value instanceof m) && ((m) value).a().equals(obj))) {
                return entry.getKey();
            }
        }
        return null;
    }

    public final int a() {
        return this.f4359a.size();
    }

    public void b() {
        this.f4359a.clear();
    }

    public final b a(j jVar, j jVar2) {
        b a2 = a(jVar);
        b bVar = a2;
        if (a2 == null && jVar2 != null) {
            bVar = a(jVar2);
        }
        return bVar;
    }

    public final b a(j jVar) {
        b bVar = this.f4359a.get(jVar);
        b bVar2 = bVar;
        if (bVar instanceof m) {
            bVar2 = ((m) bVar2).a();
        }
        if (bVar2 instanceof k) {
            bVar2 = null;
        }
        return bVar2;
    }

    public void a(j jVar, b bVar) {
        if (bVar == null) {
            m(jVar);
        } else {
            this.f4359a.put(jVar, bVar);
        }
    }

    public void a(j jVar, org.a.c.h.a.c cVar) {
        b bVar = null;
        if (cVar != null) {
            bVar = cVar.f();
        }
        a(jVar, bVar);
    }

    public void a(String str, boolean z) {
        a(j.a(str), (b) c.b(z));
    }

    public void a(j jVar, boolean z) {
        a(jVar, (b) c.b(z));
    }

    public void a(j jVar, String str) {
        j jVar2 = null;
        if (str != null) {
            jVar2 = j.a(str);
        }
        a(jVar, (b) jVar2);
    }

    public void a(j jVar, Calendar calendar) {
        b(jVar, org.a.c.i.b.a(calendar));
    }

    public void a(String str, String str2) {
        b(j.a(str), str2);
    }

    public void b(j jVar, String str) {
        s sVar = null;
        if (str != null) {
            sVar = new s(str);
        }
        a(jVar, (b) sVar);
    }

    public void a(j jVar, int i) {
        a(jVar, (b) i.a(i));
    }

    public void a(j jVar, long j) {
        a(jVar, (b) i.a(j));
    }

    public void a(j jVar, float f) {
        a(jVar, (b) new f(f));
    }

    public final void a(j jVar, int i, boolean z) {
        int i2;
        int b2 = b(jVar, 0);
        if (z) {
            i2 = b2 | i;
        } else {
            i2 = b2 & (i ^ (-1));
        }
        a(jVar, i2);
    }

    public final j b(j jVar) {
        b a2 = a(jVar);
        if (a2 instanceof j) {
            return (j) a2;
        }
        return null;
    }

    public final m c(j jVar) {
        b n = n(jVar);
        if (n instanceof m) {
            return (m) n;
        }
        return null;
    }

    public final d d(j jVar) {
        b a2 = a(jVar);
        if (a2 instanceof d) {
            return (d) a2;
        }
        return null;
    }

    public final p e(j jVar) {
        b a2 = a(jVar);
        if (a2 instanceof p) {
            return (p) a2;
        }
        return null;
    }

    public final a f(j jVar) {
        b a2 = a(jVar);
        if (a2 instanceof a) {
            return (a) a2;
        }
        return null;
    }

    public final j b(j jVar, j jVar2) {
        b a2 = a(jVar);
        if (a2 instanceof j) {
            return (j) a2;
        }
        return jVar2;
    }

    public final String g(j jVar) {
        String str = null;
        b a2 = a(jVar);
        if (a2 instanceof j) {
            str = ((j) a2).a();
        } else if (a2 instanceof s) {
            str = ((s) a2).b();
        }
        return str;
    }

    public final String h(j jVar) {
        String str = null;
        b a2 = a(jVar);
        if (a2 instanceof s) {
            str = ((s) a2).b();
        }
        return str;
    }

    public final Calendar i(j jVar) {
        b a2 = a(jVar);
        if (a2 instanceof s) {
            return org.a.c.i.b.a((s) a2);
        }
        return null;
    }

    public final boolean b(j jVar, boolean z) {
        return a(jVar, (j) null, false);
    }

    private boolean a(j jVar, j jVar2, boolean z) {
        boolean z2 = z;
        b a2 = a(jVar, jVar2);
        if (a2 instanceof c) {
            z2 = ((c) a2).a();
        }
        return z2;
    }

    public final int a(String str) {
        return b(j.a(str), -1);
    }

    public final int j(j jVar) {
        return b(jVar, -1);
    }

    public final int b(j jVar, int i) {
        return a(jVar, (j) null, i);
    }

    public final int c(j jVar, j jVar2) {
        return a(jVar, jVar2, -1);
    }

    public final int a(j jVar, j jVar2, int i) {
        int i2 = i;
        b a2 = a(jVar, jVar2);
        if (a2 instanceof l) {
            i2 = ((l) a2).c();
        }
        return i2;
    }

    public final long k(j jVar) {
        return b(jVar, -1L);
    }

    public final long b(j jVar, long j) {
        long j2 = -1;
        b a2 = a(jVar);
        if (a2 instanceof l) {
            j2 = ((l) a2).b();
        }
        return j2;
    }

    public final float l(j jVar) {
        return b(jVar, -1.0f);
    }

    public final float b(j jVar, float f) {
        float f2 = f;
        b a2 = a(jVar);
        if (a2 instanceof l) {
            f2 = ((l) a2).a();
        }
        return f2;
    }

    public final boolean c(j jVar, int i) {
        return (b(jVar, 0) & i) == i;
    }

    public void m(j jVar) {
        this.f4359a.remove(jVar);
    }

    public final b n(j jVar) {
        return this.f4359a.get(jVar);
    }

    public final b d(j jVar, j jVar2) {
        b n = n(jVar);
        b bVar = n;
        if (n == null && jVar2 != null) {
            bVar = n(jVar2);
        }
        return bVar;
    }

    public final Set<j> d() {
        return this.f4359a.keySet();
    }

    public final Set<Map.Entry<j, b>> e() {
        return this.f4359a.entrySet();
    }

    public final Collection<b> h() {
        return this.f4359a.values();
    }

    @Override // org.a.c.b.b
    public Object a(u uVar) {
        return uVar.a(this);
    }

    @Override // org.a.c.b.t
    public final boolean c() {
        return false;
    }

    public void a(d dVar) {
        for (Map.Entry<j, b> entry : dVar.e()) {
            if (!entry.getKey().a().equals("Size") || !this.f4359a.containsKey(j.a("Size"))) {
                a(entry.getKey(), entry.getValue());
            }
        }
    }

    public final boolean o(j jVar) {
        return this.f4359a.containsKey(jVar);
    }

    public final boolean b(String str) {
        return o(j.a(str));
    }

    public final d i() {
        return new w(this);
    }

    public String toString() {
        try {
            return a(this, new ArrayList());
        } catch (IOException e) {
            return "COSDictionary{" + e.getMessage() + "}";
        }
    }

    private static String a(b bVar, List<b> list) {
        if (bVar == null) {
            return "null";
        }
        if (list.contains(bVar)) {
            return String.valueOf(bVar.hashCode());
        }
        list.add(bVar);
        if (bVar instanceof d) {
            StringBuilder sb = new StringBuilder();
            sb.append("COSDictionary{");
            for (Map.Entry<j, b> entry : ((d) bVar).e()) {
                sb.append(entry.getKey());
                sb.append(":");
                sb.append(a(entry.getValue(), list));
                sb.append(";");
            }
            sb.append("}");
            if (bVar instanceof p) {
                InputStream j = ((p) bVar).j();
                sb.append("COSStream{").append(Arrays.hashCode(am.a(j))).append("}");
                j.close();
            }
            return sb.toString();
        }
        if (bVar instanceof a) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("COSArray{");
            Iterator<? extends b> it = ((a) bVar).e().iterator();
            while (it.hasNext()) {
                sb2.append(a(it.next(), list));
                sb2.append(";");
            }
            sb2.append("}");
            return sb2.toString();
        }
        if (bVar instanceof m) {
            return "COSObject{" + a(((m) bVar).a(), list) + "}";
        }
        return bVar.toString();
    }
}
