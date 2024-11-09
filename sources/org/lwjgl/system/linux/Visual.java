package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Visual.class */
public class Visual extends Struct<Visual> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int EXT_DATA;
    public static final int VISUALID;
    public static final int CLASS;
    public static final int RED_MASK;
    public static final int GREEN_MASK;
    public static final int BLUE_MASK;
    public static final int BITS_PER_RGB;
    public static final int MAP_ENTRIES;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(CLONG_SIZE), __member(4), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(CLONG_SIZE), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        EXT_DATA = __struct.offsetof(0);
        VISUALID = __struct.offsetof(1);
        CLASS = __struct.offsetof(2);
        RED_MASK = __struct.offsetof(3);
        GREEN_MASK = __struct.offsetof(4);
        BLUE_MASK = __struct.offsetof(5);
        BITS_PER_RGB = __struct.offsetof(6);
        MAP_ENTRIES = __struct.offsetof(7);
    }

    protected Visual(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public Visual create(long j, ByteBuffer byteBuffer) {
        return new Visual(j, byteBuffer);
    }

    public Visual(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("void *")
    public long ext_data() {
        return next_data(address());
    }

    @NativeType("VisualID")
    public long visualid() {
        return nvisualid(address());
    }

    public int class$() {
        return nclass$(address());
    }

    @NativeType("unsigned long")
    public long red_mask() {
        return nred_mask(address());
    }

    @NativeType("unsigned long")
    public long green_mask() {
        return ngreen_mask(address());
    }

    @NativeType("unsigned long")
    public long blue_mask() {
        return nblue_mask(address());
    }

    public int bits_per_rgb() {
        return nbits_per_rgb(address());
    }

    public int map_entries() {
        return nmap_entries(address());
    }

    public Visual ext_data(@NativeType("void *") long j) {
        next_data(address(), j);
        return this;
    }

    public Visual visualid(@NativeType("VisualID") long j) {
        nvisualid(address(), j);
        return this;
    }

    public Visual class$(int i) {
        nclass$(address(), i);
        return this;
    }

    public Visual red_mask(@NativeType("unsigned long") long j) {
        nred_mask(address(), j);
        return this;
    }

    public Visual green_mask(@NativeType("unsigned long") long j) {
        ngreen_mask(address(), j);
        return this;
    }

    public Visual blue_mask(@NativeType("unsigned long") long j) {
        nblue_mask(address(), j);
        return this;
    }

    public Visual bits_per_rgb(int i) {
        nbits_per_rgb(address(), i);
        return this;
    }

    public Visual map_entries(int i) {
        nmap_entries(address(), i);
        return this;
    }

    public Visual set(long j, long j2, int i, long j3, long j4, long j5, int i2, int i3) {
        ext_data(j);
        visualid(j2);
        class$(i);
        red_mask(j3);
        green_mask(j4);
        blue_mask(j5);
        bits_per_rgb(i2);
        map_entries(i3);
        return this;
    }

    public Visual set(Visual visual) {
        MemoryUtil.memCopy(visual.address(), address(), SIZEOF);
        return this;
    }

    public static Visual malloc() {
        return new Visual(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static Visual calloc() {
        return new Visual(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static Visual create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new Visual(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static Visual create(long j) {
        return new Visual(j, null);
    }

    public static Visual createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new Visual(j, null);
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
    public static Visual mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static Visual callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static Visual mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static Visual callocStack(MemoryStack memoryStack) {
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

    public static Visual malloc(MemoryStack memoryStack) {
        return new Visual(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static Visual calloc(MemoryStack memoryStack) {
        return new Visual(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long next_data(long j) {
        return MemoryUtil.memGetAddress(j + EXT_DATA);
    }

    public static long nvisualid(long j) {
        return MemoryUtil.memGetCLong(j + VISUALID);
    }

    public static int nclass$(long j) {
        return UNSAFE.getInt((Object) null, j + CLASS);
    }

    public static long nred_mask(long j) {
        return MemoryUtil.memGetCLong(j + RED_MASK);
    }

    public static long ngreen_mask(long j) {
        return MemoryUtil.memGetCLong(j + GREEN_MASK);
    }

    public static long nblue_mask(long j) {
        return MemoryUtil.memGetCLong(j + BLUE_MASK);
    }

    public static int nbits_per_rgb(long j) {
        return UNSAFE.getInt((Object) null, j + BITS_PER_RGB);
    }

    public static int nmap_entries(long j) {
        return UNSAFE.getInt((Object) null, j + MAP_ENTRIES);
    }

    public static void next_data(long j, long j2) {
        MemoryUtil.memPutAddress(j + EXT_DATA, j2);
    }

    public static void nvisualid(long j, long j2) {
        MemoryUtil.memPutCLong(j + VISUALID, j2);
    }

    public static void nclass$(long j, int i) {
        UNSAFE.putInt((Object) null, j + CLASS, i);
    }

    public static void nred_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + RED_MASK, j2);
    }

    public static void ngreen_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + GREEN_MASK, j2);
    }

    public static void nblue_mask(long j, long j2) {
        MemoryUtil.memPutCLong(j + BLUE_MASK, j2);
    }

    public static void nbits_per_rgb(long j, int i) {
        UNSAFE.putInt((Object) null, j + BITS_PER_RGB, i);
    }

    public static void nmap_entries(long j, int i) {
        UNSAFE.putInt((Object) null, j + MAP_ENTRIES, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Visual$Buffer.class */
    public static class Buffer extends StructBuffer<Visual, Buffer> implements NativeResource {
        private static final Visual ELEMENT_FACTORY = Visual.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / Visual.SIZEOF);
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
        public Visual getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("void *")
        public long ext_data() {
            return Visual.next_data(address());
        }

        @NativeType("VisualID")
        public long visualid() {
            return Visual.nvisualid(address());
        }

        public int class$() {
            return Visual.nclass$(address());
        }

        @NativeType("unsigned long")
        public long red_mask() {
            return Visual.nred_mask(address());
        }

        @NativeType("unsigned long")
        public long green_mask() {
            return Visual.ngreen_mask(address());
        }

        @NativeType("unsigned long")
        public long blue_mask() {
            return Visual.nblue_mask(address());
        }

        public int bits_per_rgb() {
            return Visual.nbits_per_rgb(address());
        }

        public int map_entries() {
            return Visual.nmap_entries(address());
        }

        public Buffer ext_data(@NativeType("void *") long j) {
            Visual.next_data(address(), j);
            return this;
        }

        public Buffer visualid(@NativeType("VisualID") long j) {
            Visual.nvisualid(address(), j);
            return this;
        }

        public Buffer class$(int i) {
            Visual.nclass$(address(), i);
            return this;
        }

        public Buffer red_mask(@NativeType("unsigned long") long j) {
            Visual.nred_mask(address(), j);
            return this;
        }

        public Buffer green_mask(@NativeType("unsigned long") long j) {
            Visual.ngreen_mask(address(), j);
            return this;
        }

        public Buffer blue_mask(@NativeType("unsigned long") long j) {
            Visual.nblue_mask(address(), j);
            return this;
        }

        public Buffer bits_per_rgb(int i) {
            Visual.nbits_per_rgb(address(), i);
            return this;
        }

        public Buffer map_entries(int i) {
            Visual.nmap_entries(address(), i);
            return this;
        }
    }
}
