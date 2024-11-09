package org.lwjgl.stb;

import org.lwjgl.stb.STBRPNode;
import org.lwjgl.stb.STBRPRect;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/STBRectPack.class */
public class STBRectPack {
    public static final int STBRP__MAXVAL = Integer.MAX_VALUE;
    public static final int STBRP_HEURISTIC_Skyline_default = 0;
    public static final int STBRP_HEURISTIC_Skyline_BL_sortHeight = 0;
    public static final int STBRP_HEURISTIC_Skyline_BF_sortHeight = 1;

    public static native int nstbrp_pack_rects(long j, long j2, int i);

    public static native void nstbrp_init_target(long j, int i, int i2, long j2, int i3);

    public static native void nstbrp_setup_allow_out_of_mem(long j, int i);

    public static native void nstbrp_setup_heuristic(long j, int i);

    static {
        LibSTB.initialize();
    }

    protected STBRectPack() {
        throw new UnsupportedOperationException();
    }

    public static int stbrp_pack_rects(@NativeType("stbrp_context *") STBRPContext sTBRPContext, @NativeType("stbrp_rect *") STBRPRect.Buffer buffer) {
        return nstbrp_pack_rects(sTBRPContext.address(), buffer.address(), buffer.remaining());
    }

    public static void stbrp_init_target(@NativeType("stbrp_context *") STBRPContext sTBRPContext, int i, int i2, @NativeType("stbrp_node *") STBRPNode.Buffer buffer) {
        nstbrp_init_target(sTBRPContext.address(), i, i2, buffer.address(), buffer.remaining());
    }

    public static void stbrp_setup_allow_out_of_mem(@NativeType("stbrp_context *") STBRPContext sTBRPContext, @NativeType("int") boolean z) {
        nstbrp_setup_allow_out_of_mem(sTBRPContext.address(), z ? 1 : 0);
    }

    public static void stbrp_setup_heuristic(@NativeType("stbrp_context *") STBRPContext sTBRPContext, int i) {
        nstbrp_setup_heuristic(sTBRPContext.address(), i);
    }
}
