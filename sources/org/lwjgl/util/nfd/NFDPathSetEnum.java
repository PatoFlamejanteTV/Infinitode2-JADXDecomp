package org.lwjgl.util.nfd;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct nfdpathsetenum_t")
/* loaded from: infinitode-2.jar:org/lwjgl/util/nfd/NFDPathSetEnum.class */
public class NFDPathSetEnum extends Struct<NFDPathSetEnum> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;

    private static native int offsets(long j);

    static {
        LibNFD.initialize();
        MemoryStack stackPush = MemoryStack.stackPush();
        try {
            try {
                IntBuffer mallocInt = stackPush.mallocInt(1);
                SIZEOF = offsets(MemoryUtil.memAddress(mallocInt));
                ALIGNOF = mallocInt.get(0);
                if (stackPush != null) {
                    if (r4 == null) {
                        stackPush.close();
                        return;
                    }
                    try {
                        stackPush.close();
                    } catch (Throwable th) {
                        r4.addSuppressed(th);
                    }
                }
            } catch (Throwable th2) {
                if (stackPush != null) {
                    if (r4 != null) {
                        try {
                            stackPush.close();
                        } catch (Throwable th3) {
                            r4.addSuppressed(th3);
                        }
                    } else {
                        stackPush.close();
                    }
                }
                throw th2;
            }
        } finally {
            r4 = null;
        }
    }

    protected NFDPathSetEnum(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public NFDPathSetEnum create(long j, ByteBuffer byteBuffer) {
        return new NFDPathSetEnum(j, byteBuffer);
    }

    public NFDPathSetEnum(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public static NFDPathSetEnum malloc() {
        return new NFDPathSetEnum(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static NFDPathSetEnum calloc() {
        return new NFDPathSetEnum(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static NFDPathSetEnum create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new NFDPathSetEnum(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static NFDPathSetEnum create(long j) {
        return new NFDPathSetEnum(j, null);
    }

    public static NFDPathSetEnum createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new NFDPathSetEnum(j, null);
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

    public static NFDPathSetEnum malloc(MemoryStack memoryStack) {
        return new NFDPathSetEnum(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static NFDPathSetEnum calloc(MemoryStack memoryStack) {
        return new NFDPathSetEnum(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/util/nfd/NFDPathSetEnum$Buffer.class */
    public static class Buffer extends StructBuffer<NFDPathSetEnum, Buffer> implements NativeResource {
        private static final NFDPathSetEnum ELEMENT_FACTORY = NFDPathSetEnum.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / NFDPathSetEnum.SIZEOF);
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
        public NFDPathSetEnum getElementFactory() {
            return ELEMENT_FACTORY;
        }
    }
}
