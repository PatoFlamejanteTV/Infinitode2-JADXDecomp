package com.a.a.a;

import com.a.a.a.al;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/a/aq.class */
public class aq implements an {

    /* renamed from: a, reason: collision with root package name */
    private Map<al.a, Object> f54a;

    @Override // com.a.a.a.an
    public final void a(al.a aVar, Object obj) {
        if (this.f54a == null) {
            this.f54a = new HashMap();
        } else {
            Object obj2 = this.f54a.get(aVar);
            if (obj2 != null) {
                if (obj2 == obj) {
                    return;
                } else {
                    throw new IllegalStateException("Already had POJO for id (" + aVar.f46a.getClass().getName() + ") [" + aVar + "]");
                }
            }
        }
        this.f54a.put(aVar, obj);
    }

    @Override // com.a.a.a.an
    public final Object a(al.a aVar) {
        if (this.f54a == null) {
            return null;
        }
        return this.f54a.get(aVar);
    }

    @Override // com.a.a.a.an
    public final boolean a(an anVar) {
        return anVar.getClass() == getClass();
    }

    @Override // com.a.a.a.an
    public final an a() {
        return new aq();
    }
}
