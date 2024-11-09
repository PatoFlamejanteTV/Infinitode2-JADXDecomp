package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIReadCallback.class */
public abstract class STBIReadCallback extends Callback implements STBIReadCallbackI {
    public static STBIReadCallback create(long j) {
        STBIReadCallbackI sTBIReadCallbackI = (STBIReadCallbackI) Callback.get(j);
        return sTBIReadCallbackI instanceof STBIReadCallback ? (STBIReadCallback) sTBIReadCallbackI : new Container(j, sTBIReadCallbackI);
    }

    public static STBIReadCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIReadCallback create(STBIReadCallbackI sTBIReadCallbackI) {
        return sTBIReadCallbackI instanceof STBIReadCallback ? (STBIReadCallback) sTBIReadCallbackI : new Container(sTBIReadCallbackI.address(), sTBIReadCallbackI);
    }

    protected STBIReadCallback() {
        super(CIF);
    }

    STBIReadCallback(long j) {
        super(j);
    }

    public static ByteBuffer getData(long j, int i) {
        return MemoryUtil.memByteBuffer(j, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIReadCallback$Container.class */
    public static final class Container extends STBIReadCallback {
        private final STBIReadCallbackI delegate;

        Container(long j, STBIReadCallbackI sTBIReadCallbackI) {
            super(j);
            this.delegate = sTBIReadCallbackI;
        }

        @Override // org.lwjgl.stb.STBIReadCallbackI
        public final int invoke(long j, long j2, int i) {
            return this.delegate.invoke(j, j2, i);
        }
    }
}
