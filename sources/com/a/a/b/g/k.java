package com.a.a.b.g;

import java.util.ArrayList;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/b/g/k.class */
public final class k extends j {
    private com.a.a.b.l[] d;
    private boolean e;
    private int f;
    private boolean g;

    private k(boolean z, com.a.a.b.l[] lVarArr) {
        super(lVarArr[0]);
        this.e = z;
        this.g = z && this.c.o();
        this.d = lVarArr;
        this.f = 1;
    }

    public static k a(boolean z, com.a.a.b.l lVar, com.a.a.b.l lVar2) {
        if (!(lVar instanceof k) && !(lVar2 instanceof k)) {
            return new k(false, new com.a.a.b.l[]{lVar, lVar2});
        }
        ArrayList arrayList = new ArrayList();
        if (lVar instanceof k) {
            ((k) lVar).a((List<com.a.a.b.l>) arrayList);
        } else {
            arrayList.add(lVar);
        }
        if (lVar2 instanceof k) {
            ((k) lVar2).a((List<com.a.a.b.l>) arrayList);
        } else {
            arrayList.add(lVar2);
        }
        return new k(false, (com.a.a.b.l[]) arrayList.toArray(new com.a.a.b.l[arrayList.size()]));
    }

    private void a(List<com.a.a.b.l> list) {
        int length = this.d.length;
        for (int i = this.f - 1; i < length; i++) {
            com.a.a.b.l lVar = this.d[i];
            if (lVar instanceof k) {
                ((k) lVar).a(list);
            } else {
                list.add(lVar);
            }
        }
    }

    @Override // com.a.a.b.g.j, com.a.a.b.l, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        do {
            this.c.close();
        } while (W());
    }

    @Override // com.a.a.b.g.j, com.a.a.b.l
    public final com.a.a.b.o g() {
        if (this.c == null) {
            return null;
        }
        if (this.g) {
            this.g = false;
            return this.c.k();
        }
        com.a.a.b.o g = this.c.g();
        if (g == null) {
            return X();
        }
        return g;
    }

    @Override // com.a.a.b.g.j, com.a.a.b.l
    public final com.a.a.b.l j() {
        if (this.c.k() != com.a.a.b.o.START_OBJECT && this.c.k() != com.a.a.b.o.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            com.a.a.b.o g = g();
            if (g == null) {
                return this;
            }
            if (g.e()) {
                i++;
            } else if (g.f()) {
                i--;
                if (i == 0) {
                    return this;
                }
            } else {
                continue;
            }
        }
    }

    private boolean W() {
        if (this.f < this.d.length) {
            com.a.a.b.l[] lVarArr = this.d;
            int i = this.f;
            this.f = i + 1;
            this.c = lVarArr[i];
            return true;
        }
        return false;
    }

    private com.a.a.b.o X() {
        while (this.f < this.d.length) {
            com.a.a.b.l[] lVarArr = this.d;
            int i = this.f;
            this.f = i + 1;
            this.c = lVarArr[i];
            if (this.e && this.c.o()) {
                return this.c.m();
            }
            com.a.a.b.o g = this.c.g();
            if (g != null) {
                return g;
            }
        }
        return null;
    }
}
