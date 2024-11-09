package com.a.a.c.b;

import java.io.Serializable;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/b/d.class */
public final class d implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private b f214a;

    /* renamed from: b, reason: collision with root package name */
    private s f215b;
    private s[] c;
    private Map<Class<?>, s> d;

    static {
        com.a.a.c.l.f.values();
    }

    public d() {
        this(b.TryConvert, new s(), null, null);
    }

    private d(b bVar, s sVar, s[] sVarArr, Map<Class<?>, s> map) {
        this.f215b = sVar;
        this.f214a = bVar;
        this.c = sVarArr;
        this.d = map;
    }

    public final b a(com.a.a.c.f fVar, com.a.a.c.l.f fVar2, Class<?> cls, f fVar3) {
        s sVar;
        b a2;
        s sVar2;
        b a3;
        if (this.d != null && cls != null && (sVar2 = this.d.get(cls)) != null && (a3 = sVar2.a(fVar3)) != null) {
            return a3;
        }
        if (this.c != null && fVar2 != null && (sVar = this.c[fVar2.ordinal()]) != null && (a2 = sVar.a(fVar3)) != null) {
            return a2;
        }
        b a4 = this.f215b.a(fVar3);
        if (a4 != null) {
            return a4;
        }
        switch (e.f216a[fVar3.ordinal()]) {
            case 1:
                return fVar.a(com.a.a.c.i.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT) ? b.AsNull : b.Fail;
            case 2:
                if (fVar2 == com.a.a.c.l.f.Integer) {
                    return fVar.a(com.a.a.c.i.ACCEPT_FLOAT_AS_INT) ? b.TryConvert : b.Fail;
                }
                break;
            case 3:
                if (fVar2 == com.a.a.c.l.f.Enum && fVar.a(com.a.a.c.i.FAIL_ON_NUMBERS_FOR_ENUMS)) {
                    return b.Fail;
                }
                break;
        }
        boolean a5 = a(fVar2);
        if (a5 && !fVar.a(com.a.a.c.q.ALLOW_COERCION_OF_SCALARS) && (fVar2 != com.a.a.c.l.f.Float || fVar3 != f.Integer)) {
            return b.Fail;
        }
        if (fVar3 == f.EmptyString) {
            if (a5 || fVar.a(com.a.a.c.i.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
                return b.AsNull;
            }
            if (fVar2 == com.a.a.c.l.f.OtherScalar) {
                return b.TryConvert;
            }
            return b.Fail;
        }
        return this.f214a;
    }

    public final b a(com.a.a.c.f fVar, com.a.a.c.l.f fVar2, Class<?> cls, b bVar) {
        s sVar;
        s sVar2;
        Boolean bool = null;
        b bVar2 = null;
        if (this.d != null && cls != null && (sVar2 = this.d.get(cls)) != null) {
            bool = sVar2.a();
            bVar2 = sVar2.a(f.EmptyString);
        }
        if (this.c != null && fVar2 != null && (sVar = this.c[fVar2.ordinal()]) != null) {
            if (bool == null) {
                bool = sVar.a();
            }
            if (bVar2 == null) {
                bVar2 = sVar.a(f.EmptyString);
            }
        }
        if (bool == null) {
            bool = this.f215b.a();
        }
        if (bVar2 == null) {
            bVar2 = this.f215b.a(f.EmptyString);
        }
        if (Boolean.FALSE.equals(bool)) {
            return bVar;
        }
        if (bVar2 != null) {
            return bVar2;
        }
        if (a(fVar2)) {
            return b.AsNull;
        }
        if (fVar.a(com.a.a.c.i.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)) {
            return b.AsNull;
        }
        return bVar;
    }

    private static boolean a(com.a.a.c.l.f fVar) {
        return fVar == com.a.a.c.l.f.Float || fVar == com.a.a.c.l.f.Integer || fVar == com.a.a.c.l.f.Boolean || fVar == com.a.a.c.l.f.DateTime;
    }
}
