package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTX11SyncObject.class */
public class EXTX11SyncObject {
    public static final int GL_SYNC_X11_FENCE_EXT = 37089;

    @NativeType("GLsync")
    public static native long glImportSyncEXT(@NativeType("GLenum") int i, @NativeType("GLintptr") long j, @NativeType("GLbitfield") int i2);

    static {
        GL.initialize();
    }

    protected EXTX11SyncObject() {
        throw new UnsupportedOperationException();
    }
}
