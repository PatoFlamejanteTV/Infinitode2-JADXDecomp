package org.lwjgl.system;

import java.util.function.Function;

/* loaded from: infinitode-2.jar:org/lwjgl/system/Configuration.class */
public class Configuration<T> {
    public static final Configuration<String> LIBRARY_PATH = new Configuration<>("org.lwjgl.librarypath", StateInit.STRING);
    public static final Configuration<Object> BUNDLED_LIBRARY_NAME_MAPPER = new Configuration<>("org.lwjgl.system.bundledLibrary.nameMapper", StateInit.STRING);
    public static final Configuration<Object> BUNDLED_LIBRARY_PATH_MAPPER = new Configuration<>("org.lwjgl.system.bundledLibrary.pathMapper", StateInit.STRING);
    public static final Configuration<String> SHARED_LIBRARY_EXTRACT_DIRECTORY = new Configuration<>("org.lwjgl.system.SharedLibraryExtractDirectory", StateInit.STRING);
    public static final Configuration<String> SHARED_LIBRARY_EXTRACT_PATH = new Configuration<>("org.lwjgl.system.SharedLibraryExtractPath", StateInit.STRING);
    public static final Configuration<Boolean> SHARED_LIBRARY_EXTRACT_FORCE = new Configuration<>("org.lwjgl.system.SharedLibraryExtractForce", StateInit.BOOLEAN);
    public static final Configuration<Boolean> EMULATE_SYSTEM_LOADLIBRARY = new Configuration<>("org.lwjgl.system.EmulateSystemLoadLibrary", StateInit.BOOLEAN);
    public static final Configuration<String> LIBRARY_NAME = new Configuration<>("org.lwjgl.libname", StateInit.STRING);
    public static final Configuration<Object> MEMORY_ALLOCATOR = new Configuration<>("org.lwjgl.system.allocator", StateInit.STRING);
    public static final Configuration<Integer> STACK_SIZE = new Configuration<>("org.lwjgl.system.stackSize", StateInit.INT);
    public static final Configuration<Integer> ARRAY_TLC_SIZE = new Configuration<>("org.lwjgl.system.arrayTLCSize", StateInit.INT);
    public static final Configuration<Integer> JNI_NATIVE_INTERFACE_FUNCTION_COUNT = new Configuration<>("org.lwjgl.system.JNINativeInterfaceSize", StateInit.INT);
    public static final Configuration<Boolean> DISABLE_CHECKS = new Configuration<>("org.lwjgl.util.NoChecks", StateInit.BOOLEAN);
    public static final Configuration<Boolean> DISABLE_FUNCTION_CHECKS = new Configuration<>("org.lwjgl.util.NoFunctionChecks", StateInit.BOOLEAN);
    public static final Configuration<Boolean> DEBUG = new Configuration<>("org.lwjgl.util.Debug", StateInit.BOOLEAN);
    public static final Configuration<Boolean> DEBUG_LOADER = new Configuration<>("org.lwjgl.util.DebugLoader", StateInit.BOOLEAN);
    public static final Configuration<Object> DEBUG_STREAM = new Configuration<>("org.lwjgl.util.DebugStream", StateInit.STRING);
    public static final Configuration<Boolean> DEBUG_MEMORY_ALLOCATOR = new Configuration<>("org.lwjgl.util.DebugAllocator", StateInit.BOOLEAN);
    public static final Configuration<Boolean> DEBUG_MEMORY_ALLOCATOR_INTERNAL = new Configuration<>("org.lwjgl.util.DebugAllocator.internal", StateInit.BOOLEAN);
    public static final Configuration<Boolean> DEBUG_MEMORY_ALLOCATOR_FAST = new Configuration<>("org.lwjgl.util.DebugAllocator.fast", StateInit.BOOLEAN);
    public static final Configuration<Boolean> DEBUG_STACK = new Configuration<>("org.lwjgl.util.DebugStack", StateInit.BOOLEAN);
    public static final Configuration<Boolean> DEBUG_FUNCTIONS = new Configuration<>("org.lwjgl.util.DebugFunctions", StateInit.BOOLEAN);
    public static final Configuration<String> ASSIMP_LIBRARY_NAME = new Configuration<>("org.lwjgl.assimp.libname", StateInit.STRING);
    public static final Configuration<String> ASSIMP_DRACO_LIBRARY_NAME = new Configuration<>("org.lwjgl.assimp.draco.libname", StateInit.STRING);
    public static final Configuration<String> BGFX_LIBRARY_NAME = new Configuration<>("org.lwjgl.bgfx.libname", StateInit.STRING);
    public static final Configuration<String> CUDA_LIBRARY_NAME = new Configuration<>("org.lwjgl.cuda.libname", StateInit.STRING);
    public static final Configuration<String> CUDA_TOOLKIT_VERSION = new Configuration<>("org.lwjgl.cuda.toolkit.version", StateInit.STRING);
    public static final Configuration<String> CUDA_TOOLKIT_PATH = new Configuration<>("org.lwjgl.cuda.toolkit.path", StateInit.STRING);
    public static final Configuration<String> CUDA_NVRTC_LIBRARY_NAME = new Configuration<>("org.lwjgl.cuda.nvrtc.libname", StateInit.STRING);
    public static final Configuration<String> CUDA_NVRTC_BUILTINS_LIBRARY_NAME = new Configuration<>("org.lwjgl.cuda.nvrtc-builtins.libname", StateInit.STRING);
    public static final Configuration<Boolean> CUDA_API_PER_THREAD_DEFAULT_STREAM = new Configuration<>("org.lwjgl.cuda.ptds", StateInit.BOOLEAN);
    public static final Configuration<Boolean> EGL_EXPLICIT_INIT = new Configuration<>("org.lwjgl.egl.explicitInit", StateInit.BOOLEAN);
    public static final Configuration<String> EGL_LIBRARY_NAME = new Configuration<>("org.lwjgl.egl.libname", StateInit.STRING);
    public static final Configuration<Object> EGL_EXTENSION_FILTER = new Configuration<>("org.lwjgl.egl.extensionFilter", StateInit.STRING);
    public static final Configuration<String> FMOD_LIBRARY_NAME = new Configuration<>("org.lwjgl.fmod.libname", StateInit.STRING);
    public static final Configuration<String> FMOD_STUDIO_LIBRARY_NAME = new Configuration<>("org.lwjgl.fmod.studio.libname", StateInit.STRING);
    public static final Configuration<String> FMOD_FSBANK_LIBRARY_NAME = new Configuration<>("org.lwjgl.fmod.fsbank.libname", StateInit.STRING);
    public static final Configuration<String> FREETYPE_LIBRARY_NAME = new Configuration<>("org.lwjgl.freetype.libname", StateInit.STRING);
    public static final Configuration<String> GLFW_LIBRARY_NAME = new Configuration<>("org.lwjgl.glfw.libname", StateInit.STRING);
    public static final Configuration<Boolean> GLFW_CHECK_THREAD0 = new Configuration<>("org.lwjgl.glfw.checkThread0", StateInit.BOOLEAN);
    public static final Configuration<Object> HARFBUZZ_LIBRARY_NAME = new Configuration<>("org.lwjgl.harfbuzz.libname", StateInit.STRING);
    public static final Configuration<String> HWLOC_LIBRARY_NAME = new Configuration<>("org.lwjgl.hwloc.libname", StateInit.STRING);
    public static final Configuration<String> JAWT_LIBRARY_NAME = new Configuration<>("org.lwjgl.system.jawt.libname", StateInit.STRING);
    public static final Configuration<String> JEMALLOC_LIBRARY_NAME = new Configuration<>("org.lwjgl.system.jemalloc.libname", StateInit.STRING);
    public static final Configuration<String> KTX_LIBRARY_NAME = new Configuration<>("org.lwjgl.ktx.libname", StateInit.STRING);
    public static final Configuration<String> LLVM_LIBRARY_NAME = new Configuration<>("org.lwjgl.llvm.libname", StateInit.STRING);
    public static final Configuration<String> LLVM_CLANG_LIBRARY_NAME = new Configuration<>("org.lwjgl.llvm.clang.libname", StateInit.STRING);
    public static final Configuration<String> LLVM_LTO_LIBRARY_NAME = new Configuration<>("org.lwjgl.llvm.lto.libname", StateInit.STRING);
    public static final Configuration<Boolean> NFD_LINUX_PORTAL = new Configuration<>("org.lwjgl.nfd.linux.portal", StateInit.BOOLEAN);
    public static final Configuration<String> ODBC_LIBRARY_NAME = new Configuration<>("org.lwjgl.odbc.libname", StateInit.STRING);
    public static final Configuration<Boolean> OPENAL_EXPLICIT_INIT = new Configuration<>("org.lwjgl.openal.explicitInit", StateInit.BOOLEAN);
    public static final Configuration<String> OPENAL_LIBRARY_NAME = new Configuration<>("org.lwjgl.openal.libname", StateInit.STRING);
    public static final Configuration<Object> OPENAL_EXTENSION_FILTER = new Configuration<>("org.lwjgl.openal.extensionFilter", StateInit.STRING);
    public static final Configuration<Boolean> OPENCL_EXPLICIT_INIT = new Configuration<>("org.lwjgl.opencl.explicitInit", StateInit.BOOLEAN);
    public static final Configuration<String> OPENCL_LIBRARY_NAME = new Configuration<>("org.lwjgl.opencl.libname", StateInit.STRING);
    public static final Configuration<Object> OPENCL_EXTENSION_FILTER = new Configuration<>("org.lwjgl.opencl.extensionFilter", StateInit.STRING);
    public static final Configuration<Boolean> OPENGL_EXPLICIT_INIT = new Configuration<>("org.lwjgl.opengl.explicitInit", StateInit.BOOLEAN);
    public static final Configuration<String> OPENGL_LIBRARY_NAME = new Configuration<>("org.lwjgl.opengl.libname", StateInit.STRING);
    public static final Configuration<Object> OPENGL_MAXVERSION = new Configuration<>("org.lwjgl.opengl.maxVersion", StateInit.STRING);
    public static final Configuration<Object> OPENGL_EXTENSION_FILTER = new Configuration<>("org.lwjgl.opengl.extensionFilter", StateInit.STRING);
    public static final Configuration<Boolean> OPENGLES_EXPLICIT_INIT = new Configuration<>("org.lwjgl.opengles.explicitInit", StateInit.BOOLEAN);
    public static final Configuration<String> OPENGLES_LIBRARY_NAME = new Configuration<>("org.lwjgl.opengles.libname", StateInit.STRING);
    public static final Configuration<Object> OPENGLES_MAXVERSION = new Configuration<>("org.lwjgl.opengles.maxVersion", StateInit.STRING);
    public static final Configuration<Object> OPENGLES_EXTENSION_FILTER = new Configuration<>("org.lwjgl.opengles.extensionFilter", StateInit.STRING);
    public static final Configuration<String> OPENGLES_CONTEXT_API = new Configuration<>("org.lwjgl.opengles.contextAPI", StateInit.STRING);
    public static final Configuration<String> OPENVR_LIBRARY_NAME = new Configuration<>("org.lwjgl.openvr.libname", StateInit.STRING);
    public static final Configuration<Boolean> OPENXR_EXPLICIT_INIT = new Configuration<>("org.lwjgl.openxr.explicitInit", StateInit.BOOLEAN);
    public static final Configuration<String> OPENXR_LIBRARY_NAME = new Configuration<>("org.lwjgl.openxr.libname", StateInit.STRING);
    public static final Configuration<String> OPUS_LIBRARY_NAME = new Configuration<>("org.lwjgl.opus.libname", StateInit.STRING);
    public static final Configuration<String> SHADERC_LIBRARY_NAME = new Configuration<>("org.lwjgl.shaderc.libname", StateInit.STRING);
    public static final Configuration<String> SPVC_LIBRARY_NAME = new Configuration<>("org.lwjgl.spvc.libname", StateInit.STRING);
    public static final Configuration<Boolean> VULKAN_EXPLICIT_INIT = new Configuration<>("org.lwjgl.vulkan.explicitInit", StateInit.BOOLEAN);
    public static final Configuration<String> VULKAN_LIBRARY_NAME = new Configuration<>("org.lwjgl.vulkan.libname", StateInit.STRING);
    private final String property;
    private volatile T state;

    /* loaded from: infinitode-2.jar:org/lwjgl/system/Configuration$StateInit.class */
    private interface StateInit<T> extends Function<String, T> {
        public static final StateInit<Boolean> BOOLEAN = str -> {
            String property = System.getProperty(str);
            if (property == null) {
                return null;
            }
            return Boolean.valueOf(Boolean.parseBoolean(property));
        };
        public static final StateInit<Integer> INT = Integer::getInteger;
        public static final StateInit<String> STRING = System::getProperty;
    }

    Configuration(String str, StateInit<? extends T> stateInit) {
        this.property = str;
        this.state = stateInit.apply(str);
    }

    public String getProperty() {
        return this.property;
    }

    public void set(T t) {
        this.state = t;
    }

    public T get() {
        return this.state;
    }

    public T get(T t) {
        T t2 = this.state;
        T t3 = t2;
        if (t2 == null) {
            t3 = t;
        }
        return t3;
    }
}
