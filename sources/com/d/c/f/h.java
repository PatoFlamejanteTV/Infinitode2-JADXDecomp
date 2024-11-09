package com.d.c.f;

import com.d.c.d.j;
import java.util.Iterator;
import java.util.LinkedHashMap;

/* loaded from: infinitode-2.jar:com/d/c/f/h.class */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    private static final LinkedHashMap f1090a = new LinkedHashMap();

    /* renamed from: b, reason: collision with root package name */
    private static final LinkedHashMap f1091b = new LinkedHashMap();
    private static final j c = new j((short) 3, 0.8f, "0.8em");
    private static final j d = new j((short) 3, 1.2f, "1.2em");

    static {
        f1090a.put(com.d.c.a.c.bP, new j((short) 5, 9.0f, "9px"));
        f1090a.put(com.d.c.a.c.bN, new j((short) 5, 10.0f, "10px"));
        f1090a.put(com.d.c.a.c.bF, new j((short) 5, 13.0f, "13px"));
        f1090a.put(com.d.c.a.c.bx, new j((short) 5, 16.0f, "16px"));
        f1090a.put(com.d.c.a.c.bv, new j((short) 5, 18.0f, "18px"));
        f1090a.put(com.d.c.a.c.bM, new j((short) 5, 24.0f, "24px"));
        f1090a.put(com.d.c.a.c.bO, new j((short) 5, 32.0f, "32px"));
        f1091b.put(com.d.c.a.c.bP, new j((short) 5, 9.0f, "9px"));
        f1091b.put(com.d.c.a.c.bN, new j((short) 5, 10.0f, "10px"));
        f1091b.put(com.d.c.a.c.bF, new j((short) 5, 12.0f, "12px"));
        f1091b.put(com.d.c.a.c.bx, new j((short) 5, 13.0f, "13px"));
        f1091b.put(com.d.c.a.c.bv, new j((short) 5, 16.0f, "16px"));
        f1091b.put(com.d.c.a.c.bM, new j((short) 5, 20.0f, "20px"));
        f1091b.put(com.d.c.a.c.bO, new j((short) 5, 26.0f, "26px"));
    }

    public static com.d.c.a.c a(com.d.c.a.c cVar) {
        com.d.c.a.c cVar2 = null;
        for (com.d.c.a.c cVar3 : f1090a.keySet()) {
            if (cVar3 == cVar) {
                return cVar2;
            }
            cVar2 = cVar3;
        }
        return null;
    }

    public static com.d.c.a.c b(com.d.c.a.c cVar) {
        Iterator it = f1090a.keySet().iterator();
        while (it.hasNext()) {
            if (((com.d.c.a.c) it.next()) == cVar && it.hasNext()) {
                return (com.d.c.a.c) it.next();
            }
        }
        return null;
    }

    public static j a(com.d.c.a.c cVar, String[] strArr) {
        if (a(strArr)) {
            return (j) f1091b.get(cVar);
        }
        return (j) f1090a.get(cVar);
    }

    public static j c(com.d.c.a.c cVar) {
        if (cVar == com.d.c.a.c.bw) {
            return d;
        }
        if (cVar == com.d.c.a.c.bG) {
            return c;
        }
        return null;
    }

    private static boolean a(String[] strArr) {
        for (String str : strArr) {
            if (str.equals("monospace")) {
                return true;
            }
        }
        return false;
    }
}
