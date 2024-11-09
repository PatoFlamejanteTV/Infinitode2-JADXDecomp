package org.a.b.b;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/b/b/i.class */
public abstract class i implements org.a.b.b {

    /* renamed from: a, reason: collision with root package name */
    protected String f4205a;

    /* renamed from: b, reason: collision with root package name */
    protected final Map<String, Object> f4206b = new LinkedHashMap();
    protected c c;
    protected byte[][] d;
    protected byte[][] e;

    public abstract w c(int i);

    @Override // org.a.b.b
    public final String b() {
        return this.f4205a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void e(String str) {
        this.f4205a = str;
    }

    public final void a(String str, Object obj) {
        if (obj != null) {
            this.f4206b.put(str, obj);
        }
    }

    @Override // org.a.b.b
    public final org.a.b.h.a c() {
        return new org.a.b.h.a((List) this.f4206b.get("FontBBox"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(c cVar) {
        this.c = cVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(byte[][] bArr) {
        this.e = bArr;
    }

    public String toString() {
        return getClass().getSimpleName() + "[name=" + this.f4205a + ", topDict=" + this.f4206b + ", charset=" + this.c + ", charStrings=" + Arrays.deepToString(this.d) + "]";
    }
}
