package org.a.b.h;

import java.util.List;

/* loaded from: infinitode-2.jar:org/a/b/h/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private float f4347a;

    /* renamed from: b, reason: collision with root package name */
    private float f4348b;
    private float c;
    private float d;

    public a() {
    }

    public a(float f, float f2, float f3, float f4) {
        this.f4347a = f;
        this.f4348b = f2;
        this.c = f3;
        this.d = f4;
    }

    public a(List<Number> list) {
        this.f4347a = list.get(0).floatValue();
        this.f4348b = list.get(1).floatValue();
        this.c = list.get(2).floatValue();
        this.d = list.get(3).floatValue();
    }

    public final float a() {
        return this.f4347a;
    }

    public final void a(float f) {
        this.f4347a = f;
    }

    public final float b() {
        return this.f4348b;
    }

    public final void b(float f) {
        this.f4348b = f;
    }

    public final float c() {
        return this.c;
    }

    public final void c(float f) {
        this.c = f;
    }

    public final float d() {
        return this.d;
    }

    public final void d(float f) {
        this.d = f;
    }

    public final float e() {
        return d() - b();
    }

    public final String toString() {
        return "[" + a() + "," + b() + "," + c() + "," + d() + "]";
    }
}
