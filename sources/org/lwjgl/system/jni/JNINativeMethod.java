package org.lwjgl.system.jni;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jni/JNINativeMethod.class */
public class JNINativeMethod extends Struct<JNINativeMethod> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NAME;
    public static final int SIGNATURE;
    public static final int FNPTR;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NAME = __struct.offsetof(0);
        SIGNATURE = __struct.offsetof(1);
        FNPTR = __struct.offsetof(2);
    }

    protected JNINativeMethod(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public JNINativeMethod create(long j, ByteBuffer byteBuffer) {
        return new JNINativeMethod(j, byteBuffer);
    }

    public JNINativeMethod(ByteBuffer byteBuffer) {
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
    public ByteBuffer signature() {
        return nsignature(address());
    }

    @NativeType("char *")
    public String signatureString() {
        return nsignatureString(address());
    }

    @NativeType("void *")
    public long fnPtr() {
        return nfnPtr(address());
    }

    public JNINativeMethod name(@NativeType("char *") ByteBuffer byteBuffer) {
        nname(address(), byteBuffer);
        return this;
    }

    public JNINativeMethod signature(@NativeType("char *") ByteBuffer byteBuffer) {
        nsignature(address(), byteBuffer);
        return this;
    }

    public JNINativeMethod fnPtr(@NativeType("void *") long j) {
        nfnPtr(address(), j);
        return this;
    }

    public JNINativeMethod set(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, long j) {
        name(byteBuffer);
        signature(byteBuffer2);
        fnPtr(j);
        return this;
    }

    public JNINativeMethod set(JNINativeMethod jNINativeMethod) {
        MemoryUtil.memCopy(jNINativeMethod.address(), address(), SIZEOF);
        return this;
    }

    public static JNINativeMethod malloc() {
        return new JNINativeMethod(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static JNINativeMethod calloc() {
        return new JNINativeMethod(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static JNINativeMethod create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new JNINativeMethod(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static JNINativeMethod create(long j) {
        return new JNINativeMethod(j, null);
    }

    public static JNINativeMethod createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new JNINativeMethod(j, null);
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
    public static JNINativeMethod mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static JNINativeMethod callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static JNINativeMethod mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static JNINativeMethod callocStack(MemoryStack memoryStack) {
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

    public static JNINativeMethod malloc(MemoryStack memoryStack) {
        return new JNINativeMethod(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static JNINativeMethod calloc(MemoryStack memoryStack) {
        return new JNINativeMethod(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
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

    public static ByteBuffer nsignature(long j) {
        return MemoryUtil.memByteBufferNT1(MemoryUtil.memGetAddress(j + SIGNATURE));
    }

    public static String nsignatureString(long j) {
        return MemoryUtil.memUTF8(MemoryUtil.memGetAddress(j + SIGNATURE));
    }

    public static long nfnPtr(long j) {
        return MemoryUtil.memGetAddress(j + FNPTR);
    }

    public static void nname(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + NAME, MemoryUtil.memAddress(byteBuffer));
    }

    public static void nsignature(long j, ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        MemoryUtil.memPutAddress(j + SIGNATURE, MemoryUtil.memAddress(byteBuffer));
    }

    public static void nfnPtr(long j, long j2) {
        MemoryUtil.memPutAddress(j + FNPTR, Checks.check(j2));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + NAME));
        Checks.check(MemoryUtil.memGetAddress(j + SIGNATURE));
        Checks.check(MemoryUtil.memGetAddress(j + FNPTR));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/jni/JNINativeMethod$Buffer.class */
    public static class Buffer extends StructBuffer<JNINativeMethod, Buffer> implements NativeResource {
        private static final JNINativeMethod ELEMENT_FACTORY = JNINativeMethod.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / JNINativeMethod.SIZEOF);
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
        public JNINativeMethod getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("char *")
        public ByteBuffer name() {
            return JNINativeMethod.nname(address());
        }

        @NativeType("char *")
        public String nameString() {
            return JNINativeMethod.nnameString(address());
        }

        @NativeType("char *")
        public ByteBuffer signature() {
            return JNINativeMethod.nsignature(address());
        }

        @NativeType("char *")
        public String signatureString() {
            return JNINativeMethod.nsignatureString(address());
        }

        @NativeType("void *")
        public long fnPtr() {
            return JNINativeMethod.nfnPtr(address());
        }

        public Buffer name(@NativeType("char *") ByteBuffer byteBuffer) {
            JNINativeMethod.nname(address(), byteBuffer);
            return this;
        }

        public Buffer signature(@NativeType("char *") ByteBuffer byteBuffer) {
            JNINativeMethod.nsignature(address(), byteBuffer);
            return this;
        }

        public Buffer fnPtr(@NativeType("void *") long j) {
            JNINativeMethod.nfnPtr(address(), j);
            return this;
        }
    }
}
