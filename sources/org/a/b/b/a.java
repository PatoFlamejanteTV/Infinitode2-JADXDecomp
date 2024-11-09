package org.a.b.b;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:org/a/b/b/a.class */
public final class a extends i {
    private String f;
    private String g;
    private int h;
    private s k;
    private List<Map<String, Object>> i = new LinkedList();
    private List<Map<String, Object>> j = new LinkedList();
    private final Map<Integer, p> l = new ConcurrentHashMap();
    private final C0042a m = new C0042a(this, 0);

    public final String a() {
        return this.f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void c(String str) {
        this.f = str;
    }

    public final String e() {
        return this.g;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void d(String str) {
        this.g = str;
    }

    public final int f() {
        return this.h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(int i) {
        this.h = i;
    }

    public final List<Map<String, Object>> g() {
        return this.i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(List<Map<String, Object>> list) {
        this.i = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void b(List<Map<String, Object>> list) {
        this.j = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void a(s sVar) {
        this.k = sVar;
    }

    private int d(int i) {
        int a2 = this.k.a(i);
        if (a2 != -1) {
            Map<String, Object> map = this.j.get(a2);
            if (map.containsKey("defaultWidthX")) {
                return ((Number) map.get("defaultWidthX")).intValue();
            }
            return 1000;
        }
        return 1000;
    }

    private int e(int i) {
        int a2 = this.k.a(i);
        if (a2 != -1) {
            Map<String, Object> map = this.j.get(a2);
            if (map.containsKey("nominalWidthX")) {
                return ((Number) map.get("nominalWidthX")).intValue();
            }
            return 0;
        }
        return 0;
    }

    private byte[][] f(int i) {
        int a2 = this.k.a(i);
        if (a2 != -1) {
            return (byte[][]) this.j.get(a2).get("Subrs");
        }
        return (byte[][]) null;
    }

    @Override // org.a.b.b.i
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public final p c(int i) {
        p pVar = this.l.get(Integer.valueOf(i));
        p pVar2 = pVar;
        if (pVar == null) {
            int c = this.c.c(i);
            byte[] bArr = this.d[c];
            byte[] bArr2 = bArr;
            if (bArr == null) {
                bArr2 = this.d[0];
            }
            pVar2 = new p(this.m, this.f4205a, i, c, new y(this.f4205a, i).a(bArr2, this.e, f(c)), d(c), e(c));
            this.l.put(Integer.valueOf(i), pVar2);
        }
        return pVar2;
    }

    @Override // org.a.b.b
    public final List<Number> d() {
        return (List) this.f4206b.get("FontMatrix");
    }

    @Override // org.a.b.b
    public final float a(String str) {
        return c(f(str)).a();
    }

    @Override // org.a.b.b
    public final boolean b(String str) {
        return f(str) != 0;
    }

    private static int f(String str) {
        if (!str.startsWith("\\")) {
            throw new IllegalArgumentException("Invalid selector");
        }
        return Integer.parseInt(str.substring(1));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.a.b.b.a$a, reason: collision with other inner class name */
    /* loaded from: infinitode-2.jar:org/a/b/b/a$a.class */
    public class C0042a implements org.a.b.g.c {
        private C0042a() {
        }

        /* synthetic */ C0042a(a aVar, byte b2) {
            this();
        }

        @Override // org.a.b.g.c
        public final t a_(String str) {
            return a.this.c(0);
        }
    }
}
