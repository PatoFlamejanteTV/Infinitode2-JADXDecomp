package org.a.c.b;

import java.io.OutputStream;

/* loaded from: infinitode-2.jar:org/a/c/b/c.class */
public final class c extends b {
    private static byte[] c = {116, 114, 117, 101};
    private static byte[] d = {102, 97, 108, 115, 101};

    /* renamed from: a, reason: collision with root package name */
    public static final c f4357a = new c(true);

    /* renamed from: b, reason: collision with root package name */
    public static final c f4358b = new c(false);
    private final boolean e;

    private c(boolean z) {
        this.e = z;
    }

    public final boolean a() {
        return this.e;
    }

    public static c b(boolean z) {
        return z ? f4357a : f4358b;
    }

    @Override // org.a.c.b.b
    public final Object a(u uVar) {
        return uVar.a(this);
    }

    public final String toString() {
        return String.valueOf(this.e);
    }

    public final void a(OutputStream outputStream) {
        if (this.e) {
            outputStream.write(c);
        } else {
            outputStream.write(d);
        }
    }
}
