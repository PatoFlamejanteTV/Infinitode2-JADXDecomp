package org.a.d.b;

import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/d/b/ae.class */
public final class ae {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, af> f4668a = new HashMap();

    public final void a(String str, af afVar) {
        this.f4668a.put(str, afVar);
    }

    public final af a(String str) {
        return this.f4668a.get(str);
    }
}
