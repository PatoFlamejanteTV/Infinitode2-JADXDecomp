package org.a.c.f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.a.c.b.m;
import org.a.c.b.p;

/* loaded from: infinitode-2.jar:org/a/c/f/e.class */
public class e extends a {
    private static final org.a.a.a.a c = org.a.a.a.c.a(e.class);
    private List<m> d;
    private final p e;

    public e(p pVar, org.a.c.b.e eVar) {
        super(new d(pVar.k()));
        this.d = null;
        this.e = pVar;
        this.f4430b = eVar;
    }

    public final void p() {
        try {
            int a2 = this.e.a("N");
            if (a2 != -1) {
                ArrayList arrayList = new ArrayList(a2);
                this.d = new ArrayList(a2);
                for (int i = 0; i < a2; i++) {
                    long m = m();
                    o();
                    arrayList.add(Long.valueOf(m));
                }
                int i2 = 0;
                while (true) {
                    org.a.c.b.b f = f();
                    if (f == null) {
                        break;
                    }
                    m mVar = new m(f);
                    mVar.a(0);
                    if (i2 >= arrayList.size()) {
                        new StringBuilder("/ObjStm (object stream) has more objects than /N ").append(a2);
                        break;
                    }
                    mVar.a(((Long) arrayList.get(i2)).longValue());
                    this.d.add(mVar);
                    if (c.a()) {
                        new StringBuilder("parsed=").append(mVar);
                    }
                    if (!this.f4429a.d() && this.f4429a.c() == 101) {
                        h();
                    }
                    i2++;
                }
                return;
            }
            throw new IOException("/N entry missing in object stream");
        } finally {
            this.f4429a.close();
        }
    }

    public final List<m> q() {
        return this.d;
    }
}
