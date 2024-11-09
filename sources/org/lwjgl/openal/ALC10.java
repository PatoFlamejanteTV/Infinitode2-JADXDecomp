package org.lwjgl.openal;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/openal/ALC10.class */
public class ALC10 {
    public static final int ALC_INVALID = -1;
    public static final int ALC_FALSE = 0;
    public static final int ALC_TRUE = 1;
    public static final int ALC_FREQUENCY = 4103;
    public static final int ALC_REFRESH = 4104;
    public static final int ALC_SYNC = 4105;
    public static final int ALC_NO_ERROR = 0;
    public static final int ALC_INVALID_DEVICE = 40961;
    public static final int ALC_INVALID_CONTEXT = 40962;
    public static final int ALC_INVALID_ENUM = 40963;
    public static final int ALC_INVALID_VALUE = 40964;
    public static final int ALC_OUT_OF_MEMORY = 40965;
    public static final int ALC_DEFAULT_DEVICE_SPECIFIER = 4100;
    public static final int ALC_DEVICE_SPECIFIER = 4101;
    public static final int ALC_EXTENSIONS = 4102;
    public static final int ALC_MAJOR_VERSION = 4096;
    public static final int ALC_MINOR_VERSION = 4097;
    public static final int ALC_ATTRIBUTES_SIZE = 4098;
    public static final int ALC_ALL_ATTRIBUTES = 4099;

    /* JADX INFO: Access modifiers changed from: protected */
    public ALC10() {
        throw new UnsupportedOperationException();
    }

    public static long nalcOpenDevice(long j) {
        return JNI.invokePP(j, ALC.getICD().alcOpenDevice);
    }

    @NativeType("ALCdevice *")
    public static long alcOpenDevice(@NativeType("ALCchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1Safe(byteBuffer);
        }
        return nalcOpenDevice(MemoryUtil.memAddressSafe(byteBuffer));
    }

    @NativeType("ALCdevice *")
    public static long alcOpenDevice(@NativeType("ALCchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8Safe(charSequence, true);
            return nalcOpenDevice(charSequence == null ? 0L : stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALCboolean")
    public static boolean alcCloseDevice(@NativeType("ALCdevice const *") long j) {
        long j2 = ALC.getICD().alcCloseDevice;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePZ(j, j2);
    }

    public static long nalcCreateContext(long j, long j2) {
        long j3 = ALC.getICD().alcCreateContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePPP(j, j2, j3);
    }

    @NativeType("ALCcontext *")
    public static long alcCreateContext(@NativeType("ALCdevice const *") long j, @NativeType("ALCint const *") IntBuffer intBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNTSafe(intBuffer);
        }
        return nalcCreateContext(j, MemoryUtil.memAddressSafe(intBuffer));
    }

    @NativeType("ALCboolean")
    public static boolean alcMakeContextCurrent(@NativeType("ALCcontext *") long j) {
        return JNI.invokePZ(j, ALC.getICD().alcMakeContextCurrent);
    }

    @NativeType("ALCvoid")
    public static void alcProcessContext(@NativeType("ALCcontext *") long j) {
        long j2 = ALC.getICD().alcProcessContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    @NativeType("ALCvoid")
    public static void alcSuspendContext(@NativeType("ALCcontext *") long j) {
        long j2 = ALC.getICD().alcSuspendContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    @NativeType("ALCvoid")
    public static void alcDestroyContext(@NativeType("ALCcontext *") long j) {
        long j2 = ALC.getICD().alcDestroyContext;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        JNI.invokePV(j, j2);
    }

    @NativeType("ALCcontext *")
    public static long alcGetCurrentContext() {
        return JNI.invokeP(ALC.getICD().alcGetCurrentContext);
    }

    @NativeType("ALCdevice *")
    public static long alcGetContextsDevice(@NativeType("ALCcontext *") long j) {
        long j2 = ALC.getICD().alcGetContextsDevice;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.invokePP(j, j2);
    }

    public static boolean nalcIsExtensionPresent(long j, long j2) {
        return JNI.invokePPZ(j, j2, ALC.getICD().alcIsExtensionPresent);
    }

    @NativeType("ALCboolean")
    public static boolean alcIsExtensionPresent(@NativeType("ALCdevice const *") long j, @NativeType("ALCchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nalcIsExtensionPresent(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("ALCboolean")
    public static boolean alcIsExtensionPresent(@NativeType("ALCdevice const *") long j, @NativeType("ALCchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nalcIsExtensionPresent(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static long nalcGetProcAddress(long j, long j2) {
        return JNI.invokePPP(j, j2, ALC.getICD().alcGetProcAddress);
    }

    @NativeType("void *")
    public static long alcGetProcAddress(@NativeType("ALCdevice const *") long j, @NativeType("ALchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nalcGetProcAddress(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("void *")
    public static long alcGetProcAddress(@NativeType("ALCdevice const *") long j, @NativeType("ALchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nalcGetProcAddress(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int nalcGetEnumValue(long j, long j2) {
        return JNI.invokePPI(j, j2, ALC.getICD().alcGetEnumValue);
    }

    @NativeType("ALCenum")
    public static int alcGetEnumValue(@NativeType("ALCdevice const *") long j, @NativeType("ALCchar const *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
        }
        return nalcGetEnumValue(j, MemoryUtil.memAddress(byteBuffer));
    }

    @NativeType("ALCenum")
    public static int alcGetEnumValue(@NativeType("ALCdevice const *") long j, @NativeType("ALCchar const *") CharSequence charSequence) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nASCII(charSequence, true);
            return nalcGetEnumValue(j, stackGet.getPointerAddress());
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALCenum")
    public static int alcGetError(@NativeType("ALCdevice *") long j) {
        return JNI.invokePI(j, ALC.getICD().alcGetError);
    }

    public static long nalcGetString(long j, int i) {
        return JNI.invokePP(j, i, ALC.getICD().alcGetString);
    }

    @NativeType("ALCchar const *")
    public static String alcGetString(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i) {
        return MemoryUtil.memUTF8Safe(nalcGetString(j, i));
    }

    public static void nalcGetIntegerv(long j, int i, int i2, long j2) {
        JNI.invokePPV(j, i, i2, j2, ALC.getICD().alcGetIntegerv);
    }

    @NativeType("ALCvoid")
    public static void alcGetIntegerv(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i, @NativeType("ALCint *") IntBuffer intBuffer) {
        nalcGetIntegerv(j, i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    @NativeType("ALCvoid")
    public static int alcGetInteger(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i) {
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            IntBuffer callocInt = stackGet.callocInt(1);
            nalcGetIntegerv(j, i, 1, MemoryUtil.memAddress(callocInt));
            return callocInt.get(0);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    @NativeType("ALCcontext *")
    public static long alcCreateContext(@NativeType("ALCdevice const *") long j, @NativeType("ALCint const *") int[] iArr) {
        long j2 = ALC.getICD().alcCreateContext;
        if (Checks.CHECKS) {
            Checks.check(j);
            Checks.checkNTSafe(iArr);
        }
        return JNI.invokePPP(j, iArr, j2);
    }

    @NativeType("ALCvoid")
    public static void alcGetIntegerv(@NativeType("ALCdevice *") long j, @NativeType("ALCenum") int i, @NativeType("ALCint *") int[] iArr) {
        JNI.invokePPV(j, i, iArr.length, iArr, ALC.getICD().alcGetIntegerv);
    }
}
