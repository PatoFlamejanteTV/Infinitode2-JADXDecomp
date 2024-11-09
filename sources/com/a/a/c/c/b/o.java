package com.a.a.c.c.b;

import com.a.a.a.l;
import java.util.EnumSet;
import java.util.Objects;

/* loaded from: infinitode-2.jar:com/a/a/c/c/b/o.class */
public final class o extends ae<EnumSet<?>> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.c.j f355a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.k<Enum<?>> f356b;
    private com.a.a.c.c.s c;
    private boolean d;
    private Boolean e;

    /* JADX WARN: Multi-variable type inference failed */
    public o(com.a.a.c.j jVar, com.a.a.c.k<?> kVar) {
        super((Class<?>) EnumSet.class);
        this.f355a = jVar;
        if (!jVar.h()) {
            throw new IllegalArgumentException("Type " + jVar + " not Java Enum type");
        }
        this.f356b = kVar;
        this.e = null;
        this.c = null;
        this.d = false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private o(o oVar, com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar, Boolean bool) {
        super(oVar);
        this.f355a = oVar.f355a;
        this.f356b = kVar;
        this.c = sVar;
        this.d = com.a.a.c.c.a.q.a(sVar);
        this.e = bool;
    }

    private o a(com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar, Boolean bool) {
        if (Objects.equals(this.e, bool) && this.f356b == kVar && this.c == kVar) {
            return this;
        }
        return new o(this, kVar, sVar, bool);
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        if (this.f355a.A() != null) {
            return false;
        }
        return true;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Collection;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.TRUE;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return g();
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.m.a e() {
        return com.a.a.c.m.a.DYNAMIC;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.k<?> b2;
        Boolean a2 = a(gVar, cVar, EnumSet.class, l.a.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        com.a.a.c.k<Enum<?>> kVar = this.f356b;
        if (kVar == null) {
            b2 = gVar.a(this.f355a, cVar);
        } else {
            b2 = gVar.b(kVar, cVar, this.f355a);
        }
        return a(b2, b(gVar, cVar, b2), a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public EnumSet<?> a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        EnumSet g = g();
        if (!lVar.p()) {
            return c(lVar, gVar, g);
        }
        return b(lVar, gVar, g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k
    public EnumSet<?> a(com.a.a.b.l lVar, com.a.a.c.g gVar, EnumSet<?> enumSet) {
        if (!lVar.p()) {
            return c(lVar, gVar, enumSet);
        }
        return b(lVar, gVar, enumSet);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v17, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.a.a.b.o] */
    private EnumSet<?> b(com.a.a.b.l lVar, com.a.a.c.g gVar, EnumSet enumSet) {
        ?? r0;
        Enum<?> a2;
        while (true) {
            try {
                r0 = lVar.g();
                if (r0 != com.a.a.b.o.END_ARRAY) {
                    if (r0 == com.a.a.b.o.VALUE_NULL) {
                        r0 = this.d;
                        if (r0 == 0) {
                            a2 = (Enum) this.c.a(gVar);
                        }
                    } else {
                        a2 = this.f356b.a(lVar, gVar);
                    }
                    Enum<?> r02 = a2;
                    r0 = r02;
                    if (r02 != null) {
                        r0 = enumSet.add(a2);
                    }
                } else {
                    return enumSet;
                }
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, enumSet, enumSet.size());
            }
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.b(lVar, gVar);
    }

    private EnumSet g() {
        return EnumSet.noneOf(this.f355a.b());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v5, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.Throwable] */
    private EnumSet<?> c(com.a.a.b.l lVar, com.a.a.c.g gVar, EnumSet enumSet) {
        if (!(this.e == Boolean.TRUE || (this.e == null && gVar.a(com.a.a.c.i.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {
            return (EnumSet) gVar.a(EnumSet.class, lVar);
        }
        ?? a2 = lVar.a(com.a.a.b.o.VALUE_NULL);
        if (a2 != 0) {
            return (EnumSet) gVar.a(this.f355a, lVar);
        }
        try {
            Enum<?> a3 = this.f356b.a(lVar, gVar);
            if (a3 != null) {
                a2 = enumSet.add(a3);
            }
            return enumSet;
        } catch (Exception e) {
            throw com.a.a.c.l.a((Throwable) a2, enumSet, enumSet.size());
        }
    }
}
