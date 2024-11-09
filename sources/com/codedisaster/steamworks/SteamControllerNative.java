package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamControllerNative.class */
final class SteamControllerNative {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean init();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean shutdown();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void runFrame();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getConnectedControllers(long[] jArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native boolean showBindingPanel(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getActionSetHandle(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void activateActionSet(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getCurrentActionSet(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getDigitalActionHandle(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void getDigitalActionData(long j, long j2, SteamControllerDigitalActionData steamControllerDigitalActionData);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getDigitalActionOrigins(long j, long j2, long j3, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getAnalogActionHandle(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void getAnalogActionData(long j, long j2, SteamControllerAnalogActionData steamControllerAnalogActionData);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getAnalogActionOrigins(long j, long j2, long j3, int[] iArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void stopAnalogActionMomentum(long j, long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void triggerHapticPulse(long j, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void triggerRepeatedHapticPulse(long j, int i, int i2, int i3, int i4, int i5);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void triggerVibration(long j, short s, short s2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setLEDColor(long j, byte b2, byte b3, byte b4, int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getGamepadIndexForController(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native long getControllerForGamepadIndex(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void getMotionData(long j, float[] fArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getStringForActionOrigin(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native String getGlyphForActionOrigin(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native int getInputTypeForHandle(long j);

    SteamControllerNative() {
    }
}
