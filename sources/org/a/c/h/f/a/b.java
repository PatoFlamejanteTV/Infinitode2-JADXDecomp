package org.a.c.h.f.a;

import java.awt.color.ColorSpace;
import org.lwjgl.system.macosx.CoreGraphics;

/* loaded from: infinitode-2.jar:org/a/c/h/f/a/b.class */
public abstract class b extends a {

    /* renamed from: a, reason: collision with root package name */
    protected org.a.c.b.d f4568a;
    private static final ColorSpace f = ColorSpace.getInstance(CoreGraphics.kCGErrorIllegalArgument);

    /* renamed from: b, reason: collision with root package name */
    protected float f4569b = 1.0f;
    protected float c = 1.0f;
    protected float d = 1.0f;

    /* JADX INFO: Access modifiers changed from: protected */
    public b(org.a.c.b.j jVar) {
        this.e = new org.a.c.b.a();
        this.f4568a = new org.a.c.b.d();
        this.e.a((org.a.c.b.b) jVar);
        this.e.a((org.a.c.b.b) this.f4568a);
        a(d());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public b(org.a.c.b.a aVar) {
        this.e = aVar;
        this.f4568a = (org.a.c.b.d) this.e.a(1);
        a(d());
    }

    private void a(w wVar) {
        this.f4569b = wVar.a();
        this.c = wVar.b();
        this.d = wVar.c();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static float[] a(float f2, float f3, float f4) {
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        if (f4 < 0.0f) {
            f4 = 0.0f;
        }
        return f.toRGB(new float[]{f2, f3, f4});
    }

    private w d() {
        org.a.c.b.a aVar = (org.a.c.b.a) this.f4568a.a(org.a.c.b.j.dZ);
        org.a.c.b.a aVar2 = aVar;
        if (aVar == null) {
            org.a.c.b.a aVar3 = new org.a.c.b.a();
            aVar2 = aVar3;
            aVar3.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
            aVar2.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
            aVar2.a((org.a.c.b.b) new org.a.c.b.f(1.0f));
        }
        return new w(aVar2);
    }
}
