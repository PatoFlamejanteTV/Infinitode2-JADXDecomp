package com.esotericsoftware.kryonet;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/FrameworkMessage.class */
public interface FrameworkMessage {
    public static final KeepAlive keepAlive = new KeepAlive();

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/FrameworkMessage$DiscoverHost.class */
    public static class DiscoverHost implements FrameworkMessage {
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/FrameworkMessage$KeepAlive.class */
    public static class KeepAlive implements FrameworkMessage {
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/FrameworkMessage$Ping.class */
    public static class Ping implements FrameworkMessage {
        public int id;
        public boolean isReply;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/FrameworkMessage$RegisterTCP.class */
    public static class RegisterTCP implements FrameworkMessage {
        public int connectionID;
    }

    /* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/FrameworkMessage$RegisterUDP.class */
    public static class RegisterUDP implements FrameworkMessage {
        public int connectionID;
    }
}
