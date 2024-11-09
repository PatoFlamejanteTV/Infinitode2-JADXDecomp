package org.lwjgl.opengl;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLNVDXInterop.class */
public class WGLNVDXInterop {
    public static final int WGL_ACCESS_READ_ONLY_NV = 0;
    public static final int WGL_ACCESS_READ_WRITE_NV = 1;
    public static final int WGL_ACCESS_WRITE_DISCARD_NV = 2;

    protected WGLNVDXInterop() {
        throw new UnsupportedOperationException();
    }

    @NativeType("BOOL")
    public static boolean wglDXSetResourceShareHandleNV(@NativeType("void *") long j, @NativeType("HANDLE") long j2) {
        long j3 = GL.getCapabilitiesWGL().wglDXSetResourceShareHandleNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, j3) != 0;
    }

    @NativeType("HANDLE")
    public static long wglDXOpenDeviceNV(@NativeType("void *") long j) {
        long j2 = GL.getCapabilitiesWGL().wglDXOpenDeviceNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPP(j, j2);
    }

    @NativeType("BOOL")
    public static boolean wglDXCloseDeviceNV(@NativeType("HANDLE") long j) {
        long j2 = GL.getCapabilitiesWGL().wglDXCloseDeviceNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }

    @NativeType("HANDLE")
    public static long wglDXRegisterObjectNV(@NativeType("HANDLE") long j, @NativeType("void *") long j2, @NativeType("GLuint") int i, @NativeType("GLenum") int i2, @NativeType("GLenum") int i3) {
        long j3 = GL.getCapabilitiesWGL().wglDXRegisterObjectNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPP(j, j2, i, i2, i3, j3);
    }

    @NativeType("BOOL")
    public static boolean wglDXUnregisterObjectNV(@NativeType("HANDLE") long j, @NativeType("HANDLE") long j2) {
        long j3 = GL.getCapabilitiesWGL().wglDXUnregisterObjectNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, j3) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglDXObjectAccessNV(@NativeType("HANDLE") long j, @NativeType("GLenum") int i) {
        long j2 = GL.getCapabilitiesWGL().wglDXObjectAccessNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, i, j2) != 0;
    }

    public static int nwglDXLockObjectsNV(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglDXLockObjectsNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean wglDXLockObjectsNV(@NativeType("HANDLE") long j, @NativeType("HANDLE *") PointerBuffer pointerBuffer) {
        return nwglDXLockObjectsNV(j, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer)) != 0;
    }

    public static int nwglDXUnlockObjectsNV(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglDXUnlockObjectsNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean wglDXUnlockObjectsNV(@NativeType("HANDLE") long j, @NativeType("HANDLE *") PointerBuffer pointerBuffer) {
        return nwglDXUnlockObjectsNV(j, pointerBuffer.remaining(), MemoryUtil.memAddress(pointerBuffer)) != 0;
    }
}
