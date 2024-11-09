package com.codedisaster.steamworks;

import com.codedisaster.steamworks.SteamAuth;
import java.nio.ByteBuffer;

/* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUser.class */
public class SteamUser extends SteamInterface {
    @Override // com.codedisaster.steamworks.SteamInterface
    public /* bridge */ /* synthetic */ void dispose() {
        super.dispose();
    }

    /* loaded from: infinitode-2.jar:com/codedisaster/steamworks/SteamUser$VoiceResult.class */
    public enum VoiceResult {
        OK,
        NotInitialized,
        NotRecording,
        NoData,
        BufferTooSmall,
        DataCorrupted,
        Restricted,
        UnsupportedCodec,
        ReceiverOutOfDate,
        ReceiverDidNotAnswer;

        private static final VoiceResult[] values = values();

        static VoiceResult byOrdinal(int i) {
            return values[i];
        }
    }

    public SteamUser(SteamUserCallback steamUserCallback) {
        super(SteamUserNative.createCallback(new SteamUserCallbackAdapter(steamUserCallback)));
    }

    public SteamID getSteamID() {
        return new SteamID(SteamUserNative.getSteamID());
    }

    @Deprecated
    public int initiateGameConnection(ByteBuffer byteBuffer, SteamID steamID, int i, short s, boolean z) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int initiateGameConnection = SteamUserNative.initiateGameConnection(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), steamID.handle, i, s, z);
        if (initiateGameConnection > 0) {
            byteBuffer.limit(initiateGameConnection);
        }
        return initiateGameConnection;
    }

    @Deprecated
    public void terminateGameConnection(int i, short s) {
        SteamUserNative.terminateGameConnection(i, s);
    }

    public void startVoiceRecording() {
        SteamUserNative.startVoiceRecording();
    }

    public void stopVoiceRecording() {
        SteamUserNative.stopVoiceRecording();
    }

    public VoiceResult getAvailableVoice(int[] iArr) {
        return VoiceResult.byOrdinal(SteamUserNative.getAvailableVoice(iArr));
    }

    public VoiceResult getVoice(ByteBuffer byteBuffer, int[] iArr) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return VoiceResult.byOrdinal(SteamUserNative.getVoice(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), iArr));
    }

    public VoiceResult decompressVoice(ByteBuffer byteBuffer, ByteBuffer byteBuffer2, int[] iArr, int i) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        if (!byteBuffer2.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return VoiceResult.byOrdinal(SteamUserNative.decompressVoice(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), byteBuffer2, byteBuffer2.position(), byteBuffer2.remaining(), iArr, i));
    }

    public int getVoiceOptimalSampleRate() {
        return SteamUserNative.getVoiceOptimalSampleRate();
    }

    public SteamAuthTicket getAuthSessionTicket(ByteBuffer byteBuffer, int[] iArr) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        int authSessionTicket = SteamUserNative.getAuthSessionTicket(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), iArr);
        if (authSessionTicket != 0) {
            byteBuffer.limit(iArr[0]);
        }
        return new SteamAuthTicket(authSessionTicket);
    }

    public SteamAuth.BeginAuthSessionResult beginAuthSession(ByteBuffer byteBuffer, SteamID steamID) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamAuth.BeginAuthSessionResult.byOrdinal(SteamUserNative.beginAuthSession(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), steamID.handle));
    }

    public void endAuthSession(SteamID steamID) {
        SteamUserNative.endAuthSession(steamID.handle);
    }

    public void cancelAuthTicket(SteamAuthTicket steamAuthTicket) {
        SteamUserNative.cancelAuthTicket((int) steamAuthTicket.handle);
    }

    public SteamAuth.UserHasLicenseForAppResult userHasLicenseForApp(SteamID steamID, int i) {
        return SteamAuth.UserHasLicenseForAppResult.byOrdinal(SteamUserNative.userHasLicenseForApp(steamID.handle, i));
    }

    public SteamAPICall requestEncryptedAppTicket(ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return new SteamAPICall(SteamUserNative.requestEncryptedAppTicket(this.callback, byteBuffer, byteBuffer.position(), byteBuffer.remaining()));
    }

    public boolean getEncryptedAppTicket(ByteBuffer byteBuffer, int[] iArr) {
        if (!byteBuffer.isDirect()) {
            throw new SteamException("Direct buffer required!");
        }
        return SteamUserNative.getEncryptedAppTicket(byteBuffer, byteBuffer.position(), byteBuffer.remaining(), iArr);
    }

    public boolean isBehindNAT() {
        return SteamUserNative.isBehindNAT();
    }

    public void advertiseGame(SteamID steamID, int i, short s) {
        SteamUserNative.advertiseGame(steamID.handle, i, s);
    }
}
