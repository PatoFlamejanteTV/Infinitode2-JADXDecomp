package org.lwjgl.openal;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTCallbackBufferType.class */
public abstract class SOFTCallbackBufferType extends Callback implements SOFTCallbackBufferTypeI {
    public static SOFTCallbackBufferType create(long j) {
        SOFTCallbackBufferTypeI sOFTCallbackBufferTypeI = (SOFTCallbackBufferTypeI) Callback.get(j);
        return sOFTCallbackBufferTypeI instanceof SOFTCallbackBufferType ? (SOFTCallbackBufferType) sOFTCallbackBufferTypeI : new Container(j, sOFTCallbackBufferTypeI);
    }

    public static SOFTCallbackBufferType createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static SOFTCallbackBufferType create(SOFTCallbackBufferTypeI sOFTCallbackBufferTypeI) {
        return sOFTCallbackBufferTypeI instanceof SOFTCallbackBufferType ? (SOFTCallbackBufferType) sOFTCallbackBufferTypeI : new Container(sOFTCallbackBufferTypeI.address(), sOFTCallbackBufferTypeI);
    }

    protected SOFTCallbackBufferType() {
        super(CIF);
    }

    SOFTCallbackBufferType(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTCallbackBufferType$Container.class */
    public static final class Container extends SOFTCallbackBufferType {
        private final SOFTCallbackBufferTypeI delegate;

        Container(long j, SOFTCallbackBufferTypeI sOFTCallbackBufferTypeI) {
            super(j);
            this.delegate = sOFTCallbackBufferTypeI;
        }

        @Override // org.lwjgl.openal.SOFTCallbackBufferTypeI
        public final long invoke(long j, long j2, int i) {
            return this.delegate.invoke(j, j2, i);
        }
    }
}
