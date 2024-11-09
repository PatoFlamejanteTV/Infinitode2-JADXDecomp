package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentSplit.class */
public abstract class ExtentSplit extends Callback implements ExtentSplitI {
    public static ExtentSplit create(long j) {
        ExtentSplitI extentSplitI = (ExtentSplitI) Callback.get(j);
        return extentSplitI instanceof ExtentSplit ? (ExtentSplit) extentSplitI : new Container(j, extentSplitI);
    }

    public static ExtentSplit createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentSplit create(ExtentSplitI extentSplitI) {
        return extentSplitI instanceof ExtentSplit ? (ExtentSplit) extentSplitI : new Container(extentSplitI.address(), extentSplitI);
    }

    protected ExtentSplit() {
        super(CIF);
    }

    ExtentSplit(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentSplit$Container.class */
    public static final class Container extends ExtentSplit {
        private final ExtentSplitI delegate;

        Container(long j, ExtentSplitI extentSplitI) {
            super(j);
            this.delegate = extentSplitI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentSplitI
        public final boolean invoke(long j, long j2, long j3, long j4, long j5, boolean z, int i) {
            return this.delegate.invoke(j, j2, j3, j4, j5, z, i);
        }
    }
}
