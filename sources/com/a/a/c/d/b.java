package com.a.a.c.d;

import com.a.a.c.f.s;
import com.a.a.c.j;
import com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/d/b.class */
public final class b extends l {
    private b(com.a.a.b.l lVar, String str, j jVar) {
        super(lVar, str);
    }

    private b(com.a.a.b.h hVar, String str, j jVar) {
        super(hVar, str);
    }

    private b(com.a.a.b.l lVar, String str, com.a.a.c.b bVar, s sVar) {
        super(lVar, str);
        if (bVar != null) {
            bVar.a();
        }
    }

    private b(com.a.a.b.h hVar, String str, com.a.a.c.b bVar, s sVar) {
        super(hVar, str);
        if (bVar != null) {
            bVar.a();
        }
    }

    public static b a(com.a.a.b.l lVar, String str, com.a.a.c.b bVar, s sVar) {
        return new b(lVar, str, bVar, sVar);
    }

    public static b a(com.a.a.b.l lVar, String str, j jVar) {
        return new b(lVar, str, jVar);
    }

    public static b a(com.a.a.b.h hVar, String str, com.a.a.c.b bVar, s sVar) {
        return new b(hVar, str, bVar, sVar);
    }

    public static b a(com.a.a.b.h hVar, String str, j jVar) {
        return new b(hVar, str, jVar);
    }
}
