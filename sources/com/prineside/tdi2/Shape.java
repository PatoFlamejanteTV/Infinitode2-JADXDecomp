package com.prineside.tdi2;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Shape.class */
public abstract class Shape implements Pool.Poolable {
    public abstract void draw(Batch batch);

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Shape$Factory.class */
    public static abstract class Factory<T extends Shape> implements Disposable {

        /* renamed from: a, reason: collision with root package name */
        private Pool<T> f1760a = (Pool<T>) new Pool<T>(1, 512) { // from class: com.prineside.tdi2.Shape.Factory.1
            {
                super(1, 512);
            }

            /* JADX INFO: Access modifiers changed from: private */
            @Override // com.badlogic.gdx.utils.Pool
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public T newObject() {
                return (T) Factory.this.a();
            }
        };

        public abstract void setup();

        protected abstract T a();

        public final T obtain() {
            return this.f1760a.obtain();
        }

        public void free(T t) {
            this.f1760a.free(t);
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
        }
    }
}
