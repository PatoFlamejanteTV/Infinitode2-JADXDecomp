package nonapi.io.github.classgraph.classpath;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import nonapi.io.github.classgraph.reflection.ReflectionUtils;
import nonapi.io.github.classgraph.utils.LogNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/classpath/CallStackReader.class */
public class CallStackReader {
    ReflectionUtils reflectionUtils;

    static /* synthetic */ Class[] access$000() {
        return getCallStackViaStackWalker();
    }

    public CallStackReader(ReflectionUtils reflectionUtils) {
        this.reflectionUtils = reflectionUtils;
    }

    private static Class<?>[] getCallStackViaStackWalker() {
        try {
            Class<?> cls = Class.forName("java.util.function.Consumer");
            final ArrayList arrayList = new ArrayList();
            Class<?> cls2 = Class.forName("java.lang.StackWalker$Option");
            Object invoke = Class.forName("java.lang.Enum").getMethod("valueOf", Class.class, String.class).invoke(null, cls2, "RETAIN_CLASS_REFERENCE");
            Class<?> cls3 = Class.forName("java.lang.StackWalker");
            Object invoke2 = cls3.getMethod("getInstance", cls2).invoke(null, invoke);
            final Method method = Class.forName("java.lang.StackWalker$StackFrame").getMethod("getDeclaringClass", new Class[0]);
            cls3.getMethod("forEach", cls).invoke(invoke2, Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: nonapi.io.github.classgraph.classpath.CallStackReader.1
                @Override // java.lang.reflect.InvocationHandler
                public final Object invoke(Object obj, Method method2, Object[] objArr) {
                    arrayList.add((Class) method.invoke(objArr[0], new Object[0]));
                    return null;
                }
            }));
            return (Class[]) arrayList.toArray(new Class[0]);
        } catch (Exception | LinkageError unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Class<?>[] getCallStackViaSecurityManager(LogNode logNode) {
        try {
            Object obj = null;
            Constructor<?>[] declaredConstructors = Class.forName("java.lang.SecurityManager").getDeclaredConstructors();
            int length = declaredConstructors.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Constructor<?> constructor = declaredConstructors[i];
                if (constructor.getParameterTypes().length != 0) {
                    i++;
                } else {
                    obj = constructor.newInstance(new Object[0]);
                    break;
                }
            }
            if (obj != null) {
                Method declaredMethod = obj.getClass().getDeclaredMethod("getClassContext", new Class[0]);
                declaredMethod.setAccessible(true);
                return (Class[]) declaredMethod.invoke(obj, new Object[0]);
            }
            return null;
        } catch (Throwable th) {
            if (logNode != null) {
                logNode.log("Exception while trying to obtain call stack via SecurityManager", th);
                return null;
            }
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00b2, code lost:            if (r0 == 0) goto L68;     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Class<?>[] getClassContext(final nonapi.io.github.classgraph.utils.LogNode r7) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: nonapi.io.github.classgraph.classpath.CallStackReader.getClassContext(nonapi.io.github.classgraph.utils.LogNode):java.lang.Class[]");
    }
}
