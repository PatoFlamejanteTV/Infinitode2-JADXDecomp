package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/d.class */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    private static short f6a = -32763;

    /* renamed from: b, reason: collision with root package name */
    private short f7b = -1;

    public final void a(int i, int i2) {
        int i3;
        int i4 = 1 << (i2 - 1);
        do {
            if (((this.f7b & 32768) == 0) ^ ((i & i4) == 0)) {
                this.f7b = (short) (this.f7b << 1);
                this.f7b = (short) (this.f7b ^ f6a);
            } else {
                this.f7b = (short) (this.f7b << 1);
            }
            i3 = i4 >>> 1;
            i4 = i3;
        } while (i3 != 0);
    }

    public final short a() {
        short s = this.f7b;
        this.f7b = (short) -1;
        return s;
    }
}
