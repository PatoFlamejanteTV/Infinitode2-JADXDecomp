package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stb_vorbis_comment")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBVorbisComment.class */
public class STBVorbisComment extends Struct<STBVorbisComment> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int VENDOR;
    public static final int COMMENT_LIST_LENGTH;
    public static final int COMMENT_LIST;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(4), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        VENDOR = __struct.offsetof(0);
        COMMENT_LIST_LENGTH = __struct.offsetof(1);
        COMMENT_LIST = __struct.offsetof(2);
    }

    protected STBVorbisComment(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBVorbisComment create(long j, ByteBuffer byteBuffer) {
        return new STBVorbisComment(j, byteBuffer);
    }

    public STBVorbisComment(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("char *")
    public ByteBuffer vendor() {
        return nvendor(address());
    }

    @NativeType("char *")
    public String vendorString() {
        return nvendorString(address());
    }

    public int comment_list_length() {
        return ncomment_list_length(address());
    }

    @NativeType("char **")
    public PointerBuffer comment_list() {
        return ncomment_list(address());
    }

    public static STBVorbisComment malloc() {
        return new STBVorbisComment(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBVorbisComment calloc() {
        return new STBVorbisComment(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBVorbisComment create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBVorbisComment(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBVorbisComment create(long j) {
        return new STBVorbisComment(j, null);
    }

    public static STBVorbisComment createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBVorbisComment(j, null);
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

    public static STBVorbisComment malloc(MemoryStack memoryStack) {
        return new STBVorbisComment(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBVorbisComment calloc(MemoryStack memoryStack) {
        return new STBVorbisComment(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ByteBuffer nvendor(long j) {
        return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(j + VENDOR));
    }

    public static String nvendorString(long j) {
        return MemoryUtil.memASCII(MemoryUtil.memGetAddress(j + VENDOR));
    }

    public static int ncomment_list_length(long j) {
        return UNSAFE.getInt((Object) null, j + COMMENT_LIST_LENGTH);
    }

    public static PointerBuffer ncomment_list(long j) {
        return MemoryUtil.memPointerBuffer(MemoryUtil.memGetAddress(j + COMMENT_LIST), ncomment_list_length(j));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBVorbisComment$Buffer.class */
    public static class Buffer extends StructBuffer<STBVorbisComment, Buffer> implements NativeResource {
        private static final STBVorbisComment ELEMENT_FACTORY = STBVorbisComment.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBVorbisComment.SIZEOF);
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
        public STBVorbisComment getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("char *")
        public ByteBuffer vendor() {
            return STBVorbisComment.nvendor(address());
        }

        @NativeType("char *")
        public String vendorString() {
            return STBVorbisComment.nvendorString(address());
        }

        public int comment_list_length() {
            return STBVorbisComment.ncomment_list_length(address());
        }

        @NativeType("char **")
        public PointerBuffer comment_list() {
            return STBVorbisComment.ncomment_list(address());
        }
    }
}
