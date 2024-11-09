package com.a.a.c.k.a;

import com.a.a.a.al;
import com.a.a.c.aa;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/v.class */
public final class v {

    /* renamed from: a, reason: collision with root package name */
    private al<?> f586a;

    /* renamed from: b, reason: collision with root package name */
    private Object f587b;
    private boolean c = false;

    public v(al<?> alVar) {
        this.f586a = alVar;
    }

    public final boolean a(com.a.a.b.h hVar, aa aaVar, m mVar) {
        if (this.f587b == null) {
            return false;
        }
        if (this.c || mVar.e) {
            if (hVar.d()) {
                String.valueOf(this.f587b);
                hVar.l();
                return true;
            }
            mVar.d.a(this.f587b, hVar, aaVar);
            return true;
        }
        return false;
    }

    public final Object a(Object obj) {
        if (this.f587b == null) {
            this.f587b = this.f586a.b(obj);
        }
        return this.f587b;
    }

    public final void b(com.a.a.b.h hVar, aa aaVar, m mVar) {
        this.c = true;
        if (hVar.d()) {
            hVar.f((Object) (this.f587b == null ? null : String.valueOf(this.f587b)));
            return;
        }
        com.a.a.b.r rVar = mVar.f577b;
        if (rVar != null) {
            hVar.b(rVar);
            mVar.d.a(this.f587b, hVar, aaVar);
        }
    }
}
