package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVExplicitMultisample.class */
public class NVExplicitMultisample {
    public static final int GL_SAMPLE_POSITION_NV = 36432;
    public static final int GL_SAMPLE_MASK_NV = 36433;
    public static final int GL_SAMPLE_MASK_VALUE_NV = 36434;
    public static final int GL_TEXTURE_BINDING_RENDERBUFFER_NV = 36435;
    public static final int GL_TEXTURE_RENDERBUFFER_DATA_STORE_BINDING_NV = 36436;
    public static final int GL_MAX_SAMPLE_MASK_WORDS_NV = 36441;
    public static final int GL_TEXTURE_RENDERBUFFER_NV = 36437;
    public static final int GL_SAMPLER_RENDERBUFFER_NV = 36438;
    public static final int GL_INT_SAMPLER_RENDERBUFFER_NV = 36439;
    public static final int GL_UNSIGNED_INT_SAMPLER_RENDERBUFFER_NV = 36440;

    public static native void nglGetMultisamplefvNV(int i, int i2, long j);

    public static native void glSampleMaskIndexedNV(@NativeType("GLuint") int i, @NativeType("GLbitfield") int i2);

    public static native void glTexRenderbufferNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    static {
        GL.initialize();
    }

    protected NVExplicitMultisample() {
        throw new UnsupportedOperationException();
    }

    public static void glGetMultisamplefvNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 2);
        }
        nglGetMultisamplefvNV(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    public static void glGetMultisamplefvNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLfloat *") float[] fArr) {
        long j = GL.getICD().glGetMultisamplefvNV;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 2);
        }
        JNI.callPV(i, i2, fArr, j);
    }
}
