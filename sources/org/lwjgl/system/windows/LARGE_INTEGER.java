package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/LARGE_INTEGER.class */
public class LARGE_INTEGER extends Struct<LARGE_INTEGER> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int U;
    public static final int U_LOWPART;
    public static final int U_HIGHPART;
    public static final int QUADPART;

    static {
        Struct.Layout __union = __union(__struct(__member(4), __member(4)), __member(8));
        SIZEOF = __union.getSize();
        ALIGNOF = __union.getAlignment();
        U = __union.offsetof(0);
        U_LOWPART = __union.offsetof(1);
        U_HIGHPART = __union.offsetof(2);
        QUADPART = __union.offsetof(3);
    }

    protected LARGE_INTEGER(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public LARGE_INTEGER create(long j, ByteBuffer byteBuffer) {
        return new LARGE_INTEGER(j, byteBuffer);
    }

    public LARGE_INTEGER(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int u_LowPart() {
        return nu_LowPart(address());
    }

    @NativeType("LONG")
    public int u_HighPart() {
        return nu_HighPart(address());
    }

    @NativeType("LONGLONG")
    public long QuadPart() {
        return nQuadPart(address());
    }

    public LARGE_INTEGER u_LowPart(@NativeType("DWORD") int i) {
        nu_LowPart(address(), i);
        return this;
    }

    public LARGE_INTEGER u_HighPart(@NativeType("LONG") int i) {
        nu_HighPart(address(), i);
        return this;
    }

    public LARGE_INTEGER QuadPart(@NativeType("LONGLONG") long j) {
        nQuadPart(address(), j);
        return this;
    }

    public LARGE_INTEGER set(LARGE_INTEGER large_integer) {
        MemoryUtil.memCopy(large_integer.address(), address(), SIZEOF);
        return this;
    }

    public static LARGE_INTEGER malloc() {
        return new LARGE_INTEGER(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static LARGE_INTEGER calloc() {
        return new LARGE_INTEGER(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static LARGE_INTEGER create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new LARGE_INTEGER(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static LARGE_INTEGER create(long j) {
        return new LARGE_INTEGER(j, null);
    }

    public static LARGE_INTEGER createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new LARGE_INTEGER(j, null);
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

    public static LARGE_INTEGER malloc(MemoryStack memoryStack) {
        return new LARGE_INTEGER(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static LARGE_INTEGER calloc(MemoryStack memoryStack) {
        return new LARGE_INTEGER(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nu_LowPart(long j) {
        return UNSAFE.getInt((Object) null, j + U_LOWPART);
    }

    public static int nu_HighPart(long j) {
        return UNSAFE.getInt((Object) null, j + U_HIGHPART);
    }

    public static long nQuadPart(long j) {
        return UNSAFE.getLong((Object) null, j + QUADPART);
    }

    public static void nu_LowPart(long j, int i) {
        UNSAFE.putInt((Object) null, j + U_LOWPART, i);
    }

    public static void nu_HighPart(long j, int i) {
        UNSAFE.putInt((Object) null, j + U_HIGHPART, i);
    }

    public static void nQuadPart(long j, long j2) {
        UNSAFE.putLong((Object) null, j + QUADPART, j2);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/LARGE_INTEGER$Buffer.class */
    public static class Buffer extends StructBuffer<LARGE_INTEGER, Buffer> implements NativeResource {
        private static final LARGE_INTEGER ELEMENT_FACTORY = LARGE_INTEGER.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / LARGE_INTEGER.SIZEOF);
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
        public LARGE_INTEGER getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int u_LowPart() {
            return LARGE_INTEGER.nu_LowPart(address());
        }

        @NativeType("LONG")
        public int u_HighPart() {
            return LARGE_INTEGER.nu_HighPart(address());
        }

        @NativeType("LONGLONG")
        public long QuadPart() {
            return LARGE_INTEGER.nQuadPart(address());
        }

        public Buffer u_LowPart(@NativeType("DWORD") int i) {
            LARGE_INTEGER.nu_LowPart(address(), i);
            return this;
        }

        public Buffer u_HighPart(@NativeType("LONG") int i) {
            LARGE_INTEGER.nu_HighPart(address(), i);
            return this;
        }

        public Buffer QuadPart(@NativeType("LONGLONG") long j) {
            LARGE_INTEGER.nQuadPart(address(), j);
            return this;
        }
    }
}
