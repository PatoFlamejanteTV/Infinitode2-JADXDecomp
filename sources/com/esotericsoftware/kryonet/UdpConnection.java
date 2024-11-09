package com.esotericsoftware.kryonet;

import com.esotericsoftware.kryonet.serialization.Serialization;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;

/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/UdpConnection.class */
public class UdpConnection {
    public static boolean androidFixDisabled = false;
    InetSocketAddress connectedAddress;
    DatagramChannel datagramChannel;
    final ByteBuffer readBuffer;
    final ByteBuffer writeBuffer;
    private final Serialization serialization;
    private SelectionKey selectionKey;
    private long lastCommunicationTime;
    int keepAliveMillis = 19000;
    private final Object writeLock = new Object();

    public UdpConnection(Serialization serialization, int i) {
        this.serialization = serialization;
        this.readBuffer = ByteBuffer.allocate(i);
        this.writeBuffer = ByteBuffer.allocateDirect(i);
    }

    public void bind(Selector selector, InetSocketAddress inetSocketAddress) {
        close();
        this.readBuffer.clear();
        this.writeBuffer.clear();
        try {
            this.datagramChannel = selector.provider().openDatagramChannel();
            this.datagramChannel.socket().bind(inetSocketAddress);
            this.datagramChannel.configureBlocking(false);
            this.selectionKey = this.datagramChannel.register(selector, 1);
            this.lastCommunicationTime = System.currentTimeMillis();
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    public void connect(Selector selector, InetSocketAddress inetSocketAddress) {
        close();
        this.readBuffer.clear();
        this.writeBuffer.clear();
        try {
            this.datagramChannel = selector.provider().openDatagramChannel();
            this.datagramChannel.socket().bind(null);
            this.datagramChannel.socket().connect(inetSocketAddress);
            this.datagramChannel.configureBlocking(false);
            this.selectionKey = this.datagramChannel.register(selector, 1);
            this.lastCommunicationTime = System.currentTimeMillis();
            this.connectedAddress = inetSocketAddress;
        } catch (IOException e) {
            close();
            IOException iOException = new IOException("Unable to connect to: " + inetSocketAddress);
            iOException.initCause(e);
            throw iOException;
        }
    }

    public InetSocketAddress readFromAddress() {
        DatagramChannel datagramChannel = this.datagramChannel;
        if (datagramChannel == null) {
            throw new SocketException("Connection is closed.");
        }
        this.lastCommunicationTime = System.currentTimeMillis();
        if (androidFixDisabled || !datagramChannel.isConnected()) {
            return (InetSocketAddress) datagramChannel.receive(this.readBuffer);
        }
        datagramChannel.read(this.readBuffer);
        return this.connectedAddress;
    }

    public Object readObject(Connection connection) {
        this.readBuffer.flip();
        try {
            try {
                Object read = this.serialization.read(connection, this.readBuffer);
                if (this.readBuffer.hasRemaining()) {
                    throw new KryoNetException("Incorrect number of bytes (" + this.readBuffer.remaining() + " remaining) used to deserialize object: " + read);
                }
                return read;
            } catch (Exception e) {
                throw new KryoNetException("Error during deserialization.", e);
            }
        } finally {
            this.readBuffer.clear();
        }
    }

    public int send(Connection connection, Object obj, SocketAddress socketAddress) {
        int i;
        DatagramChannel datagramChannel = this.datagramChannel;
        if (datagramChannel == null) {
            throw new SocketException("Connection is closed.");
        }
        synchronized (this.writeLock) {
            try {
                try {
                    this.serialization.write(connection, this.writeBuffer, obj);
                    this.writeBuffer.flip();
                    int limit = this.writeBuffer.limit();
                    datagramChannel.send(this.writeBuffer, socketAddress);
                    this.lastCommunicationTime = System.currentTimeMillis();
                    i = !this.writeBuffer.hasRemaining() ? limit : -1;
                } finally {
                    this.writeBuffer.clear();
                }
            } catch (Exception e) {
                throw new KryoNetException("Error serializing object of type: " + obj.getClass().getName(), e);
            }
        }
        return i;
    }

    public void close() {
        this.connectedAddress = null;
        try {
            if (this.datagramChannel != null) {
                this.datagramChannel.close();
                this.datagramChannel = null;
                if (this.selectionKey != null) {
                    this.selectionKey.selector().wakeup();
                }
            }
        } catch (IOException e) {
            if (Log.DEBUG) {
                Log.debug("kryonet", "Unable to close UDP connection.", e);
            }
        }
    }

    public boolean needsKeepAlive(long j) {
        return this.connectedAddress != null && this.keepAliveMillis > 0 && j - this.lastCommunicationTime > ((long) this.keepAliveMillis);
    }
}
