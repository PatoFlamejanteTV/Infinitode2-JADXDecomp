package org.lwjgl.system.libffi;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct ffi_type")
/* loaded from: infinitode-2.jar:org/lwjgl/system/libffi/FFIType.class */
public class FFIType extends Struct<FFIType> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int SIZE;
    public static final int ALIGNMENT;
    public static final int TYPE;
    public static final int ELEMENTS;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(2), __member(2), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        SIZE = __struct.offsetof(0);
        ALIGNMENT = __struct.offsetof(1);
        TYPE = __struct.offsetof(2);
        ELEMENTS = __struct.offsetof(3);
    }

    protected FFIType(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public FFIType create(long j, ByteBuffer byteBuffer) {
        return new FFIType(j, byteBuffer);
    }

    public FFIType(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("size_t")
    public long size() {
        return nsize(address());
    }

    @NativeType("unsigned short")
    public short alignment() {
        return nalignment(address());
    }

    @NativeType("unsigned short")
    public short type() {
        return ntype(address());
    }

    @NativeType("ffi_type *")
    public PointerBuffer elements(int i) {
        return nelements(address(), i);
    }

    public FFIType size(@NativeType("size_t") long j) {
        nsize(address(), j);
        return this;
    }

    public FFIType alignment(@NativeType("unsigned short") short s) {
        nalignment(address(), s);
        return this;
    }

    public FFIType type(@NativeType("unsigned short") short s) {
        ntype(address(), s);
        return this;
    }

    public FFIType elements(@NativeType("ffi_type *") PointerBuffer pointerBuffer) {
        nelements(address(), pointerBuffer);
        return this;
    }

    public FFIType set(long j, short s, short s2, PointerBuffer pointerBuffer) {
        size(j);
        alignment(s);
        type(s2);
        elements(pointerBuffer);
        return this;
    }

    public FFIType set(FFIType fFIType) {
        MemoryUtil.memCopy(fFIType.address(), address(), SIZEOF);
        return this;
    }

    public static FFIType malloc() {
        return new FFIType(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static FFIType calloc() {
        return new FFIType(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static FFIType create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new FFIType(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static FFIType create(long j) {
        return new FFIType(j, null);
    }

    public static FFIType createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new FFIType(j, null);
    }

    public static Buffer malloc(int i) {
        return new Buffer(MemoryUtil.nmemAllocChecked(__checkMalloc(i, SIZEOF)), i);
    }

    public static Buffer calloc(int i) {
        return new Buffer(MemoryUtil.nmemCallocChecked(i, SIZEOF), i);
    }

    public static Buffer create(int i) {
        ByteBuffer __create = __create(i, SIZEOF);
        return new Buffer(MemoryUtil.memAddress(__create), __create, -1, 0, i, i);
    }

    public static Buffer create(long j, int i) {
        return new Buffer(j, i);
    }

    public static Buffer createSafe(long j, int i) {
        if (j == 0) {
            return null;
        }
        return new Buffer(j, i);
    }

    public static FFIType malloc(MemoryStack memoryStack) {
        return new FFIType(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static FFIType calloc(MemoryStack memoryStack) {
        return new FFIType(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nsize(long j) {
        return MemoryUtil.memGetAddress(j + SIZE);
    }

    public static short nalignment(long j) {
        return UNSAFE.getShort((Object) null, j + ALIGNMENT);
    }

    public static short ntype(long j) {
        return UNSAFE.getShort((Object) null, j + TYPE);
    }

    public static PointerBuffer nelements(long j, int i) {
        return MemoryUtil.memPointerBufferSafe(MemoryUtil.memGetAddress(j + ELEMENTS), i);
    }

    public static void nsize(long j, long j2) {
        MemoryUtil.memPutAddress(j + SIZE, j2);
    }

    public static void nalignment(long j, short s) {
        UNSAFE.putShort((Object) null, j + ALIGNMENT, s);
    }

    public static void ntype(long j, short s) {
        UNSAFE.putShort((Object) null, j + TYPE, s);
    }

    public static void nelements(long j, PointerBuffer pointerBuffer) {
        MemoryUtil.memPutAddress(j + ELEMENTS, MemoryUtil.memAddressSafe(pointerBuffer));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/libffi/FFIType$Buffer.class */
    public static class Buffer extends StructBuffer<FFIType, Buffer> implements NativeResource {
        private static final FFIType ELEMENT_FACTORY = FFIType.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / FFIType.SIZEOF);
        }

        public Buffer(long j, int i) {
            super(j, null, -1, 0, i, i);
        }

        Buffer(long j, ByteBuffer byteBuffer, int i, int i2, int i3, int i4) {
            super(j, byteBuffer, i, i2, i3, i4);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.lwjgl.system.CustomBuffer
        public Buffer self() {
            return this;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // org.lwjgl.system.StructBuffer
        public FFIType getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("size_t")
        public long size() {
            return FFIType.nsize(address());
        }

        @NativeType("unsigned short")
        public short alignment() {
            return FFIType.nalignment(address());
        }

        @NativeType("unsigned short")
        public short type() {
            return FFIType.ntype(address());
        }

        @NativeType("ffi_type *")
        public PointerBuffer elements(int i) {
            return FFIType.nelements(address(), i);
        }

        public Buffer size(@NativeType("size_t") long j) {
            FFIType.nsize(address(), j);
            return this;
        }

        public Buffer alignment(@NativeType("unsigned short") short s) {
            FFIType.nalignment(address(), s);
            return this;
        }

        public Buffer type(@NativeType("unsigned short") short s) {
            FFIType.ntype(address(), s);
            return this;
        }

        public Buffer elements(@NativeType("ffi_type *") PointerBuffer pointerBuffer) {
            FFIType.nelements(address(), pointerBuffer);
            return this;
        }
    }
}
