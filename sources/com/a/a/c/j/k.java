package com.a.a.c.j;

import com.a.a.c.aa;
import com.a.a.c.n;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/j/k.class */
final class k {

    /* renamed from: a, reason: collision with root package name */
    private static final com.a.a.c.h.a f537a;

    /* renamed from: b, reason: collision with root package name */
    private static final com.a.a.c.u f538b;

    static {
        com.a.a.c.h.a aVar = new com.a.a.c.h.a();
        f537a = aVar;
        f538b = aVar.a();
        f537a.a().a();
        f537a.a(com.a.a.c.m.class);
    }

    public static String a(com.a.a.c.j.b bVar) {
        try {
            return f538b.a(b(bVar));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static com.a.a.c.n b(com.a.a.c.j.b bVar) {
        return new b(bVar);
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/j/k$b.class */
    public static class b extends n.a {

        /* renamed from: a, reason: collision with root package name */
        private com.a.a.c.j.b f541a;

        /* renamed from: b, reason: collision with root package name */
        private aa f542b;

        public b(com.a.a.c.j.b bVar) {
            this.f541a = bVar;
        }

        @Override // com.a.a.c.n
        public final void a(com.a.a.b.h hVar, aa aaVar) {
            this.f542b = aaVar;
            a(hVar, this.f541a);
        }

        @Override // com.a.a.c.n
        public final void a(com.a.a.b.h hVar, aa aaVar, com.a.a.c.i.i iVar) {
            a(hVar, aaVar);
        }

        private void a(com.a.a.b.h hVar, com.a.a.c.m mVar) {
            if (mVar instanceof r) {
                mVar.a();
                hVar.d(this);
                a(hVar, new a(), mVar.n());
            } else if (mVar instanceof com.a.a.c.j.a) {
                hVar.a(this, mVar.a());
                a(hVar, new a(), mVar.m());
            } else {
                mVar.a(hVar, this.f542b);
            }
        }

        private void a(com.a.a.b.h hVar, a aVar, Iterator<?> it) {
            com.a.a.c.m mVar;
            Iterator<?> it2 = it;
            while (true) {
                if (it2.hasNext()) {
                    Object next = it2.next();
                    if (next instanceof Map.Entry) {
                        Map.Entry entry = (Map.Entry) next;
                        hVar.a((String) entry.getKey());
                        mVar = (com.a.a.c.m) entry.getValue();
                    } else {
                        mVar = (com.a.a.c.m) next;
                    }
                    if (mVar instanceof r) {
                        aVar.a(it2);
                        it2 = mVar.n();
                        com.a.a.c.m mVar2 = mVar;
                        mVar2.a();
                        hVar.d(mVar2);
                    } else if (mVar instanceof com.a.a.c.j.a) {
                        aVar.a(it2);
                        it2 = mVar.m();
                        com.a.a.c.m mVar3 = mVar;
                        hVar.a(mVar3, mVar3.a());
                    } else {
                        mVar.a(hVar, this.f542b);
                    }
                } else {
                    if (hVar.a().b()) {
                        hVar.h();
                    } else {
                        hVar.j();
                    }
                    Iterator<?> a2 = aVar.a();
                    it2 = a2;
                    if (a2 == null) {
                        return;
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/j/k$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private Iterator<?>[] f539a;

        /* renamed from: b, reason: collision with root package name */
        private int f540b;
        private int c;

        public final void a(Iterator<?> it) {
            if (this.f540b < this.c) {
                Iterator<?>[] itArr = this.f539a;
                int i = this.f540b;
                this.f540b = i + 1;
                itArr[i] = it;
                return;
            }
            if (this.f539a == null) {
                this.c = 10;
                this.f539a = new Iterator[this.c];
            } else {
                this.c += Math.min(4000, Math.max(20, this.c >> 1));
                this.f539a = (Iterator[]) Arrays.copyOf(this.f539a, this.c);
            }
            Iterator<?>[] itArr2 = this.f539a;
            int i2 = this.f540b;
            this.f540b = i2 + 1;
            itArr2[i2] = it;
        }

        public final Iterator<?> a() {
            if (this.f540b == 0) {
                return null;
            }
            Iterator<?>[] itArr = this.f539a;
            int i = this.f540b - 1;
            this.f540b = i;
            return itArr[i];
        }
    }
}
