package net.bytebuddy.utility;

import java.lang.reflect.Method;
import java.util.Comparator;

/* loaded from: infinitode-2.jar:net/bytebuddy/utility/MethodComparator.class */
public enum MethodComparator implements Comparator<Method> {
    INSTANCE;

    @Override // java.util.Comparator
    public final int compare(Method method, Method method2) {
        if (method == method2) {
            return 0;
        }
        int compareTo = method.getName().compareTo(method2.getName());
        if (compareTo == 0) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            Class<?>[] parameterTypes2 = method2.getParameterTypes();
            if (parameterTypes.length < parameterTypes2.length) {
                return -1;
            }
            if (parameterTypes.length > parameterTypes2.length) {
                return 1;
            }
            for (int i = 0; i < parameterTypes.length; i++) {
                int compareTo2 = parameterTypes[i].getName().compareTo(parameterTypes2[i].getName());
                if (compareTo2 != 0) {
                    return compareTo2;
                }
            }
            return method.getReturnType().getName().compareTo(method2.getReturnType().getName());
        }
        return compareTo;
    }
}
