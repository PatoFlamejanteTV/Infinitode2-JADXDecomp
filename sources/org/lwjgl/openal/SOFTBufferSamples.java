package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTBufferSamples.class */
public class SOFTBufferSamples {
    public static final int AL_MONO8_SOFT = 4352;
    public static final int AL_MONO16_SOFT = 4353;
    public static final int AL_MONO32F_SOFT = 65552;
    public static final int AL_STEREO8_SOFT = 4354;
    public static final int AL_STEREO16_SOFT = 4355;
    public static final int AL_STEREO32F_SOFT = 65553;
    public static final int AL_QUAD8_SOFT = 4612;
    public static final int AL_QUAD16_SOFT = 4613;
    public static final int AL_QUAD32F_SOFT = 4614;
    public static final int AL_REAR8_SOFT = 4615;
    public static final int AL_REAR16_SOFT = 4616;
    public static final int AL_REAR32F_SOFT = 4617;
    public static final int AL_5POINT1_8_SOFT = 4618;
    public static final int AL_5POINT1_16_SOFT = 4619;
    public static final int AL_5POINT1_32F_SOFT = 4620;
    public static final int AL_6POINT1_8_SOFT = 4621;
    public static final int AL_6POINT1_16_SOFT = 4622;
    public static final int AL_6POINT1_32F_SOFT = 4623;
    public static final int AL_7POINT1_8_SOFT = 4624;
    public static final int AL_7POINT1_16_SOFT = 4625;
    public static final int AL_7POINT1_32F_SOFT = 4626;
    public static final int AL_MONO_SOFT = 5376;
    public static final int AL_STEREO_SOFT = 5377;
    public static final int AL_QUAD_SOFT = 5378;
    public static final int AL_REAR_SOFT = 5379;
    public static final int AL_5POINT1_SOFT = 5380;
    public static final int AL_6POINT1_SOFT = 5381;
    public static final int AL_7POINT1_SOFT = 5382;
    public static final int AL_BYTE_SOFT = 5120;
    public static final int AL_UNSIGNED_BYTE_SOFT = 5121;
    public static final int AL_SHORT_SOFT = 5122;
    public static final int AL_UNSIGNED_SHORT_SOFT = 5123;
    public static final int AL_INT_SOFT = 5124;
    public static final int AL_UNSIGNED_INT_SOFT = 5125;
    public static final int AL_FLOAT_SOFT = 5126;
    public static final int AL_DOUBLE_SOFT = 5127;
    public static final int AL_BYTE3_SOFT = 5128;
    public static final int AL_UNSIGNED_BYTE3_SOFT = 5129;
    public static final int AL_INTERNAL_FORMAT_SOFT = 8200;
    public static final int AL_BYTE_LENGTH_SOFT = 8201;
    public static final int AL_SAMPLE_LENGTH_SOFT = 8202;
    public static final int AL_SEC_LENGTH_SOFT = 8203;
    public static final int AL_BYTE_RW_OFFSETS_SOFT = 4145;
    public static final int AL_SAMPLE_RW_OFFSETS_SOFT = 4146;

    protected SOFTBufferSamples() {
        throw new UnsupportedOperationException();
    }

    public static void nalBufferSamplesSOFT(int i, int i2, int i3, int i4, int i5, int i6, long j) {
        long j2 = AL.getICD().alBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, i3, i4, i5, i6, j, j2);
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") ByteBuffer byteBuffer) {
        nalBufferSamplesSOFT(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(byteBuffer));
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") ShortBuffer shortBuffer) {
        nalBufferSamplesSOFT(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(shortBuffer));
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") IntBuffer intBuffer) {
        nalBufferSamplesSOFT(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(intBuffer));
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") FloatBuffer floatBuffer) {
        nalBufferSamplesSOFT(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(floatBuffer));
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") DoubleBuffer doubleBuffer) {
        nalBufferSamplesSOFT(i, i2, i3, i4, i5, i6, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void nalBufferSubSamplesSOFT(int i, int i2, int i3, int i4, int i5, long j) {
        long j2 = AL.getICD().alBufferSubSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, i3, i4, i5, j, j2);
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") ByteBuffer byteBuffer) {
        nalBufferSubSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(byteBuffer));
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") ShortBuffer shortBuffer) {
        nalBufferSubSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(shortBuffer));
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") IntBuffer intBuffer) {
        nalBufferSubSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(intBuffer));
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") FloatBuffer floatBuffer) {
        nalBufferSubSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") DoubleBuffer doubleBuffer) {
        nalBufferSubSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(doubleBuffer));
    }

    public static void nalGetBufferSamplesSOFT(int i, int i2, int i3, int i4, int i5, long j) {
        long j2 = AL.getICD().alGetBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, i3, i4, i5, j, j2);
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") ByteBuffer byteBuffer) {
        nalGetBufferSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(byteBuffer));
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") ShortBuffer shortBuffer) {
        nalGetBufferSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(shortBuffer));
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") IntBuffer intBuffer) {
        nalGetBufferSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(intBuffer));
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") FloatBuffer floatBuffer) {
        nalGetBufferSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(floatBuffer));
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") DoubleBuffer doubleBuffer) {
        nalGetBufferSamplesSOFT(i, i2, i3, i4, i5, MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("ALboolean")
    public static boolean alIsBufferFormatSupportedSOFT(@NativeType("ALenum") int i) {
        long j = AL.getICD().alIsBufferFormatSupportedSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokeZ(i, j);
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") short[] sArr) {
        long j = AL.getICD().alBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, i6, sArr, j);
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") int[] iArr) {
        long j = AL.getICD().alBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, i6, iArr, j);
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") float[] fArr) {
        long j = AL.getICD().alBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, i6, fArr, j);
    }

    public static void alBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALuint") int i2, @NativeType("ALenum") int i3, @NativeType("ALsizei") int i4, @NativeType("ALenum") int i5, @NativeType("ALenum") int i6, @NativeType("ALvoid const *") double[] dArr) {
        long j = AL.getICD().alBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, i6, dArr, j);
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") short[] sArr) {
        long j = AL.getICD().alBufferSubSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, sArr, j);
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") int[] iArr) {
        long j = AL.getICD().alBufferSubSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, iArr, j);
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") float[] fArr) {
        long j = AL.getICD().alBufferSubSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, fArr, j);
    }

    public static void alBufferSubSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid const *") double[] dArr) {
        long j = AL.getICD().alBufferSubSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, dArr, j);
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") short[] sArr) {
        long j = AL.getICD().alGetBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, sArr, j);
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") int[] iArr) {
        long j = AL.getICD().alGetBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, iArr, j);
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") float[] fArr) {
        long j = AL.getICD().alGetBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, fArr, j);
    }

    public static void alGetBufferSamplesSOFT(@NativeType("ALuint") int i, @NativeType("ALsizei") int i2, @NativeType("ALsizei") int i3, @NativeType("ALenum") int i4, @NativeType("ALenum") int i5, @NativeType("ALvoid *") double[] dArr) {
        long j = AL.getICD().alGetBufferSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, i3, i4, i5, dArr, j);
    }
}
