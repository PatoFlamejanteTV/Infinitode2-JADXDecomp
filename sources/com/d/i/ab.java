package com.d.i;

import com.a.a.c.k.b.ak;
import java.awt.Rectangle;

/* loaded from: infinitode-2.jar:com/d/i/ab.class */
public final class ab implements com.d.c.f.d, Cloneable {

    /* renamed from: a, reason: collision with root package name */
    private com.d.e.aa f1326a;

    /* renamed from: b, reason: collision with root package name */
    private com.d.d.m f1327b;
    private com.d.d.j c;
    private int d;
    private int e;
    private aa f;
    private int g;
    private com.d.e.t h;
    private int i;
    private boolean j = false;
    private boolean k = false;
    private com.d.a.a l = new ak();

    public ab(com.d.e.aa aaVar) {
        this.f1326a = aaVar;
    }

    public final boolean d() {
        return this.j;
    }

    public final void a(boolean z) {
        this.j = true;
    }

    public final com.d.d.s e() {
        return this.f1326a.m();
    }

    @Override // com.d.c.f.d
    public final float a() {
        return this.f1326a.o();
    }

    @Override // com.d.c.f.d
    public final int b() {
        return this.f1326a.s();
    }

    @Override // com.d.c.f.d
    public final float a(com.d.c.g.a aVar) {
        return this.f1326a.a(aVar).a();
    }

    @Override // com.d.c.f.d
    public final float b(com.d.c.g.a aVar) {
        return this.f1326a.a(q(), aVar);
    }

    public final com.d.d.r f() {
        return this.f1326a.e();
    }

    public final void a(com.d.a.a aVar) {
        this.l = aVar;
    }

    public final com.d.a.a g() {
        return this.l;
    }

    @Override // com.d.c.f.d
    public final k c(com.d.c.g.a aVar) {
        return this.f1326a.a(aVar);
    }

    public final Rectangle h() {
        Rectangle rectangle;
        if (!o()) {
            rectangle = this.f1326a.k();
        } else {
            rectangle = new Rectangle(0, -this.f.b(), this.f.d(this), this.f.c(this) - 1);
        }
        rectangle.translate(-1, -1);
        return rectangle;
    }

    public final Rectangle i() {
        Rectangle rectangle = new Rectangle(h());
        rectangle.y = -rectangle.y;
        return rectangle;
    }

    public final boolean j() {
        return this.f1326a.f();
    }

    public final boolean k() {
        return this.f1326a.g();
    }

    public final boolean l() {
        return this.f1326a.h();
    }

    public final boolean m() {
        return this.f1326a.i();
    }

    public final boolean n() {
        return this.f1326a.q();
    }

    public final boolean o() {
        return this.f1326a.r();
    }

    public final com.d.d.m p() {
        return this.f1327b;
    }

    public final void a(com.d.d.m mVar) {
        this.f1327b = mVar;
    }

    public final com.d.d.j q() {
        return this.c;
    }

    public final void a(com.d.d.j jVar) {
        this.c = jVar;
    }

    public final void a(int i, aa aaVar) {
        this.e = i;
        this.f = aaVar;
    }

    public final int r() {
        return this.d;
    }

    public final void a(int i) {
        this.d = i;
    }

    public final aa s() {
        return this.f;
    }

    public final int t() {
        return this.e;
    }

    @Override // com.d.c.f.d
    public final com.d.e.a c() {
        return this.f1326a.j();
    }

    @Override // com.d.c.f.d
    public final l a(k kVar) {
        com.d.d.r f = f();
        q();
        return f.a(kVar);
    }

    public final com.d.e.t u() {
        return this.h;
    }

    public final void a(com.d.e.t tVar) {
        this.h = tVar;
    }

    public final int v() {
        return this.i;
    }

    public final void b(int i) {
        this.i = i;
    }

    public final f a(String str) {
        return this.f1326a.a(str);
    }

    public final void c(int i) {
        this.g = i;
    }

    public final int w() {
        return this.g;
    }

    public final void b(boolean z) {
        this.k = z;
    }

    public final boolean x() {
        return this.k;
    }

    public final Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }
}
