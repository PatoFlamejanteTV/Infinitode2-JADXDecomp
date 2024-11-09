package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/DEVMODE.class */
public class DEVMODE extends Struct<DEVMODE> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int DMDEVICENAME;
    public static final int DMSPECVERSION;
    public static final int DMDRIVERVERSION;
    public static final int DMSIZE;
    public static final int DMDRIVEREXTRA;
    public static final int DMFIELDS;
    public static final int DMORIENTATION;
    public static final int DMPAPERSIZE;
    public static final int DMPAPERLENGTH;
    public static final int DMPAPERWIDTH;
    public static final int DMSCALE;
    public static final int DMCOPIES;
    public static final int DMDEFAULTSOURCE;
    public static final int DMPRINTQUALITY;
    public static final int DMPOSITION;
    public static final int DMDISPLAYORIENTATION;
    public static final int DMDISPLAYFIXEDOUTPUT;
    public static final int DMCOLOR;
    public static final int DMDUPLEX;
    public static final int DMYRESOLUTION;
    public static final int DMTTOPTION;
    public static final int DMCOLLATE;
    public static final int DMFORMNAME;
    public static final int DMLOGPIXELS;
    public static final int DMBITSPERPEL;
    public static final int DMPELSWIDTH;
    public static final int DMPELSHEIGHT;
    public static final int DMDISPLAYFLAGS;
    public static final int DMNUP;
    public static final int DMDISPLAYFREQUENCY;
    public static final int DMICMMETHOD;
    public static final int DMICMINTENT;
    public static final int DMMEDIATYPE;
    public static final int DMDITHERTYPE;
    public static final int DMRESERVED1;
    public static final int DMRESERVED2;
    public static final int DMPANNINGWIDTH;
    public static final int DMPANNINGHEIGHT;

    static {
        Struct.Layout __struct = __struct(__array(2, 32), __member(2), __member(2), __member(2), __member(2), __member(4), __union(__struct(__member(2), __member(2), __member(2), __member(2), __member(2), __member(2), __member(2), __member(2)), __struct(__member(POINTL.SIZEOF, POINTL.ALIGNOF), __member(4), __member(4))), __member(2), __member(2), __member(2), __member(2), __member(2), __array(2, 32), __member(2), __member(4), __member(4), __member(4), __union(__member(4), __member(4)), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        DMDEVICENAME = __struct.offsetof(0);
        DMSPECVERSION = __struct.offsetof(1);
        DMDRIVERVERSION = __struct.offsetof(2);
        DMSIZE = __struct.offsetof(3);
        DMDRIVEREXTRA = __struct.offsetof(4);
        DMFIELDS = __struct.offsetof(5);
        DMORIENTATION = __struct.offsetof(8);
        DMPAPERSIZE = __struct.offsetof(9);
        DMPAPERLENGTH = __struct.offsetof(10);
        DMPAPERWIDTH = __struct.offsetof(11);
        DMSCALE = __struct.offsetof(12);
        DMCOPIES = __struct.offsetof(13);
        DMDEFAULTSOURCE = __struct.offsetof(14);
        DMPRINTQUALITY = __struct.offsetof(15);
        DMPOSITION = __struct.offsetof(17);
        DMDISPLAYORIENTATION = __struct.offsetof(18);
        DMDISPLAYFIXEDOUTPUT = __struct.offsetof(19);
        DMCOLOR = __struct.offsetof(20);
        DMDUPLEX = __struct.offsetof(21);
        DMYRESOLUTION = __struct.offsetof(22);
        DMTTOPTION = __struct.offsetof(23);
        DMCOLLATE = __struct.offsetof(24);
        DMFORMNAME = __struct.offsetof(25);
        DMLOGPIXELS = __struct.offsetof(26);
        DMBITSPERPEL = __struct.offsetof(27);
        DMPELSWIDTH = __struct.offsetof(28);
        DMPELSHEIGHT = __struct.offsetof(29);
        DMDISPLAYFLAGS = __struct.offsetof(31);
        DMNUP = __struct.offsetof(32);
        DMDISPLAYFREQUENCY = __struct.offsetof(33);
        DMICMMETHOD = __struct.offsetof(34);
        DMICMINTENT = __struct.offsetof(35);
        DMMEDIATYPE = __struct.offsetof(36);
        DMDITHERTYPE = __struct.offsetof(37);
        DMRESERVED1 = __struct.offsetof(38);
        DMRESERVED2 = __struct.offsetof(39);
        DMPANNINGWIDTH = __struct.offsetof(40);
        DMPANNINGHEIGHT = __struct.offsetof(41);
    }

    protected DEVMODE(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public DEVMODE create(long j, ByteBuffer byteBuffer) {
        return new DEVMODE(j, byteBuffer);
    }

    public DEVMODE(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("TCHAR[32]")
    public ByteBuffer dmDeviceName() {
        return ndmDeviceName(address());
    }

    @NativeType("TCHAR[32]")
    public String dmDeviceNameString() {
        return ndmDeviceNameString(address());
    }

    @NativeType("WORD")
    public short dmSpecVersion() {
        return ndmSpecVersion(address());
    }

    @NativeType("WORD")
    public short dmDriverVersion() {
        return ndmDriverVersion(address());
    }

    @NativeType("WORD")
    public short dmSize() {
        return ndmSize(address());
    }

    @NativeType("WORD")
    public short dmDriverExtra() {
        return ndmDriverExtra(address());
    }

    @NativeType("DWORD")
    public int dmFields() {
        return ndmFields(address());
    }

    public short dmOrientation() {
        return ndmOrientation(address());
    }

    public short dmPaperSize() {
        return ndmPaperSize(address());
    }

    public short dmPaperLength() {
        return ndmPaperLength(address());
    }

    public short dmPaperWidth() {
        return ndmPaperWidth(address());
    }

    public short dmScale() {
        return ndmScale(address());
    }

    public short dmCopies() {
        return ndmCopies(address());
    }

    public short dmDefaultSource() {
        return ndmDefaultSource(address());
    }

    public short dmPrintQuality() {
        return ndmPrintQuality(address());
    }

    public POINTL dmPosition() {
        return ndmPosition(address());
    }

    @NativeType("DWORD")
    public int dmDisplayOrientation() {
        return ndmDisplayOrientation(address());
    }

    @NativeType("DWORD")
    public int dmDisplayFixedOutput() {
        return ndmDisplayFixedOutput(address());
    }

    public short dmColor() {
        return ndmColor(address());
    }

    public short dmDuplex() {
        return ndmDuplex(address());
    }

    public short dmYResolution() {
        return ndmYResolution(address());
    }

    public short dmTTOption() {
        return ndmTTOption(address());
    }

    public short dmCollate() {
        return ndmCollate(address());
    }

    @NativeType("TCHAR[32]")
    public ByteBuffer dmFormName() {
        return ndmFormName(address());
    }

    @NativeType("TCHAR[32]")
    public String dmFormNameString() {
        return ndmFormNameString(address());
    }

    @NativeType("WORD")
    public short dmLogPixels() {
        return ndmLogPixels(address());
    }

    @NativeType("DWORD")
    public int dmBitsPerPel() {
        return ndmBitsPerPel(address());
    }

    @NativeType("DWORD")
    public int dmPelsWidth() {
        return ndmPelsWidth(address());
    }

    @NativeType("DWORD")
    public int dmPelsHeight() {
        return ndmPelsHeight(address());
    }

    @NativeType("DWORD")
    public int dmDisplayFlags() {
        return ndmDisplayFlags(address());
    }

    @NativeType("DWORD")
    public int dmNup() {
        return ndmNup(address());
    }

    @NativeType("DWORD")
    public int dmDisplayFrequency() {
        return ndmDisplayFrequency(address());
    }

    @NativeType("DWORD")
    public int dmICMMethod() {
        return ndmICMMethod(address());
    }

    @NativeType("DWORD")
    public int dmICMIntent() {
        return ndmICMIntent(address());
    }

    @NativeType("DWORD")
    public int dmMediaType() {
        return ndmMediaType(address());
    }

    @NativeType("DWORD")
    public int dmDitherType() {
        return ndmDitherType(address());
    }

    @NativeType("DWORD")
    public int dmReserved1() {
        return ndmReserved1(address());
    }

    @NativeType("DWORD")
    public int dmReserved2() {
        return ndmReserved2(address());
    }

    @NativeType("DWORD")
    public int dmPanningWidth() {
        return ndmPanningWidth(address());
    }

    @NativeType("DWORD")
    public int dmPanningHeight() {
        return ndmPanningHeight(address());
    }

    public DEVMODE dmSpecVersion(@NativeType("WORD") short s) {
        ndmSpecVersion(address(), s);
        return this;
    }

    public DEVMODE dmSize(@NativeType("WORD") short s) {
        ndmSize(address(), s);
        return this;
    }

    public DEVMODE dmDriverExtra(@NativeType("WORD") short s) {
        ndmDriverExtra(address(), s);
        return this;
    }

    public DEVMODE set(DEVMODE devmode) {
        MemoryUtil.memCopy(devmode.address(), address(), SIZEOF);
        return this;
    }

    public static DEVMODE malloc() {
        return new DEVMODE(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static DEVMODE calloc() {
        return new DEVMODE(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static DEVMODE create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new DEVMODE(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static DEVMODE create(long j) {
        return new DEVMODE(j, null);
    }

    public static DEVMODE createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new DEVMODE(j, null);
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
    public static DEVMODE mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static DEVMODE callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static DEVMODE mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static DEVMODE callocStack(MemoryStack memoryStack) {
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

    public static DEVMODE malloc(MemoryStack memoryStack) {
        return new DEVMODE(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static DEVMODE calloc(MemoryStack memoryStack) {
        return new DEVMODE(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static ByteBuffer ndmDeviceName(long j) {
        return MemoryUtil.memByteBuffer(j + DMDEVICENAME, 64);
    }

    public static String ndmDeviceNameString(long j) {
        return MemoryUtil.memUTF16(j + DMDEVICENAME);
    }

    public static short ndmSpecVersion(long j) {
        return UNSAFE.getShort((Object) null, j + DMSPECVERSION);
    }

    public static short ndmDriverVersion(long j) {
        return UNSAFE.getShort((Object) null, j + DMDRIVERVERSION);
    }

    public static short ndmSize(long j) {
        return UNSAFE.getShort((Object) null, j + DMSIZE);
    }

    public static short ndmDriverExtra(long j) {
        return UNSAFE.getShort((Object) null, j + DMDRIVEREXTRA);
    }

    public static int ndmFields(long j) {
        return UNSAFE.getInt((Object) null, j + DMFIELDS);
    }

    public static short ndmOrientation(long j) {
        return UNSAFE.getShort((Object) null, j + DMORIENTATION);
    }

    public static short ndmPaperSize(long j) {
        return UNSAFE.getShort((Object) null, j + DMPAPERSIZE);
    }

    public static short ndmPaperLength(long j) {
        return UNSAFE.getShort((Object) null, j + DMPAPERLENGTH);
    }

    public static short ndmPaperWidth(long j) {
        return UNSAFE.getShort((Object) null, j + DMPAPERWIDTH);
    }

    public static short ndmScale(long j) {
        return UNSAFE.getShort((Object) null, j + DMSCALE);
    }

    public static short ndmCopies(long j) {
        return UNSAFE.getShort((Object) null, j + DMCOPIES);
    }

    public static short ndmDefaultSource(long j) {
        return UNSAFE.getShort((Object) null, j + DMDEFAULTSOURCE);
    }

    public static short ndmPrintQuality(long j) {
        return UNSAFE.getShort((Object) null, j + DMPRINTQUALITY);
    }

    public static POINTL ndmPosition(long j) {
        return POINTL.create(j + DMPOSITION);
    }

    public static int ndmDisplayOrientation(long j) {
        return UNSAFE.getInt((Object) null, j + DMDISPLAYORIENTATION);
    }

    public static int ndmDisplayFixedOutput(long j) {
        return UNSAFE.getInt((Object) null, j + DMDISPLAYFIXEDOUTPUT);
    }

    public static short ndmColor(long j) {
        return UNSAFE.getShort((Object) null, j + DMCOLOR);
    }

    public static short ndmDuplex(long j) {
        return UNSAFE.getShort((Object) null, j + DMDUPLEX);
    }

    public static short ndmYResolution(long j) {
        return UNSAFE.getShort((Object) null, j + DMYRESOLUTION);
    }

    public static short ndmTTOption(long j) {
        return UNSAFE.getShort((Object) null, j + DMTTOPTION);
    }

    public static short ndmCollate(long j) {
        return UNSAFE.getShort((Object) null, j + DMCOLLATE);
    }

    public static ByteBuffer ndmFormName(long j) {
        return MemoryUtil.memByteBuffer(j + DMFORMNAME, 64);
    }

    public static String ndmFormNameString(long j) {
        return MemoryUtil.memUTF16(j + DMFORMNAME);
    }

    public static short ndmLogPixels(long j) {
        return UNSAFE.getShort((Object) null, j + DMLOGPIXELS);
    }

    public static int ndmBitsPerPel(long j) {
        return UNSAFE.getInt((Object) null, j + DMBITSPERPEL);
    }

    public static int ndmPelsWidth(long j) {
        return UNSAFE.getInt((Object) null, j + DMPELSWIDTH);
    }

    public static int ndmPelsHeight(long j) {
        return UNSAFE.getInt((Object) null, j + DMPELSHEIGHT);
    }

    public static int ndmDisplayFlags(long j) {
        return UNSAFE.getInt((Object) null, j + DMDISPLAYFLAGS);
    }

    public static int ndmNup(long j) {
        return UNSAFE.getInt((Object) null, j + DMNUP);
    }

    public static int ndmDisplayFrequency(long j) {
        return UNSAFE.getInt((Object) null, j + DMDISPLAYFREQUENCY);
    }

    public static int ndmICMMethod(long j) {
        return UNSAFE.getInt((Object) null, j + DMICMMETHOD);
    }

    public static int ndmICMIntent(long j) {
        return UNSAFE.getInt((Object) null, j + DMICMINTENT);
    }

    public static int ndmMediaType(long j) {
        return UNSAFE.getInt((Object) null, j + DMMEDIATYPE);
    }

    public static int ndmDitherType(long j) {
        return UNSAFE.getInt((Object) null, j + DMDITHERTYPE);
    }

    public static int ndmReserved1(long j) {
        return UNSAFE.getInt((Object) null, j + DMRESERVED1);
    }

    public static int ndmReserved2(long j) {
        return UNSAFE.getInt((Object) null, j + DMRESERVED2);
    }

    public static int ndmPanningWidth(long j) {
        return UNSAFE.getInt((Object) null, j + DMPANNINGWIDTH);
    }

    public static int ndmPanningHeight(long j) {
        return UNSAFE.getInt((Object) null, j + DMPANNINGHEIGHT);
    }

    public static void ndmSpecVersion(long j, short s) {
        UNSAFE.putShort((Object) null, j + DMSPECVERSION, s);
    }

    public static void ndmSize(long j, short s) {
        UNSAFE.putShort((Object) null, j + DMSIZE, s);
    }

    public static void ndmDriverExtra(long j, short s) {
        UNSAFE.putShort((Object) null, j + DMDRIVEREXTRA, s);
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/DEVMODE$Buffer.class */
    public static class Buffer extends StructBuffer<DEVMODE, Buffer> implements NativeResource {
        private static final DEVMODE ELEMENT_FACTORY = DEVMODE.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / DEVMODE.SIZEOF);
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
        public DEVMODE getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("TCHAR[32]")
        public ByteBuffer dmDeviceName() {
            return DEVMODE.ndmDeviceName(address());
        }

        @NativeType("TCHAR[32]")
        public String dmDeviceNameString() {
            return DEVMODE.ndmDeviceNameString(address());
        }

        @NativeType("WORD")
        public short dmSpecVersion() {
            return DEVMODE.ndmSpecVersion(address());
        }

        @NativeType("WORD")
        public short dmDriverVersion() {
            return DEVMODE.ndmDriverVersion(address());
        }

        @NativeType("WORD")
        public short dmSize() {
            return DEVMODE.ndmSize(address());
        }

        @NativeType("WORD")
        public short dmDriverExtra() {
            return DEVMODE.ndmDriverExtra(address());
        }

        @NativeType("DWORD")
        public int dmFields() {
            return DEVMODE.ndmFields(address());
        }

        public short dmOrientation() {
            return DEVMODE.ndmOrientation(address());
        }

        public short dmPaperSize() {
            return DEVMODE.ndmPaperSize(address());
        }

        public short dmPaperLength() {
            return DEVMODE.ndmPaperLength(address());
        }

        public short dmPaperWidth() {
            return DEVMODE.ndmPaperWidth(address());
        }

        public short dmScale() {
            return DEVMODE.ndmScale(address());
        }

        public short dmCopies() {
            return DEVMODE.ndmCopies(address());
        }

        public short dmDefaultSource() {
            return DEVMODE.ndmDefaultSource(address());
        }

        public short dmPrintQuality() {
            return DEVMODE.ndmPrintQuality(address());
        }

        public POINTL dmPosition() {
            return DEVMODE.ndmPosition(address());
        }

        @NativeType("DWORD")
        public int dmDisplayOrientation() {
            return DEVMODE.ndmDisplayOrientation(address());
        }

        @NativeType("DWORD")
        public int dmDisplayFixedOutput() {
            return DEVMODE.ndmDisplayFixedOutput(address());
        }

        public short dmColor() {
            return DEVMODE.ndmColor(address());
        }

        public short dmDuplex() {
            return DEVMODE.ndmDuplex(address());
        }

        public short dmYResolution() {
            return DEVMODE.ndmYResolution(address());
        }

        public short dmTTOption() {
            return DEVMODE.ndmTTOption(address());
        }

        public short dmCollate() {
            return DEVMODE.ndmCollate(address());
        }

        @NativeType("TCHAR[32]")
        public ByteBuffer dmFormName() {
            return DEVMODE.ndmFormName(address());
        }

        @NativeType("TCHAR[32]")
        public String dmFormNameString() {
            return DEVMODE.ndmFormNameString(address());
        }

        @NativeType("WORD")
        public short dmLogPixels() {
            return DEVMODE.ndmLogPixels(address());
        }

        @NativeType("DWORD")
        public int dmBitsPerPel() {
            return DEVMODE.ndmBitsPerPel(address());
        }

        @NativeType("DWORD")
        public int dmPelsWidth() {
            return DEVMODE.ndmPelsWidth(address());
        }

        @NativeType("DWORD")
        public int dmPelsHeight() {
            return DEVMODE.ndmPelsHeight(address());
        }

        @NativeType("DWORD")
        public int dmDisplayFlags() {
            return DEVMODE.ndmDisplayFlags(address());
        }

        @NativeType("DWORD")
        public int dmNup() {
            return DEVMODE.ndmNup(address());
        }

        @NativeType("DWORD")
        public int dmDisplayFrequency() {
            return DEVMODE.ndmDisplayFrequency(address());
        }

        @NativeType("DWORD")
        public int dmICMMethod() {
            return DEVMODE.ndmICMMethod(address());
        }

        @NativeType("DWORD")
        public int dmICMIntent() {
            return DEVMODE.ndmICMIntent(address());
        }

        @NativeType("DWORD")
        public int dmMediaType() {
            return DEVMODE.ndmMediaType(address());
        }

        @NativeType("DWORD")
        public int dmDitherType() {
            return DEVMODE.ndmDitherType(address());
        }

        @NativeType("DWORD")
        public int dmReserved1() {
            return DEVMODE.ndmReserved1(address());
        }

        @NativeType("DWORD")
        public int dmReserved2() {
            return DEVMODE.ndmReserved2(address());
        }

        @NativeType("DWORD")
        public int dmPanningWidth() {
            return DEVMODE.ndmPanningWidth(address());
        }

        @NativeType("DWORD")
        public int dmPanningHeight() {
            return DEVMODE.ndmPanningHeight(address());
        }

        public Buffer dmSpecVersion(@NativeType("WORD") short s) {
            DEVMODE.ndmSpecVersion(address(), s);
            return this;
        }

        public Buffer dmSize(@NativeType("WORD") short s) {
            DEVMODE.ndmSize(address(), s);
            return this;
        }

        public Buffer dmDriverExtra(@NativeType("WORD") short s) {
            DEVMODE.ndmDriverExtra(address(), s);
            return this;
        }
    }
}
