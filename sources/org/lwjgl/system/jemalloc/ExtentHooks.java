package org.lwjgl.system.jemalloc;

import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.Checks;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.NativeResource;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.Struct;

@NativeType("struct extent_hooks_t")
/* loaded from: infinitode-2.jar:org/lwjgl/system/jemalloc/ExtentHooks.class */
public class ExtentHooks extends Struct<ExtentHooks> implements NativeResource {
    public static final int SIZEOF;
    public static final int ALIGNOF;
    public static final int ALLOC;
    public static final int DALLOC;
    public static final int DESTROY;
    public static final int COMMIT;
    public static final int DECOMMIT;
    public static final int PURGE_LAZY;
    public static final int PURGE_FORCED;
    public static final int SPLIT;
    public static final int MERGE;

    static {
        Struct.Layout __struct = __struct(__member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE), __member(POINTER_SIZE));
        SIZEOF = __struct.getSize();
        ALIGNOF = __struct.getAlignment();
        ALLOC = __struct.offsetof(0);
        DALLOC = __struct.offsetof(1);
        DESTROY = __struct.offsetof(2);
        COMMIT = __struct.offsetof(3);
        DECOMMIT = __struct.offsetof(4);
        PURGE_LAZY = __struct.offsetof(5);
        PURGE_FORCED = __struct.offsetof(6);
        SPLIT = __struct.offsetof(7);
        MERGE = __struct.offsetof(8);
    }

    protected ExtentHooks(long j, ByteBuffer byteBuffer) {
        super(j, byteBuffer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // org.lwjgl.system.Struct
    public ExtentHooks create(long j, ByteBuffer byteBuffer) {
        return new ExtentHooks(j, byteBuffer);
    }

    public ExtentHooks(ByteBuffer byteBuffer) {
        super(MemoryUtil.memAddress(byteBuffer), __checkContainer(byteBuffer, SIZEOF));
    }

    @Override // org.lwjgl.system.Struct
    public int sizeof() {
        return SIZEOF;
    }

    @NativeType("extent_alloc_t")
    public ExtentAlloc alloc() {
        return nalloc(address());
    }

    @NativeType("extent_dalloc_t")
    public ExtentDalloc dalloc() {
        return ndalloc(address());
    }

    @NativeType("extent_destroy_t")
    public ExtentDestroy destroy() {
        return ndestroy(address());
    }

    @NativeType("extent_commit_t")
    public ExtentCommit commit() {
        return ncommit(address());
    }

    @NativeType("extent_decommit_t")
    public ExtentDecommit decommit() {
        return ndecommit(address());
    }

    @NativeType("extent_purge_t")
    public ExtentPurge purge_lazy() {
        return npurge_lazy(address());
    }

    @NativeType("extent_purge_t")
    public ExtentPurge purge_forced() {
        return npurge_forced(address());
    }

    @NativeType("extent_split_t")
    public ExtentSplit split() {
        return nsplit(address());
    }

    @NativeType("extent_merge_t")
    public ExtentMerge merge() {
        return nmerge(address());
    }

    public ExtentHooks alloc(@NativeType("extent_alloc_t") ExtentAllocI extentAllocI) {
        nalloc(address(), extentAllocI);
        return this;
    }

    public ExtentHooks dalloc(@NativeType("extent_dalloc_t") ExtentDallocI extentDallocI) {
        ndalloc(address(), extentDallocI);
        return this;
    }

    public ExtentHooks destroy(@NativeType("extent_destroy_t") ExtentDestroyI extentDestroyI) {
        ndestroy(address(), extentDestroyI);
        return this;
    }

    public ExtentHooks commit(@NativeType("extent_commit_t") ExtentCommitI extentCommitI) {
        ncommit(address(), extentCommitI);
        return this;
    }

    public ExtentHooks decommit(@NativeType("extent_decommit_t") ExtentDecommitI extentDecommitI) {
        ndecommit(address(), extentDecommitI);
        return this;
    }

    public ExtentHooks purge_lazy(@NativeType("extent_purge_t") ExtentPurgeI extentPurgeI) {
        npurge_lazy(address(), extentPurgeI);
        return this;
    }

    public ExtentHooks purge_forced(@NativeType("extent_purge_t") ExtentPurgeI extentPurgeI) {
        npurge_forced(address(), extentPurgeI);
        return this;
    }

    public ExtentHooks split(@NativeType("extent_split_t") ExtentSplitI extentSplitI) {
        nsplit(address(), extentSplitI);
        return this;
    }

    public ExtentHooks merge(@NativeType("extent_merge_t") ExtentMergeI extentMergeI) {
        nmerge(address(), extentMergeI);
        return this;
    }

    public ExtentHooks set(ExtentAllocI extentAllocI, ExtentDallocI extentDallocI, ExtentDestroyI extentDestroyI, ExtentCommitI extentCommitI, ExtentDecommitI extentDecommitI, ExtentPurgeI extentPurgeI, ExtentPurgeI extentPurgeI2, ExtentSplitI extentSplitI, ExtentMergeI extentMergeI) {
        alloc(extentAllocI);
        dalloc(extentDallocI);
        destroy(extentDestroyI);
        commit(extentCommitI);
        decommit(extentDecommitI);
        purge_lazy(extentPurgeI);
        purge_forced(extentPurgeI2);
        split(extentSplitI);
        merge(extentMergeI);
        return this;
    }

    public ExtentHooks set(ExtentHooks extentHooks) {
        MemoryUtil.memCopy(extentHooks.address(), address(), SIZEOF);
        return this;
    }

    public static ExtentHooks malloc() {
        return new ExtentHooks(MemoryUtil.nmemAllocChecked(SIZEOF), null);
    }

    public static ExtentHooks calloc() {
        return new ExtentHooks(MemoryUtil.nmemCallocChecked(1L, SIZEOF), null);
    }

    public static ExtentHooks create() {
        ByteBuffer createByteBuffer = BufferUtils.createByteBuffer(SIZEOF);
        return new ExtentHooks(MemoryUtil.memAddress(createByteBuffer), createByteBuffer);
    }

    public static ExtentHooks create(long j) {
        return new ExtentHooks(j, null);
    }

    public static ExtentHooks createSafe(long j) {
        if (j == 0) {
            return null;
        }
        return new ExtentHooks(j, null);
    }

    @Deprecated
    public static ExtentHooks mallocStack() {
        return malloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static ExtentHooks callocStack() {
        return calloc(MemoryStack.stackGet());
    }

    @Deprecated
    public static ExtentHooks mallocStack(MemoryStack memoryStack) {
        return malloc(memoryStack);
    }

    @Deprecated
    public static ExtentHooks callocStack(MemoryStack memoryStack) {
        return calloc(memoryStack);
    }

    public static ExtentHooks malloc(MemoryStack memoryStack) {
        return new ExtentHooks(memoryStack.nmalloc(ALIGNOF, SIZEOF), null);
    }

    public static ExtentHooks calloc(MemoryStack memoryStack) {
        return new ExtentHooks(memoryStack.ncalloc(ALIGNOF, 1, SIZEOF), null);
    }

    public static ExtentAlloc nalloc(long j) {
        return ExtentAlloc.create(MemoryUtil.memGetAddress(j + ALLOC));
    }

    public static ExtentDalloc ndalloc(long j) {
        return ExtentDalloc.createSafe(MemoryUtil.memGetAddress(j + DALLOC));
    }

    public static ExtentDestroy ndestroy(long j) {
        return ExtentDestroy.createSafe(MemoryUtil.memGetAddress(j + DESTROY));
    }

    public static ExtentCommit ncommit(long j) {
        return ExtentCommit.createSafe(MemoryUtil.memGetAddress(j + COMMIT));
    }

    public static ExtentDecommit ndecommit(long j) {
        return ExtentDecommit.createSafe(MemoryUtil.memGetAddress(j + DECOMMIT));
    }

    public static ExtentPurge npurge_lazy(long j) {
        return ExtentPurge.createSafe(MemoryUtil.memGetAddress(j + PURGE_LAZY));
    }

    public static ExtentPurge npurge_forced(long j) {
        return ExtentPurge.createSafe(MemoryUtil.memGetAddress(j + PURGE_FORCED));
    }

    public static ExtentSplit nsplit(long j) {
        return ExtentSplit.createSafe(MemoryUtil.memGetAddress(j + SPLIT));
    }

    public static ExtentMerge nmerge(long j) {
        return ExtentMerge.createSafe(MemoryUtil.memGetAddress(j + MERGE));
    }

    public static void nalloc(long j, ExtentAllocI extentAllocI) {
        MemoryUtil.memPutAddress(j + ALLOC, extentAllocI.address());
    }

    public static void ndalloc(long j, ExtentDallocI extentDallocI) {
        MemoryUtil.memPutAddress(j + DALLOC, MemoryUtil.memAddressSafe(extentDallocI));
    }

    public static void ndestroy(long j, ExtentDestroyI extentDestroyI) {
        MemoryUtil.memPutAddress(j + DESTROY, MemoryUtil.memAddressSafe(extentDestroyI));
    }

    public static void ncommit(long j, ExtentCommitI extentCommitI) {
        MemoryUtil.memPutAddress(j + COMMIT, MemoryUtil.memAddressSafe(extentCommitI));
    }

    public static void ndecommit(long j, ExtentDecommitI extentDecommitI) {
        MemoryUtil.memPutAddress(j + DECOMMIT, MemoryUtil.memAddressSafe(extentDecommitI));
    }

    public static void npurge_lazy(long j, ExtentPurgeI extentPurgeI) {
        MemoryUtil.memPutAddress(j + PURGE_LAZY, MemoryUtil.memAddressSafe(extentPurgeI));
    }

    public static void npurge_forced(long j, ExtentPurgeI extentPurgeI) {
        MemoryUtil.memPutAddress(j + PURGE_FORCED, MemoryUtil.memAddressSafe(extentPurgeI));
    }

    public static void nsplit(long j, ExtentSplitI extentSplitI) {
        MemoryUtil.memPutAddress(j + SPLIT, MemoryUtil.memAddressSafe(extentSplitI));
    }

    public static void nmerge(long j, ExtentMergeI extentMergeI) {
        MemoryUtil.memPutAddress(j + MERGE, MemoryUtil.memAddressSafe(extentMergeI));
    }

    public static void validate(long j) {
        Checks.check(MemoryUtil.memGetAddress(j + ALLOC));
    }
}
