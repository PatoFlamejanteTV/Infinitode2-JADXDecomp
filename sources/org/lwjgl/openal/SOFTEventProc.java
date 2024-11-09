package org.lwjgl.openal;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTEventProc.class */
public abstract class SOFTEventProc extends Callback implements SOFTEventProcI {
    public static SOFTEventProc create(long j) {
        SOFTEventProcI sOFTEventProcI = (SOFTEventProcI) Callback.get(j);
        return sOFTEventProcI instanceof SOFTEventProc ? (SOFTEventProc) sOFTEventProcI : new Container(j, sOFTEventProcI);
    }

    public static SOFTEventProc createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static SOFTEventProc create(SOFTEventProcI sOFTEventProcI) {
        return sOFTEventProcI instanceof SOFTEventProc ? (SOFTEventProc) sOFTEventProcI : new Container(sOFTEventProcI.address(), sOFTEventProcI);
    }

    protected SOFTEventProc() {
        super(CIF);
    }

    SOFTEventProc(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTEventProc$Container.class */
    public static final class Container extends SOFTEventProc {
        private final SOFTEventProcI delegate;

        Container(long j, SOFTEventProcI sOFTEventProcI) {
            super(j);
            this.delegate = sOFTEventProcI;
        }

        @Override // org.lwjgl.openal.SOFTEventProcI
        public final void invoke(int i, int i2, int i3, int i4, long j, long j2) {
            this.delegate.invoke(i, i2, i3, i4, j, j2);
        }
    }
}
