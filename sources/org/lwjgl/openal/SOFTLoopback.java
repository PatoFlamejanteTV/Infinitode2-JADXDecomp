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

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTLoopback.class */
public class SOFTLoopback {
    public static final int ALC_BYTE_SOFT = 5120;
    public static final int ALC_UNSIGNED_BYTE_SOFT = 5121;
    public static final int ALC_SHORT_SOFT = 5122;
    public static final int ALC_UNSIGNED_SHORT_SOFT = 5123;
    public static final int ALC_INT_SOFT = 5124;
    public static final int ALC_UNSIGNED_INT_SOFT = 5125;
    public static final int ALC_FLOAT_SOFT = 5126;
    public static final int ALC_MONO_SOFT = 5376;
    public static final int ALC_STEREO_SOFT = 5377;
    public static final int ALC_QUAD_SOFT = 5379;
    public static final int ALC_5POINT1_SOFT = 5380;
    public static final int ALC_6POINT1_SOFT = 5381;
    public static final int ALC_7POINT1_SOFT = 5382;
    public static final int ALC_FORMAT_CHANNELS_SOFT = 6544;
    public static final int ALC_FORMAT_TYPE_SOFT = 6545;

    protected SOFTLoopback() {
        throw new UnsupportedOperationException();
    }

    public static long nalcLoopbackOpenDeviceSOFT(long j) {
        long j2 = ALC.getICD().alcLoopbackOpenDeviceSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        return JNI.invokePP(j, j2);
    }

    @NativeType("ALCdevice *")
    public static long alcLoopbackOpenDeviceSOFT(@NativeType("ALCchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1Safe(byteBuffer);
        }
        return nalcLoopbackOpenDeviceSOFT(MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("ALCdevice *")
    public static long alcLoopbackOpenDeviceSOFT(@NativeType("ALCchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return nalcLoopbackOpenDeviceSOFT(charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALCboolean")
    public static boolean alcIsRenderFormatSupportedSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCsizei") int i, @NativeType("ALCenum") int i2, @NativeType("ALCenum") int i3) {
        long j2 = ALC.getICD().alcIsRenderFormatSupportedSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.invokePZ(j, i, i2, i3, j2);
    }

    public static void nalcRenderSamplesSOFT(long j, long j2, int i) {
        long j3 = ALC.getICD().alcRenderSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j);
        }
        JNI.invokePPV(j, j2, i, j3);
    }

    @NativeType("ALCvoid")
    public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") ByteBuffer byteBuffer, @NativeType("ALCsizei") int i) {
        nalcRenderSamplesSOFT(j, MemoryUtil.memAddress(byteBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") ShortBuffer shortBuffer, @NativeType("ALCsizei") int i) {
        nalcRenderSamplesSOFT(j, MemoryUtil.memAddress(shortBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") IntBuffer intBuffer, @NativeType("ALCsizei") int i) {
        nalcRenderSamplesSOFT(j, MemoryUtil.memAddress(intBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") FloatBuffer floatBuffer, @NativeType("ALCsizei") int i) {
        nalcRenderSamplesSOFT(j, MemoryUtil.memAddress(floatBuffer), i);
    }

    @NativeType("ALCvoid")
    public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") short[] sArr, @NativeType("ALCsizei") int i) {
        long j2 = ALC.getICD().alcRenderSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePPV(j, sArr, i, j2);
    }

    @NativeType("ALCvoid")
    public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") int[] iArr, @NativeType("ALCsizei") int i) {
        long j2 = ALC.getICD().alcRenderSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePPV(j, iArr, i, j2);
    }

    @NativeType("ALCvoid")
    public static void alcRenderSamplesSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCvoid *") float[] fArr, @NativeType("ALCsizei") int i) {
        long j2 = ALC.getICD().alcRenderSamplesSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        JNI.invokePPV(j, fArr, i, j2);
    }
}
