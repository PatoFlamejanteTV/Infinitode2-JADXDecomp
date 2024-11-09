package org.a.b.b;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:org/a/b/b/o.class */
public final class o extends i implements org.a.b.a {
    private e g;
    private final Map<String, Object> f = new LinkedHashMap();
    private final Map<Integer, w> h = new ConcurrentHashMap();
    private final a i = new a(this, 0);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/a/b/b/o$a.class */
    public class a implements org.a.b.g.c {
        private a() {
        }

        /* synthetic */ a(o oVar, byte b2) {
            this();
        }

        @Override // org.a.b.g.c
        public final t a_(String str) {
            return o.this.c(str);
        }
    }

    @Override // org.a.b.b
    public final float a(String str) {
        return c(str).a();
    }

    @Override // org.a.b.b
    public final boolean b(String str) {
        return this.c.b(this.c.a(str)) != 0;
    }

    @Override // org.a.b.b
    public final List<Number> d() {
        return (List) this.f4206b.get("FontMatrix");
    }

    public final t c(String str) {
        return a(d(str), str);
    }

    private int d(String str) {
        return this.c.b(this.c.a(str));
    }

    @Override // org.a.b.b.i
    public final w c(int i) {
        return a(i, "GID+" + i);
    }

    private w a(int i, String str) {
        w wVar = this.h.get(Integer.valueOf(i));
        w wVar2 = wVar;
        if (wVar == null) {
            byte[] bArr = null;
            if (i < this.d.length) {
                bArr = this.d[i];
            }
            if (bArr == null) {
                bArr = this.d[0];
            }
            wVar2 = new w(this.i, this.f4205a, str, i, new y(this.f4205a, str).a(bArr, this.e, f()), g(), h());
            this.h.put(Integer.valueOf(i), wVar2);
        }
        return wVar2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(String str, Object obj) {
        if (obj != null) {
            this.f.put(str, obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // org.a.b.a
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public e a() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(e eVar) {
        this.g = eVar;
    }

    private byte[][] f() {
        return (byte[][]) this.f.get("Subrs");
    }

    private Object f(String str) {
        Object obj = this.f4206b.get(str);
        if (obj != null) {
            return obj;
        }
        Object obj2 = this.f.get(str);
        if (obj2 != null) {
            return obj2;
        }
        return null;
    }

    private int g() {
        Number number = (Number) f("defaultWidthX");
        if (number == null) {
            return 1000;
        }
        return number.intValue();
    }

    private int h() {
        Number number = (Number) f("nominalWidthX");
        if (number == null) {
            return 0;
        }
        return number.intValue();
    }
}
