package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/c.class */
public final class c extends h {

    /* renamed from: a, reason: collision with root package name */
    private int f5a;

    public c(String str, Throwable th) {
        super(str, th);
        this.f5a = 256;
    }

    public c(int i, Throwable th) {
        this(a(i), th);
        this.f5a = i;
    }

    public final int a() {
        return this.f5a;
    }

    private static String a(int i) {
        return "Bitstream errorcode " + Integer.toHexString(i);
    }
}
