package com.a.a.c.f;

import com.a.a.c.f.an;
import com.a.a.c.f.t;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/f/i.class */
public final class i extends u {
    private final com.a.a.c.l.o d;
    private final t.a e;
    private final boolean f;

    private i(com.a.a.c.a aVar, com.a.a.c.l.o oVar, t.a aVar2, boolean z) {
        super(aVar);
        this.d = oVar;
        this.e = aVar == null ? null : aVar2;
        this.f = z;
    }

    public static List<h> a(com.a.a.c.a aVar, an anVar, t.a aVar2, com.a.a.c.l.o oVar, com.a.a.c.j jVar, boolean z) {
        return new i(aVar, oVar, aVar2, z).a(anVar, jVar);
    }

    private List<h> a(an anVar, com.a.a.c.j jVar) {
        Map<String, a> a2 = a(anVar, jVar, (Map<String, a>) null);
        if (a2 == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(a2.size());
        Iterator<a> it = a2.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    private Map<String, a> a(an anVar, com.a.a.c.j jVar, Map<String, a> map) {
        Class<?> i;
        com.a.a.c.j y = jVar.y();
        if (y == null) {
            return map;
        }
        Class<?> b2 = jVar.b();
        Map<String, a> a2 = a(new an.a(this.d, y.x()), y, map);
        for (Field field : b2.getDeclaredFields()) {
            if (a(field)) {
                if (a2 == null) {
                    a2 = new LinkedHashMap();
                }
                a aVar = new a(anVar, field);
                if (this.f) {
                    aVar.f456a = a(aVar.f456a, field.getDeclaredAnnotations());
                }
                a2.put(field.getName(), aVar);
            }
        }
        if (a2 != null && this.e != null && (i = this.e.i(b2)) != null) {
            a(i, b2, a2);
        }
        return a2;
    }

    private void a(Class<?> cls, Class<?> cls2, Map<String, a> map) {
        a aVar;
        Iterator<Class<?>> it = com.a.a.c.m.i.b(cls, cls2, true).iterator();
        while (it.hasNext()) {
            for (Field field : it.next().getDeclaredFields()) {
                if (a(field) && (aVar = map.get(field.getName())) != null) {
                    aVar.f456a = a(aVar.f456a, field.getDeclaredAnnotations());
                }
            }
        }
    }

    private static boolean a(Field field) {
        if (field.isSynthetic() || Modifier.isStatic(field.getModifiers())) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/f/i$a.class */
    public static final class a {

        /* renamed from: b, reason: collision with root package name */
        private an f455b;
        private Field c;

        /* renamed from: a, reason: collision with root package name */
        public p f456a = p.b();

        public a(an anVar, Field field) {
            this.f455b = anVar;
            this.c = field;
        }

        public final h a() {
            return new h(this.f455b, this.c, this.f456a.d());
        }
    }
}
