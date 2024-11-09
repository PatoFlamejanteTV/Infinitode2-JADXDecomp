package com.badlogic.gdx.utils;

import com.badlogic.gdx.utils.reflect.ClassReflection;
import com.badlogic.gdx.utils.reflect.Constructor;
import com.badlogic.gdx.utils.reflect.ReflectionException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/ReflectionPool.class */
public class ReflectionPool<T> extends Pool<T> {
    private final Constructor constructor;

    public ReflectionPool(Class<T> cls) {
        this(cls, 16, Integer.MAX_VALUE);
    }

    public ReflectionPool(Class<T> cls, int i) {
        this(cls, i, Integer.MAX_VALUE);
    }

    public ReflectionPool(Class<T> cls, int i, int i2) {
        super(i, i2);
        this.constructor = findConstructor(cls);
        if (this.constructor == null) {
            throw new RuntimeException("Class cannot be created (missing no-arg constructor): " + cls.getName());
        }
    }

    @Null
    private Constructor findConstructor(Class<T> cls) {
        try {
            return ClassReflection.getConstructor(cls, (Class[]) null);
        } catch (Exception unused) {
            try {
                Constructor declaredConstructor = ClassReflection.getDeclaredConstructor(cls, (Class[]) null);
                declaredConstructor.setAccessible(true);
                return declaredConstructor;
            } catch (ReflectionException unused2) {
                return null;
            }
        }
    }

    @Override // com.badlogic.gdx.utils.Pool
    protected T newObject() {
        try {
            return (T) this.constructor.newInstance((Object[]) null);
        } catch (Exception e) {
            throw new GdxRuntimeException("Unable to create new instance: " + this.constructor.getDeclaringClass().getName(), e);
        }
    }
}
