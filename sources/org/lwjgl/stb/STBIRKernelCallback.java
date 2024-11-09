package org.lwjgl.stb;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIRKernelCallback.class */
public abstract class STBIRKernelCallback extends Callback implements STBIRKernelCallbackI {
    public static STBIRKernelCallback create(long j) {
        STBIRKernelCallbackI sTBIRKernelCallbackI = (STBIRKernelCallbackI) Callback.get(j);
        return sTBIRKernelCallbackI instanceof STBIRKernelCallback ? (STBIRKernelCallback) sTBIRKernelCallbackI : new Container(j, sTBIRKernelCallbackI);
    }

    public static STBIRKernelCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIRKernelCallback create(STBIRKernelCallbackI sTBIRKernelCallbackI) {
        return sTBIRKernelCallbackI instanceof STBIRKernelCallback ? (STBIRKernelCallback) sTBIRKernelCallbackI : new Container(sTBIRKernelCallbackI.address(), sTBIRKernelCallbackI);
    }

    protected STBIRKernelCallback() {
        super(CIF);
    }

    STBIRKernelCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIRKernelCallback$Container.class */
    public static final class Container extends STBIRKernelCallback {
        private final STBIRKernelCallbackI delegate;

        Container(long j, STBIRKernelCallbackI sTBIRKernelCallbackI) {
            super(j);
            this.delegate = sTBIRKernelCallbackI;
        }

        @Override // org.lwjgl.stb.STBIRKernelCallbackI
        public final float invoke(float f, float f2, long j) {
            return this.delegate.invoke(f, f2, j);
        }
    }
}
