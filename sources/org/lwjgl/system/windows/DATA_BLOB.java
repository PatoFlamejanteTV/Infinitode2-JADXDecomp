package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/DATA_BLOB.class */
public class DATA_BLOB extends Struct<DATA_BLOB> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CBDATA;
    public static final int PBDATA;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        CBDATA = __struct.offsetof(0);
        PBDATA = __struct.offsetof(1);
    }

    protected DATA_BLOB(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public DATA_BLOB create(long j, ByteBuffer byteBuffer) {
        return new DATA_BLOB(j, byteBuffer);
    }

    public DATA_BLOB(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int cbData() {
        return ncbData(address());
    }

    @NativeType("BYTE *")
    public ByteBuffer pbData() {
        return npbData(address());
    }

    public DATA_BLOB pbData(@NativeType("BYTE *") ByteBuffer byteBuffer) {
        npbData(address(), byteBuffer);
        return this;
    }

    public DATA_BLOB set(DATA_BLOB data_blob) {
        MemoryUtil.memCopy(data_blob.address(), address(), SIZEOF);
        return this;
    }

    public static DATA_BLOB malloc() {
        return new DATA_BLOB(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static DATA_BLOB calloc() {
        return new DATA_BLOB(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static DATA_BLOB create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new DATA_BLOB(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static DATA_BLOB create(long j) {
        return new DATA_BLOB(j, null);
    }

    public static DATA_BLOB createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new DATA_BLOB(j, null);
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

    public static DATA_BLOB malloc(MemoryStack memoryStack) {
        return new DATA_BLOB(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static DATA_BLOB calloc(MemoryStack memoryStack) {
        return new DATA_BLOB(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int ncbData(long j) {
        return UNSAFE.getInt((Object) null, j + CBDATA);
    }

    public static ByteBuffer npbData(long j) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + PBDATA), ncbData(j));
    }

    public static void ncbData(long j, int i) {
        UNSAFE.putInt((Object) null, j + CBDATA, i);
    }

    public static void npbData(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + PBDATA, MemoryUtil.memAddress(byteBuffer));
        ncbData(j, byteBuffer.remaining());
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + PBDATA));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/DATA_BLOB$Buffer.class */
    public static class Buffer extends StructBuffer<DATA_BLOB, Buffer> implements NativeResource {
        private static final DATA_BLOB ELEMENT_FACTORY = DATA_BLOB.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / DATA_BLOB.SIZEOF);
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
        public DATA_BLOB getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int cbData() {
            return DATA_BLOB.ncbData(address());
        }

        @NativeType("BYTE *")
        public ByteBuffer pbData() {
            return DATA_BLOB.npbData(address());
        }

        public Buffer pbData(@NativeType("BYTE *") ByteBuffer byteBuffer) {
            DATA_BLOB.npbData(address(), byteBuffer);
            return this;
        }
    }
}
