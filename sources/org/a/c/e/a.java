package org.a.c.e;

import java.awt.geom.AffineTransform;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.a.a.a.c;
import org.a.c.b.d;
import org.a.c.b.j;
import org.a.c.h.a.h;
import org.a.c.h.a.i;
import org.a.c.h.e;
import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/e/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final org.a.c.h.b f4425a;

    /* renamed from: b, reason: collision with root package name */
    private final b f4426b;
    private static final Set<String> c;

    static {
        c.a(a.class);
        c = new HashSet(Arrays.asList("Group", "LastModified", "Metadata"));
    }

    public a(org.a.c.h.b bVar) {
        this.f4425a = bVar;
        this.f4426b = new b(bVar);
    }

    public final org.a.c.h.f.b.a a(org.a.c.h.b bVar, e eVar) {
        a(bVar);
        org.a.c.h.f.b.a aVar = new org.a.c.h.f.b.a(new i(this.f4425a, eVar.a(), j.bc));
        org.a.c.h.j d = eVar.d();
        org.a.c.h.j jVar = new org.a.c.h.j();
        this.f4426b.a(d, jVar);
        aVar.a(jVar);
        a(eVar.f(), aVar.f(), c, true);
        AffineTransform a2 = aVar.h().a();
        h e = eVar.e();
        h g = eVar.g();
        h hVar = g != null ? g : e;
        int h = eVar.h();
        a2.translate(e.c() - hVar.c(), e.d() - hVar.d());
        switch (h) {
            case 90:
                a2.scale(hVar.h() / hVar.i(), hVar.i() / hVar.h());
                a2.translate(0.0d, hVar.h());
                a2.rotate(-1.5707963267948966d);
                break;
            case 180:
                a2.translate(hVar.h(), hVar.i());
                a2.rotate(-3.141592653589793d);
                break;
            case User32.WM_IME_ENDCOMPOSITION /* 270 */:
                a2.scale(hVar.h() / hVar.i(), hVar.i() / hVar.h());
                a2.translate(hVar.i(), 0.0d);
                a2.rotate(-4.71238898038469d);
                break;
        }
        a2.translate(-hVar.c(), -hVar.d());
        if (!a2.isIdentity()) {
            aVar.a(a2);
        }
        org.a.b.h.a aVar2 = new org.a.b.h.a();
        aVar2.a(hVar.c());
        aVar2.b(hVar.d());
        aVar2.c(hVar.e());
        aVar2.d(hVar.g());
        aVar.a(new h(aVar2));
        return aVar;
    }

    private void a(d dVar, d dVar2, Set<String> set, boolean z) {
        for (Map.Entry<j, org.a.c.b.b> entry : dVar.e()) {
            j key = entry.getKey();
            if (set.contains(key.a())) {
                dVar2.a(key, this.f4426b.a(entry.getValue()));
            }
        }
    }

    private void a(org.a.c.h.b bVar) {
        org.a.c.h.f.d.a e = bVar.c().e();
        if (e == null) {
            return;
        }
        org.a.c.h.c c2 = this.f4425a.c();
        org.a.c.h.f.d.a e2 = c2.e();
        if (e2 != null) {
            this.f4426b.a(e, e2);
        } else {
            c2.a(new org.a.c.h.f.d.a((d) this.f4426b.a(e)));
        }
    }
}
