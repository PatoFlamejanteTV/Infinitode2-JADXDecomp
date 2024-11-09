package org.a.c.h.c;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/c/h/c/l.class */
public final class l {

    /* renamed from: a, reason: collision with root package name */
    public static final l f4508a = new l();

    /* renamed from: b, reason: collision with root package name */
    private final Map<String, Class<? extends k>> f4509b = new HashMap();
    private final Map<Class<? extends e>, Class<? extends k>> c = new HashMap();

    private l() {
        a("Standard", p.class, o.class);
        a("Adobe.PubSec", h.class, g.class);
    }

    private void a(String str, Class<? extends k> cls, Class<? extends e> cls2) {
        if (this.f4509b.containsKey(str)) {
            throw new IllegalStateException("The security handler name is already registered");
        }
        this.f4509b.put(str, cls);
        this.c.put(cls2, cls);
    }

    public final k a(e eVar) {
        Class<? extends k> cls = this.c.get(eVar.getClass());
        if (cls == null) {
            return null;
        }
        return a(cls, (Class<?>[]) new Class[]{eVar.getClass()}, new Object[]{eVar});
    }

    public final k a(String str) {
        Class<? extends k> cls = this.f4509b.get(str);
        if (cls == null) {
            return null;
        }
        return a(cls, (Class<?>[]) new Class[0], new Object[0]);
    }

    private static k a(Class<? extends k> cls, Class<?>[] clsArr, Object[] objArr) {
        try {
            return cls.getDeclaredConstructor(clsArr).newInstance(objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e2) {
            throw new RuntimeException(e2);
        } catch (NoSuchMethodException e3) {
            throw new RuntimeException(e3);
        } catch (InvocationTargetException e4) {
            throw new RuntimeException(e4);
        }
    }
}
