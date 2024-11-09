package org.lwjgl.stb;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.StructBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIR_RESIZE.class */
public class STBIR_RESIZE extends Struct<STBIR_RESIZE> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int USER_DATA;
    public static final int INPUT_PIXELS;
    public static final int INPUT_W;
    public static final int INPUT_H;
    public static final int INPUT_S0;
    public static final int INPUT_T0;
    public static final int INPUT_S1;
    public static final int INPUT_T1;
    public static final int INPUT_CB;
    public static final int OUTPUT_PIXELS;
    public static final int OUTPUT_W;
    public static final int OUTPUT_H;
    public static final int OUTPUT_SUBX;
    public static final int OUTPUT_SUBY;
    public static final int OUTPUT_SUBW;
    public static final int OUTPUT_SUBH;
    public static final int OUTPUT_CB;
    public static final int INPUT_STRIDE_IN_BYTES;
    public static final int OUTPUT_STRIDE_IN_BYTES;
    public static final int SPLITS;
    public static final int FAST_ALPHA;
    public static final int NEEDS_REBUILD;
    public static final int CALLED_ALLOC;
    public static final int INPUT_PIXEL_LAYOUT_PUBLIC;
    public static final int OUTPUT_PIXEL_LAYOUT_PUBLIC;
    public static final int INPUT_DATA_TYPE;
    public static final int OUTPUT_DATA_TYPE;
    public static final int HORIZONTAL_FILTER;
    public static final int VERTICAL_FILTER;
    public static final int HORIZONTAL_EDGE;
    public static final int VERTICAL_EDGE;
    public static final int HORIZONTAL_FILTER_KERNEL;
    public static final int HORIZONTAL_FILTER_SUPPORT;
    public static final int VERTICAL_FILTER_KERNEL;
    public static final int VERTICAL_FILTER_SUPPORT;
    public static final int SAMPLERS;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(4), __member(4), __member(8), __member(8), __member(8), __member(8), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(POINTER_SIZE), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(4), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        USER_DATA = __struct.offsetof(0);
        INPUT_PIXELS = __struct.offsetof(1);
        INPUT_W = __struct.offsetof(2);
        INPUT_H = __struct.offsetof(3);
        INPUT_S0 = __struct.offsetof(4);
        INPUT_T0 = __struct.offsetof(5);
        INPUT_S1 = __struct.offsetof(6);
        INPUT_T1 = __struct.offsetof(7);
        INPUT_CB = __struct.offsetof(8);
        OUTPUT_PIXELS = __struct.offsetof(9);
        OUTPUT_W = __struct.offsetof(10);
        OUTPUT_H = __struct.offsetof(11);
        OUTPUT_SUBX = __struct.offsetof(12);
        OUTPUT_SUBY = __struct.offsetof(13);
        OUTPUT_SUBW = __struct.offsetof(14);
        OUTPUT_SUBH = __struct.offsetof(15);
        OUTPUT_CB = __struct.offsetof(16);
        INPUT_STRIDE_IN_BYTES = __struct.offsetof(17);
        OUTPUT_STRIDE_IN_BYTES = __struct.offsetof(18);
        SPLITS = __struct.offsetof(19);
        FAST_ALPHA = __struct.offsetof(20);
        NEEDS_REBUILD = __struct.offsetof(21);
        CALLED_ALLOC = __struct.offsetof(22);
        INPUT_PIXEL_LAYOUT_PUBLIC = __struct.offsetof(23);
        OUTPUT_PIXEL_LAYOUT_PUBLIC = __struct.offsetof(24);
        INPUT_DATA_TYPE = __struct.offsetof(25);
        OUTPUT_DATA_TYPE = __struct.offsetof(26);
        HORIZONTAL_FILTER = __struct.offsetof(27);
        VERTICAL_FILTER = __struct.offsetof(28);
        HORIZONTAL_EDGE = __struct.offsetof(29);
        VERTICAL_EDGE = __struct.offsetof(30);
        HORIZONTAL_FILTER_KERNEL = __struct.offsetof(31);
        HORIZONTAL_FILTER_SUPPORT = __struct.offsetof(32);
        VERTICAL_FILTER_KERNEL = __struct.offsetof(33);
        VERTICAL_FILTER_SUPPORT = __struct.offsetof(34);
        SAMPLERS = __struct.offsetof(35);
    }

    protected STBIR_RESIZE(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public STBIR_RESIZE create(long j, ByteBuffer byteBuffer) {
        return new STBIR_RESIZE(j, byteBuffer);
    }

    public STBIR_RESIZE(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("void *")
    public long user_data() {
        return nuser_data(address());
    }

    @NativeType("void const *")
    public ByteBuffer input_pixels(int i) {
        return ninput_pixels(address(), i);
    }

    public int input_w() {
        return ninput_w(address());
    }

    public int input_h() {
        return ninput_h(address());
    }

    public double input_s0() {
        return ninput_s0(address());
    }

    public double input_t0() {
        return ninput_t0(address());
    }

    public double input_s1() {
        return ninput_s1(address());
    }

    public double input_t1() {
        return ninput_t1(address());
    }

    @NativeType("stbir_input_callback **")
    public PointerBuffer input_cb(int i) {
        return ninput_cb(address(), i);
    }

    @NativeType("void *")
    public ByteBuffer output_pixels(int i) {
        return noutput_pixels(address(), i);
    }

    public int output_w() {
        return noutput_w(address());
    }

    public int output_h() {
        return noutput_h(address());
    }

    public int output_subx() {
        return noutput_subx(address());
    }

    public int output_suby() {
        return noutput_suby(address());
    }

    public int output_subw() {
        return noutput_subw(address());
    }

    public int output_subh() {
        return noutput_subh(address());
    }

    @NativeType("stbir_output_callback **")
    public PointerBuffer output_cb(int i) {
        return noutput_cb(address(), i);
    }

    public int input_stride_in_bytes() {
        return ninput_stride_in_bytes(address());
    }

    public int output_stride_in_bytes() {
        return noutput_stride_in_bytes(address());
    }

    public int splits() {
        return nsplits(address());
    }

    @NativeType("int")
    public boolean fast_alpha() {
        return nfast_alpha(address()) != 0;
    }

    @NativeType("int")
    public boolean needs_rebuild() {
        return nneeds_rebuild(address()) != 0;
    }

    @NativeType("int")
    public boolean called_alloc() {
        return ncalled_alloc(address()) != 0;
    }

    @NativeType("stbir_pixel_layout")
    public int input_pixel_layout_public() {
        return ninput_pixel_layout_public(address());
    }

    @NativeType("stbir_pixel_layout")
    public int output_pixel_layout_public() {
        return noutput_pixel_layout_public(address());
    }

    @NativeType("stbir_datatype")
    public int input_data_type() {
        return ninput_data_type(address());
    }

    @NativeType("stbir_datatype")
    public int output_data_type() {
        return noutput_data_type(address());
    }

    @NativeType("stbir_filter")
    public int horizontal_filter() {
        return nhorizontal_filter(address());
    }

    @NativeType("stbir_filter")
    public int vertical_filter() {
        return nvertical_filter(address());
    }

    @NativeType("stbir_edge")
    public int horizontal_edge() {
        return nhorizontal_edge(address());
    }

    @NativeType("stbir_edge")
    public int vertical_edge() {
        return nvertical_edge(address());
    }

    @NativeType("stbir__kernel_callback **")
    public PointerBuffer horizontal_filter_kernel(int i) {
        return nhorizontal_filter_kernel(address(), i);
    }

    @NativeType("stbir__support_callback **")
    public PointerBuffer horizontal_filter_support(int i) {
        return nhorizontal_filter_support(address(), i);
    }

    @NativeType("stbir__kernel_callback **")
    public PointerBuffer vertical_filter_kernel(int i) {
        return nvertical_filter_kernel(address(), i);
    }

    @NativeType("stbir__support_callback **")
    public PointerBuffer vertical_filter_support(int i) {
        return nvertical_filter_support(address(), i);
    }

    @NativeType("stbir__info *")
    public long samplers() {
        return nsamplers(address());
    }

    public STBIR_RESIZE user_data(@NativeType("void *") long j) {
        nuser_data(address(), j);
        return this;
    }

    public STBIR_RESIZE input_pixels(@NativeType("void const *") ByteBuffer byteBuffer) {
        ninput_pixels(address(), byteBuffer);
        return this;
    }

    public STBIR_RESIZE input_w(int i) {
        ninput_w(address(), i);
        return this;
    }

    public STBIR_RESIZE input_h(int i) {
        ninput_h(address(), i);
        return this;
    }

    public STBIR_RESIZE input_s0(double d) {
        ninput_s0(address(), d);
        return this;
    }

    public STBIR_RESIZE input_t0(double d) {
        ninput_t0(address(), d);
        return this;
    }

    public STBIR_RESIZE input_s1(double d) {
        ninput_s1(address(), d);
        return this;
    }

    public STBIR_RESIZE input_t1(double d) {
        ninput_t1(address(), d);
        return this;
    }

    public STBIR_RESIZE input_cb(@NativeType("stbir_input_callback **") PointerBuffer pointerBuffer) {
        ninput_cb(address(), pointerBuffer);
        return this;
    }

    public STBIR_RESIZE output_pixels(@NativeType("void *") ByteBuffer byteBuffer) {
        noutput_pixels(address(), byteBuffer);
        return this;
    }

    public STBIR_RESIZE output_w(int i) {
        noutput_w(address(), i);
        return this;
    }

    public STBIR_RESIZE output_h(int i) {
        noutput_h(address(), i);
        return this;
    }

    public STBIR_RESIZE output_subx(int i) {
        noutput_subx(address(), i);
        return this;
    }

    public STBIR_RESIZE output_suby(int i) {
        noutput_suby(address(), i);
        return this;
    }

    public STBIR_RESIZE output_subw(int i) {
        noutput_subw(address(), i);
        return this;
    }

    public STBIR_RESIZE output_subh(int i) {
        noutput_subh(address(), i);
        return this;
    }

    public STBIR_RESIZE output_cb(@NativeType("stbir_output_callback **") PointerBuffer pointerBuffer) {
        noutput_cb(address(), pointerBuffer);
        return this;
    }

    public STBIR_RESIZE input_stride_in_bytes(int i) {
        ninput_stride_in_bytes(address(), i);
        return this;
    }

    public STBIR_RESIZE output_stride_in_bytes(int i) {
        noutput_stride_in_bytes(address(), i);
        return this;
    }

    public STBIR_RESIZE splits(int i) {
        nsplits(address(), i);
        return this;
    }

    public STBIR_RESIZE fast_alpha(@NativeType("int") boolean z) {
        nfast_alpha(address(), z ? 1 : 0);
        return this;
    }

    public STBIR_RESIZE needs_rebuild(@NativeType("int") boolean z) {
        nneeds_rebuild(address(), z ? 1 : 0);
        return this;
    }

    public STBIR_RESIZE called_alloc(@NativeType("int") boolean z) {
        ncalled_alloc(address(), z ? 1 : 0);
        return this;
    }

    public STBIR_RESIZE input_pixel_layout_public(@NativeType("stbir_pixel_layout") int i) {
        ninput_pixel_layout_public(address(), i);
        return this;
    }

    public STBIR_RESIZE output_pixel_layout_public(@NativeType("stbir_pixel_layout") int i) {
        noutput_pixel_layout_public(address(), i);
        return this;
    }

    public STBIR_RESIZE input_data_type(@NativeType("stbir_datatype") int i) {
        ninput_data_type(address(), i);
        return this;
    }

    public STBIR_RESIZE output_data_type(@NativeType("stbir_datatype") int i) {
        noutput_data_type(address(), i);
        return this;
    }

    public STBIR_RESIZE horizontal_filter(@NativeType("stbir_filter") int i) {
        nhorizontal_filter(address(), i);
        return this;
    }

    public STBIR_RESIZE vertical_filter(@NativeType("stbir_filter") int i) {
        nvertical_filter(address(), i);
        return this;
    }

    public STBIR_RESIZE horizontal_edge(@NativeType("stbir_edge") int i) {
        nhorizontal_edge(address(), i);
        return this;
    }

    public STBIR_RESIZE vertical_edge(@NativeType("stbir_edge") int i) {
        nvertical_edge(address(), i);
        return this;
    }

    public STBIR_RESIZE horizontal_filter_kernel(@NativeType("stbir__kernel_callback **") PointerBuffer pointerBuffer) {
        nhorizontal_filter_kernel(address(), pointerBuffer);
        return this;
    }

    public STBIR_RESIZE horizontal_filter_support(@NativeType("stbir__support_callback **") PointerBuffer pointerBuffer) {
        nhorizontal_filter_support(address(), pointerBuffer);
        return this;
    }

    public STBIR_RESIZE vertical_filter_kernel(@NativeType("stbir__kernel_callback **") PointerBuffer pointerBuffer) {
        nvertical_filter_kernel(address(), pointerBuffer);
        return this;
    }

    public STBIR_RESIZE vertical_filter_support(@NativeType("stbir__support_callback **") PointerBuffer pointerBuffer) {
        nvertical_filter_support(address(), pointerBuffer);
        return this;
    }

    public STBIR_RESIZE samplers(@NativeType("stbir__info *") long j) {
        nsamplers(address(), j);
        return this;
    }

    public STBIR_RESIZE set(long j, ByteBuffer byteBuffer, int i, int i2, double d, double d2, double d3, double d4, PointerBuffer pointerBuffer, ByteBuffer byteBuffer2, int i3, int i4, int i5, int i6, int i7, int i8, PointerBuffer pointerBuffer2, int i9, int i10, int i11, boolean z, boolean z2, boolean z3, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, PointerBuffer pointerBuffer3, PointerBuffer pointerBuffer4, PointerBuffer pointerBuffer5, PointerBuffer pointerBuffer6, long j2) {
        user_data(j);
        input_pixels(byteBuffer);
        input_w(i);
        input_h(i2);
        input_s0(d);
        input_t0(d2);
        input_s1(d3);
        input_t1(d4);
        input_cb(pointerBuffer);
        output_pixels(byteBuffer2);
        output_w(i3);
        output_h(i4);
        output_subx(i5);
        output_suby(i6);
        output_subw(i7);
        output_subh(i8);
        output_cb(pointerBuffer2);
        input_stride_in_bytes(i9);
        output_stride_in_bytes(i10);
        splits(i11);
        fast_alpha(z);
        needs_rebuild(z2);
        called_alloc(z3);
        input_pixel_layout_public(i12);
        output_pixel_layout_public(i13);
        input_data_type(i14);
        output_data_type(i15);
        horizontal_filter(i16);
        vertical_filter(i17);
        horizontal_edge(i18);
        vertical_edge(i19);
        horizontal_filter_kernel(pointerBuffer3);
        horizontal_filter_support(pointerBuffer4);
        vertical_filter_kernel(pointerBuffer5);
        vertical_filter_support(pointerBuffer6);
        samplers(j2);
        return this;
    }

    public STBIR_RESIZE set(STBIR_RESIZE stbir_resize) {
        MemoryUtil.memCopy(stbir_resize.address(), address(), SIZEOF);
        return this;
    }

    public static STBIR_RESIZE malloc() {
        return new STBIR_RESIZE(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static STBIR_RESIZE calloc() {
        return new STBIR_RESIZE(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static STBIR_RESIZE create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new STBIR_RESIZE(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static STBIR_RESIZE create(long j) {
        return new STBIR_RESIZE(j, null);
    }

    public static STBIR_RESIZE createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new STBIR_RESIZE(j, null);
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

    public static STBIR_RESIZE malloc(MemoryStack memoryStack) {
        return new STBIR_RESIZE(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static STBIR_RESIZE calloc(MemoryStack memoryStack) {
        return new STBIR_RESIZE(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static Buffer malloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.nmalloc(ALIGNOF, i * SIZEOF), i);
    }

    public static Buffer calloc(int i, MemoryStack memoryStack) {
        return new Buffer(memoryStack.ncalloc(ALIGNOF, i, SIZEOF), i);
    }

    public static long nuser_data(long j) {
        return MemoryUtil.memGetAddress(j + USER_DATA);
    }

    public static ByteBuffer ninput_pixels(long j, int i) {
        return MemoryUtil.memByteBuffer(MemoryUtil.memGetAddress(j + INPUT_PIXELS), i);
    }

    public static int ninput_w(long j) {
        return UNSAFE.getInt((Object) null, j + INPUT_W);
    }

    public static int ninput_h(long j) {
        return UNSAFE.getInt((Object) null, j + INPUT_H);
    }

    public static double ninput_s0(long j) {
        return UNSAFE.getDouble((Object) null, j + INPUT_S0);
    }

    public static double ninput_t0(long j) {
        return UNSAFE.getDouble((Object) null, j + INPUT_T0);
    }

    public static double ninput_s1(long j) {
        return UNSAFE.getDouble((Object) null, j + INPUT_S1);
    }

    public static double ninput_t1(long j) {
        return UNSAFE.getDouble((Object) null, j + INPUT_T1);
    }

    public static PointerBuffer ninput_cb(long j, int i) {
        return MemoryUtil.memPointerBufferSafe(MemoryUtil.memGetAddress(j + INPUT_CB), i);
    }

    public static ByteBuffer noutput_pixels(long j, int i) {
        return MemoryUtil.memByteBufferSafe(MemoryUtil.memGetAddress(j + OUTPUT_PIXELS), i);
    }

    public static int noutput_w(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_W);
    }

    public static int noutput_h(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_H);
    }

    public static int noutput_subx(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_SUBX);
    }

    public static int noutput_suby(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_SUBY);
    }

    public static int noutput_subw(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_SUBW);
    }

    public static int noutput_subh(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_SUBH);
    }

    public static PointerBuffer noutput_cb(long j, int i) {
        return MemoryUtil.memPointerBufferSafe(MemoryUtil.memGetAddress(j + OUTPUT_CB), i);
    }

    public static int ninput_stride_in_bytes(long j) {
        return UNSAFE.getInt((Object) null, j + INPUT_STRIDE_IN_BYTES);
    }

    public static int noutput_stride_in_bytes(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_STRIDE_IN_BYTES);
    }

    public static int nsplits(long j) {
        return UNSAFE.getInt((Object) null, j + SPLITS);
    }

    public static int nfast_alpha(long j) {
        return UNSAFE.getInt((Object) null, j + FAST_ALPHA);
    }

    public static int nneeds_rebuild(long j) {
        return UNSAFE.getInt((Object) null, j + NEEDS_REBUILD);
    }

    public static int ncalled_alloc(long j) {
        return UNSAFE.getInt((Object) null, j + CALLED_ALLOC);
    }

    public static int ninput_pixel_layout_public(long j) {
        return UNSAFE.getInt((Object) null, j + INPUT_PIXEL_LAYOUT_PUBLIC);
    }

    public static int noutput_pixel_layout_public(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_PIXEL_LAYOUT_PUBLIC);
    }

    public static int ninput_data_type(long j) {
        return UNSAFE.getInt((Object) null, j + INPUT_DATA_TYPE);
    }

    public static int noutput_data_type(long j) {
        return UNSAFE.getInt((Object) null, j + OUTPUT_DATA_TYPE);
    }

    public static int nhorizontal_filter(long j) {
        return UNSAFE.getInt((Object) null, j + HORIZONTAL_FILTER);
    }

    public static int nvertical_filter(long j) {
        return UNSAFE.getInt((Object) null, j + VERTICAL_FILTER);
    }

    public static int nhorizontal_edge(long j) {
        return UNSAFE.getInt((Object) null, j + HORIZONTAL_EDGE);
    }

    public static int nvertical_edge(long j) {
        return UNSAFE.getInt((Object) null, j + VERTICAL_EDGE);
    }

    public static PointerBuffer nhorizontal_filter_kernel(long j, int i) {
        return MemoryUtil.memPointerBufferSafe(MemoryUtil.memGetAddress(j + HORIZONTAL_FILTER_KERNEL), i);
    }

    public static PointerBuffer nhorizontal_filter_support(long j, int i) {
        return MemoryUtil.memPointerBufferSafe(MemoryUtil.memGetAddress(j + HORIZONTAL_FILTER_SUPPORT), i);
    }

    public static PointerBuffer nvertical_filter_kernel(long j, int i) {
        return MemoryUtil.memPointerBufferSafe(MemoryUtil.memGetAddress(j + VERTICAL_FILTER_KERNEL), i);
    }

    public static PointerBuffer nvertical_filter_support(long j, int i) {
        return MemoryUtil.memPointerBufferSafe(MemoryUtil.memGetAddress(j + VERTICAL_FILTER_SUPPORT), i);
    }

    public static long nsamplers(long j) {
        return MemoryUtil.memGetAddress(j + SAMPLERS);
    }

    public static void nuser_data(long j, long j2) {
        MemoryUtil.memPutAddress(j + USER_DATA, j2);
    }

    public static void ninput_pixels(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + INPUT_PIXELS, MemoryUtil.memAddress(byteBuffer));
    }

    public static void ninput_w(long j, int i) {
        UNSAFE.putInt((Object) null, j + INPUT_W, i);
    }

    public static void ninput_h(long j, int i) {
        UNSAFE.putInt((Object) null, j + INPUT_H, i);
    }

    public static void ninput_s0(long j, double d) {
        UNSAFE.putDouble((Object) null, j + INPUT_S0, d);
    }

    public static void ninput_t0(long j, double d) {
        UNSAFE.putDouble((Object) null, j + INPUT_T0, d);
    }

    public static void ninput_s1(long j, double d) {
        UNSAFE.putDouble((Object) null, j + INPUT_S1, d);
    }

    public static void ninput_t1(long j, double d) {
        UNSAFE.putDouble((Object) null, j + INPUT_T1, d);
    }

    public static void ninput_cb(long j, PointerBuffer pointerBuffer) {
        MemoryUtil.memPutAddress(j + INPUT_CB, MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static void noutput_pixels(long j, ByteBuffer byteBuffer) {
        MemoryUtil.memPutAddress(j + OUTPUT_PIXELS, MemoryUtil.memAddressSafe(byteBuffer));
    }

    public static void noutput_w(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_W, i);
    }

    public static void noutput_h(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_H, i);
    }

    public static void noutput_subx(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_SUBX, i);
    }

    public static void noutput_suby(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_SUBY, i);
    }

    public static void noutput_subw(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_SUBW, i);
    }

    public static void noutput_subh(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_SUBH, i);
    }

    public static void noutput_cb(long j, PointerBuffer pointerBuffer) {
        MemoryUtil.memPutAddress(j + OUTPUT_CB, MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static void ninput_stride_in_bytes(long j, int i) {
        UNSAFE.putInt((Object) null, j + INPUT_STRIDE_IN_BYTES, i);
    }

    public static void noutput_stride_in_bytes(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_STRIDE_IN_BYTES, i);
    }

    public static void nsplits(long j, int i) {
        UNSAFE.putInt((Object) null, j + SPLITS, i);
    }

    public static void nfast_alpha(long j, int i) {
        UNSAFE.putInt((Object) null, j + FAST_ALPHA, i);
    }

    public static void nneeds_rebuild(long j, int i) {
        UNSAFE.putInt((Object) null, j + NEEDS_REBUILD, i);
    }

    public static void ncalled_alloc(long j, int i) {
        UNSAFE.putInt((Object) null, j + CALLED_ALLOC, i);
    }

    public static void ninput_pixel_layout_public(long j, int i) {
        UNSAFE.putInt((Object) null, j + INPUT_PIXEL_LAYOUT_PUBLIC, i);
    }

    public static void noutput_pixel_layout_public(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_PIXEL_LAYOUT_PUBLIC, i);
    }

    public static void ninput_data_type(long j, int i) {
        UNSAFE.putInt((Object) null, j + INPUT_DATA_TYPE, i);
    }

    public static void noutput_data_type(long j, int i) {
        UNSAFE.putInt((Object) null, j + OUTPUT_DATA_TYPE, i);
    }

    public static void nhorizontal_filter(long j, int i) {
        UNSAFE.putInt((Object) null, j + HORIZONTAL_FILTER, i);
    }

    public static void nvertical_filter(long j, int i) {
        UNSAFE.putInt((Object) null, j + VERTICAL_FILTER, i);
    }

    public static void nhorizontal_edge(long j, int i) {
        UNSAFE.putInt((Object) null, j + HORIZONTAL_EDGE, i);
    }

    public static void nvertical_edge(long j, int i) {
        UNSAFE.putInt((Object) null, j + VERTICAL_EDGE, i);
    }

    public static void nhorizontal_filter_kernel(long j, PointerBuffer pointerBuffer) {
        MemoryUtil.memPutAddress(j + HORIZONTAL_FILTER_KERNEL, MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static void nhorizontal_filter_support(long j, PointerBuffer pointerBuffer) {
        MemoryUtil.memPutAddress(j + HORIZONTAL_FILTER_SUPPORT, MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static void nvertical_filter_kernel(long j, PointerBuffer pointerBuffer) {
        MemoryUtil.memPutAddress(j + VERTICAL_FILTER_KERNEL, MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static void nvertical_filter_support(long j, PointerBuffer pointerBuffer) {
        MemoryUtil.memPutAddress(j + VERTICAL_FILTER_SUPPORT, MemoryUtil.memAddressSafe(pointerBuffer));
    }

    public static void nsamplers(long j, long j2) {
        MemoryUtil.memPutAddress(j + SAMPLERS, Checks.check(j2));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + INPUT_PIXELS));
        Checks.check(MemoryUtil.memGetAddress(j + SAMPLERS));
    }

    /* loaded from: infinitode-2.jar:org/lwjgl/stb/STBIR_RESIZE$Buffer.class */
    public static class Buffer extends StructBuffer<STBIR_RESIZE, Buffer> implements NativeResource {
        private static final STBIR_RESIZE ELEMENT_FACTORY = STBIR_RESIZE.create(-1L);

        public Buffer(ByteBuffer byteBuffer) {
            super(byteBuffer, byteBuffer.remaining() / STBIR_RESIZE.SIZEOF);
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
        public STBIR_RESIZE getElementFactory() {
            return ELEMENT_FACTORY;
        }

        @NativeType("void *")
        public long user_data() {
            return STBIR_RESIZE.nuser_data(address());
        }

        @NativeType("void const *")
        public ByteBuffer input_pixels(int i) {
            return STBIR_RESIZE.ninput_pixels(address(), i);
        }

        public int input_w() {
            return STBIR_RESIZE.ninput_w(address());
        }

        public int input_h() {
            return STBIR_RESIZE.ninput_h(address());
        }

        public double input_s0() {
            return STBIR_RESIZE.ninput_s0(address());
        }

        public double input_t0() {
            return STBIR_RESIZE.ninput_t0(address());
        }

        public double input_s1() {
            return STBIR_RESIZE.ninput_s1(address());
        }

        public double input_t1() {
            return STBIR_RESIZE.ninput_t1(address());
        }

        @NativeType("stbir_input_callback **")
        public PointerBuffer input_cb(int i) {
            return STBIR_RESIZE.ninput_cb(address(), i);
        }

        @NativeType("void *")
        public ByteBuffer output_pixels(int i) {
            return STBIR_RESIZE.noutput_pixels(address(), i);
        }

        public int output_w() {
            return STBIR_RESIZE.noutput_w(address());
        }

        public int output_h() {
            return STBIR_RESIZE.noutput_h(address());
        }

        public int output_subx() {
            return STBIR_RESIZE.noutput_subx(address());
        }

        public int output_suby() {
            return STBIR_RESIZE.noutput_suby(address());
        }

        public int output_subw() {
            return STBIR_RESIZE.noutput_subw(address());
        }

        public int output_subh() {
            return STBIR_RESIZE.noutput_subh(address());
        }

        @NativeType("stbir_output_callback **")
        public PointerBuffer output_cb(int i) {
            return STBIR_RESIZE.noutput_cb(address(), i);
        }

        public int input_stride_in_bytes() {
            return STBIR_RESIZE.ninput_stride_in_bytes(address());
        }

        public int output_stride_in_bytes() {
            return STBIR_RESIZE.noutput_stride_in_bytes(address());
        }

        public int splits() {
            return STBIR_RESIZE.nsplits(address());
        }

        @NativeType("int")
        public boolean fast_alpha() {
            return STBIR_RESIZE.nfast_alpha(address()) != 0;
        }

        @NativeType("int")
        public boolean needs_rebuild() {
            return STBIR_RESIZE.nneeds_rebuild(address()) != 0;
        }

        @NativeType("int")
        public boolean called_alloc() {
            return STBIR_RESIZE.ncalled_alloc(address()) != 0;
        }

        @NativeType("stbir_pixel_layout")
        public int input_pixel_layout_public() {
            return STBIR_RESIZE.ninput_pixel_layout_public(address());
        }

        @NativeType("stbir_pixel_layout")
        public int output_pixel_layout_public() {
            return STBIR_RESIZE.noutput_pixel_layout_public(address());
        }

        @NativeType("stbir_datatype")
        public int input_data_type() {
            return STBIR_RESIZE.ninput_data_type(address());
        }

        @NativeType("stbir_datatype")
        public int output_data_type() {
            return STBIR_RESIZE.noutput_data_type(address());
        }

        @NativeType("stbir_filter")
        public int horizontal_filter() {
            return STBIR_RESIZE.nhorizontal_filter(address());
        }

        @NativeType("stbir_filter")
        public int vertical_filter() {
            return STBIR_RESIZE.nvertical_filter(address());
        }

        @NativeType("stbir_edge")
        public int horizontal_edge() {
            return STBIR_RESIZE.nhorizontal_edge(address());
        }

        @NativeType("stbir_edge")
        public int vertical_edge() {
            return STBIR_RESIZE.nvertical_edge(address());
        }

        @NativeType("stbir__kernel_callback **")
        public PointerBuffer horizontal_filter_kernel(int i) {
            return STBIR_RESIZE.nhorizontal_filter_kernel(address(), i);
        }

        @NativeType("stbir__support_callback **")
        public PointerBuffer horizontal_filter_support(int i) {
            return STBIR_RESIZE.nhorizontal_filter_support(address(), i);
        }

        @NativeType("stbir__kernel_callback **")
        public PointerBuffer vertical_filter_kernel(int i) {
            return STBIR_RESIZE.nvertical_filter_kernel(address(), i);
        }

        @NativeType("stbir__support_callback **")
        public PointerBuffer vertical_filter_support(int i) {
            return STBIR_RESIZE.nvertical_filter_support(address(), i);
        }

        @NativeType("stbir__info *")
        public long samplers() {
            return STBIR_RESIZE.nsamplers(address());
        }

        public Buffer user_data(@NativeType("void *") long j) {
            STBIR_RESIZE.nuser_data(address(), j);
            return this;
        }

        public Buffer input_pixels(@NativeType("void const *") ByteBuffer byteBuffer) {
            STBIR_RESIZE.ninput_pixels(address(), byteBuffer);
            return this;
        }

        public Buffer input_w(int i) {
            STBIR_RESIZE.ninput_w(address(), i);
            return this;
        }

        public Buffer input_h(int i) {
            STBIR_RESIZE.ninput_h(address(), i);
            return this;
        }

        public Buffer input_s0(double d) {
            STBIR_RESIZE.ninput_s0(address(), d);
            return this;
        }

        public Buffer input_t0(double d) {
            STBIR_RESIZE.ninput_t0(address(), d);
            return this;
        }

        public Buffer input_s1(double d) {
            STBIR_RESIZE.ninput_s1(address(), d);
            return this;
        }

        public Buffer input_t1(double d) {
            STBIR_RESIZE.ninput_t1(address(), d);
            return this;
        }

        public Buffer input_cb(@NativeType("stbir_input_callback **") PointerBuffer pointerBuffer) {
            STBIR_RESIZE.ninput_cb(address(), pointerBuffer);
            return this;
        }

        public Buffer output_pixels(@NativeType("void *") ByteBuffer byteBuffer) {
            STBIR_RESIZE.noutput_pixels(address(), byteBuffer);
            return this;
        }

        public Buffer output_w(int i) {
            STBIR_RESIZE.noutput_w(address(), i);
            return this;
        }

        public Buffer output_h(int i) {
            STBIR_RESIZE.noutput_h(address(), i);
            return this;
        }

        public Buffer output_subx(int i) {
            STBIR_RESIZE.noutput_subx(address(), i);
            return this;
        }

        public Buffer output_suby(int i) {
            STBIR_RESIZE.noutput_suby(address(), i);
            return this;
        }

        public Buffer output_subw(int i) {
            STBIR_RESIZE.noutput_subw(address(), i);
            return this;
        }

        public Buffer output_subh(int i) {
            STBIR_RESIZE.noutput_subh(address(), i);
            return this;
        }

        public Buffer output_cb(@NativeType("stbir_output_callback **") PointerBuffer pointerBuffer) {
            STBIR_RESIZE.noutput_cb(address(), pointerBuffer);
            return this;
        }

        public Buffer input_stride_in_bytes(int i) {
            STBIR_RESIZE.ninput_stride_in_bytes(address(), i);
            return this;
        }

        public Buffer output_stride_in_bytes(int i) {
            STBIR_RESIZE.noutput_stride_in_bytes(address(), i);
            return this;
        }

        public Buffer splits(int i) {
            STBIR_RESIZE.nsplits(address(), i);
            return this;
        }

        public Buffer fast_alpha(@NativeType("int") boolean z) {
            STBIR_RESIZE.nfast_alpha(address(), z ? 1 : 0);
            return this;
        }

        public Buffer needs_rebuild(@NativeType("int") boolean z) {
            STBIR_RESIZE.nneeds_rebuild(address(), z ? 1 : 0);
            return this;
        }

        public Buffer called_alloc(@NativeType("int") boolean z) {
            STBIR_RESIZE.ncalled_alloc(address(), z ? 1 : 0);
            return this;
        }

        public Buffer input_pixel_layout_public(@NativeType("stbir_pixel_layout") int i) {
            STBIR_RESIZE.ninput_pixel_layout_public(address(), i);
            return this;
        }

        public Buffer output_pixel_layout_public(@NativeType("stbir_pixel_layout") int i) {
            STBIR_RESIZE.noutput_pixel_layout_public(address(), i);
            return this;
        }

        public Buffer input_data_type(@NativeType("stbir_datatype") int i) {
            STBIR_RESIZE.ninput_data_type(address(), i);
            return this;
        }

        public Buffer output_data_type(@NativeType("stbir_datatype") int i) {
            STBIR_RESIZE.noutput_data_type(address(), i);
            return this;
        }

        public Buffer horizontal_filter(@NativeType("stbir_filter") int i) {
            STBIR_RESIZE.nhorizontal_filter(address(), i);
            return this;
        }

        public Buffer vertical_filter(@NativeType("stbir_filter") int i) {
            STBIR_RESIZE.nvertical_filter(address(), i);
            return this;
        }

        public Buffer horizontal_edge(@NativeType("stbir_edge") int i) {
            STBIR_RESIZE.nhorizontal_edge(address(), i);
            return this;
        }

        public Buffer vertical_edge(@NativeType("stbir_edge") int i) {
            STBIR_RESIZE.nvertical_edge(address(), i);
            return this;
        }

        public Buffer horizontal_filter_kernel(@NativeType("stbir__kernel_callback **") PointerBuffer pointerBuffer) {
            STBIR_RESIZE.nhorizontal_filter_kernel(address(), pointerBuffer);
            return this;
        }

        public Buffer horizontal_filter_support(@NativeType("stbir__support_callback **") PointerBuffer pointerBuffer) {
            STBIR_RESIZE.nhorizontal_filter_support(address(), pointerBuffer);
            return this;
        }

        public Buffer vertical_filter_kernel(@NativeType("stbir__kernel_callback **") PointerBuffer pointerBuffer) {
            STBIR_RESIZE.nvertical_filter_kernel(address(), pointerBuffer);
            return this;
        }

        public Buffer vertical_filter_support(@NativeType("stbir__support_callback **") PointerBuffer pointerBuffer) {
            STBIR_RESIZE.nvertical_filter_support(address(), pointerBuffer);
            return this;
        }

        public Buffer samplers(@NativeType("stbir__info *") long j) {
            STBIR_RESIZE.nsamplers(address(), j);
            return this;
        }
    }
}
