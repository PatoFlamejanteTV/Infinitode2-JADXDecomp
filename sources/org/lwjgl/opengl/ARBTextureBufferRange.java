package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTextureBufferRange.class */
public class ARBTextureBufferRange {
    public static final int GL_TEXTURE_BUFFER_OFFSET = 37277;
    public static final int GL_TEXTURE_BUFFER_SIZE = 37278;
    public static final int GL_TEXTURE_BUFFER_OFFSET_ALIGNMENT = 37279;

    public static native void glTextureBufferRangeEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2);

    static {
        GL.initialize();
    }

    protected ARBTextureBufferRange() {
        throw new UnsupportedOperationException();
    }

    public static void glTexBufferRange(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizeiptr") long j2) {
        GL43C.glTexBufferRange(i, i2, i3, j, j2);
    }
}
