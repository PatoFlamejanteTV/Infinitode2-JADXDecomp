package org.lwjgl.stb;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIRInputCallback.class */
public abstract class STBIRInputCallback extends Callback implements STBIRInputCallbackI {
    public static STBIRInputCallback create(long j) {
        STBIRInputCallbackI sTBIRInputCallbackI = (STBIRInputCallbackI) Callback.get(j);
        return sTBIRInputCallbackI instanceof STBIRInputCallback ? (STBIRInputCallback) sTBIRInputCallbackI : new Container(j, sTBIRInputCallbackI);
    }

    public static STBIRInputCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIRInputCallback create(STBIRInputCallbackI sTBIRInputCallbackI) {
        return sTBIRInputCallbackI instanceof STBIRInputCallback ? (STBIRInputCallback) sTBIRInputCallbackI : new Container(sTBIRInputCallbackI.address(), sTBIRInputCallbackI);
    }

    protected STBIRInputCallback() {
        super(CIF);
    }

    STBIRInputCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIRInputCallback$Container.class */
    public static final class Container extends STBIRInputCallback {
        private final STBIRInputCallbackI delegate;

        Container(long j, STBIRInputCallbackI sTBIRInputCallbackI) {
            super(j);
            this.delegate = sTBIRInputCallbackI;
        }

        @Override // org.lwjgl.stb.STBIRInputCallbackI
        public final void invoke(long j, long j2, int i, int i2, int i3, long j3) {
            this.delegate.invoke(j, j2, i, i2, i3, j3);
        }
    }
}
