package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBFramebufferObject.class */
public class ARBFramebufferObject {
    public static final int GL_FRAMEBUFFER = 36160;
    public static final int GL_READ_FRAMEBUFFER = 36008;
    public static final int GL_DRAW_FRAMEBUFFER = 36009;
    public static final int GL_RENDERBUFFER = 36161;
    public static final int GL_STENCIL_INDEX1 = 36166;
    public static final int GL_STENCIL_INDEX4 = 36167;
    public static final int GL_STENCIL_INDEX8 = 36168;
    public static final int GL_STENCIL_INDEX16 = 36169;
    public static final int GL_RENDERBUFFER_WIDTH = 36162;
    public static final int GL_RENDERBUFFER_HEIGHT = 36163;
    public static final int GL_RENDERBUFFER_INTERNAL_FORMAT = 36164;
    public static final int GL_RENDERBUFFER_RED_SIZE = 36176;
    public static final int GL_RENDERBUFFER_GREEN_SIZE = 36177;
    public static final int GL_RENDERBUFFER_BLUE_SIZE = 36178;
    public static final int GL_RENDERBUFFER_ALPHA_SIZE = 36179;
    public static final int GL_RENDERBUFFER_DEPTH_SIZE = 36180;
    public static final int GL_RENDERBUFFER_STENCIL_SIZE = 36181;
    public static final int GL_RENDERBUFFER_SAMPLES = 36011;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_TYPE = 36048;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_OBJECT_NAME = 36049;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LEVEL = 36050;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_CUBE_MAP_FACE = 36051;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_TEXTURE_LAYER = 36052;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_COLOR_ENCODING = 33296;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_COMPONENT_TYPE = 33297;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_RED_SIZE = 33298;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_GREEN_SIZE = 33299;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_BLUE_SIZE = 33300;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_ALPHA_SIZE = 33301;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_DEPTH_SIZE = 33302;
    public static final int GL_FRAMEBUFFER_ATTACHMENT_STENCIL_SIZE = 33303;
    public static final int GL_UNSIGNED_NORMALIZED = 35863;
    public static final int GL_FRAMEBUFFER_DEFAULT = 33304;
    public static final int GL_INDEX = 33314;
    public static final int GL_COLOR_ATTACHMENT0 = 36064;
    public static final int GL_COLOR_ATTACHMENT1 = 36065;
    public static final int GL_COLOR_ATTACHMENT2 = 36066;
    public static final int GL_COLOR_ATTACHMENT3 = 36067;
    public static final int GL_COLOR_ATTACHMENT4 = 36068;
    public static final int GL_COLOR_ATTACHMENT5 = 36069;
    public static final int GL_COLOR_ATTACHMENT6 = 36070;
    public static final int GL_COLOR_ATTACHMENT7 = 36071;
    public static final int GL_COLOR_ATTACHMENT8 = 36072;
    public static final int GL_COLOR_ATTACHMENT9 = 36073;
    public static final int GL_COLOR_ATTACHMENT10 = 36074;
    public static final int GL_COLOR_ATTACHMENT11 = 36075;
    public static final int GL_COLOR_ATTACHMENT12 = 36076;
    public static final int GL_COLOR_ATTACHMENT13 = 36077;
    public static final int GL_COLOR_ATTACHMENT14 = 36078;
    public static final int GL_COLOR_ATTACHMENT15 = 36079;
    public static final int GL_DEPTH_ATTACHMENT = 36096;
    public static final int GL_STENCIL_ATTACHMENT = 36128;
    public static final int GL_DEPTH_STENCIL_ATTACHMENT = 33306;
    public static final int GL_MAX_SAMPLES = 36183;
    public static final int GL_FRAMEBUFFER_COMPLETE = 36053;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_ATTACHMENT = 36054;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MISSING_ATTACHMENT = 36055;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_DRAW_BUFFER = 36059;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_READ_BUFFER = 36060;
    public static final int GL_FRAMEBUFFER_UNSUPPORTED = 36061;
    public static final int GL_FRAMEBUFFER_INCOMPLETE_MULTISAMPLE = 36182;
    public static final int GL_FRAMEBUFFER_UNDEFINED = 33305;
    public static final int GL_FRAMEBUFFER_BINDING = 36006;
    public static final int GL_DRAW_FRAMEBUFFER_BINDING = 36006;
    public static final int GL_READ_FRAMEBUFFER_BINDING = 36010;
    public static final int GL_RENDERBUFFER_BINDING = 36007;
    public static final int GL_MAX_COLOR_ATTACHMENTS = 36063;
    public static final int GL_MAX_RENDERBUFFER_SIZE = 34024;
    public static final int GL_INVALID_FRAMEBUFFER_OPERATION = 1286;
    public static final int GL_DEPTH_STENCIL = 34041;
    public static final int GL_UNSIGNED_INT_24_8 = 34042;
    public static final int GL_DEPTH24_STENCIL8 = 35056;
    public static final int GL_TEXTURE_STENCIL_SIZE = 35057;

    static {
        GL.initialize();
    }

    protected ARBFramebufferObject() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLboolean")
    public static boolean glIsRenderbuffer(@NativeType("GLuint") int i) {
        return GL30C.glIsRenderbuffer(i);
    }

    public static void glBindRenderbuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL30C.glBindRenderbuffer(i, i2);
    }

    public static void nglDeleteRenderbuffers(int i, long j) {
        GL30C.nglDeleteRenderbuffers(i, j);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glDeleteRenderbuffers(intBuffer);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") int i) {
        GL30C.glDeleteRenderbuffers(i);
    }

    public static void nglGenRenderbuffers(int i, long j) {
        GL30C.nglGenRenderbuffers(i, j);
    }

    public static void glGenRenderbuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGenRenderbuffers(intBuffer);
    }

    @NativeType("void")
    public static int glGenRenderbuffers() {
        return GL30C.glGenRenderbuffers();
    }

    public static void glRenderbufferStorage(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLsizei") int i3, @NativeType("GLsizei") int i4) {
        GL30C.glRenderbufferStorage(i, i2, i3, i4);
    }

    public static void glRenderbufferStorageMultisample(@NativeType("GLenum") int i, @NativeType("GLsizei") int i2, @NativeType("GLenum") int i3, @NativeType("GLsizei") int i4, @NativeType("GLsizei") int i5) {
        GL30C.glRenderbufferStorageMultisample(i, i2, i3, i4, i5);
    }

    public static void nglGetRenderbufferParameteriv(int i, int i2, long j) {
        GL30C.nglGetRenderbufferParameteriv(i, i2, j);
    }

    public static void glGetRenderbufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetRenderbufferParameteriv(i, i2, intBuffer);
    }

    @NativeType("void")
    public static int glGetRenderbufferParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2) {
        return GL30C.glGetRenderbufferParameteri(i, i2);
    }

    @NativeType("GLboolean")
    public static boolean glIsFramebuffer(@NativeType("GLuint") int i) {
        return GL30C.glIsFramebuffer(i);
    }

    public static void glBindFramebuffer(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        GL30C.glBindFramebuffer(i, i2);
    }

    public static void nglDeleteFramebuffers(int i, long j) {
        GL30C.nglDeleteFramebuffers(i, j);
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glDeleteFramebuffers(intBuffer);
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") int i) {
        GL30C.glDeleteFramebuffers(i);
    }

    public static void nglGenFramebuffers(int i, long j) {
        GL30C.nglGenFramebuffers(i, j);
    }

    public static void glGenFramebuffers(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGenFramebuffers(intBuffer);
    }

    @NativeType("void")
    public static int glGenFramebuffers() {
        return GL30C.glGenFramebuffers();
    }

    @NativeType("GLenum")
    public static int glCheckFramebufferStatus(@NativeType("GLenum") int i) {
        return GL30C.glCheckFramebufferStatus(i);
    }

    public static void glFramebufferTexture1D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5) {
        GL30C.glFramebufferTexture1D(i, i2, i3, i4, i5);
    }

    public static void glFramebufferTexture2D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5) {
        GL30C.glFramebufferTexture2D(i, i2, i3, i4, i5);
    }

    public static void glFramebufferTexture3D(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6) {
        GL30C.glFramebufferTexture3D(i, i2, i3, i4, i5, i6);
    }

    public static void glFramebufferTextureLayer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5) {
        GL30C.glFramebufferTextureLayer(i, i2, i3, i4, i5);
    }

    public static void glFramebufferRenderbuffer(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint") int i4) {
        GL30C.glFramebufferRenderbuffer(i, i2, i3, i4);
    }

    public static void nglGetFramebufferAttachmentParameteriv(int i, int i2, int i3, long j) {
        GL30C.nglGetFramebufferAttachmentParameteriv(i, i2, i3, j);
    }

    public static void glGetFramebufferAttachmentParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") IntBuffer intBuffer) {
        GL30C.glGetFramebufferAttachmentParameteriv(i, i2, i3, intBuffer);
    }

    @NativeType("void")
    public static int glGetFramebufferAttachmentParameteri(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        return GL30C.glGetFramebufferAttachmentParameteri(i, i2, i3);
    }

    public static void glBlitFramebuffer(@NativeType("GLint") int i, @NativeType("GLint") int i2, @NativeType("GLint") int i3, @NativeType("GLint") int i4, @NativeType("GLint") int i5, @NativeType("GLint") int i6, @NativeType("GLint") int i7, @NativeType("GLint") int i8, @NativeType("GLbitfield") int i9, @NativeType("GLenum") int i10) {
        GL30C.glBlitFramebuffer(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    public static void glGenerateMipmap(@NativeType("GLenum") int i) {
        GL30C.glGenerateMipmap(i);
    }

    public static void glDeleteRenderbuffers(@NativeType("GLuint const *") int[] iArr) {
        GL30C.glDeleteRenderbuffers(iArr);
    }

    public static void glGenRenderbuffers(@NativeType("GLuint *") int[] iArr) {
        GL30C.glGenRenderbuffers(iArr);
    }

    public static void glGetRenderbufferParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetRenderbufferParameteriv(i, i2, iArr);
    }

    public static void glDeleteFramebuffers(@NativeType("GLuint const *") int[] iArr) {
        GL30C.glDeleteFramebuffers(iArr);
    }

    public static void glGenFramebuffers(@NativeType("GLuint *") int[] iArr) {
        GL30C.glGenFramebuffers(iArr);
    }

    public static void glGetFramebufferAttachmentParameteriv(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLint *") int[] iArr) {
        GL30C.glGetFramebufferAttachmentParameteriv(i, i2, i3, iArr);
    }
}
