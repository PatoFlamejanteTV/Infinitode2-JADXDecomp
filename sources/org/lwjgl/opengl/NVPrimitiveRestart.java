package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVPrimitiveRestart.class */
public class NVPrimitiveRestart {
    public static final int GL_PRIMITIVE_RESTART_NV = 34136;
    public static final int GL_PRIMITIVE_RESTART_INDEX_NV = 34137;

    public static native void glPrimitiveRestartNV();

    public static native void glPrimitiveRestartIndexNV(@NativeType("GLuint") int i);

    static {
        GL.initialize();
    }

    protected NVPrimitiveRestart() {
        throw new UnsupportedOperationException();
    }
}
