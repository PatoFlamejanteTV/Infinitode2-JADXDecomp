package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeGLX.class */
public class GLFWNativeGLX {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeGLX$Functions.class */
    public static final class Functions {
        public static final long GetGLXContext = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetGLXContext");
        public static final long GetGLXWindow = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetGLXWindow");
        public static final long GetGLXFBConfig = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetGLXFBConfig");

        private Functions() {
        }
    }

    protected GLFWNativeGLX() {
        throw new UnsupportedOperationException();
    }

    @NativeType("GLXContext")
    public static long glfwGetGLXContext(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetGLXContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("GLXWindow")
    public static long glfwGetGLXWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetGLXWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("GLXWindow")
    public static long glfwGetGLXFBConfig(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetGLXFBConfig;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static void setPath(FunctionProvider functionProvider) {
        if (!(functionProvider instanceof SharedLibrary)) {
            APIUtil.apiLog("GLFW OpenGL path override not set: Function provider is not a shared library.");
            return;
        }
        String path = ((SharedLibrary) functionProvider).getPath();
        if (path == null) {
            APIUtil.apiLog("GLFW OpenGL path override not set: Could not resolve the shared library path.");
        } else {
            setPath(path);
        }
    }

    public static void setPath(String str) {
        long functionAddress = GLFW.getLibrary().getFunctionAddress("_glfw_opengl_library");
        if (functionAddress == 0) {
            APIUtil.apiLog("GLFW OpenGL path override not set: Could not resolve override symbol.");
            return;
        }
        long memGetAddress = MemoryUtil.memGetAddress(functionAddress);
        if (memGetAddress != 0) {
            MemoryUtil.nmemFree(memGetAddress);
        }
        MemoryUtil.memPutAddress(functionAddress, str == null ? 0L : MemoryUtil.memAddress(MemoryUtil.memUTF8(str)));
    }
}
