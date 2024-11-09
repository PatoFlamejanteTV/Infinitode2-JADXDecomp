package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/Crypt32.class */
public class Crypt32 {
    private static final SharedLibrary CRYPT32 = Library.loadNative(Crypt32.class, "org.lwjgl", "crypt32");
    public static final int CRYPTPROTECT_UI_FORBIDDEN = 1;
    public static final int CRYPTPROTECT_LOCAL_MACHINE = 4;
    public static final int CRYPTPROTECT_AUDIT = 16;
    public static final int CRYPTPROTECT_VERIFY_PROTECTION = 64;
    public static final int CRYPTPROTECTMEMORY_SAME_PROCESS = 0;
    public static final int CRYPTPROTECTMEMORY_CROSS_PROCESS = 1;
    public static final int CRYPTPROTECTMEMORY_SAME_LOGON = 2;
    public static final int CRYPTPROTECT_PROMPT_ON_UNPROTECT = 1;
    public static final int CRYPTPROTECT_PROMPT_ON_PROTECT = 2;
    public static final int CRYPTPROTECTMEMORY_BLOCK_SIZE = 16;

    public static native int nCryptProtectData(long j, long j2, long j3, long j4, long j5, int i, long j6, long j7);

    public static native int nCryptProtectMemory(long j, int i, int i2, long j2);

    public static native int nCryptUnprotectData(long j, long j2, long j3, long j4, long j5, int i, long j6, long j7);

    public static native int nCryptUnprotectMemory(long j, int i, int i2, long j2);

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/Crypt32$Functions.class */
    public static final class Functions {
        public static final long CryptProtectData = APIUtil.apiGetFunctionAddress(Crypt32.CRYPT32, "CryptProtectData");
        public static final long CryptProtectMemory = APIUtil.apiGetFunctionAddressOptional(Crypt32.CRYPT32, "CryptProtectMemory");
        public static final long CryptUnprotectData = APIUtil.apiGetFunctionAddress(Crypt32.CRYPT32, "CryptUnprotectData");
        public static final long CryptUnprotectMemory = APIUtil.apiGetFunctionAddressOptional(Crypt32.CRYPT32, "CryptUnprotectMemory");

        private Functions() {
        }
    }

    public static SharedLibrary getLibrary() {
        return CRYPT32;
    }

    protected Crypt32() {
        throw new UnsupportedOperationException();
    }

    public static int nCryptProtectData(long j, long j2, long j3, long j4, long j5, int i, long j6) {
        return nCryptProtectData(j, j2, j3, j4, j5, i, j6, Functions.CryptProtectData);
    }

    @NativeType("BOOL")
    public static boolean CryptProtectData(@NativeType("DATA_BLOB *") DATA_BLOB data_blob, @NativeType("LPCWSTR") ByteBuffer byteBuffer, @NativeType("DATA_BLOB *") DATA_BLOB data_blob2, @NativeType("PVOID") long j, @NativeType("CRYPTPROTECT_PROMPTSTRUCT *") CRYPTPROTECT_PROMPTSTRUCT cryptprotect_promptstruct, @NativeType("DWORD") int i, @NativeType("DATA_BLOB *") DATA_BLOB data_blob3) {
        if (Checks.CHECKS) {
            Checks.checkNT2Safe(byteBuffer);
        }
        return nCryptProtectData(data_blob.address(), MemoryUtil.memAddressSafe(byteBuffer), MemoryUtil.memAddressSafe(data_blob2), j, MemoryUtil.memAddressSafe(cryptprotect_promptstruct), i, data_blob3.address()) != 0;
    }

    @NativeType("BOOL")
    public static boolean CryptProtectData(@NativeType("DATA_BLOB *") DATA_BLOB data_blob, @NativeType("LPCWSTR") CharSequence charSequence, @NativeType("DATA_BLOB *") DATA_BLOB data_blob2, @NativeType("PVOID") long j, @NativeType("CRYPTPROTECT_PROMPTSTRUCT *") CRYPTPROTECT_PROMPTSTRUCT cryptprotect_promptstruct, @NativeType("DWORD") int i, @NativeType("DATA_BLOB *") DATA_BLOB data_blob3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF16Safe(charSequence, true);
            return nCryptProtectData(data_blob.address(), charSequence == null ? 0L : stackGet.getPointerAddress(), MemoryUtil.memAddressSafe(data_blob2), j, MemoryUtil.memAddressSafe(cryptprotect_promptstruct), i, data_blob3.address()) != 0;
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nCryptProtectMemory(long j, int i, int i2) {
        long j2 = Functions.CryptProtectMemory;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return nCryptProtectMemory(j, i, i2, j2);
    }

    @NativeType("BOOL")
    public static boolean CryptProtectMemory(@NativeType("LPVOID") ByteBuffer byteBuffer, @NativeType("DWORD") int i) {
        return nCryptProtectMemory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i) != 0;
    }

    public static int nCryptUnprotectData(long j, long j2, long j3, long j4, long j5, int i, long j6) {
        return nCryptUnprotectData(j, j2, j3, j4, j5, i, j6, Functions.CryptUnprotectData);
    }

    @NativeType("BOOL")
    public static boolean CryptUnprotectData(@NativeType("DATA_BLOB *") DATA_BLOB data_blob, @NativeType("LPWSTR *") PointerBuffer pointerBuffer, @NativeType("DATA_BLOB *") DATA_BLOB data_blob2, @NativeType("PVOID") long j, @NativeType("CRYPTPROTECT_PROMPTSTRUCT *") CRYPTPROTECT_PROMPTSTRUCT cryptprotect_promptstruct, @NativeType("DWORD") int i, @NativeType("DATA_BLOB *") DATA_BLOB data_blob3) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nCryptUnprotectData(data_blob.address(), MemoryUtil.memAddressSafe(pointerBuffer), MemoryUtil.memAddressSafe(data_blob2), j, MemoryUtil.memAddressSafe(cryptprotect_promptstruct), i, data_blob3.address()) != 0;
    }

    public static int nCryptUnprotectMemory(long j, int i, int i2) {
        long j2 = Functions.CryptUnprotectMemory;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return nCryptUnprotectMemory(j, i, i2, j2);
    }

    @NativeType("BOOL")
    public static boolean CryptUnprotectMemory(@NativeType("LPVOID") ByteBuffer byteBuffer, @NativeType("DWORD") int i) {
        return nCryptUnprotectMemory(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i) != 0;
    }
}
