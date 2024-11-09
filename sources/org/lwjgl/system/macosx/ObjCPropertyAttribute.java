package org.lwjgl.system.macosx;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct objc_property_attribute_t")
/* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/ObjCPropertyAttribute.class */
public class ObjCPropertyAttribute extends Struct<ObjCPropertyAttribute> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NAME;
    public static final int VALUE;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NAME = __struct.offsetof(0);
        VALUE = __struct.offsetof(1);
    }

    protected ObjCPropertyAttribute(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public ObjCPropertyAttribute create(long j, ByteBuffer byteBuffer) {
        return new ObjCPropertyAttribute(j, byteBuffer);
    }

    public ObjCPropertyAttribute(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("char *")
    public ByteBuffer name() {
        return nname(address());
    }

    @NativeType("char *")
    public String nameString() {
        return nnameString(address());
    }

    @NativeType("char *")
    public ByteBuffer value() {
        return nvalue(address());
    }

    @NativeType("char *")
    public String valueString() {
        return nvalueString(address());
    }

    public ObjCPropertyAttribute name(@NativeType("char *") ByteBuffer byteBuffer) {
        nname(address(), byteBuffer);
        return this;
    }

    public ObjCPropertyAttribute value(@NativeType("char *") ByteBuffer byteBuffer) {
        nvalue(address(), byteBuffer);
        return this;
    }

    public ObjCPropertyAttribute set(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        name(byteBuffer);
        value(byteBuffer2);
        return this;
    }

    public ObjCPropertyAttribute set(ObjCPropertyAttribute objCPropertyAttribute) {
        MemoryUtil.memCopy(objCPropertyAttribute.address(), address(), SIZEOF);
        return this;
    }

    public static ObjCPropertyAttribute malloc() {
        return new ObjCPropertyAttribute(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static ObjCPropertyAttribute calloc() {
        return new ObjCPropertyAttribute(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static ObjCPropertyAttribute create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new ObjCPropertyAttribute(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static ObjCPropertyAttribute create(long j) {
        return new ObjCPropertyAttribute(j, null);
    }

    public static ObjCPropertyAttribute createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new ObjCPropertyAttribute(j, null);
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
    public static ObjCPropertyAttribute mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static ObjCPropertyAttribute callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static ObjCPropertyAttribute mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static ObjCPropertyAttribute callocStack(MemoryStack memoryStack) {
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

    public static ObjCPropertyAttribute malloc(MemoryStack memoryStack) {
        return new ObjCPropertyAttribute(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static ObjCPropertyAttribute calloc(MemoryStack memoryStack) {
        return new ObjCPropertyAttribute(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static ByteBuffer nvalue(long j) {
        return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(j + VALUE));
    }

    public static String nvalueString(long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(j + VALUE));
    }

    public static void nname(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + NAME, MemoryUtil.memAddress(byteBuffer));
    }

    public static void nvalue(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + VALUE, MemoryUtil.memAddress(byteBuffer));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + NAME));
        Checks.check(MemoryUtil.memGetAddress(j + VALUE));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/macosx/ObjCPropertyAttribute$Buffer.class */
    public static class Buffer extends StructBuffer<ObjCPropertyAttribute, Buffer> implements NativeResource {
        private static final ObjCPropertyAttribute ELEMENT_FACTORY = ObjCPropertyAttribute.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / ObjCPropertyAttribute.SIZEOF);
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
        public ObjCPropertyAttribute getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("char *")
        public ByteBuffer name() {
            return ObjCPropertyAttribute.nname(address());
        }

        @NativeType("char *")
        public String nameString() {
            return ObjCPropertyAttribute.nnameString(address());
        }

        @NativeType("char *")
        public ByteBuffer value() {
            return ObjCPropertyAttribute.nvalue(address());
        }

        @NativeType("char *")
        public String valueString() {
            return ObjCPropertyAttribute.nvalueString(address());
        }

        public Buffer name(@NativeType("char *") ByteBuffer byteBuffer) {
            ObjCPropertyAttribute.nname(address(), byteBuffer);
            return this;
        }

        public Buffer value(@NativeType("char *") ByteBuffer byteBuffer) {
            ObjCPropertyAttribute.nvalue(address(), byteBuffer);
            return this;
        }
    }
}
