package com.a.a.c.c.a;

import com.a.a.c.c.a.x;
import java.util.BitSet;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/y.class */
public final class y {

    /* renamed from: a, reason: collision with root package name */
    private com.a.a.b.l f289a;

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.g f290b;
    private s c;
    private Object[] d;
    private int e;
    private int f;
    private BitSet g;
    private x h;
    private Object i;

    public y(com.a.a.b.l lVar, com.a.a.c.g gVar, int i, s sVar) {
        this.f289a = lVar;
        this.f290b = gVar;
        this.e = i;
        this.c = sVar;
        this.d = new Object[i];
        if (i < 32) {
            this.g = null;
        } else {
            this.g = new BitSet();
        }
    }

    public final Object[] a(com.a.a.c.c.v[] vVarArr) {
        if (this.e > 0) {
            if (this.g == null) {
                int i = this.f;
                int i2 = 0;
                int length = this.d.length;
                while (i2 < length) {
                    if ((i & 1) == 0) {
                        this.d[i2] = a(vVarArr[i2]);
                    }
                    i2++;
                    i >>= 1;
                }
            } else {
                int length2 = this.d.length;
                int i3 = 0;
                while (true) {
                    int nextClearBit = this.g.nextClearBit(i3);
                    if (nextClearBit >= length2) {
                        break;
                    }
                    this.d[nextClearBit] = a(vVarArr[nextClearBit]);
                    i3 = nextClearBit + 1;
                }
            }
        }
        if (this.f290b.a(com.a.a.c.i.FAIL_ON_NULL_CREATOR_PROPERTIES)) {
            for (int i4 = 0; i4 < vVarArr.length; i4++) {
                if (this.d[i4] == null) {
                    com.a.a.c.c.v vVar = vVarArr[i4];
                    this.f290b.a(vVar, "Null value for creator property '%s' (index %d); `DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES` enabled", vVar.a(), Integer.valueOf(vVarArr[i4].h()));
                }
            }
        }
        return this.d;
    }

    private Object a(com.a.a.c.c.v vVar) {
        if (vVar.i() != null) {
            return this.f290b.a(vVar.i(), vVar, (Object) null);
        }
        if (vVar.t()) {
            this.f290b.a(vVar, "Missing required creator property '%s' (index %d)", vVar.a(), Integer.valueOf(vVar.h()));
        }
        if (this.f290b.a(com.a.a.c.i.FAIL_ON_MISSING_CREATOR_PROPERTIES)) {
            this.f290b.a(vVar, "Missing creator property '%s' (index %d); `DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES` enabled", vVar.a(), Integer.valueOf(vVar.h()));
        }
        try {
            Object b2 = vVar.r().b(this.f290b);
            if (b2 != null) {
                return b2;
            }
            return vVar.p().b(this.f290b);
        } catch (com.a.a.c.e e) {
            com.a.a.c.f.j e2 = vVar.e();
            if (e2 != null) {
                e.a(e2.h(), vVar.a());
            }
            throw e;
        }
    }

    public final boolean a(String str) {
        if (this.c != null && str.equals(this.c.f280a.b())) {
            this.i = this.c.a(this.f289a, this.f290b);
            return true;
        }
        return false;
    }

    public final Object a(com.a.a.c.g gVar, Object obj) {
        if (this.c != null) {
            if (this.i != null) {
                gVar.a(this.i, this.c.f281b, this.c.c).a(obj);
                com.a.a.c.c.v vVar = this.c.d;
                if (vVar != null) {
                    return vVar.b(obj, this.i);
                }
            } else {
                gVar.a(this.c, obj);
            }
        }
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final x a() {
        return this.h;
    }

    public final boolean a(com.a.a.c.c.v vVar, Object obj) {
        int h = vVar.h();
        this.d[h] = obj;
        if (this.g == null) {
            int i = this.f;
            int i2 = i | (1 << h);
            if (i != i2) {
                this.f = i2;
                int i3 = this.e - 1;
                this.e = i3;
                if (i3 <= 0) {
                    return this.c == null || this.i != null;
                }
                return false;
            }
            return false;
        }
        if (!this.g.get(h)) {
            this.g.set(h);
            this.e--;
            return false;
        }
        return false;
    }

    public final void b(com.a.a.c.c.v vVar, Object obj) {
        this.h = new x.c(this.h, obj, vVar);
    }

    public final void a(com.a.a.c.c.u uVar, String str, Object obj) {
        this.h = new x.a(this.h, obj, uVar, str);
    }

    public final void a(Object obj, Object obj2) {
        this.h = new x.b(this.h, obj2, obj);
    }
}
