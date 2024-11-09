package b.a.a;

import java.io.PrintStream;

/* loaded from: infinitode-2.jar:b/a/a/h.class */
public class h extends Exception {

    /* renamed from: a, reason: collision with root package name */
    private Throwable f10a;

    public h(String str, Throwable th) {
        super(str);
        this.f10a = th;
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        if (this.f10a == null) {
            super.printStackTrace(printStream);
        } else {
            this.f10a.printStackTrace();
        }
    }
}
