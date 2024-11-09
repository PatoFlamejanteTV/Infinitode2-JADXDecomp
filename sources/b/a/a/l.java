package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/l.class */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private m f29a;

    /* renamed from: b, reason: collision with root package name */
    private n f30b;
    private n c;
    private k d;
    private j e;
    private i f;
    private boolean g;

    public final m a(g gVar, b bVar) {
        if (!this.g) {
            a(gVar);
        }
        a(gVar, bVar, gVar.b()).a();
        return this.f29a;
    }

    public final void a(m mVar) {
        this.f29a = mVar;
    }

    private static e a(int i, Throwable th) {
        return new e(513, th);
    }

    private f a(g gVar, b bVar, int i) {
        f fVar = null;
        switch (i) {
            case 1:
                if (this.f == null) {
                    this.f = new i();
                    this.f.a(bVar, gVar, this.f30b, this.c, this.f29a, 0);
                }
                fVar = this.f;
                break;
            case 2:
                if (this.e == null) {
                    this.e = new j();
                    this.e.a(bVar, gVar, this.f30b, this.c, this.f29a, 0);
                }
                fVar = this.e;
                break;
            case 3:
                if (this.d == null) {
                    this.d = new k(bVar, gVar, this.f30b, this.c, this.f29a, 0);
                }
                fVar = this.d;
                break;
        }
        if (fVar == null) {
            throw a(513, (Throwable) null);
        }
        return fVar;
    }

    private void a(g gVar) {
        int f = gVar.f();
        gVar.b();
        char c = f == 3 ? (char) 1 : (char) 2;
        if (this.f29a == null) {
            throw new RuntimeException("Output buffer was not set.");
        }
        this.f30b = new n(0, 32700.0f);
        if (c == 2) {
            this.c = new n(1, 32700.0f);
        }
        gVar.e();
        this.g = true;
    }
}
