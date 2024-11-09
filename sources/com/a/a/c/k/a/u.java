package com.a.a.c.k.a;

import com.a.a.c.aa;
import com.a.a.c.z;
import java.io.Serializable;
import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/k/a/u.class */
public final class u extends com.a.a.c.k.b.d implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    protected final com.a.a.c.m.r f585a;

    public u(com.a.a.c.k.b.d dVar, com.a.a.c.m.r rVar) {
        super(dVar, rVar);
        this.f585a = rVar;
    }

    private u(u uVar, m mVar) {
        super(uVar, mVar);
        this.f585a = uVar.f585a;
    }

    private u(u uVar, m mVar, Object obj) {
        super(uVar, mVar, obj);
        this.f585a = uVar.f585a;
    }

    private u(u uVar, Set<String> set, Set<String> set2) {
        super(uVar, set, set2);
        this.f585a = uVar.f585a;
    }

    private u(u uVar, com.a.a.c.k.e[] eVarArr, com.a.a.c.k.e[] eVarArr2) {
        super(uVar, eVarArr, eVarArr2);
        this.f585a = uVar.f585a;
    }

    @Override // com.a.a.c.o
    public final com.a.a.c.o<Object> a(com.a.a.c.m.r rVar) {
        return new u(this, rVar);
    }

    @Override // com.a.a.c.o
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.k.b.d
    public final com.a.a.c.k.b.d a(m mVar) {
        return new u(this, mVar);
    }

    @Override // com.a.a.c.k.b.d
    public final com.a.a.c.k.b.d a(Object obj) {
        return new u(this, this.g, obj);
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d a(Set<String> set, Set<String> set2) {
        return new u(this, set, set2);
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d a(com.a.a.c.k.e[] eVarArr, com.a.a.c.k.e[] eVarArr2) {
        return new u(this, eVarArr, eVarArr2);
    }

    @Override // com.a.a.c.k.b.d
    protected final com.a.a.c.k.b.d d() {
        return this;
    }

    @Override // com.a.a.c.k.b.ao, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar) {
        hVar.a(obj);
        if (this.g != null) {
            a(obj, hVar, aaVar, false);
        } else if (this.f != null) {
            c(obj, hVar, aaVar);
        } else {
            b(obj, hVar, aaVar);
        }
    }

    @Override // com.a.a.c.k.b.d, com.a.a.c.o
    public final void a(Object obj, com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
        if (aaVar.a(z.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS)) {
            aaVar.a(a(), "Unwrapped property requires use of type information: cannot serialize without disabling `SerializationFeature.FAIL_ON_UNWRAPPED_TYPE_IDENTIFIERS`");
        }
        hVar.a(obj);
        if (this.g != null) {
            b(obj, hVar, aaVar, iVar);
        } else if (this.f != null) {
            c(obj, hVar, aaVar);
        } else {
            b(obj, hVar, aaVar);
        }
    }

    public final String toString() {
        return "UnwrappingBeanSerializer for " + a().getName();
    }
}
