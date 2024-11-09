package com.a.a.c.d;

import com.a.a.b.j;
import com.a.a.b.l;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/d/a.class */
public final class a extends g {
    private a(l lVar, String str, j jVar, Class<?> cls, String str2, Collection<Object> collection) {
        super(lVar, str, jVar, cls, str2, collection);
    }

    public static a a(l lVar, Object obj, String str, Collection<Object> collection) {
        Class<?> cls;
        if (obj instanceof Class) {
            cls = (Class) obj;
        } else {
            cls = obj.getClass();
        }
        a aVar = new a(lVar, String.format("Ignored field \"%s\" (class %s) encountered; mapper configured not to allow this", str, cls.getName()), lVar.e(), cls, str, collection);
        aVar.a(obj, str);
        return aVar;
    }
}
