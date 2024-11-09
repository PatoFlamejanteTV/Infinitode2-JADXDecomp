package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexAttribBinding.class */
public class ARBVertexAttribBinding {
    public static final int GL_VERTEX_ATTRIB_BINDING = 33492;
    public static final int GL_VERTEX_ATTRIB_RELATIVE_OFFSET = 33493;
    public static final int GL_VERTEX_BINDING_DIVISOR = 33494;
    public static final int GL_VERTEX_BINDING_OFFSET = 33495;
    public static final int GL_VERTEX_BINDING_STRIDE = 33496;
    public static final int GL_VERTEX_BINDING_BUFFER = 36687;
    public static final int GL_MAX_VERTEX_ATTRIB_RELATIVE_OFFSET = 33497;
    public static final int GL_MAX_VERTEX_ATTRIB_BINDINGS = 33498;

    public static native void glVertexArrayBindVertexBufferEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i4);

    public static native void glVertexArrayVertexAttribFormatEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i5);

    public static native void glVertexArrayVertexAttribIFormatEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5);

    public static native void glVertexArrayVertexAttribLFormatEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLint") int i3, @NativeType("GLenum") int i4, @NativeType("GLuint") int i5);

    public static native void glVertexArrayVertexAttribBindingEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    public static native void glVertexArrayVertexBindingDivisorEXT(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint") int i3);

    static {
        GL.initialize();
    }

    protected ARBVertexAttribBinding() {
        throw new UnsupportedOperationException();
    }

    public static void glBindVertexBuffer(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLintptr") long j, @NativeType("GLsizei") int i3) {
        GL43C.glBindVertexBuffer(i, i2, j, i3);
    }

    public static void glVertexAttribFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i4) {
        GL43C.glVertexAttribFormat(i, i2, i3, z, i4);
    }

    public static void glVertexAttribIFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4) {
        GL43C.glVertexAttribIFormat(i, i2, i3, i4);
    }

    public static void glVertexAttribLFormat(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4) {
        GL43C.glVertexAttribLFormat(i, i2, i3, i4);
    }

    public static void glVertexAttribBinding(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL43C.glVertexAttribBinding(i, i2);
    }

    public static void glVertexBindingDivisor(@NativeType("GLuint") int i, @NativeType("GLuint") int i2) {
        GL43C.glVertexBindingDivisor(i, i2);
    }
}
