package org.lwjgl.system;

import org.lwjgl.system.libffi.FFICIF;

/* loaded from: infinitode-2.jar:org/lwjgl/system/CallbackI.class */
public interface CallbackI extends Pointer {
    FFICIF getCallInterface();

    void callback(long j, long j2);

    @Override // org.lwjgl.system.Pointer
    default long address() {
        return Callback.create(getCallInterface(), this);
    }
}
