package com.a.a.c.d;

import com.a.a.c.j;
import com.a.a.c.l;

/* loaded from: infinitode-2.jar:com/a/a/c/d/f.class */
public class f extends l {
    /* JADX INFO: Access modifiers changed from: protected */
    public f(com.a.a.b.l lVar, String str) {
        this(lVar, str, (j) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(com.a.a.b.l lVar, String str, com.a.a.b.j jVar) {
        super(lVar, str, jVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public f(com.a.a.b.l lVar, String str, Class<?> cls) {
        super(lVar, str);
    }

    private f(com.a.a.b.l lVar, String str, j jVar) {
        super(lVar, str);
        com.a.a.c.m.i.a(jVar);
    }

    public static f a(com.a.a.b.l lVar, j jVar, String str) {
        return new f(lVar, str, jVar);
    }

    public static f a(com.a.a.b.l lVar, Class<?> cls, String str) {
        return new f(lVar, str, cls);
    }

    public final f a(j jVar) {
        jVar.b();
        return this;
    }
}
