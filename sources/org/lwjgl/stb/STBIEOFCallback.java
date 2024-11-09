package org.lwjgl.stb;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIEOFCallback.class */
public abstract class STBIEOFCallback extends Callback implements STBIEOFCallbackI {
    public static STBIEOFCallback create(long j) {
        STBIEOFCallbackI sTBIEOFCallbackI = (STBIEOFCallbackI) Callback.get(j);
        return sTBIEOFCallbackI instanceof STBIEOFCallback ? (STBIEOFCallback) sTBIEOFCallbackI : new Container(j, sTBIEOFCallbackI);
    }

    public static STBIEOFCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static STBIEOFCallback create(STBIEOFCallbackI sTBIEOFCallbackI) {
        return sTBIEOFCallbackI instanceof STBIEOFCallback ? (STBIEOFCallback) sTBIEOFCallbackI : new Container(sTBIEOFCallbackI.address(), sTBIEOFCallbackI);
    }

    protected STBIEOFCallback() {
        super(CIF);
    }

    STBIEOFCallback(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIEOFCallback$Container.class */
    public static final class Container extends STBIEOFCallback {
        private final STBIEOFCallbackI delegate;

        Container(long j, STBIEOFCallbackI sTBIEOFCallbackI) {
            super(j);
            this.delegate = sTBIEOFCallbackI;
        }

        @Override // org.lwjgl.stb.STBIEOFCallbackI
        public final int invoke(long j) {
            return this.delegate.invoke(j);
        }
    }
}
