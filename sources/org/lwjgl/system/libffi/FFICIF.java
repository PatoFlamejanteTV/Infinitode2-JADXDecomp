package org.lwjgl.system.libffi;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

@NativeType("struct ffi_cif")
/* loaded from: infinitode-2.jar:org/lwjgl/system/libffi/FFICIF.class */
public class FFICIF extends Struct<FFICIF> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int ABI;
    public static final int NARGS;
    public static final int ARG_TYPES;
    public static final int RTYPE;
    public static final int BYTES;
    public static final int FLAGS;

    private static native int offsets(long j);

    static {
        MemoryStack stackPush = MemoryStack.stackPush();
        Throwable th = null;
        try {
            IntBuffer mallocInt = stackPush.mallocInt(7);
            SIZEOF = offsets(MemoryUtil.memAddress(mallocInt));
            ABI = mallocInt.get(0);
            NARGS = mallocInt.get(1);
            ARG_TYPES = mallocInt.get(2);
            RTYPE = mallocInt.get(3);
            BYTES = mallocInt.get(4);
            FLAGS = mallocInt.get(5);
            ALIGNOF = mallocInt.get(6);
            if (stackPush != null) {
                if (0 == 0) {
                    stackPush.close();
                    return;
                }
                try {
                    stackPush.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
        } catch (Throwable th3) {
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    stackPush.close();
                }
            }
            throw th3;
        }
    }

    protected FFICIF(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public FFICIF create(long j, ByteBuffer byteBuffer) {
        return new FFICIF(j, byteBuffer);
    }

    public FFICIF(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("ffi_abi")
    public int abi() {
        return nabi(address());
    }

    @NativeType("unsigned")
    public int nargs() {
        return nnargs(address());
    }

    @NativeType("ffi_type **")
    public PointerBuffer arg_types(int i) {
        return narg_types(address(), i);
    }

    @NativeType("ffi_type *")
    public FFIType rtype() {
        return nrtype(address());
    }

    @NativeType("unsigned")
    public int bytes() {
        return nbytes(address());
    }

    @NativeType("unsigned")
    public int flags() {
        return nflags(address());
    }

    public static FFICIF malloc() {
        return new FFICIF(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static FFICIF calloc() {
        return new FFICIF(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static FFICIF create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new FFICIF(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static FFICIF create(long j) {
        return new FFICIF(j, null);
    }

    public static FFICIF createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new FFICIF(j, null);
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

    public static FFICIF malloc(MemoryStack memoryStack) {
        return new FFICIF(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static FFICIF calloc(MemoryStack memoryStack) {
        return new FFICIF(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static int nabi(long j) {
        return UNSAFE.getInt((Object) null, j + ABI);
    }

    public static int nnargs(long j) {
        return UNSAFE.getInt((Object) null, j + NARGS);
    }

    public static PointerBuffer narg_types(long j, int i) {
        return MemoryUtil.memPointerBuffer(MemoryUtil.memGetAddress(j + ARG_TYPES), i);
    }

    public static FFIType nrtype(long j) {
        return FFIType.create(MemoryUtil.memGetAddress(j + RTYPE));
    }

    public static int nbytes(long j) {
        return UNSAFE.getInt((Object) null, j + BYTES);
    }

    public static int nflags(long j) {
        return UNSAFE.getInt((Object) null, j + FLAGS);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/libffi/FFICIF$Buffer.class */
    public static class Buffer extends StructBuffer<FFICIF, Buffer> implements NativeResource {
        private static final FFICIF ELEMENT_FACTORY = FFICIF.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / FFICIF.SIZEOF);
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
        public FFICIF getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("ffi_abi")
        public int abi() {
            return FFICIF.nabi(address());
        }

        @NativeType("unsigned")
        public int nargs() {
            return FFICIF.nnargs(address());
        }

        @NativeType("ffi_type **")
        public PointerBuffer arg_types(int i) {
            return FFICIF.narg_types(address(), i);
        }

        @NativeType("ffi_type *")
        public FFIType rtype() {
            return FFICIF.nrtype(address());
        }

        @NativeType("unsigned")
        public int bytes() {
            return FFICIF.nbytes(address());
        }

        @NativeType("unsigned")
        public int flags() {
            return FFICIF.nflags(address());
        }
    }
}
