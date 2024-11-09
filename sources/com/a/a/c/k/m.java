package com.a.a.c.k;

import com.a.a.a.s;
import com.a.a.c.a.f;
import com.a.a.c.aa;
import com.a.a.c.y;
import com.a.a.c.z;

/* loaded from: infinitode-2.jar:com/a/a/c/k/m.class */
public final class m {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f647a = Boolean.FALSE;

    /* renamed from: b, reason: collision with root package name */
    private y f648b;
    private com.a.a.c.b c;
    private com.a.a.c.a d;
    private Object e;
    private s.b f;
    private boolean g;

    public m(y yVar, com.a.a.c.b bVar) {
        this.f648b = yVar;
        this.c = bVar;
        s.b a2 = s.b.a(bVar.a(s.b.a()), yVar.a(bVar.b(), s.b.a()));
        this.f = s.b.a(yVar.A(), a2);
        this.g = a2.b() == s.a.NON_DEFAULT;
        this.d = this.f648b.j();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v75 */
    /* JADX WARN: Type inference failed for: r0v76, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r0v79, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v91 */
    /* JADX WARN: Type inference failed for: r0v92 */
    public final e a(aa aaVar, com.a.a.c.f.s sVar, com.a.a.c.j jVar, com.a.a.c.o<?> oVar, com.a.a.c.i.i iVar, com.a.a.c.i.i iVar2, com.a.a.c.f.j jVar2, boolean z) {
        Object a2;
        try {
            com.a.a.c.j a3 = a(jVar2, z, jVar);
            if (iVar2 != null) {
                if (a3 == null) {
                    a3 = jVar;
                }
                if (a3.u() == null) {
                    aaVar.a(this.c, sVar, "serialization type " + a3 + " has no content", new Object[0]);
                }
                com.a.a.c.j b2 = a3.b(iVar2);
                a3 = b2;
                b2.u();
            }
            Object obj = null;
            boolean z2 = false;
            com.a.a.c.j jVar3 = a3 == null ? jVar : a3;
            com.a.a.c.f.j s = sVar.s();
            if (s == null) {
                return (e) aaVar.a(this.c, sVar, "could not determine property type", new Object[0]);
            }
            s.b a4 = this.f648b.a(jVar3.b(), s.d(), this.f).a(sVar.B());
            s.a b3 = a4.b();
            s.a aVar = b3;
            if (b3 == s.a.USE_DEFAULTS) {
                aVar = s.a.ALWAYS;
            }
            switch (aVar) {
                case NON_DEFAULT:
                    if (this.g && (a2 = a()) != null) {
                        boolean a5 = aaVar.a(com.a.a.c.q.CAN_OVERRIDE_ACCESS_MODIFIERS);
                        ?? r0 = a5;
                        if (a5) {
                            com.a.a.c.f.j jVar4 = jVar2;
                            jVar4.a(this.f648b.a(com.a.a.c.q.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                            r0 = jVar4;
                        }
                        try {
                            r0 = jVar2.b(a2);
                            obj = r0;
                        } catch (Exception e) {
                            a((Exception) r0, sVar.a(), a2);
                        }
                    } else {
                        obj = com.a.a.c.m.f.a(jVar3);
                        z2 = true;
                    }
                    if (obj == null) {
                        z2 = true;
                        break;
                    } else if (obj.getClass().isArray()) {
                        obj = com.a.a.c.m.c.a(obj);
                        break;
                    }
                    break;
                case NON_ABSENT:
                    z2 = true;
                    if (jVar3.F()) {
                        obj = e.f642b;
                        break;
                    }
                    break;
                case NON_EMPTY:
                    z2 = true;
                    obj = e.f642b;
                    break;
                case CUSTOM:
                    obj = aaVar.a(sVar, a4.d());
                    break;
                case NON_NULL:
                    z2 = true;
                default:
                    z zVar = z.WRITE_EMPTY_JSON_ARRAYS;
                    if (jVar3.n() && !this.f648b.a(zVar)) {
                        obj = e.f642b;
                        break;
                    }
                    break;
            }
            Class<?>[] w = sVar.w();
            Class<?>[] clsArr = w;
            if (w == null) {
                clsArr = this.c.y();
            }
            e a6 = a(sVar, jVar2, this.c.g(), jVar, oVar, iVar, a3, z2, obj, clsArr);
            Object q = this.d.q(jVar2);
            if (q != null) {
                a6.b(aaVar.b(jVar2, q));
            }
            com.a.a.c.m.r c = this.d.c(jVar2);
            if (c != null) {
                a6 = a6.b(c);
            }
            return a6;
        } catch (com.a.a.c.l e2) {
            if (sVar == null) {
                return (e) aaVar.a(jVar, com.a.a.c.m.i.g(e2));
            }
            return (e) aaVar.a(this.c, sVar, com.a.a.c.m.i.g(e2), new Object[0]);
        }
    }

    private static e a(com.a.a.c.f.s sVar, com.a.a.c.f.j jVar, com.a.a.c.m.b bVar, com.a.a.c.j jVar2, com.a.a.c.o<?> oVar, com.a.a.c.i.i iVar, com.a.a.c.j jVar3, boolean z, Object obj, Class<?>[] clsArr) {
        return new e(sVar, jVar, bVar, jVar2, oVar, iVar, jVar3, z, obj, clsArr);
    }

    private com.a.a.c.j a(com.a.a.c.f.b bVar, boolean z, com.a.a.c.j jVar) {
        com.a.a.c.j a2 = this.d.a(this.f648b, bVar, jVar);
        if (a2 != jVar) {
            Class<?> b2 = a2.b();
            Class<?> b3 = jVar.b();
            if (!b2.isAssignableFrom(b3) && !b3.isAssignableFrom(b2)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + bVar.b() + "': class " + b2.getName() + " not a super-type of (declared) class " + b3.getName());
            }
            z = true;
            jVar = a2;
        }
        f.b r = this.d.r(bVar);
        if (r != null && r != f.b.DEFAULT_TYPING) {
            z = r == f.b.STATIC;
        }
        if (z) {
            return jVar.a();
        }
        return null;
    }

    private Object a() {
        Object obj = this.e;
        Object obj2 = obj;
        if (obj == null) {
            Object a2 = this.c.a(this.f648b.g());
            obj2 = a2;
            if (a2 == null) {
                obj2 = f647a;
            }
            this.e = obj2;
        }
        if (obj2 == f647a) {
            return null;
        }
        return this.e;
    }

    private static Object a(Exception exc, String str, Object obj) {
        Throwable th = exc;
        while (true) {
            Throwable th2 = th;
            if (th2.getCause() != null) {
                th = th2.getCause();
            } else {
                com.a.a.c.m.i.a(th2);
                com.a.a.c.m.i.b(th2);
                throw new IllegalArgumentException("Failed to get property '" + str + "' of default " + obj.getClass().getName() + " instance");
            }
        }
    }
}
