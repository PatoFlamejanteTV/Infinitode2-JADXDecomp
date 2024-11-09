package org.lwjgl.system;

import org.lwjgl.system.libc.LibCString;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:org/lwjgl/system/MultiReleaseMemCopy.class */
public final class MultiReleaseMemCopy {
    private MultiReleaseMemCopy() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copy(long j, long j2, long j3) {
        if (j3 <= 160) {
            if (Pointer.BITS64 && ((j | j2) & 7) == 0) {
                MemoryUtil.memCopyAligned64(j, j2, ((int) j3) & 255);
                return;
            } else {
                MemoryUtil.UNSAFE.copyMemory((Object) null, j, (Object) null, j2, j3);
                return;
            }
        }
        LibCString.nmemcpy(j2, j, j3);
    }
}
