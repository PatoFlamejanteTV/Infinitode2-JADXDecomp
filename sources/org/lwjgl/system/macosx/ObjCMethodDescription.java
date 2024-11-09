package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct objc_method_description")
/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/ObjCMethodDescription.class */
public class ObjCMethodDescription extends Struct<ObjCMethodDescription> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NAME;
    public static final int TYPES;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NAME = __struct.offsetof(0);
        TYPES = __struct.offsetof(1);
    }

    protected ObjCMethodDescription(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public ObjCMethodDescription create(long j, ByteBuffer byteBuffer) {
        return new ObjCMethodDescription(j, byteBuffer);
    }

    public ObjCMethodDescription(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("SEL")
    public long name() {
        return nname(address());
    }

    @NativeType("char *")
    public ByteBuffer types() {
        return ntypes(address());
    }

    @NativeType("char *")
    public String typesString() {
        return ntypesString(address());
    }

    public static ObjCMethodDescription malloc() {
        return new ObjCMethodDescription(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static ObjCMethodDescription calloc() {
        return new ObjCMethodDescription(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static ObjCMethodDescription create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new ObjCMethodDescription(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static ObjCMethodDescription create(long j) {
        return new ObjCMethodDescription(j, null);
    }

    public static ObjCMethodDescription createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new ObjCMethodDescription(j, null);
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
    public static ObjCMethodDescription mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static ObjCMethodDescription callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static ObjCMethodDescription mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static ObjCMethodDescription callocStack(MemoryStack memoryStack) {
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

    public static ObjCMethodDescription malloc(MemoryStack memoryStack) {
        return new ObjCMethodDescription(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static ObjCMethodDescription calloc(MemoryStack memoryStack) {
        return new ObjCMethodDescription(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nname(long j) {
        return MemoryUtil.memGetAddress(j + NAME);
    }

    public static ByteBuffer ntypes(long j) {
        return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(j + TYPES));
    }

    public static String ntypesString(long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(j + TYPES));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/ObjCMethodDescription$Buffer.class */
    public static class Buffer extends StructBuffer<ObjCMethodDescription, Buffer> implements NativeResource {
        private static final ObjCMethodDescription ELEMENT_FACTORY = ObjCMethodDescription.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / ObjCMethodDescription.SIZEOF);
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
        public ObjCMethodDescription getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("SEL")
        public long name() {
            return ObjCMethodDescription.nname(address());
        }

        @NativeType("char *")
        public ByteBuffer types() {
            return ObjCMethodDescription.ntypes(address());
        }

        @NativeType("char *")
        public String typesString() {
            return ObjCMethodDescription.ntypesString(address());
        }
    }
}
