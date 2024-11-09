package org.lwjgl.stb;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_fontinfo")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTFontinfo.class */
public class STBTTFontinfo extends Struct<STBTTFontinfo> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;

    private static native int offsets(long j);

    static {
        LibSTB.initialize();
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

    protected STBTTFontinfo(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTFontinfo create(long j, ByteBuffer byteBuffer) {
        return new STBTTFontinfo(j, byteBuffer);
    }

    public STBTTFontinfo(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public static STBTTFontinfo malloc() {
        return new STBTTFontinfo(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTFontinfo calloc() {
        return new STBTTFontinfo(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTFontinfo create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTFontinfo(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTFontinfo create(long j) {
        return new STBTTFontinfo(j, null);
    }

    public static STBTTFontinfo createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTFontinfo(j, null);
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
    public static STBTTFontinfo mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTFontinfo callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTFontinfo mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTFontinfo callocStack(MemoryStack memoryStack) {
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

    public static STBTTFontinfo malloc(MemoryStack memoryStack) {
        return new STBTTFontinfo(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTFontinfo calloc(MemoryStack memoryStack) {
        return new STBTTFontinfo(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTFontinfo$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTFontinfo, Buffer> implements NativeResource {
        private static final STBTTFontinfo ELEMENT_FACTORY = STBTTFontinfo.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTFontinfo.SIZEOF);
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
        public STBTTFontinfo getElementFactory() {
            return ELEMENT_FACTORY;
        }
    }
}
