package org.lwjgl.system.linux;

import org.lwjgl.system.Library;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/PThread.class */
public class PThread {
    @NativeType("pthread_t")
    public static native long pthread_self();

    static {
        Library.initialize();
    }

    protected PThread() {
        throw new UnsupportedOperationException();
    }
}
