package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stb_vorbis_info")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBVorbisInfo.class */
public class STBVorbisInfo extends Struct<STBVorbisInfo> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int SAMPLE_RATE;
    public static final int CHANNELS;
    public static final int SETUP_MEMORY_REQUIRED;
    public static final int SETUP_TEMP_MEMORY_REQUIRED;
    public static final int TEMP_MEMORY_REQUIRED;
    public static final int MAX_FRAME_SIZE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        SAMPLE_RATE = __struct.offsetof(0);
        CHANNELS = __struct.offsetof(1);
        SETUP_MEMORY_REQUIRED = __struct.offsetof(2);
        SETUP_TEMP_MEMORY_REQUIRED = __struct.offsetof(3);
        TEMP_MEMORY_REQUIRED = __struct.offsetof(4);
        MAX_FRAME_SIZE = __struct.offsetof(5);
    }

    protected STBVorbisInfo(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBVorbisInfo create(long j, ByteBuffer byteBuffer) {
        return new STBVorbisInfo(j, byteBuffer);
    }

    public STBVorbisInfo(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("unsigned int")
    public int sample_rate() {
        return nsample_rate(address());
    }

    public int channels() {
        return nchannels(address());
    }

    @NativeType("unsigned int")
    public int setup_memory_required() {
        return nsetup_memory_required(address());
    }

    @NativeType("unsigned int")
    public int setup_temp_memory_required() {
        return nsetup_temp_memory_required(address());
    }

    @NativeType("unsigned int")
    public int temp_memory_required() {
        return ntemp_memory_required(address());
    }

    public int max_frame_size() {
        return nmax_frame_size(address());
    }

    public static STBVorbisInfo malloc() {
        return new STBVorbisInfo(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBVorbisInfo calloc() {
        return new STBVorbisInfo(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBVorbisInfo create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBVorbisInfo(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBVorbisInfo create(long j) {
        return new STBVorbisInfo(j, null);
    }

    public static STBVorbisInfo createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBVorbisInfo(j, null);
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
    public static STBVorbisInfo mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBVorbisInfo callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBVorbisInfo mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBVorbisInfo callocStack(MemoryStack memoryStack) {
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

    public static STBVorbisInfo malloc(MemoryStack memoryStack) {
        return new STBVorbisInfo(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBVorbisInfo calloc(MemoryStack memoryStack) {
        return new STBVorbisInfo(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nsample_rate(long j) {
        return UNSAFE.getInt((Object) null, j + SAMPLE_RATE);
    }

    public static int nchannels(long j) {
        return UNSAFE.getInt((Object) null, j + CHANNELS);
    }

    public static int nsetup_memory_required(long j) {
        return UNSAFE.getInt((Object) null, j + SETUP_MEMORY_REQUIRED);
    }

    public static int nsetup_temp_memory_required(long j) {
        return UNSAFE.getInt((Object) null, j + SETUP_TEMP_MEMORY_REQUIRED);
    }

    public static int ntemp_memory_required(long j) {
        return UNSAFE.getInt((Object) null, j + TEMP_MEMORY_REQUIRED);
    }

    public static int nmax_frame_size(long j) {
        return UNSAFE.getInt((Object) null, j + MAX_FRAME_SIZE);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBVorbisInfo$Buffer.class */
    public static class Buffer extends StructBuffer<STBVorbisInfo, Buffer> implements NativeResource {
        private static final STBVorbisInfo ELEMENT_FACTORY = STBVorbisInfo.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBVorbisInfo.SIZEOF);
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
        public STBVorbisInfo getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("unsigned int")
        public int sample_rate() {
            return STBVorbisInfo.nsample_rate(address());
        }

        public int channels() {
            return STBVorbisInfo.nchannels(address());
        }

        @NativeType("unsigned int")
        public int setup_memory_required() {
            return STBVorbisInfo.nsetup_memory_required(address());
        }

        @NativeType("unsigned int")
        public int setup_temp_memory_required() {
            return STBVorbisInfo.nsetup_temp_memory_required(address());
        }

        @NativeType("unsigned int")
        public int temp_memory_required() {
            return STBVorbisInfo.ntemp_memory_required(address());
        }

        public int max_frame_size() {
            return STBVorbisInfo.nmax_frame_size(address());
        }
    }
}
