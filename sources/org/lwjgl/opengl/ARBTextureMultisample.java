package org.lwjgl.opengl;

import java.nio.FloatBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTextureMultisample.class */
public class ARBTextureMultisample {
    public static final int GL_SAMPLE_POSITION = 36432;
    public static final int GL_SAMPLE_MASK = 36433;
    public static final int GL_SAMPLE_MASK_VALUE = 36434;
    public static final int GL_TEXTURE_2D_MULTISAMPLE = 37120;
    public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE = 37121;
    public static final int GL_TEXTURE_2D_MULTISAMPLE_ARRAY = 37122;
    public static final int GL_PROXY_TEXTURE_2D_MULTISAMPLE_ARRAY = 37123;
    public static final int GL_MAX_SAMPLE_MASK_WORDS = 36441;
    public static final int GL_MAX_COLOR_TEXTURE_SAMPLES = 37134;
    public static final int GL_MAX_DEPTH_TEXTURE_SAMPLES = 37135;
    public static final int GL_MAX_INTEGER_SAMPLES = 37136;
    public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE = 37124;
    public static final int GL_TEXTURE_BINDING_2D_MULTISAMPLE_ARRAY = 37125;
    public static final int GL_TEXTURE_SAMPLES = 37126;
    public static final int GL_TEXTURE_FIXED_SAMPLE_LOCATIONS = 37127;
    public static final int GL_SAMPLER_2D_MULTISAMPLE = 37128;
    public static final int GL_INT_SAMPLER_2D_MULTISAMPLE = 37129;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE = 37130;
    public static final int GL_SAMPLER_2D_MULTISAMPLE_ARRAY = 37131;
    public static final int GL_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37132;
    public static final int GL_UNSIGNED_INT_SAMPLER_2D_MULTISAMPLE_ARRAY = 37133;

    static {
        GL.initialize();
    }

    protected ARBTextureMultisample() {
        throw new UnsupportedOperationException();
    }

    public static void glTexImage2DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLboolean") boolean z) {
        GL32C.glTexImage2DMultisample(i, i2, i3, i4, i5, z);
    }

    public static void glTexImage3DMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLboolean") boolean z) {
        GL32C.glTexImage3DMultisample(i, i2, i3, i4, i5, i6, z);
    }

    public static void nglGetMultisamplefv(int i, int i2, long j) {
        GL32C.nglGetMultisamplefv(i, i2, j);
    }

    public static void glGetMultisamplefv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        GL32C.glGetMultisamplefv(i, i2, floatBuffer);
    }

    @NativeType("void")
    public static float glGetMultisamplef(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return GL32C.glGetMultisamplef(i, i2);
    }

    public static void glSampleMaski(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2) {
        GL32C.glSampleMaski(i, i2);
    }

    public static void glGetMultisamplefv(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        GL32C.glGetMultisamplefv(i, i2, fArr);
    }
}
