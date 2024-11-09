package org.lwjgl.openal;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTCallbackBuffer.class */
public class SOFTCallbackBuffer {
    public static final int AL_BUFFER_CALLBACK_FUNCTION_SOFT = 6560;
    public static final int AL_BUFFER_CALLBACK_USER_PARAM_SOFT = 6561;

    protected SOFTCallbackBuffer() {
        throw new UnsupportedOperationException();
    }

    public static void nalBufferCallbackSOFT(int i, int i2, int i3, long j, long j2) {
        long j3 = AL.getICD().alBufferCallbackSOFT;
        if (Checks.CHECKS) {
            Checks.check(j3);
            Checks.check(j2);
        }
        JNI.invokePPV(i, i2, i3, j, j2, j3);
    }

    @NativeType("ALvoid")
    public static void alBufferCallbackSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALsizei") int i3, @NativeType("ALBUFFERCALLBACKTYPESOFT") SOFTCallbackBufferTypeI sOFTCallbackBufferTypeI, @NativeType("ALvoid *") long j) {
        nalBufferCallbackSOFT(i, i2, i3, sOFTCallbackBufferTypeI.address(), j);
    }

    public static void nalGetBufferPtrSOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alGetBufferPtrSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetBufferPtrSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nalGetBufferPtrSOFT(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }

    @NativeType("ALvoid")
    public static long alGetBufferPtrSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            PointerBuffer callocPointer = stackGet.callocPointer(1);
            nalGetBufferPtrSOFT(i, i2, MemoryUtil.memAddress(callocPointer));
            return callocPointer.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static void nalGetBuffer3PtrSOFT(int i, int i2, long j, long j2, long j3) {
        long j4 = AL.getICD().alGetBuffer3PtrSOFT;
        if (Checks.CHECKS) {
            Checks.check(j4);
        }
        JNI.invokePPPV(i, i2, j, j2, j3, j4);
    }

    @NativeType("ALvoid")
    public static void alGetBuffer3PtrSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid **") PointerBuffer pointerBuffer, @NativeType("ALvoid **") PointerBuffer pointerBuffer2, @NativeType("ALvoid **") PointerBuffer pointerBuffer3) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer2, 1);
            Checks.check((CustomBuffer<?>) pointerBuffer3, 1);
        }
        nalGetBuffer3PtrSOFT(i, i2, MemoryUtil.memAddress(pointerBuffer), MemoryUtil.memAddress(pointerBuffer2), MemoryUtil.memAddress(pointerBuffer3));
    }

    public static void nalGetBufferPtrvSOFT(int i, int i2, long j) {
        long j2 = AL.getICD().alGetBufferPtrvSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, i2, j, j2);
    }

    @NativeType("ALvoid")
    public static void alGetBufferPtrvSOFT(@NativeType("ALuint") int i, @NativeType("ALenum") int i2, @NativeType("ALvoid **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nalGetBufferPtrvSOFT(i, i2, MemoryUtil.memAddress(pointerBuffer));
    }
}
