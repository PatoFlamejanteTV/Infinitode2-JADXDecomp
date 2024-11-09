package com.esotericsoftware.kryonet;

import com.esotericsoftware.kryonet.serialization.Serialization;
import com.esotericsoftware.minlog.Log;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: infinitode-2.jar:com/esotericsoftware/kryonet/TcpConnection.class */
public class TcpConnection {
    SocketChannel socketChannel;
    final ByteBuffer readBuffer;
    final ByteBuffer writeBuffer;
    boolean bufferPositionFix;
    final Serialization serialization;
    private SelectionKey selectionKey;
    private volatile long lastWriteTime;
    private volatile long lastReadTime;
    private int currentObjectLength;
    int keepAliveMillis = 8000;
    int timeoutMillis = 12000;
    float idleThreshold = 0.1f;
    private final Object writeLock = new Object();

    public TcpConnection(Serialization serialization, int i, int i2) {
        this.serialization = serialization;
        this.writeBuffer = ByteBuffer.allocate(i);
        this.readBuffer = ByteBuffer.allocate(i2);
        this.readBuffer.flip();
    }

    public SelectionKey accept(Selector selector, SocketChannel socketChannel) {
        this.writeBuffer.clear();
        this.readBuffer.clear();
        this.readBuffer.flip();
        this.currentObjectLength = 0;
        try {
            this.socketChannel = socketChannel;
            socketChannel.configureBlocking(false);
            socketChannel.socket().setTcpNoDelay(true);
            this.selectionKey = socketChannel.register(selector, 1);
            if (Log.DEBUG) {
                Log.debug("kryonet", "Port " + socketChannel.socket().getLocalPort() + "/TCP connected to: " + socketChannel.socket().getRemoteSocketAddress());
            }
            long currentTimeMillis = System.currentTimeMillis();
            this.lastWriteTime = currentTimeMillis;
            this.lastReadTime = currentTimeMillis;
            return this.selectionKey;
        } catch (IOException e) {
            close();
            throw e;
        }
    }

    public void connect(Selector selector, SocketAddress socketAddress, int i) {
        close();
        this.writeBuffer.clear();
        this.readBuffer.clear();
        this.readBuffer.flip();
        this.currentObjectLength = 0;
        try {
            SocketChannel openSocketChannel = selector.provider().openSocketChannel();
            Socket socket = openSocketChannel.socket();
            socket.setTcpNoDelay(true);
            socket.connect(socketAddress, i);
            openSocketChannel.configureBlocking(false);
            this.socketChannel = openSocketChannel;
            this.selectionKey = openSocketChannel.register(selector, 1);
            this.selectionKey.attach(this);
            if (Log.DEBUG) {
                Log.debug("kryonet", "Port " + openSocketChannel.socket().getLocalPort() + "/TCP connected to: " + openSocketChannel.socket().getRemoteSocketAddress());
            }
            long currentTimeMillis = System.currentTimeMillis();
            this.lastWriteTime = currentTimeMillis;
            this.lastReadTime = currentTimeMillis;
        } catch (IOException e) {
            close();
            throw new IOException("Unable to connect to: " + socketAddress, e);
        }
    }

    public Object readObject(Connection connection) {
        SocketChannel socketChannel = this.socketChannel;
        if (socketChannel == null) {
            throw new SocketException("Connection is closed.");
        }
        if (this.currentObjectLength == 0) {
            int lengthLength = this.serialization.getLengthLength();
            if (this.readBuffer.remaining() < lengthLength) {
                this.readBuffer.compact();
                int read = socketChannel.read(this.readBuffer);
                this.readBuffer.flip();
                if (read == -1) {
                    throw new SocketException("Connection is closed.");
                }
                this.lastReadTime = System.currentTimeMillis();
                if (this.readBuffer.remaining() < lengthLength) {
                    return null;
                }
            }
            this.currentObjectLength = this.serialization.readLength(this.readBuffer);
            if (this.currentObjectLength <= 0) {
                throw new KryoNetException("Invalid object length: " + this.currentObjectLength);
            }
            if (this.currentObjectLength > this.readBuffer.capacity()) {
                throw new KryoNetException("Unable to read object larger than read buffer: " + this.currentObjectLength);
            }
        }
        int i = this.currentObjectLength;
        if (this.readBuffer.remaining() < i) {
            this.readBuffer.compact();
            int read2 = socketChannel.read(this.readBuffer);
            this.readBuffer.flip();
            if (read2 == -1) {
                throw new SocketException("Connection is closed.");
            }
            this.lastReadTime = System.currentTimeMillis();
            if (this.readBuffer.remaining() < i) {
                return null;
            }
        }
        this.currentObjectLength = 0;
        int position = this.readBuffer.position();
        int limit = this.readBuffer.limit();
        this.readBuffer.limit(position + i);
        try {
            Object read3 = this.serialization.read(connection, this.readBuffer);
            this.readBuffer.limit(limit);
            if (this.readBuffer.position() - position != i) {
                throw new KryoNetException("Incorrect number of bytes (" + ((position + i) - this.readBuffer.position()) + " remaining) used to deserialize object: " + read3);
            }
            return read3;
        } catch (Exception e) {
            throw new KryoNetException("Error during deserialization.", e);
        }
    }

    public void writeOperation() {
        synchronized (this.writeLock) {
            if (writeToSocket()) {
                this.selectionKey.interestOps(1);
            }
            this.lastWriteTime = System.currentTimeMillis();
        }
    }

    private boolean writeToSocket() {
        SocketChannel socketChannel = this.socketChannel;
        if (socketChannel == null) {
            throw new SocketException("Connection is closed.");
        }
        ByteBuffer byteBuffer = this.writeBuffer;
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            if (this.bufferPositionFix) {
                byteBuffer.compact();
                byteBuffer.flip();
            }
            if (socketChannel.write(byteBuffer) == 0) {
                break;
            }
        }
        byteBuffer.compact();
        return byteBuffer.position() == 0;
    }

    public int send(Connection connection, Object obj) {
        int i;
        if (this.socketChannel == null) {
            throw new SocketException("Connection is closed.");
        }
        synchronized (this.writeLock) {
            int position = this.writeBuffer.position();
            int lengthLength = this.serialization.getLengthLength();
            try {
                this.writeBuffer.position(this.writeBuffer.position() + lengthLength);
                this.serialization.write(connection, this.writeBuffer, obj);
                int position2 = this.writeBuffer.position();
                this.writeBuffer.position(position);
                this.serialization.writeLength(this.writeBuffer, (position2 - lengthLength) - position);
                this.writeBuffer.position(position2);
                if (position == 0 && !writeToSocket()) {
                    this.selectionKey.interestOps(5);
                } else {
                    this.selectionKey.selector().wakeup();
                }
                if (Log.DEBUG || Log.TRACE) {
                    float position3 = this.writeBuffer.position() / this.writeBuffer.capacity();
                    if (Log.DEBUG && position3 > 0.75f) {
                        Log.debug("kryonet", " TCP write buffer is approaching capacity: " + position3 + "%");
                    } else if (Log.TRACE && position3 > 0.25f) {
                        Log.trace("kryonet", " TCP write buffer utilization: " + position3 + "%");
                    }
                }
                this.lastWriteTime = System.currentTimeMillis();
                i = position2 - position;
            } catch (Throwable th) {
                throw new KryoNetException("Error serializing object of type: " + obj.getClass().getName(), th);
            }
        }
        return i;
    }

    public void close() {
        try {
            if (this.socketChannel != null) {
                this.socketChannel.close();
                this.socketChannel = null;
                if (this.selectionKey != null) {
                    this.selectionKey.selector().wakeup();
                }
            }
        } catch (IOException e) {
            if (Log.DEBUG) {
                Log.debug("kryonet", "Unable to close TCP connection.", e);
            }
        }
    }

    public boolean needsKeepAlive(long j) {
        return this.socketChannel != null && this.keepAliveMillis > 0 && j - this.lastWriteTime > ((long) this.keepAliveMillis);
    }

    public boolean isTimedOut(long j) {
        return this.socketChannel != null && this.timeoutMillis > 0 && j - this.lastReadTime > ((long) this.timeoutMillis);
    }
}
