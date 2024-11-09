package com.a.a.c.m;

import com.a.a.b.h;
import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/* loaded from: infinitode-2.jar:com/a/a/c/m/i.class */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private static final Class<?> f723a = Object.class;

    /* renamed from: b, reason: collision with root package name */
    private static final Annotation[] f724b = new Annotation[0];
    private static final a[] c = new a[0];
    private static final Iterator<?> d = Collections.emptyIterator();

    public static <T> Iterator<T> a() {
        return (Iterator<T>) d;
    }

    public static List<Class<?>> a(Class<?> cls, Class<?> cls2, boolean z) {
        if (cls == null || cls == cls2 || cls == Object.class) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(8);
        a(cls, cls2, arrayList, true);
        return arrayList;
    }

    public static List<Class<?>> b(Class<?> cls, Class<?> cls2, boolean z) {
        ArrayList arrayList = new ArrayList(8);
        if (cls != null && cls != cls2) {
            if (z) {
                arrayList.add(cls);
            }
            while (true) {
                Class<? super Object> superclass = cls.getSuperclass();
                cls = superclass;
                if (superclass == null || cls == cls2) {
                    break;
                }
                arrayList.add(cls);
            }
        }
        return arrayList;
    }

    private static void a(Class<?> cls, Class<?> cls2, Collection<Class<?>> collection, boolean z) {
        if (cls == cls2 || cls == null || cls == Object.class) {
            return;
        }
        if (z) {
            if (collection.contains(cls)) {
                return;
            } else {
                collection.add(cls);
            }
        }
        for (Class<?> cls3 : x(cls)) {
            a(cls3, cls2, collection, true);
        }
        a(cls.getSuperclass(), cls2, collection, true);
    }

    public static String a(Class<?> cls) {
        if (cls.isAnnotation()) {
            return "annotation";
        }
        if (cls.isArray()) {
            return "array";
        }
        if (Enum.class.isAssignableFrom(cls)) {
            return "enum";
        }
        if (cls.isPrimitive()) {
            return "primitive";
        }
        return null;
    }

    public static String a(Class<?> cls, boolean z) {
        try {
            if (Modifier.isStatic(cls.getModifiers())) {
                return null;
            }
            if (v(cls)) {
                return "local/anonymous";
            }
            return null;
        } catch (NullPointerException unused) {
            return null;
        } catch (SecurityException unused2) {
            return null;
        }
    }

    public static Class<?> b(Class<?> cls) {
        if (!Modifier.isStatic(cls.getModifiers())) {
            try {
                if (v(cls)) {
                    return null;
                }
                return w(cls);
            } catch (SecurityException unused) {
                return null;
            }
        }
        return null;
    }

    public static boolean c(Class<?> cls) {
        String name = cls.getName();
        if (name.startsWith("net.sf.cglib.proxy.") || name.startsWith("org.hibernate.proxy.")) {
            return true;
        }
        return false;
    }

    public static boolean d(Class<?> cls) {
        return (cls.getModifiers() & 1536) == 0;
    }

    public static boolean e(Class<?> cls) {
        return cls == Void.class || cls == Void.TYPE || cls == com.a.a.c.a.j.class;
    }

    public static boolean f(Class<?> cls) {
        Class<? super Object> superclass = cls.getSuperclass();
        return superclass != null && "java.lang.Record".equals(superclass.getName());
    }

    private static boolean t(Class<?> cls) {
        return cls == f723a || cls.isPrimitive();
    }

    public static boolean a(Object obj, Class<?> cls) {
        return obj != null && obj.getClass() == cls;
    }

    public static void a(Class<?> cls, Object obj, String str) {
        if (obj.getClass() != cls) {
            throw new IllegalStateException(String.format("Sub-class %s (of class %s) must override method '%s'", obj.getClass().getName(), cls.getName(), str));
        }
    }

    public static Throwable a(Throwable th) {
        if (th instanceof Error) {
            throw ((Error) th);
        }
        return th;
    }

    public static Throwable b(Throwable th) {
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        return th;
    }

    public static Throwable c(Throwable th) {
        if (th instanceof IOException) {
            throw ((IOException) th);
        }
        return th;
    }

    public static Throwable d(Throwable th) {
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static Throwable e(Throwable th) {
        return c(d(th));
    }

    private static void h(Throwable th) {
        b(th, th.getMessage());
    }

    private static void b(Throwable th, String str) {
        b(th);
        a(th);
        throw new IllegalArgumentException(str, th);
    }

    public static <T> T a(com.a.a.c.g gVar, IOException iOException) {
        if (iOException instanceof com.a.a.c.l) {
            throw ((com.a.a.c.l) iOException);
        }
        throw com.a.a.c.l.a(gVar, iOException.getMessage()).a((Throwable) iOException);
    }

    public static void f(Throwable th) {
        h(d(th));
    }

    public static void a(Throwable th, String str) {
        b(d(th), str);
    }

    public static void a(com.a.a.b.h hVar, Exception exc) {
        hVar.a(h.a.AUTO_CLOSE_JSON_CONTENT);
        try {
            hVar.close();
        } catch (Exception e) {
            exc.addSuppressed(e);
        }
        c((Throwable) exc);
        b((Throwable) exc);
        throw new RuntimeException(exc);
    }

    public static void a(com.a.a.b.h hVar, Closeable closeable, Exception exc) {
        if (hVar != null) {
            hVar.a(h.a.AUTO_CLOSE_JSON_CONTENT);
            try {
                hVar.close();
            } catch (Exception e) {
                exc.addSuppressed(e);
            }
        }
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e2) {
                exc.addSuppressed(e2);
            }
        }
        c((Throwable) exc);
        b((Throwable) exc);
        throw new RuntimeException(exc);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.reflect.Constructor] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5, types: [T, java.lang.Object] */
    public static <T> T b(Class<T> cls, boolean z) {
        ?? r0 = (T) c(cls, z);
        if (r0 == 0) {
            throw new IllegalArgumentException("Class " + cls.getName() + " has no default (no arg) constructor");
        }
        try {
            r0 = (T) r0.newInstance(new Object[0]);
            return r0;
        } catch (Exception e) {
            a((Throwable) r0, "Failed to instantiate class " + cls.getName() + ", problem: " + e.getMessage());
            return null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 0, insn: 0x0066: INVOKE (r0 I:java.lang.Throwable), (r1 I:java.lang.String) STATIC call: com.a.a.c.m.i.a(java.lang.Throwable, java.lang.String):void A[MD:(java.lang.Throwable, java.lang.String):void (m)], block:B:15:0x0046 */
    public static <T> Constructor<T> c(Class<T> cls, boolean z) {
        Throwable a2;
        try {
            Constructor<T> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (z) {
                a(declaredConstructor, z);
            } else if (!Modifier.isPublic(declaredConstructor.getModifiers())) {
                throw new IllegalArgumentException("Default constructor for " + cls.getName() + " is not accessible (non-public?): not allowed to try modify access via Reflection: cannot instantiate type");
            }
            return declaredConstructor;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (Exception e) {
            a(a2, "Failed to find default constructor of class " + cls.getName() + ", problem: " + e.getMessage());
            return null;
        }
    }

    public static Class<?> a(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.getClass();
    }

    public static Class<?> a(com.a.a.c.j jVar) {
        if (jVar == null) {
            return null;
        }
        return jVar.b();
    }

    public static <T> T a(T t, T t2) {
        return t == null ? t2 : t;
    }

    public static String b(Object obj) {
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static String a(String str) {
        if (str == null) {
            return "";
        }
        return str;
    }

    public static String a(Object obj, String str) {
        if (obj == null) {
            return str;
        }
        return String.format("\"%s\"", obj);
    }

    public static String c(Object obj) {
        if (obj == null) {
            return "unknown";
        }
        return g(obj instanceof Class ? (Class) obj : obj.getClass());
    }

    public static String b(com.a.a.c.j jVar) {
        if (jVar == null) {
            return "[null]";
        }
        StringBuilder append = new StringBuilder(80).append('`');
        append.append(jVar.G());
        return append.append('`').toString();
    }

    public static String d(Object obj) {
        if (obj == null) {
            return "[null]";
        }
        return g(obj instanceof Class ? (Class) obj : obj.getClass());
    }

    public static String g(Class<?> cls) {
        if (cls == null) {
            return "[null]";
        }
        int i = 0;
        while (cls.isArray()) {
            i++;
            cls = cls.getComponentType();
        }
        String simpleName = cls.isPrimitive() ? cls.getSimpleName() : cls.getName();
        if (i > 0) {
            StringBuilder sb = new StringBuilder(simpleName);
            do {
                sb.append("[]");
                i--;
            } while (i > 0);
            simpleName = sb.toString();
        }
        return c(simpleName);
    }

    public static String a(v vVar) {
        if (vVar == null) {
            return "[null]";
        }
        return d(vVar.a());
    }

    public static String b(String str) {
        if (str == null) {
            return "[null]";
        }
        return d(str);
    }

    public static String a(com.a.a.c.w wVar) {
        if (wVar == null) {
            return "[null]";
        }
        return d(wVar.b());
    }

    private static String c(String str) {
        if (str == null) {
            return "[null]";
        }
        return new StringBuilder(str.length() + 2).append('`').append(str).append('`').toString();
    }

    private static String d(String str) {
        if (str == null) {
            return "[null]";
        }
        return new StringBuilder(str.length() + 2).append('\'').append(str).append('\'').toString();
    }

    public static String g(Throwable th) {
        if (th instanceof com.a.a.b.d) {
            return ((com.a.a.b.d) th).b();
        }
        if ((th instanceof InvocationTargetException) && th.getCause() != null) {
            return th.getCause().getMessage();
        }
        return th.getMessage();
    }

    public static Object h(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return 0;
        }
        if (cls == Long.TYPE) {
            return 0L;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (cls == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (cls == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (cls == Byte.TYPE) {
            return (byte) 0;
        }
        if (cls == Short.TYPE) {
            return (short) 0;
        }
        if (cls == Character.TYPE) {
            return (char) 0;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static Class<?> i(Class<?> cls) {
        if (cls == Integer.TYPE) {
            return Integer.class;
        }
        if (cls == Long.TYPE) {
            return Long.class;
        }
        if (cls == Boolean.TYPE) {
            return Boolean.class;
        }
        if (cls == Double.TYPE) {
            return Double.class;
        }
        if (cls == Float.TYPE) {
            return Float.class;
        }
        if (cls == Byte.TYPE) {
            return Byte.class;
        }
        if (cls == Short.TYPE) {
            return Short.class;
        }
        if (cls == Character.TYPE) {
            return Character.class;
        }
        throw new IllegalArgumentException("Class " + cls.getName() + " is not a primitive type");
    }

    public static Class<?> j(Class<?> cls) {
        if (cls.isPrimitive()) {
            return cls;
        }
        if (cls == Integer.class) {
            return Integer.TYPE;
        }
        if (cls == Long.class) {
            return Long.TYPE;
        }
        if (cls == Boolean.class) {
            return Boolean.TYPE;
        }
        if (cls == Double.class) {
            return Double.TYPE;
        }
        if (cls == Float.class) {
            return Float.TYPE;
        }
        if (cls == Byte.class) {
            return Byte.TYPE;
        }
        if (cls == Short.class) {
            return Short.TYPE;
        }
        if (cls == Character.class) {
            return Character.TYPE;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Member member, boolean z) {
        AccessibleObject accessibleObject = (AccessibleObject) member;
        try {
            Class<?> declaringClass = member.getDeclaringClass();
            if (!(Modifier.isPublic(member.getModifiers()) && Modifier.isPublic(declaringClass.getModifiers())) || (z && !m(declaringClass))) {
                accessibleObject.setAccessible(true);
            }
        } catch (SecurityException e) {
            if (!accessibleObject.isAccessible()) {
                throw new IllegalArgumentException("Cannot access " + member + " (from class " + member.getDeclaringClass().getName() + "; failed to set access: " + e.getMessage());
            }
        } catch (RuntimeException e2) {
            if ("InaccessibleObjectException".equals(e2.getClass().getSimpleName())) {
                throw new IllegalArgumentException(String.format("Failed to call `setAccess()` on %s '%s' (of class %s) due to `%s`, problem: %s", member.getClass().getSimpleName(), member.getName(), g(member.getDeclaringClass()), e2.getClass().getName(), e2.getMessage()), e2);
            }
            throw e2;
        }
    }

    public static boolean k(Class<?> cls) {
        return Enum.class.isAssignableFrom(cls);
    }

    public static Class<? extends Enum<?>> a(EnumSet<?> enumSet) {
        if (!enumSet.isEmpty()) {
            return a((Enum<?>) enumSet.iterator().next());
        }
        return b.f727a.a(enumSet);
    }

    public static Class<? extends Enum<?>> a(EnumMap<?, ?> enumMap) {
        if (!enumMap.isEmpty()) {
            return a((Enum<?>) enumMap.keySet().iterator().next());
        }
        return b.f727a.a(enumMap);
    }

    private static Class<? extends Enum<?>> a(Enum<?> r2) {
        return r2.getDeclaringClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Class<? extends Enum<?>> l(Class<?> cls) {
        if (cls.getSuperclass() != Enum.class) {
            cls = cls.getSuperclass();
        }
        return cls;
    }

    public static <T extends Annotation> Enum<?> a(Class<Enum<?>> cls, Class<T> cls2) {
        for (Field field : cls.getDeclaredFields()) {
            if (field.isEnumConstant() && field.getAnnotation(cls2) != null) {
                String name = field.getName();
                for (Enum<?> r0 : cls.getEnumConstants()) {
                    if (name.equals(r0.name())) {
                        return r0;
                    }
                }
            }
        }
        return null;
    }

    public static boolean e(Object obj) {
        return obj == null || u(obj.getClass());
    }

    private static boolean u(Class<?> cls) {
        return cls.getAnnotation(com.a.a.c.a.a.class) != null;
    }

    public static boolean m(Class<?> cls) {
        String name = cls.getName();
        return name.startsWith("java.") || name.startsWith("javax.");
    }

    public static boolean n(Class<?> cls) {
        return (Modifier.isStatic(cls.getModifiers()) || w(cls) == null) ? false : true;
    }

    private static boolean v(Class<?> cls) {
        return (t(cls) || cls.getEnclosingMethod() == null) ? false : true;
    }

    public static Annotation[] o(Class<?> cls) {
        if (t(cls)) {
            return f724b;
        }
        return cls.getDeclaredAnnotations();
    }

    public static Method[] p(Class<?> cls) {
        try {
            return cls.getDeclaredMethods();
        } catch (NoClassDefFoundError e) {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if (contextClassLoader == null) {
                return a(cls, (Throwable) e);
            }
            try {
                try {
                    return contextClassLoader.loadClass(cls.getName()).getDeclaredMethods();
                } catch (Throwable th) {
                    return a(cls, th);
                }
            } catch (ClassNotFoundException e2) {
                e.addSuppressed(e2);
                return a(cls, (Throwable) e);
            }
        } catch (Throwable th2) {
            return a(cls, th2);
        }
    }

    private static Method[] a(Class<?> cls, Throwable th) {
        throw new IllegalArgumentException(String.format("Failed on call to `getDeclaredMethods()` on class `%s`, problem: (%s) %s", cls.getName(), th.getClass().getName(), th.getMessage()), th);
    }

    public static a[] q(Class<?> cls) {
        if (cls.isInterface() || t(cls)) {
            return c;
        }
        Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
        int length = declaredConstructors.length;
        a[] aVarArr = new a[length];
        for (int i = 0; i < length; i++) {
            aVarArr[i] = new a(declaredConstructors[i]);
        }
        return aVarArr;
    }

    public static Type r(Class<?> cls) {
        return cls.getGenericSuperclass();
    }

    public static Type[] s(Class<?> cls) {
        return cls.getGenericInterfaces();
    }

    private static Class<?> w(Class<?> cls) {
        if (t(cls)) {
            return null;
        }
        return cls.getEnclosingClass();
    }

    private static Class<?>[] x(Class<?> cls) {
        return cls.getInterfaces();
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/i$b.class */
    static class b {

        /* renamed from: a, reason: collision with root package name */
        static final b f727a = new b();

        /* renamed from: b, reason: collision with root package name */
        private final Field f728b;
        private final Field c;
        private final String d;
        private final String e;

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.reflect.Field] */
        /* JADX WARN: Type inference failed for: r0v16, types: [java.lang.reflect.Field] */
        /* JADX WARN: Type inference failed for: r0v2 */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Exception] */
        /* JADX WARN: Type inference failed for: r0v8 */
        /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Exception] */
        private b() {
            Field field = null;
            ?? r0 = 0;
            String str = null;
            try {
                r0 = a(EnumSet.class, "elementType", Class.class);
                field = r0;
            } catch (Exception e) {
                str = r0.toString();
            }
            this.f728b = field;
            this.d = str;
            Field field2 = null;
            ?? r02 = 0;
            String str2 = null;
            try {
                r02 = a(EnumMap.class, "keyType", Class.class);
                field2 = r02;
            } catch (Exception e2) {
                str2 = r02.toString();
            }
            this.c = field2;
            this.e = str2;
        }

        public final Class<? extends Enum<?>> a(EnumSet<?> enumSet) {
            if (this.f728b != null) {
                return (Class) a(enumSet, this.f728b);
            }
            throw new IllegalStateException("Cannot figure out type parameter for `EnumSet` (odd JDK platform?), problem: " + this.d);
        }

        public final Class<? extends Enum<?>> a(EnumMap<?, ?> enumMap) {
            if (this.c != null) {
                return (Class) a(enumMap, this.c);
            }
            throw new IllegalStateException("Cannot figure out type parameter for `EnumMap` (odd JDK platform?), problem: " + this.e);
        }

        private static Object a(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        }

        private static Field a(Class<?> cls, String str, Class<?> cls2) {
            for (Field field : cls.getDeclaredFields()) {
                if (str.equals(field.getName()) && field.getType() == cls2) {
                    field.setAccessible(true);
                    return field;
                }
            }
            throw new IllegalStateException(String.format("No field named '%s' in class '%s'", str, cls.getName()));
        }
    }

    /* loaded from: infinitode-2.jar:com/a/a/c/m/i$a.class */
    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private Constructor<?> f725a;

        /* renamed from: b, reason: collision with root package name */
        private transient Annotation[] f726b;
        private transient Annotation[][] c;
        private int d = -1;

        public a(Constructor<?> constructor) {
            this.f725a = constructor;
        }

        public final Constructor<?> a() {
            return this.f725a;
        }

        public final int b() {
            int i = this.d;
            int i2 = i;
            if (i < 0) {
                i2 = this.f725a.getParameterCount();
                this.d = i2;
            }
            return i2;
        }

        public final Class<?> c() {
            return this.f725a.getDeclaringClass();
        }

        public final Annotation[] d() {
            Annotation[] annotationArr = this.f726b;
            Annotation[] annotationArr2 = annotationArr;
            if (annotationArr == null) {
                annotationArr2 = this.f725a.getDeclaredAnnotations();
                this.f726b = annotationArr2;
            }
            return annotationArr2;
        }

        public final Annotation[][] e() {
            Annotation[][] annotationArr = this.c;
            Annotation[][] annotationArr2 = annotationArr;
            if (annotationArr == null) {
                annotationArr2 = this.f725a.getParameterAnnotations();
                this.c = annotationArr2;
            }
            return annotationArr2;
        }
    }
}
