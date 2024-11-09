package com.a.a.c.d;

import com.a.a.c.j;
import com.a.a.c.w;

/* loaded from: infinitode-2.jar:com/a/a/c/d/d.class */
public final class d extends f {
    private d(com.a.a.c.g gVar, String str, w wVar) {
        super(gVar.j(), str);
    }

    public static d a(com.a.a.c.g gVar, w wVar, j jVar) {
        d dVar = new d(gVar, String.format("Invalid `null` value encountered for property %s", com.a.a.c.m.i.a((Object) wVar, "<UNKNOWN>")), wVar);
        if (jVar != null) {
            dVar.a(jVar);
        }
        return dVar;
    }
}
