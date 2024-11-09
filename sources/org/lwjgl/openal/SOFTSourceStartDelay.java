package org.lwjgl.openal;

import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTSourceStartDelay.class */
public class SOFTSourceStartDelay {
    protected SOFTSourceStartDelay() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ALvoid")
    public static void alSourcePlayAtTimeSOFT(@NativeType("ALuint") int i, @NativeType("ALint64SOFT") long j) {
        long j2 = AL.getICD().alSourcePlayAtTimeSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokeJV(i, j, j2);
    }

    public static void nalSourcePlayAtTimevSOFT(int i, long j, long j2) {
        long j3 = AL.getICD().alSourcePlayAtTimevSOFT;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.invokePJV(i, j, j2, j3);
    }

    @NativeType("ALvoid")
    public static void alSourcePlayAtTimevSOFT(@NativeType("ALuint const *") IntBuffer intBuffer, @NativeType("ALint64SOFT") long j) {
        nalSourcePlayAtTimevSOFT(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), j);
    }

    @NativeType("ALvoid")
    public static void alSourcePlayAtTimevSOFT(@NativeType("ALuint const *") int[] iArr, @NativeType("ALint64SOFT") long j) {
        long j2 = AL.getICD().alSourcePlayAtTimevSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePJV(iArr.length, iArr, j, j2);
    }
}
