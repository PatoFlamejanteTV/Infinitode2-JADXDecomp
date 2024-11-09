package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentDecommit.class */
public abstract class ExtentDecommit extends Callback implements ExtentDecommitI {
    public static ExtentDecommit create(long j) {
        ExtentDecommitI extentDecommitI = (ExtentDecommitI) Callback.get(j);
        return extentDecommitI instanceof ExtentDecommit ? (ExtentDecommit) extentDecommitI : new Container(j, extentDecommitI);
    }

    public static ExtentDecommit createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentDecommit create(ExtentDecommitI extentDecommitI) {
        return extentDecommitI instanceof ExtentDecommit ? (ExtentDecommit) extentDecommitI : new Container(extentDecommitI.address(), extentDecommitI);
    }

    protected ExtentDecommit() {
        super(CIF);
    }

    ExtentDecommit(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentDecommit$Container.class */
    public static final class Container extends ExtentDecommit {
        private final ExtentDecommitI delegate;

        Container(long j, ExtentDecommitI extentDecommitI) {
            super(j);
            this.delegate = extentDecommitI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentDecommitI
        public final boolean invoke(long j, long j2, long j3, long j4, long j5, int i) {
            return this.delegate.invoke(j, j2, j3, j4, j5, i);
        }
    }
}
