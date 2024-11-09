package org.lwjgl.stb;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIRSupportCallback.class */
public abstract class STBIRSupportCallback extends Callback implements STBIRSupportCallbackI {
    public static STBIRSupportCallback create(long j) {
        STBIRSupportCallbackI sTBIRSupportCallbackI = (STBIRSupportCallbackI) Callback.get(j);
        return sTBIRSupportCallbackI instanceof STBIRSupportCallback ? (STBIRSupportCallback) sTBIRSupportCallbackI : new Container(j, sTBIRSupportCallbackI);
    }

    public static STBIRSupportCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIRSupportCallback create(STBIRSupportCallbackI sTBIRSupportCallbackI) {
        return sTBIRSupportCallbackI instanceof STBIRSupportCallback ? (STBIRSupportCallback) sTBIRSupportCallbackI : new Container(sTBIRSupportCallbackI.address(), sTBIRSupportCallbackI);
    }

    protected STBIRSupportCallback() {
        super(CIF);
    }

    STBIRSupportCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIRSupportCallback$Container.class */
    public static final class Container extends STBIRSupportCallback {
        private final STBIRSupportCallbackI delegate;

        Container(long j, STBIRSupportCallbackI sTBIRSupportCallbackI) {
            super(j);
            this.delegate = sTBIRSupportCallbackI;
        }

        @Override // org.lwjgl.stb.STBIRSupportCallbackI
        public final float invoke(float f, long j) {
            return this.delegate.invoke(f, j);
        }
    }
}
