package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTFramebufferBlitLayers.class */
public class EXTFramebufferBlitLayers {
    public static native void glBlitFramebufferLayersEXT(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLbitfield") int i9, @NativeType("GLenum") int i10);

    public static native void glBlitFramebufferLayerEXT(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLint") int i9, @NativeType("GLint") int i10, @NativeType("GLbitfield") int i11, @NativeType("GLenum") int i12);

    static {
        GL.initialize();
    }

    protected EXTFramebufferBlitLayers() {
        throw new UnsupportedOperationException();
    }
}
