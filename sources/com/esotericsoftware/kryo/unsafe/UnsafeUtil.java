package com.esotericsoftware.kryo.unsafe;

import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.util.Util;
import com.esotericsoftware.minlog.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import sun.misc.Unsafe;
import sun.nio.ch.DirectBuffer;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/unsafe/UnsafeUtil.class */
public class UnsafeUtil {
    public static final Unsafe unsafe;
    public static final long byteArrayBaseOffset;
    public static final long floatArrayBaseOffset;
    public static final long doubleArrayBaseOffset;
    public static final long intArrayBaseOffset;
    public static final long longArrayBaseOffset;
    public static final long shortArrayBaseOffset;
    public static final long charArrayBaseOffset;
    public static final long booleanArrayBaseOffset;

    static {
        Unsafe unsafe2 = null;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        long j5 = 0;
        long j6 = 0;
        long j7 = 0;
        long j8 = 0;
        try {
            if (!Util.isAndroid) {
                Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                Unsafe unsafe3 = (Unsafe) declaredField.get(null);
                unsafe2 = unsafe3;
                j = unsafe3.arrayBaseOffset(byte[].class);
                j7 = unsafe2.arrayBaseOffset(char[].class);
                j6 = unsafe2.arrayBaseOffset(short[].class);
                j4 = unsafe2.arrayBaseOffset(int[].class);
                j2 = unsafe2.arrayBaseOffset(float[].class);
                j5 = unsafe2.arrayBaseOffset(long[].class);
                j3 = unsafe2.arrayBaseOffset(double[].class);
                j8 = unsafe2.arrayBaseOffset(boolean[].class);
            } else if (Log.DEBUG) {
                Log.debug("kryo", "Unsafe is not available on Android.");
            }
        } catch (Exception e) {
            if (Log.DEBUG) {
                Log.debug("kryo", "Unsafe is not available.", e);
            }
        }
        byteArrayBaseOffset = j;
        charArrayBaseOffset = j7;
        shortArrayBaseOffset = j6;
        intArrayBaseOffset = j4;
        floatArrayBaseOffset = j2;
        longArrayBaseOffset = j5;
        doubleArrayBaseOffset = j3;
        booleanArrayBaseOffset = j8;
        unsafe = unsafe2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryo/unsafe/UnsafeUtil$DirectBuffers.class */
    public static final class DirectBuffers {
        private static Constructor<? extends ByteBuffer> directByteBufferConstructor;
        private static Method cleanerMethod;
        private static Method cleanMethod;

        private DirectBuffers() {
        }

        static {
            try {
                Constructor declaredConstructor = ByteBuffer.allocateDirect(1).getClass().getDeclaredConstructor(Long.TYPE, Integer.TYPE);
                directByteBufferConstructor = declaredConstructor;
                declaredConstructor.setAccessible(true);
            } catch (Exception e) {
                if (Log.DEBUG) {
                    Log.debug("kryo", "No direct ByteBuffer constructor is available.", e);
                }
                directByteBufferConstructor = null;
            }
            try {
                Method method = DirectBuffer.class.getMethod("cleaner", new Class[0]);
                cleanerMethod = method;
                method.setAccessible(true);
                cleanMethod = cleanerMethod.getReturnType().getMethod("clean", new Class[0]);
            } catch (Exception e2) {
                if (Log.DEBUG) {
                    Log.debug("kryo", "No direct ByteBuffer clean method is available.", e2);
                }
                cleanerMethod = null;
            }
        }
    }

    public static ByteBuffer newDirectBuffer(long j, int i) {
        if (!isNewDirectBufferAvailable()) {
            throw new UnsupportedOperationException("No direct ByteBuffer constructor is available.");
        }
        try {
            return (ByteBuffer) DirectBuffers.directByteBufferConstructor.newInstance(Long.valueOf(j), Integer.valueOf(i));
        } catch (Exception e) {
            throw new KryoException("Error creating a ByteBuffer at address: " + j, e);
        }
    }

    public static boolean isNewDirectBufferAvailable() {
        return DirectBuffers.directByteBufferConstructor != null;
    }

    public static void dispose(ByteBuffer byteBuffer) {
        if ((byteBuffer instanceof DirectBuffer) && DirectBuffers.cleanerMethod != null) {
            try {
                DirectBuffers.cleanMethod.invoke(DirectBuffers.cleanerMethod.invoke(byteBuffer, new Object[0]), new Object[0]);
            } catch (Throwable unused) {
            }
        }
    }
}
