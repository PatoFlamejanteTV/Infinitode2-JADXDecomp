package com.a.a.c.c.b;

import com.a.a.a.l;
import java.lang.reflect.Array;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/z.class */
public final class z extends i<Object[]> implements com.a.a.c.c.k {

    /* renamed from: a, reason: collision with root package name */
    private boolean f394a;
    private Class<?> f;
    private com.a.a.c.k<Object> g;
    private com.a.a.c.i.e h;
    private Object[] i;

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final /* synthetic */ Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return c(lVar, gVar, eVar);
    }

    public z(com.a.a.c.j jVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar) {
        super(jVar, (com.a.a.c.c.s) null, (Boolean) null);
        com.a.a.c.l.a aVar = (com.a.a.c.l.a) jVar;
        this.f = aVar.u().b();
        this.f394a = this.f == Object.class;
        this.g = kVar;
        this.h = eVar;
        this.i = aVar.H();
    }

    private z(z zVar, com.a.a.c.k<Object> kVar, com.a.a.c.i.e eVar, com.a.a.c.c.s sVar, Boolean bool) {
        super(zVar, sVar, bool);
        this.f = zVar.f;
        this.f394a = zVar.f394a;
        this.i = zVar.i;
        this.g = kVar;
        this.h = eVar;
    }

    private z a(com.a.a.c.i.e eVar, com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar, Boolean bool) {
        if (Objects.equals(bool, this.e) && sVar == this.c && kVar == this.g && eVar == this.h) {
            return this;
        }
        return new z(this, kVar, eVar, sVar, bool);
    }

    @Override // com.a.a.c.k
    public final boolean c() {
        return this.g == null && this.h == null;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Array;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.k<?> b2;
        com.a.a.c.k<Object> kVar = this.g;
        Boolean a2 = a(gVar, cVar, this.f344b.b(), l.a.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        com.a.a.c.k<?> a3 = a(gVar, cVar, (com.a.a.c.k<?>) kVar);
        com.a.a.c.j u = this.f344b.u();
        if (a3 == null) {
            b2 = gVar.a(u, cVar);
        } else {
            b2 = gVar.b(a3, cVar, u);
        }
        com.a.a.c.i.e eVar = this.h;
        com.a.a.c.i.e eVar2 = eVar;
        if (eVar != null) {
            eVar2 = eVar2.a(cVar);
        }
        return a(eVar2, b2, b(gVar, cVar, b2), a2);
    }

    @Override // com.a.a.c.c.b.i
    public final com.a.a.c.k<Object> g() {
        return this.g;
    }

    @Override // com.a.a.c.c.b.i, com.a.a.c.k
    public final com.a.a.c.m.a e() {
        return com.a.a.c.m.a.CONSTANT;
    }

    @Override // com.a.a.c.c.b.i, com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [com.a.a.b.o] */
    /* JADX WARN: Type inference failed for: r0v29 */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v36, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v7, types: [com.a.a.c.i.e] */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public Object[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object[] a2;
        Object a3;
        if (!lVar.p()) {
            return f(lVar, gVar);
        }
        com.a.a.c.m.f n = gVar.n();
        Object[] a4 = n.a();
        int i = 0;
        ?? r0 = this.h;
        while (true) {
            try {
                r0 = lVar.g();
                if (r0 == com.a.a.b.o.END_ARRAY) {
                    break;
                }
                if (r0 == com.a.a.b.o.VALUE_NULL) {
                    r0 = this.d;
                    if (r0 == 0) {
                        a3 = this.c.a(gVar);
                    }
                } else if (r0 == 0) {
                    a3 = this.g.a(lVar, gVar);
                } else {
                    a3 = this.g.a(lVar, gVar, (com.a.a.c.i.e) r0);
                }
                if (i >= a4.length) {
                    a4 = n.a(a4);
                    i = 0;
                }
                r0 = a4;
                int i2 = i;
                i++;
                r0[i2] = a3;
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, a4, n.c() + i);
            }
        }
        if (this.f394a) {
            a2 = n.b(a4, i);
        } else {
            a2 = n.a(a4, i, this.f);
        }
        gVar.a(n);
        return a2;
    }

    private static Object[] c(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return (Object[]) eVar.b(lVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.a.a.b.o] */
    /* JADX WARN: Type inference failed for: r0v31 */
    /* JADX WARN: Type inference failed for: r0v33 */
    /* JADX WARN: Type inference failed for: r0v38, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v9, types: [com.a.a.c.i.e] */
    @Override // com.a.a.c.k
    public Object[] a(com.a.a.b.l lVar, com.a.a.c.g gVar, Object[] objArr) {
        Object[] a2;
        Object a3;
        if (!lVar.p()) {
            Object[] f = f(lVar, gVar);
            if (f == null) {
                return objArr;
            }
            int length = objArr.length;
            Object[] objArr2 = new Object[length + f.length];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            System.arraycopy(f, 0, objArr2, length, f.length);
            return objArr2;
        }
        com.a.a.c.m.f n = gVar.n();
        int length2 = objArr.length;
        Object[] a4 = n.a(objArr, length2);
        ?? r0 = this.h;
        while (true) {
            try {
                r0 = lVar.g();
                if (r0 == com.a.a.b.o.END_ARRAY) {
                    break;
                }
                if (r0 == com.a.a.b.o.VALUE_NULL) {
                    r0 = this.d;
                    if (r0 == 0) {
                        a3 = this.c.a(gVar);
                    }
                } else if (r0 == 0) {
                    a3 = this.g.a(lVar, gVar);
                } else {
                    a3 = this.g.a(lVar, gVar, (com.a.a.c.i.e) r0);
                }
                if (length2 >= a4.length) {
                    a4 = n.a(a4);
                    length2 = 0;
                }
                r0 = a4;
                int i = length2;
                length2++;
                r0[i] = a3;
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, a4, n.c() + length2);
            }
        }
        if (this.f394a) {
            a2 = n.b(a4, length2);
        } else {
            a2 = n.a(a4, length2, this.f);
        }
        gVar.a(n);
        return a2;
    }

    private static Byte[] e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        byte[] a2 = lVar.a(gVar.k());
        Byte[] bArr = new Byte[a2.length];
        int length = a2.length;
        for (int i = 0; i < length; i++) {
            bArr[i] = Byte.valueOf(a2[i]);
        }
        return bArr;
    }

    private Object[] f(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        Object a2;
        com.a.a.c.b.b a3;
        Object[] objArr;
        if (!(this.e == Boolean.TRUE || (this.e == null && gVar.a(com.a.a.c.i.ACCEPT_SINGLE_VALUE_AS_ARRAY)))) {
            if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
                if (this.f == Byte.class) {
                    return e(lVar, gVar);
                }
                return m(lVar, gVar);
            }
            return (Object[]) gVar.a(this.f344b, lVar);
        }
        if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
            if (this.d) {
                return this.i;
            }
            a2 = this.c.a(gVar);
        } else {
            if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
                String w = lVar.w();
                if (!w.isEmpty()) {
                    if (h(w) && (a3 = gVar.a(b(), a(), com.a.a.c.b.b.Fail)) != com.a.a.c.b.b.Fail) {
                        return (Object[]) a(gVar, a3, a());
                    }
                } else {
                    com.a.a.c.b.b a4 = gVar.a(b(), a(), com.a.a.c.b.f.EmptyString);
                    if (a4 != com.a.a.c.b.b.Fail) {
                        return (Object[]) a(gVar, a4, a());
                    }
                }
            }
            if (this.h == null) {
                a2 = this.g.a(lVar, gVar);
            } else {
                a2 = this.g.a(lVar, gVar, this.h);
            }
        }
        if (this.f394a) {
            objArr = new Object[1];
        } else {
            objArr = (Object[]) Array.newInstance(this.f, 1);
        }
        objArr[0] = a2;
        return objArr;
    }
}
