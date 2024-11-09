package org.lwjgl.system.libc;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/libc/LibCLocale.class */
public class LibCLocale {
    public static final int LC_ALL;
    public static final int LC_COLLATE;
    public static final int LC_CTYPE;
    public static final int LC_MONETARY;
    public static final int LC_NUMERIC;
    public static final int LC_TIME;

    private static native int LC_ALL();

    private static native int LC_COLLATE();

    private static native int LC_CTYPE();

    private static native int LC_MONETARY();

    private static native int LC_NUMERIC();

    private static native int LC_TIME();

    public static native long nsetlocale(int i, long j);

    static {
        Library.initialize();
        LC_ALL = LC_ALL();
        LC_COLLATE = LC_COLLATE();
        LC_CTYPE = LC_CTYPE();
        LC_MONETARY = LC_MONETARY();
        LC_NUMERIC = LC_NUMERIC();
        LC_TIME = LC_TIME();
    }

    protected LibCLocale() {
        throw new UnsupportedOperationException();
    }

    @NativeType("char *")
    public static String setlocale(int i, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return MemoryUtil.memASCIISafe(nsetlocale(i, MemoryUtil.memAddress(byteBuffer)));
    }

    @NativeType("char *")
    public static String setlocale(int i, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return MemoryUtil.memASCIISafe(nsetlocale(i, stackGet.getPointerAddress()));
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
