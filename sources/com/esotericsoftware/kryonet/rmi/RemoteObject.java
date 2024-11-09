package com.esotericsoftware.kryonet.rmi;

import com.esotericsoftware.kryonet.Connection;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/rmi/RemoteObject.class */
public interface RemoteObject {
    void setResponseTimeout(int i);

    void setNonBlocking(boolean z);

    void setTransmitReturnValue(boolean z);

    void setTransmitExceptions(boolean z);

    void setUDP(boolean z);

    void setRemoteToString(boolean z);

    Object waitForLastResponse();

    Object hasLastResponse();

    byte getLastResponseID();

    Object waitForResponse(byte b2);

    Object hasResponse(byte b2);

    void close();

    Connection getConnection();
}
