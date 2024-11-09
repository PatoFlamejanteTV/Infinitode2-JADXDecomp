package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/SECURITY_ATTRIBUTES.class */
public class SECURITY_ATTRIBUTES extends Struct<SECURITY_ATTRIBUTES> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NLENGTH;
    public static final int LPSECURITYDESCRIPTOR;
    public static final int BINHERITHANDLE;

    static {
        Struct.Layout __struct = __struct(__member(4), __member(POINTER_SIZE), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NLENGTH = __struct.offsetof(0);
        LPSECURITYDESCRIPTOR = __struct.offsetof(1);
        BINHERITHANDLE = __struct.offsetof(2);
    }

    protected SECURITY_ATTRIBUTES(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public SECURITY_ATTRIBUTES create(long j, ByteBuffer byteBuffer) {
        return new SECURITY_ATTRIBUTES(j, byteBuffer);
    }

    public SECURITY_ATTRIBUTES(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("DWORD")
    public int nLength() {
        return nnLength(address());
    }

    @NativeType("LPVOID")
    public long lpSecurityDescriptor() {
        return nlpSecurityDescriptor(address());
    }

    @NativeType("BOOL")
    public boolean bInheritHandle() {
        return nbInheritHandle(address()) != 0;
    }

    public SECURITY_ATTRIBUTES nLength(@NativeType("DWORD") int i) {
        nnLength(address(), i);
        return this;
    }

    public SECURITY_ATTRIBUTES lpSecurityDescriptor(@NativeType("LPVOID") long j) {
        nlpSecurityDescriptor(address(), j);
        return this;
    }

    public SECURITY_ATTRIBUTES bInheritHandle(@NativeType("BOOL") boolean z) {
        nbInheritHandle(address(), z ? 1 : 0);
        return this;
    }

    public SECURITY_ATTRIBUTES set(int i, long j, boolean z) {
        nLength(i);
        lpSecurityDescriptor(j);
        bInheritHandle(z);
        return this;
    }

    public SECURITY_ATTRIBUTES set(SECURITY_ATTRIBUTES security_attributes) {
        MemoryUtil.memCopy(security_attributes.address(), address(), SIZEOF);
        return this;
    }

    public static SECURITY_ATTRIBUTES malloc() {
        return new SECURITY_ATTRIBUTES(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static SECURITY_ATTRIBUTES calloc() {
        return new SECURITY_ATTRIBUTES(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static SECURITY_ATTRIBUTES create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new SECURITY_ATTRIBUTES(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static SECURITY_ATTRIBUTES create(long j) {
        return new SECURITY_ATTRIBUTES(j, null);
    }

    public static SECURITY_ATTRIBUTES createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new SECURITY_ATTRIBUTES(j, null);
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
    public static SECURITY_ATTRIBUTES mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static SECURITY_ATTRIBUTES callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static SECURITY_ATTRIBUTES mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static SECURITY_ATTRIBUTES callocStack(MemoryStack memoryStack) {
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

    public static SECURITY_ATTRIBUTES malloc(MemoryStack memoryStack) {
        return new SECURITY_ATTRIBUTES(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static SECURITY_ATTRIBUTES calloc(MemoryStack memoryStack) {
        return new SECURITY_ATTRIBUTES(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nnLength(long j) {
        return UNSAFE.getInt((Object) null, j + NLENGTH);
    }

    public static long nlpSecurityDescriptor(long j) {
        return MemoryUtil.memGetAddress(j + LPSECURITYDESCRIPTOR);
    }

    public static int nbInheritHandle(long j) {
        return UNSAFE.getInt((Object) null, j + BINHERITHANDLE);
    }

    public static void nnLength(long j, int i) {
        UNSAFE.putInt((Object) null, j + NLENGTH, i);
    }

    public static void nlpSecurityDescriptor(long j, long j2) {
        MemoryUtil.memPutAddress(j + LPSECURITYDESCRIPTOR, Checks.check(j2));
    }

    public static void nbInheritHandle(long j, int i) {
        UNSAFE.putInt((Object) null, j + BINHERITHANDLE, i);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + LPSECURITYDESCRIPTOR));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/SECURITY_ATTRIBUTES$Buffer.class */
    public static class Buffer extends StructBuffer<SECURITY_ATTRIBUTES, Buffer> implements NativeResource {
        private static final SECURITY_ATTRIBUTES ELEMENT_FACTORY = SECURITY_ATTRIBUTES.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / SECURITY_ATTRIBUTES.SIZEOF);
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
        public SECURITY_ATTRIBUTES getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("DWORD")
        public int nLength() {
            return SECURITY_ATTRIBUTES.nnLength(address());
        }

        @NativeType("LPVOID")
        public long lpSecurityDescriptor() {
            return SECURITY_ATTRIBUTES.nlpSecurityDescriptor(address());
        }

        @NativeType("BOOL")
        public boolean bInheritHandle() {
            return SECURITY_ATTRIBUTES.nbInheritHandle(address()) != 0;
        }

        public Buffer nLength(@NativeType("DWORD") int i) {
            SECURITY_ATTRIBUTES.nnLength(address(), i);
            return this;
        }

        public Buffer lpSecurityDescriptor(@NativeType("LPVOID") long j) {
            SECURITY_ATTRIBUTES.nlpSecurityDescriptor(address(), j);
            return this;
        }

        public Buffer bInheritHandle(@NativeType("BOOL") boolean z) {
            SECURITY_ATTRIBUTES.nbInheritHandle(address(), z ? 1 : 0);
            return this;
        }
    }
}
