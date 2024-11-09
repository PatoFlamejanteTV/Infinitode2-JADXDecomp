package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stb_vorbis_alloc")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBVorbisAlloc.class */
public class STBVorbisAlloc extends Struct<STBVorbisAlloc> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int ALLOC_BUFFER;
    public static final int ALLOC_BUFFER_LENGTH_IN_BYTES;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        ALLOC_BUFFER = __struct.offsetof(0);
        ALLOC_BUFFER_LENGTH_IN_BYTES = __struct.offsetof(1);
    }

    protected STBVorbisAlloc(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBVorbisAlloc create(long j, ByteBuffer byteBuffer) {
        return new STBVorbisAlloc(j, byteBuffer);
    }

    public STBVorbisAlloc(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("char *")
    public ByteBuffer alloc_buffer() {
        return nalloc_buffer(address());
    }

    public int alloc_buffer_length_in_bytes() {
        return nalloc_buffer_length_in_bytes(address());
    }

    public STBVorbisAlloc alloc_buffer(@NativeType("char *") ByteBuffer byteBuffer) {
        nalloc_buffer(address(), byteBuffer);
        return this;
    }

    public STBVorbisAlloc set(STBVorbisAlloc sTBVorbisAlloc) {
        MemoryUtil.memCopy(sTBVorbisAlloc.address(), address(), SIZEOF);
        return this;
    }

    public static STBVorbisAlloc malloc() {
        return new STBVorbisAlloc(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBVorbisAlloc calloc() {
        return new STBVorbisAlloc(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBVorbisAlloc create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBVorbisAlloc(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBVorbisAlloc create(long j) {
        return new STBVorbisAlloc(j, null);
    }

    public static STBVorbisAlloc createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBVorbisAlloc(j, null);
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

    @Deprecated
    public static STBVorbisAlloc mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBVorbisAlloc callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBVorbisAlloc mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBVorbisAlloc callocStack(MemoryStack memoryStack) {
        return calloc(memoryStack);
    }

    @Deprecated
    public static Buffer mallocStack(int i) {
        return malloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer callocStack(int i) {
        return calloc(i, MemoryStack.stackGet());
    }

    @Deprecated
    public static Buffer mallocStack(int i, MemoryStack memoryStack) {
        return malloc(i, memoryStack);
    }

    @Deprecated
    public static Buffer callocStack(int i, MemoryStack memoryStack) {
        return calloc(i, memoryStack);
    }

    public static STBVorbisAlloc malloc(MemoryStack memoryStack) {
        return new STBVorbisAlloc(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBVorbisAlloc calloc(MemoryStack memoryStack) {
        return new STBVorbisAlloc(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ByteBuffer nalloc_buffer(long j) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + ALLOC_BUFFER), nalloc_buffer_length_in_bytes(j));
    }

    public static int nalloc_buffer_length_in_bytes(long j) {
        return UNSAFE.getInt((Object) null, j + ALLOC_BUFFER_LENGTH_IN_BYTES);
    }

    public static void nalloc_buffer(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + ALLOC_BUFFER, MemoryUtil.memAddress(byteBuffer));
        nalloc_buffer_length_in_bytes(j, byteBuffer.remaining());
    }

    public static void nalloc_buffer_length_in_bytes(long j, int i) {
        UNSAFE.putInt((Object) null, j + ALLOC_BUFFER_LENGTH_IN_BYTES, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + ALLOC_BUFFER));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBVorbisAlloc$Buffer.class */
    public static class Buffer extends StructBuffer<STBVorbisAlloc, Buffer> implements NativeResource {
        private static final STBVorbisAlloc ELEMENT_FACTORY = STBVorbisAlloc.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBVorbisAlloc.SIZEOF);
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
        public STBVorbisAlloc getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("char *")
        public ByteBuffer alloc_buffer() {
            return STBVorbisAlloc.nalloc_buffer(address());
        }

        public int alloc_buffer_length_in_bytes() {
            return STBVorbisAlloc.nalloc_buffer_length_in_bytes(address());
        }

        public Buffer alloc_buffer(@NativeType("char *") ByteBuffer byteBuffer) {
            STBVorbisAlloc.nalloc_buffer(address(), byteBuffer);
            return this;
        }
    }
}
