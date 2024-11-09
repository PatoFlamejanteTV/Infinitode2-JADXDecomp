package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeWayland.class */
public class GLFWNativeWayland {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeWayland$Functions.class */
    public static final class Functions {
        public static final long GetWaylandDisplay = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetWaylandDisplay");
        public static final long GetWaylandMonitor = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetWaylandMonitor");
        public static final long GetWaylandWindow = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetWaylandWindow");

        private Functions() {
        }
    }

    protected GLFWNativeWayland() {
        throw new UnsupportedOperationException();
    }

    @NativeType("struct wl_display *")
    public static long glfwGetWaylandDisplay() {
        return JNI.invokeP(Functions.GetWaylandDisplay);
    }

    @NativeType("struct wl_output *")
    public static long glfwGetWaylandMonitor(@NativeType("GLFWmonitor *") long j) {
        long j2 = Functions.GetWaylandMonitor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("struct wl_surface *")
    public static long glfwGetWaylandWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetWaylandWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }
}
