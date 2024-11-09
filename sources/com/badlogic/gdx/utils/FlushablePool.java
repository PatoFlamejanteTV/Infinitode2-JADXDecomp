package com.badlogic.gdx.utils;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/FlushablePool.class */
public abstract class FlushablePool<T> extends Pool<T> {
    protected Array<T> obtained;

    public FlushablePool() {
        this.obtained = new Array<>();
    }

    public FlushablePool(int i) {
        super(i);
        this.obtained = new Array<>();
    }

    public FlushablePool(int i, int i2) {
        super(i, i2);
        this.obtained = new Array<>();
    }

    @Override // com.badlogic.gdx.utils.Pool
    public T obtain() {
        T t = (T) super.obtain();
        this.obtained.add(t);
        return t;
    }

    public void flush() {
        super.freeAll(this.obtained);
        this.obtained.clear();
    }

    @Override // com.badlogic.gdx.utils.Pool
    public void free(T t) {
        this.obtained.removeValue(t, true);
        super.free(t);
    }

    @Override // com.badlogic.gdx.utils.Pool
    public void freeAll(Array<T> array) {
        this.obtained.removeAll(array, true);
        super.freeAll(array);
    }
}
