package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLXEXTImportContext.class */
public class GLXEXTImportContext {
    public static final int GLX_SHARE_CONTEXT_EXT = 32778;
    public static final int GLX_VISUAL_ID_EXT = 32779;
    public static final int GLX_SCREEN_EXT = 32780;

    protected GLXEXTImportContext() {
        throw new UnsupportedOperationException();
    }

    @NativeType("Display *")
    public static long glXGetCurrentDisplayEXT() {
        long j = GL.getCapabilitiesGLXClient().glXGetCurrentDisplayEXT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callP(j);
    }

    public static int nglXQueryContextInfoEXT(long j, long j2, int i, long j3) {
        long j4 = GL.getCapabilitiesGLXClient().glXQueryContextInfoEXT;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
            Checks.check(j2);
        }
        return JNI.callPPPI(j, j2, i, j3, j4);
    }

    public static int glXQueryContextInfoEXT(@NativeType("Display *") long j, @NativeType("GLXContext") long j2, int i, @NativeType("int *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        return nglXQueryContextInfoEXT(j, j2, i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("GLXContextID")
    public static long glXGetContextIDEXT(@NativeType("GLXContext const") long j) {
        long j2 = GL.getCapabilitiesGLXClient().glXGetContextIDEXT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPN(j, j2);
    }

    @NativeType("GLXContext")
    public static long glXImportContextEXT(@NativeType("Display *") long j, @NativeType("GLXContextID") long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXImportContextEXT;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.callPNP(j, j2, j3);
    }

    public static void glXFreeContextEXT(@NativeType("Display *") long j, @NativeType("GLXContext") long j2) {
        long j3 = GL.getCapabilitiesGLXClient().glXFreeContextEXT;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
        }
        JNI.callPPV(j, j2, j3);
    }

    public static int glXQueryContextInfoEXT(@NativeType("Display *") long j, @NativeType("GLXContext") long j2, int i, @NativeType("int *") int[] iArr) {
        long j3 = GL.getCapabilitiesGLXClient().glXQueryContextInfoEXT;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
            Checks.check(j2);
            Checks.check(iArr, 1);
        }
        return JNI.callPPPI(j, j2, i, iArr, j3);
    }
}
