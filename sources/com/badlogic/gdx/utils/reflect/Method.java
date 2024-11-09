package com.badlogic.gdx.utils.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/reflect/Method.class */
public final class Method {
    private final java.lang.reflect.Method method;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Method(java.lang.reflect.Method method) {
        this.method = method;
    }

    public final String getName() {
        return this.method.getName();
    }

    public final Class getReturnType() {
        return this.method.getReturnType();
    }

    public final Class[] getParameterTypes() {
        return this.method.getParameterTypes();
    }

    public final Class getDeclaringClass() {
        return this.method.getDeclaringClass();
    }

    public final boolean isAccessible() {
        return this.method.isAccessible();
    }

    public final void setAccessible(boolean z) {
        this.method.setAccessible(z);
    }

    public final boolean isAbstract() {
        return Modifier.isAbstract(this.method.getModifiers());
    }

    public final boolean isDefaultAccess() {
        return (isPrivate() || isProtected() || isPublic()) ? false : true;
    }

    public final boolean isFinal() {
        return Modifier.isFinal(this.method.getModifiers());
    }

    public final boolean isPrivate() {
        return Modifier.isPrivate(this.method.getModifiers());
    }

    public final boolean isProtected() {
        return Modifier.isProtected(this.method.getModifiers());
    }

    public final boolean isPublic() {
        return Modifier.isPublic(this.method.getModifiers());
    }

    public final boolean isNative() {
        return Modifier.isNative(this.method.getModifiers());
    }

    public final boolean isStatic() {
        return Modifier.isStatic(this.method.getModifiers());
    }

    public final boolean isVarArgs() {
        return this.method.isVarArgs();
    }

    public final Object invoke(Object obj, Object... objArr) {
        try {
            return this.method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new ReflectionException("Illegal access to method: " + getName(), e);
        } catch (IllegalArgumentException e2) {
            throw new ReflectionException("Illegal argument(s) supplied to method: " + getName(), e2);
        } catch (InvocationTargetException e3) {
            throw new ReflectionException("Exception occurred in method: " + getName(), e3);
        }
    }

    public final boolean isAnnotationPresent(Class<? extends java.lang.annotation.Annotation> cls) {
        return this.method.isAnnotationPresent(cls);
    }

    public final Annotation[] getDeclaredAnnotations() {
        java.lang.annotation.Annotation[] declaredAnnotations = this.method.getDeclaredAnnotations();
        Annotation[] annotationArr = new Annotation[declaredAnnotations.length];
        for (int i = 0; i < declaredAnnotations.length; i++) {
            annotationArr[i] = new Annotation(declaredAnnotations[i]);
        }
        return annotationArr;
    }

    public final Annotation getDeclaredAnnotation(Class<? extends java.lang.annotation.Annotation> cls) {
        java.lang.annotation.Annotation[] declaredAnnotations = this.method.getDeclaredAnnotations();
        if (declaredAnnotations == null) {
            return null;
        }
        for (java.lang.annotation.Annotation annotation : declaredAnnotations) {
            if (annotation.annotationType().equals(cls)) {
                return new Annotation(annotation);
            }
        }
        return null;
    }
}
