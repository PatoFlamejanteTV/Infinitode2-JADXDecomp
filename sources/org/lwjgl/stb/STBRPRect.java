package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbrp_rect")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBRPRect.class */
public class STBRPRect extends Struct<STBRPRect> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int ID;
    public static final int W;
    public static final int H;
    public static final int X;
    public static final int Y;
    public static final int WAS_PACKED;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        ID = __struct.offsetof(0);
        W = __struct.offsetof(1);
        H = __struct.offsetof(2);
        X = __struct.offsetof(3);
        Y = __struct.offsetof(4);
        WAS_PACKED = __struct.offsetof(5);
    }

    protected STBRPRect(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBRPRect create(long j, ByteBuffer byteBuffer) {
        return new STBRPRect(j, byteBuffer);
    }

    public STBRPRect(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public int id() {
        return nid(address());
    }

    @NativeType("stbrp_coord")
    public int w() {
        return nw(address());
    }

    @NativeType("stbrp_coord")
    public int h() {
        return nh(address());
    }

    @NativeType("stbrp_coord")
    public int x() {
        return nx(address());
    }

    @NativeType("stbrp_coord")
    public int y() {
        return ny(address());
    }

    @NativeType("int")
    public boolean was_packed() {
        return nwas_packed(address()) != 0;
    }

    public STBRPRect id(int i) {
        nid(address(), i);
        return this;
    }

    public STBRPRect w(@NativeType("stbrp_coord") int i) {
        nw(address(), i);
        return this;
    }

    public STBRPRect h(@NativeType("stbrp_coord") int i) {
        nh(address(), i);
        return this;
    }

    public STBRPRect x(@NativeType("stbrp_coord") int i) {
        nx(address(), i);
        return this;
    }

    public STBRPRect y(@NativeType("stbrp_coord") int i) {
        ny(address(), i);
        return this;
    }

    public STBRPRect was_packed(@NativeType("int") boolean z) {
        nwas_packed(address(), z ? 1 : 0);
        return this;
    }

    public STBRPRect set(int i, int i2, int i3, int i4, int i5, boolean z) {
        id(i);
        w(i2);
        h(i3);
        x(i4);
        y(i5);
        was_packed(z);
        return this;
    }

    public STBRPRect set(STBRPRect sTBRPRect) {
        MemoryUtil.memCopy(sTBRPRect.address(), address(), SIZEOF);
        return this;
    }

    public static STBRPRect malloc() {
        return new STBRPRect(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBRPRect calloc() {
        return new STBRPRect(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBRPRect create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBRPRect(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBRPRect create(long j) {
        return new STBRPRect(j, null);
    }

    public static STBRPRect createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBRPRect(j, null);
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
    public static STBRPRect mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBRPRect callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBRPRect mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBRPRect callocStack(MemoryStack memoryStack) {
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

    public static STBRPRect malloc(MemoryStack memoryStack) {
        return new STBRPRect(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBRPRect calloc(MemoryStack memoryStack) {
        return new STBRPRect(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nid(long j) {
        return UNSAFE.getInt((Object) null, j + ID);
    }

    public static int nw(long j) {
        return UNSAFE.getInt((Object) null, j + W);
    }

    public static int nh(long j) {
        return UNSAFE.getInt((Object) null, j + H);
    }

    public static int nx(long j) {
        return UNSAFE.getInt((Object) null, j + X);
    }

    public static int ny(long j) {
        return UNSAFE.getInt((Object) null, j + Y);
    }

    public static int nwas_packed(long j) {
        return UNSAFE.getInt((Object) null, j + WAS_PACKED);
    }

    public static void nid(long j, int i) {
        UNSAFE.putInt((Object) null, j + ID, i);
    }

    public static void nw(long j, int i) {
        UNSAFE.putInt((Object) null, j + W, i);
    }

    public static void nh(long j, int i) {
        UNSAFE.putInt((Object) null, j + H, i);
    }

    public static void nx(long j, int i) {
        UNSAFE.putInt((Object) null, j + X, i);
    }

    public static void ny(long j, int i) {
        UNSAFE.putInt((Object) null, j + Y, i);
    }

    public static void nwas_packed(long j, int i) {
        UNSAFE.putInt((Object) null, j + WAS_PACKED, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBRPRect$Buffer.class */
    public static class Buffer extends StructBuffer<STBRPRect, Buffer> implements NativeResource {
        private static final STBRPRect ELEMENT_FACTORY = STBRPRect.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBRPRect.SIZEOF);
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
        public STBRPRect getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public int id() {
            return STBRPRect.nid(address());
        }

        @NativeType("stbrp_coord")
        public int w() {
            return STBRPRect.nw(address());
        }

        @NativeType("stbrp_coord")
        public int h() {
            return STBRPRect.nh(address());
        }

        @NativeType("stbrp_coord")
        public int x() {
            return STBRPRect.nx(address());
        }

        @NativeType("stbrp_coord")
        public int y() {
            return STBRPRect.ny(address());
        }

        @NativeType("int")
        public boolean was_packed() {
            return STBRPRect.nwas_packed(address()) != 0;
        }

        public Buffer id(int i) {
            STBRPRect.nid(address(), i);
            return this;
        }

        public Buffer w(@NativeType("stbrp_coord") int i) {
            STBRPRect.nw(address(), i);
            return this;
        }

        public Buffer h(@NativeType("stbrp_coord") int i) {
            STBRPRect.nh(address(), i);
            return this;
        }

        public Buffer x(@NativeType("stbrp_coord") int i) {
            STBRPRect.nx(address(), i);
            return this;
        }

        public Buffer y(@NativeType("stbrp_coord") int i) {
            STBRPRect.ny(address(), i);
            return this;
        }

        public Buffer was_packed(@NativeType("int") boolean z) {
            STBRPRect.nwas_packed(address(), z ? 1 : 0);
            return this;
        }
    }
}
