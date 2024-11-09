package org.lwjgl.stb;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

@FunctionalInterface
@NativeType("stbir__support_callback *")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIRSupportCallbackI.class */
public interface STBIRSupportCallbackI extends CallbackI {
    public static final FFICIF CIF = APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_float, LibFFI.ffi_type_float, LibFFI.ffi_type_pointer);

    float invoke(float f, @NativeType("void *") long j);

    @Override // org.lwjgl.system.CallbackI
    default FFICIF getCallInterface() {
        return CIF;
    }

    @Override // org.lwjgl.system.CallbackI
    default void callback(long j, long j2) {
        APIUtil.apiClosureRet(j, invoke(MemoryUtil.memGetFloat(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + POINTER_SIZE))));
    }
}
