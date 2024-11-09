package com.a.a.c.b;

import java.io.Serializable;

/* loaded from: infinitode-2.jar:com/a/a/c/b/l.class */
public final class l implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private final int f230a;

    /* renamed from: b, reason: collision with root package name */
    private final int f231b;

    protected l(int i, int i2, int i3, int i4) {
        this.f230a = i;
        this.f231b = i3;
    }

    public static l a() {
        return a.a();
    }

    public final boolean a(k kVar) {
        switch (kVar.c()) {
            case 0:
                return kVar.a(this.f230a);
            case 1:
                return kVar.a(this.f231b);
            default:
                com.a.a.b.g.q.a();
                return false;
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/b/l$a.class */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private static final l f232a = new l(a(n.values()), 0, a(o.values()), 0);

        /* JADX WARN: Incorrect types in method signature: <F:Ljava/lang/Enum<TF;>;:Lcom/a/a/b/g/h;>([TF;)I */
        /* JADX WARN: Multi-variable type inference failed */
        private static int a(Enum[] enumArr) {
            int i = 0;
            for (com.a.a.b.d.e eVar : enumArr) {
                if (eVar.a()) {
                    i |= eVar.b();
                }
            }
            return i;
        }

        public static l a() {
            return f232a;
        }
    }
}
