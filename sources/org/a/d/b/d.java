package org.a.d.b;

/* loaded from: infinitode-2.jar:org/a/d/b/d.class */
public abstract class d extends a {

    /* renamed from: a, reason: collision with root package name */
    private String f4677a;

    /* renamed from: b, reason: collision with root package name */
    private String f4678b;
    private String c;

    public d(org.a.d.b bVar, String str, String str2, String str3) {
        super(bVar, str3);
        al alVar = (al) getClass().getAnnotation(al.class);
        if (alVar != null) {
            this.f4677a = alVar.a();
            this.f4678b = alVar.b();
        } else {
            if (str == null) {
                throw new IllegalArgumentException("Both StructuredType annotation and namespace parameter cannot be null");
            }
            this.f4677a = str;
            this.f4678b = str2;
        }
        this.c = str2 == null ? this.f4678b : str2;
    }

    public final String h() {
        return this.f4677a;
    }

    public final String i() {
        return this.c;
    }

    public final void f(String str) {
        this.c = str;
    }

    public final am d(String str, String str2) {
        return g().b().a(h(), i(), str, str2);
    }

    public final f a(String str, k kVar) {
        return g().b().a(h(), i(), str, kVar);
    }
}
