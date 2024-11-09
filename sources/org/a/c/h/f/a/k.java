package org.a.c.h.f.a;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/k.class */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.b.d f4578a;

    public k() {
        this.f4578a = new org.a.c.b.d();
    }

    public k(org.a.c.b.d dVar) {
        this.f4578a = dVar;
    }

    public final Map<String, u> a() {
        HashMap hashMap = new HashMap();
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4578a.a(org.a.c.b.j.aa);
        org.a.c.b.d dVar2 = dVar;
        if (dVar == null) {
            dVar2 = new org.a.c.b.d();
            this.f4578a.a(org.a.c.b.j.aa, (org.a.c.b.b) dVar2);
        }
        for (org.a.c.b.j jVar : dVar2.d()) {
            hashMap.put(jVar.a(), (u) f.a(dVar2.a(jVar)));
        }
        return new org.a.c.h.a.b(hashMap, dVar2);
    }

    public final l b() {
        org.a.c.b.d dVar = (org.a.c.b.d) this.f4578a.a(org.a.c.b.j.cV);
        if (dVar != null) {
            return new l(dVar);
        }
        return null;
    }

    public final boolean c() {
        return "NChannel".equals(this.f4578a.g(org.a.c.b.j.dE));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder(this.f4578a.g(org.a.c.b.j.dE));
        sb.append('{');
        if (b() != null) {
            sb.append(b());
            sb.append(' ');
        }
        try {
            Map<String, u> a2 = a();
            sb.append("Colorants{");
            for (Map.Entry<String, u> entry : a2.entrySet()) {
                sb.append('\"');
                sb.append(entry.getKey());
                sb.append("\": ");
                sb.append(entry.getValue());
                sb.append(' ');
            }
            sb.append('}');
        } catch (IOException unused) {
            sb.append("ERROR");
        }
        sb.append('}');
        return sb.toString();
    }
}
