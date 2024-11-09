package org.lwjgl.openal;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTHRTF.class */
public class SOFTHRTF {
    public static final int ALC_HRTF_SOFT = 6546;
    public static final int ALC_HRTF_ID_SOFT = 6550;
    public static final int ALC_DONT_CARE_SOFT = 2;
    public static final int ALC_HRTF_STATUS_SOFT = 6547;
    public static final int ALC_NUM_HRTF_SPECIFIERS_SOFT = 6548;
    public static final int ALC_HRTF_SPECIFIER_SOFT = 6549;
    public static final int ALC_HRTF_DISABLED_SOFT = 0;
    public static final int ALC_HRTF_ENABLED_SOFT = 1;
    public static final int ALC_HRTF_DENIED_SOFT = 2;
    public static final int ALC_HRTF_REQUIRED_SOFT = 3;
    public static final int ALC_HRTF_HEADPHONES_DETECTED_SOFT = 4;
    public static final int ALC_HRTF_UNSUPPORTED_FORMAT_SOFT = 5;

    protected SOFTHRTF() {
        throw new UnsupportedOperationException();
    }

    public static long nalcGetStringiSOFT(long j, int i, int i2) {
        long j2 = ALC.getICD().alcGetStringiSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.invokePP(j, i, i2, j2);
    }

    @NativeType("ALCchar const *")
    public static String alcGetStringiSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i, @NativeType("ALCsizei") int i2) {
        return MemoryUtil.memUTF8Safe(nalcGetStringiSOFT(j, i, i2));
    }

    public static boolean nalcResetDeviceSOFT(long j, long j2) {
        long j3 = ALC.getICD().alcResetDeviceSOFT;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        return JNI.invokePPZ(j, j2, j3);
    }

    @NativeType("ALCboolean")
    public static boolean alcResetDeviceSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nalcResetDeviceSOFT(j, MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("ALCboolean")
    public static boolean alcResetDeviceSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCint const *") int[] iArr) {
        long j2 = ALC.getICD().alcResetDeviceSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        return JNI.invokePPZ(j, iArr, j2);
    }
}
