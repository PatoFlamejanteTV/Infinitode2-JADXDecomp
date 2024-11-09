package org.a.d.b;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/* loaded from: infinitode-2.jar:org/a/d/b/ao.class */
public final class ao {

    /* renamed from: a, reason: collision with root package name */
    private Map<ap, ae> f4670a;

    /* renamed from: b, reason: collision with root package name */
    private Map<String, ap> f4671b;
    private final org.a.d.b c;
    private Map<String, org.a.d.a.m> d;
    private static final Class<?>[] e = {org.a.d.b.class, String.class, String.class, String.class, Object.class};

    public ao(org.a.d.b bVar) {
        this.c = bVar;
        a();
    }

    private void a() {
        this.f4670a = new EnumMap(ap.class);
        this.f4671b = new HashMap();
        for (ap apVar : ap.values()) {
            if (apVar.a()) {
                Class<? extends U> asSubclass = apVar.b().asSubclass(d.class);
                String a2 = ((al) asSubclass.getAnnotation(al.class)).a();
                ae b2 = b(asSubclass);
                this.f4671b.put(a2, apVar);
                this.f4670a.put(apVar, b2);
            }
        }
        new HashMap();
        new HashMap();
        this.d = new HashMap();
        a(org.a.d.a.i.class);
        a(org.a.d.a.b.class);
        a(org.a.d.a.d.class);
        a(org.a.d.a.j.class);
        a(org.a.d.a.a.class);
        a(org.a.d.a.e.class);
        a(org.a.d.a.k.class);
        a(org.a.d.a.f.class);
        a(org.a.d.a.h.class);
        a(org.a.d.a.c.class);
        a(org.a.d.a.g.class);
        a(org.a.d.a.n.class);
    }

    public final c a(String str, String str2, String str3, Object obj, ap apVar) {
        Object[] objArr = {this.c, str, str2, str3, obj};
        Class<? extends U> asSubclass = apVar.b().asSubclass(c.class);
        try {
            return (c) asSubclass.getDeclaredConstructor(e).newInstance(objArr);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Failed to instanciate " + asSubclass.getSimpleName() + " property with value " + obj, e2);
        } catch (IllegalArgumentException e3) {
            throw new IllegalArgumentException("Failed to instanciate " + asSubclass.getSimpleName() + " property with value " + obj, e3);
        } catch (InstantiationException e4) {
            throw new IllegalArgumentException("Failed to instanciate " + asSubclass.getSimpleName() + " property with value " + obj, e4);
        } catch (NoSuchMethodError e5) {
            throw new IllegalArgumentException("Failed to instanciate " + asSubclass.getSimpleName() + " property with value " + obj, e5);
        } catch (NoSuchMethodException e6) {
            throw new IllegalArgumentException("Failed to instanciate " + asSubclass.getSimpleName() + " property with value " + obj, e6);
        } catch (SecurityException e7) {
            throw new IllegalArgumentException("Failed to instanciate " + asSubclass.getSimpleName() + " property with value " + obj, e7);
        } catch (InvocationTargetException e8) {
            throw new IllegalArgumentException("Failed to instanciate " + asSubclass.getSimpleName() + " property with value " + obj, e8);
        }
    }

    public final c a(Class<?> cls, String str, String str2, String str3, Object obj) {
        return a(str, str2, str3, obj, b(cls).a(str3).a());
    }

    private void a(Class<? extends org.a.d.a.l> cls) {
        String a2 = ((al) cls.getAnnotation(al.class)).a();
        this.d.put(a2, new org.a.d.a.m(a2, cls, b(cls)));
    }

    private static ae b(Class<?> cls) {
        ae aeVar = new ae();
        String str = null;
        for (Field field : cls.getFields()) {
            if (field.isAnnotationPresent(af.class)) {
                try {
                    str = (String) field.get(str);
                    aeVar.a(str, (af) field.getAnnotation(af.class));
                } catch (Exception e2) {
                    throw new IllegalArgumentException("couldn't read one type declaration, please check accessibility and declaration of fields annoted in " + cls.getName(), e2);
                }
            }
        }
        return aeVar;
    }

    public final am a(String str, String str2, String str3, String str4) {
        return new am(this.c, str, str2, str3, str4);
    }

    public final f a(String str, String str2, String str3, k kVar) {
        return new f(this.c, str, str2, str3, kVar);
    }
}
