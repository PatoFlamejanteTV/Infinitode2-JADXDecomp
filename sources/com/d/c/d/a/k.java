package com.d.c.d.a;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/c/d/a/k.class */
public final class k {

    /* renamed from: a, reason: collision with root package name */
    private static k f999a = new k(new com.d.c.d.j((short) 7, 148.0f, "148mm"), new com.d.c.d.j((short) 7, 210.0f, "210mm"));

    /* renamed from: b, reason: collision with root package name */
    private static k f1000b = new k(new com.d.c.d.j((short) 7, 210.0f, "210mm"), new com.d.c.d.j((short) 7, 297.0f, "297mm"));
    private static k c = new k(new com.d.c.d.j((short) 7, 297.0f, "297mm"), new com.d.c.d.j((short) 7, 420.0f, "420mm"));
    private static k d = new k(new com.d.c.d.j((short) 7, 176.0f, "176mm"), new com.d.c.d.j((short) 7, 250.0f, "250mm"));
    private static k e = new k(new com.d.c.d.j((short) 7, 250.0f, "250mm"), new com.d.c.d.j((short) 7, 353.0f, "353mm"));
    private static k f = new k(new com.d.c.d.j((short) 8, 8.5f, "8.5in"), new com.d.c.d.j((short) 8, 11.0f, "11in"));
    private static k g = new k(new com.d.c.d.j((short) 8, 8.5f, "8.5in"), new com.d.c.d.j((short) 8, 14.0f, "14in"));
    private static k h = new k(new com.d.c.d.j((short) 8, 11.0f, "11in"), new com.d.c.d.j((short) 8, 17.0f, "17in"));
    private static final Map i;
    private com.d.c.d.d j;
    private com.d.c.d.d k;

    static {
        HashMap hashMap = new HashMap();
        i = hashMap;
        hashMap.put("a3", c);
        i.put("a4", f1000b);
        i.put("a5", f999a);
        i.put("b3", d);
        i.put("b4", e);
        i.put("letter", f);
        i.put("legal", g);
        i.put("ledger", h);
    }

    private k(com.d.c.d.d dVar, com.d.c.d.d dVar2) {
        this.j = dVar;
        this.k = dVar2;
    }

    private k() {
    }

    public final com.d.c.d.d a() {
        return this.k;
    }

    public final com.d.c.d.d b() {
        return this.j;
    }

    public static k a(String str) {
        return (k) i.get(str);
    }
}
