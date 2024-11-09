package org.lwjgl.stb;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIROutputCallback.class */
public abstract class STBIROutputCallback extends Callback implements STBIROutputCallbackI {
    public static STBIROutputCallback create(long j) {
        STBIROutputCallbackI sTBIROutputCallbackI = (STBIROutputCallbackI) Callback.get(j);
        return sTBIROutputCallbackI instanceof STBIROutputCallback ? (STBIROutputCallback) sTBIROutputCallbackI : new Container(j, sTBIROutputCallbackI);
    }

    public static STBIROutputCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIROutputCallback create(STBIROutputCallbackI sTBIROutputCallbackI) {
        return sTBIROutputCallbackI instanceof STBIROutputCallback ? (STBIROutputCallback) sTBIROutputCallbackI : new Container(sTBIROutputCallbackI.address(), sTBIROutputCallbackI);
    }

    protected STBIROutputCallback() {
        super(CIF);
    }

    STBIROutputCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIROutputCallback$Container.class */
    public static final class Container extends STBIROutputCallback {
        private final STBIROutputCallbackI delegate;

        Container(long j, STBIROutputCallbackI sTBIROutputCallbackI) {
            super(j);
            this.delegate = sTBIROutputCallbackI;
        }

        @Override // org.lwjgl.stb.STBIROutputCallbackI
        public final void invoke(long j, int i, int i2, int i3, long j2) {
            this.delegate.invoke(j, i, i2, i3, j2);
        }
    }
}
