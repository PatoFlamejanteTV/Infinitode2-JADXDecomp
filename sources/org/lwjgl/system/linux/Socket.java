package org.lwjgl.system.linux;

import org.lwjgl.system.Library;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Socket.class */
public class Socket {
    public static final int SHUT_RD = 0;
    public static final int SHUT_WR = 1;
    public static final int SHUT_RDWR = 2;

    public static native int socket(int i, int i2, int i3);

    static {
        Library.initialize();
    }

    protected Socket() {
        throw new UnsupportedOperationException();
    }
}
