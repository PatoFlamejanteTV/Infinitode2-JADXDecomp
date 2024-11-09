package org.lwjgl.openal;

import java.nio.Buffer;
import java.nio.DoubleBuffer;
import java.nio.LongBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTSourceLatency.class */
public class SOFTSourceLatency {
    public static final int AL_SAMPLE_OFFSET_LATENCY_SOFT = 4608;
    public static final int AL_SEC_OFFSET_LATENCY_SOFT = 4609;

    protected SOFTSourceLatency() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ALvoid")
    public static void alSourcedSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble") double d) {
        long j = AL.getICD().alSourcedSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, d, j);
    }

    @NativeType("ALvoid")
    public static void alSource3dSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble") double d, @NativeType("ALdouble") double d2, @NativeType("ALdouble") double d3) {
        long j = AL.getICD().alSource3dSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, d, d2, d3, j);
    }

    public static void nalSourcedvSOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alSourcedvSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alSourcedvSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble const *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nalSourcedvSOFT(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void nalGetSourcedSOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alGetSourcedSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetSourcedSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nalGetSourcedSOFT(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("ALvoid")
    public static double alGetSourcedSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            DoubleBuffer callocDouble = stackGet.callocDouble(1);
            nalGetSourcedSOFT(i, i2, MemoryUtil.memAddress(callocDouble));
            return callocDouble.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetSource3dSOFT(int i, int i2, long j, long j2, long j3) {
        long j4 = AL.getICD().alGetSource3dSOFT;
        if (Checks.CHECKS) {
            Checks.check(j4);
        }
        JNI.invokePPPV(i, i2, j, j2, j3, j4);
    }

    @NativeType("ALvoid")
    public static void alGetSource3dSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble *") DoubleBuffer doubleBuffer, @NativeType("ALdouble *") DoubleBuffer doubleBuffer2, @NativeType("ALdouble *") DoubleBuffer doubleBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
            Checks.check((Buffer) doubleBuffer2, 1);
            Checks.check((Buffer) doubleBuffer3, 1);
        }
        nalGetSource3dSOFT(i, i2, MemoryUtil.memAddress(doubleBuffer), MemoryUtil.memAddress(doubleBuffer2), MemoryUtil.memAddress(doubleBuffer3));
    }

    public static void nalGetSourcedvSOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alGetSourcedvSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetSourcedvSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble *") DoubleBuffer doubleBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) doubleBuffer, 1);
        }
        nalGetSourcedvSOFT(i, i2, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("ALvoid")
    public static void alSourcei64SOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT") long j) {
        long j2 = AL.getICD().alSourcei64SOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokeJV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alSource3i64SOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT") long j, @NativeType("ALint64SOFT") long j2, @NativeType("ALint64SOFT") long j3) {
        long j4 = AL.getICD().alSource3i64SOFT;
        if (Checks.CHECKS) {
            Checks.check(j4);
        }
        JNI.invokeJJJV(i, i2, j, j2, j3, j4);
    }

    public static void nalSourcei64vSOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alSourcei64vSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alSourcei64vSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT const *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nalSourcei64vSOFT(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    public static void nalGetSourcei64SOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alGetSourcei64SOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetSourcei64SOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nalGetSourcei64SOFT(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("ALvoid")
    public static long alGetSourcei64SOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            LongBuffer callocLong = stackGet.callocLong(1);
            nalGetSourcei64SOFT(i, i2, MemoryUtil.memAddress(callocLong));
            return callocLong.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetSource3i64SOFT(int i, int i2, long j, long j2, long j3) {
        long j4 = AL.getICD().alGetSource3i64SOFT;
        if (Checks.CHECKS) {
            Checks.check(j4);
        }
        JNI.invokePPPV(i, i2, j, j2, j3, j4);
    }

    @NativeType("ALvoid")
    public static void alGetSource3i64SOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT *") LongBuffer longBuffer, @NativeType("ALint64SOFT *") LongBuffer longBuffer2, @NativeType("ALint64SOFT *") LongBuffer longBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
            Checks.check((Buffer) longBuffer2, 1);
            Checks.check((Buffer) longBuffer3, 1);
        }
        nalGetSource3i64SOFT(i, i2, MemoryUtil.memAddress(longBuffer), MemoryUtil.memAddress(longBuffer2), MemoryUtil.memAddress(longBuffer3));
    }

    public static void nalGetSourcei64vSOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alGetSourcei64vSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetSourcei64vSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT *") LongBuffer longBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) longBuffer, 1);
        }
        nalGetSourcei64vSOFT(i, i2, MemoryUtil.memAddress(longBuffer));
    }

    @NativeType("ALvoid")
    public static void alSourcedvSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble const *") double[] dArr) {
        long j = AL.getICD().alSourcedvSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.invokePV(i, i2, dArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourcedSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble *") double[] dArr) {
        long j = AL.getICD().alGetSourcedSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.invokePV(i, i2, dArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSource3dSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble *") double[] dArr, @NativeType("ALdouble *") double[] dArr2, @NativeType("ALdouble *") double[] dArr3) {
        long j = AL.getICD().alGetSource3dSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
            Checks.check(dArr2, 1);
            Checks.check(dArr3, 1);
        }
        JNI.invokePPPV(i, i2, dArr, dArr2, dArr3, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourcedvSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALdouble *") double[] dArr) {
        long j = AL.getICD().alGetSourcedvSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(dArr, 1);
        }
        JNI.invokePV(i, i2, dArr, j);
    }

    @NativeType("ALvoid")
    public static void alSourcei64vSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT const *") long[] jArr) {
        long j = AL.getICD().alSourcei64vSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.invokePV(i, i2, jArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourcei64SOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT *") long[] jArr) {
        long j = AL.getICD().alGetSourcei64SOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.invokePV(i, i2, jArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetSource3i64SOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT *") long[] jArr, @NativeType("ALint64SOFT *") long[] jArr2, @NativeType("ALint64SOFT *") long[] jArr3) {
        long j = AL.getICD().alGetSource3i64SOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
            Checks.check(jArr2, 1);
            Checks.check(jArr3, 1);
        }
        JNI.invokePPPV(i, i2, jArr, jArr2, jArr3, j);
    }

    @NativeType("ALvoid")
    public static void alGetSourcei64vSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint64SOFT *") long[] jArr) {
        long j = AL.getICD().alGetSourcei64vSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(jArr, 1);
        }
        JNI.invokePV(i, i2, jArr, j);
    }
}
