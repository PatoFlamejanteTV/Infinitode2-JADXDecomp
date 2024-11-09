package org.lwjgl.opengl;

import org.lwjgl.system.Checks;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/EXTMemoryObjectWin32.class */
public class EXTMemoryObjectWin32 {
    public static final int GL_HANDLE_TYPE_OPAQUE_WIN32_EXT = 38279;
    public static final int GL_HANDLE_TYPE_OPAQUE_WIN32_KMT_EXT = 38280;
    public static final int GL_DEVICE_LUID_EXT = 38297;
    public static final int GL_DEVICE_NODE_MASK_EXT = 38298;
    public static final int GL_LUID_SIZE_EXT = 8;
    public static final int GL_HANDLE_TYPE_D3D12_TILEPOOL_EXT = 38281;
    public static final int GL_HANDLE_TYPE_D3D12_RESOURCE_EXT = 38282;
    public static final int GL_HANDLE_TYPE_D3D11_IMAGE_EXT = 38283;
    public static final int GL_HANDLE_TYPE_D3D11_IMAGE_KMT_EXT = 38284;

    public static native void nglImportMemoryWin32HandleEXT(int i, long j, int i2, long j2);

    public static native void nglImportMemoryWin32NameEXT(int i, long j, int i2, long j2);

    static {
        GL.initialize();
    }

    protected EXTMemoryObjectWin32() {
        throw new UnsupportedOperationException();
    }

    public static void glImportMemoryWin32HandleEXT(@NativeType("GLuint") int i, @NativeType("GLuint64") long j, @NativeType("GLenum") int i2, @NativeType("void *") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        nglImportMemoryWin32HandleEXT(i, j, i2, j2);
    }

    public static void glImportMemoryWin32NameEXT(@NativeType("GLuint") int i, @NativeType("GLuint64") long j, @NativeType("GLenum") int i2, @NativeType("void const *") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        nglImportMemoryWin32NameEXT(i, j, i2, j2);
    }
}
