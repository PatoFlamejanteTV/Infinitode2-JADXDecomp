package com.a.a.c.c.a;

import com.a.a.c.c.v;
import java.util.Collection;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/m.class */
public final class m extends v.a {
    private String h;
    private boolean i;
    private com.a.a.c.c.v j;

    public m(com.a.a.c.c.v vVar, String str, com.a.a.c.c.v vVar2, boolean z) {
        super(vVar);
        this.h = str;
        this.j = vVar2;
        this.i = z;
    }

    @Override // com.a.a.c.c.v.a
    protected final com.a.a.c.c.v a(com.a.a.c.c.v vVar) {
        throw new IllegalStateException("Should never try to reset delegate");
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final void a(com.a.a.c.f fVar) {
        this.g.a(fVar);
        this.j.a(fVar);
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final void a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        a(obj, this.g.a(lVar, gVar));
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        return b(obj, a(lVar, gVar));
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final void a(Object obj, Object obj2) {
        b(obj, obj2);
    }

    @Override // com.a.a.c.c.v.a, com.a.a.c.c.v
    public final Object b(Object obj, Object obj2) {
        if (obj2 != null) {
            if (this.i) {
                if (obj2 instanceof Object[]) {
                    for (Object obj3 : (Object[]) obj2) {
                        if (obj3 != null) {
                            this.j.a(obj3, obj);
                        }
                    }
                } else if (obj2 instanceof Collection) {
                    for (Object obj4 : (Collection) obj2) {
                        if (obj4 != null) {
                            this.j.a(obj4, obj);
                        }
                    }
                } else if (obj2 instanceof Map) {
                    for (Object obj5 : ((Map) obj2).values()) {
                        if (obj5 != null) {
                            this.j.a(obj5, obj);
                        }
                    }
                } else {
                    throw new IllegalStateException("Unsupported container type (" + obj2.getClass().getName() + ") when resolving reference '" + this.h + "'");
                }
            } else {
                this.j.a(obj2, obj);
            }
        }
        return this.g.b(obj, obj2);
    }
}
