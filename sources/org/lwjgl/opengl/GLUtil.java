package org.lwjgl.opengl;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.io.PrintStream;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Callback;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/GLUtil.class */
public final class GLUtil {
    private GLUtil() {
    }

    public static Callback setupDebugMessageCallback() {
        return setupDebugMessageCallback(APIUtil.DEBUG_STREAM);
    }

    public static Callback setupDebugMessageCallback(PrintStream printStream) {
        GLCapabilities capabilities = GL.getCapabilities();
        if (capabilities.OpenGL43) {
            APIUtil.apiLog("[GL] Using OpenGL 4.3 for error logging.");
            GLDebugMessageCallback create = GLDebugMessageCallback.create((i, i2, i3, i4, i5, j, j2) -> {
                StringBuilder sb = new StringBuilder(300);
                sb.append("[LWJGL] OpenGL debug message\n");
                printDetail(sb, "ID", "0x" + Integer.toHexString(i3).toUpperCase());
                printDetail(sb, "Source", getDebugSource(i));
                printDetail(sb, "Type", getDebugType(i2));
                printDetail(sb, "Severity", getDebugSeverity(i4));
                printDetail(sb, "Message", GLDebugMessageCallback.getMessage(i5, j));
                printStream.print(sb);
            });
            GL43C.glDebugMessageCallback(create, 0L);
            if ((GL43C.glGetInteger(33310) & 2) == 0) {
                APIUtil.apiLog("[GL] Warning: A non-debug context may not produce any debug output.");
                GL43C.glEnable(37600);
            }
            return create;
        }
        if (capabilities.GL_KHR_debug) {
            APIUtil.apiLog("[GL] Using KHR_debug for error logging.");
            GLDebugMessageCallback create2 = GLDebugMessageCallback.create((i6, i7, i8, i9, i10, j3, j4) -> {
                StringBuilder sb = new StringBuilder(300);
                sb.append("[LWJGL] OpenGL debug message\n");
                printDetail(sb, "ID", "0x" + Integer.toHexString(i8).toUpperCase());
                printDetail(sb, "Source", getDebugSource(i6));
                printDetail(sb, "Type", getDebugType(i7));
                printDetail(sb, "Severity", getDebugSeverity(i9));
                printDetail(sb, "Message", GLDebugMessageCallback.getMessage(i10, j3));
                printStream.print(sb);
            });
            KHRDebug.glDebugMessageCallback(create2, 0L);
            if (capabilities.OpenGL30 && (GL43C.glGetInteger(33310) & 2) == 0) {
                APIUtil.apiLog("[GL] Warning: A non-debug context may not produce any debug output.");
                GL43C.glEnable(37600);
            }
            return create2;
        }
        if (capabilities.GL_ARB_debug_output) {
            APIUtil.apiLog("[GL] Using ARB_debug_output for error logging.");
            GLDebugMessageARBCallback create3 = GLDebugMessageARBCallback.create((i11, i12, i13, i14, i15, j5, j6) -> {
                StringBuilder sb = new StringBuilder(300);
                sb.append("[LWJGL] ARB_debug_output message\n");
                printDetail(sb, "ID", "0x" + Integer.toHexString(i13).toUpperCase());
                printDetail(sb, "Source", getSourceARB(i11));
                printDetail(sb, "Type", getTypeARB(i12));
                printDetail(sb, "Severity", getSeverityARB(i14));
                printDetail(sb, "Message", GLDebugMessageARBCallback.getMessage(i15, j5));
                printStream.print(sb);
            });
            ARBDebugOutput.glDebugMessageCallbackARB(create3, 0L);
            return create3;
        }
        if (capabilities.GL_AMD_debug_output) {
            APIUtil.apiLog("[GL] Using AMD_debug_output for error logging.");
            GLDebugMessageAMDCallback create4 = GLDebugMessageAMDCallback.create((i16, i17, i18, i19, j7, j8) -> {
                StringBuilder sb = new StringBuilder(300);
                sb.append("[LWJGL] AMD_debug_output message\n");
                printDetail(sb, "ID", "0x" + Integer.toHexString(i16).toUpperCase());
                printDetail(sb, "Category", getCategoryAMD(i17));
                printDetail(sb, "Severity", getSeverityAMD(i18));
                printDetail(sb, "Message", GLDebugMessageAMDCallback.getMessage(i19, j7));
                printStream.print(sb);
            });
            AMDDebugOutput.glDebugMessageCallbackAMD(create4, 0L);
            return create4;
        }
        APIUtil.apiLog("[GL] No debug output implementation is available.");
        return null;
    }

    private static void printDetail(StringBuilder sb, String str, String str2) {
        sb.append("\t").append(str).append(": ").append(str2).append(SequenceUtils.EOL);
    }

    private static String getDebugSource(int i) {
        switch (i) {
            case 33350:
                return "API";
            case 33351:
                return "WINDOW SYSTEM";
            case 33352:
                return "SHADER COMPILER";
            case 33353:
                return "THIRD PARTY";
            case 33354:
                return "APPLICATION";
            case 33355:
                return "OTHER";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }

    private static String getDebugType(int i) {
        switch (i) {
            case 33356:
                return "ERROR";
            case 33357:
                return "DEPRECATED BEHAVIOR";
            case 33358:
                return "UNDEFINED BEHAVIOR";
            case 33359:
                return "PORTABILITY";
            case 33360:
                return "PERFORMANCE";
            case 33361:
                return "OTHER";
            case 33384:
                return "MARKER";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }

    private static String getDebugSeverity(int i) {
        switch (i) {
            case 33387:
                return "NOTIFICATION";
            case 37190:
                return "HIGH";
            case 37191:
                return "MEDIUM";
            case 37192:
                return "LOW";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }

    private static String getSourceARB(int i) {
        switch (i) {
            case 33350:
                return "API";
            case 33351:
                return "WINDOW SYSTEM";
            case 33352:
                return "SHADER COMPILER";
            case 33353:
                return "THIRD PARTY";
            case 33354:
                return "APPLICATION";
            case 33355:
                return "OTHER";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }

    private static String getTypeARB(int i) {
        switch (i) {
            case 33356:
                return "ERROR";
            case 33357:
                return "DEPRECATED BEHAVIOR";
            case 33358:
                return "UNDEFINED BEHAVIOR";
            case 33359:
                return "PORTABILITY";
            case 33360:
                return "PERFORMANCE";
            case 33361:
                return "OTHER";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }

    private static String getSeverityARB(int i) {
        switch (i) {
            case 37190:
                return "HIGH";
            case 37191:
                return "MEDIUM";
            case 37192:
                return "LOW";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }

    private static String getCategoryAMD(int i) {
        switch (i) {
            case AMDDebugOutput.GL_DEBUG_CATEGORY_API_ERROR_AMD /* 37193 */:
                return "API ERROR";
            case AMDDebugOutput.GL_DEBUG_CATEGORY_WINDOW_SYSTEM_AMD /* 37194 */:
                return "WINDOW SYSTEM";
            case AMDDebugOutput.GL_DEBUG_CATEGORY_DEPRECATION_AMD /* 37195 */:
                return "DEPRECATION";
            case AMDDebugOutput.GL_DEBUG_CATEGORY_UNDEFINED_BEHAVIOR_AMD /* 37196 */:
                return "UNDEFINED BEHAVIOR";
            case AMDDebugOutput.GL_DEBUG_CATEGORY_PERFORMANCE_AMD /* 37197 */:
                return "PERFORMANCE";
            case AMDDebugOutput.GL_DEBUG_CATEGORY_SHADER_COMPILER_AMD /* 37198 */:
                return "SHADER COMPILER";
            case AMDDebugOutput.GL_DEBUG_CATEGORY_APPLICATION_AMD /* 37199 */:
                return "APPLICATION";
            case AMDDebugOutput.GL_DEBUG_CATEGORY_OTHER_AMD /* 37200 */:
                return "OTHER";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }

    private static String getSeverityAMD(int i) {
        switch (i) {
            case 37190:
                return "HIGH";
            case 37191:
                return "MEDIUM";
            case 37192:
                return "LOW";
            default:
                return APIUtil.apiUnknownToken(i);
        }
    }
}
