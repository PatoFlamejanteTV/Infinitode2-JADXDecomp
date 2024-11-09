package org.lwjgl.system;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import org.lwjgl.BufferUtils;
import org.lwjgl.CLongBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Pointer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryStack.class */
public class MemoryStack extends Pointer.Default implements AutoCloseable {
    private static final int DEFAULT_STACK_FRAMES = 8;
    private final ByteBuffer container;
    private final int size;
    private int pointer;
    private int[] frames;
    protected int frameIndex;
    private static final int DEFAULT_STACK_SIZE = Configuration.STACK_SIZE.get(64).intValue() << 10;
    private static final ThreadLocal<MemoryStack> TLS = ThreadLocal.withInitial(MemoryStack::create);

    static {
        if (DEFAULT_STACK_SIZE < 0) {
            throw new IllegalStateException("Invalid stack size.");
        }
    }

    protected MemoryStack(ByteBuffer byteBuffer, long j, int i) {
        super(j);
        this.container = byteBuffer;
        this.size = i;
        this.pointer = i;
        this.frames = new int[8];
    }

    public static MemoryStack create() {
        return create(DEFAULT_STACK_SIZE);
    }

    public static MemoryStack create(int i) {
        return create(BufferUtils.createByteBuffer(i));
    }

    public static MemoryStack create(ByteBuffer byteBuffer) {
        long memAddress = MemoryUtil.memAddress(byteBuffer);
        int remaining = byteBuffer.remaining();
        return Configuration.DEBUG_STACK.get(Boolean.FALSE).booleanValue() ? new DebugMemoryStack(byteBuffer, memAddress, remaining) : new MemoryStack(byteBuffer, memAddress, remaining);
    }

    public static MemoryStack ncreate(long j, int i) {
        return Configuration.DEBUG_STACK.get(Boolean.FALSE).booleanValue() ? new DebugMemoryStack(null, j, i) : new MemoryStack(null, j, i);
    }

    public MemoryStack push() {
        if (this.frameIndex == this.frames.length) {
            frameOverflow();
        }
        int[] iArr = this.frames;
        int i = this.frameIndex;
        this.frameIndex = i + 1;
        iArr[i] = this.pointer;
        return this;
    }

    private void frameOverflow() {
        if (Checks.DEBUG) {
            APIUtil.apiLog("[WARNING] Out of frame stack space (" + this.frames.length + ") in thread: " + Thread.currentThread());
        }
        this.frames = Arrays.copyOf(this.frames, (this.frames.length * 3) / 2);
    }

    public MemoryStack pop() {
        int[] iArr = this.frames;
        int i = this.frameIndex - 1;
        this.frameIndex = i;
        this.pointer = iArr[i];
        return this;
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        pop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryStack$DebugMemoryStack.class */
    public static class DebugMemoryStack extends MemoryStack {
        private Object[] debugFrames;

        DebugMemoryStack(ByteBuffer byteBuffer, long j, int i) {
            super(byteBuffer, j, i);
            this.debugFrames = new Object[8];
        }

        @Override // org.lwjgl.system.MemoryStack
        public MemoryStack push() {
            if (this.frameIndex == this.debugFrames.length) {
                frameOverflow();
            }
            this.debugFrames[this.frameIndex] = StackWalkUtil.stackWalkGetMethod(MemoryStack.class);
            return super.push();
        }

        private void frameOverflow() {
            this.debugFrames = Arrays.copyOf(this.debugFrames, (this.debugFrames.length * 3) / 2);
        }

        @Override // org.lwjgl.system.MemoryStack
        public MemoryStack pop() {
            Object obj = this.debugFrames[this.frameIndex - 1];
            Object stackWalkCheckPop = StackWalkUtil.stackWalkCheckPop(MemoryStack.class, obj);
            if (stackWalkCheckPop != null) {
                reportAsymmetricPop(obj, stackWalkCheckPop);
            }
            this.debugFrames[this.frameIndex - 1] = null;
            return super.pop();
        }

        @Override // org.lwjgl.system.MemoryStack, java.lang.AutoCloseable
        public void close() {
            this.debugFrames[this.frameIndex - 1] = null;
            super.pop();
        }

        private static void reportAsymmetricPop(Object obj, Object obj2) {
            APIUtil.DEBUG_STREAM.format("[LWJGL] Asymmetric pop detected:\n\tPUSHED: %s\n\tPOPPED: %s\n\tTHREAD: %s\n", obj, obj2, Thread.currentThread());
        }
    }

    public long getAddress() {
        return this.address;
    }

    public int getSize() {
        return this.size;
    }

    public int getFrameIndex() {
        return this.frameIndex;
    }

    public long getPointerAddress() {
        return this.address + (this.pointer & 4294967295L);
    }

    public int getPointer() {
        return this.pointer;
    }

    public void setPointer(int i) {
        if (Checks.CHECKS) {
            checkPointer(i);
        }
        this.pointer = i;
    }

    private void checkPointer(int i) {
        if (i < 0 || this.size < i) {
            throw new IndexOutOfBoundsException("Invalid stack pointer");
        }
    }

    private static void checkAlignment(int i) {
        if (Integer.bitCount(i) != 1) {
            throw new IllegalArgumentException("Alignment must be a power-of-two value.");
        }
    }

    public long nmalloc(int i) {
        return nmalloc(POINTER_SIZE, i);
    }

    public long nmalloc(int i, int i2) {
        long unsignedLong = ((this.address + this.pointer) - i2) & (Integer.toUnsignedLong(i - 1) ^ (-1));
        this.pointer = (int) (unsignedLong - this.address);
        if (Checks.CHECKS && this.pointer < 0) {
            throw new OutOfMemoryError("Out of stack space.");
        }
        return unsignedLong;
    }

    public long ncalloc(int i, int i2, int i3) {
        int i4 = i2 * i3;
        long nmalloc = nmalloc(i, i4);
        MemoryUtil.memSet(nmalloc, 0, i4);
        return nmalloc;
    }

    public ByteBuffer malloc(int i, int i2) {
        if (Checks.DEBUG) {
            checkAlignment(i);
        }
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, nmalloc(i, i2), i2)).order(MemoryUtil.NATIVE_ORDER);
    }

    public ByteBuffer calloc(int i, int i2) {
        if (Checks.DEBUG) {
            checkAlignment(i);
        }
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, ncalloc(i, i2, 1), i2)).order(MemoryUtil.NATIVE_ORDER);
    }

    public ByteBuffer malloc(int i) {
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, nmalloc(POINTER_SIZE, i), i)).order(MemoryUtil.NATIVE_ORDER);
    }

    public ByteBuffer calloc(int i) {
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, ncalloc(POINTER_SIZE, i, 1), i)).order(MemoryUtil.NATIVE_ORDER);
    }

    public long nbyte(byte b2) {
        long nmalloc = nmalloc(1, 1);
        MemoryUtil.memPutByte(nmalloc, b2);
        return nmalloc;
    }

    public ByteBuffer bytes(byte b2) {
        return malloc(1, 1).put(0, b2);
    }

    public ByteBuffer bytes(byte b2, byte b3) {
        return malloc(1, 2).put(0, b2).put(1, b3);
    }

    public ByteBuffer bytes(byte b2, byte b3, byte b4) {
        return malloc(1, 3).put(0, b2).put(1, b3).put(2, b4);
    }

    public ByteBuffer bytes(byte b2, byte b3, byte b4, byte b5) {
        return malloc(1, 4).put(0, b2).put(1, b3).put(2, b4).put(3, b5);
    }

    public ByteBuffer bytes(byte... bArr) {
        ByteBuffer put = malloc(1, bArr.length).put(bArr);
        put.flip();
        return put;
    }

    public ShortBuffer mallocShort(int i) {
        return (ShortBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_SHORT, nmalloc(2, i << 1), i);
    }

    public ShortBuffer callocShort(int i) {
        int i2 = i << 1;
        long nmalloc = nmalloc(2, i2);
        MemoryUtil.memSet(nmalloc, 0, i2);
        return (ShortBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_SHORT, nmalloc, i);
    }

    public long nshort(short s) {
        long nmalloc = nmalloc(2, 2);
        MemoryUtil.memPutShort(nmalloc, s);
        return nmalloc;
    }

    public ShortBuffer shorts(short s) {
        return mallocShort(1).put(0, s);
    }

    public ShortBuffer shorts(short s, short s2) {
        return mallocShort(2).put(0, s).put(1, s2);
    }

    public ShortBuffer shorts(short s, short s2, short s3) {
        return mallocShort(3).put(0, s).put(1, s2).put(2, s3);
    }

    public ShortBuffer shorts(short s, short s2, short s3, short s4) {
        return mallocShort(4).put(0, s).put(1, s2).put(2, s3).put(3, s4);
    }

    public ShortBuffer shorts(short... sArr) {
        ShortBuffer put = mallocShort(sArr.length).put(sArr);
        put.flip();
        return put;
    }

    public IntBuffer mallocInt(int i) {
        return (IntBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_INT, nmalloc(4, i << 2), i);
    }

    public IntBuffer callocInt(int i) {
        int i2 = i << 2;
        long nmalloc = nmalloc(4, i2);
        MemoryUtil.memSet(nmalloc, 0, i2);
        return (IntBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_INT, nmalloc, i);
    }

    public long nint(int i) {
        long nmalloc = nmalloc(4, 4);
        MemoryUtil.memPutInt(nmalloc, i);
        return nmalloc;
    }

    public IntBuffer ints(int i) {
        return mallocInt(1).put(0, i);
    }

    public IntBuffer ints(int i, int i2) {
        return mallocInt(2).put(0, i).put(1, i2);
    }

    public IntBuffer ints(int i, int i2, int i3) {
        return mallocInt(3).put(0, i).put(1, i2).put(2, i3);
    }

    public IntBuffer ints(int i, int i2, int i3, int i4) {
        return mallocInt(4).put(0, i).put(1, i2).put(2, i3).put(3, i4);
    }

    public IntBuffer ints(int... iArr) {
        IntBuffer put = mallocInt(iArr.length).put(iArr);
        put.flip();
        return put;
    }

    public LongBuffer mallocLong(int i) {
        return (LongBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_LONG, nmalloc(8, i << 3), i);
    }

    public LongBuffer callocLong(int i) {
        int i2 = i << 3;
        long nmalloc = nmalloc(8, i2);
        MemoryUtil.memSet(nmalloc, 0, i2);
        return (LongBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_LONG, nmalloc, i);
    }

    public long nlong(long j) {
        long nmalloc = nmalloc(8, 8);
        MemoryUtil.memPutLong(nmalloc, j);
        return nmalloc;
    }

    public LongBuffer longs(long j) {
        return mallocLong(1).put(0, j);
    }

    public LongBuffer longs(long j, long j2) {
        return mallocLong(2).put(0, j).put(1, j2);
    }

    public LongBuffer longs(long j, long j2, long j3) {
        return mallocLong(3).put(0, j).put(1, j2).put(2, j3);
    }

    public LongBuffer longs(long j, long j2, long j3, long j4) {
        return mallocLong(4).put(0, j).put(1, j2).put(2, j3).put(3, j4);
    }

    public LongBuffer longs(long... jArr) {
        LongBuffer put = mallocLong(jArr.length).put(jArr);
        put.flip();
        return put;
    }

    public CLongBuffer mallocCLong(int i) {
        return CLongBuffer.create(nmalloc(CLONG_SIZE, i << CLONG_SHIFT), i);
    }

    public CLongBuffer callocCLong(int i) {
        int i2 = i * CLONG_SIZE;
        long nmalloc = nmalloc(CLONG_SIZE, i2);
        MemoryUtil.memSet(nmalloc, 0, i2);
        return CLongBuffer.create(nmalloc, i);
    }

    public long nclong(long j) {
        int i = CLONG_SIZE;
        long nmalloc = nmalloc(i, i);
        MemoryUtil.memPutCLong(nmalloc, j);
        return nmalloc;
    }

    public CLongBuffer clongs(long j) {
        return mallocCLong(1).put(0, j);
    }

    public CLongBuffer clongs(long j, long j2) {
        return mallocCLong(2).put(0, j).put(1, j2);
    }

    public CLongBuffer clongs(long j, long j2, long j3) {
        return mallocCLong(3).put(0, j).put(1, j2).put(2, j3);
    }

    public CLongBuffer clongs(long j, long j2, long j3, long j4) {
        return mallocCLong(4).put(0, j).put(1, j2).put(2, j3).put(3, j4);
    }

    public CLongBuffer clongs(long... jArr) {
        CLongBuffer put = mallocCLong(jArr.length).put(jArr);
        put.flip();
        return put;
    }

    public FloatBuffer mallocFloat(int i) {
        return (FloatBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_FLOAT, nmalloc(4, i << 2), i);
    }

    public FloatBuffer callocFloat(int i) {
        int i2 = i << 2;
        long nmalloc = nmalloc(4, i2);
        MemoryUtil.memSet(nmalloc, 0, i2);
        return (FloatBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_FLOAT, nmalloc, i);
    }

    public long nfloat(float f) {
        long nmalloc = nmalloc(4, 4);
        MemoryUtil.memPutFloat(nmalloc, f);
        return nmalloc;
    }

    public FloatBuffer floats(float f) {
        return mallocFloat(1).put(0, f);
    }

    public FloatBuffer floats(float f, float f2) {
        return mallocFloat(2).put(0, f).put(1, f2);
    }

    public FloatBuffer floats(float f, float f2, float f3) {
        return mallocFloat(3).put(0, f).put(1, f2).put(2, f3);
    }

    public FloatBuffer floats(float f, float f2, float f3, float f4) {
        return mallocFloat(4).put(0, f).put(1, f2).put(2, f3).put(3, f4);
    }

    public FloatBuffer floats(float... fArr) {
        FloatBuffer put = mallocFloat(fArr.length).put(fArr);
        put.flip();
        return put;
    }

    public DoubleBuffer mallocDouble(int i) {
        return (DoubleBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_DOUBLE, nmalloc(8, i << 3), i);
    }

    public DoubleBuffer callocDouble(int i) {
        int i2 = i << 3;
        long nmalloc = nmalloc(8, i2);
        MemoryUtil.memSet(nmalloc, 0, i2);
        return (DoubleBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_DOUBLE, nmalloc, i);
    }

    public long ndouble(double d) {
        long nmalloc = nmalloc(8, 8);
        MemoryUtil.memPutDouble(nmalloc, d);
        return nmalloc;
    }

    public DoubleBuffer doubles(double d) {
        return mallocDouble(1).put(0, d);
    }

    public DoubleBuffer doubles(double d, double d2) {
        return mallocDouble(2).put(0, d).put(1, d2);
    }

    public DoubleBuffer doubles(double d, double d2, double d3) {
        return mallocDouble(3).put(0, d).put(1, d2).put(2, d3);
    }

    public DoubleBuffer doubles(double d, double d2, double d3, double d4) {
        return mallocDouble(4).put(0, d).put(1, d2).put(2, d3).put(3, d4);
    }

    public DoubleBuffer doubles(double... dArr) {
        DoubleBuffer put = mallocDouble(dArr.length).put(dArr);
        put.flip();
        return put;
    }

    public PointerBuffer mallocPointer(int i) {
        return PointerBuffer.create(nmalloc(POINTER_SIZE, i << POINTER_SHIFT), i);
    }

    public PointerBuffer callocPointer(int i) {
        int i2 = i * POINTER_SIZE;
        long nmalloc = nmalloc(POINTER_SIZE, i2);
        MemoryUtil.memSet(nmalloc, 0, i2);
        return PointerBuffer.create(nmalloc, i);
    }

    public long npointer(long j) {
        int i = POINTER_SIZE;
        long nmalloc = nmalloc(i, i);
        MemoryUtil.memPutAddress(nmalloc, j);
        return nmalloc;
    }

    public PointerBuffer pointers(long j) {
        return mallocPointer(1).put(0, j);
    }

    public PointerBuffer pointers(long j, long j2) {
        return mallocPointer(2).put(0, j).put(1, j2);
    }

    public PointerBuffer pointers(long j, long j2, long j3) {
        return mallocPointer(3).put(0, j).put(1, j2).put(2, j3);
    }

    public PointerBuffer pointers(long j, long j2, long j3, long j4) {
        return mallocPointer(4).put(0, j).put(1, j2).put(2, j3).put(3, j4);
    }

    public PointerBuffer pointers(long... jArr) {
        PointerBuffer put = mallocPointer(jArr.length).put(jArr);
        put.flip();
        return put;
    }

    public long npointer(Pointer pointer) {
        int i = POINTER_SIZE;
        long nmalloc = nmalloc(i, i);
        MemoryUtil.memPutAddress(nmalloc, pointer.address());
        return nmalloc;
    }

    public PointerBuffer pointers(Pointer pointer) {
        return mallocPointer(1).put(0, pointer);
    }

    public PointerBuffer pointers(Pointer pointer, Pointer pointer2) {
        return mallocPointer(2).put(0, pointer).put(1, pointer2);
    }

    public PointerBuffer pointers(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        return mallocPointer(3).put(0, pointer).put(1, pointer2).put(2, pointer3);
    }

    public PointerBuffer pointers(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4) {
        return mallocPointer(4).put(0, pointer).put(1, pointer2).put(2, pointer3).put(3, pointer4);
    }

    public PointerBuffer pointers(Pointer... pointerArr) {
        PointerBuffer mallocPointer = mallocPointer(pointerArr.length);
        for (int i = 0; i < pointerArr.length; i++) {
            mallocPointer.put(i, pointerArr[i]);
        }
        return mallocPointer;
    }

    public long npointer(Buffer buffer) {
        int i = POINTER_SIZE;
        long nmalloc = nmalloc(i, i);
        MemoryUtil.memPutAddress(nmalloc, MemoryUtil.memAddress(buffer));
        return nmalloc;
    }

    public PointerBuffer pointers(Buffer buffer) {
        return mallocPointer(1).put(0, MemoryUtil.memAddress(buffer));
    }

    public PointerBuffer pointers(Buffer buffer, Buffer buffer2) {
        return mallocPointer(2).put(0, MemoryUtil.memAddress(buffer)).put(1, MemoryUtil.memAddress(buffer2));
    }

    public PointerBuffer pointers(Buffer buffer, Buffer buffer2, Buffer buffer3) {
        return mallocPointer(3).put(0, MemoryUtil.memAddress(buffer)).put(1, MemoryUtil.memAddress(buffer2)).put(2, MemoryUtil.memAddress(buffer3));
    }

    public PointerBuffer pointers(Buffer buffer, Buffer buffer2, Buffer buffer3, Buffer buffer4) {
        return mallocPointer(4).put(0, MemoryUtil.memAddress(buffer)).put(1, MemoryUtil.memAddress(buffer2)).put(2, MemoryUtil.memAddress(buffer3)).put(3, MemoryUtil.memAddress(buffer4));
    }

    public PointerBuffer pointers(Buffer... bufferArr) {
        PointerBuffer mallocPointer = mallocPointer(bufferArr.length);
        for (int i = 0; i < bufferArr.length; i++) {
            mallocPointer.put(i, MemoryUtil.memAddress(bufferArr[i]));
        }
        return mallocPointer;
    }

    public PointerBuffer pointersOfElements(CustomBuffer<?> customBuffer) {
        int remaining = customBuffer.remaining();
        long address = customBuffer.address();
        long sizeof = customBuffer.sizeof();
        PointerBuffer mallocPointer = mallocPointer(remaining);
        for (int i = 0; i < remaining; i++) {
            mallocPointer.put(i, address + (sizeof * i));
        }
        return mallocPointer;
    }

    public ByteBuffer ASCII(CharSequence charSequence) {
        return ASCII(charSequence, true);
    }

    public ByteBuffer ASCII(CharSequence charSequence, boolean z) {
        int memLengthASCII = MemoryUtil.memLengthASCII(charSequence, z);
        long nmalloc = nmalloc(POINTER_SIZE, memLengthASCII);
        MemoryUtil.encodeASCIIUnsafe(charSequence, z, nmalloc);
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, nmalloc, memLengthASCII)).order(MemoryUtil.NATIVE_ORDER);
    }

    public int nASCII(CharSequence charSequence, boolean z) {
        return MemoryUtil.encodeASCIIUnsafe(charSequence, z, nmalloc(POINTER_SIZE, MemoryUtil.memLengthASCII(charSequence, z)));
    }

    public ByteBuffer ASCIISafe(CharSequence charSequence) {
        return ASCIISafe(charSequence, true);
    }

    public ByteBuffer ASCIISafe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return null;
        }
        return ASCII(charSequence, z);
    }

    public int nASCIISafe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return 0;
        }
        return nASCII(charSequence, z);
    }

    public ByteBuffer UTF8(CharSequence charSequence) {
        return UTF8(charSequence, true);
    }

    public ByteBuffer UTF8(CharSequence charSequence, boolean z) {
        int memLengthUTF8 = MemoryUtil.memLengthUTF8(charSequence, z);
        long nmalloc = nmalloc(POINTER_SIZE, memLengthUTF8);
        MemoryUtil.encodeUTF8Unsafe(charSequence, z, nmalloc);
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, nmalloc, memLengthUTF8)).order(MemoryUtil.NATIVE_ORDER);
    }

    public int nUTF8(CharSequence charSequence, boolean z) {
        return MemoryUtil.encodeUTF8Unsafe(charSequence, z, nmalloc(POINTER_SIZE, MemoryUtil.memLengthUTF8(charSequence, z)));
    }

    public ByteBuffer UTF8Safe(CharSequence charSequence) {
        return UTF8Safe(charSequence, true);
    }

    public ByteBuffer UTF8Safe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return null;
        }
        return UTF8(charSequence, z);
    }

    public int nUTF8Safe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return 0;
        }
        return nUTF8(charSequence, z);
    }

    public ByteBuffer UTF16(CharSequence charSequence) {
        return UTF16(charSequence, true);
    }

    public ByteBuffer UTF16(CharSequence charSequence, boolean z) {
        int memLengthUTF16 = MemoryUtil.memLengthUTF16(charSequence, z);
        long nmalloc = nmalloc(POINTER_SIZE, memLengthUTF16);
        MemoryUtil.encodeUTF16Unsafe(charSequence, z, nmalloc);
        return ((ByteBuffer) MemoryUtil.wrap(MemoryUtil.BUFFER_BYTE, nmalloc, memLengthUTF16)).order(MemoryUtil.NATIVE_ORDER);
    }

    public int nUTF16(CharSequence charSequence, boolean z) {
        return MemoryUtil.encodeUTF16Unsafe(charSequence, z, nmalloc(POINTER_SIZE, MemoryUtil.memLengthUTF16(charSequence, z)));
    }

    public ByteBuffer UTF16Safe(CharSequence charSequence) {
        return UTF16Safe(charSequence, true);
    }

    public ByteBuffer UTF16Safe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return null;
        }
        return UTF16(charSequence, z);
    }

    public int nUTF16Safe(CharSequence charSequence, boolean z) {
        if (charSequence == null) {
            return 0;
        }
        return nUTF16(charSequence, z);
    }

    public static MemoryStack stackGet() {
        return TLS.get();
    }

    public static MemoryStack stackPush() {
        return stackGet().push();
    }

    public static MemoryStack stackPop() {
        return stackGet().pop();
    }

    public static long nstackMalloc(int i) {
        return stackGet().nmalloc(i);
    }

    public static long nstackMalloc(int i, int i2) {
        return stackGet().nmalloc(i, i2);
    }

    public static long nstackCalloc(int i, int i2, int i3) {
        return stackGet().ncalloc(i, i2, i3);
    }

    public static ByteBuffer stackMalloc(int i) {
        return stackGet().malloc(i);
    }

    public static ByteBuffer stackCalloc(int i) {
        return stackGet().calloc(i);
    }

    public static ByteBuffer stackBytes(byte b2) {
        return stackGet().bytes(b2);
    }

    public static ByteBuffer stackBytes(byte b2, byte b3) {
        return stackGet().bytes(b2, b3);
    }

    public static ByteBuffer stackBytes(byte b2, byte b3, byte b4) {
        return stackGet().bytes(b2, b3, b4);
    }

    public static ByteBuffer stackBytes(byte b2, byte b3, byte b4, byte b5) {
        return stackGet().bytes(b2, b3, b4, b5);
    }

    public static ByteBuffer stackBytes(byte... bArr) {
        return stackGet().bytes(bArr);
    }

    public static ShortBuffer stackMallocShort(int i) {
        return stackGet().mallocShort(i);
    }

    public static ShortBuffer stackCallocShort(int i) {
        return stackGet().callocShort(i);
    }

    public static ShortBuffer stackShorts(short s) {
        return stackGet().shorts(s);
    }

    public static ShortBuffer stackShorts(short s, short s2) {
        return stackGet().shorts(s, s2);
    }

    public static ShortBuffer stackShorts(short s, short s2, short s3) {
        return stackGet().shorts(s, s2, s3);
    }

    public static ShortBuffer stackShorts(short s, short s2, short s3, short s4) {
        return stackGet().shorts(s, s2, s3, s4);
    }

    public static ShortBuffer stackShorts(short... sArr) {
        return stackGet().shorts(sArr);
    }

    public static IntBuffer stackMallocInt(int i) {
        return stackGet().mallocInt(i);
    }

    public static IntBuffer stackCallocInt(int i) {
        return stackGet().callocInt(i);
    }

    public static IntBuffer stackInts(int i) {
        return stackGet().ints(i);
    }

    public static IntBuffer stackInts(int i, int i2) {
        return stackGet().ints(i, i2);
    }

    public static IntBuffer stackInts(int i, int i2, int i3) {
        return stackGet().ints(i, i2, i3);
    }

    public static IntBuffer stackInts(int i, int i2, int i3, int i4) {
        return stackGet().ints(i, i2, i3, i4);
    }

    public static IntBuffer stackInts(int... iArr) {
        return stackGet().ints(iArr);
    }

    public static LongBuffer stackMallocLong(int i) {
        return stackGet().mallocLong(i);
    }

    public static LongBuffer stackCallocLong(int i) {
        return stackGet().callocLong(i);
    }

    public static LongBuffer stackLongs(long j) {
        return stackGet().longs(j);
    }

    public static LongBuffer stackLongs(long j, long j2) {
        return stackGet().longs(j, j2);
    }

    public static LongBuffer stackLongs(long j, long j2, long j3) {
        return stackGet().longs(j, j2, j3);
    }

    public static LongBuffer stackLongs(long j, long j2, long j3, long j4) {
        return stackGet().longs(j, j2, j3, j4);
    }

    public static LongBuffer stackLongs(long... jArr) {
        return stackGet().longs(jArr);
    }

    public static CLongBuffer stackMallocCLong(int i) {
        return stackGet().mallocCLong(i);
    }

    public static CLongBuffer stackCallocCLong(int i) {
        return stackGet().callocCLong(i);
    }

    public static CLongBuffer stackCLongs(long j) {
        return stackGet().clongs(j);
    }

    public static CLongBuffer stackCLongs(long j, long j2) {
        return stackGet().clongs(j, j2);
    }

    public static CLongBuffer stackCLongs(long j, long j2, long j3) {
        return stackGet().clongs(j, j2, j3);
    }

    public static CLongBuffer stackCLongs(long j, long j2, long j3, long j4) {
        return stackGet().clongs(j, j2, j3, j4);
    }

    public static CLongBuffer stackCLongs(long... jArr) {
        return stackGet().clongs(jArr);
    }

    public static FloatBuffer stackMallocFloat(int i) {
        return stackGet().mallocFloat(i);
    }

    public static FloatBuffer stackCallocFloat(int i) {
        return stackGet().callocFloat(i);
    }

    public static FloatBuffer stackFloats(float f) {
        return stackGet().floats(f);
    }

    public static FloatBuffer stackFloats(float f, float f2) {
        return stackGet().floats(f, f2);
    }

    public static FloatBuffer stackFloats(float f, float f2, float f3) {
        return stackGet().floats(f, f2, f3);
    }

    public static FloatBuffer stackFloats(float f, float f2, float f3, float f4) {
        return stackGet().floats(f, f2, f3, f4);
    }

    public static FloatBuffer stackFloats(float... fArr) {
        return stackGet().floats(fArr);
    }

    public static DoubleBuffer stackMallocDouble(int i) {
        return stackGet().mallocDouble(i);
    }

    public static DoubleBuffer stackCallocDouble(int i) {
        return stackGet().callocDouble(i);
    }

    public static DoubleBuffer stackDoubles(double d) {
        return stackGet().doubles(d);
    }

    public static DoubleBuffer stackDoubles(double d, double d2) {
        return stackGet().doubles(d, d2);
    }

    public static DoubleBuffer stackDoubles(double d, double d2, double d3) {
        return stackGet().doubles(d, d2, d3);
    }

    public static DoubleBuffer stackDoubles(double d, double d2, double d3, double d4) {
        return stackGet().doubles(d, d2, d3, d4);
    }

    public static DoubleBuffer stackDoubles(double... dArr) {
        return stackGet().doubles(dArr);
    }

    public static PointerBuffer stackMallocPointer(int i) {
        return stackGet().mallocPointer(i);
    }

    public static PointerBuffer stackCallocPointer(int i) {
        return stackGet().callocPointer(i);
    }

    public static PointerBuffer stackPointers(long j) {
        return stackGet().pointers(j);
    }

    public static PointerBuffer stackPointers(long j, long j2) {
        return stackGet().pointers(j, j2);
    }

    public static PointerBuffer stackPointers(long j, long j2, long j3) {
        return stackGet().pointers(j, j2, j3);
    }

    public static PointerBuffer stackPointers(long j, long j2, long j3, long j4) {
        return stackGet().pointers(j, j2, j3, j4);
    }

    public static PointerBuffer stackPointers(long... jArr) {
        return stackGet().pointers(jArr);
    }

    public static PointerBuffer stackPointers(Pointer pointer) {
        return stackGet().pointers(pointer);
    }

    public static PointerBuffer stackPointers(Pointer pointer, Pointer pointer2) {
        return stackGet().pointers(pointer, pointer2);
    }

    public static PointerBuffer stackPointers(Pointer pointer, Pointer pointer2, Pointer pointer3) {
        return stackGet().pointers(pointer, pointer2, pointer3);
    }

    public static PointerBuffer stackPointers(Pointer pointer, Pointer pointer2, Pointer pointer3, Pointer pointer4) {
        return stackGet().pointers(pointer, pointer2, pointer3, pointer4);
    }

    public static PointerBuffer stackPointers(Pointer... pointerArr) {
        return stackGet().pointers(pointerArr);
    }

    public static ByteBuffer stackASCII(CharSequence charSequence) {
        return stackGet().ASCII(charSequence);
    }

    public static ByteBuffer stackASCII(CharSequence charSequence, boolean z) {
        return stackGet().ASCII(charSequence, z);
    }

    public static ByteBuffer stackUTF8(CharSequence charSequence) {
        return stackGet().UTF8(charSequence);
    }

    public static ByteBuffer stackUTF8(CharSequence charSequence, boolean z) {
        return stackGet().UTF8(charSequence, z);
    }

    public static ByteBuffer stackUTF16(CharSequence charSequence) {
        return stackGet().UTF16(charSequence);
    }

    public static ByteBuffer stackUTF16(CharSequence charSequence, boolean z) {
        return stackGet().UTF16(charSequence, z);
    }

    public static ByteBuffer stackASCIISafe(CharSequence charSequence) {
        return stackGet().ASCIISafe(charSequence);
    }

    public static ByteBuffer stackASCIISafe(CharSequence charSequence, boolean z) {
        return stackGet().ASCIISafe(charSequence, z);
    }

    public static ByteBuffer stackUTF8Safe(CharSequence charSequence) {
        return stackGet().UTF8Safe(charSequence);
    }

    public static ByteBuffer stackUTF8Safe(CharSequence charSequence, boolean z) {
        return stackGet().UTF8Safe(charSequence, z);
    }

    public static ByteBuffer stackUTF16Safe(CharSequence charSequence) {
        return stackGet().UTF16Safe(charSequence);
    }

    public static ByteBuffer stackUTF16Safe(CharSequence charSequence, boolean z) {
        return stackGet().UTF16Safe(charSequence, z);
    }
}
