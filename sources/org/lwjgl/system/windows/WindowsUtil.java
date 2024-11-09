package org.lwjgl.system.windows;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/WindowsUtil.class */
public final class WindowsUtil {
    private WindowsUtil() {
    }

    public static void windowsThrowException(String str) {
        throw new RuntimeException(str + " (error code = " + WinBase.getLastError() + ")");
    }
}
