package com.esotericsoftware.kryonet;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/ServerDiscoveryHandler.class */
public interface ServerDiscoveryHandler {
    public static final ByteBuffer emptyBuffer = ByteBuffer.allocate(0);

    default boolean onDiscoverHost(DatagramChannel datagramChannel, InetSocketAddress inetSocketAddress) {
        datagramChannel.send(emptyBuffer, inetSocketAddress);
        return true;
    }
}
