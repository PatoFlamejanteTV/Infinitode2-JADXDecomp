package com.studiohartman.jamepad;

/* loaded from: infinitode-2.jar:com/studiohartman/jamepad/ControllerIndex.class */
public final class ControllerIndex {

    /* renamed from: a, reason: collision with root package name */
    private int f4019a;

    /* renamed from: b, reason: collision with root package name */
    private long f4020b;
    private boolean[] c = new boolean[c.values().length];
    private boolean[] d = new boolean[c.values().length];

    private native long nativeConnectController(int i);

    private native void nativeClose(long j);

    private native boolean nativeIsConnected(long j);

    private native boolean nativeCanVibrate(long j);

    private native boolean nativeDoVibration(long j, int i, int i2, int i3);

    private native boolean nativeCheckButton(long j, int i);

    private native boolean nativeButtonAvailable(long j, int i);

    private native int nativeCheckAxis(long j, int i);

    private native boolean nativeAxisAvailable(long j, int i);

    private native String nativeGetName(long j);

    private native int nativeGetPlayerIndex(long j);

    private native void nativeSetPlayerIndex(long j, int i);

    private native int nativeGetPowerLevel(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public ControllerIndex(int i) {
        this.f4019a = i;
        for (int i2 = 0; i2 < this.c.length; i2++) {
            this.c[i2] = false;
            this.d[i2] = false;
        }
        i();
    }

    private void i() {
        this.f4020b = nativeConnectController(this.f4019a);
    }

    public final void a() {
        if (this.f4020b != 0) {
            nativeClose(this.f4020b);
            this.f4020b = 0L;
        }
    }

    public final boolean b() {
        a();
        i();
        return c();
    }

    public final boolean c() {
        return this.f4020b != 0 && nativeIsConnected(this.f4020b);
    }

    public final int d() {
        return this.f4019a;
    }

    public final boolean e() {
        j();
        return nativeCanVibrate(this.f4020b);
    }

    public final boolean a(float f, float f2, int i) {
        j();
        boolean z = f >= 0.0f && f <= 1.0f;
        boolean z2 = f2 >= 0.0f && f2 <= 1.0f;
        if (!z || !z2) {
            throw new IllegalArgumentException("The passed values are not in the range 0 to 1!");
        }
        return nativeDoVibration(this.f4020b, (int) (65535.0f * f), (int) (65535.0f * f2), i);
    }

    public final boolean a(c cVar) {
        b(cVar.ordinal());
        return this.c[cVar.ordinal()];
    }

    private void b(int i) {
        j();
        boolean nativeCheckButton = nativeCheckButton(this.f4020b, i);
        this.d[i] = nativeCheckButton && !this.c[i];
        this.c[i] = nativeCheckButton;
    }

    public final boolean b(c cVar) {
        j();
        return nativeButtonAvailable(this.f4020b, cVar.ordinal());
    }

    public final float a(b bVar) {
        j();
        return nativeCheckAxis(this.f4020b, bVar.ordinal()) / 32767.0f;
    }

    public final boolean b(b bVar) {
        j();
        return nativeAxisAvailable(this.f4020b, bVar.ordinal());
    }

    public final String f() {
        j();
        String nativeGetName = nativeGetName(this.f4020b);
        if (nativeGetName == null) {
            return "Unnamed Controller";
        }
        return nativeGetName;
    }

    public final int g() {
        j();
        return nativeGetPlayerIndex(this.f4020b);
    }

    public final void a(int i) {
        j();
        nativeSetPlayerIndex(this.f4020b, i);
    }

    public final d h() {
        j();
        return d.a(nativeGetPowerLevel(this.f4020b));
    }

    private void j() {
        if (!c()) {
            throw new e("Controller at index " + this.f4019a + " is not connected!");
        }
    }
}
