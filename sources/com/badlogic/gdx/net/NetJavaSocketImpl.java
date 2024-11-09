package com.badlogic.gdx.net;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.utils.GdxRuntimeException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/net/NetJavaSocketImpl.class */
public class NetJavaSocketImpl implements Socket {
    private java.net.Socket socket;

    public NetJavaSocketImpl(Net.Protocol protocol, String str, int i, SocketHints socketHints) {
        try {
            this.socket = new java.net.Socket();
            applyHints(socketHints);
            InetSocketAddress inetSocketAddress = new InetSocketAddress(str, i);
            if (socketHints != null) {
                this.socket.connect(inetSocketAddress, socketHints.connectTimeout);
            } else {
                this.socket.connect(inetSocketAddress);
            }
        } catch (Exception e) {
            throw new GdxRuntimeException("Error making a socket connection to " + str + ":" + i, e);
        }
    }

    public NetJavaSocketImpl(java.net.Socket socket, SocketHints socketHints) {
        this.socket = socket;
        applyHints(socketHints);
    }

    private void applyHints(SocketHints socketHints) {
        if (socketHints != null) {
            try {
                this.socket.setPerformancePreferences(socketHints.performancePrefConnectionTime, socketHints.performancePrefLatency, socketHints.performancePrefBandwidth);
                this.socket.setTrafficClass(socketHints.trafficClass);
                this.socket.setTcpNoDelay(socketHints.tcpNoDelay);
                this.socket.setKeepAlive(socketHints.keepAlive);
                this.socket.setSendBufferSize(socketHints.sendBufferSize);
                this.socket.setReceiveBufferSize(socketHints.receiveBufferSize);
                this.socket.setSoLinger(socketHints.linger, socketHints.lingerDuration);
                this.socket.setSoTimeout(socketHints.socketTimeout);
            } catch (Exception e) {
                throw new GdxRuntimeException("Error setting socket hints.", e);
            }
        }
    }

    @Override // com.badlogic.gdx.net.Socket
    public boolean isConnected() {
        if (this.socket != null) {
            return this.socket.isConnected();
        }
        return false;
    }

    @Override // com.badlogic.gdx.net.Socket
    public InputStream getInputStream() {
        try {
            return this.socket.getInputStream();
        } catch (Exception e) {
            throw new GdxRuntimeException("Error getting input stream from socket.", e);
        }
    }

    @Override // com.badlogic.gdx.net.Socket
    public OutputStream getOutputStream() {
        try {
            return this.socket.getOutputStream();
        } catch (Exception e) {
            throw new GdxRuntimeException("Error getting output stream from socket.", e);
        }
    }

    @Override // com.badlogic.gdx.net.Socket
    public String getRemoteAddress() {
        return this.socket.getRemoteSocketAddress().toString();
    }

    @Override // com.badlogic.gdx.utils.Disposable
    public void dispose() {
        if (this.socket != null) {
            try {
                this.socket.close();
                this.socket = null;
            } catch (Exception e) {
                throw new GdxRuntimeException("Error closing socket.", e);
            }
        }
    }
}
