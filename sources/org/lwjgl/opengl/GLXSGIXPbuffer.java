package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.CLongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXSGIXPbuffer.class */
public class GLXSGIXPbuffer {
    public static final int GLX_MAX_PBUFFER_WIDTH_SGIX = 32790;
    public static final int GLX_MAX_PBUFFER_HEIGHT_SGIX = 32791;
    public static final int GLX_MAX_PBUFFER_PIXELS_SGIX = 32792;
    public static final int GLX_OPTIMAL_PBUFFER_WIDTH_SGIX = 32793;
    public static final int GLX_OPTIMAL_PBUFFER_HEIGHT_SGIX = 32794;
    public static final int GLX_PBUFFER_BIT_SGIX = 4;
    public static final int GLX_PRESERVED_CONTENTS_SGIX = 32795;
    public static final int GLX_LARGEST_PBUFFER_SGIX = 32796;
    public static final int GLX_WIDTH_SGIX = 32797;
    public static final int GLX_HEIGHT_SGIX = 32798;
    public static final int GLX_EVENT_MASK_SGIX = 32799;
    public static final int GLX_BUFFER_CLOBBER_MASK_SGIX = 134217728;
    public static final int GLX_DAMAGED_SGIX = 32800;
    public static final int GLX_SAVED_SGIX = 32801;
    public static final int GLX_WINDOW_SGIX = 32802;
    public static final int GLX_PBUFFER_SGIX = 32803;
    public static final int GLX_FRONT_LEFT_BUFFER_BIT_SGIX = 1;
    public static final int GLX_FRONT_RIGHT_BUFFER_BIT_SGIX = 2;
    public static final int GLX_BACK_LEFT_BUFFER_BIT_SGIX = 4;
    public static final int GLX_BACK_RIGHT_BUFFER_BIT_SGIX = 8;
    public static final int GLX_AUX_BUFFERS_BIT_SGIX = 16;
    public static final int GLX_DEPTH_BUFFER_BIT_SGIX = 32;
    public static final int GLX_STENCIL_BUFFER_BIT_SGIX = 64;
    public static final int GLX_ACCUM_BUFFER_BIT_SGIX = 128;
    public static final int GLX_SAMPLE_BUFFERS_BIT_SGIX = 256;

    protected GLXSGIXPbuffer() {
        throw new UnsupportedOperationException();
    }

    public static long nglXCreateGLXPbufferSGIX(long j, long j2, int i, int i2, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreateGLXPbufferSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPP(j, j2, i, i2, j3, j4);
    }

    @NativeType("GLXPbuffer")
    public static long glXCreateGLXPbufferSGIX(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("unsigned int") int i, @NativeType("unsigned int") int i2, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nglXCreateGLXPbufferSGIX(j, j2, i, i2, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glXDestroyGLXPbufferSGIX(@NativeType("Display *") long j, @NativeType("GLXPbuffer") long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXDestroyGLXPbufferSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, j3);
    }

    public static void nglXQueryGLXPbufferSGIX(long j, long j2, int i, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXQueryGLXPbufferSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPPV(j, j2, i, j3, j4);
    }

    public static void glXQueryGLXPbufferSGIX(@NativeType("Display *") long j, @NativeType("GLXPbuffer") long j2, int i, @NativeType("unsigned int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglXQueryGLXPbufferSGIX(j, j2, i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glXSelectEventSGIX(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("unsigned long") long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXSelectEventSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPNV(j, j2, j3, j4);
    }

    public static void nglXGetSelectedEventSGIX(long j, long j2, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXGetSelectedEventSGIX;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPPV(j, j2, j3, j4);
    }

    public static void glXGetSelectedEventSGIX(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("unsigned long *") CLongBuffer cLongBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) cLongBuffer, 1);
        }
        nglXGetSelectedEventSGIX(j, j2, MemoryUtil.memAddress(cLongBuffer));
    }

    @NativeType("GLXPbuffer")
    public static long glXCreateGLXPbufferSGIX(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("unsigned int") int i, @NativeType("unsigned int") int i2, @NativeType("int *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXCreateGLXPbufferSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPPP(j, j2, i, i2, iArr, j3);
    }

    public static void glXQueryGLXPbufferSGIX(@NativeType("Display *") long j, @NativeType("GLXPbuffer") long j2, int i, @NativeType("unsigned int *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXQueryGLXPbufferSGIX;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(iArr, 1);
        }
        JNI.callPPPV(j, j2, i, iArr, j3);
    }
}
