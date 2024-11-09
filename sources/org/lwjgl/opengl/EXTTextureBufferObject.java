package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTTextureBufferObject.class */
public class EXTTextureBufferObject {
    public static final int GL_TEXTURE_BUFFER_EXT = 35882;
    public static final int GL_MAX_TEXTURE_BUFFER_SIZE_EXT = 35883;
    public static final int GL_TEXTURE_BINDING_BUFFER_EXT = 35884;
    public static final int GL_TEXTURE_BUFFER_DATA_STORE_BINDING_EXT = 35885;
    public static final int GL_TEXTURE_BUFFER_FORMAT_EXT = 35886;

    public static native void glTexBufferEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3);

    static {
        GL.initialize();
    }

    protected EXTTextureBufferObject() {
        throw new UnsupportedOperationException();
    }
}
