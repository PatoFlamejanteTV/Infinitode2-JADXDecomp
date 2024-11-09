package org.a.b.f;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* loaded from: infinitode-2.jar:org/a/b/f/ao.class */
public final class ao implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private final ak f4285a;

    /* renamed from: b, reason: collision with root package name */
    private final int f4286b;
    private final long[] c;

    /* loaded from: infinitode-2.jar:org/a/b/f/ao$a.class */
    public interface a {
        void a(ap apVar);
    }

    public ao(File file) {
        this(new ah(file, "r"));
    }

    private ao(ak akVar) {
        this.f4285a = akVar;
        if (!akVar.m().equals("ttcf")) {
            throw new IOException("Missing TTC header");
        }
        float h = akVar.h();
        this.f4286b = (int) akVar.k();
        this.c = new long[this.f4286b];
        for (int i = 0; i < this.f4286b; i++) {
            this.c[i] = akVar.k();
        }
        if (h >= 2.0f) {
            akVar.c();
            akVar.c();
            akVar.c();
        }
    }

    public final void a(a aVar) {
        for (int i = 0; i < this.f4286b; i++) {
            aVar.a(a(i));
        }
    }

    private ap a(int i) {
        al alVar;
        this.f4285a.a(this.c[i]);
        if (this.f4285a.m().equals("OTTO")) {
            alVar = new ab(false, true);
        } else {
            alVar = new al(false, true);
        }
        this.f4285a.a(this.c[i]);
        return alVar.b(new aj(this.f4285a));
    }

    public final ap a(String str) {
        for (int i = 0; i < this.f4286b; i++) {
            ap a2 = a(i);
            if (a2.b().equals(str)) {
                return a2;
            }
        }
        return null;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.f4285a.close();
    }
}
