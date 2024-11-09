package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGL.class */
public class WGL {
    public static final int WGL_FONT_LINES = 0;
    public static final int WGL_FONT_POLYGONS = 1;
    public static final int WGL_SWAP_MAIN_PLANE = 1;
    public static final int WGL_SWAP_OVERLAY1 = 2;
    public static final int WGL_SWAP_OVERLAY2 = 4;
    public static final int WGL_SWAP_OVERLAY3 = 8;
    public static final int WGL_SWAP_OVERLAY4 = 16;
    public static final int WGL_SWAP_OVERLAY5 = 32;
    public static final int WGL_SWAP_OVERLAY6 = 64;
    public static final int WGL_SWAP_OVERLAY7 = 128;
    public static final int WGL_SWAP_OVERLAY8 = 256;
    public static final int WGL_SWAP_OVERLAY9 = 512;
    public static final int WGL_SWAP_OVERLAY10 = 1024;
    public static final int WGL_SWAP_OVERLAY11 = 2048;
    public static final int WGL_SWAP_OVERLAY12 = 4096;
    public static final int WGL_SWAP_OVERLAY13 = 8192;
    public static final int WGL_SWAP_OVERLAY14 = 16384;
    public static final int WGL_SWAP_OVERLAY15 = 32768;
    public static final int WGL_SWAP_UNDERLAY1 = 65536;
    public static final int WGL_SWAP_UNDERLAY2 = 131072;
    public static final int WGL_SWAP_UNDERLAY3 = 262144;
    public static final int WGL_SWAP_UNDERLAY4 = 524288;
    public static final int WGL_SWAP_UNDERLAY5 = 1048576;
    public static final int WGL_SWAP_UNDERLAY6 = 2097152;
    public static final int WGL_SWAP_UNDERLAY7 = 4194304;
    public static final int WGL_SWAP_UNDERLAY8 = 8388608;
    public static final int WGL_SWAP_UNDERLAY9 = 16777216;
    public static final int WGL_SWAP_UNDERLAY10 = 33554432;
    public static final int WGL_SWAP_UNDERLAY11 = 67108864;
    public static final int WGL_SWAP_UNDERLAY12 = 134217728;
    public static final int WGL_SWAP_UNDERLAY13 = 268435456;
    public static final int WGL_SWAP_UNDERLAY14 = 536870912;
    public static final int WGL_SWAP_UNDERLAY15 = 1073741824;

    public static native long nwglCreateContext(long j, long j2);

    public static native long nwglCreateLayerContext(long j, int i, long j2);

    public static native int nwglCopyContext(long j, long j2, int i, long j3);

    public static native int nwglDeleteContext(long j, long j2);

    public static native long nwglGetCurrentContext(long j);

    public static native long nwglGetCurrentDC(long j);

    public static native long nwglGetProcAddress(long j, long j2);

    public static native int nwglMakeCurrent(long j, long j2, long j3);

    public static native int nwglShareLists(long j, long j2, long j3);

    static {
        GL.initialize();
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/WGL$Functions.class */
    public static final class Functions {
        public static final long CreateContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglCreateContext");
        public static final long CreateLayerContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglCreateLayerContext");
        public static final long CopyContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglCopyContext");
        public static final long DeleteContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglDeleteContext");
        public static final long GetCurrentContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglGetCurrentContext");
        public static final long GetCurrentDC = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglGetCurrentDC");
        public static final long GetProcAddress = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglGetProcAddress");
        public static final long MakeCurrent = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglMakeCurrent");
        public static final long ShareLists = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "wglShareLists");

        private Functions() {
        }
    }

    protected WGL() {
        throw new UnsupportedOperationException();
    }

    @NativeType("HGLRC")
    public static long wglCreateContext(@NativeType("HDC") long j) {
        long j2 = Functions.CreateContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nwglCreateContext(j, j2);
    }

    @NativeType("HGLRC")
    public static long wglCreateLayerContext(@NativeType("HDC") long j, int i) {
        long j2 = Functions.CreateLayerContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nwglCreateLayerContext(j, i, j2);
    }

    @NativeType("BOOL")
    public static boolean wglCopyContext(@NativeType("HGLRC") long j, @NativeType("HGLRC") long j2, @NativeType("UINT") int i) {
        long j3 = Functions.CopyContext;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return nwglCopyContext(j, j2, i, j3) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglDeleteContext(@NativeType("HGLRC") long j) {
        long j2 = Functions.DeleteContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nwglDeleteContext(j, j2) != 0;
    }

    @NativeType("HGLRC")
    public static long wglGetCurrentContext() {
        return nwglGetCurrentContext(Functions.GetCurrentContext);
    }

    @NativeType("HDC")
    public static long wglGetCurrentDC() {
        return nwglGetCurrentDC(Functions.GetCurrentDC);
    }

    public static long nwglGetProcAddress(long j) {
        return nwglGetProcAddress(j, Functions.GetProcAddress);
    }

    @NativeType("PROC")
    public static long wglGetProcAddress(@NativeType("LPCSTR") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nwglGetProcAddress(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("PROC")
    public static long wglGetProcAddress(@NativeType("LPCSTR") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nwglGetProcAddress(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("BOOL")
    public static boolean wglMakeCurrent(@NativeType("HDC") long j, @NativeType("HGLRC") long j2) {
        return nwglMakeCurrent(j, j2, Functions.MakeCurrent) != 0;
    }

    @NativeType("BOOL")
    public static boolean wglShareLists(@NativeType("HGLRC") long j, @NativeType("HGLRC") long j2) {
        long j3 = Functions.ShareLists;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return nwglShareLists(j, j2, j3) != 0;
    }
}
