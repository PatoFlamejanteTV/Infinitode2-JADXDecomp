package org.lwjgl.system.windows;

import org.lwjgl.system.APIUtil;
import org.lwjgl.system.Checks;
import org.lwjgl.system.JNI;
import org.lwjgl.system.Library;
import org.lwjgl.system.NativeType;
import org.lwjgl.system.SharedLibrary;

/* loaded from: infinitode-2.jar:org/lwjgl/system/windows/Kernel32.class */
public class Kernel32 {
    private static final SharedLibrary KERNEL32 = Library.loadNative(Kernel32.class, "org.lwjgl", "kernel32");

    /* loaded from: infinitode-2.jar:org/lwjgl/system/windows/Kernel32$Functions.class */
    public static final class Functions {
        public static final long GetCurrentProcess = APIUtil.apiGetFunctionAddress(Kernel32.KERNEL32, "GetCurrentProcess");
        public static final long GetCurrentProcessId = APIUtil.apiGetFunctionAddress(Kernel32.KERNEL32, "GetCurrentProcessId");
        public static final long GetProcessId = APIUtil.apiGetFunctionAddress(Kernel32.KERNEL32, "GetProcessId");
        public static final long GetCurrentThread = APIUtil.apiGetFunctionAddress(Kernel32.KERNEL32, "GetCurrentThread");
        public static final long GetCurrentThreadId = APIUtil.apiGetFunctionAddress(Kernel32.KERNEL32, "GetCurrentThreadId");
        public static final long GetThreadId = APIUtil.apiGetFunctionAddressOptional(Kernel32.KERNEL32, "GetThreadId");
        public static final long GetProcessIdOfThread = APIUtil.apiGetFunctionAddressOptional(Kernel32.KERNEL32, "GetProcessIdOfThread");
        public static final long GetCurrentProcessorNumber = APIUtil.apiGetFunctionAddressOptional(Kernel32.KERNEL32, "GetCurrentProcessorNumber");

        private Functions() {
        }
    }

    public static SharedLibrary getLibrary() {
        return KERNEL32;
    }

    protected Kernel32() {
        throw new UnsupportedOperationException();
    }

    @NativeType("HANDLE")
    public static long GetCurrentProcess() {
        return JNI.callP(Functions.GetCurrentProcess);
    }

    @NativeType("DWORD")
    public static int GetCurrentProcessId() {
        return JNI.callI(Functions.GetCurrentProcessId);
    }

    @NativeType("DWORD")
    public static int GetProcessId(@NativeType("HANDLE") long j) {
        long j2 = Functions.GetProcessId;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("HANDLE")
    public static long GetCurrentThread() {
        return JNI.callP(Functions.GetCurrentThread);
    }

    @NativeType("DWORD")
    public static int GetCurrentThreadId() {
        return JNI.callI(Functions.GetCurrentThreadId);
    }

    @NativeType("DWORD")
    public static int GetThreadId(@NativeType("HANDLE") long j) {
        long j2 = Functions.GetThreadId;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("DWORD")
    public static int GetProcessIdOfThread(@NativeType("HANDLE") long j) {
        long j2 = Functions.GetProcessIdOfThread;
        if (Checks.CHECKS) {
            Checks.check(j2);
            Checks.check(j);
        }
        return JNI.callPI(j, j2);
    }

    @NativeType("DWORD")
    public static int GetCurrentProcessorNumber() {
        long j = Functions.GetCurrentProcessorNumber;
        if (Checks.CHECKS) {
            Checks.check(j);
        }
        return JNI.callI(j);
    }
}
