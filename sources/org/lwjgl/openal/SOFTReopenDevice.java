package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/SOFTReopenDevice.class */
public class SOFTReopenDevice {
    protected SOFTReopenDevice() {
        throw new UnsupportedOperationException();
    }

    public static boolean nalcReopenDeviceSOFT(long j, long j2, long j3) {
        long j4 = ALC.getICD().alcReopenDeviceSOFT;
        if (Checks.CHECKS) {
            Checks.check(j4);
            Checks.check(j);
        }
        return JNI.invokePPPZ(j, j2, j3, j4);
    }

    @NativeType("ALCboolean")
    public static boolean alcReopenDeviceSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCchar const *") ByteBuffer byteBuffer, @NativeType("ALCint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1Safe(byteBuffer);
            Checks.checkNTSafe(intBuffer);
        }
        return nalcReopenDeviceSOFT(j, MemoryUtil.memAddressSafe(byteBuffer), MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("ALCboolean")
    public static boolean alcReopenDeviceSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCchar const *") CharSequence charSequence, @NativeType("ALCint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return nalcReopenDeviceSOFT(j, charSequence == null ? 0L : stackGet.getPointerAddress(), MemoryUtil.memAddressSafe(intBuffer));
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALCboolean")
    public static boolean alcReopenDeviceSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCchar const *") ByteBuffer byteBuffer, @NativeType("ALCint const *") int[] iArr) {
        long j2 = ALC.getICD().alcReopenDeviceSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNT1Safe(byteBuffer);
            Checks.checkNTSafe(iArr);
        }
        return JNI.invokePPPZ(j, MemoryUtil.memAddressSafe(byteBuffer), iArr, j2);
    }

    @NativeType("ALCboolean")
    public static boolean alcReopenDeviceSOFT(@NativeType("ALCdevice *") long j, @NativeType("ALCchar const *") CharSequence charSequence, @NativeType("ALCint const *") int[] iArr) {
        long j2 = ALC.getICD().alcReopenDeviceSOFT;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return JNI.invokePPPZ(j, charSequence == null ? 0L : stackGet.getPointerAddress(), iArr, j2);
        } finally {
            stackGet.setPointer(pointer);
        }
    }
}
