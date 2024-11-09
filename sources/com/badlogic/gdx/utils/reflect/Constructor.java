package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.InvocationTargetException;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/reflect/Constructor.class */
public final class Constructor {
    private final java.lang.reflect.Constructor constructor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Constructor(java.lang.reflect.Constructor constructor) {
        this.constructor = constructor;
    }

    public final Class[] getParameterTypes() {
        return this.constructor.getParameterTypes();
    }

    public final Class getDeclaringClass() {
        return this.constructor.getDeclaringClass();
    }

    public final boolean isAccessible() {
        return this.constructor.isAccessible();
    }

    public final void setAccessible(boolean z) {
        this.constructor.setAccessible(z);
    }

    public final Object newInstance(Object... objArr) {
        try {
            return this.constructor.newInstance(objArr);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("Could not instantiate instance of class: " + getDeclaringClass().getName(), e);
        } catch (IllegalArgumentException e2) {
            throw new ReflectionException("Illegal argument(s) supplied to constructor for class: " + getDeclaringClass().getName(), e2);
        } catch (InstantiationException e3) {
            throw new ReflectionException("Could not instantiate instance of class: " + getDeclaringClass().getName(), e3);
        } catch (InvocationTargetException e4) {
            throw new ReflectionException("Exception occurred in constructor for class: " + getDeclaringClass().getName(), e4);
        }
    }
}
