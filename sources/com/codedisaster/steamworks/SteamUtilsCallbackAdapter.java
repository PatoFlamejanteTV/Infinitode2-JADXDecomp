package com.codedisaster.steamworks;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUtilsCallbackAdapter.class */
class SteamUtilsCallbackAdapter extends SteamCallbackAdapter<SteamUtilsCallback> {
    private SteamAPIWarningMessageHook messageHook;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamUtilsCallbackAdapter(SteamUtilsCallback steamUtilsCallback) {
        super(steamUtilsCallback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setWarningMessageHook(SteamAPIWarningMessageHook steamAPIWarningMessageHook) {
        this.messageHook = steamAPIWarningMessageHook;
    }

    void onWarningMessage(int i, String str) {
        if (this.messageHook != null) {
            this.messageHook.onWarningMessage(i, str);
        }
    }

    void onSteamShutdown() {
        ((SteamUtilsCallback) this.callback).onSteamShutdown();
    }
}
