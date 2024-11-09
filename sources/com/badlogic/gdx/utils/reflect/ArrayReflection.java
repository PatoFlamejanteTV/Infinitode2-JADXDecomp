package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.Array;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/reflect/ArrayReflection.class */
public final class ArrayReflection {
    public static Object newInstance(Class cls, int i) {
        return Array.newInstance((Class<?>) cls, i);
    }

    public static int getLength(Object obj) {
        return Array.getLength(obj);
    }

    public static Object get(Object obj, int i) {
        return Array.get(obj, i);
    }

    public static void set(Object obj, int i, Object obj2) {
        Array.set(obj, i, obj2);
    }
}
