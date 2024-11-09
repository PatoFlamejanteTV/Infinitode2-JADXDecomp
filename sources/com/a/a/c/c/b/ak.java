package com.a.a.c.c.b;

import com.a.a.a.l;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/c/b/ak.class */
public final class ak extends ae<String[]> implements com.a.a.c.c.k {

    /* renamed from: b, reason: collision with root package name */
    private static final String[] f322b = new String[0];

    /* renamed from: a, reason: collision with root package name */
    public static final ak f323a = new ak();
    private com.a.a.c.k<String> c;
    private com.a.a.c.c.s d;
    private Boolean e;
    private boolean f;

    public ak() {
        this(null, null, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ak(com.a.a.c.k<?> kVar, com.a.a.c.c.s sVar, Boolean bool) {
        super((Class<?>) String[].class);
        this.c = kVar;
        this.d = sVar;
        this.e = bool;
        this.f = com.a.a.c.c.a.q.a(sVar);
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.l.f b() {
        return com.a.a.c.l.f.Array;
    }

    @Override // com.a.a.c.k
    public final Boolean a(com.a.a.c.f fVar) {
        return Boolean.TRUE;
    }

    @Override // com.a.a.c.k
    public final com.a.a.c.m.a e() {
        return com.a.a.c.m.a.CONSTANT;
    }

    @Override // com.a.a.c.k
    public final Object c(com.a.a.c.g gVar) {
        return f322b;
    }

    @Override // com.a.a.c.c.k
    public final com.a.a.c.k<?> a(com.a.a.c.g gVar, com.a.a.c.c cVar) {
        com.a.a.c.k<?> b2;
        com.a.a.c.k<?> a2 = a(gVar, cVar, this.c);
        com.a.a.c.j b3 = gVar.b(String.class);
        if (a2 == null) {
            b2 = gVar.a(b3, cVar);
        } else {
            b2 = gVar.b(a2, cVar, b3);
        }
        Boolean a3 = a(gVar, cVar, String[].class, l.a.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        com.a.a.c.c.s b4 = b(gVar, cVar, b2);
        if (b2 != null && a(b2)) {
            b2 = null;
        }
        if (this.c == b2 && Objects.equals(this.e, a3) && this.d == b4) {
            return this;
        }
        return new ak(b2, b4, a3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v30, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    @Override // com.a.a.c.k
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public String[] a(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        if (!lVar.p()) {
            return e(lVar, gVar);
        }
        if (this.c != null) {
            return a(lVar, gVar, (String[]) null);
        }
        com.a.a.c.m.f n = gVar.n();
        Object[] a2 = n.a();
        ?? r0 = 0;
        int i = 0;
        while (true) {
            try {
                r0 = lVar.i();
                String str = r0;
                if (r0 == 0) {
                    com.a.a.b.o k = lVar.k();
                    if (k != com.a.a.b.o.END_ARRAY) {
                        if (k == com.a.a.b.o.VALUE_NULL) {
                            r0 = this.f;
                            if (r0 == 0) {
                                str = (String) this.d.a(gVar);
                            }
                        } else {
                            str = a(lVar, gVar, this.d);
                        }
                    } else {
                        String[] strArr = (String[]) n.a(a2, i, String.class);
                        gVar.a(n);
                        return strArr;
                    }
                }
                if (i >= a2.length) {
                    a2 = n.a(a2);
                    i = 0;
                }
                r0 = a2;
                int i2 = i;
                i++;
                r0[i2] = str;
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, a2, n.c() + i);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v35, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v8, types: [com.a.a.c.k, com.a.a.c.k<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r0v9 */
    private String[] a(com.a.a.b.l lVar, com.a.a.c.g gVar, String[] strArr) {
        int length;
        Object[] a2;
        String str;
        com.a.a.c.m.f n = gVar.n();
        if (strArr == null) {
            length = 0;
            a2 = n.a();
        } else {
            length = strArr.length;
            a2 = n.a(strArr, length);
        }
        ?? r0 = this.c;
        while (true) {
            try {
                r0 = lVar.i();
                if (r0 == 0) {
                    com.a.a.b.o k = lVar.k();
                    if (k != com.a.a.b.o.END_ARRAY) {
                        if (k == com.a.a.b.o.VALUE_NULL) {
                            r0 = this.f;
                            if (r0 == 0) {
                                str = (String) this.d.a(gVar);
                            }
                        } else {
                            str = (String) r0.a(lVar, gVar);
                        }
                    } else {
                        String[] strArr2 = (String[]) n.a(a2, length, String.class);
                        gVar.a(n);
                        return strArr2;
                    }
                } else {
                    str = (String) r0.a(lVar, gVar);
                }
                if (length >= a2.length) {
                    a2 = n.a(a2);
                    length = 0;
                }
                r0 = a2;
                int i = length;
                length++;
                r0[i] = str;
            } catch (Exception e) {
                throw com.a.a.c.l.a((Throwable) r0, String.class, length);
            }
        }
    }

    @Override // com.a.a.c.c.b.ae, com.a.a.c.k
    public final Object a(com.a.a.b.l lVar, com.a.a.c.g gVar, com.a.a.c.i.e eVar) {
        return eVar.b(lVar, gVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v32, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r0v45 */
    /* JADX WARN: Type inference failed for: r0v46 */
    @Override // com.a.a.c.k
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public String[] a(com.a.a.b.l lVar, com.a.a.c.g gVar, String[] strArr) {
        if (!lVar.p()) {
            String[] e = e(lVar, gVar);
            if (e == null) {
                return strArr;
            }
            int length = strArr.length;
            String[] strArr2 = new String[length + e.length];
            System.arraycopy(strArr, 0, strArr2, 0, length);
            System.arraycopy(e, 0, strArr2, length, e.length);
            return strArr2;
        }
        if (this.c != null) {
            return a(lVar, gVar, strArr);
        }
        com.a.a.c.m.f n = gVar.n();
        int length2 = strArr.length;
        Object[] a2 = n.a(strArr, length2);
        Object[] objArr = a2;
        ?? r0 = a2;
        while (true) {
            try {
                String i = lVar.i();
                String str = i;
                if (i == null) {
                    com.a.a.b.o k = lVar.k();
                    if (k != com.a.a.b.o.END_ARRAY) {
                        if (k == com.a.a.b.o.VALUE_NULL) {
                            if (this.f) {
                                r0 = f322b;
                                return r0;
                            }
                            str = (String) this.d.a(gVar);
                        } else {
                            str = a(lVar, gVar, this.d);
                        }
                    } else {
                        String[] strArr3 = (String[]) n.a(objArr, length2, String.class);
                        gVar.a(n);
                        return strArr3;
                    }
                }
                if (length2 >= objArr.length) {
                    objArr = n.a(objArr);
                    length2 = 0;
                }
                Object[] objArr2 = objArr;
                int i2 = length2;
                length2++;
                objArr2[i2] = str;
                r0 = objArr2;
            } catch (Exception e2) {
                throw com.a.a.c.l.a((Throwable) r0, objArr, n.c() + length2);
            }
        }
    }

    private final String[] e(com.a.a.b.l lVar, com.a.a.c.g gVar) {
        String a2;
        com.a.a.c.b.b a3;
        if (this.e == Boolean.TRUE || (this.e == null && gVar.a(com.a.a.c.i.ACCEPT_SINGLE_VALUE_AS_ARRAY))) {
            if (lVar.a(com.a.a.b.o.VALUE_NULL)) {
                a2 = (String) this.d.a(gVar);
            } else {
                if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
                    String w = lVar.w();
                    if (!w.isEmpty()) {
                        if (h(w) && (a3 = gVar.a(b(), a(), com.a.a.c.b.b.Fail)) != com.a.a.c.b.b.Fail) {
                            return (String[]) a(gVar, a3, a());
                        }
                    } else {
                        com.a.a.c.b.b a4 = gVar.a(b(), a(), com.a.a.c.b.f.EmptyString);
                        if (a4 != com.a.a.c.b.b.Fail) {
                            return (String[]) a(gVar, a4, a());
                        }
                    }
                }
                a2 = a(lVar, gVar, this.d);
            }
            return new String[]{a2};
        }
        if (lVar.a(com.a.a.b.o.VALUE_STRING)) {
            return m(lVar, gVar);
        }
        return (String[]) gVar.a(this.s, lVar);
    }
}
