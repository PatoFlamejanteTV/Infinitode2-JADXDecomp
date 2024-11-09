package com.a.a.c;

import com.a.a.a.al;
import com.a.a.a.an;
import com.a.a.c.f.ad;
import com.a.a.c.i.c;
import com.a.a.c.m.k;
import java.lang.reflect.Type;

/* loaded from: infinitode-2.jar:com/a/a/c/d.class */
public abstract class d {
    public abstract com.a.a.c.b.q<?> a();

    protected abstract l a(j jVar, String str, String str2);

    public abstract com.a.a.c.l.o b();

    public abstract <T> T a(j jVar, String str);

    public final j a(Type type) {
        if (type == null) {
            return null;
        }
        return b().a(type);
    }

    public final j a(j jVar, String str, com.a.a.c.i.c cVar) {
        int indexOf = str.indexOf(60);
        if (indexOf > 0) {
            return a(jVar, str, cVar, indexOf);
        }
        com.a.a.c.b.q<?> a2 = a();
        c.b a3 = cVar.a();
        if (a3 == c.b.DENIED) {
            return (j) b(jVar, str, cVar);
        }
        try {
            Class<?> a4 = b().a(str);
            if (!jVar.c(a4)) {
                return (j) b(jVar, str);
            }
            j a5 = a2.p().a(jVar, a4);
            if (a3 == c.b.INDETERMINATE && cVar.a(a2, jVar, a5) != c.b.ALLOWED) {
                return (j) c(jVar, str, cVar);
            }
            return a5;
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (Exception e) {
            throw a(jVar, str, String.format("problem: (%s) %s", e.getClass().getName(), com.a.a.c.m.i.g(e)));
        }
    }

    private j a(j jVar, String str, com.a.a.c.i.c cVar, int i) {
        com.a.a.c.b.q<?> a2 = a();
        str.substring(0, i);
        c.b a3 = cVar.a();
        if (a3 == c.b.DENIED) {
            return (j) b(jVar, str, cVar);
        }
        j b2 = b().b(str);
        if (!b2.b(jVar.b())) {
            return (j) b(jVar, str);
        }
        if (a3 != c.b.ALLOWED && cVar.a(a2, jVar, b2) != c.b.ALLOWED) {
            return (j) c(jVar, str, cVar);
        }
        return b2;
    }

    private <T> T b(j jVar, String str) {
        throw a(jVar, str, "Not a subtype");
    }

    private <T> T b(j jVar, String str, com.a.a.c.i.c cVar) {
        throw a(jVar, str, "Configured `PolymorphicTypeValidator` (of type " + com.a.a.c.m.i.d(cVar) + ") denied resolution");
    }

    private <T> T c(j jVar, String str, com.a.a.c.i.c cVar) {
        throw a(jVar, str, "Configured `PolymorphicTypeValidator` (of type " + com.a.a.c.m.i.d(cVar) + ") denied resolution");
    }

    public final al<?> a(com.a.a.c.f.b bVar, ad adVar) {
        Class<? extends al<?>> d = adVar.d();
        com.a.a.c.b.q<?> a2 = a();
        al<?> j = a2.m() == null ? null : com.a.a.c.k.a.d.j();
        al<?> alVar = j;
        if (j == null) {
            alVar = (al) com.a.a.c.m.i.b(d, a2.g());
        }
        return alVar.a(adVar.c());
    }

    public final an b(com.a.a.c.f.b bVar, ad adVar) {
        Class<? extends an> e = adVar.e();
        com.a.a.c.b.q<?> a2 = a();
        an k = a2.m() == null ? null : com.a.a.c.k.a.d.k();
        an anVar = k;
        if (k == null) {
            anVar = (an) com.a.a.c.m.i.b(e, a2.g());
        }
        return anVar;
    }

    public final com.a.a.c.m.k<Object, Object> a(com.a.a.c.f.b bVar, Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof com.a.a.c.m.k) {
            return (com.a.a.c.m.k) obj;
        }
        if (!(obj instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned Converter definition of type " + obj.getClass().getName() + "; expected type Converter or Class<Converter> instead");
        }
        Class cls = (Class) obj;
        if (cls != k.a.class && !com.a.a.c.m.i.e((Class<?>) cls)) {
            if (!com.a.a.c.m.k.class.isAssignableFrom(cls)) {
                throw new IllegalStateException("AnnotationIntrospector returned Class " + cls.getName() + "; expected Class<Converter>");
            }
            com.a.a.c.b.q<?> a2 = a();
            com.a.a.c.m.k<?, ?> m = a2.m() == null ? null : com.a.a.c.k.a.d.m();
            com.a.a.c.m.k<?, ?> kVar = m;
            if (m == null) {
                kVar = (com.a.a.c.m.k) com.a.a.c.m.i.b(cls, a2.g());
            }
            return kVar;
        }
        return null;
    }

    public final <T> T a(Class<?> cls, String str) {
        return (T) a(a(cls), str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(String str, Object... objArr) {
        if (objArr.length > 0) {
            return String.format(str, objArr);
        }
        return str;
    }

    private static String b(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() <= 500) {
            return str;
        }
        return str.substring(0, 500) + "]...[" + str.substring(str.length() - 500);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String a(String str) {
        if (str == null) {
            return "[N/A]";
        }
        return String.format("\"%s\"", b(str));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static String a(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        return str + ": " + str2;
    }
}
