package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBGeometryShader4.class */
public class ARBGeometryShader4 {
    public static final int GL_GEOMETRY_SHADER_ARB = 36313;
    public static final int GL_GEOMETRY_VERTICES_OUT_ARB = 36314;
    public static final int GL_GEOMETRY_INPUT_TYPE_ARB = 36315;
    public static final int GL_GEOMETRY_OUTPUT_TYPE_ARB = 36316;
    public static final int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS_ARB = 35881;
    public static final int GL_MAX_GEOMETRY_VARYING_COMPONENTS_ARB = 36317;
    public static final int GL_MAX_VERTEX_VARYING_COMPONENTS_ARB = 36318;
    public static final int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS_ARB = 36319;
    public static final int GL_MAX_GEOMETRY_OUTPUT_VERTICES_ARB = 36320;
    public static final int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS_ARB = 36321;
    public static final int GL_LINES_ADJACENCY_ARB = 10;
    public static final int GL_LINE_STRIP_ADJACENCY_ARB = 11;
    public static final int GL_TRIANGLES_ADJACENCY_ARB = 12;
    public static final int GL_TRIANGLE_STRIP_ADJACENCY_ARB = 13;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS_ARB = 36264;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_COUNT_ARB = 36265;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED_ARB = 36263;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
    public static final int GL_PROGRAM_POINT_SIZE_ARB = 34370;

    public static native void glProgramParameteriARB(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void glFramebufferTextureARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4);

    public static native void glFramebufferTextureLayerARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5);

    public static native void glFramebufferTextureFaceARB(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5);

    static {
        GL.initialize();
    }

    protected ARBGeometryShader4() {
        throw new UnsupportedOperationException();
    }
}
