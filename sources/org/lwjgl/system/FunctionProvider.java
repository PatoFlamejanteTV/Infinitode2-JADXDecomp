package org.lwjgl.system;

import java.nio.ByteBuffer;

@FunctionalInterface
/* loaded from: infinitode-2.jar:org/lwjgl/system/FunctionProvider.class */
public interface FunctionProvider {
    long getFunctionAddress(ByteBuffer byteBuffer);

    default long getFunctionAddress(CharSequence charSequence) {
        MemoryStack stackPush = MemoryStack.stackPush();
        Throwable th = null;
        try {
            long functionAddress = getFunctionAddress(stackPush.ASCII(charSequence));
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
            return functionAddress;
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
}
