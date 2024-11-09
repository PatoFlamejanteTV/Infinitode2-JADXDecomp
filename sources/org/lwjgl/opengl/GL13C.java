package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GL13C.class */
public class GL13C extends GL12C {
    public static final int GL_COMPRESSED_RGB = 34029;
    public static final int GL_COMPRESSED_RGBA = 34030;
    public static final int GL_TEXTURE_COMPRESSION_HINT = 34031;
    public static final int GL_TEXTURE_COMPRESSED_IMAGE_SIZE = 34464;
    public static final int GL_TEXTURE_COMPRESSED = 34465;
    public static final int GL_NUM_COMPRESSED_TEXTURE_FORMATS = 34466;
    public static final int GL_COMPRESSED_TEXTURE_FORMATS = 34467;
    public static final int GL_TEXTURE_CUBE_MAP = 34067;
    public static final int GL_TEXTURE_BINDING_CUBE_MAP = 34068;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_X = 34069;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_X = 34070;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Y = 34071;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Y = 34072;
    public static final int GL_TEXTURE_CUBE_MAP_POSITIVE_Z = 34073;
    public static final int GL_TEXTURE_CUBE_MAP_NEGATIVE_Z = 34074;
    public static final int GL_PROXY_TEXTURE_CUBE_MAP = 34075;
    public static final int GL_MAX_CUBE_MAP_TEXTURE_SIZE = 34076;
    public static final int GL_MULTISAMPLE = 32925;
    public static final int GL_SAMPLE_ALPHA_TO_COVERAGE = 32926;
    public static final int GL_SAMPLE_ALPHA_TO_ONE = 32927;
    public static final int GL_SAMPLE_COVERAGE = 32928;
    public static final int GL_SAMPLE_BUFFERS = 32936;
    public static final int GL_SAMPLES = 32937;
    public static final int GL_SAMPLE_COVERAGE_VALUE = 32938;
    public static final int GL_SAMPLE_COVERAGE_INVERT = 32939;
    public static final int GL_TEXTURE0 = 33984;
    public static final int GL_TEXTURE1 = 33985;
    public static final int GL_TEXTURE2 = 33986;
    public static final int GL_TEXTURE3 = 33987;
    public static final int GL_TEXTURE4 = 33988;
    public static final int GL_TEXTURE5 = 33989;
    public static final int GL_TEXTURE6 = 33990;
    public static final int GL_TEXTURE7 = 33991;
    public static final int GL_TEXTURE8 = 33992;
    public static final int GL_TEXTURE9 = 33993;
    public static final int GL_TEXTURE10 = 33994;
    public static final int GL_TEXTURE11 = 33995;
    public static final int GL_TEXTURE12 = 33996;
    public static final int GL_TEXTURE13 = 33997;
    public static final int GL_TEXTURE14 = 33998;
    public static final int GL_TEXTURE15 = 33999;
    public static final int GL_TEXTURE16 = 34000;
    public static final int GL_TEXTURE17 = 34001;
    public static final int GL_TEXTURE18 = 34002;
    public static final int GL_TEXTURE19 = 34003;
    public static final int GL_TEXTURE20 = 34004;
    public static final int GL_TEXTURE21 = 34005;
    public static final int GL_TEXTURE22 = 34006;
    public static final int GL_TEXTURE23 = 34007;
    public static final int GL_TEXTURE24 = 34008;
    public static final int GL_TEXTURE25 = 34009;
    public static final int GL_TEXTURE26 = 34010;
    public static final int GL_TEXTURE27 = 34011;
    public static final int GL_TEXTURE28 = 34012;
    public static final int GL_TEXTURE29 = 34013;
    public static final int GL_TEXTURE30 = 34014;
    public static final int GL_TEXTURE31 = 34015;
    public static final int GL_ACTIVE_TEXTURE = 34016;
    public static final int GL_CLAMP_TO_BORDER = 33069;

    public static native void nglCompressedTexImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglCompressedTexImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j);

    public static native void nglCompressedTexImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void nglCompressedTexSubImage3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, long j);

    public static native void nglCompressedTexSubImage2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, long j);

    public static native void nglCompressedTexSubImage1D(int i, int i2, int i3, int i4, int i5, int i6, long j);

    public static native void nglGetCompressedTexImage(int i, int i2, long j);

    public static native void glSampleCoverage(@NativeType("GLfloat") float f, @NativeType("GLboolean") boolean z);

    public static native void glActiveTexture(@NativeType("GLenum") int i);

    static {
        GL.initialize();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GL13C() {
        throw new UnsupportedOperationException();
    }

    public static void glCompressedTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        nglCompressedTexImage3D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLint") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexImage3D(i, i2, i3, i4, i5, i6, i7, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("GLsizei") int i7, @NativeType("void const *") long j) {
        nglCompressedTexImage2D(i, i2, i3, i4, i5, i6, i7, j);
    }

    public static void glCompressedTexImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5, @NativeType("GLint") int i6, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexImage2D(i, i2, i3, i4, i5, i6, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        nglCompressedTexImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLint") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexImage1D(i, i2, i3, i4, i5, Checks.remainingSafe(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void glCompressedTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("GLsizei") int i10, @NativeType("void const *") long j) {
        nglCompressedTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, j);
    }

    public static void glCompressedTexSubImage3D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLsizei") int i6, @NativeType("GLsizei") int i7, @NativeType("GLsizei") int i8, @NativeType("GLenum") int i9, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexSubImage3D(i, i2, i3, i4, i5, i6, i7, i8, i9, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("GLsizei") int i8, @NativeType("void const *") long j) {
        nglCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, i8, j);
    }

    public static void glCompressedTexSubImage2D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLsizei") int i5, @NativeType("GLsizei") int i6, @NativeType("GLenum") int i7, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexSubImage2D(i, i2, i3, i4, i5, i6, i7, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glCompressedTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("GLsizei") int i6, @NativeType("void const *") long j) {
        nglCompressedTexSubImage1D(i, i2, i3, i4, i5, i6, j);
    }

    public static void glCompressedTexSubImage1D(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLsizei") int i4, @NativeType("GLenum") int i5, @NativeType("void const *") ByteBuffer byteBuffer) {
        nglCompressedTexSubImage1D(i, i2, i3, i4, i5, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS && Checks.DEBUG) {
            Checks.check((Buffer) byteBuffer, GL11.glGetTexLevelParameteri(i, i2, 34464));
        }
        nglGetCompressedTexImage(i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void glGetCompressedTexImage(@NativeType("GLenum") int i, @NativeType("GLint") int i2, @NativeType("void *") long j) {
        nglGetCompressedTexImage(i, i2, j);
    }
}
