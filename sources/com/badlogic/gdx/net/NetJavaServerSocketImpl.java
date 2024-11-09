package com.badlogic.gdx.net;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.net.InetSocketAddress;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/net/NetJavaServerSocketImpl.class */
public class NetJavaServerSocketImpl implements ServerSocket {
    private Net.Protocol protocol;
    private java.net.ServerSocket server;

    public NetJavaServerSocketImpl(Net.Protocol protocol, int i, ServerSocketHints serverSocketHints) {
        this(protocol, null, i, serverSocketHints);
    }

    public NetJavaServerSocketImpl(Net.Protocol protocol, String str, int i, ServerSocketHints serverSocketHints) {
        InetSocketAddress inetSocketAddress;
        this.protocol = protocol;
        try {
            this.server = new java.net.ServerSocket();
            if (serverSocketHints != null) {
                this.server.setPerformancePreferences(serverSocketHints.performancePrefConnectionTime, serverSocketHints.performancePrefLatency, serverSocketHints.performancePrefBandwidth);
                this.server.setReuseAddress(serverSocketHints.reuseAddress);
                this.server.setSoTimeout(serverSocketHints.acceptTimeout);
                this.server.setReceiveBufferSize(serverSocketHints.receiveBufferSize);
            }
            if (str != null) {
                inetSocketAddress = new InetSocketAddress(str, i);
            } else {
                inetSocketAddress = new InetSocketAddress(i);
            }
            if (serverSocketHints != null) {
                this.server.bind(inetSocketAddress, serverSocketHints.backlog);
            } else {
                this.server.bind(inetSocketAddress);
            }
        } catch (Exception e) {
            throw new GdxRuntimeException("Cannot create a server socket at port " + i + ".", e);
        }
    }

    @Override // com.badlogic.gdx.net.ServerSocket
    public Net.Protocol getProtocol() {
        return this.protocol;
    }

    @Override // com.badlogic.gdx.net.ServerSocket
    public Socket accept(SocketHints socketHints) {
        try {
            return new NetJavaSocketImpl(this.server.accept(), socketHints);
        } catch (Exception e) {
            throw new GdxRuntimeException("Error accepting socket.", e);
        }
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.server != null) {
            try {
                this.server.close();
                this.server = null;
            } catch (Exception e) {
                throw new GdxRuntimeException("Error closing server.", e);
            }
        }
    }
}
