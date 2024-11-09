package org.lwjgl.system.libffi;

import java.nio.ByteBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/libffi/LibFFI.class */
public class LibFFI {
    public static final short FFI_TYPE_VOID = 0;
    public static final short FFI_TYPE_INT = 1;
    public static final short FFI_TYPE_FLOAT = 2;
    public static final short FFI_TYPE_DOUBLE = 3;
    public static final short FFI_TYPE_LONGDOUBLE;
    public static final short FFI_TYPE_UINT8 = 5;
    public static final short FFI_TYPE_SINT8 = 6;
    public static final short FFI_TYPE_UINT16 = 7;
    public static final short FFI_TYPE_SINT16 = 8;
    public static final short FFI_TYPE_UINT32 = 9;
    public static final short FFI_TYPE_SINT32 = 10;
    public static final short FFI_TYPE_UINT64 = 11;
    public static final short FFI_TYPE_SINT64 = 12;
    public static final short FFI_TYPE_STRUCT = 13;
    public static final short FFI_TYPE_POINTER = 14;
    public static final int FFI_FIRST_ABI;
    public static final int FFI_WIN64;
    public static final int FFI_GNUW64;
    public static final int FFI_UNIX64;
    public static final int FFI_EFI64;
    public static final int FFI_SYSV;
    public static final int FFI_STDCALL;
    public static final int FFI_THISCALL;
    public static final int FFI_FASTCALL;
    public static final int FFI_MS_CDECL;
    public static final int FFI_PASCAL;
    public static final int FFI_REGISTER;
    public static final int FFI_VFP;
    public static final int FFI_LAST_ABI;
    public static final int FFI_DEFAULT_ABI;
    public static final int FFI_OK = 0;
    public static final int FFI_BAD_TYPEDEF = 1;
    public static final int FFI_BAD_ABI = 2;
    public static final int FFI_BAD_ARGTYPE = 3;
    public static final FFIType ffi_type_void;
    public static final FFIType ffi_type_uint8;
    public static final FFIType ffi_type_sint8;
    public static final FFIType ffi_type_uint16;
    public static final FFIType ffi_type_sint16;
    public static final FFIType ffi_type_uint32;
    public static final FFIType ffi_type_sint32;
    public static final FFIType ffi_type_uint64;
    public static final FFIType ffi_type_sint64;
    public static final FFIType ffi_type_uchar;
    public static final FFIType ffi_type_schar;
    public static final FFIType ffi_type_ushort;
    public static final FFIType ffi_type_sshort;
    public static final FFIType ffi_type_uint;
    public static final FFIType ffi_type_sint;
    public static final FFIType ffi_type_ulong;
    public static final FFIType ffi_type_slong;
    public static final FFIType ffi_type_float;
    public static final FFIType ffi_type_double;
    public static final FFIType ffi_type_longdouble;
    public static final FFIType ffi_type_pointer;

    private static native short FFI_TYPE_DOUBLE();

    private static native int FFI_WIN64();

    private static native int FFI_GNUW64();

    private static native int FFI_UNIX64();

    private static native int FFI_EFI64();

    private static native int FFI_SYSV();

    private static native int FFI_STDCALL();

    private static native int FFI_THISCALL();

    private static native int FFI_FASTCALL();

    private static native int FFI_MS_CDECL();

    private static native int FFI_PASCAL();

    private static native int FFI_REGISTER();

    private static native int FFI_VFP();

    private static native int FFI_FIRST_ABI();

    private static native int FFI_LAST_ABI();

    private static native int FFI_DEFAULT_ABI();

    private static native long nffi_type_void();

    private static native long nffi_type_uint8();

    private static native long nffi_type_sint8();

    private static native long nffi_type_uint16();

    private static native long nffi_type_sint16();

    private static native long nffi_type_uint32();

    private static native long nffi_type_sint32();

    private static native long nffi_type_uint64();

    private static native long nffi_type_sint64();

    private static native long nffi_type_uchar();

    private static native long nffi_type_schar();

    private static native long nffi_type_ushort();

    private static native long nffi_type_sshort();

    private static native long nffi_type_uint();

    private static native long nffi_type_sint();

    private static native long nffi_type_ulong();

    private static native long nffi_type_slong();

    private static native long nffi_type_float();

    private static native long nffi_type_double();

    private static native long nffi_type_longdouble();

    private static native long nffi_type_pointer();

    public static native int nffi_prep_cif(long j, int i, int i2, long j2, long j3);

    public static native int nffi_prep_cif_var(long j, int i, int i2, int i3, long j2, long j3);

    public static native void nffi_call(long j, long j2, long j3, long j4);

    public static native int nffi_get_struct_offsets(int i, long j, long j2);

    public static native long nffi_closure_alloc(long j, long j2);

    public static native void nffi_closure_free(long j);

    public static native int nffi_prep_closure_loc(long j, long j2, long j3, long j4, long j5);

    static {
        Library.initialize();
        FFI_TYPE_LONGDOUBLE = FFI_TYPE_DOUBLE();
        FFI_FIRST_ABI = FFI_FIRST_ABI();
        FFI_WIN64 = FFI_WIN64();
        FFI_GNUW64 = FFI_GNUW64();
        FFI_UNIX64 = FFI_UNIX64();
        FFI_EFI64 = FFI_EFI64();
        FFI_SYSV = FFI_SYSV();
        FFI_STDCALL = FFI_STDCALL();
        FFI_THISCALL = FFI_THISCALL();
        FFI_FASTCALL = FFI_FASTCALL();
        FFI_MS_CDECL = FFI_MS_CDECL();
        FFI_PASCAL = FFI_PASCAL();
        FFI_REGISTER = FFI_REGISTER();
        FFI_VFP = FFI_VFP();
        FFI_LAST_ABI = FFI_LAST_ABI();
        FFI_DEFAULT_ABI = FFI_DEFAULT_ABI();
        ffi_type_void = ffi_type_void();
        ffi_type_uint8 = ffi_type_uint8();
        ffi_type_sint8 = ffi_type_sint8();
        ffi_type_uint16 = ffi_type_uint16();
        ffi_type_sint16 = ffi_type_sint16();
        ffi_type_uint32 = ffi_type_uint32();
        ffi_type_sint32 = ffi_type_sint32();
        ffi_type_uint64 = ffi_type_uint64();
        ffi_type_sint64 = ffi_type_sint64();
        ffi_type_uchar = ffi_type_uchar();
        ffi_type_schar = ffi_type_schar();
        ffi_type_ushort = ffi_type_ushort();
        ffi_type_sshort = ffi_type_sshort();
        ffi_type_uint = ffi_type_uint();
        ffi_type_sint = ffi_type_sint();
        ffi_type_ulong = ffi_type_ulong();
        ffi_type_slong = ffi_type_slong();
        ffi_type_float = ffi_type_float();
        ffi_type_double = ffi_type_double();
        ffi_type_longdouble = ffi_type_longdouble();
        ffi_type_pointer = ffi_type_pointer();
    }

    protected LibFFI() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_void() {
        return FFIType.create(nffi_type_void());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_uint8() {
        return FFIType.create(nffi_type_uint8());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_sint8() {
        return FFIType.create(nffi_type_sint8());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_uint16() {
        return FFIType.create(nffi_type_uint16());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_sint16() {
        return FFIType.create(nffi_type_sint16());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_uint32() {
        return FFIType.create(nffi_type_uint32());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_sint32() {
        return FFIType.create(nffi_type_sint32());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_uint64() {
        return FFIType.create(nffi_type_uint64());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_sint64() {
        return FFIType.create(nffi_type_sint64());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_uchar() {
        return FFIType.create(nffi_type_uchar());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_schar() {
        return FFIType.create(nffi_type_schar());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_ushort() {
        return FFIType.create(nffi_type_ushort());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_sshort() {
        return FFIType.create(nffi_type_sshort());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_uint() {
        return FFIType.create(nffi_type_uint());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_sint() {
        return FFIType.create(nffi_type_sint());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_ulong() {
        return FFIType.create(nffi_type_ulong());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_slong() {
        return FFIType.create(nffi_type_slong());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_float() {
        return FFIType.create(nffi_type_float());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_double() {
        return FFIType.create(nffi_type_double());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_longdouble() {
        return FFIType.create(nffi_type_longdouble());
    }

    @NativeType("ffi_type *")
    private static FFIType ffi_type_pointer() {
        return FFIType.create(nffi_type_pointer());
    }

    @NativeType("ffi_status")
    public static int ffi_prep_cif(@NativeType("ffi_cif *") FFICIF fficif, @NativeType("ffi_abi") int i, @NativeType("ffi_type *") FFIType fFIType, @NativeType("ffi_type **") PointerBuffer pointerBuffer) {
        return nffi_prep_cif(fficif.address(), i, Checks.remainingSafe(pointerBuffer), fFIType.address(), MemoryUtil.memAddressSafe(pointerBuffer));
    }

    @NativeType("ffi_status")
    public static int ffi_prep_cif_var(@NativeType("ffi_cif *") FFICIF fficif, @NativeType("ffi_abi") int i, @NativeType("unsigned int") int i2, @NativeType("ffi_type *") FFIType fFIType, @NativeType("ffi_type **") PointerBuffer pointerBuffer) {
        return nffi_prep_cif_var(fficif.address(), i, i2, pointerBuffer.remaining(), fFIType.address(), MemoryUtil.memAddress(pointerBuffer));
    }

    public static void ffi_call(@NativeType("ffi_cif *") FFICIF fficif, @NativeType("FFI_FN_TYPE") long j, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nffi_call(fficif.address(), j, MemoryUtil.memAddressSafe(byteBuffer), MemoryUtil.memAddressSafe(pointerBuffer));
    }

    @NativeType("ffi_status")
    public static int ffi_get_struct_offsets(@NativeType("ffi_abi") int i, @NativeType("ffi_type *") FFIType fFIType, @NativeType("size_t *") PointerBuffer pointerBuffer) {
        return nffi_get_struct_offsets(i, fFIType.address(), MemoryUtil.memAddressSafe(pointerBuffer));
    }

    @NativeType("ffi_closure *")
    public static FFIClosure ffi_closure_alloc(@NativeType("size_t") long j, @NativeType("void **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return FFIClosure.createSafe(nffi_closure_alloc(j, MemoryUtil.memAddress(pointerBuffer)));
    }

    public static void ffi_closure_free(@NativeType("ffi_closure *") FFIClosure fFIClosure) {
        nffi_closure_free(fFIClosure.address());
    }

    @NativeType("ffi_status")
    public static int ffi_prep_closure_loc(@NativeType("ffi_closure *") FFIClosure fFIClosure, @NativeType("ffi_cif *") FFICIF fficif, @NativeType("FFI_CLOSURE_FUN") long j, @NativeType("void *") long j2, @NativeType("void *") long j3) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(j3);
        }
        return nffi_prep_closure_loc(fFIClosure.address(), fficif.address(), j, j2, j3);
    }
}
