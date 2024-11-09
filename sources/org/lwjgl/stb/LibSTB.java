package org.lwjgl.stb;

import org.lwjgl.system.Configuration;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Platform;

/* loaded from: infinitode-2.jar:org/lwjgl/stb/LibSTB.class */
final class LibSTB {
    private static native void setupMalloc(long j, long j2, long j3, long j4, long j5, long j6);

    static {
        Library.loadSystem(System::load, System::loadLibrary, LibSTB.class, "org.lwjgl.stb", Platform.mapLibraryNameBundled("lwjgl_stb"));
        MemoryUtil.MemoryAllocator allocator = MemoryUtil.getAllocator(Configuration.DEBUG_MEMORY_ALLOCATOR_INTERNAL.get(Boolean.TRUE).booleanValue());
        setupMalloc(allocator.getMalloc(), allocator.getCalloc(), allocator.getRealloc(), allocator.getFree(), allocator.getAlignedAlloc(), allocator.getAlignedFree());
    }

    private LibSTB() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize() {
    }
}
