package com.d.b;

import com.d.d.s;
import com.d.i.a.r;
import com.d.m.l;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.logging.Level;

/* loaded from: infinitode-2.jar:com/d/b/c.class */
public final class c implements com.d.c.b.c {

    /* renamed from: a, reason: collision with root package name */
    private s f962a;

    /* renamed from: b, reason: collision with root package name */
    private final LinkedHashMap<String, r> f963b = new d(this, 16, 0.75f, true);
    private com.d.c.d.c c = new com.d.c.d.c(new e(this));

    public c(s sVar) {
        this.f962a = sVar;
    }

    @Override // com.d.c.b.c
    public final r a(Reader reader, com.d.l.b bVar) {
        try {
            return this.c.a(bVar.a(), bVar.c(), reader);
        } catch (IOException e) {
            l.a(Level.WARNING, "Couldn't parse stylesheet at URI " + bVar.a() + ": " + e.getMessage(), e);
            return new r(bVar.a(), bVar.c());
        }
    }

    private r b(com.d.l.b bVar) {
        Reader b2;
        com.d.j.b a2 = this.f962a.a(bVar.a());
        if (a2 == null || (b2 = a2.b()) == null) {
            return null;
        }
        try {
            return a(b2, bVar);
        } finally {
            try {
                b2.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.d.c.b.c
    public final com.d.c.e.d a(int i, String str) {
        return this.c.a(2, str);
    }

    @Deprecated
    private void a(String str, r rVar) {
        this.f963b.put(str, rVar);
    }

    @Deprecated
    public final boolean a(String str) {
        return this.f963b.containsKey(str);
    }

    @Deprecated
    private r c(String str) {
        return this.f963b.get(str);
    }

    @Deprecated
    public final Object b(String str) {
        return this.f963b.remove(str);
    }

    @Deprecated
    public final void a() {
        this.f963b.clear();
    }

    public final r a(com.d.l.b bVar) {
        l.e("Requesting stylesheet: " + bVar.a());
        r c = c(bVar.a());
        r rVar = c;
        if (c == null && !a(bVar.a())) {
            rVar = b(bVar);
            a(bVar.a(), rVar);
        }
        return rVar;
    }

    public final void a(s sVar) {
        this.f962a = sVar;
    }

    public final void a(boolean z) {
        this.c.a(z);
    }
}
