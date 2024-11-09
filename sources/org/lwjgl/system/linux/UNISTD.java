package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/UNISTD.class */
public class UNISTD {
    public static final int _SC_OPEN_MAX = 4;
    public static final int _SC_PAGE_SIZE = 30;
    public static final int _SC_IOV_MAX = 60;

    public static native int close(int i);

    public static native long sysconf(int i);

    public static native long nread(int i, long j, long j2);

    @NativeType("pid_t")
    public static native int getpid();

    @NativeType("pid_t")
    public static native int getppid();

    @NativeType("pid_t")
    public static native int gettid();

    static {
        Library.initialize();
    }

    protected UNISTD() {
        throw new UnsupportedOperationException();
    }

    @NativeType("ssize_t")
    public static long read(int i, @NativeType("void *") ByteBuffer byteBuffer) {
        return nread(i, MemoryUtil.memAddress(byteBuffer), byteBuffer.remaining());
    }
}
