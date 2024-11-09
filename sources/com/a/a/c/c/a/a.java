package com.a.a.c.c.a;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/a.class */
public final class a extends com.a.a.c.c.f {
    private com.a.a.c.c.f t;
    private com.a.a.c.c.v[] u;
    private com.a.a.c.f.k v;
    private com.a.a.c.j w;

    public a(com.a.a.c.c.f fVar, com.a.a.c.j jVar, com.a.a.c.c.v[] vVarArr, com.a.a.c.f.k kVar) {
        super(fVar);
        this.t = fVar;
        this.w = jVar;
        this.u = vVarArr;
        this.v = kVar;
    }

    @Override // com.a.a.c.c.f, com.a.a.c.k
    public final com.a.a.c.k<Object> a(com.a.a.c.m.r rVar) {
        return this.t.a(rVar);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(s sVar) {
        return new a(this.t.a(sVar), this.w, this.u, this.v);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(Set<String> set, Set<String> set2) {
        return new a(this.t.a(set, set2), this.w, this.u, this.v);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(boolean z) {
        return new a(this.t.a(z), this.w, this.u, this.v);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(c cVar) {
        return new a(this.t.a(cVar), this.w, this.u, this.v);
    }

    @Override // com.a.a.c.c.f
    protected final com.a.a.c.c.f g() {
        return this;
    }

    @Override // com.a.a.c.c.f, com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.FALSE;
    }

    private Object b(com.a.a.c.g gVar, Object obj) {
        try {
            return this.v.i().invoke(obj, (Object[]) null);
        } catch (Exception e) {
            return a(e, gVar);
        }
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (!lVar.p()) {
            return b(gVar, w(lVar, gVar));
        }
        if (!this.g) {
            return b(gVar, v(lVar, gVar));
        }
        Object a2 = this.f402b.a(gVar);
        com.a.a.c.c.v[] vVarArr = this.u;
        int i = 0;
        int length = vVarArr.length;
        while (lVar.g() != com.a.a.b.o.END_ARRAY) {
            if (i != length) {
                com.a.a.c.c.v vVar = vVarArr[i];
                if (vVar != null) {
                    try {
                        a2 = vVar.b(lVar, gVar, a2);
                    } catch (Exception e) {
                        a(e, a2, vVar.a(), gVar);
                    }
                } else {
                    lVar.j();
                }
                i++;
            } else {
                if (!this.m && gVar.a(com.a.a.c.i.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    gVar.a(a(), "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                while (lVar.g() != com.a.a.b.o.END_ARRAY) {
                    lVar.j();
                }
                return b(gVar, a2);
            }
        }
        return b(gVar, a2);
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        return this.t.a(lVar, gVar, (com.a.a.c.g) obj);
    }

    @Override // com.a.a.c.c.f
    public final Object b(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return w(lVar, gVar);
    }

    private Object v(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.f) {
            return g(lVar, gVar);
        }
        Object a2 = this.f402b.a(gVar);
        if (this.i != null) {
            a(gVar, a2);
        }
        Class<?> d = this.n ? gVar.d() : null;
        com.a.a.c.c.v[] vVarArr = this.u;
        int i = 0;
        int length = vVarArr.length;
        while (lVar.g() != com.a.a.b.o.END_ARRAY) {
            if (i != length) {
                com.a.a.c.c.v vVar = vVarArr[i];
                i++;
                if (vVar != null && (d == null || vVar.a(d))) {
                    try {
                        vVar.b(lVar, gVar, a2);
                    } catch (Exception e) {
                        a(e, a2, vVar.a(), gVar);
                    }
                } else {
                    lVar.j();
                }
            } else {
                if (!this.m && gVar.a(com.a.a.c.i.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    gVar.a(this, com.a.a.b.o.END_ARRAY, "Unexpected JSON value(s); expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                while (lVar.g() != com.a.a.b.o.END_ARRAY) {
                    lVar.j();
                }
                return a2;
            }
        }
        return a2;
    }

    @Override // com.a.a.c.c.f
    protected final Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        v vVar = this.e;
        y a2 = vVar.a(lVar, gVar, this.q);
        com.a.a.c.c.v[] vVarArr = this.u;
        int length = vVarArr.length;
        Class<?> d = this.n ? gVar.d() : null;
        int i = 0;
        Object obj = null;
        while (lVar.g() != com.a.a.b.o.END_ARRAY) {
            com.a.a.c.c.v vVar2 = i < length ? vVarArr[i] : null;
            com.a.a.c.c.v vVar3 = vVar2;
            if (vVar2 == null) {
                lVar.j();
            } else if (d != null && !vVar3.a(d)) {
                lVar.j();
            } else if (obj != null) {
                try {
                    obj = vVar3.b(lVar, gVar, obj);
                } catch (Exception e) {
                    a(e, obj, vVar3.a(), gVar);
                }
            } else {
                String a3 = vVar3.a();
                com.a.a.c.c.v a4 = vVar.a(a3);
                if (!a2.a(a3) || a4 != null) {
                    if (a4 != null) {
                        if (a2.a(a4, a4.a(lVar, gVar))) {
                            try {
                                obj = vVar.a(gVar, a2);
                                if (obj.getClass() != this.f401a.b()) {
                                    return gVar.a(this.f401a, String.format("Cannot support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", com.a.a.c.m.i.b(this.f401a), obj.getClass().getName()));
                                }
                            } catch (Exception e2) {
                                a(e2, this.f401a.b(), a3, gVar);
                            }
                        } else {
                            continue;
                        }
                    } else {
                        a2.b(vVar3, vVar3.a(lVar, gVar));
                    }
                }
            }
            i++;
        }
        if (obj == null) {
            try {
                obj = vVar.a(gVar, a2);
            } catch (Exception e3) {
                return a(e3, gVar);
            }
        }
        return obj;
    }

    private Object w(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        return gVar.a(e(gVar), lVar.k(), lVar, "Cannot deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", this.f401a.b().getName(), lVar.k());
    }
}
