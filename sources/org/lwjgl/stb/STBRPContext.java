package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBRPNode;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbrp_context")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBRPContext.class */
public class STBRPContext extends Struct<STBRPContext> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int WIDTH;
    public static final int HEIGHT;
    public static final int ALIGN;
    public static final int INIT_MODE;
    public static final int HEURISTIC;
    public static final int NUM_NODES;
    public static final int ACTIVE_HEAD;
    public static final int FREE_HEAD;
    public static final int EXTRA;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE), __array(STBRPNode.SIZEOF, STBRPNode.ALIGNOF, 2));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        WIDTH = __struct.offsetof(0);
        HEIGHT = __struct.offsetof(1);
        ALIGN = __struct.offsetof(2);
        INIT_MODE = __struct.offsetof(3);
        HEURISTIC = __struct.offsetof(4);
        NUM_NODES = __struct.offsetof(5);
        ACTIVE_HEAD = __struct.offsetof(6);
        FREE_HEAD = __struct.offsetof(7);
        EXTRA = __struct.offsetof(8);
    }

    protected STBRPContext(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBRPContext create(long j, ByteBuffer byteBuffer) {
        return new STBRPContext(j, byteBuffer);
    }

    public STBRPContext(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int width() {
        return nwidth(address());
    }

    public int height() {
        return nheight(address());
    }

    public int align() {
        return nalign(address());
    }

    public int init_mode() {
        return ninit_mode(address());
    }

    public int heuristic() {
        return nheuristic(address());
    }

    public int num_nodes() {
        return nnum_nodes(address());
    }

    @NativeType("stbrp_node *")
    public STBRPNode active_head() {
        return nactive_head(address());
    }

    @NativeType("stbrp_node *")
    public STBRPNode free_head() {
        return nfree_head(address());
    }

    @NativeType("stbrp_node[2]")
    public STBRPNode.Buffer extra() {
        return nextra(address());
    }

    @NativeType("stbrp_node")
    public STBRPNode extra(int i) {
        return nextra(address(), i);
    }

    public static STBRPContext malloc() {
        return new STBRPContext(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBRPContext calloc() {
        return new STBRPContext(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBRPContext create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBRPContext(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBRPContext create(long j) {
        return new STBRPContext(j, null);
    }

    public static STBRPContext createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBRPContext(j, null);
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
    public static STBRPContext mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBRPContext callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBRPContext mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBRPContext callocStack(MemoryStack memoryStack) {
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

    public static STBRPContext malloc(MemoryStack memoryStack) {
        return new STBRPContext(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBRPContext calloc(MemoryStack memoryStack) {
        return new STBRPContext(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nwidth(long j) {
        return UNSAFE.getInt((Object) null, j + WIDTH);
    }

    public static int nheight(long j) {
        return UNSAFE.getInt((Object) null, j + HEIGHT);
    }

    public static int nalign(long j) {
        return UNSAFE.getInt((Object) null, j + ALIGN);
    }

    public static int ninit_mode(long j) {
        return UNSAFE.getInt((Object) null, j + INIT_MODE);
    }

    public static int nheuristic(long j) {
        return UNSAFE.getInt((Object) null, j + HEURISTIC);
    }

    public static int nnum_nodes(long j) {
        return UNSAFE.getInt((Object) null, j + NUM_NODES);
    }

    public static STBRPNode nactive_head(long j) {
        return STBRPNode.createSafe(MemoryUtil.memGetAddress(j + ACTIVE_HEAD));
    }

    public static STBRPNode nfree_head(long j) {
        return STBRPNode.createSafe(MemoryUtil.memGetAddress(j + FREE_HEAD));
    }

    public static STBRPNode.Buffer nextra(long j) {
        return STBRPNode.create(j + EXTRA, 2);
    }

    public static STBRPNode nextra(long j, int i) {
        return STBRPNode.create(j + EXTRA + (Checks.check(i, 2) * STBRPNode.SIZEOF));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBRPContext$Buffer.class */
    public static class Buffer extends StructBuffer<STBRPContext, Buffer> implements NativeResource {
        private static final STBRPContext ELEMENT_FACTORY = STBRPContext.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBRPContext.SIZEOF);
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
        public STBRPContext getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int width() {
            return STBRPContext.nwidth(address());
        }

        public int height() {
            return STBRPContext.nheight(address());
        }

        public int align() {
            return STBRPContext.nalign(address());
        }

        public int init_mode() {
            return STBRPContext.ninit_mode(address());
        }

        public int heuristic() {
            return STBRPContext.nheuristic(address());
        }

        public int num_nodes() {
            return STBRPContext.nnum_nodes(address());
        }

        @NativeType("stbrp_node *")
        public STBRPNode active_head() {
            return STBRPContext.nactive_head(address());
        }

        @NativeType("stbrp_node *")
        public STBRPNode free_head() {
            return STBRPContext.nfree_head(address());
        }

        @NativeType("stbrp_node[2]")
        public STBRPNode.Buffer extra() {
            return STBRPContext.nextra(address());
        }

        @NativeType("stbrp_node")
        public STBRPNode extra(int i) {
            return STBRPContext.nextra(address(), i);
        }
    }
}
