package com.google.common.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ElementTypesAreNonnullByDefault
/* loaded from: infinitode-2.jar:com/google/common/base/Throwables.class */
public final class Throwables {
    private static final String JAVA_LANG_ACCESS_CLASSNAME = "sun.misc.JavaLangAccess";
    static final String SHARED_SECRETS_CLASSNAME = "sun.misc.SharedSecrets";
    private static final Object jla;
    private static final Method getStackTraceElementMethod;
    private static final Method getStackTraceDepthMethod;

    private Throwables() {
    }

    public static <X extends Throwable> void throwIfInstanceOf(Throwable th, Class<X> cls) {
        Preconditions.checkNotNull(th);
        if (cls.isInstance(th)) {
            throw cls.cast(th);
        }
    }

    @Deprecated
    public static <X extends Throwable> void propagateIfInstanceOf(Throwable th, Class<X> cls) {
        if (th != null) {
            throwIfInstanceOf(th, cls);
        }
    }

    public static void throwIfUnchecked(Throwable th) {
        Preconditions.checkNotNull(th);
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
    }

    @Deprecated
    public static void propagateIfPossible(Throwable th) {
        if (th != null) {
            throwIfUnchecked(th);
        }
    }

    public static <X extends Throwable> void propagateIfPossible(Throwable th, Class<X> cls) {
        propagateIfInstanceOf(th, cls);
        propagateIfPossible(th);
    }

    public static <X1 extends Throwable, X2 extends Throwable> void propagateIfPossible(Throwable th, Class<X1> cls, Class<X2> cls2) {
        Preconditions.checkNotNull(cls2);
        propagateIfInstanceOf(th, cls);
        propagateIfPossible(th, cls2);
    }

    @Deprecated
    public static RuntimeException propagate(Throwable th) {
        throwIfUnchecked(th);
        throw new RuntimeException(th);
    }

    public static Throwable getRootCause(Throwable th) {
        Throwable th2 = th;
        boolean z = false;
        while (true) {
            boolean z2 = z;
            Throwable cause = th.getCause();
            if (cause != null) {
                th = cause;
                if (cause == th2) {
                    throw new IllegalArgumentException("Loop in causal chain detected.", th);
                }
                if (z2) {
                    th2 = th2.getCause();
                }
                z = !z2;
            } else {
                return th;
            }
        }
    }

    public static List<Throwable> getCausalChain(Throwable th) {
        Preconditions.checkNotNull(th);
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(th);
        Throwable th2 = th;
        boolean z = false;
        while (true) {
            boolean z2 = z;
            Throwable cause = th.getCause();
            if (cause != null) {
                th = cause;
                arrayList.add(th);
                if (th == th2) {
                    throw new IllegalArgumentException("Loop in causal chain detected.", th);
                }
                if (z2) {
                    th2 = th2.getCause();
                }
                z = !z2;
            } else {
                return Collections.unmodifiableList(arrayList);
            }
        }
    }

    public static <X extends Throwable> X getCauseAs(Throwable th, Class<X> cls) {
        X cast;
        try {
            cast = cls.cast(th.getCause());
            return cast;
        } catch (ClassCastException e) {
            cast.initCause(th);
            throw e;
        }
    }

    public static String getStackTraceAsString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @Deprecated
    public static List<StackTraceElement> lazyStackTrace(Throwable th) {
        if (lazyStackTraceIsLazy()) {
            return jlaStackTrace(th);
        }
        return Collections.unmodifiableList(Arrays.asList(th.getStackTrace()));
    }

    @Deprecated
    public static boolean lazyStackTraceIsLazy() {
        return (getStackTraceElementMethod == null || getStackTraceDepthMethod == null) ? false : true;
    }

    private static List<StackTraceElement> jlaStackTrace(final Throwable th) {
        Preconditions.checkNotNull(th);
        return new AbstractList<StackTraceElement>() { // from class: com.google.common.base.Throwables.1
            @Override // java.util.AbstractList, java.util.List
            public StackTraceElement get(int i) {
                return (StackTraceElement) Throwables.invokeAccessibleNonThrowingMethod((Method) java.util.Objects.requireNonNull(Throwables.getStackTraceElementMethod), java.util.Objects.requireNonNull(Throwables.jla), th, Integer.valueOf(i));
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return ((Integer) Throwables.invokeAccessibleNonThrowingMethod((Method) java.util.Objects.requireNonNull(Throwables.getStackTraceDepthMethod), java.util.Objects.requireNonNull(Throwables.jla), th)).intValue();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Object, java.lang.reflect.InvocationTargetException] */
    public static Object invokeAccessibleNonThrowingMethod(Method method, Object obj, Object... objArr) {
        ?? invoke;
        try {
            invoke = method.invoke(obj, objArr);
            return invoke;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw propagate(invoke.getCause());
        }
    }

    static {
        Object jla2 = getJLA();
        jla = jla2;
        getStackTraceElementMethod = jla2 == null ? null : getGetMethod();
        getStackTraceDepthMethod = jla == null ? null : getSizeMethod(jla);
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable, java.lang.Object] */
    private static Object getJLA() {
        ?? invoke;
        try {
            invoke = Class.forName(SHARED_SECRETS_CLASSNAME, false, null).getMethod("getJavaLangAccess", new Class[0]).invoke(null, new Object[0]);
            return invoke;
        } catch (ThreadDeath e) {
            throw invoke;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static Method getGetMethod() {
        return getJlaMethod("getStackTraceElement", Throwable.class, Integer.TYPE);
    }

    private static Method getSizeMethod(Object obj) {
        try {
            Method jlaMethod = getJlaMethod("getStackTraceDepth", Throwable.class);
            if (jlaMethod == null) {
                return null;
            }
            jlaMethod.invoke(obj, new Throwable());
            return jlaMethod;
        } catch (IllegalAccessException | UnsupportedOperationException | InvocationTargetException unused) {
            return null;
        }
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable, java.lang.reflect.Method] */
    private static Method getJlaMethod(String str, Class<?>... clsArr) {
        ?? method;
        try {
            method = Class.forName(JAVA_LANG_ACCESS_CLASSNAME, false, null).getMethod(str, clsArr);
            return method;
        } catch (ThreadDeath e) {
            throw method;
        } catch (Throwable unused) {
            return null;
        }
    }
}
