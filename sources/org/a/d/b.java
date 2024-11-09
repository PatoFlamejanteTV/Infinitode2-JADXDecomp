package org.a.d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.a.d.a.d;
import org.a.d.a.e;
import org.a.d.a.i;
import org.a.d.a.l;
import org.a.d.b.al;
import org.a.d.b.ao;

/* loaded from: infinitode-2.jar:org/a/d/b.class */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private String f4664a;

    /* renamed from: b, reason: collision with root package name */
    private String f4665b;
    private String c;
    private List<l> d;
    private ao e;

    protected b() {
        this("\ufeff", "W5M0MpCehiHzreSzNTczkc9d", c.f4686a, "UTF-8");
    }

    private b(String str, String str2, String str3, String str4) {
        this.f4664a = null;
        this.f4665b = null;
        this.c = "w";
        this.d = new ArrayList();
        this.e = new ao(this);
        this.f4665b = str;
        this.f4664a = str2;
    }

    public static b a() {
        return new b();
    }

    public final ao b() {
        return this.e;
    }

    public final String c() {
        return this.f4665b;
    }

    public final String d() {
        return this.f4664a;
    }

    public final List<l> e() {
        ArrayList arrayList = new ArrayList();
        Iterator<l> it = this.d.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public final String f() {
        return this.c;
    }

    private l a(String str) {
        for (l lVar : this.d) {
            if (lVar.h().equals(str)) {
                return lVar;
            }
        }
        return null;
    }

    private l a(Class<? extends l> cls) {
        return a(((al) cls.getAnnotation(al.class)).a());
    }

    public final d g() {
        d dVar = new d(this);
        dVar.d("");
        a(dVar);
        return dVar;
    }

    public final d h() {
        return (d) a(d.class);
    }

    public final e i() {
        e eVar = new e(this);
        eVar.d("");
        a(eVar);
        return eVar;
    }

    public final org.a.d.a.b j() {
        org.a.d.a.b bVar = new org.a.d.a.b(this);
        bVar.d("");
        a(bVar);
        return bVar;
    }

    public final org.a.d.a.b k() {
        return (org.a.d.a.b) a(org.a.d.a.b.class);
    }

    public final i l() {
        i iVar = new i(this);
        iVar.d("");
        a(iVar);
        return iVar;
    }

    public final org.a.d.a.a m() {
        org.a.d.a.a aVar = new org.a.d.a.a(this);
        aVar.d("");
        a(aVar);
        return aVar;
    }

    private void a(l lVar) {
        this.d.add(lVar);
    }
}
