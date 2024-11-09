package com.d.c.f;

import com.d.h.w;

/* loaded from: infinitode-2.jar:com/d/c/f/e.class */
public abstract class e implements g {

    /* renamed from: a, reason: collision with root package name */
    private String f1088a;

    /* renamed from: b, reason: collision with root package name */
    private short f1089b;

    /* JADX INFO: Access modifiers changed from: protected */
    public e(com.d.c.a.a aVar, short s, String str, String str2) {
        this.f1089b = s;
        if (str == null) {
            throw new w.a("CSSValue for '" + aVar + "' is null after resolving CSS identifier for value '" + str2 + "'");
        }
        this.f1088a = a(str, str2);
    }

    private String a(String str, String str2) {
        switch (this.f1089b) {
            case 19:
            case 20:
            case 21:
            case 22:
                return str2 == null ? str : str2;
            default:
                return str;
        }
    }

    public final String a() {
        return this.f1088a;
    }

    public final short i() {
        return this.f1089b;
    }

    @Override // com.d.c.f.g
    public float b() {
        throw new w.a("asFloat() needs to be overridden in subclass.");
    }

    @Override // com.d.c.f.g
    public com.d.c.d.g c() {
        throw new w.a("asColor() needs to be overridden in subclass.");
    }

    @Override // com.d.c.f.g
    public float a(com.d.c.a.a aVar, float f, d dVar) {
        throw new w.a("getFloatProportionalTo() needs to be overridden in subclass.");
    }

    @Override // com.d.c.f.g
    public final String d() {
        return a();
    }

    @Override // com.d.c.f.g
    public String[] e() {
        throw new w.a("asStringArray() needs to be overridden in subclass.");
    }

    @Override // com.d.c.f.g
    public final com.d.c.a.c f() {
        throw new w.a("asIdentValue() needs to be overridden in subclass.");
    }

    @Override // com.d.c.f.g
    public boolean g() {
        throw new w.a("hasAbsoluteUnit() needs to be overridden in subclass.");
    }

    @Override // com.d.c.f.g
    public final boolean h() {
        return false;
    }
}
