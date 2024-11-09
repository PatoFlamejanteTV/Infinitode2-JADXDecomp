package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.system.Checks;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeType;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/Stat.class */
public class Stat {
    public static native int nstat(long j, long j2);

    public static native int nfstat(int i, long j);

    static {
        Library.initialize();
    }

    protected Stat() {
        throw new UnsupportedOperationException();
    }

    public static int stat(@NativeType("char const *") ByteBuffer byteBuffer, @NativeType("struct stat *") long j) {
        if (Checks.CHECKS) {
            Checks.checkNT1(byteBuffer);
            Checks.check(j);
        }
        return nstat(MemoryUtil.memAddress(byteBuffer), j);
    }

    public static int stat(@NativeType("char const *") CharSequence charSequence, @NativeType("struct stat *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        MemoryStack stackGet = MemoryStack.stackGet();
        int pointer = stackGet.getPointer();
        try {
            stackGet.nUTF8(charSequence, true);
            return nstat(stackGet.getPointerAddress(), j);
        } finally {
            stackGet.setPointer(pointer);
        }
    }

    public static int fstat(int i, @NativeType("struct stat *") long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return nfstat(i, j);
    }
}
