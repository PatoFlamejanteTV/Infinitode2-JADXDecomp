package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

@FunctionalInterface
@NativeType("GLFWscrollfun")
/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWScrollCallbackI.class */
public interface GLFWScrollCallbackI extends CallbackI {
    public static final FFICIF CIF = APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_void, LibFFI.ffi_type_pointer, LibFFI.ffi_type_double, LibFFI.ffi_type_double);

    void invoke(@NativeType("GLFWwindow *") long j, double d, double d2);

    @Override // org.lwjgl.system.CallbackI
    default FFICIF getCallInterface() {
        return CIF;
    }

    @Override // org.lwjgl.system.CallbackI
    default void callback(long j, long j2) {
        invoke(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetDouble(MemoryUtil.memGetAddress(j2 + POINTER_SIZE)), MemoryUtil.memGetDouble(MemoryUtil.memGetAddress(j2 + (2 * POINTER_SIZE))));
    }
}
