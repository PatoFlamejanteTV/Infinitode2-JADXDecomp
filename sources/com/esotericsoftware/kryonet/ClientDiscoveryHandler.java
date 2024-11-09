package com.esotericsoftware.kryonet;

import java.net.DatagramPacket;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/ClientDiscoveryHandler.class */
public interface ClientDiscoveryHandler {
    default DatagramPacket onRequestNewDatagramPacket() {
        return new DatagramPacket(new byte[0], 0);
    }

    default void onDiscoveredHost(DatagramPacket datagramPacket) {
    }

    default void onFinally() {
    }
}
