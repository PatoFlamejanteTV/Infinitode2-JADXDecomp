package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTCompiledVertexArray.class */
public class EXTCompiledVertexArray {
    public static final int GL_ARRAY_ELEMENT_LOCK_FIRST_EXT = 33192;
    public static final int GL_ARRAY_ELEMENT_LOCK_COUNT_EXT = 33193;

    public static native void glLockArraysEXT(@NativeType("GLint") int i, @NativeType("GLsizei") int i2);

    public static native void glUnlockArraysEXT();

    static {
        GL.initialize();
    }

    protected EXTCompiledVertexArray() {
        throw new UnsupportedOperationException();
    }
}
