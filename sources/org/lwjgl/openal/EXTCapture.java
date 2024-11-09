package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/EXTCapture.class */
public class EXTCapture {
    public static final int ALC_CAPTURE_DEVICE_SPECIFIER = 784;
    public static final int ALC_CAPTURE_DEFAULT_DEVICE_SPECIFIER = 785;
    public static final int ALC_CAPTURE_SAMPLES = 786;

    protected EXTCapture() {
        throw new UnsupportedOperationException();
    }

    public static long nalcCaptureOpenDevice(long j, int i, int i2, int i3) {
        return ALC11.nalcCaptureOpenDevice(j, i, i2, i3);
    }

    @NativeType("ALCdevice *")
    public static long alcCaptureOpenDevice(@NativeType("ALCchar const *") ByteBuffer byteBuffer, @NativeType("ALCuint") int i, @NativeType("ALCenum") int i2, @NativeType("ALCsizei") int i3) {
        return ALC11.alcCaptureOpenDevice(byteBuffer, i, i2, i3);
    }

    @NativeType("ALCdevice *")
    public static long alcCaptureOpenDevice(@NativeType("ALCchar const *") CharSequence charSequence, @NativeType("ALCuint") int i, @NativeType("ALCenum") int i2, @NativeType("ALCsizei") int i3) {
        return ALC11.alcCaptureOpenDevice(charSequence, i, i2, i3);
    }

    @NativeType("ALCboolean")
    public static boolean alcCaptureCloseDevice(@NativeType("ALCdevice *") long j) {
        return ALC11.alcCaptureCloseDevice(j);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureStart(@NativeType("ALCdevice *") long j) {
        ALC11.alcCaptureStart(j);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureStop(@NativeType("ALCdevice *") long j) {
        ALC11.alcCaptureStop(j);
    }

    public static void nalcCaptureSamples(long j, long j2, int i) {
        ALC11.nalcCaptureSamples(j, j2, i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") ByteBuffer byteBuffer, @NativeType("ALCsizei") int i) {
        ALC11.alcCaptureSamples(j, byteBuffer, i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") ShortBuffer shortBuffer, @NativeType("ALCsizei") int i) {
        ALC11.alcCaptureSamples(j, shortBuffer, i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") IntBuffer intBuffer, @NativeType("ALCsizei") int i) {
        ALC11.alcCaptureSamples(j, intBuffer, i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") FloatBuffer floatBuffer, @NativeType("ALCsizei") int i) {
        ALC11.alcCaptureSamples(j, floatBuffer, i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") short[] sArr, @NativeType("ALCsizei") int i) {
        ALC11.alcCaptureSamples(j, sArr, i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") int[] iArr, @NativeType("ALCsizei") int i) {
        ALC11.alcCaptureSamples(j, iArr, i);
    }

    @NativeType("ALCvoid")
    public static void alcCaptureSamples(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") float[] fArr, @NativeType("ALCsizei") int i) {
        ALC11.alcCaptureSamples(j, fArr, i);
    }
}
