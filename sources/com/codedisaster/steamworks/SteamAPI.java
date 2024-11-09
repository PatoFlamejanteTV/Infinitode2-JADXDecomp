package com.codedisaster.steamworks;

import java.io.PrintStream;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamAPI.class */
public class SteamAPI {
    private static boolean isRunning = false;
    private static boolean isNativeAPILoaded = false;

    private static native boolean nativeRestartAppIfNecessary(int i);

    public static native void releaseCurrentThreadMemory();

    private static native boolean nativeInit();

    private static native void nativeShutdown();

    public static native void runCallbacks();

    private static native boolean isSteamRunningNative();

    public static void loadLibraries() {
        loadLibraries(null);
    }

    public static void loadLibraries(String str) {
        if (isNativeAPILoaded) {
            return;
        }
        if (str == null && SteamSharedLibraryLoader.DEBUG) {
            SteamSharedLibraryLoader.loadLibrary("steam_api", SteamSharedLibraryLoader.getSdkRedistributableBinPath());
        } else {
            SteamSharedLibraryLoader.loadLibrary("steam_api", str);
        }
        SteamSharedLibraryLoader.loadLibrary("steamworks4j", str);
        isNativeAPILoaded = true;
    }

    public static void skipLoadLibraries() {
        isNativeAPILoaded = true;
    }

    public static boolean restartAppIfNecessary(int i) {
        if (!isNativeAPILoaded) {
            throw new SteamException("Native libraries not loaded.\nEnsure to call SteamAPI.loadLibraries() first!");
        }
        return nativeRestartAppIfNecessary(i);
    }

    public static boolean init() {
        if (!isNativeAPILoaded) {
            throw new SteamException("Native libraries not loaded.\nEnsure to call SteamAPI.loadLibraries() first!");
        }
        boolean nativeInit = nativeInit();
        isRunning = nativeInit;
        return nativeInit;
    }

    public static void shutdown() {
        isRunning = false;
        nativeShutdown();
    }

    public static boolean isSteamRunning() {
        return isSteamRunning(false);
    }

    public static boolean isSteamRunning(boolean z) {
        if (isRunning) {
            return !z || isSteamRunningNative();
        }
        return false;
    }

    public static void printDebugInfo(PrintStream printStream) {
        printStream.println("  Steam API initialized: " + isRunning);
        printStream.println("  Steam client active: " + isSteamRunning());
    }

    static boolean isIsNativeAPILoaded() {
        return isNativeAPILoaded;
    }
}
