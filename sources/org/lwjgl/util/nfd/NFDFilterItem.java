package org.lwjgl.util.nfd;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct nfdfilteritem_t")
/* loaded from: infinitode-2.jar:org/lwjgl/util/nfd/NFDFilterItem.class */
public class NFDFilterItem extends Struct<NFDFilterItem> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NAME;
    public static final int SPEC;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NAME = __struct.offsetof(0);
        SPEC = __struct.offsetof(1);
    }

    protected NFDFilterItem(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public NFDFilterItem create(long j, ByteBuffer byteBuffer) {
        return new NFDFilterItem(j, byteBuffer);
    }

    public NFDFilterItem(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("nfdchar_t const *")
    public ByteBuffer name() {
        return nname(address());
    }

    @NativeType("nfdchar_t const *")
    public String nameString() {
        return nnameString(address());
    }

    @NativeType("nfdchar_t const *")
    public ByteBuffer spec() {
        return nspec(address());
    }

    @NativeType("nfdchar_t const *")
    public String specString() {
        return nspecString(address());
    }

    public NFDFilterItem name(@NativeType("nfdchar_t const *") ByteBuffer byteBuffer) {
        nname(address(), byteBuffer);
        return this;
    }

    public NFDFilterItem spec(@NativeType("nfdchar_t const *") ByteBuffer byteBuffer) {
        nspec(address(), byteBuffer);
        return this;
    }

    public NFDFilterItem set(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        name(byteBuffer);
        spec(byteBuffer2);
        return this;
    }

    public NFDFilterItem set(NFDFilterItem nFDFilterItem) {
        MemoryUtil.memCopy(nFDFilterItem.address(), address(), SIZEOF);
        return this;
    }

    public static NFDFilterItem malloc() {
        return new NFDFilterItem(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static NFDFilterItem calloc() {
        return new NFDFilterItem(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static NFDFilterItem create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new NFDFilterItem(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static NFDFilterItem create(long j) {
        return new NFDFilterItem(j, null);
    }

    public static NFDFilterItem createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new NFDFilterItem(j, null);
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

    public static NFDFilterItem malloc(MemoryStack memoryStack) {
        return new NFDFilterItem(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static NFDFilterItem calloc(MemoryStack memoryStack) {
        return new NFDFilterItem(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ByteBuffer nname(long j) {
        return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(j + NAME));
    }

    public static String nnameString(long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(j + NAME));
    }

    public static ByteBuffer nspec(long j) {
        return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(j + SPEC));
    }

    public static String nspecString(long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(j + SPEC));
    }

    public static void nname(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + NAME, MemoryUtil.memAddress(byteBuffer));
    }

    public static void nspec(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + SPEC, MemoryUtil.memAddress(byteBuffer));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + NAME));
        Checks.check(MemoryUtil.memGetAddress(j + SPEC));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/util/nfd/NFDFilterItem$Buffer.class */
    public static class Buffer extends StructBuffer<NFDFilterItem, Buffer> implements NativeResource {
        private static final NFDFilterItem ELEMENT_FACTORY = NFDFilterItem.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / NFDFilterItem.SIZEOF);
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
        public NFDFilterItem getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("nfdchar_t const *")
        public ByteBuffer name() {
            return NFDFilterItem.nname(address());
        }

        @NativeType("nfdchar_t const *")
        public String nameString() {
            return NFDFilterItem.nnameString(address());
        }

        @NativeType("nfdchar_t const *")
        public ByteBuffer spec() {
            return NFDFilterItem.nspec(address());
        }

        @NativeType("nfdchar_t const *")
        public String specString() {
            return NFDFilterItem.nspecString(address());
        }

        public Buffer name(@NativeType("nfdchar_t const *") ByteBuffer byteBuffer) {
            NFDFilterItem.nname(address(), byteBuffer);
            return this;
        }

        public Buffer spec(@NativeType("nfdchar_t const *") ByteBuffer byteBuffer) {
            NFDFilterItem.nspec(address(), byteBuffer);
            return this;
        }
    }
}
