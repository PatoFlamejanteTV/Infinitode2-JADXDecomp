package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/INTELMapTexture.class */
public class INTELMapTexture {
    public static final int GL_TEXTURE_MEMORY_LAYOUT_INTEL = 33791;
    public static final int GL_LAYOUT_DEFAULT_INTEL = 0;
    public static final int GL_LAYOUT_LINEAR_INTEL = 1;
    public static final int GL_LAYOUT_LINEAR_CPU_CACHED_INTEL = 2;

    public static native void glSyncTextureINTEL(@NativeType("GLuint") int i);

    public static native void glUnmapTexture2DINTEL(@NativeType("GLuint") int i, @NativeType("GLint") int i2);

    public static native long nglMapTexture2DINTEL(int i, int i2, int i3, long j, long j2);

    static {
        GL.initialize();
    }

    protected INTELMapTexture() {
        throw new UnsupportedOperationException();
    }

    @NativeType("void *")
    public static ByteBuffer glMapTexture2DINTEL(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLbitfield") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return MemoryUtil.memByteBufferSafe(nglMapTexture2DINTEL(i, i2, i3, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)), getStride(intBuffer) * GLChecks.getTexLevelParameteri(i, 3553, i2, 4097));
    }

    @NativeType("void *")
    public static ByteBuffer glMapTexture2DINTEL(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLbitfield") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapTexture2DINTEL(i, i2, i3, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)), getStride(intBuffer) * GLChecks.getTexLevelParameteri(i, 3553, i2, 4097));
    }

    @NativeType("void *")
    public static ByteBuffer glMapTexture2DINTEL(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLbitfield") int i3, @NativeType("GLint *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return APIUtil.apiGetMappedBuffer(byteBuffer, nglMapTexture2DINTEL(i, i2, i3, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)), (int) j);
    }

    @NativeType("void *")
    public static ByteBuffer glMapTexture2DINTEL(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLbitfield") int i3, @NativeType("GLint *") int[] iArr, @NativeType("GLenum *") int[] iArr2) {
        long j = GL.getICD().glMapTexture2DINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return MemoryUtil.memByteBufferSafe(JNI.callPPP(i, i2, i3, iArr, iArr2, j), getStride(iArr) * GLChecks.getTexLevelParameteri(i, 3553, i2, 4097));
    }

    @NativeType("void *")
    public static ByteBuffer glMapTexture2DINTEL(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLbitfield") int i3, @NativeType("GLint *") int[] iArr, @NativeType("GLenum *") int[] iArr2, ByteBuffer byteBuffer) {
        long j = GL.getICD().glMapTexture2DINTEL;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return APIUtil.apiGetMappedBuffer(byteBuffer, JNI.callPPP(i, i2, i3, iArr, iArr2, j), getStride(iArr) * GLChecks.getTexLevelParameteri(i, 3553, i2, 4097));
    }

    @NativeType("void *")
    public static ByteBuffer glMapTexture2DINTEL(@NativeType("GLuint") int i, @NativeType("GLint") int i2, @NativeType("GLbitfield") int i3, @NativeType("GLint *") int[] iArr, @NativeType("GLenum *") int[] iArr2, long j, ByteBuffer byteBuffer) {
        long j2 = GL.getICD().glMapTexture2DINTEL;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return APIUtil.apiGetMappedBuffer(byteBuffer, JNI.callPPP(i, i2, i3, iArr, iArr2, j2), (int) j);
    }

    private static int getStride(IntBuffer intBuffer) {
        return intBuffer.get(intBuffer.position());
    }

    private static int getStride(int[] iArr) {
        return iArr[0];
    }
}
