package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentDalloc.class */
public abstract class ExtentDalloc extends Callback implements ExtentDallocI {
    public static ExtentDalloc create(long j) {
        ExtentDallocI extentDallocI = (ExtentDallocI) Callback.get(j);
        return extentDallocI instanceof ExtentDalloc ? (ExtentDalloc) extentDallocI : new Container(j, extentDallocI);
    }

    public static ExtentDalloc createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static ExtentDalloc create(ExtentDallocI extentDallocI) {
        return extentDallocI instanceof ExtentDalloc ? (ExtentDalloc) extentDallocI : new Container(extentDallocI.address(), extentDallocI);
    }

    protected ExtentDalloc() {
        super(CIF);
    }

    ExtentDalloc(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentDalloc$Container.class */
    public static final class Container extends ExtentDalloc {
        private final ExtentDallocI delegate;

        Container(long j, ExtentDallocI extentDallocI) {
            super(j);
            this.delegate = extentDallocI;
        }

        @Override // org.lwjgl.system.jemalloc.ExtentDallocI
        public final boolean invoke(long j, long j2, long j3, boolean z, int i) {
            return this.delegate.invoke(j, j2, j3, z, i);
        }
    }
}
