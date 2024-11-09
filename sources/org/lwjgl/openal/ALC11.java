package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/ALC11.class */
public class ALC11 extends ALC10 {
    public static final int ALC_MONO_SOURCES = 4112;
    public static final int ALC_STEREO_SOURCES = 4113;
    public static final int ALC_DEFAULT_ALL_DEVICES_SPECIFIER = 4114;
    public static final int ALC_ALL_DEVICES_SPECIFIER = 4115;
    public static final int ALC_CAPTURE_DEVICE_SPECIFIER = 784;
    public static final int ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER = 785;
    public static final int ALC_CAPTURE_SAMPLES = 786;

    protected ALC11() {
        throw new UnsupportedOperationException();
    }

    public static long nalcCaptureOpenDevice(long j, int i, int i2, int i3) {
        long j2 = ALC.getICD().alcCaptureOpenDevice;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePP(j, i, i2, i3, j2);
    }

    @NativeType("ALCdevice *")
    public static long alcCaptureOpenDevice(@NativeType("ALCchar const *") ByteBuffer byteBuffer, @NativeType("ALCuint") int i, @NativeType("ALCenum") int i2, @NativeType("ALCsizei") int i3) {
        if (Checks.CHECKS) {
            Checks.checkNT1Safe(byteBuffer);
        }
        return nalcCaptureOpenDevice(MemoryUtil.memAddressSafe(byteBuffer), i, i2, i3);
    }

    @NativeType("ALCdevice *")
    public static long alcCaptureOpenDevice(@NativeType("ALCchar const *") CharSequence charSequence, @NativeType("ALCuint") int i, @NativeType("ALCenum") int i2, @NativeType("ALCsizei") int i3) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return nalcCaptureOpenDevice(charSequence == null ? 0L : stackGet.getPointerAddress(), i, i2, i3);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALCboolean")
    public static boolean alcCaptureCloseDevice(@NativeType("ALCdevice *") long j) {
        long j2 = ALC.getICD().alcCaptureCloseDevice;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.invokePZ(j, j2);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureStart(@NativeType("ALCdevice *") long j) {
        long j2 = ALC.getICD().alcCaptureStart;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureStop(@NativeType("ALCdevice *") long j) {
        long j2 = ALC.getICD().alcCaptureStop;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    public static void nalcCaptureSamples(long j, long j2, int i) {
        long j3 = ALC.getICD().alcCaptureSamples;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, i, j3);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") ByteBuffer byteBuffer, @NativeType("ALCsizei") int i) {
        nalcCaptureSamples(j, MemoryUtil.memAddress(byteBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") ShortBuffer shortBuffer, @NativeType("ALCsizei") int i) {
        nalcCaptureSamples(j, MemoryUtil.memAddress(shortBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") IntBuffer intBuffer, @NativeType("ALCsizei") int i) {
        nalcCaptureSamples(j, MemoryUtil.memAddress(intBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") FloatBuffer floatBuffer, @NativeType("ALCsizei") int i) {
        nalcCaptureSamples(j, MemoryUtil.memAddress(floatBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") short[] sArr, @NativeType("ALCsizei") int i) {
        long j2 = ALC.getICD().alcCaptureSamples;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePPV(j, sArr, i, j2);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") int[] iArr, @NativeType("ALCsizei") int i) {
        long j2 = ALC.getICD().alcCaptureSamples;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePPV(j, iArr, i, j2);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") float[] fArr, @NativeType("ALCsizei") int i) {
        long j2 = ALC.getICD().alcCaptureSamples;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePPV(j, fArr, i, j2);
    }
}
