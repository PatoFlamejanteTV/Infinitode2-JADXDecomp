package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/a.class */
final class a {
    private final int[] d = new int[32768];

    /* renamed from: a, reason: collision with root package name */
    private int f1a = 0;

    /* renamed from: b, reason: collision with root package name */
    private int f2b = 0;
    private int c = 0;

    public final int a() {
        return this.f2b;
    }

    public final int a(int i) {
        this.f2b += i;
        int i2 = 0;
        int i3 = this.c;
        int i4 = i3;
        if (i3 + i >= 32768) {
            while (true) {
                int i5 = i;
                i--;
                if (i5 <= 0) {
                    break;
                }
                i2 = (i2 << 1) | (this.d[i4] != 0 ? 1 : 0);
                i4 = (i4 + 1) & 32767;
            }
        } else {
            while (true) {
                int i6 = i;
                i--;
                if (i6 <= 0) {
                    break;
                }
                int i7 = i4;
                i4++;
                i2 = (i2 << 1) | (this.d[i7] != 0 ? 1 : 0);
            }
        }
        this.c = i4;
        return i2;
    }

    public final int b() {
        this.f2b++;
        int i = this.d[this.c];
        this.c = (this.c + 1) & 32767;
        return i;
    }

    public final void b(int i) {
        int i2 = this.f1a;
        int i3 = i2 + 1;
        this.d[i2] = i & 128;
        int i4 = i3 + 1;
        this.d[i3] = i & 64;
        int i5 = i4 + 1;
        this.d[i4] = i & 32;
        int i6 = i5 + 1;
        this.d[i5] = i & 16;
        int i7 = i6 + 1;
        this.d[i6] = i & 8;
        int i8 = i7 + 1;
        this.d[i7] = i & 4;
        int i9 = i8 + 1;
        this.d[i8] = i & 2;
        int i10 = i9 + 1;
        this.d[i9] = i & 1;
        if (i10 == 32768) {
            this.f1a = 0;
        } else {
            this.f1a = i10;
        }
    }

    public final void c(int i) {
        this.f2b -= i;
        this.c -= i;
        if (this.c < 0) {
            this.c += 32768;
        }
    }

    public final void d(int i) {
        this.f2b -= 32768;
        this.c -= 32768;
        if (this.c < 0) {
            this.c += 32768;
        }
    }
}
