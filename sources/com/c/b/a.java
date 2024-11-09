package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/a.class */
public class a {

    /* renamed from: a, reason: collision with root package name */
    float[][] f904a = new float[0];

    /* renamed from: b, reason: collision with root package name */
    com.c.a.a f905b = new com.c.a.a();
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    long i;
    long j;
    e k;

    /* JADX WARN: Type inference failed for: r1v1, types: [float[], float[][]] */
    public a(e eVar) {
        this.k = eVar;
    }

    public final void a(e eVar) {
        this.k = eVar;
    }

    public final int a() {
        return 0;
    }

    /* JADX WARN: Type inference failed for: r1v46, types: [float[], float[][]] */
    public final int a(com.c.a.b bVar) {
        int c;
        l lVar = this.k.f914a;
        this.f905b.a(bVar.f896a, bVar.f897b, bVar.c);
        if (this.f905b.c(1) != 0 || (c = this.f905b.c(this.k.f915b)) == -1) {
            return -1;
        }
        this.g = c;
        this.d = lVar.i[this.g].f934a;
        if (this.d != 0) {
            this.c = this.f905b.c(1);
            this.e = this.f905b.c(1);
            if (this.e == -1) {
                return -1;
            }
        } else {
            this.c = 0;
            this.e = 0;
        }
        this.i = bVar.f;
        this.j = bVar.g - 3;
        this.h = bVar.e;
        this.f = lVar.c[this.d];
        if (this.f904a.length < lVar.f929a) {
            this.f904a = new float[lVar.f929a];
        }
        for (int i = 0; i < lVar.f929a; i++) {
            if (this.f904a[i] == null || this.f904a[i].length < this.f) {
                this.f904a[i] = new float[this.f];
            } else {
                for (int i2 = 0; i2 < this.f; i2++) {
                    this.f904a[i][i2] = 0.0f;
                }
            }
        }
        return i.f926a[lVar.j[lVar.i[this.g].d]].a(this, this.k.f[this.g]);
    }
}
