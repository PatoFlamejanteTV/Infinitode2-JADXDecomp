package org.a.c.f;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.a.c.b.n;

/* loaded from: infinitode-2.jar:org/a/c/f/l.class */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private final Map<Long, b> f4442a = new HashMap();

    /* renamed from: b, reason: collision with root package name */
    private b f4443b = null;
    private b c = null;
    private static final org.a.a.a.a d = org.a.a.a.c.a(l.class);

    /* loaded from: infinitode-2.jar:org/a/c/f/l$a.class */
    public enum a {
        TABLE,
        STREAM
    }

    /* loaded from: infinitode-2.jar:org/a/c/f/l$b.class */
    static class b {

        /* renamed from: a, reason: collision with root package name */
        protected org.a.c.b.d f4446a;

        /* renamed from: b, reason: collision with root package name */
        private a f4447b;
        private final Map<n, Long> c;

        /* synthetic */ b(byte b2) {
            this();
        }

        private b() {
            this.f4446a = null;
            this.c = new HashMap();
            this.f4447b = a.TABLE;
        }

        public final void a() {
            this.c.clear();
        }
    }

    public final void a(long j, a aVar) {
        Map<Long, b> map = this.f4442a;
        Long valueOf = Long.valueOf(j);
        b bVar = new b((byte) 0);
        this.f4443b = bVar;
        map.put(valueOf, bVar);
        this.f4443b.f4447b = aVar;
    }

    public final a a() {
        if (this.c == null) {
            return null;
        }
        return this.c.f4447b;
    }

    public final void a(n nVar, long j) {
        if (this.f4443b != null) {
            if (!this.f4443b.c.containsKey(nVar)) {
                this.f4443b.c.put(nVar, Long.valueOf(j));
                return;
            }
            return;
        }
        new StringBuilder("Cannot add XRef entry for '").append(nVar.b()).append("' because XRef start was not signalled.");
    }

    public final void a(org.a.c.b.d dVar) {
        if (this.f4443b == null) {
            return;
        }
        this.f4443b.f4446a = dVar;
    }

    public final org.a.c.b.d b() {
        return this.f4443b.f4446a;
    }

    public final void a(long j) {
        if (this.c != null) {
            return;
        }
        this.c = new b((byte) 0);
        this.c.f4446a = new org.a.c.b.d();
        b bVar = this.f4442a.get(Long.valueOf(j));
        ArrayList arrayList = new ArrayList();
        if (bVar == null) {
            new StringBuilder("Did not found XRef object at specified startxref position ").append(j);
            arrayList.addAll(this.f4442a.keySet());
            Collections.sort(arrayList);
        } else {
            this.c.f4447b = bVar.f4447b;
            arrayList.add(Long.valueOf(j));
            while (true) {
                if (bVar.f4446a == null) {
                    break;
                }
                long b2 = bVar.f4446a.b(org.a.c.b.j.cU, -1L);
                if (b2 == -1) {
                    break;
                }
                b bVar2 = this.f4442a.get(Long.valueOf(b2));
                bVar = bVar2;
                if (bVar2 == null) {
                    new StringBuilder("Did not found XRef object pointed to by 'Prev' key at position ").append(b2);
                    break;
                } else {
                    arrayList.add(Long.valueOf(b2));
                    if (arrayList.size() >= this.f4442a.size()) {
                        break;
                    }
                }
            }
            Collections.reverse(arrayList);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            b bVar3 = this.f4442a.get((Long) it.next());
            if (bVar3.f4446a != null) {
                this.c.f4446a.a(bVar3.f4446a);
            }
            this.c.c.putAll(bVar3.c);
        }
    }

    public final org.a.c.b.d c() {
        if (this.c == null) {
            return null;
        }
        return this.c.f4446a;
    }

    public final Map<n, Long> d() {
        if (this.c == null) {
            return null;
        }
        return this.c.c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void e() {
        Iterator<b> it = this.f4442a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f4443b = null;
        this.c = null;
    }
}
