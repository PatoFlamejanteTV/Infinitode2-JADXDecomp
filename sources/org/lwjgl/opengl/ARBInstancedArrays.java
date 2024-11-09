package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBInstancedArrays.class */
public class ARBInstancedArrays {
    public static final int GL_VERTEX_ATTRIB_ARRAY_DIVISOR_ARB = 35070;

    public static native void glVertexAttribDivisorARB(@NativeType("GLuint") int i, @NativeType("GLuint") int i2);

    public static native void glVertexArrayVertexAttribDivisorEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    static {
        GL.initialize();
    }

    protected ARBInstancedArrays() {
        throw new UnsupportedOperationException();
    }
}
