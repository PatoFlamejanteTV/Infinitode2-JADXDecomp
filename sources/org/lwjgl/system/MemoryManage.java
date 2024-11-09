package org.lwjgl.system;

import com.vladsch.flexmark.util.sequence.SequenceUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import org.lwjgl.system.MemoryUtil;
import org.lwjgl.system.libc.LibCStdlib;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.LibFFI;

/* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryManage.class */
final class MemoryManage {
    private MemoryManage() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static MemoryUtil.MemoryAllocator getInstance() {
        String str;
        Object obj = Configuration.MEMORY_ALLOCATOR.get();
        if (!(obj instanceof MemoryUtil.MemoryAllocator)) {
            if (!"system".equals(obj)) {
                if (obj != null && !"jemalloc".equals(obj)) {
                    if ("rpmalloc".equals(obj)) {
                        str = "org.lwjgl.system.rpmalloc.RPmallocAllocator";
                    } else {
                        str = obj.toString();
                    }
                } else {
                    str = "org.lwjgl.system.jemalloc.JEmallocAllocator";
                }
                try {
                    return (MemoryUtil.MemoryAllocator) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    if (Checks.DEBUG && obj != null) {
                        th.printStackTrace(APIUtil.DEBUG_STREAM);
                    }
                    APIUtil.apiLog(String.format("Warning: Failed to instantiate memory allocator: %s. Using the system default.", str));
                }
            }
            return new StdlibAllocator();
        }
        return (MemoryUtil.MemoryAllocator) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryManage$StdlibAllocator.class */
    public static class StdlibAllocator implements MemoryUtil.MemoryAllocator {
        private StdlibAllocator() {
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getMalloc() {
            return MemoryAccessJNI.malloc;
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getCalloc() {
            return MemoryAccessJNI.calloc;
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getRealloc() {
            return MemoryAccessJNI.realloc;
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getFree() {
            return MemoryAccessJNI.free;
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getAlignedAlloc() {
            return MemoryAccessJNI.aligned_alloc;
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getAlignedFree() {
            return MemoryAccessJNI.aligned_free;
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long malloc(long j) {
            return LibCStdlib.nmalloc(j);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long calloc(long j, long j2) {
            return LibCStdlib.ncalloc(j, j2);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long realloc(long j, long j2) {
            return LibCStdlib.nrealloc(j, j2);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public void free(long j) {
            LibCStdlib.nfree(j);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long aligned_alloc(long j, long j2) {
            return LibCStdlib.naligned_alloc(j, j2);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public void aligned_free(long j) {
            LibCStdlib.naligned_free(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryManage$DebugAllocator.class */
    public static class DebugAllocator implements MemoryUtil.MemoryAllocator {
        private static final ConcurrentMap<Allocation, Allocation> ALLOCATIONS = new ConcurrentHashMap();
        private static final ConcurrentMap<Long, String> THREADS = new ConcurrentHashMap();
        private final MemoryUtil.MemoryAllocator allocator;
        private final long[] callbacks = {new CallbackI() { // from class: org.lwjgl.system.MemoryManage.DebugAllocator.1
            @Override // org.lwjgl.system.CallbackI
            public FFICIF getCallInterface() {
                return APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer);
            }

            @Override // org.lwjgl.system.CallbackI
            public void callback(long j, long j2) {
                MemoryUtil.memPutAddress(j, DebugAllocator.this.malloc(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2))));
            }
        }.address(), new CallbackI() { // from class: org.lwjgl.system.MemoryManage.DebugAllocator.2
            @Override // org.lwjgl.system.CallbackI
            public FFICIF getCallInterface() {
                return APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer);
            }

            @Override // org.lwjgl.system.CallbackI
            public void callback(long j, long j2) {
                MemoryUtil.memPutAddress(j, DebugAllocator.this.calloc(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + POINTER_SIZE))));
            }
        }.address(), new CallbackI() { // from class: org.lwjgl.system.MemoryManage.DebugAllocator.3
            @Override // org.lwjgl.system.CallbackI
            public FFICIF getCallInterface() {
                return APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer);
            }

            @Override // org.lwjgl.system.CallbackI
            public void callback(long j, long j2) {
                MemoryUtil.memPutAddress(j, DebugAllocator.this.realloc(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + POINTER_SIZE))));
            }
        }.address(), new CallbackI() { // from class: org.lwjgl.system.MemoryManage.DebugAllocator.4
            @Override // org.lwjgl.system.CallbackI
            public FFICIF getCallInterface() {
                return APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_void, LibFFI.ffi_type_pointer);
            }

            @Override // org.lwjgl.system.CallbackI
            public void callback(long j, long j2) {
                DebugAllocator.this.free(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)));
            }
        }.address(), new CallbackI() { // from class: org.lwjgl.system.MemoryManage.DebugAllocator.5
            @Override // org.lwjgl.system.CallbackI
            public FFICIF getCallInterface() {
                return APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer, LibFFI.ffi_type_pointer);
            }

            @Override // org.lwjgl.system.CallbackI
            public void callback(long j, long j2) {
                MemoryUtil.memPutAddress(j, DebugAllocator.this.aligned_alloc(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)), MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2 + POINTER_SIZE))));
            }
        }.address(), new CallbackI() { // from class: org.lwjgl.system.MemoryManage.DebugAllocator.6
            @Override // org.lwjgl.system.CallbackI
            public FFICIF getCallInterface() {
                return APIUtil.apiCreateCIF(LibFFI.FFI_DEFAULT_ABI, LibFFI.ffi_type_void, LibFFI.ffi_type_pointer);
            }

            @Override // org.lwjgl.system.CallbackI
            public void callback(long j, long j2) {
                DebugAllocator.this.aligned_free(MemoryUtil.memGetAddress(MemoryUtil.memGetAddress(j2)));
            }
        }.address()};

        /* JADX INFO: Access modifiers changed from: package-private */
        public DebugAllocator(MemoryUtil.MemoryAllocator memoryAllocator) {
            this.allocator = memoryAllocator;
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                for (long j : this.callbacks) {
                    Callback.free(j);
                }
                if (ALLOCATIONS.isEmpty()) {
                    return;
                }
                boolean z = false;
                for (Allocation allocation : ALLOCATIONS.keySet()) {
                    StringBuilder sb = new StringBuilder(512);
                    sb.append("[LWJGL] ").append(allocation.size).append(" bytes leaked, thread ").append(allocation.threadId).append(" (").append(THREADS.get(Long.valueOf(allocation.threadId))).append("), address: 0x").append(Long.toHexString(allocation.address).toUpperCase()).append(SequenceUtils.EOL);
                    StackTraceElement[] elements = allocation.getElements();
                    if (elements != null) {
                        for (StackTraceElement stackTraceElement : elements) {
                            sb.append("\tat ").append(stackTraceElement.toString()).append(SequenceUtils.EOL);
                        }
                    } else {
                        z = true;
                    }
                    APIUtil.DEBUG_STREAM.print(sb);
                }
                if (z) {
                    APIUtil.DEBUG_STREAM.print("[LWJGL] Reminder: disable Configuration.DEBUG_MEMORY_ALLOCATOR_FAST to get stacktraces of leaking allocations.\n");
                }
            }));
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getMalloc() {
            return this.callbacks[0];
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getCalloc() {
            return this.callbacks[1];
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getRealloc() {
            return this.callbacks[2];
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getFree() {
            return this.callbacks[3];
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getAlignedAlloc() {
            return this.callbacks[4];
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long getAlignedFree() {
            return this.callbacks[5];
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long malloc(long j) {
            return track(this.allocator.malloc(j), j);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long calloc(long j, long j2) {
            return track(this.allocator.calloc(j, j2), j * j2);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long realloc(long j, long j2) {
            long untrack = untrack(j);
            long realloc = this.allocator.realloc(j, j2);
            if (j2 != 0) {
                track(realloc, j2);
            } else if (j2 != 0) {
                track(j, untrack);
            }
            return realloc;
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public void free(long j) {
            untrack(j);
            this.allocator.free(j);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public long aligned_alloc(long j, long j2) {
            return track(this.allocator.aligned_alloc(j, j2), j2);
        }

        @Override // org.lwjgl.system.MemoryUtil.MemoryAllocator
        public void aligned_free(long j) {
            untrack(j);
            this.allocator.aligned_free(j);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static long track(long j, long j2) {
            if (j != 0) {
                Thread currentThread = Thread.currentThread();
                THREADS.putIfAbsent(Long.valueOf(currentThread.getId()), currentThread.getName());
                Allocation allocation = new Allocation(j, j2, currentThread.getId(), Configuration.DEBUG_MEMORY_ALLOCATOR_FAST.get(Boolean.FALSE).booleanValue() ? null : StackWalkUtil.stackWalkGetTrace());
                Allocation put = ALLOCATIONS.put(allocation, allocation);
                if (put != null) {
                    trackAbort(j, put, allocation);
                }
            }
            return j;
        }

        private static void trackAbort(long j, Allocation allocation, Allocation allocation2) {
            String upperCase = Long.toHexString(j).toUpperCase();
            trackAbortPrint(allocation, "Old", upperCase);
            trackAbortPrint(allocation2, "New", upperCase);
            throw new IllegalStateException("The memory address specified is already being tracked: 0x" + upperCase);
        }

        private static void trackAbortPrint(Allocation allocation, String str, String str2) {
            StringBuilder sb = new StringBuilder(512);
            sb.append("[LWJGL] ").append(str).append(" allocation with size ").append(allocation.size).append(", thread ").append(allocation.threadId).append(" (").append(THREADS.get(Long.valueOf(allocation.threadId))).append("), address: 0x").append(str2).append(SequenceUtils.EOL);
            StackTraceElement[] elements = allocation.getElements();
            if (elements != null) {
                for (StackTraceElement stackTraceElement : elements) {
                    sb.append("\tat ").append(stackTraceElement.toString()).append(SequenceUtils.EOL);
                }
            }
            APIUtil.DEBUG_STREAM.print(sb);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static long untrack(long j) {
            if (j == 0) {
                return 0L;
            }
            Allocation remove = ALLOCATIONS.remove(new Allocation(j, 0L, 0L, null));
            if (remove == null) {
                untrackAbort(j);
            }
            return remove.size;
        }

        private static void untrackAbort(long j) {
            throw new IllegalStateException("The memory address specified is not being tracked: 0x" + Long.toHexString(j).toUpperCase());
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryManage$DebugAllocator$Allocation.class */
        public static class Allocation {
            final long address;
            final long size;
            final long threadId;
            private final Object[] stacktrace;

            Allocation(long j, long j2, long j3, Object[] objArr) {
                this.address = j;
                this.size = j2;
                this.threadId = j3;
                this.stacktrace = objArr;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public StackTraceElement[] getElements() {
                if (this.stacktrace == null) {
                    return null;
                }
                return StackWalkUtil.stackWalkArray(this.stacktrace);
            }

            public boolean equals(Object obj) {
                return this.address == ((Allocation) obj).address;
            }

            public int hashCode() {
                return Long.hashCode(this.address);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void report(MemoryUtil.MemoryAllocationReport memoryAllocationReport) {
            for (Allocation allocation : ALLOCATIONS.keySet()) {
                memoryAllocationReport.invoke(allocation.address, allocation.size, allocation.threadId, THREADS.get(Long.valueOf(allocation.threadId)), allocation.getElements());
            }
        }

        private static <T> void aggregate(T t, long j, Map<T, AtomicLong> map) {
            AtomicLong computeIfAbsent = map.computeIfAbsent(t, obj -> {
                return new AtomicLong();
            });
            computeIfAbsent.set(computeIfAbsent.get() + j);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static void report(MemoryUtil.MemoryAllocationReport memoryAllocationReport, MemoryUtil.MemoryAllocationReport.Aggregate aggregate, boolean z) {
            switch (aggregate) {
                case ALL:
                    reportAll(memoryAllocationReport, z);
                    return;
                case GROUP_BY_METHOD:
                    reportByMethod(memoryAllocationReport, z);
                    return;
                case GROUP_BY_STACKTRACE:
                    reportByStacktrace(memoryAllocationReport, z);
                    return;
                default:
                    return;
            }
        }

        private static void reportAll(MemoryUtil.MemoryAllocationReport memoryAllocationReport, boolean z) {
            if (z) {
                HashMap hashMap = new HashMap();
                for (Allocation allocation : ALLOCATIONS.values()) {
                    aggregate(Long.valueOf(allocation.threadId), allocation.size, hashMap);
                }
                for (Map.Entry entry : hashMap.entrySet()) {
                    memoryAllocationReport.invoke(0L, ((AtomicLong) entry.getValue()).get(), ((Long) entry.getKey()).longValue(), THREADS.get(entry.getKey()), (StackTraceElement[]) null);
                }
                return;
            }
            long j = 0;
            Iterator<Allocation> it = ALLOCATIONS.values().iterator();
            while (it.hasNext()) {
                j += it.next().size;
            }
            memoryAllocationReport.invoke(0L, j, 0L, null, (StackTraceElement[]) null);
        }

        private static void reportByMethod(MemoryUtil.MemoryAllocationReport memoryAllocationReport, boolean z) {
            if (z) {
                HashMap hashMap = new HashMap();
                for (Allocation allocation : ALLOCATIONS.keySet()) {
                    StackTraceElement[] elements = allocation.getElements();
                    if (elements != null) {
                        aggregate(elements[0], allocation.size, (Map) hashMap.computeIfAbsent(Long.valueOf(allocation.threadId), l -> {
                            return new HashMap();
                        }));
                    }
                }
                for (Map.Entry entry : hashMap.entrySet()) {
                    long longValue = ((Long) entry.getKey()).longValue();
                    String str = THREADS.get(Long.valueOf(longValue));
                    for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                        memoryAllocationReport.invoke(0L, ((AtomicLong) entry2.getValue()).get(), longValue, str, (StackTraceElement) entry2.getKey());
                    }
                }
                return;
            }
            HashMap hashMap2 = new HashMap();
            for (Allocation allocation2 : ALLOCATIONS.keySet()) {
                StackTraceElement[] elements2 = allocation2.getElements();
                if (elements2 != null) {
                    aggregate(elements2[0], allocation2.size, hashMap2);
                }
            }
            for (Map.Entry entry3 : hashMap2.entrySet()) {
                memoryAllocationReport.invoke(0L, ((AtomicLong) entry3.getValue()).get(), 0L, null, (StackTraceElement) entry3.getKey());
            }
        }

        private static void reportByStacktrace(MemoryUtil.MemoryAllocationReport memoryAllocationReport, boolean z) {
            if (z) {
                HashMap hashMap = new HashMap();
                for (Allocation allocation : ALLOCATIONS.keySet()) {
                    StackTraceElement[] elements = allocation.getElements();
                    if (elements != null) {
                        aggregate(new AllocationKey(elements), allocation.size, (Map) hashMap.computeIfAbsent(Long.valueOf(allocation.threadId), l -> {
                            return new HashMap();
                        }));
                    }
                }
                for (Map.Entry entry : hashMap.entrySet()) {
                    long longValue = ((Long) entry.getKey()).longValue();
                    for (Map.Entry entry2 : ((Map) entry.getValue()).entrySet()) {
                        memoryAllocationReport.invoke(0L, ((AtomicLong) entry2.getValue()).get(), longValue, THREADS.get(Long.valueOf(longValue)), ((AllocationKey) entry2.getKey()).elements);
                    }
                }
                return;
            }
            HashMap hashMap2 = new HashMap();
            for (Allocation allocation2 : ALLOCATIONS.keySet()) {
                StackTraceElement[] elements2 = allocation2.getElements();
                if (elements2 != null) {
                    aggregate(new AllocationKey(elements2), allocation2.size, hashMap2);
                }
            }
            for (Map.Entry entry3 : hashMap2.entrySet()) {
                memoryAllocationReport.invoke(0L, ((AtomicLong) entry3.getValue()).get(), 0L, null, ((AllocationKey) entry3.getKey()).elements);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: infinitode-2.jar:org/lwjgl/system/MemoryManage$DebugAllocator$AllocationKey.class */
        public static class AllocationKey {
            final StackTraceElement[] elements;

            AllocationKey(StackTraceElement[] stackTraceElementArr) {
                this.elements = stackTraceElementArr;
            }

            public boolean equals(Object obj) {
                return this == obj || Arrays.equals(this.elements, ((AllocationKey) obj).elements);
            }

            public int hashCode() {
                return Arrays.hashCode(this.elements);
            }
        }
    }
}
