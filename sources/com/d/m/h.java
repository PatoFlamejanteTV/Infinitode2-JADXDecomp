package com.d.m;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/d/m/h.class */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    private d f1423a;

    /* renamed from: b, reason: collision with root package name */
    private Object f1424b;
    private int c;
    private int d;

    private h(d dVar, Object obj) {
        this.f1423a = dVar;
        this.f1424b = obj;
    }

    public h() {
        this(d.c, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
    }

    public h(int i, int i2, d dVar, Object obj) {
        this(dVar, obj);
        b(Math.max(1, i2));
        a(Math.max(1, i));
    }

    public final d a() {
        return this.f1423a;
    }

    private Object d() {
        return this.f1424b;
    }

    public final void a(Graphics2D graphics2D) {
        graphics2D.setRenderingHints(e());
    }

    private Map e() {
        HashMap hashMap = new HashMap();
        hashMap.put(RenderingHints.KEY_INTERPOLATION, d());
        return hashMap;
    }

    public final boolean a(int i, int i2) {
        return i == b() && i2 == c();
    }

    public final int b() {
        return this.c;
    }

    public final int c() {
        return this.d;
    }

    public final void a(int i) {
        this.c = i;
    }

    public final void b(int i) {
        this.d = i;
    }
}
