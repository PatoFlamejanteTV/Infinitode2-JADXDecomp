package org.lwjgl.glfw;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.PrintStream;
import java.util.Map;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Callback;
import org.lwjgl.system.MemoryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWErrorCallback.class */
public abstract class GLFWErrorCallback extends Callback implements GLFWErrorCallbackI {
    public static GLFWErrorCallback create(long j) {
        GLFWErrorCallbackI gLFWErrorCallbackI = (GLFWErrorCallbackI) Callback.get(j);
        return gLFWErrorCallbackI instanceof GLFWErrorCallback ? (GLFWErrorCallback) gLFWErrorCallbackI : new Container(j, gLFWErrorCallbackI);
    }

    public static GLFWErrorCallback createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return create(j);
    }

    public static GLFWErrorCallback create(GLFWErrorCallbackI gLFWErrorCallbackI) {
        return gLFWErrorCallbackI instanceof GLFWErrorCallback ? (GLFWErrorCallback) gLFWErrorCallbackI : new Container(gLFWErrorCallbackI.address(), gLFWErrorCallbackI);
    }

    protected GLFWErrorCallback() {
        super(CIF);
    }

    GLFWErrorCallback(long j) {
        super(j);
    }

    public static String getDescription(long j) {
        return MemoryUtil.memUTF8(j);
    }

    public static GLFWErrorCallback createPrint() {
        return createPrint(APIUtil.DEBUG_STREAM);
    }

    public static GLFWErrorCallback createPrint(final PrintStream printStream) {
        return new GLFWErrorCallback() { // from class: org.lwjgl.glfw.GLFWErrorCallback.1
            private Map<Integer, String> ERROR_CODES = APIUtil.apiClassTokens((field, num) -> {
                return 65536 < num.intValue() && num.intValue() < 131072;
            }, null, GLFW.class);

            @Override // org.lwjgl.glfw.GLFWErrorCallbackI
            public final void invoke(int i, long j) {
                String description = getDescription(j);
                StringBuilder sb = new StringBuilder(512);
                sb.append("[LWJGL] ").append(this.ERROR_CODES.get(Integer.valueOf(i))).append(" error\n\tDescription : ").append(description).append("\n\tStacktrace  :\n");
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                for (int i2 = 4; i2 < stackTrace.length; i2++) {
                    sb.append("\t\t");
                    sb.append(stackTrace[i2]);
                    sb.append(SequenceUtils.EOL);
                }
                printStream.print(sb);
            }
        };
    }

    public static GLFWErrorCallback createThrow() {
        return new GLFWErrorCallback() { // from class: org.lwjgl.glfw.GLFWErrorCallback.2
            @Override // org.lwjgl.glfw.GLFWErrorCallbackI
            public final void invoke(int i, long j) {
                throw new IllegalStateException(String.format("GLFW error [0x%X]: %s", Integer.valueOf(i), getDescription(j)));
            }
        };
    }

    public GLFWErrorCallback set() {
        GLFW.glfwSetErrorCallback(this);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWErrorCallback$Container.class */
    public static final class Container extends GLFWErrorCallback {
        private final GLFWErrorCallbackI delegate;

        Container(long j, GLFWErrorCallbackI gLFWErrorCallbackI) {
            super(j);
            this.delegate = gLFWErrorCallbackI;
        }

        @Override // org.lwjgl.glfw.GLFWErrorCallbackI
        public final void invoke(int i, long j) {
            this.delegate.invoke(i, j);
        }
    }
}
