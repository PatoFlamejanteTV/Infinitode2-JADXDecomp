package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct stbi_io_callbacks")
/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIIOCallbacks.class */
public class STBIIOCallbacks extends Struct<STBIIOCallbacks> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int READ;
    public static final int SKIP;
    public static final int EOF;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        READ = __struct.offsetof(0);
        SKIP = __struct.offsetof(1);
        EOF = __struct.offsetof(2);
    }

    protected STBIIOCallbacks(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBIIOCallbacks create(long j, ByteBuffer byteBuffer) {
        return new STBIIOCallbacks(j, byteBuffer);
    }

    public STBIIOCallbacks(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("int (*) (void *, char *, int)")
    public STBIReadCallback read() {
        return nread(address());
    }

    @NativeType("void (*) (void *, int)")
    public STBISkipCallback skip() {
        return nskip(address());
    }

    @NativeType("int (*) (void *)")
    public STBIEOFCallback eof() {
        return neof(address());
    }

    public STBIIOCallbacks read(@NativeType("int (*) (void *, char *, int)") STBIReadCallbackI sTBIReadCallbackI) {
        nread(address(), sTBIReadCallbackI);
        return this;
    }

    public STBIIOCallbacks skip(@NativeType("void (*) (void *, int)") STBISkipCallbackI sTBISkipCallbackI) {
        nskip(address(), sTBISkipCallbackI);
        return this;
    }

    public STBIIOCallbacks eof(@NativeType("int (*) (void *)") STBIEOFCallbackI sTBIEOFCallbackI) {
        neof(address(), sTBIEOFCallbackI);
        return this;
    }

    public STBIIOCallbacks set(STBIReadCallbackI sTBIReadCallbackI, STBISkipCallbackI sTBISkipCallbackI, STBIEOFCallbackI sTBIEOFCallbackI) {
        read(sTBIReadCallbackI);
        skip(sTBISkipCallbackI);
        eof(sTBIEOFCallbackI);
        return this;
    }

    public STBIIOCallbacks set(STBIIOCallbacks sTBIIOCallbacks) {
        MemoryUtil.memCopy(sTBIIOCallbacks.address(), address(), SIZEOF);
        return this;
    }

    public static STBIIOCallbacks malloc() {
        return new STBIIOCallbacks(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBIIOCallbacks calloc() {
        return new STBIIOCallbacks(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBIIOCallbacks create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBIIOCallbacks(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBIIOCallbacks create(long j) {
        return new STBIIOCallbacks(j, null);
    }

    public static STBIIOCallbacks createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBIIOCallbacks(j, null);
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
    public static STBIIOCallbacks mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBIIOCallbacks callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static STBIIOCallbacks mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static STBIIOCallbacks callocStack(MemoryStack memoryStack) {
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

    public static STBIIOCallbacks malloc(MemoryStack memoryStack) {
        return new STBIIOCallbacks(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBIIOCallbacks calloc(MemoryStack memoryStack) {
        return new STBIIOCallbacks(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static STBIReadCallback nread(long j) {
        return STBIReadCallback.create(MemoryUtil.memGetAddress(j + READ));
    }

    public static STBISkipCallback nskip(long j) {
        return STBISkipCallback.create(MemoryUtil.memGetAddress(j + SKIP));
    }

    public static STBIEOFCallback neof(long j) {
        return STBIEOFCallback.create(MemoryUtil.memGetAddress(j + EOF));
    }

    public static void nread(long j, STBIReadCallbackI sTBIReadCallbackI) {
        MemoryUtil.memPutAddress(j + READ, sTBIReadCallbackI.address());
    }

    public static void nskip(long j, STBISkipCallbackI sTBISkipCallbackI) {
        MemoryUtil.memPutAddress(j + SKIP, sTBISkipCallbackI.address());
    }

    public static void neof(long j, STBIEOFCallbackI sTBIEOFCallbackI) {
        MemoryUtil.memPutAddress(j + EOF, sTBIEOFCallbackI.address());
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + READ));
        Checks.check(MemoryUtil.memGetAddress(j + SKIP));
        Checks.check(MemoryUtil.memGetAddress(j + EOF));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIIOCallbacks$Buffer.class */
    public static class Buffer extends StructBuffer<STBIIOCallbacks, Buffer> implements NativeResource {
        private static final STBIIOCallbacks ELEMENT_FACTORY = STBIIOCallbacks.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBIIOCallbacks.SIZEOF);
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
        public STBIIOCallbacks getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("int (*) (void *, char *, int)")
        public STBIReadCallback read() {
            return STBIIOCallbacks.nread(address());
        }

        @NativeType("void (*) (void *, int)")
        public STBISkipCallback skip() {
            return STBIIOCallbacks.nskip(address());
        }

        @NativeType("int (*) (void *)")
        public STBIEOFCallback eof() {
            return STBIIOCallbacks.neof(address());
        }

        public Buffer read(@NativeType("int (*) (void *, char *, int)") STBIReadCallbackI sTBIReadCallbackI) {
            STBIIOCallbacks.nread(address(), sTBIReadCallbackI);
            return this;
        }

        public Buffer skip(@NativeType("void (*) (void *, int)") STBISkipCallbackI sTBISkipCallbackI) {
            STBIIOCallbacks.nskip(address(), sTBISkipCallbackI);
            return this;
        }

        public Buffer eof(@NativeType("int (*) (void *)") STBIEOFCallbackI sTBIEOFCallbackI) {
            STBIIOCallbacks.neof(address(), sTBIEOFCallbackI);
            return this;
        }
    }
}
