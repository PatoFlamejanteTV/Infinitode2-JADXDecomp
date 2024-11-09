package com.a.a.c.l;

import com.a.a.c.m.q;
import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: infinitode-2.jar:com/a/a/c/l/o.class */
public final class o implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    private static final com.a.a.c.j[] f665a = new com.a.a.c.j[0];

    /* renamed from: b, reason: collision with root package name */
    private static o f666b = new o();
    private static n c = n.a();
    private static final Class<?> d = String.class;
    private static final Class<?> e = Object.class;
    private static final Class<?> f = Comparable.class;
    private static final Class<?> g = Enum.class;
    private static final Class<?> h = com.a.a.c.m.class;
    private static final Class<?> i = Boolean.TYPE;
    private static final Class<?> j = Integer.TYPE;
    private static final Class<?> k = Long.TYPE;
    private static l l = new l(i);
    private static l m = new l(j);
    private static l n = new l(k);
    private static l o = new l(d);
    private static l p = new l(e);
    private static l q = new l(f);
    private static l r = new l(g);
    private static l s = new l(h);
    private q<Object, com.a.a.c.j> t;
    private com.a.a.c.k.a.d[] u;
    private p v;
    private ClassLoader w;

    private o() {
        this((q) null);
    }

    private o(q<Object, com.a.a.c.j> qVar) {
        this.t = qVar == null ? new com.a.a.c.m.o(16, 200) : qVar;
        this.v = new p(this);
        this.u = null;
        this.w = null;
    }

    public static o a() {
        return f666b;
    }

    private ClassLoader c() {
        return this.w;
    }

    public static com.a.a.c.j b() {
        return d();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v17, types: [java.lang.Class<?>, java.lang.Class] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public final Class<?> a(String str) {
        Class<?> d2;
        if (str.indexOf(46) < 0 && (d2 = d(str)) != null) {
            return d2;
        }
        Throwable th = null;
        ClassLoader c2 = c();
        ClassLoader classLoader = c2;
        if (c2 == null) {
            classLoader = Thread.currentThread().getContextClassLoader();
        }
        ?? r0 = classLoader;
        if (r0 != 0) {
            try {
                r0 = a(str, classLoader);
                return r0;
            } catch (Exception e2) {
                th = com.a.a.c.m.i.d((Throwable) r0);
            }
        }
        try {
            return c(str);
        } catch (Exception e3) {
            if (th == null) {
                th = com.a.a.c.m.i.d((Throwable) e3);
            }
            com.a.a.c.m.i.b(th);
            throw new ClassNotFoundException(th.getMessage(), th);
        }
    }

    private static Class<?> a(String str, ClassLoader classLoader) {
        return Class.forName(str, true, classLoader);
    }

    private static Class<?> c(String str) {
        return Class.forName(str);
    }

    private static Class<?> d(String str) {
        if ("int".equals(str)) {
            return Integer.TYPE;
        }
        if ("long".equals(str)) {
            return Long.TYPE;
        }
        if ("float".equals(str)) {
            return Float.TYPE;
        }
        if ("double".equals(str)) {
            return Double.TYPE;
        }
        if ("boolean".equals(str)) {
            return Boolean.TYPE;
        }
        if ("byte".equals(str)) {
            return Byte.TYPE;
        }
        if ("char".equals(str)) {
            return Character.TYPE;
        }
        if ("short".equals(str)) {
            return Short.TYPE;
        }
        if ("void".equals(str)) {
            return Void.TYPE;
        }
        return null;
    }

    public final com.a.a.c.j a(com.a.a.c.j jVar, Class<?> cls) {
        return a(jVar, cls, false);
    }

    public final com.a.a.c.j a(com.a.a.c.j jVar, Class<?> cls, boolean z) {
        com.a.a.c.j a2;
        Class<?> b2 = jVar.b();
        if (b2 == cls) {
            return jVar;
        }
        if (b2 == Object.class) {
            a2 = a((c) null, cls, c);
        } else {
            if (!b2.isAssignableFrom(cls)) {
                throw new IllegalArgumentException(String.format("Class %s not subtype of %s", com.a.a.c.m.i.g(cls), com.a.a.c.m.i.b(jVar)));
            }
            if (jVar.n()) {
                if (jVar.p()) {
                    if (cls == HashMap.class || cls == LinkedHashMap.class || cls == EnumMap.class || cls == TreeMap.class) {
                        a2 = a((c) null, cls, n.a(cls, jVar.t(), jVar.u()));
                    }
                } else if (jVar.o()) {
                    if (cls == ArrayList.class || cls == LinkedList.class || cls == HashSet.class || cls == TreeSet.class) {
                        a2 = a((c) null, cls, n.a(cls, jVar.u()));
                    } else if (b2 == EnumSet.class) {
                        return jVar;
                    }
                }
            }
            if (jVar.x().b()) {
                a2 = a((c) null, cls, c);
            } else {
                int length = cls.getTypeParameters().length;
                if (length == 0) {
                    a2 = a((c) null, cls, c);
                } else {
                    a2 = a((c) null, cls, a(jVar, length, cls, z));
                }
            }
        }
        return a2.b(jVar);
    }

    private n a(com.a.a.c.j jVar, int i2, Class<?> cls, boolean z) {
        i[] iVarArr = new i[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            iVarArr[i3] = new i(i3);
        }
        com.a.a.c.j d2 = a((c) null, cls, n.a(cls, iVarArr)).d(jVar.b());
        if (d2 == null) {
            throw new IllegalArgumentException(String.format("Internal error: unable to locate supertype (%s) from resolved subtype %s", jVar.b().getName(), cls.getName()));
        }
        String a2 = a(jVar, d2);
        if (a2 != null && !z) {
            throw new IllegalArgumentException("Failed to specialize base type " + jVar.G() + " as " + cls.getName() + ", problem: " + a2);
        }
        com.a.a.c.j[] jVarArr = new com.a.a.c.j[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            com.a.a.c.j H = iVarArr[i4].H();
            com.a.a.c.j jVar2 = H;
            if (H == null) {
                jVar2 = b();
            }
            jVarArr[i4] = jVar2;
        }
        return n.a(cls, jVarArr);
    }

    private String a(com.a.a.c.j jVar, com.a.a.c.j jVar2) {
        List<com.a.a.c.j> d2 = jVar.x().d();
        List<com.a.a.c.j> d3 = jVar2.x().d();
        int size = d3.size();
        int i2 = 0;
        int size2 = d2.size();
        while (i2 < size2) {
            com.a.a.c.j jVar3 = d2.get(i2);
            com.a.a.c.j b2 = i2 < size ? d3.get(i2) : b();
            if (b(jVar3, b2) || jVar3.a(Object.class) || ((i2 == 0 && jVar.p() && b2.a(Object.class)) || (jVar3.k() && jVar3.c(b2.b())))) {
                i2++;
            } else {
                return String.format("Type parameter #%d/%d differs; can not specialize %s with %s", Integer.valueOf(i2 + 1), Integer.valueOf(size2), jVar3.G(), b2.G());
            }
        }
        return null;
    }

    private boolean b(com.a.a.c.j jVar, com.a.a.c.j jVar2) {
        if (jVar2 instanceof i) {
            ((i) jVar2).e(jVar);
            return true;
        }
        if (jVar.b() != jVar2.b()) {
            return false;
        }
        List<com.a.a.c.j> d2 = jVar.x().d();
        List<com.a.a.c.j> d3 = jVar2.x().d();
        int size = d2.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (!b(d2.get(i2), d3.get(i2))) {
                return false;
            }
        }
        return true;
    }

    public static com.a.a.c.j b(com.a.a.c.j jVar, Class<?> cls) {
        Class<?> b2 = jVar.b();
        if (b2 == cls) {
            return jVar;
        }
        com.a.a.c.j d2 = jVar.d(cls);
        if (d2 == null) {
            if (!cls.isAssignableFrom(b2)) {
                throw new IllegalArgumentException(String.format("Class %s not a super-type of %s", cls.getName(), jVar));
            }
            throw new IllegalArgumentException(String.format("Internal error: class %s not included as super-type for %s", cls.getName(), jVar));
        }
        return d2;
    }

    public final com.a.a.c.j b(String str) {
        return this.v.a(str);
    }

    public static com.a.a.c.j[] c(com.a.a.c.j jVar, Class<?> cls) {
        com.a.a.c.j d2 = jVar.d(cls);
        if (d2 == null) {
            return f665a;
        }
        return d2.x().e();
    }

    public final com.a.a.c.j a(Type type) {
        return a((c) null, type, c);
    }

    public final com.a.a.c.j a(Type type, n nVar) {
        return a((c) null, type, nVar);
    }

    public final e a(Class<? extends Collection> cls, Class<?> cls2) {
        return a(cls, a((c) null, cls2, c));
    }

    public final e a(Class<? extends Collection> cls, com.a.a.c.j jVar) {
        n b2 = n.b(cls, jVar);
        e eVar = (e) a((c) null, (Class<?>) cls, b2);
        if (b2.b() && jVar != null) {
            com.a.a.c.j u = eVar.d(Collection.class).u();
            if (!u.equals(jVar)) {
                throw new IllegalArgumentException(String.format("Non-generic Collection class %s did not resolve to something with element type %s but %s ", com.a.a.c.m.i.g(cls), jVar, u));
            }
        }
        return eVar;
    }

    public final h a(Class<? extends Map> cls, Class<?> cls2, Class<?> cls3) {
        com.a.a.c.j a2;
        com.a.a.c.j a3;
        if (cls == Properties.class) {
            l lVar = o;
            a3 = lVar;
            a2 = lVar;
        } else {
            a2 = a((c) null, cls2, c);
            a3 = a((c) null, cls3, c);
        }
        return a(cls, a2, a3);
    }

    public final h a(Class<? extends Map> cls, com.a.a.c.j jVar, com.a.a.c.j jVar2) {
        n b2 = n.b(cls, new com.a.a.c.j[]{jVar, jVar2});
        h hVar = (h) a((c) null, (Class<?>) cls, b2);
        if (b2.b()) {
            com.a.a.c.j d2 = hVar.d(Map.class);
            com.a.a.c.j t = d2.t();
            if (!t.equals(jVar)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with key type %s but %s ", com.a.a.c.m.i.g(cls), jVar, t));
            }
            com.a.a.c.j u = d2.u();
            if (!u.equals(jVar2)) {
                throw new IllegalArgumentException(String.format("Non-generic Map class %s did not resolve to something with value type %s but %s ", com.a.a.c.m.i.g(cls), jVar2, u));
            }
        }
        return hVar;
    }

    @Deprecated
    public final com.a.a.c.j a(Class<?> cls) {
        return d(cls, c, null, null);
    }

    public final com.a.a.c.j a(Class<?> cls, n nVar) {
        return a(cls, a((c) null, cls, nVar));
    }

    private com.a.a.c.j a(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        com.a.a.c.j jVar2;
        com.a.a.c.j jVar3;
        if (cls == Properties.class) {
            l lVar = o;
            jVar3 = lVar;
            jVar2 = lVar;
        } else {
            List<com.a.a.c.j> d2 = nVar.d();
            int size = d2.size();
            switch (size) {
                case 0:
                    com.a.a.c.j d3 = d();
                    jVar3 = d3;
                    jVar2 = d3;
                    break;
                case 2:
                    jVar2 = d2.get(0);
                    jVar3 = d2.get(1);
                    break;
                default:
                    Object[] objArr = new Object[4];
                    objArr[0] = com.a.a.c.m.i.g(cls);
                    objArr[1] = Integer.valueOf(size);
                    objArr[2] = size == 1 ? "" : "s";
                    objArr[3] = nVar;
                    throw new IllegalArgumentException(String.format("Strange Map type %s with %d type parameter%s (%s), can not resolve", objArr));
            }
        }
        return h.a(cls, nVar, jVar, jVarArr, jVar2, jVar3);
    }

    private com.a.a.c.j b(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        com.a.a.c.j jVar2;
        List<com.a.a.c.j> d2 = nVar.d();
        if (d2.isEmpty()) {
            jVar2 = d();
        } else if (d2.size() == 1) {
            jVar2 = d2.get(0);
        } else {
            throw new IllegalArgumentException("Strange Collection type " + cls.getName() + ": cannot determine type parameters");
        }
        return e.a(cls, nVar, jVar, jVarArr, jVar2);
    }

    private com.a.a.c.j c(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        com.a.a.c.j jVar2;
        List<com.a.a.c.j> d2 = nVar.d();
        if (d2.isEmpty()) {
            jVar2 = d();
        } else if (d2.size() == 1) {
            jVar2 = d2.get(0);
        } else {
            throw new IllegalArgumentException("Strange Reference type " + cls.getName() + ": cannot determine type parameters");
        }
        return j.a(cls, nVar, jVar, jVarArr, jVar2);
    }

    private com.a.a.c.j d(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        com.a.a.c.j b2;
        if (nVar.b() && (b2 = b(cls)) != null) {
            return b2;
        }
        return e(cls, nVar, jVar, jVarArr);
    }

    private static com.a.a.c.j e(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        return new l(cls, nVar, jVar, jVarArr);
    }

    private static com.a.a.c.j d() {
        return p;
    }

    private static com.a.a.c.j b(Class<?> cls) {
        if (cls.isPrimitive()) {
            if (cls == i) {
                return l;
            }
            if (cls == j) {
                return m;
            }
            if (cls == k) {
                return n;
            }
            return null;
        }
        if (cls == d) {
            return o;
        }
        if (cls == e) {
            return p;
        }
        if (cls == h) {
            return s;
        }
        return null;
    }

    private com.a.a.c.j a(c cVar, Type type, n nVar) {
        com.a.a.c.j a2;
        if (type instanceof Class) {
            a2 = a(cVar, (Class<?>) type, c);
        } else if (type instanceof ParameterizedType) {
            a2 = a(cVar, (ParameterizedType) type, nVar);
        } else {
            if (type instanceof com.a.a.c.j) {
                return (com.a.a.c.j) type;
            }
            if (type instanceof GenericArrayType) {
                a2 = a(cVar, (GenericArrayType) type, nVar);
            } else if (type instanceof TypeVariable) {
                a2 = a(cVar, (TypeVariable<?>) type, nVar);
            } else if (type instanceof WildcardType) {
                a2 = a(cVar, (WildcardType) type, nVar);
            } else {
                throw new IllegalArgumentException("Unrecognized Type: " + (type == null ? "[null]" : type.toString()));
            }
        }
        return a(type, a2);
    }

    private com.a.a.c.j a(Type type, com.a.a.c.j jVar) {
        if (this.u == null) {
            return jVar;
        }
        com.a.a.c.j jVar2 = jVar;
        jVar.x();
        for (com.a.a.c.k.a.d dVar : this.u) {
            com.a.a.c.j q2 = dVar.q();
            if (q2 == null) {
                throw new IllegalStateException(String.format("TypeModifier %s (of type %s) return null for type %s", dVar, dVar.getClass().getName(), jVar2));
            }
            jVar2 = q2;
        }
        return jVar2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final com.a.a.c.j a(c cVar, Class<?> cls, n nVar) {
        Object obj;
        c a2;
        com.a.a.c.j b2;
        com.a.a.c.j[] c2;
        com.a.a.c.j b3 = b(cls);
        if (b3 != null) {
            return b3;
        }
        if (nVar == null || nVar.b()) {
            obj = cls;
        } else {
            obj = nVar.a(cls);
        }
        com.a.a.c.j a3 = this.t.a(obj);
        com.a.a.c.j jVar = a3;
        if (a3 != null) {
            return jVar;
        }
        if (cVar == null) {
            a2 = new c(cls);
        } else {
            c b4 = cVar.b(cls);
            if (b4 != null) {
                k kVar = new k(cls, c);
                b4.a(kVar);
                return kVar;
            }
            a2 = cVar.a(cls);
        }
        if (cls.isArray()) {
            jVar = a.a(a(a2, (Type) cls.getComponentType(), nVar), nVar);
        } else {
            if (cls.isInterface()) {
                b2 = null;
                c2 = c(a2, cls, nVar);
            } else {
                b2 = b(a2, cls, nVar);
                c2 = c(a2, cls, nVar);
            }
            if (cls == Properties.class) {
                l lVar = o;
                jVar = h.a(cls, nVar, b2, c2, lVar, lVar);
            } else if (b2 != null) {
                jVar = b2.a(cls, nVar, b2, c2);
            }
            if (jVar == null) {
                com.a.a.c.j f2 = f(cls, nVar, b2, c2);
                jVar = f2;
                if (f2 == null) {
                    com.a.a.c.j g2 = g(cls, nVar, b2, c2);
                    jVar = g2;
                    if (g2 == null) {
                        jVar = e(cls, nVar, b2, c2);
                    }
                }
            }
        }
        a2.a(jVar);
        if (!jVar.C()) {
            this.t.b(obj, jVar);
        }
        return jVar;
    }

    private com.a.a.c.j b(c cVar, Class<?> cls, n nVar) {
        Type r2 = com.a.a.c.m.i.r(cls);
        if (r2 == null) {
            return null;
        }
        return a(cVar, r2, nVar);
    }

    private com.a.a.c.j[] c(c cVar, Class<?> cls, n nVar) {
        Type[] s2 = com.a.a.c.m.i.s(cls);
        if (s2 == null || s2.length == 0) {
            return f665a;
        }
        int length = s2.length;
        com.a.a.c.j[] jVarArr = new com.a.a.c.j[length];
        for (int i2 = 0; i2 < length; i2++) {
            jVarArr[i2] = a(cVar, s2[i2], nVar);
        }
        return jVarArr;
    }

    private com.a.a.c.j f(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        if (nVar == null) {
            nVar = c;
        }
        if (cls == Map.class) {
            return a(cls, nVar, jVar, jVarArr);
        }
        if (cls == Collection.class) {
            return b(cls, nVar, jVar, jVarArr);
        }
        if (cls == AtomicReference.class) {
            return c(cls, nVar, jVar, jVarArr);
        }
        return null;
    }

    private static com.a.a.c.j g(Class<?> cls, n nVar, com.a.a.c.j jVar, com.a.a.c.j[] jVarArr) {
        for (com.a.a.c.j jVar2 : jVarArr) {
            com.a.a.c.j a2 = jVar2.a(cls, nVar, jVar, jVarArr);
            if (a2 != null) {
                return a2;
            }
        }
        return null;
    }

    private com.a.a.c.j a(c cVar, ParameterizedType parameterizedType, n nVar) {
        n a2;
        Class<?> cls = (Class) parameterizedType.getRawType();
        if (cls == g) {
            return r;
        }
        if (cls == f) {
            return q;
        }
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        int length = actualTypeArguments == null ? 0 : actualTypeArguments.length;
        int i2 = length;
        if (length == 0) {
            a2 = c;
        } else {
            com.a.a.c.j[] jVarArr = new com.a.a.c.j[i2];
            for (int i3 = 0; i3 < i2; i3++) {
                jVarArr[i3] = a(cVar, actualTypeArguments[i3], nVar);
            }
            a2 = n.a(cls, jVarArr);
        }
        return a(cVar, cls, a2);
    }

    private com.a.a.c.j a(c cVar, GenericArrayType genericArrayType, n nVar) {
        return a.a(a(cVar, genericArrayType.getGenericComponentType(), nVar), nVar);
    }

    private com.a.a.c.j a(c cVar, TypeVariable<?> typeVariable, n nVar) {
        Type[] bounds;
        String name = typeVariable.getName();
        if (nVar == null) {
            throw new IllegalArgumentException("Null `bindings` passed (type variable \"" + name + "\")");
        }
        com.a.a.c.j b2 = nVar.b(name);
        if (b2 != null) {
            return b2;
        }
        if (nVar.c(name)) {
            return p;
        }
        n a2 = nVar.a(name);
        synchronized (typeVariable) {
            bounds = typeVariable.getBounds();
        }
        return a(cVar, bounds[0], a2);
    }

    private com.a.a.c.j a(c cVar, WildcardType wildcardType, n nVar) {
        return a(cVar, wildcardType.getUpperBounds()[0], nVar);
    }
}
