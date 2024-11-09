package org.lwjgl.system.jemalloc;

import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/MallocMessageCallback.class */
public abstract class MallocMessageCallback extends Callback implements MallocMessageCallbackI {
    public static MallocMessageCallback create(long j) {
        MallocMessageCallbackI mallocMessageCallbackI = (MallocMessageCallbackI) Callback.get(j);
        return mallocMessageCallbackI instanceof MallocMessageCallback ? (MallocMessageCallback) mallocMessageCallbackI : new Container(j, mallocMessageCallbackI);
    }

    public static MallocMessageCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static MallocMessageCallback create(MallocMessageCallbackI mallocMessageCallbackI) {
        return mallocMessageCallbackI instanceof MallocMessageCallback ? (MallocMessageCallback) mallocMessageCallbackI : new Container(mallocMessageCallbackI.address(), mallocMessageCallbackI);
    }

    protected MallocMessageCallback() {
        super(CIF);
    }

    MallocMessageCallback(long j) {
        super(j);
    }

    public static String getMessage(long j) {
        return MemoryUtil.memASCII(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/MallocMessageCallback$Container.class */
    public static final class Container extends MallocMessageCallback {
        private final MallocMessageCallbackI delegate;

        Container(long j, MallocMessageCallbackI mallocMessageCallbackI) {
            super(j);
            this.delegate = mallocMessageCallbackI;
        }

        @Override // org.lwjgl.system.jemalloc.MallocMessageCallbackI
        public final void invoke(long j, long j2) {
            this.delegate.invoke(j, j2);
        }
    }
}
