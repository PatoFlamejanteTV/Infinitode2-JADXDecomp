package com.d.e;

import org.w3c.dom.Text;

/* loaded from: infinitode-2.jar:com/d/e/x.class */
public final class x {

    /* renamed from: a, reason: collision with root package name */
    private String f1168a;

    /* renamed from: b, reason: collision with root package name */
    private int f1169b;
    private int c;
    private int d;
    private boolean e;
    private boolean f;
    private int g;
    private Text h;

    public final int a() {
        return this.f1168a.length();
    }

    public final void b() {
        this.g = 0;
        this.e = false;
        this.f = false;
    }

    public final int c() {
        return this.c;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final String d() {
        return this.f1168a;
    }

    public final void a(String str) {
        this.f1168a = str;
    }

    public final int e() {
        return this.f1169b;
    }

    public final void b(int i) {
        this.f1169b = i;
    }

    public final String f() {
        return this.f1168a.substring(this.f1169b);
    }

    public final String g() {
        if (this.c > 0 && this.f1168a.charAt(this.c - 1) == '\n') {
            return this.f1168a.substring(this.f1169b, this.c - 1);
        }
        return this.f1168a.substring(this.f1169b, this.c);
    }

    public final boolean h() {
        return this.e;
    }

    public final void a(boolean z) {
        this.e = true;
    }

    public final boolean i() {
        return this.f;
    }

    public final void b(boolean z) {
        this.f = true;
    }

    public final int j() {
        return this.g;
    }

    public final void c(int i) {
        this.g = i;
    }

    public final boolean k() {
        return this.c == d().length();
    }

    public final void l() {
        this.c = this.d;
    }

    public final void m() {
        this.d = this.c;
    }

    public final Text n() {
        return this.h;
    }

    public final void a(Text text) {
        this.h = text;
    }
}
