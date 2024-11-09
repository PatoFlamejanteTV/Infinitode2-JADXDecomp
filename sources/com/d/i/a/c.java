package com.d.i.a;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: infinitode-2.jar:com/d/i/a/c.class */
public abstract class c {
    public abstract a a(int i);

    public abstract int a();

    public abstract int b();

    /* loaded from: infinitode-2.jar:com/d/i/a/c$a.class */
    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private List<com.d.d.b> f1287a = null;

        /* renamed from: b, reason: collision with root package name */
        private List<a> f1288b = null;
        private final a c;

        public a(a aVar) {
            this.c = aVar;
        }

        private boolean c() {
            return this.c != null;
        }

        private a d() {
            return this.c;
        }

        public final void a(com.d.d.b bVar) {
            if (this.f1287a == null) {
                this.f1287a = new ArrayList();
            }
            this.f1287a.add(bVar);
        }

        private void b(int i) {
            for (int size = this.f1288b.size(); size <= i; size++) {
                this.f1288b.add(new a(this));
            }
        }

        public final a a(int i) {
            if (c()) {
                return d().a(i);
            }
            if (this.f1288b == null) {
                this.f1288b = new ArrayList();
            }
            b(i);
            return this.f1288b.get(i);
        }

        public final List<a> a() {
            if (c()) {
                return this.c.a();
            }
            return this.f1288b == null ? Collections.emptyList() : this.f1288b;
        }

        public final List<com.d.d.b> b() {
            return this.f1287a == null ? Collections.emptyList() : this.f1287a;
        }
    }
}
