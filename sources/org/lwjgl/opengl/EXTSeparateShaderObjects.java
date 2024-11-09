package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTSeparateShaderObjects.class */
public class EXTSeparateShaderObjects {
    public static final int GL_ACTIVE_PROGRAM_EXT = 35725;

    public static native void glUseShaderProgramEXT(@NativeType("GLenum") int i, @NativeType("GLuint") int i2);

    public static native void glActiveProgramEXT(@NativeType("GLuint") int i);

    public static native int nglCreateShaderProgramEXT(int i, long j);

    static {
        GL.initialize();
    }

    protected EXTSeparateShaderObjects() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramEXT(@NativeType("GLenum") int i, @NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglCreateShaderProgramEXT(i, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLuint")
    public static int glCreateShaderProgramEXT(@NativeType("GLenum") int i, @NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nglCreateShaderProgramEXT(i, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
