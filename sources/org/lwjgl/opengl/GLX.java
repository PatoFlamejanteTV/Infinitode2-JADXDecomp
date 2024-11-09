package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.linux.XVisualInfo;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLX.class */
public class GLX {
    public static final int GLXBadContext = 0;
    public static final int GLXBadContextState = 1;
    public static final int GLXBadDrawable = 2;
    public static final int GLXBadPixmap = 3;
    public static final int GLXBadContextTag = 4;
    public static final int GLXBadCurrentWindow = 5;
    public static final int GLXBadRenderRequest = 6;
    public static final int GLXBadLargeRequest = 7;
    public static final int GLXUnsupportedPrivateRequest = 8;
    public static final int GLXBadFBConfig = 9;
    public static final int GLXBadPbuffer = 10;
    public static final int GLXBadCurrentDrawable = 11;
    public static final int GLXBadWindow = 12;
    public static final int GLX_USE_GL = 1;
    public static final int GLX_BUFFER_SIZE = 2;
    public static final int GLX_LEVEL = 3;
    public static final int GLX_RGBA = 4;
    public static final int GLX_DOUBLEBUFFER = 5;
    public static final int GLX_STEREO = 6;
    public static final int GLX_AUX_BUFFERS = 7;
    public static final int GLX_RED_SIZE = 8;
    public static final int GLX_GREEN_SIZE = 9;
    public static final int GLX_BLUE_SIZE = 10;
    public static final int GLX_ALPHA_SIZE = 11;
    public static final int GLX_DEPTH_SIZE = 12;
    public static final int GLX_STENCIL_SIZE = 13;
    public static final int GLX_ACCUM_RED_SIZE = 14;
    public static final int GLX_ACCUM_GREEN_SIZE = 15;
    public static final int GLX_ACCUM_BLUE_SIZE = 16;
    public static final int GLX_ACCUM_ALPHA_SIZE = 17;
    public static final int GLX_BAD_SCREEN = 1;
    public static final int GLX_BAD_ATTRIBUTE = 2;
    public static final int GLX_NO_EXTENSION = 3;
    public static final int GLX_BAD_VISUAL = 4;
    public static final int GLX_BAD_CONTEXT = 5;
    public static final int GLX_BAD_VALUE = 6;
    public static final int GLX_BAD_ENUM = 7;

    /* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLX$Functions.class */
    public static final class Functions {
        public static final long QueryExtension = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXQueryExtension");
        public static final long QueryVersion = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXQueryVersion");
        public static final long GetConfig = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXGetConfig");
        public static final long ChooseVisual = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXChooseVisual");
        public static final long CreateContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXCreateContext");
        public static final long MakeCurrent = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXMakeCurrent");
        public static final long CopyContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXCopyContext");
        public static final long IsDirect = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXIsDirect");
        public static final long DestroyContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXDestroyContext");
        public static final long GetCurrentContext = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXGetCurrentContext");
        public static final long GetCurrentDrawable = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXGetCurrentDrawable");
        public static final long WaitGL = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXWaitGL");
        public static final long WaitX = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXWaitX");
        public static final long SwapBuffers = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXSwapBuffers");
        public static final long UseXFont = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXUseXFont");
        public static final long CreateGLXPixmap = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXCreateGLXPixmap");
        public static final long DestroyGLXPixmap = APIUtil.apiGetFunctionAddress(GL.getFunctionProvider(), "glXDestroyGLXPixmap");

        private Functions() {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public GLX() {
        throw new UnsupportedOperationException();
    }

    public static int nglXQueryExtension(long j, long j2, long j3) {
        long j4 = Functions.QueryExtension;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPPI(j, j2, j3, j4);
    }

    @NativeType("Bool")
    public static boolean glXQueryExtension(@NativeType("Display *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nglXQueryExtension(j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)) != 0;
    }

    public static int nglXQueryVersion(long j, long j2, long j3) {
        long j4 = Functions.QueryVersion;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPPI(j, j2, j3, j4);
    }

    @NativeType("Bool")
    public static boolean glXQueryVersion(@NativeType("Display *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
            Checks.check((Buffer) intBuffer2, 1);
        }
        return nglXQueryVersion(j, MemoryUtil.memAddress(intBuffer), MemoryUtil.memAddress(intBuffer2)) != 0;
    }

    public static int nglXGetConfig(long j, long j2, int i, long j3) {
        long j4 = Functions.GetConfig;
        if (Checks.CHECKS) {
            Checks.check(j);
            XVisualInfo.validate(j2);
        }
        return JNI.callPPPI(j, j2, i, j3, j4);
    }

    public static int glXGetConfig(@NativeType("Display *") long j, @NativeType("XVisualInfo *") XVisualInfo xVisualInfo, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXGetConfig(j, xVisualInfo.address(), i, MemoryUtil.memAddress(intBuffer));
    }

    public static long nglXChooseVisual(long j, int i, long j2) {
        long j3 = Functions.ChooseVisual;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPP(j, i, j2, j3);
    }

    @NativeType("XVisualInfo *")
    public static XVisualInfo glXChooseVisual(@NativeType("Display *") long j, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return XVisualInfo.createSafe(nglXChooseVisual(j, i, MemoryUtil.memAddressSafe(intBuffer)));
    }

    public static long nglXCreateContext(long j, long j2, long j3, int i) {
        long j4 = Functions.CreateContext;
        if (Checks.CHECKS) {
            Checks.check(j);
            XVisualInfo.validate(j2);
        }
        return JNI.callPPPP(j, j2, j3, i, j4);
    }

    @NativeType("GLXContext")
    public static long glXCreateContext(@NativeType("Display *") long j, @NativeType("XVisualInfo *") XVisualInfo xVisualInfo, @NativeType("GLXContext") long j2, @NativeType("Bool") boolean z) {
        return nglXCreateContext(j, xVisualInfo.address(), j2, z ? 1 : 0);
    }

    @NativeType("Bool")
    public static boolean glXMakeCurrent(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2, @NativeType("GLXContext") long j3) {
        long j4 = Functions.MakeCurrent;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPPPI(j, j2, j3, j4) != 0;
    }

    public static void glXCopyContext(@NativeType("Display *") long j, @NativeType("GLXContext") long j2, @NativeType("GLXContext") long j3, @NativeType("unsigned long") long j4) {
        long j5 = Functions.CopyContext;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
            Checks.check(j3);
        }
        JNI.callPPPNV(j, j2, j3, j4, j5);
    }

    @NativeType("Bool")
    public static boolean glXIsDirect(@NativeType("Display *") long j, @NativeType("GLXContext") long j2) {
        long j3 = Functions.IsDirect;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPI(j, j2, j3) != 0;
    }

    public static void glXDestroyContext(@NativeType("Display *") long j, @NativeType("GLXContext") long j2) {
        long j3 = Functions.DestroyContext;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, j3);
    }

    @NativeType("GLXContext")
    public static long glXGetCurrentContext() {
        return JNI.callP(Functions.GetCurrentContext);
    }

    @NativeType("GLXDrawable")
    public static long glXGetCurrentDrawable() {
        return JNI.callP(Functions.GetCurrentDrawable);
    }

    public static void glXWaitGL() {
        JNI.callV(Functions.WaitGL);
    }

    public static void glXWaitX() {
        JNI.callV(Functions.WaitX);
    }

    public static void glXSwapBuffers(@NativeType("Display *") long j, @NativeType("GLXDrawable") long j2) {
        long j3 = Functions.SwapBuffers;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, j3);
    }

    public static void glXUseXFont(@NativeType("Font") long j, int i, int i2, int i3) {
        JNI.callNV(j, i, i2, i3, Functions.UseXFont);
    }

    public static long nglXCreateGLXPixmap(long j, long j2, long j3) {
        long j4 = Functions.CreateGLXPixmap;
        if (Checks.CHECKS) {
            Checks.check(j);
            XVisualInfo.validate(j2);
        }
        return JNI.callPPNP(j, j2, j3, j4);
    }

    @NativeType("GLXPixmap")
    public static long glXCreateGLXPixmap(@NativeType("Display *") long j, @NativeType("XVisualInfo *") XVisualInfo xVisualInfo, @NativeType("Pixmap") long j2) {
        return nglXCreateGLXPixmap(j, xVisualInfo.address(), j2);
    }

    public static void glXDestroyGLXPixmap(@NativeType("Display *") long j, @NativeType("GLXPixmap") long j2) {
        long j3 = Functions.DestroyGLXPixmap;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, j3);
    }

    @NativeType("Bool")
    public static boolean glXQueryExtension(@NativeType("Display *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = Functions.QueryExtension;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return JNI.callPPPI(j, iArr, iArr2, j2) != 0;
    }

    @NativeType("Bool")
    public static boolean glXQueryVersion(@NativeType("Display *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2) {
        long j2 = Functions.QueryVersion;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            Checks.check(iArr2, 1);
        }
        return JNI.callPPPI(j, iArr, iArr2, j2) != 0;
    }

    public static int glXGetConfig(@NativeType("Display *") long j, @NativeType("XVisualInfo *") XVisualInfo xVisualInfo, int i, @NativeType("int *") int[] iArr) {
        long j2 = Functions.GetConfig;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
            XVisualInfo.validate(xVisualInfo.address());
        }
        return JNI.callPPPI(j, xVisualInfo.address(), i, iArr, j2);
    }

    @NativeType("XVisualInfo *")
    public static XVisualInfo glXChooseVisual(@NativeType("Display *") long j, int i, @NativeType("int *") int[] iArr) {
        long j2 = Functions.ChooseVisual;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        return XVisualInfo.createSafe(JNI.callPPP(j, i, iArr, j2));
    }
}
