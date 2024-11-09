package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserCallbackAdapter.class */
class SteamUserCallbackAdapter extends SteamCallbackAdapter<SteamUserCallback> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SteamUserCallbackAdapter(SteamUserCallback steamUserCallback) {
        super(steamUserCallback);
    }

    void onAuthSessionTicket(long j, int i) {
        ((SteamUserCallback) this.callback).onAuthSessionTicket(new SteamAuthTicket(j), SteamResult.byValue(i));
    }

    void onValidateAuthTicket(long j, int i, long j2) {
        ((SteamUserCallback) this.callback).onValidateAuthTicket(new SteamID(j), SteamAuth.AuthSessionResponse.byOrdinal(i), new SteamID(j2));
    }

    void onMicroTxnAuthorization(int i, long j, boolean z) {
        ((SteamUserCallback) this.callback).onMicroTxnAuthorization(i, j, z);
    }

    void onEncryptedAppTicket(int i) {
        ((SteamUserCallback) this.callback).onEncryptedAppTicket(SteamResult.byValue(i));
    }
}
