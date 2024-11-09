package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentCommit.class */
public abstract class ExtentCommit extends Callback implements ExtentCommitI {
    public static ExtentCommit create(long j) {
        ExtentCommitI extentCommitI = (ExtentCommitI) Callback.get(j);
        return extentCommitI instanceof ExtentCommit ? (ExtentCommit) extentCommitI : new Container(j, extentCommitI);
    }

    public static ExtentCommit createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentCommit create(ExtentCommitI extentCommitI) {
        return extentCommitI instanceof ExtentCommit ? (ExtentCommit) extentCommitI : new Container(extentCommitI.address(), extentCommitI);
    }

    protected ExtentCommit() {
        super(CIF);
    }

    ExtentCommit(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentCommit$Container.class */
    public static final class Container extends ExtentCommit {
        private final ExtentCommitI delegate;

        Container(long j, ExtentCommitI extentCommitI) {
            super(j);
            this.delegate = extentCommitI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentCommitI
        public final boolean invoke(long j, long j2, long j3, long j4, long j5, int i) {
            return this.delegate.invoke(j, j2, j3, j4, j5, i);
        }
    }
}
