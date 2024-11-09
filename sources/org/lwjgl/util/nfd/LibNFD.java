package org.lwjgl.util.nfd;

import org.lwjgl.system.Configuration;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.Platform;

/* loaded from: infinitode-2.jar:org/lwjgl/util/nfd/LibNFD.class */
final class LibNFD {
    private static native void setupMalloc(long j, long j2, long j3, long j4, long j5, long j6);

    static {
        Library.loadSystem(System::load, System::loadLibrary, LibNFD.class, "org.lwjgl.nfd", Platform.mapLibraryNameBundled((Platform.get() == Platform.LINUX && Configuration.NFD_LINUX_PORTAL.get(Boolean.FALSE).booleanValue()) ? "lwjgl_nfd_portal" : "lwjgl_nfd"));
        MemoryUtil.MemoryAllocator allocator = MemoryUtil.getAllocator(Configuration.DEBUG_MEMORY_ALLOCATOR_INTERNAL.get(Boolean.TRUE).booleanValue());
        setupMalloc(allocator.getMalloc(), allocator.getCalloc(), allocator.getRealloc(), allocator.getFree(), allocator.getAlignedAlloc(), allocator.getAlignedFree());
    }

    private LibNFD() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void initialize() {
    }
}
