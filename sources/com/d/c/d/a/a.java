package com.d.c.d.a;

import com.d.i.v;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/c/d/a/a.class */
public abstract class a implements m {
    @Override // com.d.c.d.a.m
    public final List a(com.d.c.a.a aVar, List list, int i, boolean z) {
        return a(aVar, list, i, z, true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(com.d.c.a.a aVar, int i, int i2) {
        if (i != i2) {
            throw new com.d.c.d.b("Found " + i2 + " value(s) for " + aVar + " when " + i + " value(s) were expected", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(com.d.c.a.a aVar, int i, int i2, int i3) {
        if (i3 < i || i3 > i2) {
            throw new com.d.c.d.b("Found " + i3 + " value(s) for " + aVar + " when between " + i + " and " + i2 + " value(s) were expected", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        if (dVar.a() != 21) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void b(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 != 21 && a2 != 20) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier or a URI", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void c(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 != 21 && a2 != 25) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier or a color", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void d(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if ((a2 != 21 && a2 != 1) || (a2 == 1 && ((int) dVar.b()) != Math.round(dVar.b()))) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier or an integer", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void e(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 != 1 || (a2 == 1 && ((int) dVar.b()) != Math.round(dVar.b()))) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an integer", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void f(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        if (dVar.a() != 21 && !a(dVar)) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier or a length", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void g(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 != 21 && a2 != 1) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier or a length", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void h(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 != 21 && !a(dVar) && a2 != 2) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier, length, or percentage", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void i(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (!a(dVar) && a2 != 2) {
            throw new com.d.c.d.b("Value for " + aVar + " must be a length or percentage", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void j(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        if (!a(dVar)) {
            throw new com.d.c.d.b("Value for " + aVar + " must be a length", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void k(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        if (dVar.a() != 1) {
            throw new com.d.c.d.b("Value for " + aVar + " must be a number", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void l(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        if (dVar.a() != 11 && dVar.a() != 12 && dVar.a() != 13) {
            throw new com.d.c.d.b("Value for " + aVar + "must be an angle (degrees, radians or grads)", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void m(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 != 19 && a2 != 21) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier or string", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void n(com.d.c.a.a aVar, com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 != 21 && !a(dVar) && a2 != 2 && a2 != 1) {
            throw new com.d.c.d.b("Value for " + aVar + " must be an identifier, length, or percentage", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean a(com.d.c.d.d dVar) {
        short a2 = dVar.a();
        if (a2 == 3 || a2 == 4 || a2 == 5 || a2 == 8 || a2 == 6 || a2 == 7 || a2 == 9 || a2 == 10) {
            return true;
        }
        return a2 == 1 && dVar.b() == 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(com.d.c.a.a aVar, BitSet bitSet, com.d.c.a.c cVar) {
        if (!bitSet.get(cVar.f968a)) {
            throw new com.d.c.d.b("Ident " + cVar + " is an invalid or unsupported value for " + aVar, -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static com.d.c.a.c b(com.d.c.d.d dVar) {
        com.d.c.a.c b2 = com.d.c.a.c.b(dVar.c());
        if (b2 != null) {
            ((com.d.c.d.j) dVar).a(b2);
            return b2;
        }
        throw new com.d.c.d.b("Value " + dVar.c() + " is not a recognized identifier", -1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static v a(v vVar, com.d.c.a.a aVar) {
        return new v(aVar, vVar.e(), vVar.f(), vVar.g());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void a(com.d.c.d.d dVar, boolean z) {
        if (dVar.e() == 0 && !z) {
            throw new com.d.c.d.b("Invalid use of inherit", -1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List a(com.d.c.a.a[] aVarArr, List list, int i, boolean z, boolean z2) {
        if (list.size() == 1) {
            com.d.c.d.d dVar = (com.d.c.d.d) list.get(0);
            a(dVar, z2);
            if (dVar.e() == 0) {
                ArrayList arrayList = new ArrayList(aVarArr.length);
                for (com.d.c.a.a aVar : aVarArr) {
                    arrayList.add(new v(aVar, dVar, z, i));
                }
                return arrayList;
            }
            return null;
        }
        return null;
    }
}
