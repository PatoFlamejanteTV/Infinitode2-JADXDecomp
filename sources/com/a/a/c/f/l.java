package com.a.a.c.f;

import com.a.a.c.f.an;
import com.a.a.c.f.t;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: infinitode-2.jar:com/a/a/c/f/l.class */
public final class l extends u {
    private final t.a d;
    private final boolean e;

    private l(com.a.a.c.a aVar, t.a aVar2, boolean z) {
        super(aVar);
        this.d = aVar == null ? null : aVar2;
        this.e = z;
    }

    public static m a(com.a.a.c.a aVar, an anVar, t.a aVar2, com.a.a.c.l.o oVar, com.a.a.c.j jVar, List<com.a.a.c.j> list, Class<?> cls, boolean z) {
        return new l(aVar, aVar2, z).a(oVar, anVar, jVar, list, cls);
    }

    private m a(com.a.a.c.l.o oVar, an anVar, com.a.a.c.j jVar, List<com.a.a.c.j> list, Class<?> cls) {
        Class<?> i;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        a(anVar, jVar.b(), linkedHashMap, cls);
        for (com.a.a.c.j jVar2 : list) {
            a(new an.a(oVar, jVar2.x()), jVar2.b(), linkedHashMap, this.d == null ? null : this.d.i(jVar2.b()));
        }
        boolean z = false;
        if (this.d != null && (i = this.d.i(Object.class)) != null) {
            b(anVar, jVar.b(), linkedHashMap, i);
            z = true;
        }
        if (z && this.c != null && !linkedHashMap.isEmpty()) {
            for (Map.Entry<z, a> entry : linkedHashMap.entrySet()) {
                z key = entry.getKey();
                if ("hashCode".equals(key.a()) && 0 == key.b()) {
                    try {
                        Method declaredMethod = Object.class.getDeclaredMethod(key.a(), new Class[0]);
                        if (declaredMethod != null) {
                            a value = entry.getValue();
                            value.c = b(value.c, declaredMethod.getDeclaredAnnotations());
                            value.f460b = declaredMethod;
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        if (linkedHashMap.isEmpty()) {
            return new m();
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(linkedHashMap.size());
        for (Map.Entry<z, a> entry2 : linkedHashMap.entrySet()) {
            k a2 = entry2.getValue().a();
            if (a2 != null) {
                linkedHashMap2.put(entry2.getKey(), a2);
            }
        }
        return new m(linkedHashMap2);
    }

    private void a(an anVar, Class<?> cls, Map<z, a> map, Class<?> cls2) {
        if (cls2 != null) {
            b(anVar, cls, map, cls2);
        }
        if (cls == null) {
            return;
        }
        for (Method method : com.a.a.c.m.i.p(cls)) {
            if (a(method)) {
                z zVar = new z(method);
                a aVar = map.get(zVar);
                if (aVar == null) {
                    map.put(zVar, new a(anVar, method, this.c == null ? p.b() : a(method.getDeclaredAnnotations())));
                } else {
                    if (this.e) {
                        aVar.c = b(aVar.c, method.getDeclaredAnnotations());
                    }
                    Method method2 = aVar.f460b;
                    if (method2 == null) {
                        aVar.f460b = method;
                    } else if (Modifier.isAbstract(method2.getModifiers()) && !Modifier.isAbstract(method.getModifiers())) {
                        aVar.f460b = method;
                        aVar.f459a = anVar;
                    }
                }
            }
        }
    }

    private void b(an anVar, Class<?> cls, Map<z, a> map, Class<?> cls2) {
        if (this.c == null) {
            return;
        }
        Iterator<Class<?>> it = com.a.a.c.m.i.a(cls2, cls, true).iterator();
        while (it.hasNext()) {
            for (Method method : it.next().getDeclaredMethods()) {
                if (a(method)) {
                    z zVar = new z(method);
                    a aVar = map.get(zVar);
                    Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                    if (aVar == null) {
                        map.put(zVar, new a(anVar, null, a(declaredAnnotations)));
                    } else {
                        aVar.c = b(aVar.c, declaredAnnotations);
                    }
                }
            }
        }
    }

    private static boolean a(Method method) {
        return (Modifier.isStatic(method.getModifiers()) || method.isSynthetic() || method.isBridge() || method.getParameterCount() > 2) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:com/a/a/c/f/l$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        public an f459a;

        /* renamed from: b, reason: collision with root package name */
        public Method f460b;
        public p c;

        public a(an anVar, Method method, p pVar) {
            this.f459a = anVar;
            this.f460b = method;
            this.c = pVar;
        }

        public final k a() {
            if (this.f460b == null) {
                return null;
            }
            return new k(this.f459a, this.f460b, this.c.d(), null);
        }
    }
}
