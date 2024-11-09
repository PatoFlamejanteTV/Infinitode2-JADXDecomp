package org.lwjgl.system.windows;

import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WindowProc.class */
public abstract class WindowProc extends Callback implements WindowProcI {
    public static WindowProc create(long j) {
        WindowProcI windowProcI = (WindowProcI) Callback.get(j);
        return windowProcI instanceof WindowProc ? (WindowProc) windowProcI : new Container(j, windowProcI);
    }

    public static WindowProc createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static WindowProc create(WindowProcI windowProcI) {
        return windowProcI instanceof WindowProc ? (WindowProc) windowProcI : new Container(windowProcI.address(), windowProcI);
    }

    protected WindowProc() {
        super(CIF);
    }

    WindowProc(long j) {
        super(j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WindowProc$Container.class */
    public static final class Container extends WindowProc {
        private final WindowProcI delegate;

        Container(long j, WindowProcI windowProcI) {
            super(j);
            this.delegate = windowProcI;
        }

        @Override // org.lwjgl.system.windows.WindowProcI
        public final long invoke(long j, int i, long j2, long j3) {
            return this.delegate.invoke(j, i, j2, j3);
        }
    }
}
