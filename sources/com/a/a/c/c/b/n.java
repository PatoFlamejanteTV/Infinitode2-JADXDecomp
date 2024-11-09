package com.a.a.c.c.b;

import java.io.IOException;
import java.util.EnumMap;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/n.class */
public final class n extends i<EnumMap<?, ?>> implements com.a.a.c.c.k, com.a.a.c.c.t {

    /* renamed from: a, reason: collision with root package name */
    private Class<?> f354a;
    private com.a.a.c.p f;
    private com.a.a.c.k<Object> g;
    private com.a.a.c.i.e h;
    private com.a.a.c.c.x i;
    private com.a.a.c.k<Object> j;
    private com.a.a.c.c.a.v k;

    public n(com.a.a.c.j jVar, com.a.a.c.c.x xVar, com.a.a.c.p pVar, com.a.a.c.k<?> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.s sVar) {
        super(jVar, sVar, (Boolean) null);
        this.f354a = jVar.t().b();
        this.f = pVar;
        this.g = kVar;
        this.h = eVar;
        this.i = xVar;
    }

    private n(n nVar, com.a.a.c.p pVar, com.a.a.c.k<?> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.s sVar) {
        super(nVar, sVar, nVar.e);
        this.f354a = nVar.f354a;
        this.f = pVar;
        this.g = kVar;
        this.h = eVar;
        this.i = nVar.i;
        this.j = nVar.j;
        this.k = nVar.k;
    }

    private n a(com.a.a.c.p pVar, com.a.a.c.k<?> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.s sVar) {
        if (pVar == this.f && sVar == this.c && kVar == this.g && eVar == this.h) {
            return this;
        }
        return new n(this, pVar, kVar, eVar, sVar);
    }

    @Override // com.a.a.c.c.t
    public final void d(com.a.a.c.g gVar) {
        if (this.i != null) {
            if (this.i.m()) {
                com.a.a.c.c.x xVar = this.i;
                gVar.a();
                com.a.a.c.j p = xVar.p();
                if (p == null) {
                    gVar.a(this.f344b, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'", this.f344b, this.i.getClass().getName()));
                }
                this.j = a(gVar, p, (com.a.a.c.c) null);
                return;
            }
            if (!this.i.n()) {
                if (this.i.o()) {
                    this.k = com.a.a.c.c.a.v.a(gVar, this.i, this.i.a(gVar.a()), gVar.a(com.a.a.c.q.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
                }
            } else {
                com.a.a.c.c.x xVar2 = this.i;
                gVar.a();
                com.a.a.c.j q = xVar2.q();
                if (q == null) {
                    gVar.a(this.f344b, String.format("Invalid delegate-creator definition for %s: value instantiator (%s) returned true for 'canCreateUsingArrayDelegate()', but null for 'getArrayDelegateType()'", this.f344b, this.i.getClass().getName()));
                }
                this.j = a(gVar, q, (com.a.a.c.c) null);
            }
        }
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.k<?> b2;
        com.a.a.c.p pVar = this.f;
        com.a.a.c.p pVar2 = pVar;
        if (pVar == null) {
            pVar2 = gVar.b(this.f344b.t(), cVar);
        }
        com.a.a.c.k<?> kVar = this.g;
        com.a.a.c.j u = this.f344b.u();
        if (kVar == null) {
            b2 = gVar.a(u, cVar);
        } else {
            b2 = gVar.b(kVar, cVar, u);
        }
        com.a.a.c.i.e eVar = this.h;
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar != null) {
            eVar2 = eVar2.a(cVar);
        }
        return a(pVar2, b2, eVar2, b(gVar, cVar, b2));
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return this.g == null && this.f == null && this.h == null;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Map;
    }

    @Override // com.a.a.c.c.b.i
    public final com.a.a.c.k<Object> g() {
        return this.g;
    }

    @Override // com.a.a.c.c.b.ae
    public final com.a.a.c.c.x i() {
        return this.i;
    }

    @Override // com.a.a.c.c.b.i, com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return k(gVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public EnumMap<?, ?> a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (this.k != null) {
            return e(lVar, gVar);
        }
        if (this.j != null) {
            return (EnumMap) this.i.a(gVar, this.j.a(lVar, gVar));
        }
        switch (lVar.l()) {
            case 1:
            case 2:
            case 5:
                return a(lVar, gVar, (EnumMap) k(gVar));
            case 3:
                return d(lVar, gVar);
            case 4:
            default:
                return (EnumMap) gVar.a(e(gVar), lVar);
            case 6:
                return m(lVar, gVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    public EnumMap<?, ?> a(com.a.a.b.l lVar, com.a.a.c.g gVar, EnumMap enumMap) {
        String v;
        Object a2;
        lVar.a(enumMap);
        com.a.a.c.k<Object> kVar = this.g;
        com.a.a.c.i.e eVar = this.h;
        if (lVar.q()) {
            v = lVar.h();
        } else {
            com.a.a.b.o k = lVar.k();
            if (k != com.a.a.b.o.FIELD_NAME) {
                if (k == com.a.a.b.o.END_OBJECT) {
                    return enumMap;
                }
                gVar.a(this, com.a.a.b.o.FIELD_NAME, (String) null, new Object[0]);
            }
            v = lVar.v();
        }
        while (true) {
            String str = v;
            if (str != null) {
                Enum r0 = (Enum) this.f.a(str, gVar);
                com.a.a.b.o g = lVar.g();
                if (r0 == null) {
                    if (!gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                        return (EnumMap) gVar.b(this.f354a, str, "value not one of declared Enum instance names for %s", this.f344b.t());
                    }
                    lVar.j();
                } else {
                    try {
                        if (g == com.a.a.b.o.VALUE_NULL) {
                            if (!this.d) {
                                a2 = this.c.a(gVar);
                            }
                        } else if (eVar == null) {
                            a2 = kVar.a(lVar, gVar);
                        } else {
                            a2 = kVar.a(lVar, gVar, eVar);
                        }
                        enumMap.put((EnumMap) r0, (Enum) a2);
                    } catch (Exception e) {
                        return (EnumMap) a(gVar, e, enumMap, str);
                    }
                }
                v = lVar.h();
            } else {
                return enumMap;
            }
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.a(lVar, gVar);
    }

    private EnumMap<?, ?> k(com.a.a.c.g gVar) {
        if (this.i == null) {
            return new EnumMap<>(this.f354a);
        }
        try {
            if (!this.i.l()) {
                return (EnumMap) gVar.a(a(), i(), (com.a.a.b.l) null, "no default constructor found", new Object[0]);
            }
            return (EnumMap) this.i.a(gVar);
        } catch (IOException e) {
            return (EnumMap) com.a.a.c.m.i.a(gVar, e);
        }
    }

    private EnumMap<?, ?> e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String str;
        Object a2;
        com.a.a.c.c.a.v vVar = this.k;
        com.a.a.c.c.a.y a3 = vVar.a(lVar, gVar, null);
        if (lVar.q()) {
            str = lVar.h();
        } else if (lVar.a(com.a.a.b.o.FIELD_NAME)) {
            str = lVar.v();
        } else {
            str = null;
        }
        while (true) {
            String str2 = str;
            if (str2 != null) {
                com.a.a.b.o g = lVar.g();
                com.a.a.c.c.v a4 = vVar.a(str2);
                if (a4 != null) {
                    if (a3.a(a4, a4.a(lVar, gVar))) {
                        lVar.g();
                        try {
                            return a(lVar, gVar, (EnumMap) vVar.a(gVar, a3));
                        } catch (Exception e) {
                            return (EnumMap) a(gVar, e, this.f344b.b(), str2);
                        }
                    }
                } else {
                    Enum r0 = (Enum) this.f.a(str2, gVar);
                    if (r0 == null) {
                        if (!gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                            return (EnumMap) gVar.b(this.f354a, str2, "value not one of declared Enum instance names for %s", this.f344b.t());
                        }
                        lVar.g();
                        lVar.j();
                    } else {
                        try {
                            if (g == com.a.a.b.o.VALUE_NULL) {
                                if (!this.d) {
                                    a2 = this.c.a(gVar);
                                }
                            } else if (this.h == null) {
                                a2 = this.g.a(lVar, gVar);
                            } else {
                                a2 = this.g.a(lVar, gVar, this.h);
                            }
                            a3.a(r0, a2);
                        } catch (Exception e2) {
                            a(gVar, e2, this.f344b.b(), str2);
                            return null;
                        }
                    }
                }
                str = lVar.h();
            } else {
                try {
                    return (EnumMap) vVar.a(gVar, a3);
                } catch (Exception e3) {
                    a(gVar, e3, this.f344b.b(), str2);
                    return null;
                }
            }
        }
    }
}
