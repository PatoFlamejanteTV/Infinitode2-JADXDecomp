package org.lwjgl.openal;

import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTDeviceClock.class */
public class SOFTDeviceClock {
    public static final int ALC_DEVICE_CLOCK_SOFT = 5632;
    public static final int ALC_DEVICE_LATENCY_SOFT = 5633;
    public static final int ALC_DEVICE_CLOCK_LATENCY_SOFT = 5634;
    public static final int AL_SAMPLE_OFFSET_CLOCK_SOFT = 4610;
    public static final int AL_SEC_OFFSET_CLOCK_SOFT = 4611;

    protected SOFTDeviceClock() {
        throw new UnsupportedOperationException();
    }

    public static void nalcGetInteger64vSOFT(long j, int i, int i2, long j2) {
        long j3 = ALC.getICD().alcGetInteger64vSOFT;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.invokePPV(j, i, i2, j2, j3);
    }

    @NativeType("ALCvoid")
    public static void alcGetInteger64vSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i, @NativeType("ALCint64SOFT *") LongBuffer longBuffer) {
        nalcGetInteger64vSOFT(j, i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("ALCvoid")
    public static long alcGetInteger64vSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nalcGetInteger64vSOFT(j, i, 1, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALCvoid")
    public static void alcGetInteger64vSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i, @NativeType("ALCint64SOFT *") long[] jArr) {
        long j2 = ALC.getICD().alcGetInteger64vSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePPV(j, i, jArr.length, jArr, j2);
    }
}
