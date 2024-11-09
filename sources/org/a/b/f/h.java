package org.a.b.f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/f/h.class */
public class h extends i {

    /* renamed from: a, reason: collision with root package name */
    private static final org.a.a.a.a f4297a = org.a.a.a.c.a(h.class);

    /* renamed from: b, reason: collision with root package name */
    private final List<e> f4298b;
    private final Map<Integer, l> c;
    private p d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;

    public h(ak akVar, p pVar) {
        super((short) -1);
        e eVar;
        this.f4298b = new ArrayList();
        this.c = new HashMap();
        this.d = null;
        this.e = false;
        this.f = false;
        this.g = -1;
        this.h = -1;
        this.d = pVar;
        do {
            eVar = new e(akVar);
            this.f4298b.add(eVar);
        } while ((eVar.c() & 32) != 0);
        if ((eVar.c() & 256) != 0) {
            a(akVar, akVar.c());
        }
        e();
    }

    @Override // org.a.b.f.i, org.a.b.f.l
    public final void a() {
        if (this.f || this.e) {
            return;
        }
        this.e = true;
        int i = 0;
        int i2 = 0;
        for (e eVar : this.f4298b) {
            eVar.a(i);
            eVar.b(i2);
            l lVar = this.c.get(Integer.valueOf(eVar.d()));
            if (lVar != null) {
                lVar.a();
                i += lVar.c();
                i2 += lVar.d();
            }
        }
        this.f = true;
        this.e = false;
    }

    @Override // org.a.b.f.l
    public final int a(int i) {
        e f = f(i);
        if (f != null) {
            return this.c.get(Integer.valueOf(f.d())).a(i - f.b()) + f.a();
        }
        return 0;
    }

    @Override // org.a.b.f.l
    public final byte b(int i) {
        e e = e(i);
        if (e != null) {
            return this.c.get(Integer.valueOf(e.d())).b(i - e.a());
        }
        return (byte) 0;
    }

    @Override // org.a.b.f.l
    public final short c(int i) {
        e e = e(i);
        if (e != null) {
            l lVar = this.c.get(Integer.valueOf(e.d()));
            int a2 = i - e.a();
            return (short) (((short) e.a(lVar.c(a2), lVar.d(a2))) + e.e());
        }
        return (short) 0;
    }

    @Override // org.a.b.f.l
    public final short d(int i) {
        e e = e(i);
        if (e != null) {
            l lVar = this.c.get(Integer.valueOf(e.d()));
            int a2 = i - e.a();
            return (short) (((short) e.b(lVar.c(a2), lVar.d(a2))) + e.f());
        }
        return (short) 0;
    }

    @Override // org.a.b.f.l
    public final boolean b() {
        return true;
    }

    @Override // org.a.b.f.l
    public final int c() {
        if (this.g < 0) {
            e eVar = this.f4298b.get(this.f4298b.size() - 1);
            l lVar = this.c.get(Integer.valueOf(eVar.d()));
            if (lVar == null) {
                new StringBuilder("GlyphDescription for index ").append(eVar.d()).append(" is null, returning 0");
                this.g = 0;
            } else {
                this.g = eVar.a() + lVar.c();
            }
        }
        return this.g;
    }

    @Override // org.a.b.f.i, org.a.b.f.l
    public final int d() {
        if (this.h < 0) {
            e eVar = this.f4298b.get(this.f4298b.size() - 1);
            this.h = eVar.b() + this.c.get(Integer.valueOf(eVar.d())).d();
        }
        return this.h;
    }

    private e e(int i) {
        for (e eVar : this.f4298b) {
            l lVar = this.c.get(Integer.valueOf(eVar.d()));
            if (eVar.a() <= i && lVar != null && i < eVar.a() + lVar.c()) {
                return eVar;
            }
        }
        return null;
    }

    private e f(int i) {
        for (e eVar : this.f4298b) {
            l lVar = this.c.get(Integer.valueOf(eVar.d()));
            if (eVar.b() <= i && lVar != null && i < eVar.b() + lVar.d()) {
                return eVar;
            }
        }
        return null;
    }

    private void e() {
        Iterator<e> it = this.f4298b.iterator();
        while (it.hasNext()) {
            try {
                int d = it.next().d();
                k a2 = this.d.a(d);
                if (a2 != null) {
                    this.c.put(Integer.valueOf(d), a2.a());
                }
            } catch (IOException unused) {
            }
        }
    }
}
