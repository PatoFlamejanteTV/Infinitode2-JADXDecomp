package org.a.d.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/d/b/b.class */
public abstract class b {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.d.b f4674a;

    /* renamed from: b, reason: collision with root package name */
    private String f4675b;
    private final Map<String, g> c = new HashMap();

    public b(org.a.d.b bVar, String str) {
        this.f4674a = bVar;
        this.f4675b = str;
    }

    public final String e() {
        return this.f4675b;
    }

    public final void a(g gVar) {
        if (this.c.containsKey(gVar.a())) {
            this.c.remove(gVar.a());
        }
        this.c.put(gVar.a(), gVar);
    }

    public final g e(String str) {
        return this.c.get(str);
    }

    public final List<g> f() {
        return new ArrayList(this.c.values());
    }

    public final org.a.d.b g() {
        return this.f4674a;
    }
}
