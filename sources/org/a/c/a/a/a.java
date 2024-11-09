package org.a.c.a.a;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.a.c.b.d;

/* loaded from: infinitode-2.jar:org/a/c/a/a/a.class */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private final String f4353a;

    /* renamed from: b, reason: collision with root package name */
    private byte[] f4354b;
    private d c;
    private static final ConcurrentMap<String, a> d = new ConcurrentHashMap();

    private a(String str) {
        this.f4353a = str;
        if (str.startsWith("/")) {
            throw new IllegalArgumentException("Operators are not allowed to start with / '" + str + "'");
        }
    }

    public static a a(String str) {
        a aVar;
        if (str.equals("ID") || "BI".equals(str)) {
            aVar = new a(str);
        } else {
            a aVar2 = d.get(str);
            aVar = aVar2;
            if (aVar2 == null) {
                a putIfAbsent = d.putIfAbsent(str, new a(str));
                aVar = putIfAbsent;
                if (putIfAbsent == null) {
                    aVar = d.get(str);
                }
            }
        }
        return aVar;
    }

    public final String a() {
        return this.f4353a;
    }

    public final String toString() {
        return "PDFOperator{" + this.f4353a + "}";
    }

    public final byte[] b() {
        return this.f4354b;
    }

    public final void a(byte[] bArr) {
        this.f4354b = bArr;
    }

    public final d c() {
        return this.c;
    }

    public final void a(d dVar) {
        this.c = dVar;
    }
}
