package com.d.a.a;

import com.a.a.c.k.b.aa;
import com.b.a.c.c;
import com.b.a.c.f;

/* loaded from: infinitode-2.jar:com/d/a/a/b.class */
public final class b implements com.d.a.b {

    /* renamed from: a, reason: collision with root package name */
    private c f953a = new c();

    /* loaded from: infinitode-2.jar:com/d/a/a/b$a.class */
    public static class a implements com.d.a.c {
        @Override // com.d.a.c
        public final com.d.a.b a() {
            return new b();
        }
    }

    @Override // com.d.a.b
    public final void a(String str, byte b2) {
        this.f953a.a(str, b2, (byte[]) null);
    }

    @Override // com.d.a.b
    public final int a() {
        return this.f953a.b();
    }

    @Override // com.d.a.b
    public final aa a(int i) {
        f c = this.f953a.c(i);
        return new aa(c.a(), c.b(), c.d());
    }

    @Override // com.d.a.b
    public final byte a(String str) {
        return c.a(str);
    }
}
