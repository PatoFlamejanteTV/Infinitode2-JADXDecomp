package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTEvents.class */
public class SOFTEvents {
    public static final int AL_EVENT_CALLBACK_FUNCTION_SOFT = 6562;
    public static final int AL_EVENT_CALLBACK_USER_PARAM_SOFT = 6563;
    public static final int AL_EVENT_TYPE_BUFFER_COMPLETED_SOFT = 6564;
    public static final int AL_EVENT_TYPE_SOURCE_STATE_CHANGED_SOFT = 6565;
    public static final int AL_EVENT_TYPE_DISCONNECTED_SOFT = 6566;

    protected SOFTEvents() {
        throw new UnsupportedOperationException();
    }

    public static void nalEventControlSOFT(int i, long j, boolean z) {
        long j2 = AL.getICD().alEventControlSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, j, z, j2);
    }

    public static void alEventControlSOFT(@NativeType("ALenum const *") IntBuffer intBuffer, @NativeType("ALboolean") boolean z) {
        nalEventControlSOFT(intBuffer.remaining(), MemoryUtil.memAddress(intBuffer), z);
    }

    public static void nalEventCallbackSOFT(long j, long j2) {
        long j3 = AL.getICD().alEventCallbackSOFT;
        if (Checks.CHECKS) {
            Checks.check(j3);
        }
        JNI.invokePPV(j, j2, j3);
    }

    public static void alEventCallbackSOFT(@NativeType("ALEVENTPROCSOFT") SOFTEventProcI sOFTEventProcI, @NativeType("ALvoid *") ByteBuffer byteBuffer) {
        nalEventCallbackSOFT(sOFTEventProcI.address(), MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("ALvoid *")
    public static long alGetPointerSOFT(@NativeType("ALenum") int i) {
        long j = AL.getICD().alGetPointerSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokeP(i, j);
    }

    public static void nalGetPointervSOFT(int i, long j) {
        long j2 = AL.getICD().alGetPointervSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
        }
        JNI.invokePV(i, j, j2);
    }

    public static void alGetPointervSOFT(@NativeType("ALenum") int i, @NativeType("ALvoid **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        nalGetPointervSOFT(i, MemoryUtil.memAddress(pointerBuffer));
    }

    public static void alEventControlSOFT(@NativeType("ALenum const *") int[] iArr, @NativeType("ALboolean") boolean z) {
        long j = AL.getICD().alEventControlSOFT;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(iArr.length, iArr, z, j);
    }
}
