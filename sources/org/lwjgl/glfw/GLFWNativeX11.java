package org.lwjgl.glfw;

import java.nio.ByteBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeX11.class */
public class GLFWNativeX11 {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeX11$Functions.class */
    public static final class Functions {
        public static final long GetX11Display = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetX11Display");
        public static final long GetX11Adapter = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetX11Adapter");
        public static final long GetX11Monitor = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetX11Monitor");
        public static final long GetX11Window = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetX11Window");
        public static final long SetX11SelectionString = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwSetX11SelectionString");
        public static final long GetX11SelectionString = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetX11SelectionString");

        private Functions() {
        }
    }

    protected GLFWNativeX11() {
        throw new UnsupportedOperationException();
    }

    @NativeType("Display *")
    public static long glfwGetX11Display() {
        return JNI.invokeP(Functions.GetX11Display);
    }

    @NativeType("RRCrtc")
    public static long glfwGetX11Adapter(@NativeType("GLFWmonitor *") long j) {
        long j2 = Functions.GetX11Adapter;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePN(j, j2);
    }

    @NativeType("RROutput")
    public static long glfwGetX11Monitor(@NativeType("GLFWmonitor *") long j) {
        long j2 = Functions.GetX11Monitor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePN(j, j2);
    }

    @NativeType("Window")
    public static long glfwGetX11Window(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetX11Window;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePN(j, j2);
    }

    public static void nglfwSetX11SelectionString(long j) {
        JNI.invokePV(j, Functions.SetX11SelectionString);
    }

    public static void glfwSetX11SelectionString(@NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        nglfwSetX11SelectionString(MemoryUtil.memAddress(byteBuffer));
    }

    public static void glfwSetX11SelectionString(@NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            nglfwSetX11SelectionString(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetX11SelectionString() {
        return JNI.invokeP(Functions.GetX11SelectionString);
    }

    @NativeType("char const *")
    public static String glfwGetX11SelectionString() {
        return MemoryUtil.memUTF8Safe(nglfwGetX11SelectionString());
    }
}
