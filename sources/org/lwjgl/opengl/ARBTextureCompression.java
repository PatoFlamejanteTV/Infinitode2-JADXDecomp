package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBTextureCompression.class */
public class ARBTextureCompression {
    public static final int GL_COMPRESSED_ALPHA_ARB = 34025;
    public static final int GL_COMPRESSED_LUMINANCE_ARB = 34026;
    public static final int GL_COMPRESSED_LUMINANCE_ALPHA_ARB = 34027;
    public static final int GL_COMPRESSED_INTENSITY_ARB = 34028;
    public static final int GL_COMPRESSED_RGB_ARB = 34029;
    public static final int GL_COMPRESSED_RGBA_ARB = 34030;
    public static final int GL_TEXTURE_COMPRESSION_HINT_ARB = 34031;
    public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE_ARB = 34464;
    public static final int GL_TEXTURE_COMPRESSED_ARB = 34465;
    public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS_ARB = 34466;
    public static final int GL_COMPRESSED_TEXTURE_FORMATS_ARB = 34467;

    public static native void nglCompressedTexImage3DARB(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglCompressedTexImage2DARB(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglCompressedTexImage1DARB(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void nglCompressedTexSubImage3DARB(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void nglCompressedTexSubImage2DARB(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglCompressedTexSubImage1DARB(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void nglGetCompressedTexImageARB(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected ARBTextureCompression() {
        throw new UnsupportedOperationException();
    }

    public static void glCompressedTexImage3DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        nglCompressedTexImage3DARB(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexImage3DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexImage3DARB(i, i2, i3, i4, i5, i6, 0, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTexImage2DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("void const *") long j) {
        nglCompressedTexImage2DARB(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedTexImage2DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexImage2DARB(i, i2, i3, i4, i5, 0, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTexImage1DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        nglCompressedTexImage1DARB(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexImage1DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexImage1DARB(i, i2, i3, i4, 0, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTexSubImage3DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLsizei") int i10, @NativeType("void const *") long j) {
        nglCompressedTexSubImage3DARB(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glCompressedTexSubImage3DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexSubImage3DARB(i, i2, i3, i4, i5, i6, i7, i8, i9, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTexSubImage2DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        nglCompressedTexSubImage2DARB(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexSubImage2DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexSubImage2DARB(i, i2, i3, i4, i5, i6, i7, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTexSubImage1DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        nglCompressedTexSubImage1DARB(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexSubImage1DARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexSubImage1DARB(i, i2, i3, i4, i5, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, GL11.glGetTexLevelParameteri(i, i2, 34464));
        }
        nglGetCompressedTexImageARB(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedTexImageARB(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") long j) {
        nglGetCompressedTexImageARB(i, i2, j);
    }
}
