package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentDestroy.class */
public abstract class ExtentDestroy extends Callback implements ExtentDestroyI {
    public static ExtentDestroy create(long j) {
        ExtentDestroyI extentDestroyI = (ExtentDestroyI) Callback.get(j);
        return extentDestroyI instanceof ExtentDestroy ? (ExtentDestroy) extentDestroyI : new Container(j, extentDestroyI);
    }

    public static ExtentDestroy createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentDestroy create(ExtentDestroyI extentDestroyI) {
        return extentDestroyI instanceof ExtentDestroy ? (ExtentDestroy) extentDestroyI : new Container(extentDestroyI.address(), extentDestroyI);
    }

    protected ExtentDestroy() {
        super(CIF);
    }

    ExtentDestroy(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentDestroy$Container.class */
    public static final class Container extends ExtentDestroy {
        private final ExtentDestroyI delegate;

        Container(long j, ExtentDestroyI extentDestroyI) {
            super(j);
            this.delegate = extentDestroyI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentDestroyI
        public final boolean invoke(long j, long j2, long j3, boolean z, int i) {
            return this.delegate.invoke(j, j2, j3, z, i);
        }
    }
}
