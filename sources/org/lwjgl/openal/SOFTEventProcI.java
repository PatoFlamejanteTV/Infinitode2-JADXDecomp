package org.lwjgl.openal;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

@FunctionalInterface
@NativeType("ALEVENTPROCSOFT")
/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTEventProcI.class */
public interface SOFTEventProcI extends CallbackI {
    public static final FFICIF CIF = APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_void, LibFFI.ffi_type_sint32, LibFFI.ffi_type_uint32, LibFFI.ffi_type_uint32, LibFFI.ffi_type_sint32, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer);

    void invoke(@NativeType("ALenum") int i, @NativeType("ALuint") int i2, @NativeType("ALuint") int i3, @NativeType("ALsizei") int i4, @NativeType("ALchar const *") long j, @NativeType("ALvoid *") long j2);

    @Override // org.lwjgl.system.CallbackI
    default FFICIF getCallInterface() {
        return CIF;
    }

    @Override // org.lwjgl.system.CallbackI
    default void callback(long j, long j2) {
        invoke(MemoryUtil.memGetInt(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetInt(MemoryUtil.memGetAddress(j2 + POINTER_SIZE)), MemoryUtil.memGetInt(MemoryUtil.memGetAddress(j2 + (2 * POINTER_SIZE))), MemoryUtil.memGetInt(MemoryUtil.memGetAddress(j2 + (3 * POINTER_SIZE))), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (4 * POINTER_SIZE))), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (5 * POINTER_SIZE))));
    }
}
