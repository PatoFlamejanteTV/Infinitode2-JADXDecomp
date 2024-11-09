package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBDrawBuffersBlend.class */
public class ARBDrawBuffersBlend {
    public static native void glBlendEquationiARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glBlendEquationSeparateiARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3);

    public static native void glBlendFunciARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3);

    public static native void glBlendFuncSeparateiARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLenum") int i4, @NativeType("GLenum") int i5);

    static {
        GL.initialize();
    }

    protected ARBDrawBuffersBlend() {
        throw new UnsupportedOperationException();
    }
}
