package org.lwjgl.openal;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTSourceResampler.class */
public class SOFTSourceResampler {
    public static final int AL_NUM_RESAMPLERS_SOFT = 4624;
    public static final int AL_DEFAULT_RESAMPLER_SOFT = 4625;
    public static final int AL_SOURCE_RESAMPLER_SOFT = 4626;
    public static final int AL_RESAMPLER_NAME_SOFT = 4627;

    protected SOFTSourceResampler() {
        throw new UnsupportedOperationException();
    }

    public static long nalGetStringiSOFT(int i, int i2) {
        long j = AL.getICD().alGetStringiSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokeP(i, i2, j);
    }

    @NativeType("ALchar const *")
    public static String alGetStringiSOFT(@NativeType("ALenum") int i, @NativeType("ALsizei") int i2) {
        return MemoryUtil.memUTF8Safe(nalGetStringiSOFT(i, i2));
    }
}
