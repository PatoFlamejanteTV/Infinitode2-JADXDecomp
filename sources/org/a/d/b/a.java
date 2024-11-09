package org.a.d.b;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/d/b/a.class */
public abstract class a extends b {

    /* renamed from: a, reason: collision with root package name */
    private final m f4666a;

    /* renamed from: b, reason: collision with root package name */
    private final Map<String, String> f4667b;

    public a(org.a.d.b bVar, String str) {
        super(bVar, str);
        this.f4666a = new m();
        this.f4667b = new HashMap();
    }

    public final void c(String str, String str2) {
        this.f4667b.put(str, str2);
    }

    public final Map<String, String> b() {
        return this.f4667b;
    }

    public final void a(b bVar) {
        this.f4666a.a(bVar.e());
        this.f4666a.a(bVar);
    }

    public final void b(b bVar) {
        this.f4666a.b(bVar);
    }

    public final m c() {
        return this.f4666a;
    }

    public final List<b> d() {
        return this.f4666a.a();
    }
}
