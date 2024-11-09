package org.lwjgl.system.linux;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.SharedLibrary;
import org.lwjgl.system.SharedLibraryUtil;

/* loaded from: infinitode-2.jar:org/lwjgl/system/linux/LinuxLibrary.class */
public class LinuxLibrary extends SharedLibrary.Default {
    public LinuxLibrary(String str) {
        this(str, loadLibrary(str));
    }

    public LinuxLibrary(String str, long j) {
        super(str, j);
    }

    private static long loadLibrary(String str) {
        MemoryStack stackPush = MemoryStack.stackPush();
        try {
            try {
                long dlopen = DynamicLinkLoader.dlopen(stackPush.UTF8(str), 1);
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
                if (dlopen == 0) {
                    throw new UnsatisfiedLinkError("Failed to dynamically load library: " + str + "(error = " + DynamicLinkLoader.dlerror() + ")");
                }
                return dlopen;
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

    @Override // org.lwjgl.system.SharedLibrary
    public String getPath() {
        return SharedLibraryUtil.getLibraryPath(address());
    }

    @Override // org.lwjgl.system.FunctionProvider
    public long getFunctionAddress(ByteBuffer byteBuffer) {
        return DynamicLinkLoader.dlsym(address(), byteBuffer);
    }

    @Override // org.lwjgl.system.NativeResource
    public void free() {
        DynamicLinkLoader.dlclose(address());
    }
}
