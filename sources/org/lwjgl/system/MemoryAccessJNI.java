package org.lwjgl.system;

/* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryAccessJNI.class */
final class MemoryAccessJNI {
    static final long malloc;
    static final long calloc;
    static final long realloc;
    static final long free;
    static final long aligned_alloc;
    static final long aligned_free;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getPointerSize();

    @NativeType("void * (*) (size_t)")
    private static native long malloc();

    @NativeType("void * (*) (size_t, size_t)")
    private static native long calloc();

    @NativeType("void * (*) (void *, size_t)")
    private static native long realloc();

    @NativeType("void (*) (void *)")
    private static native long free();

    @NativeType("void * (*) (size_t, size_t)")
    private static native long aligned_alloc();

    @NativeType("void (*) (void *)")
    private static native long aligned_free();

    static native byte ngetByte(long j);

    static native short ngetShort(long j);

    static native int ngetInt(long j);

    static native long ngetLong(long j);

    static native float ngetFloat(long j);

    static native double ngetDouble(long j);

    static native long ngetAddress(long j);

    static native void nputByte(long j, byte b2);

    static native void nputShort(long j, short s);

    static native void nputInt(long j, int i);

    static native void nputLong(long j, long j2);

    static native void nputFloat(long j, float f);

    static native void nputDouble(long j, double d);

    static native void nputAddress(long j, long j2);

    static {
        Library.initialize();
        malloc = malloc();
        calloc = calloc();
        realloc = realloc();
        free = free();
        aligned_alloc = aligned_alloc();
        aligned_free = aligned_free();
    }

    private MemoryAccessJNI() {
        throw new UnsupportedOperationException();
    }

    @NativeType("int8_t")
    static byte getByte(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ngetByte(j);
    }

    @NativeType("int16_t")
    static short getShort(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ngetShort(j);
    }

    @NativeType("int32_t")
    static int getInt(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ngetInt(j);
    }

    @NativeType("int64_t")
    static long getLong(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ngetLong(j);
    }

    static float getFloat(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ngetFloat(j);
    }

    static double getDouble(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ngetDouble(j);
    }

    @NativeType("uintptr_t")
    static long getAddress(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ngetAddress(j);
    }

    static void putByte(@NativeType("void *") long j, @NativeType("int8_t") byte b2) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nputByte(j, b2);
    }

    static void putShort(@NativeType("void *") long j, @NativeType("int16_t") short s) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nputShort(j, s);
    }

    static void putInt(@NativeType("void *") long j, @NativeType("int32_t") int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nputInt(j, i);
    }

    static void putLong(@NativeType("void *") long j, @NativeType("int64_t") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nputLong(j, j2);
    }

    static void putFloat(@NativeType("void *") long j, float f) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nputFloat(j, f);
    }

    static void putDouble(@NativeType("void *") long j, double d) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nputDouble(j, d);
    }

    static void putAddress(@NativeType("void *") long j, @NativeType("uintptr_t") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nputAddress(j, j2);
    }
}
