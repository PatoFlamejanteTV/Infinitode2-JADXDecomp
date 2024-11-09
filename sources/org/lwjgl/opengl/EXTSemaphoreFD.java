package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTSemaphoreFD.class */
public class EXTSemaphoreFD {
    public static final int GL_HANDLE_TYPE_OPAQUE_FD_EXT = 38278;

    public static native void glImportSemaphoreFdEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    static {
        GL.initialize();
    }

    protected EXTSemaphoreFD() {
        throw new UnsupportedOperationException();
    }
}
