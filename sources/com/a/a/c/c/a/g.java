package com.a.a.c.c.a;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/g.class */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    private final com.a.a.c.j f262a;

    /* renamed from: b, reason: collision with root package name */
    private final b[] f263b;
    private final Map<String, Object> c;
    private final String[] d;
    private final com.a.a.c.m.ac[] e;

    protected g(com.a.a.c.j jVar, b[] bVarArr, Map<String, Object> map, String[] strArr, com.a.a.c.m.ac[] acVarArr) {
        this.f262a = jVar;
        this.f263b = bVarArr;
        this.c = map;
        this.d = strArr;
        this.e = acVarArr;
    }

    private g(g gVar) {
        this.f262a = gVar.f262a;
        this.f263b = gVar.f263b;
        this.c = gVar.c;
        int length = this.f263b.length;
        this.d = new String[length];
        this.e = new com.a.a.c.m.ac[length];
    }

    public static a a(com.a.a.c.j jVar) {
        return new a(jVar);
    }

    public final g a() {
        return new g(this);
    }

    public final boolean a(com.a.a.b.l lVar, com.a.a.c.g gVar, String str, Object obj) {
        Object obj2 = this.c.get(str);
        if (obj2 == null) {
            return false;
        }
        String w = lVar.w();
        if (obj2 instanceof List) {
            boolean z = false;
            Iterator it = ((List) obj2).iterator();
            while (it.hasNext()) {
                if (a(lVar, gVar, str, obj, w, ((Integer) it.next()).intValue())) {
                    z = true;
                }
            }
            return z;
        }
        return a(lVar, gVar, str, obj, w, ((Integer) obj2).intValue());
    }

    private final boolean a(com.a.a.b.l lVar, com.a.a.c.g gVar, String str, Object obj, String str2, int i) {
        if (!this.f263b[i].a(str)) {
            return false;
        }
        if ((obj == null || this.e[i] == null) ? false : true) {
            a(lVar, gVar, obj, i, str2);
            this.e[i] = null;
            return true;
        }
        this.d[i] = str2;
        return true;
    }

    public final boolean b(com.a.a.b.l lVar, com.a.a.c.g gVar, String str, Object obj) {
        boolean z;
        Object obj2 = this.c.get(str);
        if (obj2 == null) {
            return false;
        }
        if (obj2 instanceof List) {
            Iterator it = ((List) obj2).iterator();
            Integer num = (Integer) it.next();
            if (this.f263b[num.intValue()].a(str)) {
                String w = lVar.w();
                lVar.j();
                this.d[num.intValue()] = w;
                while (it.hasNext()) {
                    this.d[((Integer) it.next()).intValue()] = w;
                }
                return true;
            }
            com.a.a.c.m.ac b2 = gVar.b(lVar);
            this.e[num.intValue()] = b2;
            while (it.hasNext()) {
                this.e[((Integer) it.next()).intValue()] = b2;
            }
            return true;
        }
        int intValue = ((Integer) obj2).intValue();
        if (this.f263b[intValue].a(str)) {
            this.d[intValue] = lVar.R();
            lVar.j();
            z = (obj == null || this.e[intValue] == null) ? false : true;
        } else {
            this.e[intValue] = gVar.b(lVar);
            z = (obj == null || this.d[intValue] == null) ? false : true;
        }
        if (z) {
            String str2 = this.d[intValue];
            this.d[intValue] = null;
            a(lVar, gVar, obj, intValue, str2);
            this.e[intValue] = null;
            return true;
        }
        return true;
    }

    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        int length = this.f263b.length;
        for (int i = 0; i < length; i++) {
            String str = this.d[i];
            b bVar = this.f263b[i];
            if (str == null) {
                com.a.a.c.m.ac acVar = this.e[i];
                if (acVar != null) {
                    if (acVar.q().g()) {
                        com.a.a.b.l c = acVar.c(lVar);
                        c.g();
                        com.a.a.c.c.v d = bVar.d();
                        Object a2 = com.a.a.c.i.e.a(c, gVar, d.c());
                        if (a2 != null) {
                            d.a(obj, a2);
                        }
                    }
                    if (!bVar.a()) {
                        gVar.a(this.f262a, bVar.d().a(), "Missing external type id property '%s' (and no 'defaultImpl' specified)", bVar.c());
                    } else {
                        String b2 = bVar.b();
                        str = b2;
                        if (b2 == null) {
                            gVar.a(this.f262a, bVar.d().a(), "Invalid default type id for property '%s': `null` returned by TypeIdResolver", bVar.c());
                        }
                    }
                }
            } else if (this.e[i] == null) {
                com.a.a.c.c.v d2 = bVar.d();
                if (d2.t() || gVar.a(com.a.a.c.i.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    gVar.c(obj.getClass(), d2.a(), "Missing property '%s' for external type id '%s'", d2.a(), bVar.c());
                }
                return obj;
            }
            a(lVar, gVar, obj, i, str);
        }
        return obj;
    }

    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, y yVar, v vVar) {
        Object a2;
        int length = this.f263b.length;
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            String str = this.d[i];
            b bVar = this.f263b[i];
            if (str == null) {
                com.a.a.c.m.ac acVar = this.e[i];
                if (acVar != null && acVar.q() != com.a.a.b.o.VALUE_NULL) {
                    if (!bVar.a()) {
                        gVar.a(this.f262a, bVar.d().a(), "Missing external type id property '%s'", bVar.c());
                    } else {
                        str = bVar.b();
                    }
                }
            }
            if (this.e[i] != null) {
                objArr[i] = a(lVar, gVar, i, str);
            } else {
                if (gVar.a(com.a.a.c.i.FAIL_ON_MISSING_EXTERNAL_TYPE_ID_PROPERTY)) {
                    com.a.a.c.c.v d = bVar.d();
                    gVar.a(this.f262a, d.a(), "Missing property '%s' for external type id '%s'", d.a(), this.f263b[i].c());
                }
                objArr[i] = b(lVar, gVar, i, str);
            }
            com.a.a.c.c.v d2 = bVar.d();
            if (d2.h() >= 0) {
                yVar.a(d2, objArr[i]);
                com.a.a.c.c.v e = bVar.e();
                if (e != null && e.h() >= 0) {
                    if (e.c().a(String.class)) {
                        a2 = str;
                    } else {
                        com.a.a.c.m.ac a3 = gVar.a(lVar);
                        a3.b(str);
                        a2 = e.p().a(a3.p(), gVar);
                        a3.close();
                    }
                    yVar.a(e, a2);
                }
            }
        }
        Object a4 = vVar.a(gVar, yVar);
        for (int i2 = 0; i2 < length; i2++) {
            com.a.a.c.c.v d3 = this.f263b[i2].d();
            if (d3.h() < 0) {
                d3.a(a4, objArr[i2]);
            }
        }
        return a4;
    }

    private Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, int i, String str) {
        com.a.a.b.l c = this.e[i].c(lVar);
        if (c.g() == com.a.a.b.o.VALUE_NULL) {
            return null;
        }
        com.a.a.c.m.ac a2 = gVar.a(lVar);
        a2.g();
        a2.b(str);
        a2.b(c);
        a2.h();
        com.a.a.b.l c2 = a2.c(lVar);
        c2.g();
        return this.f263b[i].d().a(c2, gVar);
    }

    private Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, int i, String str) {
        com.a.a.c.m.ac a2 = gVar.a(lVar);
        a2.g();
        a2.b(str);
        a2.h();
        com.a.a.b.l c = a2.c(lVar);
        c.g();
        return this.f263b[i].d().a(c, gVar);
    }

    private void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj, int i, String str) {
        if (str == null) {
            gVar.a(this.f262a, "Internal error in external Type Id handling: `null` type id passed", new Object[0]);
        }
        com.a.a.b.l c = this.e[i].c(lVar);
        if (c.g() == com.a.a.b.o.VALUE_NULL) {
            this.f263b[i].d().a(obj, (Object) null);
            return;
        }
        com.a.a.c.m.ac a2 = gVar.a(lVar);
        a2.g();
        a2.b(str);
        a2.b(c);
        a2.h();
        com.a.a.b.l c2 = a2.c(lVar);
        c2.g();
        this.f263b[i].d().a(c2, gVar, obj);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/g$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private final com.a.a.c.j f264a;

        /* renamed from: b, reason: collision with root package name */
        private final List<b> f265b = new ArrayList();
        private final Map<String, Object> c = new HashMap();

        protected a(com.a.a.c.j jVar) {
            this.f264a = jVar;
        }

        public final void a(com.a.a.c.c.v vVar, com.a.a.c.i.e eVar) {
            Integer valueOf = Integer.valueOf(this.f265b.size());
            this.f265b.add(new b(vVar, eVar));
            a(vVar.a(), valueOf);
            a(eVar.b(), valueOf);
        }

        private void a(String str, Integer num) {
            Object obj = this.c.get(str);
            if (obj == null) {
                this.c.put(str, num);
                return;
            }
            if (obj instanceof List) {
                ((List) obj).add(num);
                return;
            }
            LinkedList linkedList = new LinkedList();
            linkedList.add(obj);
            linkedList.add(num);
            this.c.put(str, linkedList);
        }

        public final g a(c cVar) {
            int size = this.f265b.size();
            b[] bVarArr = new b[size];
            for (int i = 0; i < size; i++) {
                b bVar = this.f265b.get(i);
                com.a.a.c.c.v a2 = cVar.a(bVar.c());
                if (a2 != null) {
                    bVar.a(a2);
                }
                bVarArr[i] = bVar;
            }
            return new g(this.f264a, bVarArr, this.c, null, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/g$b.class */
    public static final class b {

        /* renamed from: a, reason: collision with root package name */
        private final com.a.a.c.c.v f266a;

        /* renamed from: b, reason: collision with root package name */
        private final com.a.a.c.i.e f267b;
        private final String c;
        private com.a.a.c.c.v d;

        public b(com.a.a.c.c.v vVar, com.a.a.c.i.e eVar) {
            this.f266a = vVar;
            this.f267b = eVar;
            this.c = eVar.b();
        }

        public final void a(com.a.a.c.c.v vVar) {
            this.d = vVar;
        }

        public final boolean a(String str) {
            return str.equals(this.c);
        }

        public final boolean a() {
            return this.f267b.e();
        }

        public final String b() {
            Class<?> d = this.f267b.d();
            if (d != null) {
                return this.f267b.c().a((Object) null, d);
            }
            return null;
        }

        public final String c() {
            return this.c;
        }

        public final com.a.a.c.c.v d() {
            return this.f266a;
        }

        public final com.a.a.c.c.v e() {
            return this.d;
        }
    }
}
