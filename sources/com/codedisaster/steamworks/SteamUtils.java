package com.codedisaster.steamworks;

import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUtils.class */
public class SteamUtils extends SteamInterface {
    private final SteamUtilsCallbackAdapter callbackAdapter;

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUtils$FloatingGamepadTextInputMode.class */
    public enum FloatingGamepadTextInputMode {
        ModeSingleLine,
        ModeMultipleLines,
        ModeEmail,
        ModeNumeric
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUtils$NotificationPosition.class */
    public enum NotificationPosition {
        TopLeft,
        TopRight,
        BottomLeft,
        BottomRight
    }

    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUtils$SteamAPICallFailure.class */
    public enum SteamAPICallFailure {
        None(-1),
        SteamGone(0),
        NetworkFailure(1),
        InvalidHandle(2),
        MismatchedCallback(3);

        private final int code;
        private static final SteamAPICallFailure[] values = values();

        SteamAPICallFailure(int i) {
            this.code = i;
        }

        static SteamAPICallFailure byValue(int i) {
            for (SteamAPICallFailure steamAPICallFailure : values) {
                if (steamAPICallFailure.code == i) {
                    return steamAPICallFailure;
                }
            }
            return None;
        }
    }

    public SteamUtils(SteamUtilsCallback steamUtilsCallback) {
        this.callbackAdapter = new SteamUtilsCallbackAdapter(steamUtilsCallback);
        setCallback(SteamUtilsNative.createCallback(this.callbackAdapter));
    }

    public int getSecondsSinceAppActive() {
        return SteamUtilsNative.getSecondsSinceAppActive();
    }

    public int getSecondsSinceComputerActive() {
        return SteamUtilsNative.getSecondsSinceComputerActive();
    }

    public SteamUniverse getConnectedUniverse() {
        return SteamUniverse.byValue(SteamUtilsNative.getConnectedUniverse());
    }

    public int getServerRealTime() {
        return SteamUtilsNative.getServerRealTime();
    }

    public int getImageWidth(int i) {
        return SteamUtilsNative.getImageWidth(i);
    }

    public int getImageHeight(int i) {
        return SteamUtilsNative.getImageHeight(i);
    }

    public boolean getImageSize(int i, int[] iArr) {
        return SteamUtilsNative.getImageSize(i, iArr);
    }

    public boolean getImageRGBA(int i, ByteBuffer byteBuffer) {
        checkBuffer(byteBuffer);
        return SteamUtilsNative.getImageRGBA(i, byteBuffer, byteBuffer.position(), byteBuffer.remaining());
    }

    public int getAppID() {
        return SteamUtilsNative.getAppID();
    }

    public void setOverlayNotificationPosition(NotificationPosition notificationPosition) {
        SteamUtilsNative.setOverlayNotificationPosition(notificationPosition.ordinal());
    }

    public boolean isAPICallCompleted(SteamAPICall steamAPICall, boolean[] zArr) {
        return SteamUtilsNative.isAPICallCompleted(steamAPICall.handle, zArr);
    }

    public SteamAPICallFailure getAPICallFailureReason(SteamAPICall steamAPICall) {
        return SteamAPICallFailure.byValue(SteamUtilsNative.getAPICallFailureReason(steamAPICall.handle));
    }

    public void setWarningMessageHook(SteamAPIWarningMessageHook steamAPIWarningMessageHook) {
        this.callbackAdapter.setWarningMessageHook(steamAPIWarningMessageHook);
        SteamUtilsNative.enableWarningMessageHook(this.callback, steamAPIWarningMessageHook != null);
    }

    public boolean isOverlayEnabled() {
        return SteamUtilsNative.isOverlayEnabled();
    }

    public boolean isSteamRunningOnSteamDeck() {
        return SteamUtilsNative.isSteamRunningOnSteamDeck();
    }

    public boolean showFloatingGamepadTextInput(FloatingGamepadTextInputMode floatingGamepadTextInputMode, int i, int i2, int i3, int i4) {
        return SteamUtilsNative.showFloatingGamepadTextInput(floatingGamepadTextInputMode.ordinal(), i, i2, i3, i4);
    }

    public boolean dismissFloatingGamepadTextInput() {
        return SteamUtilsNative.dismissFloatingGamepadTextInput();
    }
}
