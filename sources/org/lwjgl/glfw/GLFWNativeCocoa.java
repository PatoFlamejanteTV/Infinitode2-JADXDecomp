package org.lwjgl.glfw;

import com.vladsch.flexmark.util.html.Attribute;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeCocoa.class */
public class GLFWNativeCocoa {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeCocoa$Functions.class */
    public static final class Functions {
        public static final long GetCocoaMonitor = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetCocoaMonitor");
        public static final long GetCocoaWindow = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetCocoaWindow");
        public static final long GetCocoaView = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetCocoaView");

        private Functions() {
        }
    }

    protected GLFWNativeCocoa() {
        throw new UnsupportedOperationException();
    }

    @NativeType("CGDirectDisplayID")
    public static int glfwGetCocoaMonitor(@NativeType("GLFWmonitor *") long j) {
        long j2 = Functions.GetCocoaMonitor;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePI(j, j2);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long glfwGetCocoaWindow(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetCocoaWindow;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType(Attribute.ID_ATTR)
    public static long glfwGetCocoaView(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetCocoaView;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }
}
