package org.lwjgl.system.jemalloc;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

@FunctionalInterface
@NativeType("extent_alloc_t")
/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentAllocI.class */
public interface ExtentAllocI extends CallbackI {
    public static final FFICIF CIF = APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_uint32);

    @NativeType("void *")
    long invoke(@NativeType("extent_hooks_t *") long j, @NativeType("void *") long j2, @NativeType("size_t") long j3, @NativeType("size_t") long j4, @NativeType("bool *") long j5, @NativeType("bool *") long j6, @NativeType("unsigned int") int i);

    @Override // org.lwjgl.system.CallbackI
    default FFICIF getCallInterface() {
        return CIF;
    }

    @Override // org.lwjgl.system.CallbackI
    default void callback(long j, long j2) {
        APIUtil.apiClosureRetP(j, invoke(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + POINTER_SIZE)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (2 * POINTER_SIZE))), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (3 * POINTER_SIZE))), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (4 * POINTER_SIZE))), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (5 * POINTER_SIZE))), MemoryUtil.memGetInt(MemoryUtil.memGetAddress(j2 + (6 * POINTER_SIZE)))));
    }
}
