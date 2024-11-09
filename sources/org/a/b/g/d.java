package org.a.b.g;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.a.b.b.t;
import org.a.b.b.v;

/* loaded from: infinitode-2.jar:org/a/b/g/d.class */
public final class d implements org.a.b.a, org.a.b.b, c {

    /* renamed from: a, reason: collision with root package name */
    String f4341a = "";

    /* renamed from: b, reason: collision with root package name */
    org.a.b.d.b f4342b = null;
    List<Number> c = new ArrayList();
    List<Number> d = new ArrayList();
    String e = "";
    String f = "";
    String g = "";
    final List<byte[]> h;
    final Map<String, byte[]> i;
    private final Map<String, t> j;

    public static d a(InputStream inputStream) {
        org.a.b.e.a aVar = new org.a.b.e.a(inputStream);
        return new f().a(aVar.a(), aVar.b());
    }

    public static d a(byte[] bArr) {
        org.a.b.e.a aVar = new org.a.b.e.a(bArr);
        return new f().a(aVar.a(), aVar.b());
    }

    public static d a(byte[] bArr, byte[] bArr2) {
        return new f().a(bArr, bArr2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(byte[] bArr, byte[] bArr2) {
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        new ArrayList();
        this.h = new ArrayList();
        this.i = new LinkedHashMap();
        this.j = new ConcurrentHashMap();
    }

    @Override // org.a.b.b
    public final String b() {
        return this.f4341a;
    }

    @Override // org.a.b.b
    public final float a(String str) {
        return a_(str).a();
    }

    @Override // org.a.b.b
    public final boolean b(String str) {
        return this.i.get(str) != null;
    }

    @Override // org.a.b.g.c
    public final t a_(String str) {
        t tVar = this.j.get(str);
        t tVar2 = tVar;
        if (tVar == null) {
            byte[] bArr = this.i.get(str);
            byte[] bArr2 = bArr;
            if (bArr == null) {
                bArr2 = this.i.get(".notdef");
            }
            tVar2 = new t(this, this.f4341a, str, new v(this.f4341a, str).a(bArr2, this.h));
            this.j.put(str, tVar2);
        }
        return tVar2;
    }

    @Override // org.a.b.a
    public final org.a.b.d.b a() {
        return this.f4342b;
    }

    @Override // org.a.b.b
    public final List<Number> d() {
        return Collections.unmodifiableList(this.c);
    }

    @Override // org.a.b.b
    public final org.a.b.h.a c() {
        return new org.a.b.h.a(this.d);
    }

    public final String e() {
        return this.f;
    }

    public final String f() {
        return this.g;
    }

    public final String toString() {
        return getClass().getName() + "[fontName=" + this.f4341a + ", fullName=" + this.e + ", encoding=" + this.f4342b + ", charStringsDict=" + this.i + "]";
    }
}
