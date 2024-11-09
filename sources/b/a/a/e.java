package b.a.a;

/* loaded from: infinitode-2.jar:b/a/a/e.class */
public final class e extends h {
    private e(String str, Throwable th) {
        super(str, th);
    }

    public e(int i, Throwable th) {
        this(a(i), th);
    }

    private static String a(int i) {
        return "Decoder errorcode " + Integer.toHexString(i);
    }
}
