package com.prineside.tdi2.utils;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import java.lang.reflect.Field;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/utils/DebugPool.class */
public abstract class DebugPool<T> extends Pool<T> {
    public DebugPool(int i, int i2) {
        super(i, i2);
    }

    @Override // com.badlogic.gdx.utils.Pool
    public T obtain() {
        return (T) super.obtain();
    }

    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Throwable, java.lang.IllegalAccessException, java.lang.NoSuchFieldException, java.lang.IllegalArgumentException] */
    @Override // com.badlogic.gdx.utils.Pool
    public void free(T t) {
        ?? illegalArgumentException;
        try {
            Field declaredField = Pool.class.getDeclaredField("freeObjects");
            declaredField.setAccessible(true);
            Array array = (Array) declaredField.get(this);
            for (int i = 0; i < array.size; i++) {
                if (array.get(i) == t) {
                    illegalArgumentException = new IllegalArgumentException("Object is already freed: " + t);
                    throw illegalArgumentException;
                }
            }
        } catch (IllegalAccessException e) {
            illegalArgumentException.printStackTrace();
        } catch (NoSuchFieldException e2) {
            illegalArgumentException.printStackTrace();
        }
        super.free(t);
    }
}
