package org.a.d.b;

/* loaded from: infinitode-2.jar:org/a/d/b/c.class */
public abstract class c extends b {

    /* renamed from: a, reason: collision with root package name */
    private final String f4676a;

    public abstract void a(Object obj);

    public abstract String a();

    public c(org.a.d.b bVar, String str, String str2, String str3, Object obj) {
        super(bVar, str3);
        a(obj);
        this.f4676a = str2;
    }

    public String toString() {
        return "[" + getClass().getSimpleName() + ":" + a() + "]";
    }

    public final String b() {
        return this.f4676a;
    }
}
