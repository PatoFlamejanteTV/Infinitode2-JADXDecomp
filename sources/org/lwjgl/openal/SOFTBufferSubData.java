package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTBufferSubData.class */
public class SOFTBufferSubData {
    public static final int AL_BYTE_RW_OFFSETS_SOFT = 4145;
    public static final int AL_SAMPLE_RW_OFFSETS_SOFT = 4146;

    protected SOFTBufferSubData() {
        throw new UnsupportedOperationException();
    }

    public static void nalBufferSubDataSOFT(int i, int i2, long j, int i3, int i4) {
        long j2 = AL.getICD().alBufferSubDataSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, i3, i4, j2);
    }

    public static void alBufferSubDataSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") ByteBuffer byteBuffer, @NativeType("ALsizei") int i3) {
        nalBufferSubDataSOFT(i, i2, MemoryUtil.memAddress(byteBuffer), i3, byteBuffer.remaining());
    }

    public static void alBufferSubDataSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") ShortBuffer shortBuffer, @NativeType("ALsizei") int i3) {
        nalBufferSubDataSOFT(i, i2, MemoryUtil.memAddress(shortBuffer), i3, shortBuffer.remaining() << 1);
    }

    public static void alBufferSubDataSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") IntBuffer intBuffer, @NativeType("ALsizei") int i3) {
        nalBufferSubDataSOFT(i, i2, MemoryUtil.memAddress(intBuffer), i3, intBuffer.remaining() << 2);
    }

    public static void alBufferSubDataSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") FloatBuffer floatBuffer, @NativeType("ALsizei") int i3) {
        nalBufferSubDataSOFT(i, i2, MemoryUtil.memAddress(floatBuffer), i3, floatBuffer.remaining() << 2);
    }

    public static void alBufferSubDataSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") short[] sArr, @NativeType("ALsizei") int i3) {
        long j = AL.getICD().alBufferSubDataSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, sArr, i3, sArr.length << 1, j);
    }

    public static void alBufferSubDataSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") int[] iArr, @NativeType("ALsizei") int i3) {
        long j = AL.getICD().alBufferSubDataSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, iArr, i3, iArr.length << 2, j);
    }

    public static void alBufferSubDataSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid const *") float[] fArr, @NativeType("ALsizei") int i3) {
        long j = AL.getICD().alBufferSubDataSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, fArr, i3, fArr.length << 2, j);
    }
}
