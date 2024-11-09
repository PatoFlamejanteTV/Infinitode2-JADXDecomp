package com.a.a.c.c.b;

import com.a.a.a.l;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/l.class */
public final class l extends ai<Object> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    private Object[] f351a;

    /* renamed from: b, reason: collision with root package name */
    private final Enum<?> f352b;
    private com.a.a.c.m.j c;
    private com.a.a.c.m.j d;
    private Boolean e;
    private boolean f;

    public l(com.a.a.c.m.l lVar, Boolean bool) {
        super(lVar.e());
        this.c = lVar.a();
        this.f351a = lVar.c();
        this.f352b = lVar.b();
        this.e = bool;
        this.f = lVar.f();
    }

    private l(l lVar, Boolean bool) {
        super(lVar);
        this.c = lVar.c;
        this.f351a = lVar.f351a;
        this.f352b = lVar.f352b;
        this.e = bool;
        this.f = lVar.f;
    }

    public static com.a.a.c.k<?> a(com.a.a.c.f fVar, Class<?> cls, com.a.a.c.f.k kVar, com.a.a.c.c.x xVar, com.a.a.c.c.v[] vVarArr) {
        if (fVar.g()) {
            com.a.a.c.m.i.a(kVar.i(), fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new p(cls, kVar, kVar.b(0), xVar, vVarArr);
    }

    public static com.a.a.c.k<?> a(com.a.a.c.f fVar, Class<?> cls, com.a.a.c.f.k kVar) {
        if (fVar.g()) {
            com.a.a.c.m.i.a(kVar.i(), fVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return new p(cls, kVar);
    }

    private l a(Boolean bool) {
        if (Objects.equals(this.e, bool)) {
            return this;
        }
        return new l(this, bool);
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        Boolean a2 = a(gVar, cVar, a(), l.a.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
        Boolean bool = a2;
        if (a2 == null) {
            bool = this.e;
        }
        return a(bool);
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return true;
    }

    @Override // com.a.a.c.c.b.ai, com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Enum;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return this.f352b;
    }

    @Override // com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
            return a(lVar, gVar, lVar.w());
        }
        if (lVar.a(com.a.a.b.o.VALUE_NUMBER_INT)) {
            if (this.f) {
                return a(lVar, gVar, lVar.w());
            }
            return a(gVar, lVar.G());
        }
        if (lVar.q()) {
            return a(lVar, gVar, gVar.a(lVar, this.s));
        }
        return c(lVar, gVar);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x0036, code lost:            if (r0 == null) goto L12;     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.Object a(com.a.a.b.l r6, com.a.a.c.g r7, java.lang.String r8) {
        /*
            r5 = this;
            r0 = r7
            com.a.a.c.i r1 = com.a.a.c.i.READ_ENUMS_USING_TO_STRING
            boolean r0 = r0.a(r1)
            if (r0 == 0) goto L12
            r0 = r5
            r1 = r7
            com.a.a.c.m.j r0 = r0.d(r1)
            goto L16
        L12:
            r0 = r5
            com.a.a.c.m.j r0 = r0.c
        L16:
            r1 = r0
            r6 = r1
            r1 = r8
            java.lang.Object r0 = r0.a(r1)
            r1 = r0
            r9 = r1
            if (r0 != 0) goto L42
            r0 = r8
            java.lang.String r0 = r0.trim()
            r1 = r0
            r10 = r1
            r1 = r8
            if (r0 == r1) goto L39
            r0 = r6
            r1 = r10
            java.lang.Object r0 = r0.a(r1)
            r1 = r0
            r9 = r1
            if (r0 != 0) goto L42
        L39:
            r0 = r5
            r1 = r7
            r2 = r6
            r3 = r10
            java.lang.Object r0 = r0.a(r1, r2, r3)
            return r0
        L42:
            r0 = r9
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.a.c.c.b.l.a(com.a.a.b.l, com.a.a.c.g, java.lang.String):java.lang.Object");
    }

    private Object a(com.a.a.c.g gVar, int i) {
        com.a.a.c.b.b a2 = gVar.a(b(), a(), com.a.a.c.b.f.Integer);
        if (a2 == com.a.a.c.b.b.Fail) {
            if (gVar.a(com.a.a.c.i.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                return gVar.a(g(), (Number) Integer.valueOf(i), "not allowed to deserialize Enum value out of number: disable DeserializationConfig.DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS to allow", new Object[0]);
            }
            a(gVar, a2, a(), Integer.valueOf(i), "Integer value (" + i + ")");
        }
        switch (a2) {
            case AsNull:
                return null;
            case AsEmpty:
                return c(gVar);
            default:
                if (i >= 0 && i < this.f351a.length) {
                    return this.f351a[i];
                }
                if (this.f352b != null && gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
                    return this.f352b;
                }
                if (!gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                    return gVar.a(g(), (Number) Integer.valueOf(i), "index value outside legal index range [0..%s]", Integer.valueOf(this.f351a.length - 1));
                }
                return null;
        }
    }

    private final Object a(com.a.a.c.g gVar, com.a.a.c.m.j jVar, String str) {
        char charAt;
        com.a.a.c.b.b a2;
        String trim = str.trim();
        if (trim.isEmpty()) {
            if (this.f352b != null && gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
                return this.f352b;
            }
            if (gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
                return null;
            }
            if (str.isEmpty()) {
                a2 = a(gVar, g(gVar), a(), str, "empty String (\"\")");
            } else {
                a2 = a(gVar, i(gVar), a(), str, "blank String (all whitespace)");
            }
            switch (a2) {
                case AsEmpty:
                case TryConvert:
                    return c(gVar);
                default:
                    return null;
            }
        }
        if (Boolean.TRUE.equals(this.e)) {
            Object b2 = jVar.b(trim);
            if (b2 != null) {
                return b2;
            }
        } else if (!gVar.a(com.a.a.c.i.FAIL_ON_NUMBERS_FOR_ENUMS) && !this.f && (charAt = trim.charAt(0)) >= '0' && charAt <= '9') {
            try {
                int parseInt = Integer.parseInt(trim);
                if (!gVar.a(com.a.a.c.q.ALLOW_COERCION_OF_SCALARS)) {
                    return gVar.b(g(), trim, "value looks like quoted Enum index, but `MapperFeature.ALLOW_COERCION_OF_SCALARS` prevents use", new Object[0]);
                }
                if (parseInt >= 0 && parseInt < this.f351a.length) {
                    return this.f351a[parseInt];
                }
            } catch (NumberFormatException unused) {
            }
        }
        if (this.f352b != null && gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)) {
            return this.f352b;
        }
        if (gVar.a(com.a.a.c.i.READ_UNKNOWN_ENUM_VALUES_AS_NULL)) {
            return null;
        }
        return gVar.b(g(), trim, "not one of the values accepted for Enum class: %s", jVar.a());
    }

    private Object c(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (lVar.a(com.a.a.b.o.START_ARRAY)) {
            return d(lVar, gVar);
        }
        return gVar.a(g(), lVar);
    }

    private Class<?> g() {
        return a();
    }

    private com.a.a.c.m.j d(com.a.a.c.g gVar) {
        com.a.a.c.m.j jVar = this.d;
        com.a.a.c.m.j jVar2 = jVar;
        if (jVar == null) {
            synchronized (this) {
                jVar2 = com.a.a.c.m.l.b(gVar.a(), g()).a();
            }
            this.d = jVar2;
        }
        return jVar2;
    }
}
