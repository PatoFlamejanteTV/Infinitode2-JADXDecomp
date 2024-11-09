package org.lwjgl.system.libffi;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct ffi_closure")
/* loaded from: infinitode-2.jar:org/lwjgl/system/libffi/FFIClosure.class */
public class FFIClosure extends Struct<FFIClosure> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int CIF;
    public static final int FUN;
    public static final int USER_DATA;

    private static native int offsets(long j);

    static {
        MemoryStack stackPush = MemoryStack.stackPush();
        Throwable th = null;
        try {
            IntBuffer mallocInt = stackPush.mallocInt(4);
            SIZEOF = offsets(MemoryUtil.memAddress(mallocInt));
            CIF = mallocInt.get(0);
            FUN = mallocInt.get(1);
            USER_DATA = mallocInt.get(2);
            ALIGNOF = mallocInt.get(3);
            if (stackPush != null) {
                if (0 == 0) {
                    stackPush.close();
                    return;
                }
                try {
                    stackPush.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
        } catch (Throwable th3) {
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    stackPush.close();
                }
            }
            throw th3;
        }
    }

    protected FFIClosure(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public FFIClosure create(long j, ByteBuffer byteBuffer) {
        return new FFIClosure(j, byteBuffer);
    }

    public FFIClosure(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("ffi_cif *")
    public FFICIF cif() {
        return ncif(address());
    }

    @NativeType("void (*)(ffi_cif*,void*,void**,void*)")
    public long fun() {
        return nfun(address());
    }

    @NativeType("void *")
    public long user_data() {
        return nuser_data(address());
    }

    public static FFIClosure malloc() {
        return new FFIClosure(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static FFIClosure calloc() {
        return new FFIClosure(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static FFIClosure create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new FFIClosure(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static FFIClosure create(long j) {
        return new FFIClosure(j, null);
    }

    public static FFIClosure createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new FFIClosure(j, null);
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

    public static FFIClosure malloc(MemoryStack memoryStack) {
        return new FFIClosure(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static FFIClosure calloc(MemoryStack memoryStack) {
        return new FFIClosure(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static FFICIF ncif(long j) {
        return FFICIF.create(MemoryUtil.memGetAddress(j + CIF));
    }

    public static long nfun(long j) {
        return MemoryUtil.memGetAddress(j + FUN);
    }

    public static long nuser_data(long j) {
        return MemoryUtil.memGetAddress(j + USER_DATA);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/libffi/FFIClosure$Buffer.class */
    public static class Buffer extends StructBuffer<FFIClosure, Buffer> implements NativeResource {
        private static final FFIClosure ELEMENT_FACTORY = FFIClosure.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / FFIClosure.SIZEOF);
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
        public FFIClosure getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("ffi_cif *")
        public FFICIF cif() {
            return FFIClosure.ncif(address());
        }

        @NativeType("void (*)(ffi_cif*,void*,void**,void*)")
        public long fun() {
            return FFIClosure.nfun(address());
        }

        @NativeType("void *")
        public long user_data() {
            return FFIClosure.nuser_data(address());
        }
    }
}
