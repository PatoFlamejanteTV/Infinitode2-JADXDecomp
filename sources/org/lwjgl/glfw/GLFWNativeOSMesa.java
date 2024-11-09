package org.lwjgl.glfw;

import java.nio.Buffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeOSMesa.class */
public class GLFWNativeOSMesa {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWNativeOSMesa$Functions.class */
    public static final class Functions {
        public static final long GetOSMesaColorBuffer = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetOSMesaColorBuffer");
        public static final long GetOSMesaDepthBuffer = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetOSMesaDepthBuffer");
        public static final long GetOSMesaContext = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetOSMesaContext");

        private Functions() {
        }
    }

    protected GLFWNativeOSMesa() {
        throw new UnsupportedOperationException();
    }

    public static int nglfwGetOSMesaColorBuffer(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.GetOSMesaColorBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPPPPI(j, j2, j3, j4, j5, j6);
    }

    @NativeType("int")
    public static boolean glfwGetOSMesaColorBuffer(@NativeType("GLFWwindow *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nglfwGetOSMesaColorBuffer(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(pointerBuffer)) != 0;
    }

    public static int nglfwGetOSMesaDepthBuffer(long j, long j2, long j3, long j4, long j5) {
        long j6 = Functions.GetOSMesaDepthBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPPPPI(j, j2, j3, j4, j5, j6);
    }

    public static int glfwGetOSMesaDepthBuffer(@NativeType("GLFWwindow *") long j, @NativeType("int *") IntBuffer intBuffer, @NativeType("int *") IntBuffer intBuffer2, @NativeType("int *") IntBuffer intBuffer3, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) intBuffer, 1);
            Checks.checkSafe((Buffer) intBuffer2, 1);
            Checks.checkSafe((Buffer) intBuffer3, 1);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nglfwGetOSMesaDepthBuffer(j, MemoryUtil.memAddressSafe(intBuffer), MemoryUtil.memAddressSafe(intBuffer2), MemoryUtil.memAddressSafe(intBuffer3), MemoryUtil.memAddressSafe(pointerBuffer));
    }

    @NativeType("OSMesaContext")
    public static long glfwGetOSMesaContext(@NativeType("GLFWwindow *") long j) {
        long j2 = Functions.GetOSMesaContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("int")
    public static boolean glfwGetOSMesaColorBuffer(@NativeType("GLFWwindow *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("void **") PointerBuffer pointerBuffer) {
        long j2 = Functions.GetOSMesaColorBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return JNI.invokePPPPPI(j, iArr, iArr2, iArr3, MemoryUtil.memAddressSafe(pointerBuffer), j2) != 0;
    }

    public static int glfwGetOSMesaDepthBuffer(@NativeType("GLFWwindow *") long j, @NativeType("int *") int[] iArr, @NativeType("int *") int[] iArr2, @NativeType("int *") int[] iArr3, @NativeType("void **") PointerBuffer pointerBuffer) {
        long j2 = Functions.GetOSMesaDepthBuffer;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkSafe(iArr, 1);
            Checks.checkSafe(iArr2, 1);
            Checks.checkSafe(iArr3, 1);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return JNI.invokePPPPPI(j, iArr, iArr2, iArr3, MemoryUtil.memAddressSafe(pointerBuffer), j2);
    }

    public static void setPath(FunctionProvider functionProvider) {
        if (!(functionProvider instanceof SharedLibrary)) {
            APIUtil.apiLog("GLFW OSMesa path override not set: Function provider is not a shared library.");
            return;
        }
        String path = ((SharedLibrary) functionProvider).getPath();
        if (path == null) {
            APIUtil.apiLog("GLFW OSMesa path override not set: Could not resolve the OSMesa shared library path.");
        } else {
            setPath(path);
        }
    }

    public static void setPath(String str) {
        long functionAddress = GLFW.getLibrary().getFunctionAddress("_glfw_mesa_library");
        if (functionAddress == 0) {
            APIUtil.apiLog("GLFW OSMesa path override not set: Could not resolve override symbol.");
            return;
        }
        long memGetAddress = MemoryUtil.memGetAddress(functionAddress);
        if (memGetAddress != 0) {
            MemoryUtil.nmemFree(memGetAddress);
        }
        MemoryUtil.memPutAddress(functionAddress, str == null ? 0L : MemoryUtil.memAddress(MemoryUtil.memUTF8(str)));
    }
}
