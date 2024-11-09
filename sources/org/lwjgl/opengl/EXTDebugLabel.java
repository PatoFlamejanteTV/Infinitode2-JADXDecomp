package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTDebugLabel.class */
public class EXTDebugLabel {
    public static final int GL_BUFFER_OBJECT_EXT = 37201;
    public static final int GL_SHADER_OBJECT_EXT = 35656;
    public static final int GL_PROGRAM_OBJECT_EXT = 35648;
    public static final int GL_VERTEX_ARRAY_OBJECT_EXT = 37204;
    public static final int GL_QUERY_OBJECT_EXT = 37203;
    public static final int GL_PROGRAM_PIPELINE_OBJECT_EXT = 35407;

    public static native void nglLabelObjectEXT(int i, int i2, int i3, long j);

    public static native void nglGetObjectLabelEXT(int i, int i2, int i3, long j, long j2);

    static {
        GL.initialize();
    }

    protected EXTDebugLabel() {
        throw new UnsupportedOperationException();
    }

    public static void glLabelObjectEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        nglLabelObjectEXT(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void glLabelObjectEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            nglLabelObjectEXT(i, i2, stackGet.nUTF8(charSequence, false), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetObjectLabelEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") IntBuffer intBuffer, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglGetObjectLabelEXT(i, i2, byteBuffer.remaining(), MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void")
    public static String glGetObjectLabelEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer ints = stackGet.ints(0);
            ByteBuffer malloc = stackGet.malloc(i3);
            nglGetObjectLabelEXT(i, i2, i3, MemoryUtil.memAddress(ints), MemoryUtil.memAddress(malloc));
            return MemoryUtil.memUTF8(malloc, ints.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void glGetObjectLabelEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2, @NativeType("GLsizei *") int[] iArr, @NativeType("GLchar *") ByteBuffer byteBuffer) {
        long j = GL.getICD().glGetObjectLabelEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.callPPV(i, i2, byteBuffer.remaining(), iArr, MemoryUtil.memAddress(byteBuffer), j);
    }
}
