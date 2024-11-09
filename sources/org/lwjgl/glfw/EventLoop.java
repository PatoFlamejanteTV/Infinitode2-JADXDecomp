package org.lwjgl.glfw;

import org.lwjgl.system.Configuration;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Platform;
import org.lwjgl.system.macosx.LibC;
import org.lwjgl.system.macosx.ObjCRuntime;

/* loaded from: infinitode-2.jar:org/lwjgl/glfw/EventLoop.class */
final class EventLoop {
    private EventLoop() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void check() {
        if (Platform.get() == Platform.MACOSX && !isMainThread()) {
            throw new IllegalStateException(isJavaStartedOnFirstThread() ? "GLFW may only be used on the main thread. This check may be disabled with Configuration.GLFW_CHECK_THREAD0." : "GLFW may only be used on the main thread and that thread must be the first thread in the process. Please run the JVM with -XstartOnFirstThread. This check may be disabled with Configuration.GLFW_CHECK_THREAD0.");
        }
    }

    private static boolean isMainThread() {
        if (!Configuration.GLFW_CHECK_THREAD0.get(Boolean.TRUE).booleanValue() || Configuration.GLFW_LIBRARY_NAME.get("").contains("glfw_async")) {
            return true;
        }
        long functionAddress = ObjCRuntime.getLibrary().getFunctionAddress("objc_msgSend");
        return JNI.invokePPZ(JNI.invokePPP(ObjCRuntime.objc_getClass("NSThread"), ObjCRuntime.sel_getUid("currentThread"), functionAddress), ObjCRuntime.sel_getUid("isMainThread"), functionAddress);
    }

    private static boolean isJavaStartedOnFirstThread() {
        return "1".equals(System.getenv().get("JAVA_STARTED_ON_FIRST_THREAD_" + LibC.getpid()));
    }
}
