package nonapi.io.github.classgraph.utils;

/* loaded from: infinitode-2.jar:nonapi/io/github/classgraph/utils/Assert.class */
public final class Assert {
    public static void isAnnotation(Class<?> cls) {
        if (!cls.isAnnotation()) {
            throw new IllegalArgumentException(cls + " is not an annotation");
        }
    }

    public static void isInterface(Class<?> cls) {
        if (!cls.isInterface()) {
            throw new IllegalArgumentException(cls + " is not an interface");
        }
    }
}
