package org.lwjgl.opengl;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGLNVGPUAffinity.class */
public class WGLNVGPUAffinity {
    public static final int ERROR_INCOMPATIBLE_AFFINITY_MASKS_NV = 8400;
    public static final int ERROR_MISSING_AFFINITY_MASK_NV = 8401;

    protected WGLNVGPUAffinity() {
        throw new UnsupportedOperationException();
    }

    public static int nwglEnumGpusNV(int i, long j) {
        long j2 = GL.getCapabilitiesWGL().wglEnumGpusNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPI(i, j, j2);
    }

    @NativeType("BOOL")
    public static boolean wglEnumGpusNV(@NativeType("UINT") int i, @NativeType("HGPUNV *") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nwglEnumGpusNV(i, MemoryUtil.memAddress(pointerBuffer)) != 0;
    }

    public static int nwglEnumGpuDevicesNV(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglEnumGpuDevicesNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean wglEnumGpuDevicesNV(@NativeType("HGPUNV") long j, @NativeType("UINT") int i, @NativeType("PGPU_DEVICE") GPU_DEVICE gpu_device) {
        return nwglEnumGpuDevicesNV(j, i, gpu_device.address()) != 0;
    }

    public static long nwglCreateAffinityDCNV(long j) {
        long j2 = GL.getCapabilitiesWGL().wglCreateAffinityDCNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.callPP(j, j2);
    }

    @NativeType("HDC")
    public static long wglCreateAffinityDCNV(@NativeType("HGPUNV const *") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT(pointerBuffer);
        }
        return nwglCreateAffinityDCNV(MemoryUtil.memAddress(pointerBuffer));
    }

    public static int nwglEnumGpusFromAffinityDCNV(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesWGL().wglEnumGpusFromAffinityDCNV;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPI(j, i, j2, j3);
    }

    @NativeType("BOOL")
    public static boolean wglEnumGpusFromAffinityDCNV(@NativeType("HDC") long j, @NativeType("UINT") int i, @NativeType("HGPUNV *") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nwglEnumGpusFromAffinityDCNV(j, i, MemoryUtil.memAddress(pointerBuffer)) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglDeleteDCNV(@NativeType("HDC") long j) {
        long j2 = GL.getCapabilitiesWGL().wglDeleteDCNV;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2) != 0;
    }
}
