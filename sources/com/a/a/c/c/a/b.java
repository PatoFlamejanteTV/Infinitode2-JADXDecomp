package com.a.a.c.c.a;

import java.util.Set;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/b.class */
public final class b extends com.a.a.c.c.f {
    private com.a.a.c.c.f t;
    private com.a.a.c.c.v[] u;

    public b(com.a.a.c.c.f fVar, com.a.a.c.c.v[] vVarArr) {
        super(fVar);
        this.t = fVar;
        this.u = vVarArr;
    }

    @Override // com.a.a.c.c.f, com.a.a.c.k
    public final com.a.a.c.k<Object> a(com.a.a.c.m.r rVar) {
        return this.t.a(rVar);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(s sVar) {
        return new b(this.t.a(sVar), this.u);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(Set<String> set, Set<String> set2) {
        return new b(this.t.a(set, set2), this.u);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(boolean z) {
        return new b(this.t.a(z), this.u);
    }

    @Override // com.a.a.c.c.f
    public final com.a.a.c.c.f a(c cVar) {
        return new b(this.t.a(cVar), this.u);
    }

    @Override // com.a.a.c.c.f
    protected final com.a.a.c.c.f g() {
        return this;
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (!lVar.p()) {
            return w(lVar, gVar);
        }
        if (!this.g) {
            return v(lVar, gVar);
        }
        Object a2 = this.f402b.a(gVar);
        lVar.a(a2);
        com.a.a.c.c.v[] vVarArr = this.u;
        int i = 0;
        int length = vVarArr.length;
        while (lVar.g() != com.a.a.b.o.END_ARRAY) {
            if (i != length) {
                com.a.a.c.c.v vVar = vVarArr[i];
                if (vVar != null) {
                    try {
                        vVar.a(lVar, gVar, a2);
                    } catch (Exception e) {
                        a(e, a2, vVar.a(), gVar);
                    }
                } else {
                    lVar.j();
                }
                i++;
            } else {
                if (!this.m && gVar.a(com.a.a.c.i.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    gVar.a(this, com.a.a.b.o.END_ARRAY, "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                do {
                    lVar.j();
                } while (lVar.g() != com.a.a.b.o.END_ARRAY);
                return a2;
            }
        }
        return a2;
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object obj) {
        lVar.a(obj);
        if (!lVar.p()) {
            return w(lVar, gVar);
        }
        if (this.i != null) {
            a(gVar, obj);
        }
        com.a.a.c.c.v[] vVarArr = this.u;
        int i = 0;
        int length = vVarArr.length;
        while (lVar.g() != com.a.a.b.o.END_ARRAY) {
            if (i != length) {
                com.a.a.c.c.v vVar = vVarArr[i];
                if (vVar != null) {
                    try {
                        vVar.a(lVar, gVar, obj);
                    } catch (Exception e) {
                        a(e, obj, vVar.a(), gVar);
                    }
                } else {
                    lVar.j();
                }
                i++;
            } else {
                if (!this.m && gVar.a(com.a.a.c.i.FAIL_ON_UNKNOWN_PROPERTIES)) {
                    gVar.a(this, com.a.a.b.o.END_ARRAY, "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                do {
                    lVar.j();
                } while (lVar.g() != com.a.a.b.o.END_ARRAY);
                return obj;
            }
        }
        return obj;
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
        lVar.a(a2);
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
                        vVar.a(lVar, gVar, a2);
                    } catch (Exception e) {
                        a(e, a2, vVar.a(), gVar);
                    }
                } else {
                    lVar.j();
                }
            } else {
                if (!this.m) {
                    gVar.a(this, com.a.a.b.o.END_ARRAY, "Unexpected JSON values; expected at most %d properties (in JSON Array)", Integer.valueOf(length));
                }
                do {
                    lVar.j();
                } while (lVar.g() != com.a.a.b.o.END_ARRAY);
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
        int i = 0;
        Object obj = null;
        Class<?> d = this.n ? gVar.d() : null;
        while (lVar.g() != com.a.a.b.o.END_ARRAY) {
            com.a.a.c.c.v vVar2 = i < length ? vVarArr[i] : null;
            com.a.a.c.c.v vVar3 = vVar2;
            if (vVar2 == null) {
                lVar.j();
            } else if (d != null && !vVar3.a(d)) {
                lVar.j();
            } else if (obj != null) {
                try {
                    vVar3.a(lVar, gVar, obj);
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
                                lVar.a(obj);
                                if (obj.getClass() != this.f401a.b()) {
                                    gVar.a(this.f401a, String.format("Cannot support implicit polymorphic deserialization for POJOs-as-Arrays style: nominal type %s, actual type %s", com.a.a.c.m.i.b(this.f401a), com.a.a.c.m.i.c(obj)));
                                }
                            } catch (Exception e2) {
                                a(e2, this.f401a.b(), a3, gVar);
                            }
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
        return gVar.a(e(gVar), lVar.k(), lVar, "Cannot deserialize a POJO (of type %s) from non-Array representation (token: %s): type/property designed to be serialized as JSON Array", com.a.a.c.m.i.b(this.f401a), lVar.k());
    }
}
