package com.a.a.c;

import com.a.a.c.f.w;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/a/a/c/t.class */
public class t extends com.a.a.b.p implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private f f758a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.c.n f759b;
    private com.a.a.b.f c;
    private ConcurrentHashMap<j, k<Object>> d;

    /* JADX INFO: Access modifiers changed from: protected */
    public t(s sVar, f fVar, j jVar, Object obj, w.a aVar, com.a.a.c.k.a.d dVar) {
        this.f758a = fVar;
        this.f759b = sVar.d;
        this.d = sVar.e;
        this.c = sVar.f756a;
        fVar.a();
        a(jVar);
    }

    @Override // com.a.a.b.p
    public final void a(com.a.a.b.h hVar, Object obj) {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    private com.a.a.c.c.n a() {
        return this.f759b.a(this.f758a);
    }

    private k<Object> a(j jVar) {
        if (jVar == null || !this.f758a.a(i.EAGER_DESERIALIZER_FETCH)) {
            return null;
        }
        k<Object> kVar = this.d.get(jVar);
        k<Object> kVar2 = kVar;
        if (kVar == null) {
            try {
                k<Object> b2 = a().b(jVar);
                kVar2 = b2;
                if (b2 != null) {
                    this.d.put(jVar, kVar2);
                }
                return kVar2;
            } catch (com.a.a.b.d unused) {
            }
        }
        return kVar2;
    }
}
