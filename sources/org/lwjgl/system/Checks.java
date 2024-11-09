package org.lwjgl.system;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.lwjgl.PointerBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/Checks.class */
public final class Checks {
    public static final boolean CHECKS;
    public static final boolean DEBUG;
    public static final boolean DEBUG_FUNCTIONS;

    static {
        CHECKS = !Configuration.DISABLE_CHECKS.get(Boolean.FALSE).booleanValue();
        DEBUG = Configuration.DEBUG.get(Boolean.FALSE).booleanValue();
        boolean booleanValue = Configuration.DEBUG_FUNCTIONS.get(Boolean.FALSE).booleanValue();
        DEBUG_FUNCTIONS = booleanValue;
        if (booleanValue && !DEBUG) {
            APIUtil.DEBUG_STREAM.println("[LWJGL] The DEBUG_FUNCTIONS option requires DEBUG to produce output.");
        }
    }

    private Checks() {
    }

    public static int lengthSafe(short[] sArr) {
        if (sArr == null) {
            return 0;
        }
        return sArr.length;
    }

    public static int lengthSafe(int[] iArr) {
        if (iArr == null) {
            return 0;
        }
        return iArr.length;
    }

    public static int lengthSafe(long[] jArr) {
        if (jArr == null) {
            return 0;
        }
        return jArr.length;
    }

    public static int lengthSafe(float[] fArr) {
        if (fArr == null) {
            return 0;
        }
        return fArr.length;
    }

    public static int lengthSafe(double[] dArr) {
        if (dArr == null) {
            return 0;
        }
        return dArr.length;
    }

    public static int remainingSafe(Buffer buffer) {
        if (buffer == null) {
            return 0;
        }
        return buffer.remaining();
    }

    public static int remainingSafe(CustomBuffer<?> customBuffer) {
        if (customBuffer == null) {
            return 0;
        }
        return customBuffer.remaining();
    }

    public static boolean checkFunctions(long... jArr) {
        for (long j : jArr) {
            if (j == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkFunctions(FunctionProvider functionProvider, PointerBuffer pointerBuffer, int[] iArr, String... strArr) {
        boolean z = true;
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            if (i2 >= 0 && pointerBuffer.get(i2) == 0) {
                long functionAddress = functionProvider.getFunctionAddress(strArr[i]);
                if (functionAddress == 0) {
                    z = false;
                } else {
                    pointerBuffer.put(i2, functionAddress);
                }
            }
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [long, java.lang.CharSequence] */
    public static boolean checkFunctions(FunctionProviderLocal functionProviderLocal, long j, PointerBuffer pointerBuffer, int[] iArr, String... strArr) {
        boolean z = true;
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            if (i2 >= 0 && pointerBuffer.get(i2) == 0) {
                ?? r2 = strArr[i];
                long functionAddress = functionProviderLocal.getFunctionAddress(j, (CharSequence) r2);
                if (r2 != 0) {
                    pointerBuffer.put(i2, functionAddress);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    public static boolean checkFunctions(FunctionProvider functionProvider, long[] jArr, int[] iArr, String... strArr) {
        boolean z = true;
        for (int i = 0; i < iArr.length; i++) {
            int i2 = iArr[i];
            if (i2 >= 0 && jArr[i2] == 0) {
                long functionAddress = functionProvider.getFunctionAddress(strArr[i]);
                if (functionAddress == 0) {
                    z = false;
                } else {
                    jArr[i2] = functionAddress;
                }
            }
        }
        return z;
    }

    public static boolean reportMissing(String str, String str2) {
        APIUtil.apiLog("[" + str + "] " + str2 + " was reported as available but an entry point is missing.");
        return false;
    }

    public static long check(long j) {
        if (j == 0) {
            throw new NullPointerException();
        }
        return j;
    }

    private static void assertNT(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Missing termination");
        }
    }

    public static void checkNT(int[] iArr) {
        checkBuffer(iArr.length, 1);
        assertNT(iArr[iArr.length - 1] == 0);
    }

    public static void checkNT(int[] iArr, int i) {
        checkBuffer(iArr.length, 1);
        assertNT(iArr[iArr.length - 1] == i);
    }

    public static void checkNT(long[] jArr) {
        checkBuffer(jArr.length, 1);
        assertNT(jArr[jArr.length - 1] == 0);
    }

    public static void checkNT(float[] fArr) {
        checkBuffer(fArr.length, 1);
        assertNT(fArr[fArr.length - 1] == 0.0f);
    }

    public static void checkNT1(ByteBuffer byteBuffer) {
        checkBuffer(byteBuffer.remaining(), 1);
        assertNT(byteBuffer.get(byteBuffer.limit() - 1) == 0);
    }

    public static void checkNT2(ByteBuffer byteBuffer) {
        checkBuffer(byteBuffer.remaining(), 2);
        assertNT(byteBuffer.get(byteBuffer.limit() - 2) == 0);
    }

    public static void checkNT(IntBuffer intBuffer) {
        checkBuffer(intBuffer.remaining(), 1);
        assertNT(intBuffer.get(intBuffer.limit() - 1) == 0);
    }

    public static void checkNT(IntBuffer intBuffer, int i) {
        checkBuffer(intBuffer.remaining(), 1);
        assertNT(intBuffer.get(intBuffer.limit() - 1) == i);
    }

    public static void checkNT(LongBuffer longBuffer) {
        checkBuffer(longBuffer.remaining(), 1);
        assertNT(longBuffer.get(longBuffer.limit() - 1) == 0);
    }

    public static void checkNT(FloatBuffer floatBuffer) {
        checkBuffer(floatBuffer.remaining(), 1);
        assertNT(floatBuffer.get(floatBuffer.limit() - 1) == 0.0f);
    }

    public static void checkNT(PointerBuffer pointerBuffer) {
        checkBuffer(pointerBuffer.remaining(), 1);
        assertNT(pointerBuffer.get(pointerBuffer.limit() - 1) == 0);
    }

    public static void checkNT(PointerBuffer pointerBuffer, long j) {
        checkBuffer(pointerBuffer.remaining(), 1);
        assertNT(pointerBuffer.get(pointerBuffer.limit() - 1) == j);
    }

    public static void checkNTSafe(int[] iArr) {
        if (iArr != null) {
            checkBuffer(iArr.length, 1);
            assertNT(iArr[iArr.length - 1] == 0);
        }
    }

    public static void checkNTSafe(int[] iArr, int i) {
        if (iArr != null) {
            checkBuffer(iArr.length, 1);
            assertNT(iArr[iArr.length - 1] == i);
        }
    }

    public static void checkNTSafe(long[] jArr) {
        if (jArr != null) {
            checkBuffer(jArr.length, 1);
            assertNT(jArr[jArr.length - 1] == 0);
        }
    }

    public static void checkNTSafe(float[] fArr) {
        if (fArr != null) {
            checkBuffer(fArr.length, 1);
            assertNT(fArr[fArr.length - 1] == 0.0f);
        }
    }

    public static void checkNT1Safe(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            checkBuffer(byteBuffer.remaining(), 1);
            assertNT(byteBuffer.get(byteBuffer.limit() - 1) == 0);
        }
    }

    public static void checkNT2Safe(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            checkBuffer(byteBuffer.remaining(), 2);
            assertNT(byteBuffer.get(byteBuffer.limit() - 2) == 0);
        }
    }

    public static void checkNTSafe(IntBuffer intBuffer) {
        if (intBuffer != null) {
            checkBuffer(intBuffer.remaining(), 1);
            assertNT(intBuffer.get(intBuffer.limit() - 1) == 0);
        }
    }

    public static void checkNTSafe(IntBuffer intBuffer, int i) {
        if (intBuffer != null) {
            checkBuffer(intBuffer.remaining(), 1);
            assertNT(intBuffer.get(intBuffer.limit() - 1) == i);
        }
    }

    public static void checkNTSafe(LongBuffer longBuffer) {
        if (longBuffer != null) {
            checkBuffer(longBuffer.remaining(), 1);
            assertNT(longBuffer.get(longBuffer.limit() - 1) == 0);
        }
    }

    public static void checkNTSafe(FloatBuffer floatBuffer) {
        if (floatBuffer != null) {
            checkBuffer(floatBuffer.remaining(), 1);
            assertNT(floatBuffer.get(floatBuffer.limit() - 1) == 0.0f);
        }
    }

    public static void checkNTSafe(PointerBuffer pointerBuffer) {
        if (pointerBuffer != null) {
            checkBuffer(pointerBuffer.remaining(), 1);
            assertNT(pointerBuffer.get(pointerBuffer.limit() - 1) == 0);
        }
    }

    public static void checkNTSafe(PointerBuffer pointerBuffer, long j) {
        if (pointerBuffer != null) {
            checkBuffer(pointerBuffer.remaining(), 1);
            assertNT(pointerBuffer.get(pointerBuffer.limit() - 1) == j);
        }
    }

    private static void checkBuffer(int i, int i2) {
        if (i < i2) {
            throwIAE(i, i2);
        }
    }

    public static void check(byte[] bArr, int i) {
        checkBuffer(bArr.length, i);
    }

    public static void check(short[] sArr, int i) {
        checkBuffer(sArr.length, i);
    }

    public static void check(int[] iArr, int i) {
        checkBuffer(iArr.length, i);
    }

    public static void check(long[] jArr, int i) {
        checkBuffer(jArr.length, i);
    }

    public static void check(float[] fArr, int i) {
        checkBuffer(fArr.length, i);
    }

    public static void check(double[] dArr, int i) {
        checkBuffer(dArr.length, i);
    }

    public static void check(CharSequence charSequence, int i) {
        checkBuffer(charSequence.length(), i);
    }

    public static void check(Buffer buffer, int i) {
        checkBuffer(buffer.remaining(), i);
    }

    public static void check(Buffer buffer, long j) {
        checkBuffer(buffer.remaining(), (int) j);
    }

    public static void check(CustomBuffer<?> customBuffer, int i) {
        checkBuffer(customBuffer.remaining(), i);
    }

    public static void check(CustomBuffer<?> customBuffer, long j) {
        checkBuffer(customBuffer.remaining(), (int) j);
    }

    public static void checkSafe(short[] sArr, int i) {
        if (sArr != null) {
            checkBuffer(sArr.length, i);
        }
    }

    public static void checkSafe(int[] iArr, int i) {
        if (iArr != null) {
            checkBuffer(iArr.length, i);
        }
    }

    public static void checkSafe(long[] jArr, int i) {
        if (jArr != null) {
            checkBuffer(jArr.length, i);
        }
    }

    public static void checkSafe(float[] fArr, int i) {
        if (fArr != null) {
            checkBuffer(fArr.length, i);
        }
    }

    public static void checkSafe(double[] dArr, int i) {
        if (dArr != null) {
            checkBuffer(dArr.length, i);
        }
    }

    public static void checkSafe(Buffer buffer, int i) {
        if (buffer != null) {
            checkBuffer(buffer.remaining(), i);
        }
    }

    public static void checkSafe(Buffer buffer, long j) {
        if (buffer != null) {
            checkBuffer(buffer.remaining(), (int) j);
        }
    }

    public static void checkSafe(CustomBuffer<?> customBuffer, int i) {
        if (customBuffer != null) {
            checkBuffer(customBuffer.remaining(), i);
        }
    }

    public static void checkSafe(CustomBuffer<?> customBuffer, long j) {
        if (customBuffer != null) {
            checkBuffer(customBuffer.remaining(), (int) j);
        }
    }

    public static void check(Object[] objArr, int i) {
        checkBuffer(objArr.length, i);
    }

    private static void checkBufferGT(int i, int i2) {
        if (i2 < i) {
            throwIAEGT(i, i2);
        }
    }

    public static void checkGT(Buffer buffer, int i) {
        checkBufferGT(buffer.remaining(), i);
    }

    public static void checkGT(CustomBuffer<?> customBuffer, int i) {
        checkBufferGT(customBuffer.remaining(), i);
    }

    public static long check(int i, int i2) {
        if (CHECKS) {
            CheckIntrinsics.checkIndex(i, i2);
        }
        return Integer.toUnsignedLong(i);
    }

    private static void throwIAE(int i, int i2) {
        throw new IllegalArgumentException("Number of remaining elements is " + i + ", must be at least " + i2);
    }

    private static void throwIAEGT(int i, int i2) {
        throw new IllegalArgumentException("Number of remaining buffer elements is " + i + ", must be at most " + i2);
    }
}
