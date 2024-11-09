package org.lwjgl.system;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:org/lwjgl/system/FunctionProviderLocal.class */
public interface FunctionProviderLocal extends FunctionProvider {
    long getFunctionAddress(long j, ByteBuffer byteBuffer);

    default long getFunctionAddress(long j, CharSequence charSequence) {
        MemoryStack stackPush = MemoryStack.stackPush();
        try {
            try {
                long functionAddress = getFunctionAddress(j, stackPush.ASCII(charSequence));
                if (stackPush != null) {
                    if (r11 != null) {
                        try {
                            stackPush.close();
                        } catch (Throwable th) {
                            r11.addSuppressed(th);
                        }
                    } else {
                        stackPush.close();
                    }
                }
                return functionAddress;
            } finally {
                r11 = null;
            }
        } catch (Throwable th2) {
            if (stackPush != null) {
                if (r11 != null) {
                    try {
                        stackPush.close();
                    } catch (Throwable th3) {
                        r11.addSuppressed(th3);
                    }
                } else {
                    stackPush.close();
                }
            }
            throw th2;
        }
    }
}
