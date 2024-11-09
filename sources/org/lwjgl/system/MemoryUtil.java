package org.lwjgl.system;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.function.LongPredicate;
import org.lwjgl.CLongBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryManage;
import org.lwjgl.system.jni.JNINativeInterface;
import org.lwjgl.system.libc.LibCString;
import sun.misc.Unsafe;

/* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryUtil.class */
public final class MemoryUtil {
    public static final long NULL = 0;
    public static final int PAGE_SIZE;
    public static final int CACHE_LINE_SIZE;
    static final int ARRAY_TLC_SIZE = Configuration.ARRAY_TLC_SIZE.get(8192).intValue();
    static final ThreadLocal<byte[]> ARRAY_TLC_BYTE = ThreadLocal.withInitial(() -> {
        return new byte[ARRAY_TLC_SIZE];
    });
    static final ThreadLocal<char[]> ARRAY_TLC_CHAR = ThreadLocal.withInitial(() -> {
        return new char[ARRAY_TLC_SIZE];
    });
    static final Unsafe UNSAFE;
    static final ByteOrder NATIVE_ORDER;
    private static final Charset UTF16;
    static final Class<? extends ByteBuffer> BUFFER_BYTE;
    static final Class<? extends ShortBuffer> BUFFER_SHORT;
    static final Class<? extends CharBuffer> BUFFER_CHAR;
    static final Class<? extends IntBuffer> BUFFER_INT;
    static final Class<? extends LongBuffer> BUFFER_LONG;
    static final Class<? extends FloatBuffer> BUFFER_FLOAT;
    static final Class<? extends DoubleBuffer> BUFFER_DOUBLE;
    private static final long MARK;
    private static final long POSITION;
    private static final long LIMIT;
    private static final long CAPACITY;
    private static final long ADDRESS;
    private static final long PARENT_BYTE;
    private static final long PARENT_SHORT;
    private static final long PARENT_CHAR;
    private static final long PARENT_INT;
    private static final long PARENT_LONG;
    private static final long PARENT_FLOAT;
    private static final long PARENT_DOUBLE;
    private static final int FILL_PATTERN_32;
    private static final long FILL_PATTERN_64;
    private static final int MAGIC_CAPACITY = 219540062;
    private static final int MAGIC_POSITION = 16435934;

    /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryUtil$MemoryAllocationReport.class */
    public interface MemoryAllocationReport {

        /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryUtil$MemoryAllocationReport$Aggregate.class */
        public enum Aggregate {
            ALL,
            GROUP_BY_METHOD,
            GROUP_BY_STACKTRACE
        }

        void invoke(long j, long j2, long j3, String str, StackTraceElement... stackTraceElementArr);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryUtil$MemoryAllocator.class */
    public interface MemoryAllocator {
        long getMalloc();

        long getCalloc();

        long getRealloc();

        long getFree();

        long getAlignedAlloc();

        long getAlignedFree();

        long malloc(long j);

        long calloc(long j, long j2);

        long realloc(long j, long j2);

        void free(long j);

        long aligned_alloc(long j, long j2);

        void aligned_free(long j);
    }

    public static native <T> T memGlobalRefToObject(long j);

    static {
        ByteOrder nativeOrder = ByteOrder.nativeOrder();
        NATIVE_ORDER = nativeOrder;
        UTF16 = nativeOrder == ByteOrder.LITTLE_ENDIAN ? StandardCharsets.UTF_16LE : StandardCharsets.UTF_16BE;
        Library.initialize();
        ByteBuffer order = ByteBuffer.allocateDirect(0).order(NATIVE_ORDER);
        BUFFER_BYTE = order.getClass();
        BUFFER_SHORT = order.asShortBuffer().getClass();
        BUFFER_CHAR = order.asCharBuffer().getClass();
        BUFFER_INT = order.asIntBuffer().getClass();
        BUFFER_LONG = order.asLongBuffer().getClass();
        BUFFER_FLOAT = order.asFloatBuffer().getClass();
        BUFFER_DOUBLE = order.asDoubleBuffer().getClass();
        UNSAFE = getUnsafeInstance();
        try {
            MARK = getMarkOffset();
            POSITION = getPositionOffset();
            LIMIT = getLimitOffset();
            CAPACITY = getCapacityOffset();
            ADDRESS = getAddressOffset();
            PARENT_BYTE = getFieldOffsetObject(order.duplicate().order(order.order()), order);
            PARENT_SHORT = getFieldOffsetObject(order.asShortBuffer(), order);
            PARENT_CHAR = getFieldOffsetObject(order.asCharBuffer(), order);
            PARENT_INT = getFieldOffsetObject(order.asIntBuffer(), order);
            PARENT_LONG = getFieldOffsetObject(order.asLongBuffer(), order);
            PARENT_FLOAT = getFieldOffsetObject(order.asFloatBuffer(), order);
            PARENT_DOUBLE = getFieldOffsetObject(order.asDoubleBuffer(), order);
            PAGE_SIZE = UNSAFE.pageSize();
            CACHE_LINE_SIZE = 64;
            FILL_PATTERN_32 = Integer.divideUnsigned(-1, 255);
            FILL_PATTERN_64 = Long.divideUnsigned(-1L, 255L);
        } catch (Throwable th) {
            throw new UnsupportedOperationException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryUtil$LazyInit.class */
    public static final class LazyInit {
        static final MemoryAllocator ALLOCATOR_IMPL;
        static final MemoryAllocator ALLOCATOR;

        private LazyInit() {
        }

        static {
            boolean booleanValue = Configuration.DEBUG_MEMORY_ALLOCATOR.get(Boolean.FALSE).booleanValue();
            ALLOCATOR_IMPL = MemoryManage.getInstance();
            ALLOCATOR = booleanValue ? new MemoryManage.DebugAllocator(ALLOCATOR_IMPL) : ALLOCATOR_IMPL;
            APIUtil.apiLog("MemoryUtil allocator: " + ALLOCATOR.getClass().getSimpleName());
            if (booleanValue && !Configuration.DEBUG_MEMORY_ALLOCATOR_FAST.get(Boolean.FALSE).booleanValue()) {
                APIUtil.apiLogMore("Reminder: enable Configuration.DEBUG_MEMORY_ALLOCATOR_FAST for low overhead allocation tracking.");
            }
        }
    }

    private MemoryUtil() {
    }

    public static MemoryAllocator getAllocator() {
        return getAllocator(false);
    }

    public static MemoryAllocator getAllocator(boolean z) {
        return z ? LazyInit.ALLOCATOR : LazyInit.ALLOCATOR_IMPL;
    }

    public static long nmemAlloc(long j) {
        return LazyInit.ALLOCATOR.malloc(j);
    }

    public static long nmemAllocChecked(long j) {
        long nmemAlloc = nmemAlloc(j != 0 ? j : 1L);
        if (Checks.CHECKS && nmemAlloc == 0) {
            throw new OutOfMemoryError();
        }
        return nmemAlloc;
    }

    private static long getAllocationSize(int i, int i2) {
        return APIUtil.apiCheckAllocation(i, Integer.toUnsignedLong(i) << i2, Pointer.BITS64 ? Long.MAX_VALUE : 4294967295L);
    }

    public static ByteBuffer memAlloc(int i) {
        return ((ByteBuffer) wrap(BUFFER_BYTE, nmemAllocChecked(i), i)).order(NATIVE_ORDER);
    }

    public static ShortBuffer memAllocShort(int i) {
        return (ShortBuffer) wrap(BUFFER_SHORT, nmemAllocChecked(getAllocationSize(i, 1)), i);
    }

    public static IntBuffer memAllocInt(int i) {
        return (IntBuffer) wrap(BUFFER_INT, nmemAllocChecked(getAllocationSize(i, 2)), i);
    }

    public static FloatBuffer memAllocFloat(int i) {
        return (FloatBuffer) wrap(BUFFER_FLOAT, nmemAllocChecked(getAllocationSize(i, 2)), i);
    }

    public static LongBuffer memAllocLong(int i) {
        return (LongBuffer) wrap(BUFFER_LONG, nmemAllocChecked(getAllocationSize(i, 3)), i);
    }

    public static CLongBuffer memAllocCLong(int i) {
        return CLongBuffer.create(nmemAllocChecked(getAllocationSize(i, Pointer.CLONG_SHIFT)), i);
    }

    public static DoubleBuffer memAllocDouble(int i) {
        return (DoubleBuffer) wrap(BUFFER_DOUBLE, nmemAllocChecked(getAllocationSize(i, 3)), i);
    }

    public static PointerBuffer memAllocPointer(int i) {
        return PointerBuffer.create(nmemAllocChecked(getAllocationSize(i, Pointer.POINTER_SHIFT)), i);
    }

    public static void nmemFree(long j) {
        LazyInit.ALLOCATOR.free(j);
    }

    public static void memFree(Buffer buffer) {
        if (buffer != null) {
            nmemFree(UNSAFE.getLong(buffer, ADDRESS));
        }
    }

    public static void memFree(CustomBuffer<?> customBuffer) {
        if (customBuffer != null) {
            nmemFree(customBuffer.address);
        }
    }

    public static long nmemCalloc(long j, long j2) {
        return LazyInit.ALLOCATOR.calloc(j, j2);
    }

    public static long nmemCallocChecked(long j, long j2) {
        if (j == 0 || j2 == 0) {
            j = 1;
            j2 = 1;
        }
        long nmemCalloc = nmemCalloc(j, j2);
        if (Checks.CHECKS && nmemCalloc == 0) {
            throw new OutOfMemoryError();
        }
        return nmemCalloc;
    }

    public static ByteBuffer memCalloc(int i, int i2) {
        return ((ByteBuffer) wrap(BUFFER_BYTE, nmemCallocChecked(i, i2), i * i2)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memCalloc(int i) {
        return ((ByteBuffer) wrap(BUFFER_BYTE, nmemCallocChecked(i, 1L), i)).order(NATIVE_ORDER);
    }

    public static ShortBuffer memCallocShort(int i) {
        return (ShortBuffer) wrap(BUFFER_SHORT, nmemCallocChecked(i, 2L), i);
    }

    public static IntBuffer memCallocInt(int i) {
        return (IntBuffer) wrap(BUFFER_INT, nmemCallocChecked(i, 4L), i);
    }

    public static FloatBuffer memCallocFloat(int i) {
        return (FloatBuffer) wrap(BUFFER_FLOAT, nmemCallocChecked(i, 4L), i);
    }

    public static LongBuffer memCallocLong(int i) {
        return (LongBuffer) wrap(BUFFER_LONG, nmemCallocChecked(i, 8L), i);
    }

    public static CLongBuffer memCallocCLong(int i) {
        return CLongBuffer.create(nmemCallocChecked(i, Pointer.CLONG_SIZE), i);
    }

    public static DoubleBuffer memCallocDouble(int i) {
        return (DoubleBuffer) wrap(BUFFER_DOUBLE, nmemCallocChecked(i, 8L), i);
    }

    public static PointerBuffer memCallocPointer(int i) {
        return PointerBuffer.create(nmemCallocChecked(i, Pointer.POINTER_SIZE), i);
    }

    public static long nmemRealloc(long j, long j2) {
        return LazyInit.ALLOCATOR.realloc(j, j2);
    }

    public static long nmemReallocChecked(long j, long j2) {
        long nmemRealloc = nmemRealloc(j, j2 != 0 ? j2 : 1L);
        if (Checks.CHECKS && nmemRealloc == 0) {
            throw new OutOfMemoryError();
        }
        return nmemRealloc;
    }

    private static <T extends Buffer> T realloc(T t, T t2, int i) {
        if (t != null) {
            t2.position(Math.min(t.position(), i));
        }
        return t2;
    }

    public static ByteBuffer memRealloc(ByteBuffer byteBuffer, int i) {
        return (ByteBuffer) realloc(byteBuffer, memByteBuffer(nmemReallocChecked(byteBuffer == null ? 0L : UNSAFE.getLong(byteBuffer, ADDRESS), i), i), i);
    }

    public static ShortBuffer memRealloc(ShortBuffer shortBuffer, int i) {
        return (ShortBuffer) realloc(shortBuffer, memShortBuffer(nmemReallocChecked(shortBuffer == null ? 0L : UNSAFE.getLong(shortBuffer, ADDRESS), getAllocationSize(i, 1)), i), i);
    }

    public static IntBuffer memRealloc(IntBuffer intBuffer, int i) {
        return (IntBuffer) realloc(intBuffer, memIntBuffer(nmemReallocChecked(intBuffer == null ? 0L : UNSAFE.getLong(intBuffer, ADDRESS), getAllocationSize(i, 2)), i), i);
    }

    public static LongBuffer memRealloc(LongBuffer longBuffer, int i) {
        return (LongBuffer) realloc(longBuffer, memLongBuffer(nmemReallocChecked(longBuffer == null ? 0L : UNSAFE.getLong(longBuffer, ADDRESS), getAllocationSize(i, 3)), i), i);
    }

    public static CLongBuffer memRealloc(CLongBuffer cLongBuffer, int i) {
        CLongBuffer memCLongBuffer = memCLongBuffer(nmemReallocChecked(cLongBuffer == null ? 0L : cLongBuffer.address, getAllocationSize(i, Pointer.CLONG_SIZE)), i);
        if (cLongBuffer != null) {
            memCLongBuffer.position(Math.min(cLongBuffer.position(), i));
        }
        return memCLongBuffer;
    }

    public static FloatBuffer memRealloc(FloatBuffer floatBuffer, int i) {
        return (FloatBuffer) realloc(floatBuffer, memFloatBuffer(nmemReallocChecked(floatBuffer == null ? 0L : UNSAFE.getLong(floatBuffer, ADDRESS), getAllocationSize(i, 2)), i), i);
    }

    public static DoubleBuffer memRealloc(DoubleBuffer doubleBuffer, int i) {
        return (DoubleBuffer) realloc(doubleBuffer, memDoubleBuffer(nmemReallocChecked(doubleBuffer == null ? 0L : UNSAFE.getLong(doubleBuffer, ADDRESS), getAllocationSize(i, 3)), i), i);
    }

    public static PointerBuffer memRealloc(PointerBuffer pointerBuffer, int i) {
        PointerBuffer memPointerBuffer = memPointerBuffer(nmemReallocChecked(pointerBuffer == null ? 0L : pointerBuffer.address, getAllocationSize(i, Pointer.POINTER_SHIFT)), i);
        if (pointerBuffer != null) {
            memPointerBuffer.position(Math.min(pointerBuffer.position(), i));
        }
        return memPointerBuffer;
    }

    public static long nmemAlignedAlloc(long j, long j2) {
        return LazyInit.ALLOCATOR.aligned_alloc(j, j2);
    }

    public static long nmemAlignedAllocChecked(long j, long j2) {
        long nmemAlignedAlloc = nmemAlignedAlloc(j, j2 != 0 ? j2 : 1L);
        if (Checks.CHECKS && nmemAlignedAlloc == 0) {
            throw new OutOfMemoryError();
        }
        return nmemAlignedAlloc;
    }

    public static ByteBuffer memAlignedAlloc(int i, int i2) {
        return ((ByteBuffer) wrap(BUFFER_BYTE, nmemAlignedAllocChecked(i, i2), i2)).order(NATIVE_ORDER);
    }

    public static void nmemAlignedFree(long j) {
        LazyInit.ALLOCATOR.aligned_free(j);
    }

    public static void memAlignedFree(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            nmemAlignedFree(UNSAFE.getLong(byteBuffer, ADDRESS));
        }
    }

    public static void memReport(MemoryAllocationReport memoryAllocationReport) {
        MemoryManage.DebugAllocator.report(memoryAllocationReport);
    }

    public static void memReport(MemoryAllocationReport memoryAllocationReport, MemoryAllocationReport.Aggregate aggregate, boolean z) {
        MemoryManage.DebugAllocator.report(memoryAllocationReport, aggregate, z);
    }

    public static long memAddress0(Buffer buffer) {
        return UNSAFE.getLong(buffer, ADDRESS);
    }

    public static long memAddress(ByteBuffer byteBuffer) {
        return byteBuffer.position() + memAddress0(byteBuffer);
    }

    public static long memAddress(ByteBuffer byteBuffer, int i) {
        Objects.requireNonNull(byteBuffer);
        return memAddress0(byteBuffer) + Integer.toUnsignedLong(i);
    }

    private static long address(int i, int i2, long j) {
        return j + ((i & 4294967295L) << i2);
    }

    public static long memAddress(ShortBuffer shortBuffer) {
        return address(shortBuffer.position(), 1, memAddress0(shortBuffer));
    }

    public static long memAddress(ShortBuffer shortBuffer, int i) {
        Objects.requireNonNull(shortBuffer);
        return address(i, 1, memAddress0(shortBuffer));
    }

    public static long memAddress(CharBuffer charBuffer) {
        return address(charBuffer.position(), 1, memAddress0(charBuffer));
    }

    public static long memAddress(CharBuffer charBuffer, int i) {
        Objects.requireNonNull(charBuffer);
        return address(i, 1, memAddress0(charBuffer));
    }

    public static long memAddress(IntBuffer intBuffer) {
        return address(intBuffer.position(), 2, memAddress0(intBuffer));
    }

    public static long memAddress(IntBuffer intBuffer, int i) {
        Objects.requireNonNull(intBuffer);
        return address(i, 2, memAddress0(intBuffer));
    }

    public static long memAddress(FloatBuffer floatBuffer) {
        return address(floatBuffer.position(), 2, memAddress0(floatBuffer));
    }

    public static long memAddress(FloatBuffer floatBuffer, int i) {
        Objects.requireNonNull(floatBuffer);
        return address(i, 2, memAddress0(floatBuffer));
    }

    public static long memAddress(LongBuffer longBuffer) {
        return address(longBuffer.position(), 3, memAddress0(longBuffer));
    }

    public static long memAddress(LongBuffer longBuffer, int i) {
        Objects.requireNonNull(longBuffer);
        return address(i, 3, memAddress0(longBuffer));
    }

    public static long memAddress(DoubleBuffer doubleBuffer) {
        return address(doubleBuffer.position(), 3, memAddress0(doubleBuffer));
    }

    public static long memAddress(DoubleBuffer doubleBuffer, int i) {
        Objects.requireNonNull(doubleBuffer);
        return address(i, 3, memAddress0(doubleBuffer));
    }

    public static long memAddress(Buffer buffer) {
        int i;
        if (buffer instanceof ByteBuffer) {
            i = 0;
        } else if ((buffer instanceof ShortBuffer) || (buffer instanceof CharBuffer)) {
            i = 1;
        } else if ((buffer instanceof IntBuffer) || (buffer instanceof FloatBuffer)) {
            i = 2;
        } else {
            i = 3;
        }
        return address(buffer.position(), i, memAddress0(buffer));
    }

    public static long memAddress(CustomBuffer<?> customBuffer) {
        return customBuffer.address();
    }

    public static long memAddress(CustomBuffer<?> customBuffer, int i) {
        return customBuffer.address(i);
    }

    public static long memAddressSafe(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return 0L;
        }
        return memAddress0(byteBuffer) + byteBuffer.position();
    }

    public static long memAddressSafe(ShortBuffer shortBuffer) {
        if (shortBuffer == null) {
            return 0L;
        }
        return address(shortBuffer.position(), 1, memAddress0(shortBuffer));
    }

    public static long memAddressSafe(CharBuffer charBuffer) {
        if (charBuffer == null) {
            return 0L;
        }
        return address(charBuffer.position(), 1, memAddress0(charBuffer));
    }

    public static long memAddressSafe(IntBuffer intBuffer) {
        if (intBuffer == null) {
            return 0L;
        }
        return address(intBuffer.position(), 2, memAddress0(intBuffer));
    }

    public static long memAddressSafe(FloatBuffer floatBuffer) {
        if (floatBuffer == null) {
            return 0L;
        }
        return address(floatBuffer.position(), 2, memAddress0(floatBuffer));
    }

    public static long memAddressSafe(LongBuffer longBuffer) {
        if (longBuffer == null) {
            return 0L;
        }
        return address(longBuffer.position(), 3, memAddress0(longBuffer));
    }

    public static long memAddressSafe(DoubleBuffer doubleBuffer) {
        if (doubleBuffer == null) {
            return 0L;
        }
        return address(doubleBuffer.position(), 3, memAddress0(doubleBuffer));
    }

    public static long memAddressSafe(Pointer pointer) {
        if (pointer == null) {
            return 0L;
        }
        return pointer.address();
    }

    public static ByteBuffer memByteBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, j, i)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, j, i)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBuffer(ShortBuffer shortBuffer) {
        if (Checks.CHECKS && 1073741823 < shortBuffer.remaining()) {
            throw new IllegalArgumentException("The source buffer range is too wide");
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, memAddress(shortBuffer), shortBuffer.remaining() << 1)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBuffer(CharBuffer charBuffer) {
        if (Checks.CHECKS && 1073741823 < charBuffer.remaining()) {
            throw new IllegalArgumentException("The source buffer range is too wide");
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, memAddress(charBuffer), charBuffer.remaining() << 1)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBuffer(IntBuffer intBuffer) {
        if (Checks.CHECKS && 536870911 < intBuffer.remaining()) {
            throw new IllegalArgumentException("The source buffer range is too wide");
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, memAddress(intBuffer), intBuffer.remaining() << 2)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBuffer(LongBuffer longBuffer) {
        if (Checks.CHECKS && 268435455 < longBuffer.remaining()) {
            throw new IllegalArgumentException("The source buffer range is too wide");
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, memAddress(longBuffer), longBuffer.remaining() << 3)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBuffer(FloatBuffer floatBuffer) {
        if (Checks.CHECKS && 536870911 < floatBuffer.remaining()) {
            throw new IllegalArgumentException("The source buffer range is too wide");
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, memAddress(floatBuffer), floatBuffer.remaining() << 2)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBuffer(DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS && 268435455 < doubleBuffer.remaining()) {
            throw new IllegalArgumentException("The source buffer range is too wide");
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, memAddress(doubleBuffer), doubleBuffer.remaining() << 3)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memByteBuffer(CustomBuffer<?> customBuffer) {
        if (Checks.CHECKS && Integer.MAX_VALUE / customBuffer.sizeof() < customBuffer.remaining()) {
            throw new IllegalArgumentException("The source buffer range is too wide");
        }
        return ((ByteBuffer) wrap(BUFFER_BYTE, memAddress(customBuffer), customBuffer.remaining() * customBuffer.sizeof())).order(NATIVE_ORDER);
    }

    public static <T extends Struct<T>> ByteBuffer memByteBuffer(T t) {
        return ((ByteBuffer) wrap(BUFFER_BYTE, t.address, t.sizeof())).order(NATIVE_ORDER);
    }

    public static ShortBuffer memShortBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return (ShortBuffer) wrap(BUFFER_SHORT, j, i);
    }

    public static ShortBuffer memShortBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return (ShortBuffer) wrap(BUFFER_SHORT, j, i);
    }

    public static CharBuffer memCharBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return (CharBuffer) wrap(BUFFER_CHAR, j, i);
    }

    public static CharBuffer memCharBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return (CharBuffer) wrap(BUFFER_CHAR, j, i);
    }

    public static IntBuffer memIntBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return (IntBuffer) wrap(BUFFER_INT, j, i);
    }

    public static IntBuffer memIntBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return (IntBuffer) wrap(BUFFER_INT, j, i);
    }

    public static LongBuffer memLongBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return (LongBuffer) wrap(BUFFER_LONG, j, i);
    }

    public static LongBuffer memLongBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return (LongBuffer) wrap(BUFFER_LONG, j, i);
    }

    public static CLongBuffer memCLongBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return CLongBuffer.create(j, i);
    }

    public static CLongBuffer memCLongBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return CLongBuffer.create(j, i);
    }

    public static FloatBuffer memFloatBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return (FloatBuffer) wrap(BUFFER_FLOAT, j, i);
    }

    public static FloatBuffer memFloatBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return (FloatBuffer) wrap(BUFFER_FLOAT, j, i);
    }

    public static DoubleBuffer memDoubleBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return (DoubleBuffer) wrap(BUFFER_DOUBLE, j, i);
    }

    public static DoubleBuffer memDoubleBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return (DoubleBuffer) wrap(BUFFER_DOUBLE, j, i);
    }

    public static PointerBuffer memPointerBuffer(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return PointerBuffer.create(j, i);
    }

    public static PointerBuffer memPointerBufferSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return PointerBuffer.create(j, i);
    }

    public static ByteBuffer memDuplicate(ByteBuffer byteBuffer) {
        try {
            ByteBuffer byteBuffer2 = (ByteBuffer) UNSAFE.allocateInstance(BUFFER_BYTE);
            UNSAFE.putLong(byteBuffer2, ADDRESS, UNSAFE.getLong(byteBuffer, ADDRESS));
            UNSAFE.putInt(byteBuffer2, MARK, UNSAFE.getInt(byteBuffer, MARK));
            UNSAFE.putInt(byteBuffer2, POSITION, UNSAFE.getInt(byteBuffer, POSITION));
            UNSAFE.putInt(byteBuffer2, LIMIT, UNSAFE.getInt(byteBuffer, LIMIT));
            UNSAFE.putInt(byteBuffer2, CAPACITY, UNSAFE.getInt(byteBuffer, CAPACITY));
            Object object = UNSAFE.getObject(byteBuffer, PARENT_BYTE);
            UNSAFE.putObject(byteBuffer2, PARENT_BYTE, object == null ? byteBuffer : object);
            return byteBuffer2.order(byteBuffer.order());
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static ShortBuffer memDuplicate(ShortBuffer shortBuffer) {
        return (ShortBuffer) duplicate(BUFFER_SHORT, shortBuffer, PARENT_SHORT);
    }

    public static CharBuffer memDuplicate(CharBuffer charBuffer) {
        return (CharBuffer) duplicate(BUFFER_CHAR, charBuffer, PARENT_CHAR);
    }

    public static IntBuffer memDuplicate(IntBuffer intBuffer) {
        return (IntBuffer) duplicate(BUFFER_INT, intBuffer, PARENT_INT);
    }

    public static LongBuffer memDuplicate(LongBuffer longBuffer) {
        return (LongBuffer) duplicate(BUFFER_LONG, longBuffer, PARENT_LONG);
    }

    public static FloatBuffer memDuplicate(FloatBuffer floatBuffer) {
        return (FloatBuffer) duplicate(BUFFER_FLOAT, floatBuffer, PARENT_FLOAT);
    }

    public static DoubleBuffer memDuplicate(DoubleBuffer doubleBuffer) {
        return (DoubleBuffer) duplicate(BUFFER_DOUBLE, doubleBuffer, PARENT_DOUBLE);
    }

    public static ByteBuffer memSlice(ByteBuffer byteBuffer) {
        return slice(byteBuffer, memAddress0(byteBuffer) + byteBuffer.position(), byteBuffer.remaining());
    }

    public static ShortBuffer memSlice(ShortBuffer shortBuffer) {
        return (ShortBuffer) slice(BUFFER_SHORT, shortBuffer, address(shortBuffer.position(), 1, memAddress0(shortBuffer)), shortBuffer.remaining(), PARENT_SHORT);
    }

    public static CharBuffer memSlice(CharBuffer charBuffer) {
        return (CharBuffer) slice(BUFFER_CHAR, charBuffer, address(charBuffer.position(), 1, memAddress0(charBuffer)), charBuffer.remaining(), PARENT_CHAR);
    }

    public static IntBuffer memSlice(IntBuffer intBuffer) {
        return (IntBuffer) slice(BUFFER_INT, intBuffer, address(intBuffer.position(), 2, memAddress0(intBuffer)), intBuffer.remaining(), PARENT_INT);
    }

    public static LongBuffer memSlice(LongBuffer longBuffer) {
        return (LongBuffer) slice(BUFFER_LONG, longBuffer, address(longBuffer.position(), 3, memAddress0(longBuffer)), longBuffer.remaining(), PARENT_LONG);
    }

    public static FloatBuffer memSlice(FloatBuffer floatBuffer) {
        return (FloatBuffer) slice(BUFFER_FLOAT, floatBuffer, address(floatBuffer.position(), 2, memAddress0(floatBuffer)), floatBuffer.remaining(), PARENT_FLOAT);
    }

    public static DoubleBuffer memSlice(DoubleBuffer doubleBuffer) {
        return (DoubleBuffer) slice(BUFFER_DOUBLE, doubleBuffer, address(doubleBuffer.position(), 3, memAddress0(doubleBuffer)), doubleBuffer.remaining(), PARENT_DOUBLE);
    }

    public static ByteBuffer memSlice(ByteBuffer byteBuffer, int i, int i2) {
        int position = byteBuffer.position() + i;
        if (i < 0 || byteBuffer.limit() < position) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || byteBuffer.capacity() - position < i2) {
            throw new IllegalArgumentException();
        }
        return slice(byteBuffer, memAddress0(byteBuffer) + position, i2);
    }

    public static ShortBuffer memSlice(ShortBuffer shortBuffer, int i, int i2) {
        int position = shortBuffer.position() + i;
        if (i < 0 || shortBuffer.limit() < position) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || shortBuffer.capacity() - position < i2) {
            throw new IllegalArgumentException();
        }
        return (ShortBuffer) slice(BUFFER_SHORT, shortBuffer, address(position, 1, memAddress0(shortBuffer)), i2, PARENT_SHORT);
    }

    public static CharBuffer memSlice(CharBuffer charBuffer, int i, int i2) {
        int position = charBuffer.position() + i;
        if (i < 0 || charBuffer.limit() < position) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || charBuffer.capacity() - position < i2) {
            throw new IllegalArgumentException();
        }
        return (CharBuffer) slice(BUFFER_CHAR, charBuffer, address(position, 1, memAddress0(charBuffer)), i2, PARENT_CHAR);
    }

    public static IntBuffer memSlice(IntBuffer intBuffer, int i, int i2) {
        int position = intBuffer.position() + i;
        if (i < 0 || intBuffer.limit() < position) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || intBuffer.capacity() - position < i2) {
            throw new IllegalArgumentException();
        }
        return (IntBuffer) slice(BUFFER_INT, intBuffer, address(position, 2, memAddress0(intBuffer)), i2, PARENT_INT);
    }

    public static LongBuffer memSlice(LongBuffer longBuffer, int i, int i2) {
        int position = longBuffer.position() + i;
        if (i < 0 || longBuffer.limit() < position) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || longBuffer.capacity() - position < i2) {
            throw new IllegalArgumentException();
        }
        return (LongBuffer) slice(BUFFER_LONG, longBuffer, address(position, 3, memAddress0(longBuffer)), i2, PARENT_LONG);
    }

    public static FloatBuffer memSlice(FloatBuffer floatBuffer, int i, int i2) {
        int position = floatBuffer.position() + i;
        if (i < 0 || floatBuffer.limit() < position) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || floatBuffer.capacity() - position < i2) {
            throw new IllegalArgumentException();
        }
        return (FloatBuffer) slice(BUFFER_FLOAT, floatBuffer, address(position, 2, memAddress0(floatBuffer)), i2, PARENT_FLOAT);
    }

    public static DoubleBuffer memSlice(DoubleBuffer doubleBuffer, int i, int i2) {
        int position = doubleBuffer.position() + i;
        if (i < 0 || doubleBuffer.limit() < position) {
            throw new IllegalArgumentException();
        }
        if (i2 < 0 || doubleBuffer.capacity() - position < i2) {
            throw new IllegalArgumentException();
        }
        return (DoubleBuffer) slice(BUFFER_DOUBLE, doubleBuffer, address(position, 3, memAddress0(doubleBuffer)), i2, PARENT_DOUBLE);
    }

    public static <T extends CustomBuffer<T>> T memSlice(T t, int i, int i2) {
        return (T) t.slice(i, i2);
    }

    public static void memSet(ByteBuffer byteBuffer, int i) {
        memSet(memAddress(byteBuffer), i, byteBuffer.remaining());
    }

    public static void memSet(ShortBuffer shortBuffer, int i) {
        memSet(memAddress(shortBuffer), i, APIUtil.apiGetBytes(shortBuffer.remaining(), 1));
    }

    public static void memSet(CharBuffer charBuffer, int i) {
        memSet(memAddress(charBuffer), i, APIUtil.apiGetBytes(charBuffer.remaining(), 1));
    }

    public static void memSet(IntBuffer intBuffer, int i) {
        memSet(memAddress(intBuffer), i, APIUtil.apiGetBytes(intBuffer.remaining(), 2));
    }

    public static void memSet(LongBuffer longBuffer, int i) {
        memSet(memAddress(longBuffer), i, APIUtil.apiGetBytes(longBuffer.remaining(), 3));
    }

    public static void memSet(FloatBuffer floatBuffer, int i) {
        memSet(memAddress(floatBuffer), i, APIUtil.apiGetBytes(floatBuffer.remaining(), 2));
    }

    public static void memSet(DoubleBuffer doubleBuffer, int i) {
        memSet(memAddress(doubleBuffer), i, APIUtil.apiGetBytes(doubleBuffer.remaining(), 3));
    }

    public static <T extends CustomBuffer<T>> void memSet(T t, int i) {
        memSet(memAddress((CustomBuffer<?>) t), i, Integer.toUnsignedLong(t.remaining()) * t.sizeof());
    }

    public static <T extends Struct<T>> void memSet(T t, int i) {
        memSet(t.address, i, t.sizeof());
    }

    public static void memCopy(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer2, byteBuffer.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress(byteBuffer), memAddress(byteBuffer2), byteBuffer.remaining());
    }

    public static void memCopy(ShortBuffer shortBuffer, ShortBuffer shortBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) shortBuffer2, shortBuffer.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress(shortBuffer), memAddress(shortBuffer2), APIUtil.apiGetBytes(shortBuffer.remaining(), 1));
    }

    public static void memCopy(CharBuffer charBuffer, CharBuffer charBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) charBuffer2, charBuffer.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress(charBuffer), memAddress(charBuffer2), APIUtil.apiGetBytes(charBuffer.remaining(), 1));
    }

    public static void memCopy(IntBuffer intBuffer, IntBuffer intBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer2, intBuffer.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress(intBuffer), memAddress(intBuffer2), APIUtil.apiGetBytes(intBuffer.remaining(), 2));
    }

    public static void memCopy(LongBuffer longBuffer, LongBuffer longBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer2, longBuffer.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress(longBuffer), memAddress(longBuffer2), APIUtil.apiGetBytes(longBuffer.remaining(), 3));
    }

    public static void memCopy(FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer2, floatBuffer.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress(floatBuffer), memAddress(floatBuffer2), APIUtil.apiGetBytes(floatBuffer.remaining(), 2));
    }

    public static void memCopy(DoubleBuffer doubleBuffer, DoubleBuffer doubleBuffer2) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer2, doubleBuffer.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress(doubleBuffer), memAddress(doubleBuffer2), APIUtil.apiGetBytes(doubleBuffer.remaining(), 3));
    }

    public static <T extends CustomBuffer<T>> void memCopy(T t, T t2) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) t2, t.remaining());
        }
        MultiReleaseMemCopy.copy(memAddress((CustomBuffer<?>) t), memAddress((CustomBuffer<?>) t2), Integer.toUnsignedLong(t.remaining()) * t.sizeof());
    }

    public static <T extends Struct<T>> void memCopy(T t, T t2) {
        MultiReleaseMemCopy.copy(t.address, t2.address, t.sizeof());
    }

    public static void memSet(long j, int i, long j2) {
        if (Checks.DEBUG && (j == 0 || j2 < 0)) {
            throw new IllegalArgumentException();
        }
        if (j2 < 256) {
            int i2 = (int) j;
            if (Pointer.BITS64) {
                if ((i2 & 7) == 0) {
                    memSet64(j, i, ((int) j2) & 255);
                    return;
                }
            } else if ((i2 & 3) == 0) {
                memSet32(i2, i, ((int) j2) & 255);
                return;
            }
        }
        LibCString.nmemset(j, i, j2);
    }

    private static void memSet64(long j, int i, int i2) {
        int i3 = i2 & (-8);
        long j2 = (i & 255) * FILL_PATTERN_64;
        for (int i4 = 0; i4 < i3; i4 += 8) {
            UNSAFE.putLong((Object) null, j + i4, j2);
        }
        byte b2 = (byte) i;
        for (int i5 = i3; i5 < i2; i5++) {
            UNSAFE.putByte((Object) null, j + i5, b2);
        }
    }

    private static void memSet32(int i, int i2, int i3) {
        int i4 = i3 & (-4);
        int i5 = (i2 & 255) * FILL_PATTERN_32;
        for (int i6 = 0; i6 < i4; i6 += 4) {
            UNSAFE.putInt((Object) null, (i + i6) & 4294967295L, i5);
        }
        byte b2 = (byte) i2;
        for (int i7 = i4; i7 < i3; i7++) {
            UNSAFE.putByte((Object) null, (i + i7) & 4294967295L, b2);
        }
    }

    public static void memCopy(long j, long j2, long j3) {
        if (Checks.DEBUG && (j == 0 || j2 == 0 || j3 < 0)) {
            throw new IllegalArgumentException();
        }
        MultiReleaseMemCopy.copy(j, j2, j3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void memCopyAligned64(long j, long j2, int i) {
        int i2 = i & (-8);
        for (int i3 = 0; i3 < i2; i3 += 8) {
            UNSAFE.putLong((Object) null, j2 + i3, UNSAFE.getLong((Object) null, j + i3));
        }
        for (int i4 = i2; i4 < i; i4++) {
            UNSAFE.putByte((Object) null, j2 + i4, UNSAFE.getByte((Object) null, j + i4));
        }
    }

    static void memCopyAligned32(int i, int i2, int i3) {
        int i4 = i3 & (-4);
        for (int i5 = 0; i5 < i4; i5 += 4) {
            UNSAFE.putInt((Object) null, (i2 + i5) & 4294967295L, UNSAFE.getInt((Object) null, (i + i5) & 4294967295L));
        }
        for (int i6 = i4; i6 < i3; i6++) {
            UNSAFE.putByte((Object) null, (i2 + i6) & 4294967295L, UNSAFE.getByte((Object) null, (i + i6) & 4294967295L));
        }
    }

    public static boolean memGetBoolean(long j) {
        return UNSAFE.getByte((Object) null, j) != 0;
    }

    public static byte memGetByte(long j) {
        return UNSAFE.getByte((Object) null, j);
    }

    public static short memGetShort(long j) {
        return UNSAFE.getShort((Object) null, j);
    }

    public static int memGetInt(long j) {
        return UNSAFE.getInt((Object) null, j);
    }

    public static long memGetLong(long j) {
        return UNSAFE.getLong((Object) null, j);
    }

    public static float memGetFloat(long j) {
        return UNSAFE.getFloat((Object) null, j);
    }

    public static double memGetDouble(long j) {
        return UNSAFE.getDouble((Object) null, j);
    }

    public static long memGetCLong(long j) {
        if (Pointer.CLONG_SIZE == 8) {
            return UNSAFE.getLong((Object) null, j);
        }
        return UNSAFE.getInt((Object) null, j);
    }

    public static long memGetAddress(long j) {
        if (Pointer.BITS64) {
            return UNSAFE.getLong((Object) null, j);
        }
        return UNSAFE.getInt((Object) null, j) & 4294967295L;
    }

    public static void memPutByte(long j, byte b2) {
        UNSAFE.putByte((Object) null, j, b2);
    }

    public static void memPutShort(long j, short s) {
        UNSAFE.putShort((Object) null, j, s);
    }

    public static void memPutInt(long j, int i) {
        UNSAFE.putInt((Object) null, j, i);
    }

    public static void memPutLong(long j, long j2) {
        UNSAFE.putLong((Object) null, j, j2);
    }

    public static void memPutFloat(long j, float f) {
        UNSAFE.putFloat((Object) null, j, f);
    }

    public static void memPutDouble(long j, double d) {
        UNSAFE.putDouble((Object) null, j, d);
    }

    public static void memPutCLong(long j, long j2) {
        if (Pointer.CLONG_SIZE == 8) {
            UNSAFE.putLong((Object) null, j, j2);
        } else {
            UNSAFE.putInt((Object) null, j, (int) j2);
        }
    }

    public static void memPutAddress(long j, long j2) {
        if (Pointer.BITS64) {
            UNSAFE.putLong((Object) null, j, j2);
        } else {
            UNSAFE.putInt((Object) null, j, (int) j2);
        }
    }

    private static int write8(long j, int i, int i2) {
        UNSAFE.putByte((Object) null, j + Integer.toUnsignedLong(i), (byte) i2);
        return i + 1;
    }

    private static int write8Safe(long j, int i, int i2, int i3) {
        if (i == i2) {
            throw new BufferOverflowException();
        }
        UNSAFE.putByte((Object) null, j + Integer.toUnsignedLong(i), (byte) i3);
        return i + 1;
    }

    private static int write16(long j, int i, char c) {
        UNSAFE.putShort((Object) null, j + Integer.toUnsignedLong(i), (short) c);
        return i + 2;
    }

    public static ByteBuffer memASCII(CharSequence charSequence) {
        return memASCII(charSequence, true);
    }

    public static ByteBuffer memASCIISafe(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return memASCII(charSequence, true);
    }

    public static ByteBuffer memASCII(CharSequence charSequence, boolean z) {
        int memLengthASCII = memLengthASCII(charSequence, z);
        long nmemAlloc = nmemAlloc(memLengthASCII);
        if (Checks.CHECKS && nmemAlloc == 0) {
            throw new OutOfMemoryError();
        }
        encodeASCIIUnsafe(charSequence, z, nmemAlloc);
        return ((ByteBuffer) wrap(BUFFER_BYTE, nmemAlloc, memLengthASCII)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memASCIISafe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return null;
        }
        return memASCII(charSequence, z);
    }

    public static int memASCII(CharSequence charSequence, boolean z, ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < memLengthASCII(charSequence, z)) {
            throw new BufferOverflowException();
        }
        return encodeASCIIUnsafe(charSequence, z, memAddress(byteBuffer));
    }

    public static int memASCII(CharSequence charSequence, boolean z, ByteBuffer byteBuffer, int i) {
        if (byteBuffer.capacity() - i < memLengthASCII(charSequence, z)) {
            throw new BufferOverflowException();
        }
        return encodeASCIIUnsafe(charSequence, z, memAddress(byteBuffer, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodeASCIIUnsafe(CharSequence charSequence, boolean z, long j) {
        int i = 0;
        int length = charSequence.length();
        while (i < length) {
            i = write8(j, i, charSequence.charAt(i));
        }
        if (z) {
            i = write8(j, i, 0);
        }
        return i;
    }

    public static int memLengthASCII(CharSequence charSequence, boolean z) {
        int length = charSequence.length() + (z ? 1 : 0);
        if (length < 0) {
            throw new BufferOverflowException();
        }
        return length;
    }

    public static ByteBuffer memUTF8(CharSequence charSequence) {
        return memUTF8(charSequence, true);
    }

    public static ByteBuffer memUTF8Safe(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return memUTF8(charSequence, true);
    }

    public static ByteBuffer memUTF8(CharSequence charSequence, boolean z) {
        int memLengthUTF8 = memLengthUTF8(charSequence, z);
        long nmemAlloc = nmemAlloc(memLengthUTF8);
        if (Checks.CHECKS && nmemAlloc == 0) {
            throw new OutOfMemoryError();
        }
        encodeUTF8Unsafe(charSequence, z, nmemAlloc);
        return ((ByteBuffer) wrap(BUFFER_BYTE, nmemAlloc, memLengthUTF8)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memUTF8Safe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return null;
        }
        return memUTF8(charSequence, z);
    }

    public static int memUTF8(CharSequence charSequence, boolean z, ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < memLengthASCII(charSequence, z)) {
            throw new BufferOverflowException();
        }
        return encodeUTF8Safe(charSequence, z, memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static int memUTF8(CharSequence charSequence, boolean z, ByteBuffer byteBuffer, int i) {
        if (byteBuffer.capacity() - i < memLengthASCII(charSequence, z)) {
            throw new BufferOverflowException();
        }
        return encodeUTF8Safe(charSequence, z, memAddress(byteBuffer, i), byteBuffer.capacity() - i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v18, types: [int] */
    public static int encodeUTF8Unsafe(CharSequence charSequence, boolean z, long j) {
        int write8;
        int write82;
        int i = 0;
        int i2 = 0;
        int length = charSequence.length();
        while (i2 < length) {
            int i3 = i2;
            i2++;
            char charAt = charSequence.charAt(i3);
            if (charAt < 128) {
                i = write8(j, i, charAt);
            } else {
                char c = charAt;
                if (charAt < 2048) {
                    write82 = write8(j, i, 192 | (c >> 6));
                } else {
                    if (!Character.isHighSurrogate(charAt)) {
                        write8 = write8(j, i, 224 | (c >> '\f'));
                    } else {
                        i2++;
                        c = Character.toCodePoint(charAt, charSequence.charAt(i2));
                        write8 = write8(j, write8(j, i, 240 | (c >> 18)), 128 | ((c >> '\f') & 63));
                    }
                    write82 = write8(j, write8, 128 | ((c >> 6) & 63));
                }
                i = write8(j, write82, 128 | (c & '?'));
            }
        }
        if (z) {
            i = write8(j, i, 0);
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v19, types: [int] */
    static int encodeUTF8Safe(CharSequence charSequence, boolean z, long j, int i) {
        int write8Safe;
        int write8Safe2;
        char charAt;
        int i2 = 0;
        int i3 = 0;
        int length = charSequence.length();
        while (i3 < length && 128 > (charAt = charSequence.charAt(i3))) {
            i2 = write8(j, i2, charAt);
            i3++;
        }
        while (i3 < length) {
            int i4 = i3;
            i3++;
            char charAt2 = charSequence.charAt(i4);
            if (charAt2 < 128) {
                i2 = write8Safe(j, i2, i, charAt2);
            } else {
                char c = charAt2;
                if (charAt2 < 2048) {
                    write8Safe2 = write8Safe(j, i2, i, 192 | (c >> 6));
                } else {
                    if (!Character.isHighSurrogate(charAt2)) {
                        write8Safe = write8Safe(j, i2, i, 224 | (c >> '\f'));
                    } else {
                        i3++;
                        c = Character.toCodePoint(charAt2, charSequence.charAt(i3));
                        write8Safe = write8Safe(j, write8Safe(j, i2, i, 240 | (c >> 18)), i, 128 | ((c >> '\f') & 63));
                    }
                    write8Safe2 = write8Safe(j, write8Safe, i, 128 | ((c >> 6) & 63));
                }
                i2 = write8Safe(j, write8Safe2, i, 128 | (c & '?'));
            }
        }
        if (z) {
            i2 = write8Safe(j, i2, i, 0);
        }
        return i2;
    }

    public static int memLengthUTF8(CharSequence charSequence, boolean z) {
        int length = charSequence.length();
        int i = length + (z ? 1 : 0);
        int i2 = 0;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt >= 128) {
                if (charAt < 2048) {
                    i += (127 - charAt) >>> 31;
                } else {
                    i += 2;
                    if (Character.isHighSurrogate(charAt)) {
                        i2++;
                    }
                }
                if (i < 0) {
                    throw new BufferOverflowException();
                }
            }
            i2++;
        }
        if (i < 0) {
            throw new BufferOverflowException();
        }
        return i;
    }

    public static ByteBuffer memUTF16(CharSequence charSequence) {
        return memUTF16(charSequence, true);
    }

    public static ByteBuffer memUTF16Safe(CharSequence charSequence) {
        if (charSequence == null) {
            return null;
        }
        return memUTF16(charSequence, true);
    }

    public static ByteBuffer memUTF16(CharSequence charSequence, boolean z) {
        int memLengthUTF16 = memLengthUTF16(charSequence, z);
        long nmemAlloc = nmemAlloc(memLengthUTF16);
        if (Checks.CHECKS && nmemAlloc == 0) {
            throw new OutOfMemoryError();
        }
        encodeUTF16Unsafe(charSequence, z, nmemAlloc);
        return ((ByteBuffer) wrap(BUFFER_BYTE, nmemAlloc, memLengthUTF16)).order(NATIVE_ORDER);
    }

    public static ByteBuffer memUTF16Safe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return null;
        }
        return memUTF16(charSequence, z);
    }

    public static int memUTF16(CharSequence charSequence, boolean z, ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < memLengthUTF16(charSequence, z)) {
            throw new BufferOverflowException();
        }
        return encodeUTF16Unsafe(charSequence, z, memAddress(byteBuffer));
    }

    public static int memUTF16(CharSequence charSequence, boolean z, ByteBuffer byteBuffer, int i) {
        if (byteBuffer.capacity() - i < memLengthUTF16(charSequence, z)) {
            throw new BufferOverflowException();
        }
        return encodeUTF16Unsafe(charSequence, z, memAddress(byteBuffer, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int encodeUTF16Unsafe(CharSequence charSequence, boolean z, long j) {
        int i = 0;
        int i2 = 0;
        int length = charSequence.length();
        while (i2 < length) {
            int i3 = i2;
            i2++;
            i = write16(j, i, charSequence.charAt(i3));
        }
        if (z) {
            i = write16(j, i, (char) 0);
        }
        return i;
    }

    public static int memLengthUTF16(CharSequence charSequence, boolean z) {
        int length = charSequence.length() + (z ? 1 : 0);
        if (length < 0 || 1073741823 < length) {
            throw new BufferOverflowException();
        }
        return length << 1;
    }

    private static int memLengthNT1(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        if (Pointer.BITS64) {
            return strlen64NT1(j, i);
        }
        return strlen32NT1(j, i);
    }

    private static int strlen64NT1(long j, int i) {
        int i2 = 0;
        if (8 <= i) {
            int i3 = ((int) j) & 7;
            if (i3 != 0) {
                int i4 = 8 - i3;
                while (i2 < i4) {
                    if (UNSAFE.getByte((Object) null, j + i2) != 0) {
                        i2++;
                    } else {
                        return i2;
                    }
                }
            }
            while (i2 <= i - 8 && !MathUtil.mathHasZeroByte(UNSAFE.getLong((Object) null, j + i2))) {
                i2 += 8;
            }
        }
        while (i2 < i && UNSAFE.getByte((Object) null, j + i2) != 0) {
            i2++;
        }
        return i2;
    }

    private static int strlen32NT1(long j, int i) {
        int i2 = 0;
        if (4 <= i) {
            int i3 = ((int) j) & 3;
            if (i3 != 0) {
                int i4 = 4 - i3;
                while (i2 < i4) {
                    if (UNSAFE.getByte((Object) null, j + i2) != 0) {
                        i2++;
                    } else {
                        return i2;
                    }
                }
            }
            while (i2 <= i - 4 && !MathUtil.mathHasZeroByte(UNSAFE.getInt((Object) null, j + i2))) {
                i2 += 4;
            }
        }
        while (i2 < i && UNSAFE.getByte((Object) null, j + i2) != 0) {
            i2++;
        }
        return i2;
    }

    public static int memLengthNT1(ByteBuffer byteBuffer) {
        return memLengthNT1(memAddress(byteBuffer), byteBuffer.remaining());
    }

    private static int memLengthNT2(long j, int i) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        if (Pointer.BITS64) {
            return strlen64NT2(j, i);
        }
        return strlen32NT2((int) j, i);
    }

    private static int strlen64NT2(long j, int i) {
        int i2 = 0;
        if (8 <= i) {
            int i3 = ((int) j) & 7;
            if (i3 != 0) {
                int i4 = 8 - i3;
                while (i2 < i4) {
                    if (UNSAFE.getShort((Object) null, j + i2) != 0) {
                        i2 += 2;
                    } else {
                        return i2;
                    }
                }
            }
            while (i2 <= i - 8 && !MathUtil.mathHasZeroShort(UNSAFE.getLong((Object) null, j + i2))) {
                i2 += 8;
            }
        }
        while (i2 < i && UNSAFE.getShort((Object) null, j + i2) != 0) {
            i2 += 2;
        }
        return i2;
    }

    private static int strlen32NT2(long j, int i) {
        int i2 = 0;
        if (4 <= i) {
            int i3 = ((int) j) & 3;
            if (i3 != 0) {
                int i4 = 4 - i3;
                while (i2 < i4) {
                    if (UNSAFE.getShort((Object) null, j + i2) != 0) {
                        i2 += 2;
                    } else {
                        return i2;
                    }
                }
            }
            while (i2 <= i - 4 && !MathUtil.mathHasZeroShort(UNSAFE.getInt((Object) null, j + i2))) {
                i2 += 4;
            }
        }
        while (i2 < i && UNSAFE.getShort((Object) null, j + i2) != 0) {
            i2 += 2;
        }
        return i2;
    }

    public static int memLengthNT2(ByteBuffer byteBuffer) {
        return memLengthNT2(memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static ByteBuffer memByteBufferNT1(long j) {
        return memByteBuffer(j, memLengthNT1(j, Integer.MAX_VALUE));
    }

    public static ByteBuffer memByteBufferNT1(long j, int i) {
        return memByteBuffer(j, memLengthNT1(j, i));
    }

    public static ByteBuffer memByteBufferNT1Safe(long j) {
        if (j == 0) {
            return null;
        }
        return memByteBuffer(j, memLengthNT1(j, Integer.MAX_VALUE));
    }

    public static ByteBuffer memByteBufferNT1Safe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return memByteBuffer(j, memLengthNT1(j, i));
    }

    public static ByteBuffer memByteBufferNT2(long j) {
        return memByteBufferNT2(j, 2147483646);
    }

    public static ByteBuffer memByteBufferNT2(long j, int i) {
        if (Checks.DEBUG && (i & 1) != 0) {
            throw new IllegalArgumentException("The maximum length must be an even number.");
        }
        return memByteBuffer(j, memLengthNT2(j, i));
    }

    public static ByteBuffer memByteBufferNT2Safe(long j) {
        if (j == 0) {
            return null;
        }
        return memByteBufferNT2(j, 2147483646);
    }

    public static ByteBuffer memByteBufferNT2Safe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return memByteBufferNT2(j, i);
    }

    public static String memASCII(long j) {
        return memASCII(j, memLengthNT1(j, Integer.MAX_VALUE));
    }

    public static String memASCII(long j, int i) {
        if (i <= 0) {
            return "";
        }
        byte[] bArr = i <= ARRAY_TLC_SIZE ? ARRAY_TLC_BYTE.get() : new byte[i];
        memByteBuffer(j, i).get(bArr, 0, i);
        return new String(bArr, 0, 0, i);
    }

    public static String memASCII(ByteBuffer byteBuffer) {
        return memASCII(memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static String memASCIISafe(long j) {
        if (j == 0) {
            return null;
        }
        return memASCII(j, memLengthNT1(j, Integer.MAX_VALUE));
    }

    public static String memASCIISafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return memASCII(j, i);
    }

    public static String memASCIISafe(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        return memASCII(memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static String memASCII(ByteBuffer byteBuffer, int i) {
        return memASCII(memAddress(byteBuffer), i);
    }

    public static String memASCII(ByteBuffer byteBuffer, int i, int i2) {
        return memASCII(memAddress(byteBuffer, i2), i);
    }

    public static String memUTF8(long j) {
        return MultiReleaseTextDecoding.decodeUTF8(j, memLengthNT1(j, Integer.MAX_VALUE));
    }

    public static String memUTF8(long j, int i) {
        return MultiReleaseTextDecoding.decodeUTF8(j, i);
    }

    public static String memUTF8(ByteBuffer byteBuffer) {
        return MultiReleaseTextDecoding.decodeUTF8(memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static String memUTF8Safe(long j) {
        if (j == 0) {
            return null;
        }
        return MultiReleaseTextDecoding.decodeUTF8(j, memLengthNT1(j, Integer.MAX_VALUE));
    }

    public static String memUTF8Safe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return MultiReleaseTextDecoding.decodeUTF8(j, i);
    }

    public static String memUTF8Safe(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        return MultiReleaseTextDecoding.decodeUTF8(memAddress(byteBuffer), byteBuffer.remaining());
    }

    public static String memUTF8(ByteBuffer byteBuffer, int i) {
        return MultiReleaseTextDecoding.decodeUTF8(memAddress(byteBuffer), i);
    }

    public static String memUTF8(ByteBuffer byteBuffer, int i, int i2) {
        return MultiReleaseTextDecoding.decodeUTF8(memAddress(byteBuffer, i2), i);
    }

    public static String memUTF16(long j) {
        return memUTF16(j, memLengthNT2(j, 2147483646) >> 1);
    }

    public static String memUTF16(long j, int i) {
        if (i <= 0) {
            return "";
        }
        if (Checks.DEBUG) {
            int i2 = i << 1;
            byte[] bArr = i2 <= ARRAY_TLC_SIZE ? ARRAY_TLC_BYTE.get() : new byte[i2];
            memByteBuffer(j, i2).get(bArr, 0, i2);
            return new String(bArr, 0, i2, UTF16);
        }
        char[] cArr = i <= ARRAY_TLC_SIZE ? ARRAY_TLC_CHAR.get() : new char[i];
        memCharBuffer(j, i).get(cArr, 0, i);
        return new String(cArr, 0, i);
    }

    public static String memUTF16(ByteBuffer byteBuffer) {
        return memUTF16(memAddress(byteBuffer), byteBuffer.remaining() >> 1);
    }

    public static String memUTF16Safe(long j) {
        if (j == 0) {
            return null;
        }
        return memUTF16(j, memLengthNT2(j, 2147483646) >> 1);
    }

    public static String memUTF16Safe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return memUTF16(j, i);
    }

    public static String memUTF16Safe(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return null;
        }
        return memUTF16(memAddress(byteBuffer), byteBuffer.remaining() >> 1);
    }

    public static String memUTF16(ByteBuffer byteBuffer, int i) {
        return memUTF16(memAddress(byteBuffer), i);
    }

    public static String memUTF16(ByteBuffer byteBuffer, int i, int i2) {
        return memUTF16(memAddress(byteBuffer, i2), i);
    }

    private static Unsafe getUnsafeInstance() {
        Field[] declaredFields = Unsafe.class.getDeclaredFields();
        int length = declaredFields.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Field field = declaredFields[i];
            if (field.getType().equals(Unsafe.class)) {
                int modifiers = field.getModifiers();
                if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
                    try {
                        field.setAccessible(true);
                        return (Unsafe) field.get(null);
                    } catch (Exception unused) {
                    }
                }
            }
            i++;
        }
        throw new UnsupportedOperationException("LWJGL requires sun.misc.Unsafe to be available.");
    }

    private static long getFieldOffset(Class<?> cls, Class<?> cls2, LongPredicate longPredicate) {
        Class<?> cls3 = cls;
        while (true) {
            Class<?> cls4 = cls3;
            if (cls4 != Object.class) {
                for (Field field : cls4.getDeclaredFields()) {
                    if (field.getType().isAssignableFrom(cls2) && !Modifier.isStatic(field.getModifiers()) && !field.isSynthetic()) {
                        long objectFieldOffset = UNSAFE.objectFieldOffset(field);
                        if (longPredicate.test(objectFieldOffset)) {
                            return objectFieldOffset;
                        }
                    }
                }
                cls3 = cls4.getSuperclass();
            } else {
                throw new UnsupportedOperationException("Failed to find field offset in class.");
            }
        }
    }

    private static long getFieldOffsetInt(Object obj, int i) {
        return getFieldOffset(obj.getClass(), Integer.TYPE, j -> {
            return UNSAFE.getInt(obj, j) == i;
        });
    }

    private static long getFieldOffsetObject(Object obj, Object obj2) {
        return getFieldOffset(obj.getClass(), obj2.getClass(), j -> {
            return UNSAFE.getObject(obj, j) == obj2;
        });
    }

    private static long getAddressOffset() {
        long j = (-2401053090268712947L) & (Pointer.BITS32 ? 4294967295L : -1L);
        ByteBuffer byteBuffer = (ByteBuffer) Objects.requireNonNull(JNINativeInterface.NewDirectByteBuffer(j, 0L));
        return getFieldOffset(byteBuffer.getClass(), Long.TYPE, j2 -> {
            return UNSAFE.getLong(byteBuffer, j2) == j;
        });
    }

    private static long getMarkOffset() {
        return getFieldOffsetInt((ByteBuffer) Objects.requireNonNull(JNINativeInterface.NewDirectByteBuffer(1L, 0L)), -1);
    }

    private static long getPositionOffset() {
        ByteBuffer byteBuffer = (ByteBuffer) Objects.requireNonNull(JNINativeInterface.NewDirectByteBuffer(-1L, 219540062L));
        byteBuffer.position(MAGIC_POSITION);
        return getFieldOffsetInt(byteBuffer, MAGIC_POSITION);
    }

    private static long getLimitOffset() {
        ByteBuffer byteBuffer = (ByteBuffer) Objects.requireNonNull(JNINativeInterface.NewDirectByteBuffer(-1L, 219540062L));
        byteBuffer.limit(MAGIC_POSITION);
        return getFieldOffsetInt(byteBuffer, MAGIC_POSITION);
    }

    private static long getCapacityOffset() {
        ByteBuffer byteBuffer = (ByteBuffer) Objects.requireNonNull(JNINativeInterface.NewDirectByteBuffer(-1L, 219540062L));
        byteBuffer.limit(0);
        return getFieldOffsetInt(byteBuffer, MAGIC_CAPACITY);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends Buffer> T wrap(Class<? extends T> cls, long j, int i) {
        try {
            T t = (T) UNSAFE.allocateInstance(cls);
            UNSAFE.putLong(t, ADDRESS, j);
            UNSAFE.putInt(t, MARK, -1);
            UNSAFE.putInt(t, LIMIT, i);
            UNSAFE.putInt(t, CAPACITY, i);
            return t;
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    static ByteBuffer slice(ByteBuffer byteBuffer, long j, int i) {
        try {
            ByteBuffer byteBuffer2 = (ByteBuffer) UNSAFE.allocateInstance(BUFFER_BYTE);
            UNSAFE.putLong(byteBuffer2, ADDRESS, j);
            UNSAFE.putInt(byteBuffer2, MARK, -1);
            UNSAFE.putInt(byteBuffer2, LIMIT, i);
            UNSAFE.putInt(byteBuffer2, CAPACITY, i);
            Object object = UNSAFE.getObject(byteBuffer, PARENT_BYTE);
            UNSAFE.putObject(byteBuffer2, PARENT_BYTE, object == null ? byteBuffer : object);
            return byteBuffer2.order(byteBuffer.order());
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    static <T extends Buffer> T slice(Class<? extends T> cls, T t, long j, int i, long j2) {
        try {
            T t2 = (T) UNSAFE.allocateInstance(cls);
            UNSAFE.putLong(t2, ADDRESS, j);
            UNSAFE.putInt(t2, MARK, -1);
            UNSAFE.putInt(t2, LIMIT, i);
            UNSAFE.putInt(t2, CAPACITY, i);
            UNSAFE.putObject(t2, j2, UNSAFE.getObject(t, j2));
            return t2;
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    static <T extends Buffer> T duplicate(Class<? extends T> cls, T t, long j) {
        try {
            T t2 = (T) UNSAFE.allocateInstance(cls);
            UNSAFE.putLong(t2, ADDRESS, UNSAFE.getLong(t, ADDRESS));
            UNSAFE.putInt(t2, MARK, UNSAFE.getInt(t, MARK));
            UNSAFE.putInt(t2, POSITION, UNSAFE.getInt(t, POSITION));
            UNSAFE.putInt(t2, LIMIT, UNSAFE.getInt(t, LIMIT));
            UNSAFE.putInt(t2, CAPACITY, UNSAFE.getInt(t, CAPACITY));
            UNSAFE.putObject(t2, j, UNSAFE.getObject(t, j));
            return t2;
        } catch (InstantiationException e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
