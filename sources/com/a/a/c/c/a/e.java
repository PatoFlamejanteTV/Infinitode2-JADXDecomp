package com.a.a.c.c.a;

import com.a.a.c.c.b.aj;
import java.lang.reflect.Member;
import java.util.HashMap;
import net.bytebuddy.implementation.MethodDelegation;

/* loaded from: infinitode-2.jar:com/a/a/c/c/a/e.class */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static String[] f259a = {"default", "from-String", "from-int", "from-long", "from-big-integer", "from-double", "from-big-decimal", "from-boolean", MethodDelegation.ImplementationDelegate.FIELD_NAME_PREFIX, "property-based", "array-delegate"};

    /* renamed from: b, reason: collision with root package name */
    private com.a.a.c.b f260b;
    private boolean c;
    private boolean d;
    private com.a.a.c.f.o[] e = new com.a.a.c.f.o[11];
    private int f = 0;
    private boolean g = false;
    private com.a.a.c.c.v[] h;
    private com.a.a.c.c.v[] i;
    private com.a.a.c.c.v[] j;

    public e(com.a.a.c.b bVar, com.a.a.c.b.q<?> qVar) {
        this.f260b = bVar;
        this.c = qVar.g();
        this.d = qVar.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS);
    }

    public final com.a.a.c.c.x a(com.a.a.c.g gVar) {
        gVar.a();
        com.a.a.c.j a2 = a(gVar, this.e[8], this.h);
        com.a.a.c.j a3 = a(gVar, this.e[10], this.i);
        aj ajVar = new aj(this.f260b.a());
        ajVar.a(this.e[0], this.e[8], a2, this.h, this.e[9], this.j);
        ajVar.a(this.e[10], a3, this.i);
        ajVar.a(this.e[1]);
        ajVar.b(this.e[2]);
        ajVar.c(this.e[3]);
        ajVar.d(this.e[4]);
        ajVar.e(this.e[5]);
        ajVar.f(this.e[6]);
        ajVar.g(this.e[7]);
        return ajVar;
    }

    public final void a(com.a.a.c.f.o oVar) {
        this.e[0] = (com.a.a.c.f.o) a((e) oVar);
    }

    public final void a(com.a.a.c.f.o oVar, boolean z) {
        a(oVar, 1, z);
    }

    public final void b(com.a.a.c.f.o oVar, boolean z) {
        a(oVar, 2, z);
    }

    public final void c(com.a.a.c.f.o oVar, boolean z) {
        a(oVar, 3, z);
    }

    public final void d(com.a.a.c.f.o oVar, boolean z) {
        a(oVar, 4, z);
    }

    public final void e(com.a.a.c.f.o oVar, boolean z) {
        a(oVar, 5, z);
    }

    public final void f(com.a.a.c.f.o oVar, boolean z) {
        a(oVar, 6, z);
    }

    public final void g(com.a.a.c.f.o oVar, boolean z) {
        a(oVar, 7, z);
    }

    public final void a(com.a.a.c.f.o oVar, boolean z, com.a.a.c.c.v[] vVarArr, int i) {
        if (oVar.b(i).o()) {
            if (a(oVar, 10, z)) {
                this.i = vVarArr;
            }
        } else if (a(oVar, 8, z)) {
            this.h = vVarArr;
        }
    }

    public final void a(com.a.a.c.f.o oVar, boolean z, com.a.a.c.c.v[] vVarArr) {
        Integer num;
        if (a(oVar, 9, z)) {
            if (vVarArr.length > 1) {
                HashMap hashMap = new HashMap();
                int length = vVarArr.length;
                for (int i = 0; i < length; i++) {
                    String a2 = vVarArr[i].a();
                    if ((!a2.isEmpty() || vVarArr[i].i() == null) && (num = (Integer) hashMap.put(a2, Integer.valueOf(i))) != null) {
                        throw new IllegalArgumentException(String.format("Duplicate creator property \"%s\" (index %s vs %d) for type %s ", a2, num, Integer.valueOf(i), com.a.a.c.m.i.g(this.f260b.b())));
                    }
                }
            }
            this.j = vVarArr;
        }
    }

    public final boolean a() {
        return this.e[0] != null;
    }

    public final boolean b() {
        return this.e[8] != null;
    }

    public final boolean c() {
        return this.e[9] != null;
    }

    private com.a.a.c.j a(com.a.a.c.g gVar, com.a.a.c.f.o oVar, com.a.a.c.c.v[] vVarArr) {
        if (!this.g || oVar == null) {
            return null;
        }
        int i = 0;
        if (vVarArr != null) {
            int i2 = 0;
            int length = vVarArr.length;
            while (true) {
                if (i2 >= length) {
                    break;
                }
                if (vVarArr[i2] != null) {
                    i2++;
                } else {
                    i = i2;
                    break;
                }
            }
        }
        com.a.a.c.f a2 = gVar.a();
        com.a.a.c.j b2 = oVar.b(i);
        com.a.a.c.a j = a2.j();
        if (j != null) {
            com.a.a.c.f.n c = oVar.c(i);
            Object z = j.z(c);
            if (z != null) {
                b2 = b2.c(gVar.b(c, z));
            } else {
                b2 = j.b((com.a.a.c.b.q<?>) a2, (com.a.a.c.f.b) c, b2);
            }
        }
        return b2;
    }

    private <T extends com.a.a.c.f.j> T a(T t) {
        if (t != null && this.c) {
            com.a.a.c.m.i.a((Member) t.a(), this.d);
        }
        return t;
    }

    private boolean a(com.a.a.c.f.o oVar, int i, boolean z) {
        boolean z2;
        int i2 = 1 << i;
        this.g = true;
        com.a.a.c.f.o oVar2 = this.e[i];
        if (oVar2 != null) {
            if ((this.f & i2) != 0) {
                if (!z) {
                    return false;
                }
                z2 = true;
            } else {
                z2 = !z;
            }
            if (z2 && oVar2.getClass() == oVar.getClass()) {
                Class<?> a2 = oVar2.a(0);
                Class<?> a3 = oVar.a(0);
                if (a2 == a3) {
                    if (b(oVar)) {
                        return false;
                    }
                    if (!b(oVar2)) {
                        a(i, z, oVar2, oVar);
                    }
                } else {
                    if (a3.isAssignableFrom(a2)) {
                        return false;
                    }
                    if (!a2.isAssignableFrom(a3)) {
                        if (a2.isPrimitive() != a3.isPrimitive()) {
                            if (a2.isPrimitive()) {
                                return false;
                            }
                        } else {
                            a(i, z, oVar2, oVar);
                        }
                    }
                }
            }
        }
        if (z) {
            this.f |= i2;
        }
        this.e[i] = (com.a.a.c.f.o) a((e) oVar);
        return true;
    }

    private static void a(int i, boolean z, com.a.a.c.f.o oVar, com.a.a.c.f.o oVar2) {
        Object[] objArr = new Object[4];
        objArr[0] = f259a[i];
        objArr[1] = z ? "explicitly marked" : "implicitly discovered";
        objArr[2] = oVar;
        objArr[3] = oVar2;
        throw new IllegalArgumentException(String.format("Conflicting %s creators: already had %s creator %s, encountered another: %s", objArr));
    }

    private static boolean b(com.a.a.c.f.o oVar) {
        return com.a.a.c.m.i.k(oVar.h()) && "valueOf".equals(oVar.b());
    }
}
