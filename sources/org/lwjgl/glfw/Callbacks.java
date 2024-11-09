package org.lwjgl.glfw;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.Callback;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/Callbacks.class */
public final class Callbacks {
    private Callbacks() {
    }

    public static void glfwFreeCallbacks(@NativeType("GLFWwindow *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        long[] jArr = {GLFW.Functions.SetWindowPosCallback, GLFW.Functions.SetWindowSizeCallback, GLFW.Functions.SetWindowCloseCallback, GLFW.Functions.SetWindowRefreshCallback, GLFW.Functions.SetWindowFocusCallback, GLFW.Functions.SetWindowIconifyCallback, GLFW.Functions.SetWindowMaximizeCallback, GLFW.Functions.SetFramebufferSizeCallback, GLFW.Functions.SetWindowContentScaleCallback, GLFW.Functions.SetKeyCallback, GLFW.Functions.SetCharCallback, GLFW.Functions.SetCharModsCallback, GLFW.Functions.SetMouseButtonCallback, GLFW.Functions.SetCursorPosCallback, GLFW.Functions.SetCursorEnterCallback, GLFW.Functions.SetScrollCallback, GLFW.Functions.SetDropCallback};
        for (int i = 0; i < 17; i++) {
            long invokePPP = JNI.invokePPP(j, 0L, jArr[i]);
            if (invokePPP != 0) {
                Callback.free(invokePPP);
            }
        }
    }
}
