package com.d.m;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/m/d.class */
public final class d {
    private static Map e;

    /* renamed from: a, reason: collision with root package name */
    public static final d f1419a = a("HIGH");

    /* renamed from: b, reason: collision with root package name */
    public static final d f1420b = a("MED");
    public static final d c = a("LOW");
    public static final d d = a("AREA");
    private final String f;

    private static d a(String str) {
        b();
        if (e.containsKey(str)) {
            throw new RuntimeException("Type strings for DownscaleQuality should be unique; " + str + " is declared twice");
        }
        d dVar = new d(str);
        e.put(str, dVar);
        return dVar;
    }

    private static void b() {
        if (e == null) {
            e = new HashMap();
        }
    }

    private d(String str) {
        this.f = str;
    }

    public final String a() {
        return this.f;
    }

    public static d a(String str, d dVar) {
        d dVar2 = (d) e.get(str);
        return dVar2 == null ? dVar : dVar2;
    }
}
