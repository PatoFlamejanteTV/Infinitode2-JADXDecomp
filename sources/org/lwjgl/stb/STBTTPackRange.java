package org.lwjgl.stb;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTPackedchar;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbtt_pack_range")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTPackRange.class */
public class STBTTPackRange extends Struct<STBTTPackRange> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int FONT_SIZE;
    public static final int FIRST_UNICODE_CODEPOINT_IN_RANGE;
    public static final int ARRAY_OF_UNICODE_CODEPOINTS;
    public static final int NUM_CHARS;
    public static final int CHARDATA_FOR_RANGE;
    public static final int H_OVERSAMPLE;
    public static final int V_OVERSAMPLE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(4), __member(POINTER_SIZE), __member(4), __member(POINTER_SIZE), __member(1), __member(1));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        FONT_SIZE = __struct.offsetof(0);
        FIRST_UNICODE_CODEPOINT_IN_RANGE = __struct.offsetof(1);
        ARRAY_OF_UNICODE_CODEPOINTS = __struct.offsetof(2);
        NUM_CHARS = __struct.offsetof(3);
        CHARDATA_FOR_RANGE = __struct.offsetof(4);
        H_OVERSAMPLE = __struct.offsetof(5);
        V_OVERSAMPLE = __struct.offsetof(6);
    }

    protected STBTTPackRange(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBTTPackRange create(long j, ByteBuffer byteBuffer) {
        return new STBTTPackRange(j, byteBuffer);
    }

    public STBTTPackRange(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    public float font_size() {
        return nfont_size(address());
    }

    public int first_unicode_codepoint_in_range() {
        return nfirst_unicode_codepoint_in_range(address());
    }

    @NativeType("int *")
    public IntBuffer array_of_unicode_codepoints() {
        return narray_of_unicode_codepoints(address());
    }

    public int num_chars() {
        return nnum_chars(address());
    }

    @NativeType("stbtt_packedchar *")
    public STBTTPackedchar.Buffer chardata_for_range() {
        return nchardata_for_range(address());
    }

    @NativeType("unsigned char")
    public byte h_oversample() {
        return nh_oversample(address());
    }

    @NativeType("unsigned char")
    public byte v_oversample() {
        return nv_oversample(address());
    }

    public STBTTPackRange font_size(float f) {
        nfont_size(address(), f);
        return this;
    }

    public STBTTPackRange first_unicode_codepoint_in_range(int i) {
        nfirst_unicode_codepoint_in_range(address(), i);
        return this;
    }

    public STBTTPackRange array_of_unicode_codepoints(@NativeType("int *") IntBuffer intBuffer) {
        narray_of_unicode_codepoints(address(), intBuffer);
        return this;
    }

    public STBTTPackRange num_chars(int i) {
        nnum_chars(address(), i);
        return this;
    }

    public STBTTPackRange chardata_for_range(@NativeType("stbtt_packedchar *") STBTTPackedchar.Buffer buffer) {
        nchardata_for_range(address(), buffer);
        return this;
    }

    public STBTTPackRange h_oversample(@NativeType("unsigned char") byte b2) {
        nh_oversample(address(), b2);
        return this;
    }

    public STBTTPackRange v_oversample(@NativeType("unsigned char") byte b2) {
        nv_oversample(address(), b2);
        return this;
    }

    public STBTTPackRange set(float f, int i, IntBuffer intBuffer, int i2, STBTTPackedchar.Buffer buffer, byte b2, byte b3) {
        font_size(f);
        first_unicode_codepoint_in_range(i);
        array_of_unicode_codepoints(intBuffer);
        num_chars(i2);
        chardata_for_range(buffer);
        h_oversample(b2);
        v_oversample(b3);
        return this;
    }

    public STBTTPackRange set(STBTTPackRange sTBTTPackRange) {
        MemoryUtil.memCopy(sTBTTPackRange.address(), address(), SIZEOF);
        return this;
    }

    public static STBTTPackRange malloc() {
        return new STBTTPackRange(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBTTPackRange calloc() {
        return new STBTTPackRange(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBTTPackRange create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBTTPackRange(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBTTPackRange create(long j) {
        return new STBTTPackRange(j, null);
    }

    public static STBTTPackRange createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBTTPackRange(j, null);
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
    public static STBTTPackRange mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTPackRange callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBTTPackRange mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBTTPackRange callocStack(MemoryStack memoryStack) {
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

    public static STBTTPackRange malloc(MemoryStack memoryStack) {
        return new STBTTPackRange(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBTTPackRange calloc(MemoryStack memoryStack) {
        return new STBTTPackRange(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static float nfont_size(long j) {
        return UNSAFE.getFloat((Object) null, j + FONT_SIZE);
    }

    public static int nfirst_unicode_codepoint_in_range(long j) {
        return UNSAFE.getInt((Object) null, j + FIRST_UNICODE_CODEPOINT_IN_RANGE);
    }

    public static IntBuffer narray_of_unicode_codepoints(long j) {
        return MemoryUtil.memIntBufferSafe(MemoryUtil.memGetAddress(j + ARRAY_OF_UNICODE_CODEPOINTS), nnum_chars(j));
    }

    public static int nnum_chars(long j) {
        return UNSAFE.getInt((Object) null, j + NUM_CHARS);
    }

    public static STBTTPackedchar.Buffer nchardata_for_range(long j) {
        return STBTTPackedchar.create(MemoryUtil.memGetAddress(j + CHARDATA_FOR_RANGE), nnum_chars(j));
    }

    public static byte nh_oversample(long j) {
        return UNSAFE.getByte((Object) null, j + H_OVERSAMPLE);
    }

    public static byte nv_oversample(long j) {
        return UNSAFE.getByte((Object) null, j + V_OVERSAMPLE);
    }

    public static void nfont_size(long j, float f) {
        UNSAFE.putFloat((Object) null, j + FONT_SIZE, f);
    }

    public static void nfirst_unicode_codepoint_in_range(long j, int i) {
        UNSAFE.putInt((Object) null, j + FIRST_UNICODE_CODEPOINT_IN_RANGE, i);
    }

    public static void narray_of_unicode_codepoints(long j, IntBuffer intBuffer) {
        MemoryUtil.memPutAddress(j + ARRAY_OF_UNICODE_CODEPOINTS, MemoryUtil.memAddressSafe(intBuffer));
    }

    public static void nnum_chars(long j, int i) {
        UNSAFE.putInt((Object) null, j + NUM_CHARS, i);
    }

    public static void nchardata_for_range(long j, STBTTPackedchar.Buffer buffer) {
        MemoryUtil.memPutAddress(j + CHARDATA_FOR_RANGE, buffer.address());
    }

    public static void nh_oversample(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + H_OVERSAMPLE, b2);
    }

    public static void nv_oversample(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + V_OVERSAMPLE, b2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + CHARDATA_FOR_RANGE));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBTTPackRange$Buffer.class */
    public static class Buffer extends StructBuffer<STBTTPackRange, Buffer> implements NativeResource {
        private static final STBTTPackRange ELEMENT_FACTORY = STBTTPackRange.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBTTPackRange.SIZEOF);
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
        public STBTTPackRange getElementFactory() {
            return ELEMENT_FACTORY;
        }

        public float font_size() {
            return STBTTPackRange.nfont_size(address());
        }

        public int first_unicode_codepoint_in_range() {
            return STBTTPackRange.nfirst_unicode_codepoint_in_range(address());
        }

        @NativeType("int *")
        public IntBuffer array_of_unicode_codepoints() {
            return STBTTPackRange.narray_of_unicode_codepoints(address());
        }

        public int num_chars() {
            return STBTTPackRange.nnum_chars(address());
        }

        @NativeType("stbtt_packedchar *")
        public STBTTPackedchar.Buffer chardata_for_range() {
            return STBTTPackRange.nchardata_for_range(address());
        }

        @NativeType("unsigned char")
        public byte h_oversample() {
            return STBTTPackRange.nh_oversample(address());
        }

        @NativeType("unsigned char")
        public byte v_oversample() {
            return STBTTPackRange.nv_oversample(address());
        }

        public Buffer font_size(float f) {
            STBTTPackRange.nfont_size(address(), f);
            return this;
        }

        public Buffer first_unicode_codepoint_in_range(int i) {
            STBTTPackRange.nfirst_unicode_codepoint_in_range(address(), i);
            return this;
        }

        public Buffer array_of_unicode_codepoints(@NativeType("int *") IntBuffer intBuffer) {
            STBTTPackRange.narray_of_unicode_codepoints(address(), intBuffer);
            return this;
        }

        public Buffer num_chars(int i) {
            STBTTPackRange.nnum_chars(address(), i);
            return this;
        }

        public Buffer chardata_for_range(@NativeType("stbtt_packedchar *") STBTTPackedchar.Buffer buffer) {
            STBTTPackRange.nchardata_for_range(address(), buffer);
            return this;
        }

        public Buffer h_oversample(@NativeType("unsigned char") byte b2) {
            STBTTPackRange.nh_oversample(address(), b2);
            return this;
        }

        public Buffer v_oversample(@NativeType("unsigned char") byte b2) {
            STBTTPackRange.nv_oversample(address(), b2);
            return this;
        }
    }
}
