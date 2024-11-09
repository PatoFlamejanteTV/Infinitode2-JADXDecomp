package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/PIXELFORMATDESCRIPTOR.class */
public class PIXELFORMATDESCRIPTOR extends Struct<PIXELFORMATDESCRIPTOR> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int NSIZE;
    public static final int NVERSION;
    public static final int DWFLAGS;
    public static final int IPIXELTYPE;
    public static final int CCOLORBITS;
    public static final int CREDBITS;
    public static final int CREDSHIFT;
    public static final int CGREENBITS;
    public static final int CGREENSHIFT;
    public static final int CBLUEBITS;
    public static final int CBLUESHIFT;
    public static final int CALPHABITS;
    public static final int CALPHASHIFT;
    public static final int CACCUMBITS;
    public static final int CACCUMREDBITS;
    public static final int CACCUMGREENBITS;
    public static final int CACCUMBLUEBITS;
    public static final int CACCUMALPHABITS;
    public static final int CDEPTHBITS;
    public static final int CSTENCILBITS;
    public static final int CAUXBUFFERS;
    public static final int ILAYERTYPE;
    public static final int BRESERVED;
    public static final int DWLAYERMASK;
    public static final int DWVISIBLEMASK;
    public static final int DWDAMAGEMASK;

    static {
        Struct.Layout __struct = __struct(__member(2), __member(2), __member(4), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(1), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        NSIZE = __struct.offsetof(0);
        NVERSION = __struct.offsetof(1);
        DWFLAGS = __struct.offsetof(2);
        IPIXELTYPE = __struct.offsetof(3);
        CCOLORBITS = __struct.offsetof(4);
        CREDBITS = __struct.offsetof(5);
        CREDSHIFT = __struct.offsetof(6);
        CGREENBITS = __struct.offsetof(7);
        CGREENSHIFT = __struct.offsetof(8);
        CBLUEBITS = __struct.offsetof(9);
        CBLUESHIFT = __struct.offsetof(10);
        CALPHABITS = __struct.offsetof(11);
        CALPHASHIFT = __struct.offsetof(12);
        CACCUMBITS = __struct.offsetof(13);
        CACCUMREDBITS = __struct.offsetof(14);
        CACCUMGREENBITS = __struct.offsetof(15);
        CACCUMBLUEBITS = __struct.offsetof(16);
        CACCUMALPHABITS = __struct.offsetof(17);
        CDEPTHBITS = __struct.offsetof(18);
        CSTENCILBITS = __struct.offsetof(19);
        CAUXBUFFERS = __struct.offsetof(20);
        ILAYERTYPE = __struct.offsetof(21);
        BRESERVED = __struct.offsetof(22);
        DWLAYERMASK = __struct.offsetof(23);
        DWVISIBLEMASK = __struct.offsetof(24);
        DWDAMAGEMASK = __struct.offsetof(25);
    }

    protected PIXELFORMATDESCRIPTOR(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public PIXELFORMATDESCRIPTOR create(long j, ByteBuffer byteBuffer) {
        return new PIXELFORMATDESCRIPTOR(j, byteBuffer);
    }

    public PIXELFORMATDESCRIPTOR(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("WORD")
    public short nSize() {
        return nnSize(address());
    }

    @NativeType("WORD")
    public short nVersion() {
        return nnVersion(address());
    }

    @NativeType("DWORD")
    public int dwFlags() {
        return ndwFlags(address());
    }

    @NativeType("BYTE")
    public byte iPixelType() {
        return niPixelType(address());
    }

    @NativeType("BYTE")
    public byte cColorBits() {
        return ncColorBits(address());
    }

    @NativeType("BYTE")
    public byte cRedBits() {
        return ncRedBits(address());
    }

    @NativeType("BYTE")
    public byte cRedShift() {
        return ncRedShift(address());
    }

    @NativeType("BYTE")
    public byte cGreenBits() {
        return ncGreenBits(address());
    }

    @NativeType("BYTE")
    public byte cGreenShift() {
        return ncGreenShift(address());
    }

    @NativeType("BYTE")
    public byte cBlueBits() {
        return ncBlueBits(address());
    }

    @NativeType("BYTE")
    public byte cBlueShift() {
        return ncBlueShift(address());
    }

    @NativeType("BYTE")
    public byte cAlphaBits() {
        return ncAlphaBits(address());
    }

    @NativeType("BYTE")
    public byte cAlphaShift() {
        return ncAlphaShift(address());
    }

    @NativeType("BYTE")
    public byte cAccumBits() {
        return ncAccumBits(address());
    }

    @NativeType("BYTE")
    public byte cAccumRedBits() {
        return ncAccumRedBits(address());
    }

    @NativeType("BYTE")
    public byte cAccumGreenBits() {
        return ncAccumGreenBits(address());
    }

    @NativeType("BYTE")
    public byte cAccumBlueBits() {
        return ncAccumBlueBits(address());
    }

    @NativeType("BYTE")
    public byte cAccumAlphaBits() {
        return ncAccumAlphaBits(address());
    }

    @NativeType("BYTE")
    public byte cDepthBits() {
        return ncDepthBits(address());
    }

    @NativeType("BYTE")
    public byte cStencilBits() {
        return ncStencilBits(address());
    }

    @NativeType("BYTE")
    public byte cAuxBuffers() {
        return ncAuxBuffers(address());
    }

    @NativeType("BYTE")
    public byte iLayerType() {
        return niLayerType(address());
    }

    @NativeType("BYTE")
    public byte bReserved() {
        return nbReserved(address());
    }

    @NativeType("DWORD")
    public int dwLayerMask() {
        return ndwLayerMask(address());
    }

    @NativeType("DWORD")
    public int dwVisibleMask() {
        return ndwVisibleMask(address());
    }

    @NativeType("DWORD")
    public int dwDamageMask() {
        return ndwDamageMask(address());
    }

    public PIXELFORMATDESCRIPTOR nSize(@NativeType("WORD") short s) {
        nnSize(address(), s);
        return this;
    }

    public PIXELFORMATDESCRIPTOR nVersion(@NativeType("WORD") short s) {
        nnVersion(address(), s);
        return this;
    }

    public PIXELFORMATDESCRIPTOR dwFlags(@NativeType("DWORD") int i) {
        ndwFlags(address(), i);
        return this;
    }

    public PIXELFORMATDESCRIPTOR iPixelType(@NativeType("BYTE") byte b2) {
        niPixelType(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cColorBits(@NativeType("BYTE") byte b2) {
        ncColorBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cRedBits(@NativeType("BYTE") byte b2) {
        ncRedBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cRedShift(@NativeType("BYTE") byte b2) {
        ncRedShift(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cGreenBits(@NativeType("BYTE") byte b2) {
        ncGreenBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cGreenShift(@NativeType("BYTE") byte b2) {
        ncGreenShift(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cBlueBits(@NativeType("BYTE") byte b2) {
        ncBlueBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cBlueShift(@NativeType("BYTE") byte b2) {
        ncBlueShift(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAlphaBits(@NativeType("BYTE") byte b2) {
        ncAlphaBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAlphaShift(@NativeType("BYTE") byte b2) {
        ncAlphaShift(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAccumBits(@NativeType("BYTE") byte b2) {
        ncAccumBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAccumRedBits(@NativeType("BYTE") byte b2) {
        ncAccumRedBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAccumGreenBits(@NativeType("BYTE") byte b2) {
        ncAccumGreenBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAccumBlueBits(@NativeType("BYTE") byte b2) {
        ncAccumBlueBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAccumAlphaBits(@NativeType("BYTE") byte b2) {
        ncAccumAlphaBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cDepthBits(@NativeType("BYTE") byte b2) {
        ncDepthBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cStencilBits(@NativeType("BYTE") byte b2) {
        ncStencilBits(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR cAuxBuffers(@NativeType("BYTE") byte b2) {
        ncAuxBuffers(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR iLayerType(@NativeType("BYTE") byte b2) {
        niLayerType(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR bReserved(@NativeType("BYTE") byte b2) {
        nbReserved(address(), b2);
        return this;
    }

    public PIXELFORMATDESCRIPTOR dwLayerMask(@NativeType("DWORD") int i) {
        ndwLayerMask(address(), i);
        return this;
    }

    public PIXELFORMATDESCRIPTOR dwVisibleMask(@NativeType("DWORD") int i) {
        ndwVisibleMask(address(), i);
        return this;
    }

    public PIXELFORMATDESCRIPTOR dwDamageMask(@NativeType("DWORD") int i) {
        ndwDamageMask(address(), i);
        return this;
    }

    public PIXELFORMATDESCRIPTOR set(short s, short s2, int i, byte b2, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11, byte b12, byte b13, byte b14, byte b15, byte b16, byte b17, byte b18, byte b19, byte b20, byte b21, int i2, int i3, int i4) {
        nSize(s);
        nVersion(s2);
        dwFlags(i);
        iPixelType(b2);
        cColorBits(b3);
        cRedBits(b4);
        cRedShift(b5);
        cGreenBits(b6);
        cGreenShift(b7);
        cBlueBits(b8);
        cBlueShift(b9);
        cAlphaBits(b10);
        cAlphaShift(b11);
        cAccumBits(b12);
        cAccumRedBits(b13);
        cAccumGreenBits(b14);
        cAccumBlueBits(b15);
        cAccumAlphaBits(b16);
        cDepthBits(b17);
        cStencilBits(b18);
        cAuxBuffers(b19);
        iLayerType(b20);
        bReserved(b21);
        dwLayerMask(i2);
        dwVisibleMask(i3);
        dwDamageMask(i4);
        return this;
    }

    public PIXELFORMATDESCRIPTOR set(PIXELFORMATDESCRIPTOR pixelformatdescriptor) {
        MemoryUtil.memCopy(pixelformatdescriptor.address(), address(), SIZEOF);
        return this;
    }

    public static PIXELFORMATDESCRIPTOR malloc() {
        return new PIXELFORMATDESCRIPTOR(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static PIXELFORMATDESCRIPTOR calloc() {
        return new PIXELFORMATDESCRIPTOR(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static PIXELFORMATDESCRIPTOR create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new PIXELFORMATDESCRIPTOR(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static PIXELFORMATDESCRIPTOR create(long j) {
        return new PIXELFORMATDESCRIPTOR(j, null);
    }

    public static PIXELFORMATDESCRIPTOR createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new PIXELFORMATDESCRIPTOR(j, null);
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
    public static PIXELFORMATDESCRIPTOR mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static PIXELFORMATDESCRIPTOR callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static PIXELFORMATDESCRIPTOR mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static PIXELFORMATDESCRIPTOR callocStack(MemoryStack memoryStack) {
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

    public static PIXELFORMATDESCRIPTOR malloc(MemoryStack memoryStack) {
        return new PIXELFORMATDESCRIPTOR(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static PIXELFORMATDESCRIPTOR calloc(MemoryStack memoryStack) {
        return new PIXELFORMATDESCRIPTOR(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static short nnSize(long j) {
        return UNSAFE.getShort((Object) null, j + NSIZE);
    }

    public static short nnVersion(long j) {
        return UNSAFE.getShort((Object) null, j + NVERSION);
    }

    public static int ndwFlags(long j) {
        return UNSAFE.getInt((Object) null, j + DWFLAGS);
    }

    public static byte niPixelType(long j) {
        return UNSAFE.getByte((Object) null, j + IPIXELTYPE);
    }

    public static byte ncColorBits(long j) {
        return UNSAFE.getByte((Object) null, j + CCOLORBITS);
    }

    public static byte ncRedBits(long j) {
        return UNSAFE.getByte((Object) null, j + CREDBITS);
    }

    public static byte ncRedShift(long j) {
        return UNSAFE.getByte((Object) null, j + CREDSHIFT);
    }

    public static byte ncGreenBits(long j) {
        return UNSAFE.getByte((Object) null, j + CGREENBITS);
    }

    public static byte ncGreenShift(long j) {
        return UNSAFE.getByte((Object) null, j + CGREENSHIFT);
    }

    public static byte ncBlueBits(long j) {
        return UNSAFE.getByte((Object) null, j + CBLUEBITS);
    }

    public static byte ncBlueShift(long j) {
        return UNSAFE.getByte((Object) null, j + CBLUESHIFT);
    }

    public static byte ncAlphaBits(long j) {
        return UNSAFE.getByte((Object) null, j + CALPHABITS);
    }

    public static byte ncAlphaShift(long j) {
        return UNSAFE.getByte((Object) null, j + CALPHASHIFT);
    }

    public static byte ncAccumBits(long j) {
        return UNSAFE.getByte((Object) null, j + CACCUMBITS);
    }

    public static byte ncAccumRedBits(long j) {
        return UNSAFE.getByte((Object) null, j + CACCUMREDBITS);
    }

    public static byte ncAccumGreenBits(long j) {
        return UNSAFE.getByte((Object) null, j + CACCUMGREENBITS);
    }

    public static byte ncAccumBlueBits(long j) {
        return UNSAFE.getByte((Object) null, j + CACCUMBLUEBITS);
    }

    public static byte ncAccumAlphaBits(long j) {
        return UNSAFE.getByte((Object) null, j + CACCUMALPHABITS);
    }

    public static byte ncDepthBits(long j) {
        return UNSAFE.getByte((Object) null, j + CDEPTHBITS);
    }

    public static byte ncStencilBits(long j) {
        return UNSAFE.getByte((Object) null, j + CSTENCILBITS);
    }

    public static byte ncAuxBuffers(long j) {
        return UNSAFE.getByte((Object) null, j + CAUXBUFFERS);
    }

    public static byte niLayerType(long j) {
        return UNSAFE.getByte((Object) null, j + ILAYERTYPE);
    }

    public static byte nbReserved(long j) {
        return UNSAFE.getByte((Object) null, j + BRESERVED);
    }

    public static int ndwLayerMask(long j) {
        return UNSAFE.getInt((Object) null, j + DWLAYERMASK);
    }

    public static int ndwVisibleMask(long j) {
        return UNSAFE.getInt((Object) null, j + DWVISIBLEMASK);
    }

    public static int ndwDamageMask(long j) {
        return UNSAFE.getInt((Object) null, j + DWDAMAGEMASK);
    }

    public static void nnSize(long j, short s) {
        UNSAFE.putShort((Object) null, j + NSIZE, s);
    }

    public static void nnVersion(long j, short s) {
        UNSAFE.putShort((Object) null, j + NVERSION, s);
    }

    public static void ndwFlags(long j, int i) {
        UNSAFE.putInt((Object) null, j + DWFLAGS, i);
    }

    public static void niPixelType(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + IPIXELTYPE, b2);
    }

    public static void ncColorBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CCOLORBITS, b2);
    }

    public static void ncRedBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CREDBITS, b2);
    }

    public static void ncRedShift(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CREDSHIFT, b2);
    }

    public static void ncGreenBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CGREENBITS, b2);
    }

    public static void ncGreenShift(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CGREENSHIFT, b2);
    }

    public static void ncBlueBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CBLUEBITS, b2);
    }

    public static void ncBlueShift(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CBLUESHIFT, b2);
    }

    public static void ncAlphaBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CALPHABITS, b2);
    }

    public static void ncAlphaShift(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CALPHASHIFT, b2);
    }

    public static void ncAccumBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CACCUMBITS, b2);
    }

    public static void ncAccumRedBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CACCUMREDBITS, b2);
    }

    public static void ncAccumGreenBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CACCUMGREENBITS, b2);
    }

    public static void ncAccumBlueBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CACCUMBLUEBITS, b2);
    }

    public static void ncAccumAlphaBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CACCUMALPHABITS, b2);
    }

    public static void ncDepthBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CDEPTHBITS, b2);
    }

    public static void ncStencilBits(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CSTENCILBITS, b2);
    }

    public static void ncAuxBuffers(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + CAUXBUFFERS, b2);
    }

    public static void niLayerType(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + ILAYERTYPE, b2);
    }

    public static void nbReserved(long j, byte b2) {
        UNSAFE.putByte((Object) null, j + BRESERVED, b2);
    }

    public static void ndwLayerMask(long j, int i) {
        UNSAFE.putInt((Object) null, j + DWLAYERMASK, i);
    }

    public static void ndwVisibleMask(long j, int i) {
        UNSAFE.putInt((Object) null, j + DWVISIBLEMASK, i);
    }

    public static void ndwDamageMask(long j, int i) {
        UNSAFE.putInt((Object) null, j + DWDAMAGEMASK, i);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/PIXELFORMATDESCRIPTOR$Buffer.class */
    public static class Buffer extends StructBuffer<PIXELFORMATDESCRIPTOR, Buffer> implements NativeResource {
        private static final PIXELFORMATDESCRIPTOR ELEMENT_FACTORY = PIXELFORMATDESCRIPTOR.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / PIXELFORMATDESCRIPTOR.SIZEOF);
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
        public PIXELFORMATDESCRIPTOR getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("WORD")
        public short nSize() {
            return PIXELFORMATDESCRIPTOR.nnSize(address());
        }

        @NativeType("WORD")
        public short nVersion() {
            return PIXELFORMATDESCRIPTOR.nnVersion(address());
        }

        @NativeType("DWORD")
        public int dwFlags() {
            return PIXELFORMATDESCRIPTOR.ndwFlags(address());
        }

        @NativeType("BYTE")
        public byte iPixelType() {
            return PIXELFORMATDESCRIPTOR.niPixelType(address());
        }

        @NativeType("BYTE")
        public byte cColorBits() {
            return PIXELFORMATDESCRIPTOR.ncColorBits(address());
        }

        @NativeType("BYTE")
        public byte cRedBits() {
            return PIXELFORMATDESCRIPTOR.ncRedBits(address());
        }

        @NativeType("BYTE")
        public byte cRedShift() {
            return PIXELFORMATDESCRIPTOR.ncRedShift(address());
        }

        @NativeType("BYTE")
        public byte cGreenBits() {
            return PIXELFORMATDESCRIPTOR.ncGreenBits(address());
        }

        @NativeType("BYTE")
        public byte cGreenShift() {
            return PIXELFORMATDESCRIPTOR.ncGreenShift(address());
        }

        @NativeType("BYTE")
        public byte cBlueBits() {
            return PIXELFORMATDESCRIPTOR.ncBlueBits(address());
        }

        @NativeType("BYTE")
        public byte cBlueShift() {
            return PIXELFORMATDESCRIPTOR.ncBlueShift(address());
        }

        @NativeType("BYTE")
        public byte cAlphaBits() {
            return PIXELFORMATDESCRIPTOR.ncAlphaBits(address());
        }

        @NativeType("BYTE")
        public byte cAlphaShift() {
            return PIXELFORMATDESCRIPTOR.ncAlphaShift(address());
        }

        @NativeType("BYTE")
        public byte cAccumBits() {
            return PIXELFORMATDESCRIPTOR.ncAccumBits(address());
        }

        @NativeType("BYTE")
        public byte cAccumRedBits() {
            return PIXELFORMATDESCRIPTOR.ncAccumRedBits(address());
        }

        @NativeType("BYTE")
        public byte cAccumGreenBits() {
            return PIXELFORMATDESCRIPTOR.ncAccumGreenBits(address());
        }

        @NativeType("BYTE")
        public byte cAccumBlueBits() {
            return PIXELFORMATDESCRIPTOR.ncAccumBlueBits(address());
        }

        @NativeType("BYTE")
        public byte cAccumAlphaBits() {
            return PIXELFORMATDESCRIPTOR.ncAccumAlphaBits(address());
        }

        @NativeType("BYTE")
        public byte cDepthBits() {
            return PIXELFORMATDESCRIPTOR.ncDepthBits(address());
        }

        @NativeType("BYTE")
        public byte cStencilBits() {
            return PIXELFORMATDESCRIPTOR.ncStencilBits(address());
        }

        @NativeType("BYTE")
        public byte cAuxBuffers() {
            return PIXELFORMATDESCRIPTOR.ncAuxBuffers(address());
        }

        @NativeType("BYTE")
        public byte iLayerType() {
            return PIXELFORMATDESCRIPTOR.niLayerType(address());
        }

        @NativeType("BYTE")
        public byte bReserved() {
            return PIXELFORMATDESCRIPTOR.nbReserved(address());
        }

        @NativeType("DWORD")
        public int dwLayerMask() {
            return PIXELFORMATDESCRIPTOR.ndwLayerMask(address());
        }

        @NativeType("DWORD")
        public int dwVisibleMask() {
            return PIXELFORMATDESCRIPTOR.ndwVisibleMask(address());
        }

        @NativeType("DWORD")
        public int dwDamageMask() {
            return PIXELFORMATDESCRIPTOR.ndwDamageMask(address());
        }

        public Buffer nSize(@NativeType("WORD") short s) {
            PIXELFORMATDESCRIPTOR.nnSize(address(), s);
            return this;
        }

        public Buffer nVersion(@NativeType("WORD") short s) {
            PIXELFORMATDESCRIPTOR.nnVersion(address(), s);
            return this;
        }

        public Buffer dwFlags(@NativeType("DWORD") int i) {
            PIXELFORMATDESCRIPTOR.ndwFlags(address(), i);
            return this;
        }

        public Buffer iPixelType(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.niPixelType(address(), b2);
            return this;
        }

        public Buffer cColorBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncColorBits(address(), b2);
            return this;
        }

        public Buffer cRedBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncRedBits(address(), b2);
            return this;
        }

        public Buffer cRedShift(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncRedShift(address(), b2);
            return this;
        }

        public Buffer cGreenBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncGreenBits(address(), b2);
            return this;
        }

        public Buffer cGreenShift(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncGreenShift(address(), b2);
            return this;
        }

        public Buffer cBlueBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncBlueBits(address(), b2);
            return this;
        }

        public Buffer cBlueShift(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncBlueShift(address(), b2);
            return this;
        }

        public Buffer cAlphaBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAlphaBits(address(), b2);
            return this;
        }

        public Buffer cAlphaShift(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAlphaShift(address(), b2);
            return this;
        }

        public Buffer cAccumBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAccumBits(address(), b2);
            return this;
        }

        public Buffer cAccumRedBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAccumRedBits(address(), b2);
            return this;
        }

        public Buffer cAccumGreenBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAccumGreenBits(address(), b2);
            return this;
        }

        public Buffer cAccumBlueBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAccumBlueBits(address(), b2);
            return this;
        }

        public Buffer cAccumAlphaBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAccumAlphaBits(address(), b2);
            return this;
        }

        public Buffer cDepthBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncDepthBits(address(), b2);
            return this;
        }

        public Buffer cStencilBits(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncStencilBits(address(), b2);
            return this;
        }

        public Buffer cAuxBuffers(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.ncAuxBuffers(address(), b2);
            return this;
        }

        public Buffer iLayerType(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.niLayerType(address(), b2);
            return this;
        }

        public Buffer bReserved(@NativeType("BYTE") byte b2) {
            PIXELFORMATDESCRIPTOR.nbReserved(address(), b2);
            return this;
        }

        public Buffer dwLayerMask(@NativeType("DWORD") int i) {
            PIXELFORMATDESCRIPTOR.ndwLayerMask(address(), i);
            return this;
        }

        public Buffer dwVisibleMask(@NativeType("DWORD") int i) {
            PIXELFORMATDESCRIPTOR.ndwVisibleMask(address(), i);
            return this;
        }

        public Buffer dwDamageMask(@NativeType("DWORD") int i) {
            PIXELFORMATDESCRIPTOR.ndwDamageMask(address(), i);
            return this;
        }
    }
}
