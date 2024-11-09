package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeWin32.class */
public class GLFWNativeWin32 {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeWin32$Functions.class */
    public static final class Functions {
        public static final long GetWin32Adapter = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetWin32Adapter");
        public static final long GetWin32Monitor = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetWin32Monitor");
        public static final long GetWin32Window = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetWin32Window");
        public static final long AttachWin32Window = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwAttachWin32Window");

        private Functions() {
        }
    }

    protected GLFWNativeWin32() {
        throw new UnsupportedOperationException();
    }

    public static long nglfwGetWin32Adapter(long j) {
        long j2 = Functions.GetWin32Adapter;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String glfwGetWin32Adapter(@NativeType("GLFWmonitor *") long j) {
        return MemoryUtil.memUTF8Safe(nglfwGetWin32Adapter(j));
    }

    public static long nglfwGetWin32Monitor(long j) {
        long j2 = Functions.GetWin32Monitor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("char const *")
    public static String glfwGetWin32Monitor(@NativeType("GLFWmonitor *") long j) {
        return MemoryUtil.memUTF8Safe(nglfwGetWin32Monitor(j));
    }

    @NativeType("HWND")
    public static long glfwGetWin32Window(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetWin32Window;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("GLFWwindow *")
    public static long glfwAttachWin32Window(@NativeType("HWND") long j, @NativeType("GLFWwindow *") long j2) {
        long j3 = Functions.AttachWin32Window;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }
}
