package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/m.class */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private Float f31a;

    /* renamed from: b, reason: collision with root package name */
    private int f32b;
    private byte[] c;
    private int[] d;
    private boolean e = false;

    public m(int i, boolean z) {
        this.f32b = i;
        this.c = new byte[i * 2304];
        this.d = new int[i];
        b();
    }

    private void a(int i, short s) {
        byte b2;
        byte b3;
        if (this.e) {
            b2 = (byte) (s >>> 8);
            b3 = (byte) s;
        } else {
            b2 = (byte) s;
            b3 = (byte) (s >>> 8);
        }
        this.c[this.d[i]] = b2;
        this.c[this.d[i] + 1] = b3;
        int[] iArr = this.d;
        iArr[i] = iArr[i] + (this.f32b << 1);
    }

    public final void a(int i, float[] fArr) {
        if (this.f31a != null) {
            int i2 = 0;
            while (i2 < 32) {
                int i3 = i2;
                i2++;
                a(i, a(fArr[i3] * this.f31a.floatValue()));
            }
            return;
        }
        int i4 = 0;
        while (i4 < 32) {
            int i5 = i4;
            i4++;
            a(i, a(fArr[i5]));
        }
    }

    public final byte[] a() {
        return this.c;
    }

    public final int b() {
        try {
            int i = this.f32b - 1;
            int i2 = this.d[i] - (i << 1);
            for (int i3 = 0; i3 < this.f32b; i3++) {
                int i4 = i3;
                this.d[i4] = i4 << 1;
            }
            return i2;
        } catch (Throwable th) {
            for (int i5 = 0; i5 < this.f32b; i5++) {
                int i6 = i5;
                this.d[i6] = i6 << 1;
            }
            throw th;
        }
    }

    private static short a(float f) {
        if (f > 32767.0f) {
            return Short.MAX_VALUE;
        }
        if (f < -32768.0f) {
            return Short.MIN_VALUE;
        }
        return (short) f;
    }
}
