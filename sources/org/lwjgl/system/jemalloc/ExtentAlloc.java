package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentAlloc.class */
public abstract class ExtentAlloc extends Callback implements ExtentAllocI {
    public static ExtentAlloc create(long j) {
        ExtentAllocI extentAllocI = (ExtentAllocI) Callback.get(j);
        return extentAllocI instanceof ExtentAlloc ? (ExtentAlloc) extentAllocI : new Container(j, extentAllocI);
    }

    public static ExtentAlloc createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentAlloc create(ExtentAllocI extentAllocI) {
        return extentAllocI instanceof ExtentAlloc ? (ExtentAlloc) extentAllocI : new Container(extentAllocI.address(), extentAllocI);
    }

    protected ExtentAlloc() {
        super(CIF);
    }

    ExtentAlloc(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentAlloc$Container.class */
    public static final class Container extends ExtentAlloc {
        private final ExtentAllocI delegate;

        Container(long j, ExtentAllocI extentAllocI) {
            super(j);
            this.delegate = extentAllocI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentAllocI
        public final long invoke(long j, long j2, long j3, long j4, long j5, long j6, int i) {
            return this.delegate.invoke(j, j2, j3, j4, j5, j6, i);
        }
    }
}
