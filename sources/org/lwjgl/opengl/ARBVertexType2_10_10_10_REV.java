package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexType2_10_10_10_REV.class */
public class ARBVertexType2_10_10_10_REV {
    public static final int GL_INT_2_10_10_10_REV = 36255;

    static {
        GL.initialize();
    }

    protected ARBVertexType2_10_10_10_REV() {
        throw new UnsupportedOperationException();
    }

    public static void glVertexP2ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glVertexP2ui(i, i2);
    }

    public static void glVertexP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glVertexP3ui(i, i2);
    }

    public static void glVertexP4ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glVertexP4ui(i, i2);
    }

    public static void nglVertexP2uiv(int i, long j) {
        GL33.nglVertexP2uiv(i, j);
    }

    public static void glVertexP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glVertexP2uiv(i, intBuffer);
    }

    public static void nglVertexP3uiv(int i, long j) {
        GL33.nglVertexP3uiv(i, j);
    }

    public static void glVertexP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glVertexP3uiv(i, intBuffer);
    }

    public static void nglVertexP4uiv(int i, long j) {
        GL33.nglVertexP4uiv(i, j);
    }

    public static void glVertexP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glVertexP4uiv(i, intBuffer);
    }

    public static void glTexCoordP1ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glTexCoordP1ui(i, i2);
    }

    public static void glTexCoordP2ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glTexCoordP2ui(i, i2);
    }

    public static void glTexCoordP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glTexCoordP3ui(i, i2);
    }

    public static void glTexCoordP4ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glTexCoordP4ui(i, i2);
    }

    public static void nglTexCoordP1uiv(int i, long j) {
        GL33.nglTexCoordP1uiv(i, j);
    }

    public static void glTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glTexCoordP1uiv(i, intBuffer);
    }

    public static void nglTexCoordP2uiv(int i, long j) {
        GL33.nglTexCoordP2uiv(i, j);
    }

    public static void glTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glTexCoordP2uiv(i, intBuffer);
    }

    public static void nglTexCoordP3uiv(int i, long j) {
        GL33.nglTexCoordP3uiv(i, j);
    }

    public static void glTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glTexCoordP3uiv(i, intBuffer);
    }

    public static void nglTexCoordP4uiv(int i, long j) {
        GL33.nglTexCoordP4uiv(i, j);
    }

    public static void glTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glTexCoordP4uiv(i, intBuffer);
    }

    public static void glMultiTexCoordP1ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        GL33.glMultiTexCoordP1ui(i, i2, i3);
    }

    public static void glMultiTexCoordP2ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        GL33.glMultiTexCoordP2ui(i, i2, i3);
    }

    public static void glMultiTexCoordP3ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        GL33.glMultiTexCoordP3ui(i, i2, i3);
    }

    public static void glMultiTexCoordP4ui(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3) {
        GL33.glMultiTexCoordP4ui(i, i2, i3);
    }

    public static void nglMultiTexCoordP1uiv(int i, int i2, long j) {
        GL33.nglMultiTexCoordP1uiv(i, i2, j);
    }

    public static void glMultiTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glMultiTexCoordP1uiv(i, i2, intBuffer);
    }

    public static void nglMultiTexCoordP2uiv(int i, int i2, long j) {
        GL33.nglMultiTexCoordP2uiv(i, i2, j);
    }

    public static void glMultiTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glMultiTexCoordP2uiv(i, i2, intBuffer);
    }

    public static void nglMultiTexCoordP3uiv(int i, int i2, long j) {
        GL33.nglMultiTexCoordP3uiv(i, i2, j);
    }

    public static void glMultiTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glMultiTexCoordP3uiv(i, i2, intBuffer);
    }

    public static void nglMultiTexCoordP4uiv(int i, int i2, long j) {
        GL33.nglMultiTexCoordP4uiv(i, i2, j);
    }

    public static void glMultiTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glMultiTexCoordP4uiv(i, i2, intBuffer);
    }

    public static void glNormalP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glNormalP3ui(i, i2);
    }

    public static void nglNormalP3uiv(int i, long j) {
        GL33.nglNormalP3uiv(i, j);
    }

    public static void glNormalP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glNormalP3uiv(i, intBuffer);
    }

    public static void glColorP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glColorP3ui(i, i2);
    }

    public static void glColorP4ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glColorP4ui(i, i2);
    }

    public static void nglColorP3uiv(int i, long j) {
        GL33.nglColorP3uiv(i, j);
    }

    public static void glColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glColorP3uiv(i, intBuffer);
    }

    public static void nglColorP4uiv(int i, long j) {
        GL33.nglColorP4uiv(i, j);
    }

    public static void glColorP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glColorP4uiv(i, intBuffer);
    }

    public static void glSecondaryColorP3ui(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL33.glSecondaryColorP3ui(i, i2);
    }

    public static void nglSecondaryColorP3uiv(int i, long j) {
        GL33.nglSecondaryColorP3uiv(i, j);
    }

    public static void glSecondaryColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33.glSecondaryColorP3uiv(i, intBuffer);
    }

    public static void glVertexAttribP1ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP1ui(i, i2, z, i3);
    }

    public static void glVertexAttribP2ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP2ui(i, i2, z, i3);
    }

    public static void glVertexAttribP3ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP3ui(i, i2, z, i3);
    }

    public static void glVertexAttribP4ui(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint") int i3) {
        GL33C.glVertexAttribP4ui(i, i2, z, i3);
    }

    public static void nglVertexAttribP1uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP1uiv(i, i2, z, j);
    }

    public static void glVertexAttribP1uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP1uiv(i, i2, z, intBuffer);
    }

    public static void nglVertexAttribP2uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP2uiv(i, i2, z, j);
    }

    public static void glVertexAttribP2uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP2uiv(i, i2, z, intBuffer);
    }

    public static void nglVertexAttribP3uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP3uiv(i, i2, z, j);
    }

    public static void glVertexAttribP3uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP3uiv(i, i2, z, intBuffer);
    }

    public static void nglVertexAttribP4uiv(int i, int i2, boolean z, long j) {
        GL33C.nglVertexAttribP4uiv(i, i2, z, j);
    }

    public static void glVertexAttribP4uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") IntBuffer intBuffer) {
        GL33C.glVertexAttribP4uiv(i, i2, z, intBuffer);
    }

    public static void glVertexP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glVertexP2uiv(i, iArr);
    }

    public static void glVertexP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glVertexP3uiv(i, iArr);
    }

    public static void glVertexP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glVertexP4uiv(i, iArr);
    }

    public static void glTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glTexCoordP1uiv(i, iArr);
    }

    public static void glTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glTexCoordP2uiv(i, iArr);
    }

    public static void glTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glTexCoordP3uiv(i, iArr);
    }

    public static void glTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glTexCoordP4uiv(i, iArr);
    }

    public static void glMultiTexCoordP1uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL33.glMultiTexCoordP1uiv(i, i2, iArr);
    }

    public static void glMultiTexCoordP2uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL33.glMultiTexCoordP2uiv(i, i2, iArr);
    }

    public static void glMultiTexCoordP3uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL33.glMultiTexCoordP3uiv(i, i2, iArr);
    }

    public static void glMultiTexCoordP4uiv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint const *") int[] iArr) {
        GL33.glMultiTexCoordP4uiv(i, i2, iArr);
    }

    public static void glNormalP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glNormalP3uiv(i, iArr);
    }

    public static void glColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glColorP3uiv(i, iArr);
    }

    public static void glColorP4uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glColorP4uiv(i, iArr);
    }

    public static void glSecondaryColorP3uiv(@NativeType("GLenum") int i, @NativeType("GLuint const *") int[] iArr) {
        GL33.glSecondaryColorP3uiv(i, iArr);
    }

    public static void glVertexAttribP1uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP1uiv(i, i2, z, iArr);
    }

    public static void glVertexAttribP2uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP2uiv(i, i2, z, iArr);
    }

    public static void glVertexAttribP3uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP3uiv(i, i2, z, iArr);
    }

    public static void glVertexAttribP4uiv(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLboolean") boolean z, @NativeType("GLuint const *") int[] iArr) {
        GL33C.glVertexAttribP4uiv(i, i2, z, iArr);
    }
}
