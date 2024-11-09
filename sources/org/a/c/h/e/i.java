package org.a.c.h.e;

import org.lwjgl.system.windows.User32;

/* loaded from: infinitode-2.jar:org/a/c/h/e/i.class */
public abstract class i {
    public abstract String a();

    public abstract h b();

    public abstract b c();

    public abstract org.a.b.b d();

    public abstract int e();

    public abstract int f();

    public abstract int g();

    public abstract int h();

    public abstract int i();

    public abstract z j();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int k() {
        switch (f()) {
            case -1:
                return 0;
            case 0:
                return 0;
            case 100:
                return 2;
            case 200:
                return 3;
            case 300:
                return 4;
            case 400:
                return 5;
            case 500:
                return 6;
            case 600:
                return 7;
            case 700:
                return 8;
            case User32.WM_DWMCOLORIZATIONCOLORCHANGED /* 800 */:
                return 9;
            case 900:
                return 10;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long l() {
        return ((h() & 4294967295L) << 32) | (g() & 4294967295L);
    }

    public String toString() {
        return a() + " (" + b() + ", mac: 0x" + Integer.toHexString(i()) + ", os/2: 0x" + Integer.toHexString(e()) + ", cid: " + c() + ")";
    }
}
