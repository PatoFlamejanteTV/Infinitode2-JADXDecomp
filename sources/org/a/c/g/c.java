package org.a.c.g;

import org.a.c.b.n;

/* loaded from: infinitode-2.jar:org/a/c/g/c.class */
public final class c implements Comparable<c> {

    /* renamed from: a, reason: collision with root package name */
    private long f4452a;

    /* renamed from: b, reason: collision with root package name */
    private n f4453b;
    private boolean c = false;
    private static final c d;

    static {
        c cVar = new c(0L, null, new n(0L, 65535));
        d = cVar;
        cVar.a(true);
    }

    public c(long j, org.a.c.b.b bVar, n nVar) {
        a(j);
        a(nVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(c cVar) {
        if (cVar == null || b().b() < cVar.b().b()) {
            return -1;
        }
        if (b().b() > cVar.b().b()) {
            return 1;
        }
        return 0;
    }

    public static c a() {
        return d;
    }

    public final n b() {
        return this.f4453b;
    }

    public final long c() {
        return this.f4452a;
    }

    public final boolean d() {
        return this.c;
    }

    private void a(boolean z) {
        this.c = true;
    }

    private void a(n nVar) {
        this.f4453b = nVar;
    }

    private void a(long j) {
        this.f4452a = j;
    }
}
