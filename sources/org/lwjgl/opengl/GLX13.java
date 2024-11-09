package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.CLongBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.linux.XVisualInfo;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLX13.class */
public class GLX13 extends GLX12 {
    public static final int GLX_WINDOW_BIT = 1;
    public static final int GLX_PIXMAP_BIT = 2;
    public static final int GLX_PBUFFER_BIT = 4;
    public static final int GLX_RGBA_BIT = 1;
    public static final int GLX_COLOR_INDEX_BIT = 2;
    public static final int GLX_PBUFFER_CLOBBER_MASK = 134217728;
    public static final int GLX_FRONT_LEFT_BUFFER_BIT = 1;
    public static final int GLX_FRONT_RIGHT_BUFFER_BIT = 2;
    public static final int GLX_BACK_LEFT_BUFFER_BIT = 4;
    public static final int GLX_BACK_RIGHT_BUFFER_BIT = 8;
    public static final int GLX_AUX_BUFFERS_BIT = 16;
    public static final int GLX_DEPTH_BUFFER_BIT = 32;
    public static final int GLX_STENCIL_BUFFER_BIT = 64;
    public static final int GLX_ACCUM_BUFFER_BIT = 128;
    public static final int GLX_CONFIG_CAVEAT = 32;
    public static final int GLX_X_VISUAL_TYPE = 34;
    public static final int GLX_TRANSPARENT_TYPE = 35;
    public static final int GLX_TRANSPARENT_INDEX_VALUE = 36;
    public static final int GLX_TRANSPARENT_RED_VALUE = 37;
    public static final int GLX_TRANSPARENT_GREEN_VALUE = 38;
    public static final int GLX_TRANSPARENT_BLUE_VALUE = 39;
    public static final int GLX_TRANSPARENT_ALPHA_VALUE = 40;
    public static final int GLX_DONT_CARE = -1;
    public static final int GLX_NONE = 32768;
    public static final int GLX_SLOW_CONFIG = 32769;
    public static final int GLX_TRUE_COLOR = 32770;
    public static final int GLX_DIRECT_COLOR = 32771;
    public static final int GLX_PSEUDO_COLOR = 32772;
    public static final int GLX_STATIC_COLOR = 32773;
    public static final int GLX_GRAY_SCALE = 32774;
    public static final int GLX_STATIC_GRAY = 32775;
    public static final int GLX_TRANSPARENT_RGB = 32776;
    public static final int GLX_TRANSPARENT_INDEX = 32777;
    public static final int GLX_VISUAL_ID = 32779;
    public static final int GLX_SCREEN = 32780;
    public static final int GLX_NON_CONFORMANT_CONFIG = 32781;
    public static final int GLX_DRAWABLE_TYPE = 32784;
    public static final int GLX_RENDER_TYPE = 32785;
    public static final int GLX_X_RENDERABLE = 32786;
    public static final int GLX_FBCONFIG_ID = 32787;
    public static final int GLX_RGBA_TYPE = 32788;
    public static final int GLX_COLOR_INDEX_TYPE = 32789;
    public static final int GLX_MAX_PBUFFER_WIDTH = 32790;
    public static final int GLX_MAX_PBUFFER_HEIGHT = 32791;
    public static final int GLX_MAX_PBUFFER_PIXELS = 32792;
    public static final int GLX_PRESERVED_CONTENTS = 32795;
    public static final int GLX_LARGEST_PBUFFER = 32796;
    public static final int GLX_WIDTH = 32797;
    public static final int GLX_HEIGHT = 32798;
    public static final int GLX_EVENT_MASK = 32799;
    public static final int GLX_DAMAGED = 32800;
    public static final int GLX_SAVED = 32801;
    public static final int GLX_WINDOW = 32802;
    public static final int GLX_PBUFFER = 32803;
    public static final int GLX_PBUFFER_HEIGHT = 32832;
    public static final int GLX_PBUFFER_WIDTH = 32833;

    /* JADX INFO: Access modifiers changed from: protected */
    public GLX13() {
        throw new UnsupportedOperationException();
    }

    public static long nglXGetFBConfigs(long j, int i, long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXGetFBConfigs;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPPP(j, i, j2, j3);
    }

    @NativeType("GLXFBConfig *")
    public static PointerBuffer glXGetFBConfigs(@NativeType("Display *") long j, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nglXGetFBConfigs(j, i, MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglXChooseFBConfig(long j, int i, long j2, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXChooseFBConfig;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.callPPPP(j, i, j2, j3, j4);
    }

    @NativeType("GLXFBConfig *")
    public static PointerBuffer glXChooseFBConfig(@NativeType("Display *") long j, int i, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nglXChooseFBConfig(j, i, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nglXGetFBConfigAttrib(long j, long j2, int i, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXGetFBConfigAttrib;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPI(j, j2, i, j3, j4);
    }

    public static int glXGetFBConfigAttrib(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXGetFBConfigAttrib(j, j2, i, MemoryUtil.memAddress(intBuffer));
    }

    public static long nglXGetVisualFromFBConfig(long j, long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXGetVisualFromFBConfig;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPP(j, j2, j3);
    }

    @NativeType("XVisualInfo *")
    public static XVisualInfo glXGetVisualFromFBConfig(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2) {
        return XVisualInfo.createSafe(nglXGetVisualFromFBConfig(j, j2));
    }

    public static long nglXCreateWindow(long j, long j2, long j3, long j4) {
        long j5 = GL.getCapabilitiesGLXClient().glXCreateWindow;
        if (Checks.CHECKS) {
            Checks.check(j5);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPNPP(j, j2, j3, j4, j5);
    }

    @NativeType("GLXWindow")
    public static long glXCreateWindow(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("Window") long j3, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nglXCreateWindow(j, j2, j3, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static long nglXCreatePixmap(long j, long j2, long j3, long j4) {
        long j5 = GL.getCapabilitiesGLXClient().glXCreatePixmap;
        if (Checks.CHECKS) {
            Checks.check(j5);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPNPP(j, j2, j3, j4, j5);
    }

    @NativeType("GLXPixmap")
    public static long glXCreatePixmap(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("Pixmap") long j3, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nglXCreatePixmap(j, j2, j3, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glXDestroyPixmap(@NativeType("Display *") long j, @NativeType("GLXPixmap") long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXDestroyPixmap;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, j3);
    }

    public static long nglXCreatePbuffer(long j, long j2, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreatePbuffer;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPP(j, j2, j3, j4);
    }

    @NativeType("GLXPbuffer")
    public static long glXCreatePbuffer(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("int const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nglXCreatePbuffer(j, j2, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void glXDestroyPbuffer(@NativeType("Display *") long j, @NativeType("GLXPbuffer") long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXDestroyPbuffer;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, j3);
    }

    public static void nglXQueryDrawable(long j, long j2, int i, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXQueryDrawable;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPPV(j, j2, i, j3, j4);
    }

    public static void glXQueryDrawable(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, int i, @NativeType("unsigned int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nglXQueryDrawable(j, j2, i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("void")
    public static int glXQueryDrawable(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nglXQueryDrawable(j, j2, i, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("GLXContext")
    public static long glXCreateNewContext(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, int i, @NativeType("GLXContext") long j3, @NativeType("Bool") boolean z) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreateNewContext;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPP(j, j2, i, j3, z ? 1 : 0, j4);
    }

    @NativeType("Bool")
    public static boolean glXMakeContextCurrent(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLXDrawable") long j3, @NativeType("GLXContext") long j4) {
        long j5 = GL.getCapabilitiesGLXClient().glXMakeContextCurrent;
        if (Checks.CHECKS) {
            Checks.check(j5);
            Checks.check(j);
        }
        return JNI.callPPPPI(j, j2, j3, j4, j5) != 0;
    }

    @NativeType("GLXDrawable")
    public static long glXGetCurrentReadDrawable() {
        long j = GL.getCapabilitiesGLXClient().glXGetCurrentReadDrawable;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }

    public static int nglXQueryContext(long j, long j2, int i, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXQueryContext;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPI(j, j2, i, j3, j4);
    }

    public static int glXQueryContext(@NativeType("Display *") long j, @NativeType("GLXContext") long j2, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXQueryContext(j, j2, i, MemoryUtil.memAddress(intBuffer));
    }

    public static void glXSelectEvent(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("unsigned long") long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXSelectEvent;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPNV(j, j2, j3, j4);
    }

    public static void nglXGetSelectedEvent(long j, long j2, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXGetSelectedEvent;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPPV(j, j2, j3, j4);
    }

    public static void glXGetSelectedEvent(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("unsigned long *") CLongBuffer cLongBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) cLongBuffer, 1);
        }
        nglXGetSelectedEvent(j, j2, MemoryUtil.memAddress(cLongBuffer));
    }

    @NativeType("GLXFBConfig *")
    public static PointerBuffer glXChooseFBConfig(@NativeType("Display *") long j, int i, @NativeType("int const *") int[] iArr) {
        long j2 = GL.getCapabilitiesGLXClient().glXChooseFBConfig;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(JNI.callPPPP(j, i, iArr, MemoryUtil.memAddress(callocInt), j2), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int glXGetFBConfigAttrib(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, int i, @NativeType("int *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXGetFBConfigAttrib;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(iArr, 1);
        }
        return JNI.callPPPI(j, j2, i, iArr, j3);
    }

    @NativeType("GLXWindow")
    public static long glXCreateWindow(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("Window") long j3, @NativeType("int const *") int[] iArr) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreateWindow;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPNPP(j, j2, j3, iArr, j4);
    }

    @NativeType("GLXPixmap")
    public static long glXCreatePixmap(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("Pixmap") long j3, @NativeType("int const *") int[] iArr) {
        long j4 = GL.getCapabilitiesGLXClient().glXCreatePixmap;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPNPP(j, j2, j3, iArr, j4);
    }

    @NativeType("GLXPbuffer")
    public static long glXCreatePbuffer(@NativeType("Display *") long j, @NativeType("GLXFBConfig") long j2, @NativeType("int const *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXCreatePbuffer;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.checkNTSafe(iArr);
        }
        return JNI.callPPPP(j, j2, iArr, j3);
    }

    public static void glXQueryDrawable(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, int i, @NativeType("unsigned int *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXQueryDrawable;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(iArr, 1);
        }
        JNI.callPPPV(j, j2, i, iArr, j3);
    }

    public static int glXQueryContext(@NativeType("Display *") long j, @NativeType("GLXContext") long j2, int i, @NativeType("int *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXQueryContext;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(iArr, 1);
        }
        return JNI.callPPPI(j, j2, i, iArr, j3);
    }
}
