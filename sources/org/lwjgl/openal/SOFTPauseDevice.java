package org.lwjgl.openal;

import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTPauseDevice.class */
public class SOFTPauseDevice {
    protected SOFTPauseDevice() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ALCvoid")
    public static void alcDevicePauseSOFT(@NativeType("ALCdevice *") long j) {
        long j2 = ALC.getICD().alcDevicePauseSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    @NativeType("ALCvoid")
    public static void alcDeviceResumeSOFT(@NativeType("ALCdevice *") long j) {
        long j2 = ALC.getICD().alcDeviceResumeSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }
}
