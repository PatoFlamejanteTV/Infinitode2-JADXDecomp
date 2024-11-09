package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTBindableUniform.class */
public class EXTBindableUniform {
    public static final int GL_MAX_VERTEX_BINDABLE_UNIFORMS_EXT = 36322;
    public static final int GL_MAX_FRAGMENT_BINDABLE_UNIFORMS_EXT = 36323;
    public static final int GL_MAX_GEOMETRY_BINDABLE_UNIFORMS_EXT = 36324;
    public static final int GL_MAX_BINDABLE_UNIFORM_SIZE_EXT = 36333;
    public static final int GL_UNIFORM_BUFFER_BINDING_EXT = 36335;
    public static final int GL_UNIFORM_BUFFER_EXT = 36334;

    public static native void glUniformBufferEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLuint") int i3);

    @NativeType("GLint")
    public static native int glGetUniformBufferSizeEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2);

    @NativeType("GLintptr")
    public static native long glGetUniformOffsetEXT(@NativeType("GLuint") int i, @NativeType("GLint") int i2);

    static {
        GL.initialize();
    }

    protected EXTBindableUniform() {
        throw new UnsupportedOperationException();
    }
}
