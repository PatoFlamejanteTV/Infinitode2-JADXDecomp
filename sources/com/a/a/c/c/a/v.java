package com.a.a.c.c.a;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/v.class */
public final class v {

    /* renamed from: a, reason: collision with root package name */
    private int f284a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.c.x f285b;
    private HashMap<String, com.a.a.c.c.v> c;
    private com.a.a.c.c.v[] d;

    private v(com.a.a.c.g gVar, com.a.a.c.c.x xVar, com.a.a.c.c.v[] vVarArr, boolean z, boolean z2) {
        this.f285b = xVar;
        if (z) {
            this.c = a.a(gVar.a().t());
        } else {
            this.c = new HashMap<>();
        }
        int length = vVarArr.length;
        this.f284a = length;
        this.d = new com.a.a.c.c.v[length];
        if (z2) {
            com.a.a.c.f a2 = gVar.a();
            for (com.a.a.c.c.v vVar : vVarArr) {
                if (!vVar.g()) {
                    List<com.a.a.c.w> a3 = vVar.a((com.a.a.c.b.q<?>) a2);
                    if (!a3.isEmpty()) {
                        Iterator<com.a.a.c.w> it = a3.iterator();
                        while (it.hasNext()) {
                            this.c.put(it.next().b(), vVar);
                        }
                    }
                }
            }
        }
        for (int i = 0; i < length; i++) {
            com.a.a.c.c.v vVar2 = vVarArr[i];
            this.d[i] = vVar2;
            if (!vVar2.g()) {
                this.c.put(vVar2.a(), vVar2);
            }
        }
    }

    public static v a(com.a.a.c.g gVar, com.a.a.c.c.x xVar, com.a.a.c.c.v[] vVarArr, c cVar) {
        int length = vVarArr.length;
        com.a.a.c.c.v[] vVarArr2 = new com.a.a.c.c.v[length];
        for (int i = 0; i < length; i++) {
            com.a.a.c.c.v vVar = vVarArr[i];
            com.a.a.c.c.v vVar2 = vVar;
            if (!vVar.n() && !vVar2.j()) {
                vVar2 = vVar2.a((com.a.a.c.k<?>) gVar.a(vVar2.c(), vVar2));
            }
            vVarArr2[i] = vVar2;
        }
        return new v(gVar, xVar, vVarArr2, cVar.c(), true);
    }

    public static v a(com.a.a.c.g gVar, com.a.a.c.c.x xVar, com.a.a.c.c.v[] vVarArr, boolean z) {
        int length = vVarArr.length;
        com.a.a.c.c.v[] vVarArr2 = new com.a.a.c.c.v[length];
        for (int i = 0; i < length; i++) {
            com.a.a.c.c.v vVar = vVarArr[i];
            com.a.a.c.c.v vVar2 = vVar;
            if (!vVar.n()) {
                vVar2 = vVar2.a((com.a.a.c.k<?>) gVar.a(vVar2.c(), vVar2));
            }
            vVarArr2[i] = vVar2;
        }
        return new v(gVar, xVar, vVarArr2, z, false);
    }

    public final com.a.a.c.c.v a(String str) {
        return this.c.get(str);
    }

    public final y a(com.a.a.b.l lVar, com.a.a.c.g gVar, s sVar) {
        return new y(lVar, gVar, this.f284a, sVar);
    }

    public final Object a(com.a.a.c.g gVar, y yVar) {
        Object a2 = this.f285b.a(gVar, this.d, yVar);
        Object obj = a2;
        if (a2 != null) {
            obj = yVar.a(gVar, obj);
            x a3 = yVar.a();
            while (true) {
                x xVar = a3;
                if (xVar == null) {
                    break;
                }
                xVar.a(obj);
                a3 = xVar.f287a;
            }
        }
        return obj;
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/c/a/v$a.class */
    static class a extends HashMap<String, com.a.a.c.c.v> {

        /* renamed from: a, reason: collision with root package name */
        private Locale f286a;

        @Deprecated
        public a() {
            this(Locale.getDefault());
        }

        private a(Locale locale) {
            this.f286a = locale;
        }

        public static a a(Locale locale) {
            return new a(locale);
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public com.a.a.c.c.v get(Object obj) {
            return (com.a.a.c.c.v) super.get(((String) obj).toLowerCase(this.f286a));
        }

        /* JADX INFO: Access modifiers changed from: private */
        @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public com.a.a.c.c.v put(String str, com.a.a.c.c.v vVar) {
            return (com.a.a.c.c.v) super.put(str.toLowerCase(this.f286a), vVar);
        }
    }
}
