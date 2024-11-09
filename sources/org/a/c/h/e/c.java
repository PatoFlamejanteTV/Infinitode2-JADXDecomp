package org.a.c.h.e;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/a/c/h/e/c.class */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private static Map<String, org.a.b.c.a> f4531a = Collections.synchronizedMap(new HashMap());

    public static org.a.b.c.a a(String str) {
        org.a.b.c.a aVar = f4531a.get(str);
        if (aVar != null) {
            return aVar;
        }
        org.a.b.c.a a2 = new org.a.b.c.b().a(str);
        f4531a.put(a2.c(), a2);
        return a2;
    }

    public static org.a.b.c.a a(InputStream inputStream) {
        org.a.b.c.a aVar = null;
        if (inputStream != null) {
            aVar = new org.a.b.c.b().a(inputStream);
        }
        return aVar;
    }
}
