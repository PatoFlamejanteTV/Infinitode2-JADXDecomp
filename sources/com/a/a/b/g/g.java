package com.a.a.b.g;

import java.util.concurrent.ConcurrentHashMap;

/* loaded from: infinitode-2.jar:com/a/a/b/g/g.class */
public final class g extends ConcurrentHashMap<String, String> {

    /* renamed from: a, reason: collision with root package name */
    public static final g f159a = new g();

    /* renamed from: b, reason: collision with root package name */
    private final Object f160b;

    private g() {
        super(180, 0.8f, 4);
        this.f160b = new Object();
    }

    public final String a(String str) {
        String str2 = get(str);
        if (str2 != null) {
            return str2;
        }
        if (size() >= 180) {
            synchronized (this.f160b) {
                if (size() >= 180) {
                    clear();
                }
            }
        }
        String intern = str.intern();
        put(intern, intern);
        return intern;
    }
}
