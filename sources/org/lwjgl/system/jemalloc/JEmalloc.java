package org.lwjgl.system.jemalloc;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Configuration;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Platform;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/JEmalloc.class */
public class JEmalloc {
    public static final int JEMALLOC_VERSION_MAJOR = 5;
    public static final int JEMALLOC_VERSION_MINOR = 2;
    public static final int JEMALLOC_VERSION_BUGFIX = 1;
    public static final int JEMALLOC_VERSION_NREV = 0;
    public static final String JEMALLOC_VERSION_GID = "ea6b3e973b477b8061e0076bb257dbd7f3faa756";
    public static final String JEMALLOC_VERSION = "5.2.1-0-gea6b3e973b477b8061e0076bb257dbd7f3faa756";
    public static final int MALLOCX_ZERO = 64;
    public static final int MALLCTL_ARENAS_ALL = 4096;
    public static final int MALLCTL_ARENAS_DESTROYED = 4097;
    private static final SharedLibrary JEMALLOC = Library.loadNative((Class<?>) JEmalloc.class, "org.lwjgl.jemalloc", Configuration.JEMALLOC_LIBRARY_NAME.get(Platform.mapLibraryNameBundled("jemalloc")), true);
    public static final int MALLOCX_TCACHE_NONE = MALLOCX_TCACHE(-1);

    static {
        if (Platform.get() == Platform.WINDOWS) {
            JNI.invokePV(JNI.invokePP(8L, APIUtil.apiGetFunctionAddress(JEMALLOC, "je_malloc")), APIUtil.apiGetFunctionAddress(JEMALLOC, "je_free"));
        }
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/JEmalloc$Functions.class */
    public static final class Functions {
        public static final long malloc_message = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_malloc_message");
        public static final long malloc = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_malloc");
        public static final long calloc = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_calloc");
        public static final long posix_memalign = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_posix_memalign");
        public static final long aligned_alloc = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_aligned_alloc");
        public static final long realloc = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_realloc");
        public static final long free = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_free");
        public static final long free_sized = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_free_sized");
        public static final long free_aligned_sized = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_free_aligned_sized");
        public static final long mallocx = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_mallocx");
        public static final long rallocx = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_rallocx");
        public static final long xallocx = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_xallocx");
        public static final long sallocx = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_sallocx");
        public static final long dallocx = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_dallocx");
        public static final long sdallocx = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_sdallocx");
        public static final long nallocx = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_nallocx");
        public static final long mallctl = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_mallctl");
        public static final long mallctlnametomib = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_mallctlnametomib");
        public static final long mallctlbymib = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_mallctlbymib");
        public static final long malloc_stats_print = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_malloc_stats_print");
        public static final long malloc_usable_size = APIUtil.apiGetFunctionAddress(JEmalloc.JEMALLOC, "je_malloc_usable_size");

        private Functions() {
        }
    }

    public static SharedLibrary getLibrary() {
        return JEMALLOC;
    }

    protected JEmalloc() {
        throw new UnsupportedOperationException();
    }

    @NativeType("void (*) (void *, char const *) *")
    public static PointerBuffer je_malloc_message() {
        return MemoryUtil.memPointerBuffer(Functions.malloc_message, 1);
    }

    public static long nje_malloc(long j) {
        return JNI.invokePP(j, Functions.malloc);
    }

    @NativeType("void *")
    public static ByteBuffer je_malloc(@NativeType("size_t") long j) {
        return MemoryUtil.memByteBufferSafe(nje_malloc(j), (int) j);
    }

    public static long nje_calloc(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.calloc);
    }

    @NativeType("void *")
    public static ByteBuffer je_calloc(@NativeType("size_t") long j, @NativeType("size_t") long j2) {
        return MemoryUtil.memByteBufferSafe(nje_calloc(j, j2), ((int) j) * ((int) j2));
    }

    public static int nje_posix_memalign(long j, long j2, long j3) {
        return JNI.invokePPPI(j, j2, j3, Functions.posix_memalign);
    }

    public static int je_posix_memalign(@NativeType("void **") PointerBuffer pointerBuffer, @NativeType("size_t") long j, @NativeType("size_t") long j2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nje_posix_memalign(MemoryUtil.memAddress(pointerBuffer), j, j2);
    }

    public static long nje_aligned_alloc(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.aligned_alloc);
    }

    @NativeType("void *")
    public static ByteBuffer je_aligned_alloc(@NativeType("size_t") long j, @NativeType("size_t") long j2) {
        return MemoryUtil.memByteBufferSafe(nje_aligned_alloc(j, j2), (int) j2);
    }

    public static long nje_realloc(long j, long j2) {
        return JNI.invokePPP(j, j2, Functions.realloc);
    }

    @NativeType("void *")
    public static ByteBuffer je_realloc(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("size_t") long j) {
        return MemoryUtil.memByteBufferSafe(nje_realloc(MemoryUtil.memAddressSafe(byteBuffer), j), (int) j);
    }

    public static void nje_free(long j) {
        JNI.invokePV(j, Functions.free);
    }

    public static void je_free(@NativeType("void *") ByteBuffer byteBuffer) {
        nje_free(MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void je_free(@NativeType("void *") ShortBuffer shortBuffer) {
        nje_free(MemoryUtil.memAddressSafe(shortBuffer));
    }

    public static void je_free(@NativeType("void *") IntBuffer intBuffer) {
        nje_free(MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void je_free(@NativeType("void *") LongBuffer longBuffer) {
        nje_free(MemoryUtil.memAddressSafe(longBuffer));
    }

    public static void je_free(@NativeType("void *") FloatBuffer floatBuffer) {
        nje_free(MemoryUtil.memAddressSafe(floatBuffer));
    }

    public static void je_free(@NativeType("void *") DoubleBuffer doubleBuffer) {
        nje_free(MemoryUtil.memAddressSafe(doubleBuffer));
    }

    public static void je_free(@NativeType("void *") PointerBuffer pointerBuffer) {
        nje_free(MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static void nje_free_sized(long j, long j2) {
        JNI.invokePPV(j, j2, Functions.free_sized);
    }

    public static void je_free_sized(@NativeType("void *") ByteBuffer byteBuffer) {
        nje_free_sized(MemoryUtil.memAddressSafe(byteBuffer), Checks.remainingSafe(byteBuffer));
    }

    public static void je_free_sized(@NativeType("void *") ShortBuffer shortBuffer) {
        nje_free_sized(MemoryUtil.memAddressSafe(shortBuffer), Integer.toUnsignedLong(Checks.remainingSafe(shortBuffer)) << 1);
    }

    public static void je_free_sized(@NativeType("void *") IntBuffer intBuffer) {
        nje_free_sized(MemoryUtil.memAddressSafe(intBuffer), Integer.toUnsignedLong(Checks.remainingSafe(intBuffer)) << 2);
    }

    public static void je_free_sized(@NativeType("void *") LongBuffer longBuffer) {
        nje_free_sized(MemoryUtil.memAddressSafe(longBuffer), Integer.toUnsignedLong(Checks.remainingSafe(longBuffer)) << 3);
    }

    public static void je_free_sized(@NativeType("void *") FloatBuffer floatBuffer) {
        nje_free_sized(MemoryUtil.memAddressSafe(floatBuffer), Integer.toUnsignedLong(Checks.remainingSafe(floatBuffer)) << 2);
    }

    public static void je_free_sized(@NativeType("void *") DoubleBuffer doubleBuffer) {
        nje_free_sized(MemoryUtil.memAddressSafe(doubleBuffer), Integer.toUnsignedLong(Checks.remainingSafe(doubleBuffer)) << 3);
    }

    public static void je_free_sized(@NativeType("void *") PointerBuffer pointerBuffer) {
        nje_free_sized(MemoryUtil.memAddressSafe(pointerBuffer), Integer.toUnsignedLong(Checks.remainingSafe(pointerBuffer)) << MemoryStack.POINTER_SHIFT);
    }

    public static void nje_free_aligned_sized(long j, long j2, long j3) {
        JNI.invokePPPV(j, j2, j3, Functions.free_aligned_sized);
    }

    public static void je_free_aligned_sized(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("size_t") long j) {
        nje_free_aligned_sized(MemoryUtil.memAddressSafe(byteBuffer), j, Checks.remainingSafe(byteBuffer));
    }

    public static void je_free_aligned_sized(@NativeType("void *") ShortBuffer shortBuffer, @NativeType("size_t") long j) {
        nje_free_aligned_sized(MemoryUtil.memAddressSafe(shortBuffer), j, Integer.toUnsignedLong(Checks.remainingSafe(shortBuffer)) << 1);
    }

    public static void je_free_aligned_sized(@NativeType("void *") IntBuffer intBuffer, @NativeType("size_t") long j) {
        nje_free_aligned_sized(MemoryUtil.memAddressSafe(intBuffer), j, Integer.toUnsignedLong(Checks.remainingSafe(intBuffer)) << 2);
    }

    public static void je_free_aligned_sized(@NativeType("void *") LongBuffer longBuffer, @NativeType("size_t") long j) {
        nje_free_aligned_sized(MemoryUtil.memAddressSafe(longBuffer), j, Integer.toUnsignedLong(Checks.remainingSafe(longBuffer)) << 3);
    }

    public static void je_free_aligned_sized(@NativeType("void *") FloatBuffer floatBuffer, @NativeType("size_t") long j) {
        nje_free_aligned_sized(MemoryUtil.memAddressSafe(floatBuffer), j, Integer.toUnsignedLong(Checks.remainingSafe(floatBuffer)) << 2);
    }

    public static void je_free_aligned_sized(@NativeType("void *") DoubleBuffer doubleBuffer, @NativeType("size_t") long j) {
        nje_free_aligned_sized(MemoryUtil.memAddressSafe(doubleBuffer), j, Integer.toUnsignedLong(Checks.remainingSafe(doubleBuffer)) << 3);
    }

    public static void je_free_aligned_sized(@NativeType("void *") PointerBuffer pointerBuffer, @NativeType("size_t") long j) {
        nje_free_aligned_sized(MemoryUtil.memAddressSafe(pointerBuffer), j, Integer.toUnsignedLong(Checks.remainingSafe(pointerBuffer)) << MemoryStack.POINTER_SHIFT);
    }

    public static long nje_mallocx(long j, int i) {
        return JNI.invokePP(j, i, Functions.mallocx);
    }

    @NativeType("void *")
    public static ByteBuffer je_mallocx(@NativeType("size_t") long j, int i) {
        return MemoryUtil.memByteBufferSafe(nje_mallocx(j, i), (int) j);
    }

    public static long nje_rallocx(long j, long j2, int i) {
        return JNI.invokePPP(j, j2, i, Functions.rallocx);
    }

    @NativeType("void *")
    public static ByteBuffer je_rallocx(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("size_t") long j, int i) {
        return MemoryUtil.memByteBufferSafe(nje_rallocx(MemoryUtil.memAddressSafe(byteBuffer), j, i), (int) j);
    }

    public static long nje_xallocx(long j, long j2, long j3, int i) {
        return JNI.invokePPPP(j, j2, j3, i, Functions.xallocx);
    }

    @NativeType("size_t")
    public static long je_xallocx(@NativeType("void *") ByteBuffer byteBuffer, @NativeType("size_t") long j, @NativeType("size_t") long j2, int i) {
        return nje_xallocx(MemoryUtil.memAddressSafe(byteBuffer), j, j2, i);
    }

    public static long nje_sallocx(long j, int i) {
        return JNI.invokePP(j, i, Functions.sallocx);
    }

    @NativeType("size_t")
    public static long je_sallocx(@NativeType("void const *") ByteBuffer byteBuffer, int i) {
        return nje_sallocx(MemoryUtil.memAddress(byteBuffer), i);
    }

    public static void nje_dallocx(long j, int i) {
        JNI.invokePV(j, i, Functions.dallocx);
    }

    public static void je_dallocx(@NativeType("void *") ByteBuffer byteBuffer, int i) {
        nje_dallocx(MemoryUtil.memAddress(byteBuffer), i);
    }

    public static void je_dallocx(@NativeType("void *") ShortBuffer shortBuffer, int i) {
        nje_dallocx(MemoryUtil.memAddress(shortBuffer), i);
    }

    public static void je_dallocx(@NativeType("void *") IntBuffer intBuffer, int i) {
        nje_dallocx(MemoryUtil.memAddress(intBuffer), i);
    }

    public static void je_dallocx(@NativeType("void *") LongBuffer longBuffer, int i) {
        nje_dallocx(MemoryUtil.memAddress(longBuffer), i);
    }

    public static void je_dallocx(@NativeType("void *") FloatBuffer floatBuffer, int i) {
        nje_dallocx(MemoryUtil.memAddress(floatBuffer), i);
    }

    public static void je_dallocx(@NativeType("void *") DoubleBuffer doubleBuffer, int i) {
        nje_dallocx(MemoryUtil.memAddress(doubleBuffer), i);
    }

    public static void je_dallocx(@NativeType("void *") PointerBuffer pointerBuffer, int i) {
        nje_dallocx(MemoryUtil.memAddress(pointerBuffer), i);
    }

    public static void nje_sdallocx(long j, long j2, int i) {
        JNI.invokePPV(j, j2, i, Functions.sdallocx);
    }

    public static void je_sdallocx(@NativeType("void *") ByteBuffer byteBuffer, int i) {
        nje_sdallocx(MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i);
    }

    public static void je_sdallocx(@NativeType("void *") ShortBuffer shortBuffer, int i) {
        nje_sdallocx(MemoryUtil.memAddress(shortBuffer), Integer.toUnsignedLong(shortBuffer.remaining()) << 1, i);
    }

    public static void je_sdallocx(@NativeType("void *") IntBuffer intBuffer, int i) {
        nje_sdallocx(MemoryUtil.memAddress(intBuffer), Integer.toUnsignedLong(intBuffer.remaining()) << 2, i);
    }

    public static void je_sdallocx(@NativeType("void *") LongBuffer longBuffer, int i) {
        nje_sdallocx(MemoryUtil.memAddress(longBuffer), Integer.toUnsignedLong(longBuffer.remaining()) << 3, i);
    }

    public static void je_sdallocx(@NativeType("void *") FloatBuffer floatBuffer, int i) {
        nje_sdallocx(MemoryUtil.memAddress(floatBuffer), Integer.toUnsignedLong(floatBuffer.remaining()) << 2, i);
    }

    public static void je_sdallocx(@NativeType("void *") DoubleBuffer doubleBuffer, int i) {
        nje_sdallocx(MemoryUtil.memAddress(doubleBuffer), Integer.toUnsignedLong(doubleBuffer.remaining()) << 3, i);
    }

    public static void je_sdallocx(@NativeType("void *") PointerBuffer pointerBuffer, int i) {
        nje_sdallocx(MemoryUtil.memAddress(pointerBuffer), Integer.toUnsignedLong(pointerBuffer.remaining()) << MemoryStack.POINTER_SHIFT, i);
    }

    public static long nje_nallocx(long j, int i) {
        return JNI.invokePP(j, i, Functions.nallocx);
    }

    @NativeType("void *")
    public static ByteBuffer je_nallocx(@NativeType("size_t") long j, int i) {
        return MemoryUtil.memByteBufferSafe(nje_nallocx(j, i), (int) j);
    }

    public static int nje_mallctl(long j, long j2, long j3, long j4, long j5) {
        return JNI.invokePPPPPI(j, j2, j3, j4, j5, Functions.mallctl);
    }

    public static int je_mallctl(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("void *") ByteBuffer byteBuffer2, @NativeType("size_t *") PointerBuffer pointerBuffer, @NativeType("void *") ByteBuffer byteBuffer3) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nje_mallctl(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddressSafe(byteBuffer2), MemoryUtil.memAddressSafe(pointerBuffer), MemoryUtil.memAddressSafe(byteBuffer3), Checks.remainingSafe(byteBuffer3));
    }

    public static int je_mallctl(@NativeType("char const *") CharSequence charSequence, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("size_t *") PointerBuffer pointerBuffer, @NativeType("void *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer, 1);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nje_mallctl(stackGet.getPointerAddress(), MemoryUtil.memAddressSafe(byteBuffer), MemoryUtil.memAddressSafe(pointerBuffer), MemoryUtil.memAddressSafe(byteBuffer2), Checks.remainingSafe(byteBuffer2));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nje_mallctlnametomib(long j, long j2, long j3) {
        return JNI.invokePPPI(j, j2, j3, Functions.mallctlnametomib);
    }

    public static int je_mallctlnametomib(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("size_t *") PointerBuffer pointerBuffer, @NativeType("size_t *") PointerBuffer pointerBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check((CustomBuffer<?>) pointerBuffer2, 1);
            Checks.check(pointerBuffer, pointerBuffer2.get(pointerBuffer2.position()));
        }
        return nje_mallctlnametomib(MemoryUtil.memAddress(byteBuffer), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(pointerBuffer2));
    }

    public static int je_mallctlnametomib(@NativeType("char const *") CharSequence charSequence, @NativeType("size_t *") PointerBuffer pointerBuffer, @NativeType("size_t *") PointerBuffer pointerBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer2, 1);
            Checks.check(pointerBuffer, pointerBuffer2.get(pointerBuffer2.position()));
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nje_mallctlnametomib(stackGet.getPointerAddress(), MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(pointerBuffer2));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nje_mallctlbymib(long j, long j2, long j3, long j4, long j5, long j6) {
        return JNI.invokePPPPPPI(j, j2, j3, j4, j5, j6, Functions.mallctlbymib);
    }

    public static int je_mallctlbymib(@NativeType("size_t const *") PointerBuffer pointerBuffer, @NativeType("void *") ByteBuffer byteBuffer, @NativeType("size_t *") PointerBuffer pointerBuffer2, @NativeType("void *") ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.checkSafe((CustomBuffer<?>) pointerBuffer2, 1);
        }
        return nje_mallctlbymib(MemoryUtil.memAddress(pointerBuffer), pointerBuffer.remaining(), MemoryUtil.memAddressSafe(byteBuffer), MemoryUtil.memAddressSafe(pointerBuffer2), MemoryUtil.memAddressSafe(byteBuffer2), Checks.remainingSafe(byteBuffer2));
    }

    public static void nje_malloc_stats_print(long j, long j2, long j3) {
        JNI.invokePPPV(j, j2, j3, Functions.malloc_stats_print);
    }

    public static void je_malloc_stats_print(@NativeType("void (*) (void *, char const *)") MallocMessageCallbackI mallocMessageCallbackI, @NativeType("void *") long j, @NativeType("char const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1Safe(byteBuffer);
        }
        nje_malloc_stats_print(MemoryUtil.memAddressSafe(mallocMessageCallbackI), j, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void je_malloc_stats_print(@NativeType("void (*) (void *, char const *)") MallocMessageCallbackI mallocMessageCallbackI, @NativeType("void *") long j, @NativeType("char const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCIISafe(charSequence, true);
            nje_malloc_stats_print(MemoryUtil.memAddressSafe(mallocMessageCallbackI), j, charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nje_malloc_usable_size(long j) {
        return JNI.invokePP(j, Functions.malloc_usable_size);
    }

    @NativeType("size_t")
    public static long je_malloc_usable_size(@NativeType("void const *") ByteBuffer byteBuffer) {
        return nje_malloc_usable_size(MemoryUtil.memAddress(byteBuffer));
    }

    public static int MALLOCX_LG_ALIGN(int i) {
        return i;
    }

    public static int MALLOCX_ALIGN(int i) {
        return Integer.numberOfTrailingZeros(i);
    }

    public static int MALLOCX_TCACHE(int i) {
        return (i + 2) << 8;
    }

    public static int MALLOCX_ARENA(int i) {
        return (i + 1) << 20;
    }
}
