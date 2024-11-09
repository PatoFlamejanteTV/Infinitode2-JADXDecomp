package org.lwjgl.opengl;

import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTGeometryShader4.class */
public class EXTGeometryShader4 {
    public static final int GL_GEOMETRY_SHADER_EXT = 36313;
    public static final int GL_GEOMETRY_VERTICES_OUT_EXT = 36314;
    public static final int GL_GEOMETRY_INPUT_TYPE_EXT = 36315;
    public static final int GL_GEOMETRY_OUTPUT_TYPE_EXT = 36316;
    public static final int GL_MAX_GEOMETRY_TEXTURE_IMAGE_UNITS_EXT = 35881;
    public static final int GL_MAX_GEOMETRY_VARYING_COMPONENTS_EXT = 36317;
    public static final int GL_MAX_VERTEX_VARYING_COMPONENTS_EXT = 36318;
    public static final int GL_MAX_VARYING_COMPONENTS_EXT = 35659;
    public static final int GL_MAX_GEOMETRY_UNIFORM_COMPONENTS_EXT = 36319;
    public static final int GL_MAX_GEOMETRY_OUTPUT_VERTICES_EXT = 36320;
    public static final int GL_MAX_GEOMETRY_TOTAL_OUTPUT_COMPONENTS_EXT = 36321;
    public static final int GL_LINES_ADJACENCY_EXT = 10;
    public static final int GL_LINE_STRIP_ADJACENCY_EXT = 11;
    public static final int GL_TRIANGLES_ADJACENCY_EXT = 12;
    public static final int GL_TRIANGLE_STRIP_ADJACENCY_EXT = 13;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_TARGETS_EXT = 36264;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_LAYER_COUNT_EXT = 36265;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_LAYERED_EXT = 36263;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER_EXT = 36052;
    public static final int GL_PROGRAM_POINT_SIZE_EXT = 34370;

    public static native void glProgramParameteriEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3);

    public static native void glFramebufferTextureEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4);

    public static native void glFramebufferTextureFaceEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLenum") int i5);

    static {
        GL.initialize();
    }

    protected EXTGeometryShader4() {
        throw new UnsupportedOperationException();
    }

    public static void glFramebufferTextureLayerEXT(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        EXTTextureArray.glFramebufferTextureLayerEXT(i, i2, i3, i4, i5);
    }
}
