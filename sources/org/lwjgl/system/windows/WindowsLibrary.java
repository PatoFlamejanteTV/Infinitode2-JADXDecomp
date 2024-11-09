package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import org.lwjgl.system.Library;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WindowsLibrary.class */
public class WindowsLibrary extends SharedLibrary.Default {
    public static final long HINSTANCE;

    static {
        MemoryStack stackPush = MemoryStack.stackPush();
        RuntimeException runtimeException = null;
        try {
            try {
                long GetModuleHandle = WinBase.GetModuleHandle(stackPush.UTF16(Library.JNI_LIBRARY_NAME));
                HINSTANCE = GetModuleHandle;
                if (GetModuleHandle == 0) {
                    runtimeException = new RuntimeException("Failed to retrieve LWJGL module handle.");
                    throw runtimeException;
                }
                if (stackPush != null) {
                    if (0 == 0) {
                        stackPush.close();
                        return;
                    }
                    try {
                        stackPush.close();
                    } catch (Throwable th) {
                        runtimeException.addSuppressed(th);
                    }
                }
            } catch (Throwable th2) {
                throw th2;
            }
        } catch (Throwable th3) {
            if (stackPush != null) {
                if (0 != 0) {
                    try {
                        stackPush.close();
                    } catch (Throwable th4) {
                        runtimeException.addSuppressed(th4);
                    }
                } else {
                    stackPush.close();
                }
            }
            throw th3;
        }
    }

    public WindowsLibrary(String str) {
        this(str, loadLibrary(str));
    }

    public WindowsLibrary(String str, long j) {
        super(str, j);
    }

    private static long loadLibrary(String str) {
        MemoryStack stackPush = MemoryStack.stackPush();
        try {
            try {
                long LoadLibrary = WinBase.LoadLibrary(stackPush.UTF16(str));
                if (stackPush != null) {
                    if (r10 != null) {
                        try {
                            stackPush.close();
                        } catch (Throwable th) {
                            r10.addSuppressed(th);
                        }
                    } else {
                        stackPush.close();
                    }
                }
                if (LoadLibrary == 0) {
                    throw new UnsatisfiedLinkError("Failed to load library: " + str + " (error code = " + WinBase.getLastError() + ")");
                }
                return LoadLibrary;
            } finally {
                r10 = null;
            }
        } catch (Throwable th2) {
            if (stackPush != null) {
                if (r10 != null) {
                    try {
                        stackPush.close();
                    } catch (Throwable th3) {
                        r10.addSuppressed(th3);
                    }
                } else {
                    stackPush.close();
                }
            }
            throw th2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001e, code lost:            if (r0 != 0) goto L8;     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0021, code lost:            r0 = null;     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002a, code lost:            r5 = r0;        r0 = r6;     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0030, code lost:            return r5;     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x0025, code lost:            r0 = org.lwjgl.system.MemoryUtil.memUTF16(r6, r0);     */
    @Override // org.lwjgl.system.SharedLibrary
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getPath() {
        /*
            r4 = this;
            r0 = 256(0x100, float:3.59E-43)
            r5 = r0
            r0 = 256(0x100, float:3.59E-43)
            java.nio.ByteBuffer r0 = org.lwjgl.system.MemoryUtil.memAlloc(r0)
            r6 = r0
        Lb:
            r0 = r4
            long r0 = r0.address()     // Catch: java.lang.Throwable -> L4f
            r1 = r6
            int r0 = org.lwjgl.system.windows.WinBase.GetModuleFileName(r0, r1)     // Catch: java.lang.Throwable -> L4f
            r7 = r0
            int r0 = org.lwjgl.system.windows.WinBase.getLastError()     // Catch: java.lang.Throwable -> L4f
            r1 = r0
            r8 = r1
            if (r0 != 0) goto L31
            r0 = r7
            if (r0 != 0) goto L25
            r0 = 0
            goto L2a
        L25:
            r0 = r6
            r1 = r7
            java.lang.String r0 = org.lwjgl.system.MemoryUtil.memUTF16(r0, r1)     // Catch: java.lang.Throwable -> L4f
        L2a:
            r5 = r0
            r0 = r6
            org.lwjgl.system.MemoryUtil.memFree(r0)
            r0 = r5
            return r0
        L31:
            r0 = r8
            r1 = 122(0x7a, float:1.71E-43)
            if (r0 == r1) goto L40
            r0 = 0
            r5 = r0
            r0 = r6
            org.lwjgl.system.MemoryUtil.memFree(r0)
            r0 = r5
            return r0
        L40:
            r0 = r6
            r1 = r5
            r2 = 3
            int r1 = r1 * r2
            r2 = 2
            int r1 = r1 / r2
            r2 = r1
            r5 = r2
            java.nio.ByteBuffer r0 = org.lwjgl.system.MemoryUtil.memRealloc(r0, r1)     // Catch: java.lang.Throwable -> L4f
            r6 = r0
            goto Lb
        L4f:
            r5 = move-exception
            r0 = r6
            org.lwjgl.system.MemoryUtil.memFree(r0)
            r0 = r5
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.lwjgl.system.windows.WindowsLibrary.getPath():java.lang.String");
    }

    @Override // org.lwjgl.system.FunctionProvider
    public long getFunctionAddress(ByteBuffer byteBuffer) {
        return WinBase.GetProcAddress(address(), byteBuffer);
    }

    @Override // org.lwjgl.system.NativeResource
    public void free() {
        if (!WinBase.FreeLibrary(address())) {
            WindowsUtil.windowsThrowException("Failed to unload library: " + getName());
        }
    }
}
