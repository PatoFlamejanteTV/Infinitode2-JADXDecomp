package org.lwjgl.system;

import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryManage;
import org.lwjgl.system.jni.JNINativeInterface;
import org.lwjgl.system.libffi.FFICIF;
import org.lwjgl.system.libffi.FFIClosure;
import org.lwjgl.system.libffi.LibFFI;

/* loaded from: infinitode-2.jar:org/lwjgl/system/Callback.class */
public abstract class Callback implements NativeResource, Pointer {
    private static final boolean DEBUG_ALLOCATOR = Configuration.DEBUG_MEMORY_ALLOCATOR.get(Boolean.FALSE).booleanValue();
    private static final ClosureRegistry CLOSURE_REGISTRY;
    private static final long CALLBACK_HANDLER;
    private long address;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: infinitode-2.jar:org/lwjgl/system/Callback$ClosureRegistry.class */
    public interface ClosureRegistry {
        void put(long j, FFIClosure fFIClosure);

        FFIClosure get(long j);

        FFIClosure remove(long j);
    }

    private static native long getCallbackHandler(Method method);

    static {
        MemoryStack stackPush = MemoryStack.stackPush();
        Throwable th = null;
        try {
            PointerBuffer mallocPointer = stackPush.mallocPointer(1);
            FFIClosure ffi_closure_alloc = LibFFI.ffi_closure_alloc(FFIClosure.SIZEOF, mallocPointer);
            if (ffi_closure_alloc == null) {
                throw new OutOfMemoryError();
            }
            if (mallocPointer.get(0) == ffi_closure_alloc.address()) {
                APIUtil.apiLog("Closure Registry: simple");
                CLOSURE_REGISTRY = new ClosureRegistry() { // from class: org.lwjgl.system.Callback.1
                    @Override // org.lwjgl.system.Callback.ClosureRegistry
                    public final void put(long j, FFIClosure fFIClosure) {
                    }

                    @Override // org.lwjgl.system.Callback.ClosureRegistry
                    public final FFIClosure get(long j) {
                        return FFIClosure.create(j);
                    }

                    @Override // org.lwjgl.system.Callback.ClosureRegistry
                    public final FFIClosure remove(long j) {
                        return get(j);
                    }
                };
            } else {
                APIUtil.apiLog("Closure Registry: ConcurrentHashMap");
                CLOSURE_REGISTRY = new ClosureRegistry() { // from class: org.lwjgl.system.Callback.2
                    private final ConcurrentHashMap<Long, FFIClosure> map = new ConcurrentHashMap<>();

                    @Override // org.lwjgl.system.Callback.ClosureRegistry
                    public final void put(long j, FFIClosure fFIClosure) {
                        this.map.put(Long.valueOf(j), fFIClosure);
                    }

                    @Override // org.lwjgl.system.Callback.ClosureRegistry
                    public final FFIClosure get(long j) {
                        return this.map.get(Long.valueOf(j));
                    }

                    @Override // org.lwjgl.system.Callback.ClosureRegistry
                    public final FFIClosure remove(long j) {
                        return this.map.remove(Long.valueOf(j));
                    }
                };
            }
            LibFFI.ffi_closure_free(ffi_closure_alloc);
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    stackPush.close();
                }
            }
            try {
                CALLBACK_HANDLER = getCallbackHandler(CallbackI.class.getDeclaredMethod("callback", Long.TYPE, Long.TYPE));
                MemoryUtil.getAllocator();
            } catch (Exception e) {
                throw new IllegalStateException("Failed to initialize the native callback handler.", e);
            }
        } catch (Throwable th3) {
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th4) {
                        th.addSuppressed(th4);
                    }
                } else {
                    stackPush.close();
                }
            }
            throw th3;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Callback(FFICIF fficif) {
        this.address = create(fficif, this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Callback(long j) {
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        this.address = j;
    }

    @Override // org.lwjgl.system.Pointer
    public long address() {
        return this.address;
    }

    @Override // org.lwjgl.system.NativeResource
    public void free() {
        free(address());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long create(FFICIF fficif, Object obj) {
        MemoryStack stackPush = MemoryStack.stackPush();
        Throwable th = null;
        try {
            PointerBuffer mallocPointer = stackPush.mallocPointer(1);
            FFIClosure ffi_closure_alloc = LibFFI.ffi_closure_alloc(FFIClosure.SIZEOF, mallocPointer);
            if (ffi_closure_alloc == null) {
                throw new OutOfMemoryError();
            }
            long j = mallocPointer.get(0);
            if (DEBUG_ALLOCATOR) {
                MemoryManage.DebugAllocator.track(j, FFIClosure.SIZEOF);
            }
            long NewGlobalRef = JNINativeInterface.NewGlobalRef(obj);
            if (LibFFI.ffi_prep_closure_loc(ffi_closure_alloc, fficif, CALLBACK_HANDLER, NewGlobalRef, j) == 0) {
                CLOSURE_REGISTRY.put(j, ffi_closure_alloc);
                return j;
            }
            JNINativeInterface.DeleteGlobalRef(NewGlobalRef);
            LibFFI.ffi_closure_free(ffi_closure_alloc);
            throw new RuntimeException("Failed to prepare the libffi closure");
        } finally {
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                } else {
                    stackPush.close();
                }
            }
        }
    }

    public static <T extends CallbackI> T get(long j) {
        return (T) MemoryUtil.memGlobalRefToObject(CLOSURE_REGISTRY.get(j).user_data());
    }

    public static <T extends CallbackI> T getSafe(long j) {
        if (j == 0) {
            return null;
        }
        return (T) get(j);
    }

    public static void free(long j) {
        if (DEBUG_ALLOCATOR) {
            MemoryManage.DebugAllocator.untrack(j);
        }
        FFIClosure remove = CLOSURE_REGISTRY.remove(j);
        JNINativeInterface.DeleteGlobalRef(remove.user_data());
        LibFFI.ffi_closure_free(remove);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof Callback) && this.address == ((Callback) obj).address();
    }

    public int hashCode() {
        return (int) (this.address ^ (this.address >>> 32));
    }

    public String toString() {
        return String.format("%s pointer [0x%X]", getClass().getSimpleName(), Long.valueOf(this.address));
    }
}
