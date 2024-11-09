package org.lwjgl.glfw;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.FunctionProvider;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Platform;
import org.lwjgl.system.Pointer;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.vulkan.VK;
import org.lwjgl.vulkan.VkAllocationCallbacks;
import org.lwjgl.vulkan.VkInstance;
import org.lwjgl.vulkan.VkPhysicalDevice;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWVulkan.class */
public class GLFWVulkan {

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWVulkan$Functions.class */
    public static final class Functions {
        public static final long InitVulkanLoader = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwInitVulkanLoader");
        public static final long VulkanSupported = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwVulkanSupported");
        public static final long GetRequiredInstanceExtensions = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetRequiredInstanceExtensions");
        public static final long GetInstanceProcAddress = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetInstanceProcAddress");
        public static final long GetPhysicalDevicePresentationSupport = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwGetPhysicalDevicePresentationSupport");
        public static final long CreateWindowSurface = APIUtil.apiGetFunctionAddress(GLFW.getLibrary(), "glfwCreateWindowSurface");

        private Functions() {
        }
    }

    static {
        if (Platform.get() == Platform.MACOSX) {
            setPath(VK.getFunctionProvider());
        }
    }

    protected GLFWVulkan() {
        throw new UnsupportedOperationException();
    }

    public static void glfwInitVulkanLoader(@NativeType("PFN_vkGetInstanceProcAddr") long j) {
        JNI.invokePV(j, Functions.InitVulkanLoader);
    }

    @NativeType("int")
    public static boolean glfwVulkanSupported() {
        return JNI.invokeI(Functions.VulkanSupported) != 0;
    }

    public static long nglfwGetRequiredInstanceExtensions(long j) {
        return JNI.invokePP(j, Functions.GetRequiredInstanceExtensions);
    }

    @NativeType("char const **")
    public static PointerBuffer glfwGetRequiredInstanceExtensions() {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        IntBuffer callocInt = stackGet.callocInt(1);
        try {
            return MemoryUtil.memPointerBufferSafe(nglfwGetRequiredInstanceExtensions(MemoryUtil.memAddress(callocInt)), callocInt.get(0));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nglfwGetInstanceProcAddress(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.GetInstanceProcAddress);
    }

    @NativeType("GLFWvkproc")
    public static long glfwGetInstanceProcAddress(VkInstance vkInstance, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nglfwGetInstanceProcAddress(MemoryUtil.memAddressSafe((Pointer) vkInstance), MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("GLFWvkproc")
    public static long glfwGetInstanceProcAddress(VkInstance vkInstance, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nglfwGetInstanceProcAddress(MemoryUtil.memAddressSafe((Pointer) vkInstance), stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("int")
    public static boolean glfwGetPhysicalDevicePresentationSupport(VkInstance vkInstance, VkPhysicalDevice vkPhysicalDevice, @NativeType("uint32_t") int i) {
        return JNI.invokePPI(vkInstance.address(), vkPhysicalDevice.address(), i, Functions.GetPhysicalDevicePresentationSupport) != 0;
    }

    public static int nglfwCreateWindowSurface(long j, long j2, long j3, long j4) {
        long j5 = Functions.CreateWindowSurface;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePPPPI(j, j2, j3, j4, j5);
    }

    @NativeType("VkResult")
    public static int glfwCreateWindowSurface(VkInstance vkInstance, @NativeType("GLFWwindow *") long j, @NativeType("VkAllocationCallbacks const *") VkAllocationCallbacks vkAllocationCallbacks, @NativeType("VkSurfaceKHR *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        return nglfwCreateWindowSurface(vkInstance.address(), j, MemoryUtil.memAddressSafe((Pointer) vkAllocationCallbacks), MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("VkResult")
    public static int glfwCreateWindowSurface(VkInstance vkInstance, @NativeType("GLFWwindow *") long j, @NativeType("VkAllocationCallbacks const *") VkAllocationCallbacks vkAllocationCallbacks, @NativeType("VkSurfaceKHR *") long[] jArr) {
        long j2 = Functions.CreateWindowSurface;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        return JNI.invokePPPPI(vkInstance.address(), j, MemoryUtil.memAddressSafe((Pointer) vkAllocationCallbacks), jArr, j2);
    }

    public static void setPath(FunctionProvider functionProvider) {
        if (!(functionProvider instanceof SharedLibrary)) {
            APIUtil.apiLog("GLFW Vulkan path override not set: function provider is not a shared library.");
            return;
        }
        String path = ((SharedLibrary) functionProvider).getPath();
        if (path == null) {
            APIUtil.apiLog("GLFW Vulkan path override not set: Could not resolve the shared library path.");
        } else {
            setPath(path);
        }
    }

    public static void setPath(String str) {
        long functionAddress = GLFW.getLibrary().getFunctionAddress("_glfw_vulkan_library");
        if (functionAddress == 0) {
            APIUtil.apiLog("GLFW Vulkan path override not set: Could not resolve override symbol.");
            return;
        }
        long memGetAddress = MemoryUtil.memGetAddress(functionAddress);
        if (memGetAddress != 0) {
            MemoryUtil.nmemFree(memGetAddress);
        }
        MemoryUtil.memPutAddress(functionAddress, str == null ? 0L : MemoryUtil.memAddress(MemoryUtil.memUTF8(str)));
    }
}
