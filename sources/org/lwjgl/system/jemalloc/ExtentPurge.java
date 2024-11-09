package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentPurge.class */
public abstract class ExtentPurge extends Callback implements ExtentPurgeI {
    public static ExtentPurge create(long j) {
        ExtentPurgeI extentPurgeI = (ExtentPurgeI) Callback.get(j);
        return extentPurgeI instanceof ExtentPurge ? (ExtentPurge) extentPurgeI : new Container(j, extentPurgeI);
    }

    public static ExtentPurge createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentPurge create(ExtentPurgeI extentPurgeI) {
        return extentPurgeI instanceof ExtentPurge ? (ExtentPurge) extentPurgeI : new Container(extentPurgeI.address(), extentPurgeI);
    }

    protected ExtentPurge() {
        super(CIF);
    }

    ExtentPurge(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentPurge$Container.class */
    public static final class Container extends ExtentPurge {
        private final ExtentPurgeI delegate;

        Container(long j, ExtentPurgeI extentPurgeI) {
            super(j);
            this.delegate = extentPurgeI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentPurgeI
        public final boolean invoke(long j, long j2, long j3, long j4, long j5, int i) {
            return this.delegate.invoke(j, j2, j3, j4, j5, i);
        }
    }
}
