package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/KHRDebug.class */
public class KHRDebug {
    public static final int GL_DEBUG_OUTPUT = 37600;
    public static final int GL_DEBUG_OUTPUT_SYNCHRONOUS = 33346;
    public static final int GL_CONTEXT_FLAG_DEBUG_BIT = 2;
    public static final int GL_MAX_DEBUG_MESSAGE_LENGTH = 37187;
    public static final int GL_MAX_DEBUG_LOGGED_MESSAGES = 37188;
    public static final int GL_DEBUG_LOGGED_MESSAGES = 37189;
    public static final int GL_DEBUG_NEXT_LOGGED_MESSAGE_LENGTH = 33347;
    public static final int GL_MAX_DEBUG_GROUP_STACK_DEPTH = 33388;
    public static final int GL_DEBUG_GROUP_STACK_DEPTH = 33389;
    public static final int GL_MAX_LABEL_LENGTH = 33512;
    public static final int GL_DEBUG_CALLBACK_FUNCTION = 33348;
    public static final int GL_DEBUG_CALLBACK_USER_PARAM = 33349;
    public static final int GL_DEBUG_SOURCE_API = 33350;
    public static final int GL_DEBUG_SOURCE_WINDOW_SYSTEM = 33351;
    public static final int GL_DEBUG_SOURCE_SHADER_COMPILER = 33352;
    public static final int GL_DEBUG_SOURCE_THIRD_PARTY = 33353;
    public static final int GL_DEBUG_SOURCE_APPLICATION = 33354;
    public static final int GL_DEBUG_SOURCE_OTHER = 33355;
    public static final int GL_DEBUG_TYPE_ERROR = 33356;
    public static final int GL_DEBUG_TYPE_DEPRECATED_BEHAVIOR = 33357;
    public static final int GL_DEBUG_TYPE_UNDEFINED_BEHAVIOR = 33358;
    public static final int GL_DEBUG_TYPE_PORTABILITY = 33359;
    public static final int GL_DEBUG_TYPE_PERFORMANCE = 33360;
    public static final int GL_DEBUG_TYPE_OTHER = 33361;
    public static final int GL_DEBUG_TYPE_MARKER = 33384;
    public static final int GL_DEBUG_TYPE_PUSH_GROUP = 33385;
    public static final int GL_DEBUG_TYPE_POP_GROUP = 33386;
    public static final int GL_DEBUG_SEVERITY_HIGH = 37190;
    public static final int GL_DEBUG_SEVERITY_MEDIUM = 37191;
    public static final int GL_DEBUG_SEVERITY_LOW = 37192;
    public static final int GL_DEBUG_SEVERITY_NOTIFICATION = 33387;
    public static final int GL_BUFFER = 33504;
    public static final int GL_SHADER = 33505;
    public static final int GL_PROGRAM = 33506;
    public static final int GL_QUERY = 33507;
    public static final int GL_PROGRAM_PIPELINE = 33508;
    public static final int GL_SAMPLER = 33510;
    public static final int GL_DISPLAY_LIST = 33511;

    static {
        GL.initialize();
    }

    protected KHRDebug() {
        throw new UnsupportedOperationException();
    }

    public static void nglDebugMessageControl(int i, int i2, int i3, int i4, long j, boolean z) {
        GL43C.nglDebugMessageControl(i, i2, i3, i4, j, z);
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") IntBuffer intBuffer, @NativeType("GLboolean") boolean z) {
        GL43C.glDebugMessageControl(i, i2, i3, intBuffer, z);
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int i4, @NativeType("GLboolean") boolean z) {
        GL43C.glDebugMessageControl(i, i2, i3, i4, z);
    }

    public static void nglDebugMessageInsert(int i, int i2, int i3, int i4, int i5, long j) {
        GL43C.nglDebugMessageInsert(i, i2, i3, i4, i5, j);
    }

    public static void glDebugMessageInsert(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glDebugMessageInsert(i, i2, i3, i4, byteBuffer);
    }

    public static void glDebugMessageInsert(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLuint") int i3, @NativeType("GLenum") int i4, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glDebugMessageInsert(i, i2, i3, i4, charSequence);
    }

    public static void nglDebugMessageCallback(long j, long j2) {
        GL43C.nglDebugMessageCallback(j, j2);
    }

    public static void glDebugMessageCallback(@NativeType("GLDEBUGPROC") GLDebugMessageCallbackI gLDebugMessageCallbackI, @NativeType("void const *") long j) {
        GL43C.glDebugMessageCallback(gLDebugMessageCallbackI, j);
    }

    public static int nglGetDebugMessageLog(int i, int i2, long j, long j2, long j3, long j4, long j5, long j6) {
        return GL43C.nglGetDebugMessageLog(i, i2, j, j2, j3, j4, j5, j6);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLog(@NativeType("GLuint") int i, @NativeType("GLenum *") IntBuffer intBuffer, @NativeType("GLenum *") IntBuffer intBuffer2, @NativeType("GLuint *") IntBuffer intBuffer3, @NativeType("GLenum *") IntBuffer intBuffer4, @NativeType("GLsizei *") IntBuffer intBuffer5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        return GL43C.glGetDebugMessageLog(i, intBuffer, intBuffer2, intBuffer3, intBuffer4, intBuffer5, byteBuffer);
    }

    public static void nglPushDebugGroup(int i, int i2, int i3, long j) {
        GL43C.nglPushDebugGroup(i, i2, i3, j);
    }

    public static void glPushDebugGroup(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glPushDebugGroup(i, i2, byteBuffer);
    }

    public static void glPushDebugGroup(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glPushDebugGroup(i, i2, charSequence);
    }

    public static void glPopDebugGroup() {
        GL43C.glPopDebugGroup();
    }

    public static void nglObjectLabel(int i, int i2, int i3, long j) {
        GL43C.nglObjectLabel(i, i2, i3, j);
    }

    public static void glObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glObjectLabel(i, i2, byteBuffer);
    }

    public static void glObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glObjectLabel(i, i2, charSequence);
    }

    public static void nglGetObjectLabel(int i, int i2, int i3, long j, long j2) {
        GL43C.nglGetObjectLabel(i, i2, i3, j, j2);
    }

    public static void glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectLabel(i, i2, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        return GL43C.glGetObjectLabel(i, i2, i3);
    }

    @NativeType("void")
    public static String glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2) {
        return glGetObjectLabel(i, i2, GL11.glGetInteger(33512));
    }

    public static void nglObjectPtrLabel(long j, int i, long j2) {
        GL43C.nglObjectPtrLabel(j, i, j2);
    }

    public static void glObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        GL43C.glObjectPtrLabel(j, byteBuffer);
    }

    public static void glObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLchar const *") CharSequence charSequence) {
        GL43C.glObjectPtrLabel(j, charSequence);
    }

    public static void nglGetObjectPtrLabel(long j, int i, long j2, long j3) {
        GL43C.nglGetObjectPtrLabel(j, i, j2, j3);
    }

    public static void glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectPtrLabel(j, intBuffer, byteBuffer);
    }

    @NativeType("void")
    public static String glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei") int i) {
        return GL43C.glGetObjectPtrLabel(j, i);
    }

    @NativeType("void")
    public static String glGetObjectPtrLabel(@NativeType("void *") long j) {
        return glGetObjectPtrLabel(j, GL11.glGetInteger(33512));
    }

    public static void glDebugMessageControl(@NativeType("GLenum") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3, @NativeType("GLuint const *") int[] iArr, @NativeType("GLboolean") boolean z) {
        GL43C.glDebugMessageControl(i, i2, i3, iArr, z);
    }

    @NativeType("GLuint")
    public static int glGetDebugMessageLog(@NativeType("GLuint") int i, @NativeType("GLenum *") int[] iArr, @NativeType("GLenum *") int[] iArr2, @NativeType("GLuint *") int[] iArr3, @NativeType("GLenum *") int[] iArr4, @NativeType("GLsizei *") int[] iArr5, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        return GL43C.glGetDebugMessageLog(i, iArr, iArr2, iArr3, iArr4, iArr5, byteBuffer);
    }

    public static void glGetObjectLabel(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectLabel(i, i2, iArr, byteBuffer);
    }

    public static void glGetObjectPtrLabel(@NativeType("void *") long j, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        GL43C.glGetObjectPtrLabel(j, iArr, byteBuffer);
    }
}
