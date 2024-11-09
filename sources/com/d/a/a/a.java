package com.d.a.a;

import com.b.a.c.c;
import com.d.m.l;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/a/a/a.class */
public final class a implements com.d.a.a {

    /* renamed from: a, reason: collision with root package name */
    private com.b.a.c.a f951a = new com.b.a.c.a(8);

    /* renamed from: b, reason: collision with root package name */
    private com.b.a.c.a f952b = new com.b.a.c.a(16);

    @Override // com.d.a.a
    public final String a(String str) {
        return c.a(str, 2);
    }

    @Override // com.d.a.a
    public final String b(String str) {
        try {
            return this.f951a.a(str);
        } catch (com.b.a.c.b e) {
            l.c(Level.WARNING, "Exception while shaping text", e);
            return str;
        }
    }

    @Override // com.d.a.a
    public final String c(String str) {
        try {
            return this.f952b.a(str);
        } catch (com.b.a.c.b e) {
            l.c(Level.WARNING, "Exception while deshaping text", e);
            return str;
        }
    }

    @Override // com.d.a.a
    public final boolean b() {
        return true;
    }
}
