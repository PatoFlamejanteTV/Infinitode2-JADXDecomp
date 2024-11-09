package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentMerge.class */
public abstract class ExtentMerge extends Callback implements ExtentMergeI {
    public static ExtentMerge create(long j) {
        ExtentMergeI extentMergeI = (ExtentMergeI) Callback.get(j);
        return extentMergeI instanceof ExtentMerge ? (ExtentMerge) extentMergeI : new Container(j, extentMergeI);
    }

    public static ExtentMerge createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentMerge create(ExtentMergeI extentMergeI) {
        return extentMergeI instanceof ExtentMerge ? (ExtentMerge) extentMergeI : new Container(extentMergeI.address(), extentMergeI);
    }

    protected ExtentMerge() {
        super(CIF);
    }

    ExtentMerge(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentMerge$Container.class */
    public static final class Container extends ExtentMerge {
        private final ExtentMergeI delegate;

        Container(long j, ExtentMergeI extentMergeI) {
            super(j);
            this.delegate = extentMergeI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentMergeI
        public final boolean invoke(long j, long j2, long j3, long j4, long j5, boolean z, int i) {
            return this.delegate.invoke(j, j2, j3, j4, j5, z, i);
        }
    }
}
