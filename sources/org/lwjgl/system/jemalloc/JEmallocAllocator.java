package org.lwjgl.system.jemalloc;

import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.jemalloc.JEmalloc;

/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/JEmallocAllocator.class */
public class JEmallocAllocator implements MemoryUtil.MemoryAllocator {
    static {
        JEmalloc.getLibrary();
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long getMalloc() {
        return JEmalloc.Functions.malloc;
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long getCalloc() {
        return JEmalloc.Functions.calloc;
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long getRealloc() {
        return JEmalloc.Functions.realloc;
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long getFree() {
        return JEmalloc.Functions.free;
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long getAlignedAlloc() {
        return JEmalloc.Functions.aligned_alloc;
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long getAlignedFree() {
        return JEmalloc.Functions.free;
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long malloc(long j) {
        return JEmalloc.nje_malloc(j);
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long calloc(long j, long j2) {
        return JEmalloc.nje_calloc(j, j2);
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long realloc(long j, long j2) {
        return JEmalloc.nje_realloc(j, j2);
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public void free(long j) {
        JEmalloc.nje_free(j);
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public long aligned_alloc(long j, long j2) {
        return JEmalloc.nje_aligned_alloc(j, j2);
    }

    @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
    public void aligned_free(long j) {
        JEmalloc.nje_free(j);
    }
}
