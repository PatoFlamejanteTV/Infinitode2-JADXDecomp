package org.lwjgl.system.libc;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/libc/LibCStdio.class */
public class LibCStdio {
    public static final long sscanf;
    public static final long snprintf;

    @NativeType("void *")
    private static native long sscanf();

    public static native int nvsscanf(long j, long j2, long j3);

    @NativeType("void *")
    private static native long snprintf();

    public static native int nvsnprintf(long j, long j2, long j3, long j4);

    static {
        Library.initialize();
        sscanf = sscanf();
        snprintf = snprintf();
    }

    protected LibCStdio() {
        throw new UnsupportedOperationException();
    }

    public static int vsscanf(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2, @NativeType("va_list") long j) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkNT1(byteBuffer2);
            Checks.check(j);
        }
        return nvsscanf(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(byteBuffer2), j);
    }

    public static int vsscanf(@NativeType("char const *") CharSequence charSequence, @NativeType("char const *") CharSequence charSequence2, @NativeType("va_list") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            long pointerAddress = stackGet.getPointerAddress();
            stackGet.nASCII(charSequence2, true);
            return nvsscanf(pointerAddress, stackGet.getPointerAddress(), j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int vsnprintf(@NativeType("char *") ByteBuffer byteBuffer, @NativeType("char const *") ByteBuffer byteBuffer2, @NativeType("va_list") long j) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer2);
            Checks.check(j);
        }
        return nvsnprintf(MemoryUtil.memAddressSafe(byteBuffer), Checks.remainingSafe(byteBuffer), MemoryUtil.memAddress(byteBuffer2), j);
    }

    public static int vsnprintf(@NativeType("char *") ByteBuffer byteBuffer, @NativeType("char const *") CharSequence charSequence, @NativeType("va_list") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nvsnprintf(MemoryUtil.memAddressSafe(byteBuffer), Checks.remainingSafe(byteBuffer), stackGet.getPointerAddress(), j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
