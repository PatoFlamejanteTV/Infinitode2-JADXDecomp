package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeEGL.class */
public class GLFWNativeEGL {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeEGL$Functions.class */
    public static final class Functions {
        public static final long GetEGLDisplay = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetEGLDisplay");
        public static final long GetEGLContext = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetEGLContext");
        public static final long GetEGLSurface = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetEGLSurface");
        public static final long GetEGLConfig = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetEGLConfig");

        private Functions() {
        }
    }

    protected GLFWNativeEGL() {
        throw new UnsupportedOperationException();
    }

    @NativeType("EGLDisplay")
    public static long glfwGetEGLDisplay() {
        return JNI.invokeP(Functions.GetEGLDisplay);
    }

    @NativeType("EGLContext")
    public static long glfwGetEGLContext(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetEGLContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("EGLSurface")
    public static long glfwGetEGLSurface(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetEGLSurface;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("EGLConfig")
    public static long glfwGetEGLConfig(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetEGLConfig;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static void setEGLPath(FunctionProvider functionProvider) {
        if (!(functionProvider instanceof SharedLibrary)) {
            APIUtil.apiLog("GLFW EGL path override not set: Function provider is not a shared library.");
            return;
        }
        String path = ((SharedLibrary) functionProvider).getPath();
        if (path == null) {
            APIUtil.apiLog("GLFW EGL path override not set: Could not resolve the shared library path.");
        } else {
            setEGLPath(path);
        }
    }

    public static void setEGLPath(String str) {
        if (!override("_glfw_egl_library", str)) {
            APIUtil.apiLog("GLFW EGL path override not set: Could not resolve override symbol.");
        }
    }

    public static void setGLESPath(FunctionProvider functionProvider) {
        if (!(functionProvider instanceof SharedLibrary)) {
            APIUtil.apiLog("GLFW OpenGL ES path override not set: Function provider is not a shared library.");
            return;
        }
        String path = ((SharedLibrary) functionProvider).getPath();
        if (path == null) {
            APIUtil.apiLog("GLFW OpenGL ES path override not set: Could not resolve the shared library path.");
        } else {
            setGLESPath(path);
        }
    }

    public static void setGLESPath(String str) {
        if (!override("_glfw_opengles_library", str)) {
            APIUtil.apiLog("GLFW OpenGL ES path override not set: Could not resolve override symbol.");
        }
    }

    private static boolean override(String str, String str2) {
        long functionAddress = GLFW.getLibrary().getFunctionAddress(str);
        if (functionAddress == 0) {
            return false;
        }
        long memGetAddress = MemoryUtil.memGetAddress(functionAddress);
        if (memGetAddress != 0) {
            MemoryUtil.nmemFree(memGetAddress);
        }
        MemoryUtil.memPutAddress(functionAddress, str2 == null ? 0L : MemoryUtil.memAddress(MemoryUtil.memUTF8(str2)));
        return true;
    }
}
