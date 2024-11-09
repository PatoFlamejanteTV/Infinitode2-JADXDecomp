package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIWriteCallback.class */
public abstract class STBIWriteCallback extends Callback implements STBIWriteCallbackI {
    public static STBIWriteCallback create(long j) {
        STBIWriteCallbackI sTBIWriteCallbackI = (STBIWriteCallbackI) Callback.get(j);
        return sTBIWriteCallbackI instanceof STBIWriteCallback ? (STBIWriteCallback) sTBIWriteCallbackI : new Container(j, sTBIWriteCallbackI);
    }

    public static STBIWriteCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIWriteCallback create(STBIWriteCallbackI sTBIWriteCallbackI) {
        return sTBIWriteCallbackI instanceof STBIWriteCallback ? (STBIWriteCallback) sTBIWriteCallbackI : new Container(sTBIWriteCallbackI.address(), sTBIWriteCallbackI);
    }

    protected STBIWriteCallback() {
        super(CIF);
    }

    STBIWriteCallback(long j) {
        super(j);
    }

    public static ByteBuffer getData(long j, int i) {
        return MemoryUtil.memByteBuffer(j, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIWriteCallback$Container.class */
    public static final class Container extends STBIWriteCallback {
        private final STBIWriteCallbackI delegate;

        Container(long j, STBIWriteCallbackI sTBIWriteCallbackI) {
            super(j);
            this.delegate = sTBIWriteCallbackI;
        }

        @Override // org.lwjgl.stb.STBIWriteCallbackI
        public final void invoke(long j, long j2, int i) {
            this.delegate.invoke(j, j2, i);
        }
    }
}
