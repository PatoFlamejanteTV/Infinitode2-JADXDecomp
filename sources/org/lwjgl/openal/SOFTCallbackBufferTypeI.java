package org.lwjgl.openal;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

@FunctionalInterface
@NativeType("ALBUFFERCALLBACKTYPESOFT")
/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTCallbackBufferTypeI.class */
public interface SOFTCallbackBufferTypeI extends CallbackI {
    public static final FFICIF CIF = APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_sint32);

    @NativeType("void *")
    long invoke(@NativeType("ALvoid *") long j, @NativeType("ALvoid *") long j2, @NativeType("ALsizei") int i);

    @Override // org.lwjgl.system.CallbackI
    default FFICIF getCallInterface() {
        return CIF;
    }

    @Override // org.lwjgl.system.CallbackI
    default void callback(long j, long j2) {
        APIUtil.apiClosureRetP(j, invoke(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + POINTER_SIZE)), MemoryUtil.memGetInt(MemoryUtil.memGetAddress(j2 + (2 * POINTER_SIZE)))));
    }
}
