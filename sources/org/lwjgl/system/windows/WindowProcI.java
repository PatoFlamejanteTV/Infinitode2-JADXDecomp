package org.lwjgl.system.windows;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.CallbackI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

@FunctionalInterface
@NativeType("WNDPROC")
/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WindowProcI.class */
public interface WindowProcI extends CallbackI {
    public static final FFICIF CIF = APIUtil.apiCreateCIF(APIUtil.apiStdcall(), LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_uint32, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer);

    @NativeType("LRESULT")
    long invoke(@NativeType("HWND") long j, @NativeType("UINT") int i, @NativeType("WPARAM") long j2, @NativeType("LPARAM") long j3);

    @Override // org.lwjgl.system.CallbackI
    default FFICIF getCallInterface() {
        return CIF;
    }

    @Override // org.lwjgl.system.CallbackI
    default void callback(long j, long j2) {
        APIUtil.apiClosureRetP(j, invoke(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetInt(MemoryUtil.memGetAddress(j2 + POINTER_SIZE)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (2 * POINTER_SIZE))), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + (3 * POINTER_SIZE)))));
    }
}
