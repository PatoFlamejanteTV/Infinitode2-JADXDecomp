package com.badlogic.gdx.utils.reflect;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/reflect/Annotation.class */
public final class Annotation {
    private java.lang.annotation.Annotation annotation;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Annotation(java.lang.annotation.Annotation annotation) {
        this.annotation = annotation;
    }

    public final <T extends java.lang.annotation.Annotation> T getAnnotation(Class<T> cls) {
        if (this.annotation.annotationType().equals(cls)) {
            return (T) this.annotation;
        }
        return null;
    }

    public final Class<? extends java.lang.annotation.Annotation> getAnnotationType() {
        return this.annotation.annotationType();
    }
}
