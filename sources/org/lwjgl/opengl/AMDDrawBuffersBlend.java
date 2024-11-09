package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/AMDDrawBuffersBlend.class */
public class AMDDrawBuffersBlend {
    public static native void glBlendFuncIndexedAMD(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3);

    public static native void glBlendFuncSeparateIndexedAMD(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5);

    public static native void glBlendEquationIndexedAMD(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glBlendEquationSeparateIndexedAMD(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3);

    static {
        GL.initialize();
    }

    protected AMDDrawBuffersBlend() {
        throw new UnsupportedOperationException();
    }
}
