package com.prineside.tdi2;

import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.Disposable;

/* loaded from: infinitode-2.jar:com/prineside/tdi2/Manager.class */
public interface Manager extends Disposable {
    void setup();

    void preRender(float f);

    void postRender(float f);

    void test();

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Manager$ManagerAdapter.class */
    public static abstract class ManagerAdapter implements Manager {
        @Override // com.prineside.tdi2.Manager
        public void setup() {
        }

        @Override // com.prineside.tdi2.Manager
        public void preRender(float f) {
        }

        @Override // com.prineside.tdi2.Manager
        public void postRender(float f) {
        }

        @Override // com.prineside.tdi2.Manager
        public void test() {
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
        }
    }

    /* loaded from: infinitode-2.jar:com/prineside/tdi2/Manager$ManagerWithListeners.class */
    public static abstract class ManagerWithListeners<T> implements Manager {

        /* renamed from: a, reason: collision with root package name */
        protected final DelayedRemovalArray<T> f1735a = new DelayedRemovalArray<>();

        public void addListener(T t) {
            if (t == null) {
                throw new IllegalArgumentException("listener is null");
            }
            if (!this.f1735a.contains(t, true)) {
                this.f1735a.add(t);
            }
        }

        public void removeListener(T t) {
            if (t == null) {
                throw new IllegalArgumentException("listener is null");
            }
            this.f1735a.removeValue(t, true);
        }

        @Override // com.prineside.tdi2.Manager
        public void setup() {
        }

        @Override // com.prineside.tdi2.Manager
        public void preRender(float f) {
        }

        @Override // com.prineside.tdi2.Manager
        public void postRender(float f) {
        }

        @Override // com.prineside.tdi2.Manager
        public void test() {
        }

        @Override // com.badlogic.gdx.utils.Disposable
        public void dispose() {
        }
    }
}
