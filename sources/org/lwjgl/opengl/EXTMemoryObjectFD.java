package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTMemoryObjectFD.class */
public class EXTMemoryObjectFD {
    public static final int GL_HANDLE_TYPE_OPAQUE_FD_EXT = 38278;

    public static native void glImportMemoryFdEXT(@NativeType("GLuint") int i, @NativeType("GLuint64") long j, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    static {
        GL.initialize();
    }

    protected EXTMemoryObjectFD() {
        throw new UnsupportedOperationException();
    }
}
