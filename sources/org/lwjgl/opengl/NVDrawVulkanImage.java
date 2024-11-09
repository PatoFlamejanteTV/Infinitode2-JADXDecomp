package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/NVDrawVulkanImage.class */
public class NVDrawVulkanImage {
    public static native void glDrawVkImageNV(@NativeType("GLuint64") long j, @NativeType("GLuint") int i, @NativeType("GLfloat") float f, @NativeType("GLfloat") float f2, @NativeType("GLfloat") float f3, @NativeType("GLfloat") float f4, @NativeType("GLfloat") float f5, @NativeType("GLfloat") float f6, @NativeType("GLfloat") float f7, @NativeType("GLfloat") float f8, @NativeType("GLfloat") float f9);

    public static native long nglGetVkProcAddrNV(long j);

    public static native void glWaitVkSemaphoreNV(@NativeType("GLuint64") long j);

    public static native void glSignalVkSemaphoreNV(@NativeType("GLuint64") long j);

    public static native void glSignalVkFenceNV(@NativeType("GLuint64") long j);

    static {
        GL.initialize();
    }

    protected NVDrawVulkanImage() {
        throw new UnsupportedOperationException();
    }

    @NativeType("VULKANPROCNV")
    public static long glGetVkProcAddrNV(@NativeType("GLchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglGetVkProcAddrNV(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("VULKANPROCNV")
    public static long glGetVkProcAddrNV(@NativeType("GLchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglGetVkProcAddrNV(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
