package com.d.i;

import java.awt.Rectangle;

/* loaded from: infinitode-2.jar:com/d/i/w.class */
public final class w extends c {

    /* renamed from: a, reason: collision with root package name */
    private Rectangle f1381a;

    public w(Rectangle rectangle) {
        this.f1381a = rectangle;
    }

    @Override // com.d.i.f
    public final int Q() {
        return this.f1381a.width;
    }

    @Override // com.d.i.f
    public final int as() {
        return this.f1381a.height;
    }

    @Override // com.d.i.f
    public final int d_() {
        return this.f1381a.width;
    }

    @Override // com.d.i.f
    public final Rectangle c(int i, int i2, com.d.c.f.d dVar) {
        return new Rectangle(-this.f1381a.x, -this.f1381a.y, this.f1381a.width, this.f1381a.height);
    }

    @Override // com.d.i.f
    public final Rectangle b(int i, int i2, com.d.c.f.d dVar) {
        return new Rectangle(-this.f1381a.x, -this.f1381a.y, this.f1381a.width, this.f1381a.height);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.f
    public final int aj() {
        return this.f1381a.width;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.d.i.f
    public final int l(com.d.c.f.d dVar) {
        return this.f1381a.width;
    }

    @Override // com.d.i.c
    public final c c() {
        throw new IllegalArgumentException("cannot be copied");
    }
}
