package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/DynamicLinkLoader.class */
public class DynamicLinkLoader {
    public static final int RTLD_LAZY = 1;
    public static final int RTLD_NOW = 2;
    public static final int RTLD_LOCAL = 4;
    public static final int RTLD_GLOBAL = 8;
    public static final long RTLD_NEXT = -1;
    public static final long RTLD_DEFAULT = -2;
    public static final long RTLD_SELF = -3;
    public static final long RTLD_MAIN_ONLY = -5;

    public static native long ndlopen(long j, int i);

    public static native long ndlerror();

    public static native long ndlsym(long j, long j2);

    public static native int ndlclose(long j);

    static {
        Library.initialize();
    }

    protected DynamicLinkLoader() {
        throw new UnsupportedOperationException();
    }

    @NativeType("void *")
    public static long dlopen(@NativeType("char const *") ByteBuffer byteBuffer, int i) {
        if (Checks.CHECKS) {
            Checks.checkNT1Safe(byteBuffer);
        }
        return ndlopen(MemoryUtil.memAddressSafe(byteBuffer), i);
    }

    @NativeType("void *")
    public static long dlopen(@NativeType("char const *") CharSequence charSequence, int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return ndlopen(charSequence == null ? 0L : stackGet.getPointerAddress(), i);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("char const *")
    public static String dlerror() {
        return MemoryUtil.memUTF8Safe(ndlerror());
    }

    @NativeType("void *")
    public static long dlsym(@NativeType("void *") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNT1(byteBuffer);
        }
        return ndlsym(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void *")
    public static long dlsym(@NativeType("void *") long j, @NativeType("char const *") CharSequence charSequence) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return ndlsym(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int dlclose(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ndlclose(j);
    }
}
