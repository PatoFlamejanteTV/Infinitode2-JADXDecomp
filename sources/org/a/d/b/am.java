package org.a.d.b;

/* loaded from: infinitode-2.jar:org/a/d/b/am.class */
public class am extends c {

    /* renamed from: a, reason: collision with root package name */
    private String f4669a;

    public am(org.a.d.b bVar, String str, String str2, String str3, Object obj) {
        super(bVar, str, str2, str3, obj);
    }

    @Override // org.a.d.b.c
    public final void a(Object obj) {
        if (!(obj instanceof String)) {
            throw new IllegalArgumentException("Value given is not allowed for the Text type : '" + obj + "'");
        }
        this.f4669a = (String) obj;
    }

    @Override // org.a.d.b.c
    public final String a() {
        return this.f4669a;
    }
}
