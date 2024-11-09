package org.a.b.c;

/* loaded from: infinitode-2.jar:org/a/b/c/c.class */
/* synthetic */ class c {

    /* renamed from: a, reason: collision with root package name */
    private final char f4259a;

    /* renamed from: b, reason: collision with root package name */
    private char f4260b;
    private final int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(char c, char c2, int i) {
        this.f4259a = c;
        this.f4260b = c2;
        this.c = i;
    }

    public int a(char c) {
        if (this.f4259a <= c && c <= this.f4260b) {
            return this.c + (c - this.f4259a);
        }
        return -1;
    }

    public boolean a(char c, char c2, int i) {
        if (c == this.f4260b + 1 && i == ((this.c + this.f4260b) - this.f4259a) + 1) {
            this.f4260b = c2;
            return true;
        }
        return false;
    }
}
