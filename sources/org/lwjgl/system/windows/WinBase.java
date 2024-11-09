package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WinBase.class */
public class WinBase {
    public static final int FALSE = 0;
    public static final int TRUE = 1;

    public static native long nLocalFree(long j);

    @NativeType("DWORD")
    public static native int GetLastError();

    @NativeType("DWORD")
    public static native int getLastError();

    public static native long nGetModuleHandle(long j);

    public static native int nGetModuleFileName(long j, long j2, int i);

    public static native long nLoadLibrary(long j);

    public static native long nGetProcAddress(long j, long j2);

    public static native int nFreeLibrary(long j);

    static {
        Library.initialize();
    }

    protected WinBase() {
        throw new UnsupportedOperationException();
    }

    @NativeType("HLOCAL")
    public static long LocalFree(@NativeType("HLOCAL") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nLocalFree(j);
    }

    @NativeType("HMODULE")
    public static long GetModuleHandle(@NativeType("LPCTSTR") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT2Safe(byteBuffer);
        }
        return nGetModuleHandle(MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("HMODULE")
    public static long GetModuleHandle(@NativeType("LPCTSTR") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF16Safe(charSequence, true);
            return nGetModuleHandle(charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("DWORD")
    public static int GetModuleFileName(@NativeType("HMODULE") long j, @NativeType("LPTSTR") ByteBuffer byteBuffer) {
        return nGetModuleFileName(j, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining() >> 1);
    }

    @NativeType("DWORD")
    public static String GetModuleFileName(@NativeType("HMODULE") long j, @NativeType("DWORD") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            ByteBuffer malloc = stackGet.malloc(i << 1);
            return MemoryUtil.memUTF16(malloc, nGetModuleFileName(j, MemoryUtil.memAddress(malloc), i));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("HMODULE")
    public static long LoadLibrary(@NativeType("LPCTSTR") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT2(byteBuffer);
        }
        return nLoadLibrary(MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("HMODULE")
    public static long LoadLibrary(@NativeType("LPCTSTR") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF16(charSequence, true);
            return nLoadLibrary(stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("FARPROC")
    public static long GetProcAddress(@NativeType("HMODULE") long j, @NativeType("LPCSTR") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNT1(byteBuffer);
        }
        return nGetProcAddress(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("FARPROC")
    public static long GetProcAddress(@NativeType("HMODULE") long j, @NativeType("LPCSTR") CharSequence charSequence) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nGetProcAddress(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("BOOL")
    public static boolean FreeLibrary(@NativeType("HMODULE") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nFreeLibrary(j) != 0;
    }
}
