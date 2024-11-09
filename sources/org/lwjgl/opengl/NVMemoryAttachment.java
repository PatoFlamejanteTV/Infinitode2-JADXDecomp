package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVMemoryAttachment.class */
public class NVMemoryAttachment {
    public static final int GL_ATTACHED_MEMORY_OBJECT_NV = 38308;
    public static final int GL_ATTACHED_MEMORY_OFFSET_NV = 38309;
    public static final int GL_MEMORY_ATTACHABLE_ALIGNMENT_NV = 38310;
    public static final int GL_MEMORY_ATTACHABLE_SIZE_NV = 38311;
    public static final int GL_MEMORY_ATTACHABLE_NV = 38312;
    public static final int GL_DETACHED_MEMORY_INCARNATION_NV = 38313;
    public static final int GL_DETACHED_TEXTURES_NV = 38314;
    public static final int GL_DETACHED_BUFFERS_NV = 38315;
    public static final int GL_MAX_DETACHED_TEXTURES_NV = 38316;
    public static final int GL_MAX_DETACHED_BUFFERS_NV = 38317;

    public static native void nglGetMemoryObjectDetachedResourcesuivNV(int i, int i2, int i3, int i4, long j);

    public static native void glResetMemoryObjectParameterNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2);

    public static native void glTexAttachMemoryNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j);

    public static native void glBufferAttachMemoryNV(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j);

    public static native void glTextureAttachMemoryNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j);

    public static native void glNamedBufferAttachMemoryNV(@NativeType("GLuint") int i, @NativeType("GLuint") int i2, @NativeType("GLuint64") long j);

    static {
        GL.initialize();
    }

    protected NVMemoryAttachment() {
        throw new UnsupportedOperationException();
    }

    public static void glGetMemoryObjectDetachedResourcesuivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLuint *") IntBuffer intBuffer) {
        nglGetMemoryObjectDetachedResourcesuivNV(i, i2, i3, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void glGetMemoryObjectDetachedResourcesuivNV(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLint") int i3, @NativeType("GLuint *") int[] iArr) {
        long j = GL.getICD().glGetMemoryObjectDetachedResourcesuivNV;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.callPV(i, i2, i3, iArr.length, iArr, j);
    }
}
