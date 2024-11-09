package com.d.i;

import java.awt.Rectangle;

/* loaded from: infinitode-2.jar:com/d/i/ae.class */
public final class ae extends c {

    /* renamed from: a, reason: collision with root package name */
    private final Rectangle f1332a;

    public ae(Rectangle rectangle) {
        this.f1332a = rectangle;
    }

    @Override // com.d.i.f
    public final int Q() {
        return this.f1332a.width;
    }

    @Override // com.d.i.f
    public final int as() {
        return this.f1332a.height;
    }

    @Override // com.d.i.f
    public final int d_() {
        return this.f1332a.width;
    }

    @Override // com.d.i.f
    public final Rectangle c(int i, int i2, com.d.c.f.d dVar) {
        return new Rectangle(-this.f1332a.x, -this.f1332a.y, this.f1332a.width, this.f1332a.height);
    }

    @Override // com.d.i.f
    public final Rectangle b(int i, int i2, com.d.c.f.d dVar) {
        return new Rectangle(-this.f1332a.x, -this.f1332a.y, this.f1332a.width, this.f1332a.height);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.f
    public final int l(com.d.c.f.d dVar) {
        return this.f1332a.width;
    }

    @Override // com.d.i.c
    public final c c() {
        throw new IllegalArgumentException("cannot be copied");
    }

    @Override // com.d.i.c
    public final boolean b_() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.c
    public final int h(com.d.c.f.d dVar) {
        return this.f1332a.height;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.f
    public final boolean ax() {
        return true;
    }
}
