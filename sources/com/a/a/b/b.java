package com.a.a.b;

/* loaded from: infinitode-2.jar:com/a/a/b/b.class */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static a f87a = new a("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);

    /* renamed from: b, reason: collision with root package name */
    private static a f88b = new a(f87a, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);

    static {
        new a(f87a, "PEM", true, '=', 64);
        StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        sb.setCharAt(sb.indexOf("+"), '-');
        sb.setCharAt(sb.indexOf("/"), '_');
        new a("MODIFIED-FOR-URL", sb.toString(), false, (char) 0, Integer.MAX_VALUE);
    }

    public static a a() {
        return f88b;
    }
}
