package org.lwjgl.system;

import org.lwjgl.PointerBuffer;
import org.lwjgl.system.jni.JNINativeInterface;

/* loaded from: infinitode-2.jar:org/lwjgl/system/ThreadLocalUtil.class */
public final class ThreadLocalUtil {
    private static final int JNI_NATIVE_INTERFACE_FUNCTION_COUNT;
    private static final long JNI_NATIVE_INTERFACE = MemoryUtil.memGetAddress(getThreadJNIEnv());
    private static final long FUNCTION_MISSING_ABORT = getFunctionMissingAbort();
    private static long FUNCTION_MISSING_ABORT_TABLE = 0;
    private static final int CAPABILITIES_OFFSET = 3 * Pointer.POINTER_SIZE;

    private static native long getThreadJNIEnv();

    private static native long getFunctionMissingAbort();

    private static native long nsetupEnvData(int i);

    static {
        int i;
        int i2;
        int GetVersion = JNINativeInterface.GetVersion();
        switch (GetVersion) {
            case 65537:
                i = 12;
                break;
            default:
                i = 4;
                break;
        }
        switch (GetVersion) {
            case 65537:
                i2 = 208;
                break;
            case 65538:
                i2 = 225;
                break;
            case 65540:
                i2 = 228;
                break;
            case 65542:
            case 65544:
                i2 = 229;
                break;
            case 589824:
            case JNINativeInterface.JNI_VERSION_10 /* 655360 */:
                i2 = 230;
                break;
            case JNINativeInterface.JNI_VERSION_19 /* 1245184 */:
            case JNINativeInterface.JNI_VERSION_20 /* 1310720 */:
                i2 = 231;
                break;
            case JNINativeInterface.JNI_VERSION_21 /* 1376256 */:
                i2 = 232;
                break;
            default:
                i2 = 232;
                APIUtil.DEBUG_STREAM.println("[LWJGL] [ThreadLocalUtil] Unsupported JNI version detected, this may result in a crash. Please inform LWJGL developers.");
                break;
        }
        JNI_NATIVE_INTERFACE_FUNCTION_COUNT = i + Configuration.JNI_NATIVE_INTERFACE_FUNCTION_COUNT.get(Integer.valueOf(i2)).intValue();
    }

    private ThreadLocalUtil() {
    }

    public static long setupEnvData() {
        return nsetupEnvData(JNI_NATIVE_INTERFACE_FUNCTION_COUNT);
    }

    public static void setCapabilities(long j) {
        long threadJNIEnv = getThreadJNIEnv();
        long memGetAddress = MemoryUtil.memGetAddress(threadJNIEnv);
        if (j == 0) {
            if (memGetAddress != JNI_NATIVE_INTERFACE) {
                MemoryUtil.memPutAddress(memGetAddress + CAPABILITIES_OFFSET, FUNCTION_MISSING_ABORT_TABLE);
            }
        } else {
            if (memGetAddress == JNI_NATIVE_INTERFACE) {
                setupEnvData();
                memGetAddress = MemoryUtil.memGetAddress(threadJNIEnv);
            }
            MemoryUtil.memPutAddress(memGetAddress + CAPABILITIES_OFFSET, j);
        }
    }

    public static void setFunctionMissingAddresses(int i) {
        long memGetAddress = MemoryUtil.memGetAddress(JNI_NATIVE_INTERFACE);
        long j = JNI_NATIVE_INTERFACE + CAPABILITIES_OFFSET;
        long memGetAddress2 = MemoryUtil.memGetAddress(j);
        if (i == 0) {
            if (memGetAddress2 != memGetAddress) {
                FUNCTION_MISSING_ABORT_TABLE = 0L;
                MemoryUtil.getAllocator().free(memGetAddress2);
                MemoryUtil.memPutAddress(j, 0L);
                return;
            }
            return;
        }
        if (memGetAddress2 != memGetAddress) {
            throw new IllegalStateException("setFunctionMissingAddresses has been called already");
        }
        if (memGetAddress2 != 0) {
            return;
        }
        FUNCTION_MISSING_ABORT_TABLE = MemoryUtil.getAllocator().malloc(Integer.toUnsignedLong(i) * Pointer.POINTER_SIZE);
        for (int i2 = 0; i2 < i; i2++) {
            MemoryUtil.memPutAddress(FUNCTION_MISSING_ABORT_TABLE + (Integer.toUnsignedLong(i2) * Pointer.POINTER_SIZE), FUNCTION_MISSING_ABORT);
        }
        MemoryUtil.memPutAddress(j, FUNCTION_MISSING_ABORT_TABLE);
    }

    public static PointerBuffer setupAddressBuffer(PointerBuffer pointerBuffer) {
        for (int position = pointerBuffer.position(); position < pointerBuffer.limit(); position++) {
            if (pointerBuffer.get(position) == 0) {
                pointerBuffer.put(position, FUNCTION_MISSING_ABORT);
            }
        }
        return pointerBuffer;
    }

    public static boolean areCapabilitiesDifferent(PointerBuffer pointerBuffer, PointerBuffer pointerBuffer2) {
        for (int i = 0; i < pointerBuffer.remaining(); i++) {
            if (pointerBuffer.get(i) != pointerBuffer2.get(i) && pointerBuffer2.get(i) != 0) {
                return true;
            }
        }
        return false;
    }
}
