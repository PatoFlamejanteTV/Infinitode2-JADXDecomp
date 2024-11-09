package com.d.c.e;

import com.d.i.v;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/c/e/c.class */
public final class c implements e {

    /* renamed from: a, reason: collision with root package name */
    private String f1068a;

    /* renamed from: b, reason: collision with root package name */
    private String f1069b;
    private d c;
    private int d;
    private final Map<com.d.c.a.d, List<v>> e = new HashMap();
    private int f;
    private int g;
    private int h;
    private int i;

    public c(int i) {
        this.d = i;
    }

    public final void a(String str) {
        this.f1069b = str;
        if (str.equals("first")) {
            this.h = 1;
        } else {
            this.i = 1;
        }
    }

    public final d b() {
        return this.c;
    }

    @Override // com.d.c.e.e
    public final void a(d dVar) {
        if (this.c != null) {
            throw new IllegalStateException("Ruleset has already been set");
        }
        this.c = dVar;
    }

    @Override // com.d.c.e.e
    public final int a() {
        return this.d;
    }

    public final void b(String str) {
        this.f1068a = str;
        this.g = 1;
    }

    public final void a(com.d.c.a.d dVar, List<v> list) {
        this.e.put(dVar, list);
    }

    public final Map<com.d.c.a.d, List<v>> c() {
        return this.e;
    }

    public final long d() {
        return 0 | (this.g << 32) | (this.h << 24) | (this.i << 16) | this.f;
    }

    public final boolean a(String str, String str2) {
        if (this.f1068a == null && this.f1069b == null) {
            return true;
        }
        if (this.f1068a == null && this.f1069b != null) {
            if (!this.f1069b.equals(str2)) {
                if (this.f1069b.equals("right") && str2 != null && str2.equals("first")) {
                    return true;
                }
            } else {
                return true;
            }
        }
        if (this.f1068a != null && this.f1068a.equals(str) && this.f1069b == null) {
            return true;
        }
        if (this.f1068a != null && this.f1068a.equals(str) && this.f1069b != null && this.f1069b.equals(str2)) {
            return true;
        }
        return false;
    }

    public final void a(int i) {
        this.f = i;
    }
}
