package com.a.a.c.k.a;

import com.a.a.a.l;
import com.a.a.c.aa;
import com.a.a.c.z;
import java.util.Objects;

@com.a.a.c.a.a
/* loaded from: infinitode-2.jar:com/a/a/c/k/a/o.class */
public final class o extends com.a.a.c.k.b.a<String[]> {

    /* renamed from: a, reason: collision with root package name */
    public static final o f579a;
    private com.a.a.c.o<Object> d;

    @Override // com.a.a.c.k.j
    public final /* synthetic */ boolean a(Object obj) {
        return b((String[]) obj);
    }

    @Override // com.a.a.c.o
    public final /* bridge */ /* synthetic */ boolean a(aa aaVar, Object obj) {
        return a((String[]) obj);
    }

    static {
        com.a.a.c.l.o.a().a(String.class);
        f579a = new o();
    }

    protected o() {
        super(String[].class);
        this.d = null;
    }

    private o(o oVar, com.a.a.c.c cVar, com.a.a.c.o<?> oVar2, Boolean bool) {
        super(oVar, cVar, bool);
        this.d = oVar2;
    }

    @Override // com.a.a.c.k.b.a
    public final com.a.a.c.o<?> a(com.a.a.c.c cVar, Boolean bool) {
        return new o(this, cVar, this.d, bool);
    }

    @Override // com.a.a.c.k.j
    public final com.a.a.c.k.j<?> b(com.a.a.c.i.i iVar) {
        return this;
    }

    @Override // com.a.a.c.k.b.a, com.a.a.c.k.k
    public final com.a.a.c.o<?> a(aa aaVar, com.a.a.c.c cVar) {
        Object p;
        com.a.a.c.o<Object> oVar = null;
        if (cVar != null) {
            com.a.a.c.a d = aaVar.d();
            com.a.a.c.f.j e = cVar.e();
            if (e != null && (p = d.p(e)) != null) {
                oVar = aaVar.b(e, p);
            }
        }
        Boolean a2 = a(aaVar, cVar, String[].class, l.a.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED);
        if (oVar == null) {
            oVar = this.d;
        }
        com.a.a.c.o<?> a3 = a(aaVar, cVar, (com.a.a.c.o<?>) oVar);
        com.a.a.c.o<?> oVar2 = a3;
        if (a3 == null) {
            oVar2 = aaVar.c(String.class, cVar);
        }
        if (a(oVar2)) {
            oVar2 = null;
        }
        if (oVar2 == this.d && Objects.equals(a2, this.c)) {
            return this;
        }
        return new o(this, cVar, oVar2, a2);
    }

    private static boolean a(String[] strArr) {
        return strArr.length == 0;
    }

    private static boolean b(String[] strArr) {
        return strArr.length == 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.a, com.a.a.c.k.b.ao, com.a.a.c.o
    public void a(String[] strArr, com.a.a.b.h hVar, aa aaVar) {
        int length = strArr.length;
        if (length == 1 && ((this.c == null && aaVar.a(z.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this.c == Boolean.TRUE)) {
            b(strArr, hVar, aaVar);
            return;
        }
        hVar.a(strArr, length);
        b(strArr, hVar, aaVar);
        hVar.h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.a.a.c.k.b.a
    public void b(String[] strArr, com.a.a.b.h hVar, aa aaVar) {
        int length = strArr.length;
        if (length == 0) {
            return;
        }
        if (this.d != null) {
            a(strArr, hVar, aaVar, this.d);
            return;
        }
        for (int i = 0; i < length; i++) {
            if (strArr[i] == null) {
                hVar.k();
            } else {
                hVar.b(strArr[i]);
            }
        }
    }

    private static void a(String[] strArr, com.a.a.b.h hVar, aa aaVar, com.a.a.c.o<Object> oVar) {
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            if (strArr[i] == null) {
                aaVar.a(hVar);
            } else {
                oVar.a(strArr[i], hVar, aaVar);
            }
        }
    }
}
