package org.lwjgl.system;

import java.util.Arrays;
import java.util.Objects;

/* loaded from: infinitode-2.jar:org/lwjgl/system/StackWalkUtil.class */
final class StackWalkUtil {
    private StackWalkUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static StackTraceElement[] stackWalkArray(Object[] objArr) {
        return (StackTraceElement[]) objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object stackWalkGetMethod(Class<?> cls) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stackTrace.length; i++) {
            if (!stackTrace[i].getClassName().startsWith(cls.getName())) {
                return stackTrace[i];
            }
        }
        throw new IllegalStateException();
    }

    private static boolean isSameMethod(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
        return isSameMethod(stackTraceElement, stackTraceElement2, stackTraceElement2.getMethodName());
    }

    private static boolean isSameMethod(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2, String str) {
        return stackTraceElement.getMethodName().equals(str) && stackTraceElement.getClassName().equals(stackTraceElement2.getClassName()) && Objects.equals(stackTraceElement.getFileName(), stackTraceElement2.getFileName());
    }

    private static boolean isAutoCloseable(StackTraceElement stackTraceElement, StackTraceElement stackTraceElement2) {
        if (isSameMethod(stackTraceElement, stackTraceElement2, "$closeResource")) {
            return true;
        }
        if ("closeFinally".equals(stackTraceElement.getMethodName()) && "AutoCloseable.kt".equals(stackTraceElement.getFileName())) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object stackWalkCheckPop(Class<?> cls, Object obj) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (int i = 3; i < stackTrace.length; i++) {
            StackTraceElement stackTraceElement = stackTrace[i];
            StackTraceElement stackTraceElement2 = stackTraceElement;
            if (!stackTraceElement.getClassName().startsWith(cls.getName())) {
                StackTraceElement stackTraceElement3 = (StackTraceElement) obj;
                if (isSameMethod(stackTraceElement2, stackTraceElement3)) {
                    return null;
                }
                if (isAutoCloseable(stackTraceElement2, stackTraceElement3) && i + 1 < stackTrace.length) {
                    stackTraceElement2 = stackTrace[i + 1];
                    if (isSameMethod(stackTraceElement3, stackTrace[i + 1])) {
                        return null;
                    }
                }
                return stackTraceElement2;
            }
        }
        throw new IllegalStateException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object[] stackWalkGetTrace() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int i = 3;
        while (i < stackTrace.length && stackTrace[i].getClassName().startsWith("org.lwjgl.system.Memory")) {
            i++;
        }
        return Arrays.copyOfRange(stackTrace, i, stackTrace.length);
    }
}
