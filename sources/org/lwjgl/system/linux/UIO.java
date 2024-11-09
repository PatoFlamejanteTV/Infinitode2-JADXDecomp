package org.lwjgl.system.linux;

import org.lwjgl.system.Library;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/UIO.class */
public class UIO {
    public static final int UIO_FASTIOV = 8;
    public static final int UIO_MAXIOV = 1024;
    public static final int RWF_HIPRI = 1;
    public static final int RWF_DSYNC = 2;
    public static final int RWF_SYNC = 4;
    public static final int RWF_NOWAIT = 8;
    public static final int RWF_APPEND = 16;

    public static native long nreadv(int i, long j, int i2);

    public static native long nwritev(int i, long j, int i2);

    public static native long npreadv(int i, long j, int i2, long j2);

    public static native long npwritev(int i, long j, int i2, long j2);

    public static native long nprocess_vm_readv(int i, long j, long j2, long j3, long j4, long j5);

    public static native long nprocess_vm_writev(int i, long j, long j2, long j3, long j4, long j5);

    static {
        Library.initialize();
    }

    protected UIO() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ssize_t")
    public static long readv(int i, @NativeType("struct iovec const *") IOVec iOVec, int i2) {
        return nreadv(i, iOVec.address(), i2);
    }

    @NativeType("ssize_t")
    public static long writev(int i, @NativeType("struct iovec const *") IOVec iOVec, int i2) {
        return nwritev(i, iOVec.address(), i2);
    }

    @NativeType("ssize_t")
    public static long preadv(int i, @NativeType("struct iovec const *") IOVec iOVec, int i2, @NativeType("off_t") long j) {
        return npreadv(i, iOVec.address(), i2, j);
    }

    @NativeType("ssize_t")
    public static long pwritev(int i, @NativeType("struct iovec const *") IOVec iOVec, int i2, @NativeType("off_t") long j) {
        return npwritev(i, iOVec.address(), i2, j);
    }

    @NativeType("ssize_t")
    public static long process_vm_readv(@NativeType("pid_t") int i, @NativeType("struct iovec const *") IOVec iOVec, @NativeType("unsigned long int") long j, @NativeType("struct iovec const *") IOVec iOVec2, @NativeType("unsigned long int") long j2, @NativeType("unsigned long int") long j3) {
        return nprocess_vm_readv(i, iOVec.address(), j, iOVec2.address(), j2, j3);
    }

    @NativeType("ssize_t")
    public static long process_vm_writev(@NativeType("pid_t") int i, @NativeType("struct iovec const *") IOVec iOVec, @NativeType("unsigned long int") long j, @NativeType("struct iovec const *") IOVec iOVec2, @NativeType("unsigned long int") long j2, @NativeType("unsigned long int") long j3) {
        return nprocess_vm_writev(i, iOVec.address(), j, iOVec2.address(), j2, j3);
    }
}
