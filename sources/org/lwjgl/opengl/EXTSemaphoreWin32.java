package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTSemaphoreWin32.class */
public class EXTSemaphoreWin32 {
    public static final int GL_HANDLE_TYPE_OPAQUE_WIN32_EXT = 38279;
    public static final int GL_HANDLE_TYPE_OPAQUE_WIN32_KMT_EXT = 38280;
    public static final int GL_DEVICE_LUID_EXT = 38297;
    public static final int GL_DEVICE_NODE_MASK_EXT = 38298;
    public static final int GL_LUID_SIZE_EXT = 8;
    public static final int GL_HANDLE_TYPE_D3D12_FENCE_EXT = 38292;
    public static final int GL_D3D12_FENCE_VALUE_EXT = 38293;

    public static native void nglImportSemaphoreWin32HandleEXT(int i, int i2, long j);

    public static native void nglImportSemaphoreWin32NameEXT(int i, int i2, long j);

    static {
        GL.initialize();
    }

    protected EXTSemaphoreWin32() {
        throw new UnsupportedOperationException();
    }

    public static void glImportSemaphoreWin32HandleEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nglImportSemaphoreWin32HandleEXT(i, i2, j);
    }

    public static void glImportSemaphoreWin32NameEXT(@NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("void const *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nglImportSemaphoreWin32NameEXT(i, i2, j);
    }
}
