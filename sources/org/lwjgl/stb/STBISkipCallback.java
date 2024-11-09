package org.lwjgl.stb;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBISkipCallback.class */
public abstract class STBISkipCallback extends Callback implements STBISkipCallbackI {
    public static STBISkipCallback create(long j) {
        STBISkipCallbackI sTBISkipCallbackI = (STBISkipCallbackI) Callback.get(j);
        return sTBISkipCallbackI instanceof STBISkipCallback ? (STBISkipCallback) sTBISkipCallbackI : new Container(j, sTBISkipCallbackI);
    }

    public static STBISkipCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBISkipCallback create(STBISkipCallbackI sTBISkipCallbackI) {
        return sTBISkipCallbackI instanceof STBISkipCallback ? (STBISkipCallback) sTBISkipCallbackI : new Container(sTBISkipCallbackI.address(), sTBISkipCallbackI);
    }

    protected STBISkipCallback() {
        super(CIF);
    }

    STBISkipCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBISkipCallback$Container.class */
    public static final class Container extends STBISkipCallback {
        private final STBISkipCallbackI delegate;

        Container(long j, STBISkipCallbackI sTBISkipCallbackI) {
            super(j);
            this.delegate = sTBISkipCallbackI;
        }

        @Override // org.lwjgl.stb.STBISkipCallbackI
        public final void invoke(long j, int i) {
            this.delegate.invoke(j, i);
        }
    }
}
