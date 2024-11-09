package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUserCallback.class */
public interface SteamUserCallback {
    void onAuthSessionTicket(SteamAuthTicket steamAuthTicket, SteamResult steamResult);

    void onValidateAuthTicket(SteamID steamID, SteamAuth.AuthSessionResponse authSessionResponse, SteamID steamID2);

    void onMicroTxnAuthorization(int i, long j, boolean z);

    void onEncryptedAppTicket(SteamResult steamResult);
}
