package org.lwjgl.system.jni;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.CustomBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;
import org.lwjgl.system.jni.JNINativeMethod;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jni/JNINativeInterface.class */
public class JNINativeInterface {
    public static final int JNI_VERSION_1_1 = 65537;
    public static final int JNI_VERSION_1_2 = 65538;
    public static final int JNI_VERSION_1_4 = 65540;
    public static final int JNI_VERSION_1_6 = 65542;
    public static final int JNI_VERSION_1_8 = 65544;
    public static final int JNI_VERSION_9 = 589824;
    public static final int JNI_VERSION_10 = 655360;
    public static final int JNI_VERSION_19 = 1245184;
    public static final int JNI_VERSION_20 = 1310720;
    public static final int JNI_VERSION_21 = 1376256;
    public static final int JNIInvalidRefType = 0;
    public static final int JNILocalRefType = 1;
    public static final int JNIGlobalRefType = 2;
    public static final int JNIWeakGlobalRefType = 3;
    public static final int JNI_FALSE = 0;
    public static final int JNI_TRUE = 1;
    public static final int JNI_OK = 0;
    public static final int JNI_ERR = -1;
    public static final int JNI_EDETACHED = -2;
    public static final int JNI_EVERSION = -3;
    public static final int JNI_ENOMEM = -4;
    public static final int JNI_EEXIST = -5;
    public static final int JNI_EINVAL = -6;
    public static final int JNI_COMMIT = 1;
    public static final int JNI_ABORT = 2;

    @NativeType("jint")
    public static native int GetVersion();

    @NativeType("jmethodID")
    public static native long FromReflectedMethod(@NativeType("jobject") Method method);

    @NativeType("jfieldID")
    public static native long FromReflectedField(@NativeType("jobject") Field field);

    public static native Method nToReflectedMethod(Class<?> cls, long j, boolean z);

    public static native Field nToReflectedField(Class<?> cls, long j, boolean z);

    @NativeType("void *")
    public static native long NewGlobalRef(@NativeType("jobject") Object obj);

    public static native void nDeleteGlobalRef(long j);

    public static native long nGetBooleanArrayElements(byte[] bArr, long j);

    public static native void nReleaseBooleanArrayElements(byte[] bArr, long j, int i);

    public static native long nGetByteArrayElements(byte[] bArr, long j);

    public static native void nReleaseByteArrayElements(byte[] bArr, long j, int i);

    public static native long nGetCharArrayElements(char[] cArr, long j);

    public static native void nReleaseCharArrayElements(char[] cArr, long j, int i);

    public static native long nGetShortArrayElements(short[] sArr, long j);

    public static native void nReleaseShortArrayElements(short[] sArr, long j, int i);

    public static native long nGetIntArrayElements(int[] iArr, long j);

    public static native void nReleaseIntArrayElements(int[] iArr, long j, int i);

    public static native long nGetLongArrayElements(long[] jArr, long j);

    public static native void nReleaseLongArrayElements(long[] jArr, long j, int i);

    public static native long nGetFloatArrayElements(float[] fArr, long j);

    public static native void nReleaseFloatArrayElements(float[] fArr, long j, int i);

    public static native long nGetDoubleArrayElements(double[] dArr, long j);

    public static native void nReleaseDoubleArrayElements(double[] dArr, long j, int i);

    public static native void nGetBooleanArrayRegion(byte[] bArr, int i, int i2, long j);

    public static native void nSetBooleanArrayRegion(byte[] bArr, int i, int i2, long j);

    public static native void nGetByteArrayRegion(byte[] bArr, int i, int i2, long j);

    public static native void nSetByteArrayRegion(byte[] bArr, int i, int i2, long j);

    public static native void nGetCharArrayRegion(char[] cArr, int i, int i2, long j);

    public static native void nSetCharArrayRegion(char[] cArr, int i, int i2, long j);

    public static native void nGetShortArrayRegion(short[] sArr, int i, int i2, long j);

    public static native void nSetShortArrayRegion(short[] sArr, int i, int i2, long j);

    public static native void nGetIntArrayRegion(int[] iArr, int i, int i2, long j);

    public static native void nSetIntArrayRegion(int[] iArr, int i, int i2, long j);

    public static native void nGetLongArrayRegion(long[] jArr, int i, int i2, long j);

    public static native void nSetLongArrayRegion(long[] jArr, int i, int i2, long j);

    public static native void nGetFloatArrayRegion(float[] fArr, int i, int i2, long j);

    public static native void nSetFloatArrayRegion(float[] fArr, int i, int i2, long j);

    public static native void nGetDoubleArrayRegion(double[] dArr, int i, int i2, long j);

    public static native void nSetDoubleArrayRegion(double[] dArr, int i, int i2, long j);

    public static native int nRegisterNatives(Class<?> cls, long j, int i);

    @NativeType("jint")
    public static native int UnregisterNatives(@NativeType("jclass") Class<?> cls);

    public static native int nGetJavaVM(long j);

    public static native void nGetStringRegion(String str, int i, int i2, long j);

    public static native void nGetStringUTFRegion(String str, int i, int i2, long j);

    @NativeType("void *")
    public static native long NewWeakGlobalRef(@NativeType("jobject") Object obj);

    public static native void nDeleteWeakGlobalRef(long j);

    public static native ByteBuffer nNewDirectByteBuffer(long j, long j2);

    @NativeType("void *")
    public static native long GetDirectBufferAddress(@NativeType("jobject") Buffer buffer);

    @NativeType("jobjectRefType")
    public static native int GetObjectRefType(@NativeType("jobject") Object obj);

    public static native void noop();

    static {
        Library.initialize();
    }

    protected JNINativeInterface() {
        throw new UnsupportedOperationException();
    }

    @NativeType("jobject")
    public static Method ToReflectedMethod(@NativeType("jclass") Class<?> cls, @NativeType("jmethodID") long j, @NativeType("jboolean") boolean z) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nToReflectedMethod(cls, j, z);
    }

    @NativeType("jobject")
    public static Field ToReflectedField(@NativeType("jclass") Class<?> cls, @NativeType("jfieldID") long j, @NativeType("jboolean") boolean z) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nToReflectedField(cls, j, z);
    }

    public static void DeleteGlobalRef(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nDeleteGlobalRef(j);
    }

    @NativeType("jboolean *")
    public static ByteBuffer GetBooleanArrayElements(@NativeType("jbooleanArray") byte[] bArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memByteBufferSafe(nGetBooleanArrayElements(bArr, MemoryUtil.memAddressSafe(byteBuffer)), bArr.length);
    }

    public static void ReleaseBooleanArrayElements(@NativeType("jbooleanArray") byte[] bArr, @NativeType("jboolean *") ByteBuffer byteBuffer, @NativeType("jint") int i) {
        nReleaseBooleanArrayElements(bArr, MemoryUtil.memAddress(byteBuffer), i);
    }

    @NativeType("jbyte *")
    public static ByteBuffer GetByteArrayElements(@NativeType("jbyteArray") byte[] bArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memByteBufferSafe(nGetByteArrayElements(bArr, MemoryUtil.memAddressSafe(byteBuffer)), bArr.length);
    }

    public static void ReleaseByteArrayElements(@NativeType("jbyteArray") byte[] bArr, @NativeType("jbyte *") ByteBuffer byteBuffer, @NativeType("jint") int i) {
        nReleaseByteArrayElements(bArr, MemoryUtil.memAddress(byteBuffer), i);
    }

    @NativeType("jchar *")
    public static ShortBuffer GetCharArrayElements(@NativeType("jcharArray") char[] cArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memShortBufferSafe(nGetCharArrayElements(cArr, MemoryUtil.memAddressSafe(byteBuffer)), cArr.length);
    }

    public static void ReleaseCharArrayElements(@NativeType("jcharArray") char[] cArr, @NativeType("jchar *") ShortBuffer shortBuffer, @NativeType("jint") int i) {
        nReleaseCharArrayElements(cArr, MemoryUtil.memAddress(shortBuffer), i);
    }

    @NativeType("jshort *")
    public static ShortBuffer GetShortArrayElements(@NativeType("jshortArray") short[] sArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memShortBufferSafe(nGetShortArrayElements(sArr, MemoryUtil.memAddressSafe(byteBuffer)), sArr.length);
    }

    public static void ReleaseShortArrayElements(@NativeType("jshortArray") short[] sArr, @NativeType("jshort *") ShortBuffer shortBuffer, @NativeType("jint") int i) {
        nReleaseShortArrayElements(sArr, MemoryUtil.memAddress(shortBuffer), i);
    }

    @NativeType("jint *")
    public static IntBuffer GetIntArrayElements(@NativeType("jintArray") int[] iArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memIntBufferSafe(nGetIntArrayElements(iArr, MemoryUtil.memAddressSafe(byteBuffer)), iArr.length);
    }

    public static void ReleaseIntArrayElements(@NativeType("jintArray") int[] iArr, @NativeType("jint *") IntBuffer intBuffer, @NativeType("jint") int i) {
        nReleaseIntArrayElements(iArr, MemoryUtil.memAddress(intBuffer), i);
    }

    @NativeType("jlong *")
    public static LongBuffer GetLongArrayElements(@NativeType("jlongArray") long[] jArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memLongBufferSafe(nGetLongArrayElements(jArr, MemoryUtil.memAddressSafe(byteBuffer)), jArr.length);
    }

    public static void ReleaseLongArrayElements(@NativeType("jlongArray") long[] jArr, @NativeType("jlong *") LongBuffer longBuffer, @NativeType("jint") int i) {
        nReleaseLongArrayElements(jArr, MemoryUtil.memAddress(longBuffer), i);
    }

    @NativeType("jfloat *")
    public static FloatBuffer GetFloatArrayElements(@NativeType("jfloatArray") float[] fArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memFloatBufferSafe(nGetFloatArrayElements(fArr, MemoryUtil.memAddressSafe(byteBuffer)), fArr.length);
    }

    public static void ReleaseFloatArrayElements(@NativeType("jfloatArray") float[] fArr, @NativeType("jfloat *") FloatBuffer floatBuffer, @NativeType("jint") int i) {
        nReleaseFloatArrayElements(fArr, MemoryUtil.memAddress(floatBuffer), i);
    }

    @NativeType("jdouble *")
    public static DoubleBuffer GetDoubleArrayElements(@NativeType("jdoubleArray") double[] dArr, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.checkSafe((Buffer) byteBuffer, 1);
        }
        return MemoryUtil.memDoubleBufferSafe(nGetDoubleArrayElements(dArr, MemoryUtil.memAddressSafe(byteBuffer)), dArr.length);
    }

    public static void ReleaseDoubleArrayElements(@NativeType("jdoubleArray") double[] dArr, @NativeType("jdouble *") DoubleBuffer doubleBuffer, @NativeType("jint") int i) {
        nReleaseDoubleArrayElements(dArr, MemoryUtil.memAddress(doubleBuffer), i);
    }

    public static void GetBooleanArrayRegion(@NativeType("jbooleanArray") byte[] bArr, @NativeType("jsize") int i, @NativeType("jboolean *") ByteBuffer byteBuffer) {
        nGetBooleanArrayRegion(bArr, i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void SetBooleanArrayRegion(@NativeType("jbooleanArray") byte[] bArr, @NativeType("jsize") int i, @NativeType("jboolean const *") ByteBuffer byteBuffer) {
        nSetBooleanArrayRegion(bArr, i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void GetByteArrayRegion(@NativeType("jbyteArray") byte[] bArr, @NativeType("jsize") int i, @NativeType("jbyte *") ByteBuffer byteBuffer) {
        nGetByteArrayRegion(bArr, i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void SetByteArrayRegion(@NativeType("jbyteArray") byte[] bArr, @NativeType("jsize") int i, @NativeType("jbyte const *") ByteBuffer byteBuffer) {
        nSetByteArrayRegion(bArr, i, byteBuffer.remaining(), MemoryUtil.memAddress(byteBuffer));
    }

    public static void GetCharArrayRegion(@NativeType("jcharArray") char[] cArr, @NativeType("jsize") int i, @NativeType("jchar *") ShortBuffer shortBuffer) {
        nGetCharArrayRegion(cArr, i, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void SetCharArrayRegion(@NativeType("jcharArray") char[] cArr, @NativeType("jsize") int i, @NativeType("jchar const *") ShortBuffer shortBuffer) {
        nSetCharArrayRegion(cArr, i, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void GetShortArrayRegion(@NativeType("jshortArray") short[] sArr, @NativeType("jsize") int i, @NativeType("jshort *") ShortBuffer shortBuffer) {
        nGetShortArrayRegion(sArr, i, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void SetShortArrayRegion(@NativeType("jshortArray") short[] sArr, @NativeType("jsize") int i, @NativeType("jshort const *") ShortBuffer shortBuffer) {
        nSetShortArrayRegion(sArr, i, shortBuffer.remaining(), MemoryUtil.memAddress(shortBuffer));
    }

    public static void GetIntArrayRegion(@NativeType("jintArray") int[] iArr, @NativeType("jsize") int i, @NativeType("jint *") IntBuffer intBuffer) {
        nGetIntArrayRegion(iArr, i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void SetIntArrayRegion(@NativeType("jintArray") int[] iArr, @NativeType("jsize") int i, @NativeType("jint const *") IntBuffer intBuffer) {
        nSetIntArrayRegion(iArr, i, intBuffer.remaining(), MemoryUtil.memAddress(intBuffer));
    }

    public static void GetLongArrayRegion(@NativeType("jlongArray") long[] jArr, @NativeType("jsize") int i, @NativeType("jlong *") LongBuffer longBuffer) {
        nGetLongArrayRegion(jArr, i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void SetLongArrayRegion(@NativeType("jlongArray") long[] jArr, @NativeType("jsize") int i, @NativeType("jlong const *") LongBuffer longBuffer) {
        nSetLongArrayRegion(jArr, i, longBuffer.remaining(), MemoryUtil.memAddress(longBuffer));
    }

    public static void GetFloatArrayRegion(@NativeType("jfloatArray") float[] fArr, @NativeType("jsize") int i, @NativeType("jfloat *") FloatBuffer floatBuffer) {
        nGetFloatArrayRegion(fArr, i, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void SetFloatArrayRegion(@NativeType("jfloatArray") float[] fArr, @NativeType("jsize") int i, @NativeType("jfloat const *") FloatBuffer floatBuffer) {
        nSetFloatArrayRegion(fArr, i, floatBuffer.remaining(), MemoryUtil.memAddress(floatBuffer));
    }

    public static void GetDoubleArrayRegion(@NativeType("jdoubleArray") double[] dArr, @NativeType("jsize") int i, @NativeType("jdouble *") DoubleBuffer doubleBuffer) {
        nGetDoubleArrayRegion(dArr, i, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    public static void SetDoubleArrayRegion(@NativeType("jdoubleArray") double[] dArr, @NativeType("jsize") int i, @NativeType("jdouble const *") DoubleBuffer doubleBuffer) {
        nSetDoubleArrayRegion(dArr, i, doubleBuffer.remaining(), MemoryUtil.memAddress(doubleBuffer));
    }

    @NativeType("jint")
    public static int RegisterNatives(@NativeType("jclass") Class<?> cls, @NativeType("JNINativeMethod const *") JNINativeMethod.Buffer buffer) {
        if (Checks.CHECKS) {
            Struct.validate(buffer.address(), buffer.remaining(), JNINativeMethod.SIZEOF, JNINativeMethod::validate);
        }
        return nRegisterNatives(cls, buffer.address(), buffer.remaining());
    }

    @NativeType("jint")
    public static int GetJavaVM(@NativeType("JavaVM **") PointerBuffer pointerBuffer) {
        if (Checks.CHECKS) {
            Checks.check((CustomBuffer<?>) pointerBuffer, 1);
        }
        return nGetJavaVM(MemoryUtil.memAddress(pointerBuffer));
    }

    public static void GetStringRegion(@NativeType("jstring") String str, @NativeType("jsize") int i, @NativeType("jchar *") ByteBuffer byteBuffer) {
        nGetStringRegion(str, i, byteBuffer.remaining() >> 1, MemoryUtil.memAddress(byteBuffer));
    }

    public static void GetStringUTFRegion(@NativeType("jstring") String str, @NativeType("jsize") int i, @NativeType("jsize") int i2, @NativeType("char *") ByteBuffer byteBuffer) {
        if (Checks.CHECKS) {
            Checks.check((Buffer) byteBuffer, i2);
        }
        nGetStringUTFRegion(str, i, i2, MemoryUtil.memAddress(byteBuffer));
    }

    public static void DeleteWeakGlobalRef(@NativeType("void *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        nDeleteWeakGlobalRef(j);
    }

    @NativeType("jobject")
    public static ByteBuffer NewDirectByteBuffer(@NativeType("void *") long j, @NativeType("jlong") long j2) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nNewDirectByteBuffer(j, j2);
    }
}
