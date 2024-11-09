package org.lwjgl.opengl;

/* loaded from: infinitode-2.jar:org/lwjgl/opengl/KHRBlendEquationAdvanced.class */
public class KHRBlendEquationAdvanced {
    public static final int GL_MULTIPLY_KHR = 37524;
    public static final int GL_SCREEN_KHR = 37525;
    public static final int GL_OVERLAY_KHR = 37526;
    public static final int GL_DARKEN_KHR = 37527;
    public static final int GL_LIGHTEN_KHR = 37528;
    public static final int GL_COLORDODGE_KHR = 37529;
    public static final int GL_COLORBURN_KHR = 37530;
    public static final int GL_HARDLIGHT_KHR = 37531;
    public static final int GL_SOFTLIGHT_KHR = 37532;
    public static final int GL_DIFFERENCE_KHR = 37534;
    public static final int GL_EXCLUSION_KHR = 37536;
    public static final int GL_HSL_HUE_KHR = 37549;
    public static final int GL_HSL_SATURATION_KHR = 37550;
    public static final int GL_HSL_COLOR_KHR = 37551;
    public static final int GL_HSL_LUMINOSITY_KHR = 37552;

    public static native void glBlendBarrierKHR();

    static {
        GL.initialize();
    }

    protected KHRBlendEquationAdvanced() {
        throw new UnsupportedOperationException();
    }
}
