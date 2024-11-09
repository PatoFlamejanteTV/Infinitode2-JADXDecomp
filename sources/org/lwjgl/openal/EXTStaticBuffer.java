package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/EXTStaticBuffer.class */
public class EXTStaticBuffer {
    protected EXTStaticBuffer() {
        throw new UnsupportedOperationException();
    }

    public static void nalBufferDataStatic(int i, int i2, long j, int i3, int i4) {
        long j2 = AL.getICD().alBufferDataStatic;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, i3, i4, j2);
    }

    @NativeType("ALvoid")
    public static void alBufferDataStatic(@NativeType("ALint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid *") ByteBuffer byteBuffer, @NativeType("ALsizei") int i3) {
        nalBufferDataStatic(i, i2, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining(), i3);
    }

    @NativeType("ALvoid")
    public static void alBufferDataStatic(@NativeType("ALint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid *") ShortBuffer shortBuffer, @NativeType("ALsizei") int i3) {
        nalBufferDataStatic(i, i2, MemoryUtil.memAddress(shortBuffer), shortBuffer.remaining() << 1, i3);
    }

    @NativeType("ALvoid")
    public static void alBufferDataStatic(@NativeType("ALint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid *") IntBuffer intBuffer, @NativeType("ALsizei") int i3) {
        nalBufferDataStatic(i, i2, MemoryUtil.memAddress(intBuffer), intBuffer.remaining() << 2, i3);
    }

    @NativeType("ALvoid")
    public static void alBufferDataStatic(@NativeType("ALint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid *") FloatBuffer floatBuffer, @NativeType("ALsizei") int i3) {
        nalBufferDataStatic(i, i2, MemoryUtil.memAddress(floatBuffer), floatBuffer.remaining() << 2, i3);
    }

    @NativeType("ALvoid")
    public static void alBufferDataStatic(@NativeType("ALint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid *") short[] sArr, @NativeType("ALsizei") int i3) {
        long j = AL.getICD().alBufferDataStatic;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, sArr, sArr.length << 1, i3, j);
    }

    @NativeType("ALvoid")
    public static void alBufferDataStatic(@NativeType("ALint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid *") int[] iArr, @NativeType("ALsizei") int i3) {
        long j = AL.getICD().alBufferDataStatic;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, iArr, iArr.length << 2, i3, j);
    }

    @NativeType("ALvoid")
    public static void alBufferDataStatic(@NativeType("ALint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid *") float[] fArr, @NativeType("ALsizei") int i3) {
        long j = AL.getICD().alBufferDataStatic;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(i, i2, fArr, fArr.length << 2, i3, j);
    }
}
