package org.lwjgl.system;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/SharedLibraryUtil.class */
public final class SharedLibraryUtil {
    private static native int getLibraryPath(long j, long j2, int i);

    public static String getLibraryPath(long j) {
        int i = 256;
        ByteBuffer memAlloc = MemoryUtil.memAlloc(256);
        while (true) {
            try {
                int libraryPath = getLibraryPath(j, MemoryUtil.memAddress(memAlloc), i);
                if (libraryPath != 0) {
                    if (libraryPath < i) {
                        String memUTF8 = MemoryUtil.memUTF8(memAlloc, libraryPath - 1);
                        MemoryUtil.memFree(memAlloc);
                        return memUTF8;
                    }
                    int i2 = (i * 3) / 2;
                    i = i2;
                    memAlloc = MemoryUtil.memRealloc(memAlloc, i2);
                } else {
                    return null;
                }
            } finally {
                MemoryUtil.memFree(memAlloc);
            }
        }
    }
}
