package org.lwjgl.openal;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/AL11.class */
public class AL11 extends AL10 {
    public static final int AL_SEC_OFFSET = 4132;
    public static final int AL_SAMPLE_OFFSET = 4133;
    public static final int AL_BYTE_OFFSET = 4134;
    public static final int AL_STATIC = 4136;
    public static final int AL_STREAMING = 4137;
    public static final int AL_UNDETERMINED = 4144;
    public static final int AL_ILLEGAL_COMMAND = 40964;
    public static final int AL_SPEED_OF_SOUND = 49155;
    public static final int AL_LINEAR_DISTANCE = 53251;
    public static final int AL_LINEAR_DISTANCE_CLAMPED = 53252;
    public static final int AL_EXPONENT_DISTANCE = 53253;
    public static final int AL_EXPONENT_DISTANCE_CLAMPED = 53254;

    protected AL11() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ALvoid")
    public static void alListener3i(@NativeType("ALenum") int i, @NativeType("ALint") int i2, @NativeType("ALint") int i3, @NativeType("ALint") int i4) {
        long j = AL.getICD().alListener3i;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, i3, i4, j);
    }

    public static void nalGetListeneriv(int i, long j) {
        long j2 = AL.getICD().alGetListeneriv;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetListeneriv(@NativeType("ALenum") int i, @NativeType("ALint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalGetListeneriv(i, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static void alSource3i(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint") int i3, @NativeType("ALint") int i4, @NativeType("ALint") int i5) {
        long j = AL.getICD().alSource3i;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, i3, i4, i5, j);
    }

    public static void nalListeneriv(int i, long j) {
        long j2 = AL.getICD().alListeneriv;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, j, j2);
    }

    @NativeType("ALvoid")
    public static void alListeneriv(@NativeType("ALenum") int i, @NativeType("ALint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalListeneriv(i, MemoryUtil.memAddress(intBuffer));
    }

    public static void nalSourceiv(int i, int i2, long j) {
        long j2 = AL.getICD().alSourceiv;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alSourceiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalSourceiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALvoid")
    public static void alBufferf(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat") float f) {
        long j = AL.getICD().alBufferf;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, f, j);
    }

    @NativeType("ALvoid")
    public static void alBuffer3f(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat") float f, @NativeType("ALfloat") float f2, @NativeType("ALfloat") float f3) {
        long j = AL.getICD().alBuffer3f;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, f, f2, f3, j);
    }

    public static void nalBufferfv(int i, int i2, long j) {
        long j2 = AL.getICD().alBufferfv;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alBufferfv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat const *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalBufferfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("ALvoid")
    public static void alBufferi(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint") int i3) {
        long j = AL.getICD().alBufferi;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, i3, j);
    }

    @NativeType("ALvoid")
    public static void alBuffer3i(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint") int i3, @NativeType("ALint") int i4, @NativeType("ALint") int i5) {
        long j = AL.getICD().alBuffer3i;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(i, i2, i3, i4, i5, j);
    }

    public static void nalBufferiv(int i, int i2, long j) {
        long j2 = AL.getICD().alBufferiv;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alBufferiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalBufferiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void nalGetBufferiv(int i, int i2, long j) {
        long j2 = AL.getICD().alGetBufferiv;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetBufferiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) intBuffer, 1);
        }
        nalGetBufferiv(i, i2, MemoryUtil.memAddress(intBuffer));
    }

    public static void nalGetBufferfv(int i, int i2, long j) {
        long j2 = AL.getICD().alGetBufferfv;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetBufferfv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") FloatBuffer floatBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) floatBuffer, 1);
        }
        nalGetBufferfv(i, i2, MemoryUtil.memAddress(floatBuffer));
    }

    @NativeType("ALvoid")
    public static void alSpeedOfSound(@NativeType("ALfloat") float f) {
        long j = AL.getICD().alSpeedOfSound;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokeV(f, j);
    }

    @NativeType("ALvoid")
    public static void alGetListeneriv(@NativeType("ALenum") int i, @NativeType("ALint *") int[] iArr) {
        long j = AL.getICD().alGetListeneriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alListeneriv(@NativeType("ALenum") int i, @NativeType("ALint const *") int[] iArr) {
        long j = AL.getICD().alListeneriv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alSourceiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint const *") int[] iArr) {
        long j = AL.getICD().alSourceiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, i2, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alBufferfv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat const *") float[] fArr) {
        long j = AL.getICD().alBufferfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, i2, fArr, j);
    }

    @NativeType("ALvoid")
    public static void alBufferiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint const *") int[] iArr) {
        long j = AL.getICD().alBufferiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, i2, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetBufferiv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALint *") int[] iArr) {
        long j = AL.getICD().alGetBufferiv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(iArr, 1);
        }
        JNI.invokePV(i, i2, iArr, j);
    }

    @NativeType("ALvoid")
    public static void alGetBufferfv(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALfloat *") float[] fArr) {
        long j = AL.getICD().alGetBufferfv;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.check(fArr, 1);
        }
        JNI.invokePV(i, i2, fArr, j);
    }
}
