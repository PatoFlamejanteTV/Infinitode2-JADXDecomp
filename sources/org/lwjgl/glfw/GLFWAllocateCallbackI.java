package org.lwjgl.glfw;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

@FunctionalInterface
@NativeType("GLFWallocatefun")
/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWAllocateCallbackI.class */
public interface GLFWAllocateCallbackI extends CallbackI {
    public static final FFICIF CIF = APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer);

    @NativeType("void *")
    long invoke(@NativeType("size_t") long j, @NativeType("void *") long j2);

    @Override // org.lwjgl.system.CallbackI
    default FFICIF getCallInterface() {
        return CIF;
    }

    @Override // org.lwjgl.system.CallbackI
    default void callback(long j, long j2) {
        APIUtil.apiClosureRetP(j, invoke(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + POINTER_SIZE))));
    }
}
