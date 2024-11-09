package org.lwjgl.glfw;

import com.vladsch.flexmark.util.html.Attribute;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeNSGL.class */
public class GLFWNativeNSGL {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeNSGL$Functions.class */
    public static final class Functions {
        public static final long GetNSGLContext = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetNSGLContext");

        private Functions() {
        }
    }

    protected GLFWNativeNSGL() {
        throw new UnsupportedOperationException();
    }

    @NativeType(Attribute.ID_ATTR)
    public static long glfwGetNSGLContext(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetNSGLContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }
}
