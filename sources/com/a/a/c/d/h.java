package com.a.a.c.d;

import com.a.a.b.j;
import com.a.a.b.l;
import java.util.Collection;

/* loaded from: infinitode-2.jar:com/a/a/c/d/h.class */
public final class h extends g {
    private h(l lVar, String str, j jVar, Class<?> cls, String str2, Collection<Object> collection) {
        super(lVar, str, jVar, cls, str2, collection);
    }

    public static h a(l lVar, Object obj, String str, Collection<Object> collection) {
        Class<?> cls;
        if (obj instanceof Class) {
            cls = (Class) obj;
        } else {
            cls = obj.getClass();
        }
        h hVar = new h(lVar, String.format("Unrecognized field \"%s\" (class %s), not marked as ignorable", str, cls.getName()), lVar.e(), cls, str, collection);
        hVar.a(obj, str);
        return hVar;
    }
}
