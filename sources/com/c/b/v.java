package com.c.b;

/* loaded from: infinitode-2.jar:com/c/b/v.class */
final class v {

    /* renamed from: a, reason: collision with root package name */
    int f948a;

    /* renamed from: b, reason: collision with root package name */
    int f949b;
    int[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int[] i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int a(com.c.a.a aVar) {
        if (aVar.c(24) != 5653314) {
            return -1;
        }
        this.f948a = aVar.c(16);
        this.f949b = aVar.c(24);
        if (this.f949b == -1) {
            return -1;
        }
        switch (aVar.c(1)) {
            case 0:
                this.c = new int[this.f949b];
                if (aVar.c(1) != 0) {
                    for (int i = 0; i < this.f949b; i++) {
                        if (aVar.c(1) != 0) {
                            int c = aVar.c(5);
                            if (c != -1) {
                                this.c[i] = c + 1;
                            } else {
                                return -1;
                            }
                        } else {
                            this.c[i] = 0;
                        }
                    }
                    break;
                } else {
                    for (int i2 = 0; i2 < this.f949b; i2++) {
                        int c2 = aVar.c(5);
                        if (c2 != -1) {
                            this.c[i2] = c2 + 1;
                        } else {
                            return -1;
                        }
                    }
                    break;
                }
            case 1:
                int c3 = aVar.c(5) + 1;
                this.c = new int[this.f949b];
                int i3 = 0;
                while (i3 < this.f949b) {
                    int c4 = aVar.c(o.a(this.f949b - i3));
                    if (c4 == -1) {
                        return -1;
                    }
                    int i4 = 0;
                    while (i4 < c4) {
                        this.c[i3] = c3;
                        i4++;
                        i3++;
                    }
                    c3++;
                }
                break;
            default:
                return -1;
        }
        int c5 = aVar.c(4);
        this.d = c5;
        switch (c5) {
            case 0:
                return 0;
            case 1:
            case 2:
                this.e = aVar.c(32);
                this.f = aVar.c(32);
                this.g = aVar.c(4) + 1;
                this.h = aVar.c(1);
                int i5 = 0;
                switch (this.d) {
                    case 1:
                        i5 = b();
                        break;
                    case 2:
                        i5 = this.f949b * this.f948a;
                        break;
                }
                this.i = new int[i5];
                for (int i6 = 0; i6 < i5; i6++) {
                    this.i[i6] = aVar.c(this.g);
                }
                if (this.i[i5 - 1] == -1) {
                    return -1;
                }
                return 0;
            default:
                return -1;
        }
    }

    private int b() {
        int floor = (int) Math.floor(Math.pow(this.f949b, 1.0d / this.f948a));
        while (true) {
            int i = 1;
            int i2 = 1;
            for (int i3 = 0; i3 < this.f948a; i3++) {
                i *= floor;
                i2 *= floor + 1;
            }
            if (i <= this.f949b && i2 > this.f949b) {
                return floor;
            }
            floor = i > this.f949b ? floor - 1 : floor + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final float[] a() {
        if (this.d == 1 || this.d == 2) {
            float a2 = a(this.e);
            float a3 = a(this.f);
            float[] fArr = new float[this.f949b * this.f948a];
            switch (this.d) {
                case 1:
                    int b2 = b();
                    for (int i = 0; i < this.f949b; i++) {
                        float f = 0.0f;
                        int i2 = 1;
                        for (int i3 = 0; i3 < this.f948a; i3++) {
                            float abs = (Math.abs(this.i[(i / i2) % b2]) * a3) + a2 + f;
                            if (this.h != 0) {
                                f = abs;
                            }
                            fArr[(i * this.f948a) + i3] = abs;
                            i2 *= b2;
                        }
                    }
                    break;
                case 2:
                    for (int i4 = 0; i4 < this.f949b; i4++) {
                        float f2 = 0.0f;
                        for (int i5 = 0; i5 < this.f948a; i5++) {
                            float abs2 = (Math.abs(this.i[(i4 * this.f948a) + i5]) * a3) + a2 + f2;
                            if (this.h != 0) {
                                f2 = abs2;
                            }
                            fArr[(i4 * this.f948a) + i5] = abs2;
                        }
                    }
                    break;
            }
            return fArr;
        }
        return null;
    }

    private static float a(int i) {
        float f = i & 2097151;
        float f2 = (i & 2145386496) >>> 21;
        if ((i & Integer.MIN_VALUE) != 0) {
            f = -f;
        }
        return a(f, (((int) f2) - 20) - 768);
    }

    private static float a(float f, int i) {
        return (float) (f * Math.pow(2.0d, i));
    }
}
