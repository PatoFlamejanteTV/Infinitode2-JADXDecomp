package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBRPNode;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_pack_context")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTPackContext.class */
public class STBTTPackContext extends Struct<STBTTPackContext> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int USER_ALLOCATOR_CONTEXT;
    public static final int PACK_INFO;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int STRIDE_IN_BYTES;
    public static final int PADDING;
    public static final int SKIP_MISSING;
    public static final int H_OVERSAMPLE;
    public static final int V_OVERSAMPLE;
    public static final int PIXELS;
    public static final int NODES;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        USER_ALLOCATOR_CONTEXT = __struct.offsetof(0);
        PACK_INFO = __struct.offsetof(1);
        WIDTH = __struct.offsetof(2);
        HEIGHT = __struct.offsetof(3);
        STRIDE_IN_BYTES = __struct.offsetof(4);
        PADDING = __struct.offsetof(5);
        SKIP_MISSING = __struct.offsetof(6);
        H_OVERSAMPLE = __struct.offsetof(7);
        V_OVERSAMPLE = __struct.offsetof(8);
        PIXELS = __struct.offsetof(9);
        NODES = __struct.offsetof(10);
    }

    protected STBTTPackContext(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTPackContext create(long j, ByteBuffer byteBuffer) {
        return new STBTTPackContext(j, byteBuffer);
    }

    public STBTTPackContext(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("void *")
    public long user_allocator_context() {
        return nuser_allocator_context(address());
    }

    @NativeType("stbrp_context *")
    public STBRPContext pack_info() {
        return npack_info(address());
    }

    public int width() {
        return nwidth(address());
    }

    public int height() {
        return nheight(address());
    }

    public int stride_in_bytes() {
        return nstride_in_bytes(address());
    }

    public int padding() {
        return npadding(address());
    }

    @NativeType("int")
    public boolean skip_missing() {
        return nskip_missing(address()) != 0;
    }

    @NativeType("unsigned int")
    public int h_oversample() {
        return nh_oversample(address());
    }

    @NativeType("unsigned int")
    public int v_oversample() {
        return nv_oversample(address());
    }

    @NativeType("unsigned char *")
    public ByteBuffer pixels(int i) {
        return npixels(address(), i);
    }

    @NativeType("stbrp_node *")
    public STBRPNode.Buffer nodes(int i) {
        return nnodes(address(), i);
    }

    public static STBTTPackContext malloc() {
        return new STBTTPackContext(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTPackContext calloc() {
        return new STBTTPackContext(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTPackContext create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTPackContext(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTPackContext create(long j) {
        return new STBTTPackContext(j, null);
    }

    public static STBTTPackContext createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTPackContext(j, null);
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
    public static STBTTPackContext mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTPackContext callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTPackContext mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTPackContext callocStack(MemoryStack memoryStack) {
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

    public static STBTTPackContext malloc(MemoryStack memoryStack) {
        return new STBTTPackContext(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTPackContext calloc(MemoryStack memoryStack) {
        return new STBTTPackContext(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nuser_allocator_context(long j) {
        return MemoryUtil.memGetAddress(j + USER_ALLOCATOR_CONTEXT);
    }

    public static STBRPContext npack_info(long j) {
        return STBRPContext.create(MemoryUtil.memGetAddress(j + PACK_INFO));
    }

    public static int nwidth(long j) {
        return UNSAFE.getInt((Object) null, j + WIDTH);
    }

    public static int nheight(long j) {
        return UNSAFE.getInt((Object) null, j + HEIGHT);
    }

    public static int nstride_in_bytes(long j) {
        return UNSAFE.getInt((Object) null, j + STRIDE_IN_BYTES);
    }

    public static int npadding(long j) {
        return UNSAFE.getInt((Object) null, j + PADDING);
    }

    public static int nskip_missing(long j) {
        return UNSAFE.getInt((Object) null, j + SKIP_MISSING);
    }

    public static int nh_oversample(long j) {
        return UNSAFE.getInt((Object) null, j + H_OVERSAMPLE);
    }

    public static int nv_oversample(long j) {
        return UNSAFE.getInt((Object) null, j + V_OVERSAMPLE);
    }

    public static ByteBuffer npixels(long j, int i) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + PIXELS), i);
    }

    public static STBRPNode.Buffer nnodes(long j, int i) {
        return STBRPNode.create(MemoryUtil.memGetAddress(j + NODES), i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTPackContext$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTPackContext, Buffer> implements NativeResource {
        private static final STBTTPackContext ELEMENT_FACTORY = STBTTPackContext.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTPackContext.SIZEOF);
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
        public STBTTPackContext getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("void *")
        public long user_allocator_context() {
            return STBTTPackContext.nuser_allocator_context(address());
        }

        @NativeType("stbrp_context *")
        public STBRPContext pack_info() {
            return STBTTPackContext.npack_info(address());
        }

        public int width() {
            return STBTTPackContext.nwidth(address());
        }

        public int height() {
            return STBTTPackContext.nheight(address());
        }

        public int stride_in_bytes() {
            return STBTTPackContext.nstride_in_bytes(address());
        }

        public int padding() {
            return STBTTPackContext.npadding(address());
        }

        @NativeType("int")
        public boolean skip_missing() {
            return STBTTPackContext.nskip_missing(address()) != 0;
        }

        @NativeType("unsigned int")
        public int h_oversample() {
            return STBTTPackContext.nh_oversample(address());
        }

        @NativeType("unsigned int")
        public int v_oversample() {
            return STBTTPackContext.nv_oversample(address());
        }

        @NativeType("unsigned char *")
        public ByteBuffer pixels(int i) {
            return STBTTPackContext.npixels(address(), i);
        }

        @NativeType("stbrp_node *")
        public STBRPNode.Buffer nodes(int i) {
            return STBTTPackContext.nnodes(address(), i);
        }
    }
}
