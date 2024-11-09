package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDInterleavedElements.class */
public class AMDInterleavedElements {
    public static final int GL_VERTEX_ELEMENT_SWIZZLE_AMD = 37284;
    public static final int GL_VERTEX_ID_SWIZZLE_AMD = 37285;

    public static native void glVertexAttribParameteriAMD(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    static {
        GL.initialize();
    }

    protected AMDInterleavedElements() {
        throw new UnsupportedOperationException();
    }
}
