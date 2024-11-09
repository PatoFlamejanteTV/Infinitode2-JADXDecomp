package org.lwjgl.opengl;

import java.nio.IntBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/ARBVertexArrayObject.class */
public class ARBVertexArrayObject {
    public static final int GL_VERTEX_ARRAY_BINDING = 34229;

    static {
        GL.initialize();
    }

    protected ARBVertexArrayObject() {
        throw new UnsupportedOperationException();
    }

    public static void glBindVertexArray(@NativeType("GLuint") int i) {
        GL30C.glBindVertexArray(i);
    }

    public static void nglDeleteVertexArrays(int i, long j) {
        GL30C.nglDeleteVertexArrays(i, j);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") IntBuffer intBuffer) {
        GL30C.glDeleteVertexArrays(intBuffer);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") int i) {
        GL30C.glDeleteVertexArrays(i);
    }

    public static void nglGenVertexArrays(int i, long j) {
        GL30C.nglGenVertexArrays(i, j);
    }

    public static void glGenVertexArrays(@NativeType("GLuint *") IntBuffer intBuffer) {
        GL30C.glGenVertexArrays(intBuffer);
    }

    @NativeType("void")
    public static int glGenVertexArrays() {
        return GL30C.glGenVertexArrays();
    }

    @NativeType("GLboolean")
    public static boolean glIsVertexArray(@NativeType("GLuint") int i) {
        return GL30C.glIsVertexArray(i);
    }

    public static void glDeleteVertexArrays(@NativeType("GLuint const *") int[] iArr) {
        GL30C.glDeleteVertexArrays(iArr);
    }

    public static void glGenVertexArrays(@NativeType("GLuint *") int[] iArr) {
        GL30C.glGenVertexArrays(iArr);
    }
}
