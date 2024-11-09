package org.a.b.f;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:org/a/b/f/as.class */
public final class as extends an {
    private Map<Integer, Integer> c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public as(ap apVar) {
        super(apVar);
    }

    @Override // org.a.b.f.an
    public final void a(ap apVar, ak akVar) {
        akVar.h();
        akVar.d();
        int c = akVar.c();
        this.c = new ConcurrentHashMap(c);
        for (int i = 0; i < c; i++) {
            this.c.put(Integer.valueOf(akVar.c()), Integer.valueOf(akVar.d()));
        }
        this.f4283a = true;
    }
}
