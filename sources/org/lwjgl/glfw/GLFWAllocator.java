package org.lwjgl.glfw;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct GLFWallocator")
/* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWAllocator.class */
public class GLFWAllocator extends Struct<GLFWAllocator> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int ALLOCATE;
    public static final int REALLOCATE;
    public static final int DEALLOCATE;
    public static final int USER;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        ALLOCATE = __struct.offsetof(0);
        REALLOCATE = __struct.offsetof(1);
        DEALLOCATE = __struct.offsetof(2);
        USER = __struct.offsetof(3);
    }

    protected GLFWAllocator(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public GLFWAllocator create(long j, ByteBuffer byteBuffer) {
        return new GLFWAllocator(j, byteBuffer);
    }

    public GLFWAllocator(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("GLFWallocatefun")
    public GLFWAllocateCallback allocate() {
        return nallocate(address());
    }

    @NativeType("GLFWreallocatefun")
    public GLFWReallocateCallback reallocate() {
        return nreallocate(address());
    }

    @NativeType("GLFWdeallocatefun")
    public GLFWDeallocateCallback deallocate() {
        return ndeallocate(address());
    }

    @NativeType("void *")
    public long user() {
        return nuser(address());
    }

    public GLFWAllocator allocate(@NativeType("GLFWallocatefun") GLFWAllocateCallbackI gLFWAllocateCallbackI) {
        nallocate(address(), gLFWAllocateCallbackI);
        return this;
    }

    public GLFWAllocator reallocate(@NativeType("GLFWreallocatefun") GLFWReallocateCallbackI gLFWReallocateCallbackI) {
        nreallocate(address(), gLFWReallocateCallbackI);
        return this;
    }

    public GLFWAllocator deallocate(@NativeType("GLFWdeallocatefun") GLFWDeallocateCallbackI gLFWDeallocateCallbackI) {
        ndeallocate(address(), gLFWDeallocateCallbackI);
        return this;
    }

    public GLFWAllocator user(@NativeType("void *") long j) {
        nuser(address(), j);
        return this;
    }

    public GLFWAllocator set(GLFWAllocateCallbackI gLFWAllocateCallbackI, GLFWReallocateCallbackI gLFWReallocateCallbackI, GLFWDeallocateCallbackI gLFWDeallocateCallbackI, long j) {
        allocate(gLFWAllocateCallbackI);
        reallocate(gLFWReallocateCallbackI);
        deallocate(gLFWDeallocateCallbackI);
        user(j);
        return this;
    }

    public GLFWAllocator set(GLFWAllocator gLFWAllocator) {
        MemoryUtil.memCopy(gLFWAllocator.address(), address(), SIZEOF);
        return this;
    }

    public static GLFWAllocator malloc() {
        return new GLFWAllocator(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static GLFWAllocator calloc() {
        return new GLFWAllocator(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static GLFWAllocator create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new GLFWAllocator(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static GLFWAllocator create(long j) {
        return new GLFWAllocator(j, null);
    }

    public static GLFWAllocator createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new GLFWAllocator(j, null);
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

    public static GLFWAllocator malloc(MemoryStack memoryStack) {
        return new GLFWAllocator(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static GLFWAllocator calloc(MemoryStack memoryStack) {
        return new GLFWAllocator(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static GLFWAllocateCallback nallocate(long j) {
        return GLFWAllocateCallback.create(MemoryUtil.memGetAddress(j + ALLOCATE));
    }

    public static GLFWReallocateCallback nreallocate(long j) {
        return GLFWReallocateCallback.create(MemoryUtil.memGetAddress(j + REALLOCATE));
    }

    public static GLFWDeallocateCallback ndeallocate(long j) {
        return GLFWDeallocateCallback.create(MemoryUtil.memGetAddress(j + DEALLOCATE));
    }

    public static long nuser(long j) {
        return MemoryUtil.memGetAddress(j + USER);
    }

    public static void nallocate(long j, GLFWAllocateCallbackI gLFWAllocateCallbackI) {
        MemoryUtil.memPutAddress(j + ALLOCATE, gLFWAllocateCallbackI.address());
    }

    public static void nreallocate(long j, GLFWReallocateCallbackI gLFWReallocateCallbackI) {
        MemoryUtil.memPutAddress(j + REALLOCATE, gLFWReallocateCallbackI.address());
    }

    public static void ndeallocate(long j, GLFWDeallocateCallbackI gLFWDeallocateCallbackI) {
        MemoryUtil.memPutAddress(j + DEALLOCATE, gLFWDeallocateCallbackI.address());
    }

    public static void nuser(long j, long j2) {
        MemoryUtil.memPutAddress(j + USER, j2);
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + ALLOCATE));
        Checks.check(MemoryUtil.memGetAddress(j + REALLOCATE));
        Checks.check(MemoryUtil.memGetAddress(j + DEALLOCATE));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/glfw/GLFWAllocator$Buffer.class */
    public static class Buffer extends StructBuffer<GLFWAllocator, Buffer> implements NativeResource {
        private static final GLFWAllocator ELEMENT_FACTORY = GLFWAllocator.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / GLFWAllocator.SIZEOF);
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
        public GLFWAllocator getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("GLFWallocatefun")
        public GLFWAllocateCallback allocate() {
            return GLFWAllocator.nallocate(address());
        }

        @NativeType("GLFWreallocatefun")
        public GLFWReallocateCallback reallocate() {
            return GLFWAllocator.nreallocate(address());
        }

        @NativeType("GLFWdeallocatefun")
        public GLFWDeallocateCallback deallocate() {
            return GLFWAllocator.ndeallocate(address());
        }

        @NativeType("void *")
        public long user() {
            return GLFWAllocator.nuser(address());
        }

        public Buffer allocate(@NativeType("GLFWallocatefun") GLFWAllocateCallbackI gLFWAllocateCallbackI) {
            GLFWAllocator.nallocate(address(), gLFWAllocateCallbackI);
            return this;
        }

        public Buffer reallocate(@NativeType("GLFWreallocatefun") GLFWReallocateCallbackI gLFWReallocateCallbackI) {
            GLFWAllocator.nreallocate(address(), gLFWReallocateCallbackI);
            return this;
        }

        public Buffer deallocate(@NativeType("GLFWdeallocatefun") GLFWDeallocateCallbackI gLFWDeallocateCallbackI) {
            GLFWAllocator.ndeallocate(address(), gLFWDeallocateCallbackI);
            return this;
        }

        public Buffer user(@NativeType("void *") long j) {
            GLFWAllocator.nuser(address(), j);
            return this;
        }
    }
}
